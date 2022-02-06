package com.letscode.app.services;

import com.letscode.app.utils.Match;
import com.letscode.app.utils.Team;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HandleData
{
    public static List<Match> sortMatchesByDateTeam1Team2(Set<Match> validResults) {
        return validResults.stream()
                .sorted(Comparator.comparing(Match::getMatchDate).thenComparing(Match::getFirstTeam).thenComparing(Match::getSecondTeam))
                .collect(Collectors.toList());
    }

    public static Set<Team> extractTeamSet(List<Match> sortedList) {
        Set<Team> teamsList = new HashSet<>();
        sortedList.stream().forEach(match -> teamsList.add(new Team(match.getFirstTeam())));
        return teamsList;
    }

    public static List<Match> extractTeamMatches(List<Match> tournamentResults, String team) {
        List<Match> teamMatches = tournamentResults.stream().filter(match -> match.getFirstTeam().equals(team) || match.getSecondTeam().equals(team)).collect(Collectors.toList());
        return teamMatches;
    }

    public static void updateTeamPoints(List<Match> teamMatches, Team team) {
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

    public static List<Team> sortTeamsByTotalPoints(Set<Team> teamSet) {
        return teamSet.stream().sorted(Comparator.comparingInt(Team::getTotalPoints).reversed()).collect(Collectors.toList());
    }
}
