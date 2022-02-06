package com.letscode.app;

import com.letscode.app.services.HandleFiles;
import com.letscode.app.utils.Match;
import com.letscode.app.utils.Team;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class App
{
    public static final String FILES_PATH = "/Users/cassia/Documents/";

    public static void main(String[] args) throws IOException {
        String inputFile = FILES_PATH + "mod7-santander811matchesResult.csv";

        List<Match> resultsList = HandleFiles.readCSVFile(inputFile);
        List<Match> validResults = removeDuplicates(resultsList);
        List<Match> sortedResults = sortResultsByDateTeam1Team2(validResults);

        Set<Team> teamSet = extractTeamsList(sortedResults);

        teamSet.stream().forEach(team -> {
            List<Match> teamMatches = extractTeamMatches(sortedResults, team.getName());
            try {
                HandleFiles.generateTeamFile(teamMatches, team.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateTeamPunctuation(teamMatches, team);
        });

        List<Team> finalResultsTable = sortTeamsByTotalPoints(teamSet);
//        finalResultsTable.stream().forEach(team -> System.out.println(team.getName() + " " + team.getTotalPoints()));

        String outputFile = FILES_PATH + "output-file3-sorted.csv";
        HandleFiles.writeDataOnCSVFile(sortedResults, outputFile);
    }

    private static List<Match> removeDuplicates(List<Match> resultsList) {
        // TODO: better way to remove duplicates from list
        List<Match> nonDuplicatedResults = new ArrayList<>();
        boolean isNewMatch;

        nonDuplicatedResults.add(resultsList.get(0));

        for (int i = 1; i < resultsList.size(); i++) {
            isNewMatch = true;
            for (int j = 0; j < nonDuplicatedResults.size(); j++) {
                if (checkIfObjectsAreEqual(resultsList.get(i), nonDuplicatedResults.get(j))) {
                    isNewMatch = false;
                    break;
                }
            }
            if (isNewMatch) nonDuplicatedResults.add(resultsList.get(i));
        }

        return nonDuplicatedResults;
    }

    private static boolean checkIfObjectsAreEqual(Match match1, Match match2) {
        if (match1.getFirstTeam().equals(match2.getFirstTeam()) &&
                match1.getSecondTeam().equals(match2.getSecondTeam()) &&
                match1.getFirstTeamResult() == match2.getFirstTeamResult() &&
                match1.getSecondTeamResult() == match2.getSecondTeamResult() &&
                match1.getMatchDate().isEqual( match2.getMatchDate())) {
            return true;
        }
        return false;
    }

    private static List<Match> sortResultsByDateTeam1Team2(List<Match> validResults) {
        return validResults.stream()
                .sorted(Comparator.comparing(Match::getMatchDate).thenComparing(Match::getFirstTeam).thenComparing(Match::getSecondTeam))
                .collect(Collectors.toList());
    }

    private static Set<Team> extractTeamsList(List<Match> sortedList) {
        Set<Team> teamsList = new HashSet<>();
        sortedList.stream().forEach(match -> teamsList.add(new Team(match.getFirstTeam())));
        return teamsList;
    }

    private static List<Match> extractTeamMatches(List<Match> tournamentResults, String team) {
        List<Match> teamMatches = tournamentResults.stream().filter(match -> match.getFirstTeam().equals(team) || match.getSecondTeam().equals(team)).collect(Collectors.toList());
        return teamMatches;
    }
    
    private static void updateTeamPunctuation(List<Match> teamMatches, Team team) {
        teamMatches.stream().forEach(match -> {
            if (match.getFirstTeam().equals(team.getName())) {
                if (match.getFirstTeamResult() > match.getSecondTeamResult()) {
                    team.setVictories();
                } else if (match.getFirstTeamResult() < match.getSecondTeamResult()) {
                    team.setDefeats();
                } else {
                    team.setDraws();
                }
            } else if (match.getSecondTeam().equals( team.getName())) {
                if (match.getFirstTeamResult() < match.getSecondTeamResult()) {
                    team.setVictories();
                } else if (match.getFirstTeamResult() > match.getSecondTeamResult()) {
                    team.setDefeats();
                } else {
                    team.setDraws();
                }
            }
            team.setTotalPoints();
        });
    }

    private static List<Team> sortTeamsByTotalPoints(Set<Team> teamSet) {
        return teamSet.stream().sorted(Comparator.comparingInt(Team::getTotalPoints).reversed()).collect(Collectors.toList());
    }
}
