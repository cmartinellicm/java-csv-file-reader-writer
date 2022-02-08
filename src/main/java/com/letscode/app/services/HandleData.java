package com.letscode.app.services;

import com.letscode.app.entities.Match;
import com.letscode.app.entities.Team;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HandleData
{
    public static List<Match> sortMatchesByDateTeam1Team2(Set<Match> matchSet) {
        return matchSet.stream()
                .sorted(Comparator.comparing(Match::getMatchDate).thenComparing(Match::getFirstTeam).thenComparing(Match::getSecondTeam))
                .collect(Collectors.toList());
    }

    public static List<Team> sortTeamsByTotalPoints(Set<Team> teamSet) {
        return teamSet.stream().sorted(Comparator.comparingInt(Team::getTotalPoints).reversed()).collect(Collectors.toList());
    }

    public static Set<Team> extractTeamSet(List<Match> matchList) {
        Set<Team> teamSet = new HashSet<>();
        matchList.stream().forEach(match -> teamSet.add(new Team(match.getFirstTeam())));
        return teamSet;
    }

    public static List<Match> extractTeamMatches(List<Match> matchList, Team team) {
        String teamName = team.getName();
        return matchList.stream().filter(match -> match.getFirstTeam().equals(teamName) || match.getSecondTeam().equals(teamName)).collect(Collectors.toList());
    }

    public static void updateTeamPoints(List<Match> teamMatches, Team team) {
        String teamName = team.getName();
        teamMatches.stream().forEach(match -> {
            if (match.getFirstTeam().equals(teamName)) {
                if (match.getFirstTeamResult() > match.getSecondTeamResult()) {
                    team.setVictories();
                } else if (match.getFirstTeamResult() < match.getSecondTeamResult()) {
                    team.setDefeats();
                } else {
                    team.setDraws();
                }
            } else if (match.getSecondTeam().equals(teamName)) {
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
}
