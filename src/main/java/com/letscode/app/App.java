package com.letscode.app;

import com.letscode.app.services.HandleFiles;
import com.letscode.app.services.HandleData;
import com.letscode.app.utils.Match;
import com.letscode.app.utils.Team;

import java.io.IOException;
import java.util.*;

public class App
{
    public static final String FILES_PATH = "/Users/cassia/Documents/";

    public static void main(String[] args) throws IOException {
        String inputFile = FILES_PATH + "santander811matchesResult.csv";

        Set<Match> validMatchesSet = HandleFiles.readCSVFile(inputFile);
        List<Match> matchListByDate = HandleData.sortMatchesByDateTeam1Team2(validMatchesSet);

        Set<Team> teamSet = HandleData.extractTeamSet(matchListByDate);

        for (Team team : teamSet) {
            List<Match> teamMatchesList = HandleData.extractTeamMatches(matchListByDate, team.getName());

            HandleFiles.generateTeamFile(teamMatchesList, team);
            HandleData.updateTeamPoints(teamMatchesList, team);
        }

        List<Team> finalResultsList = HandleData.sortTeamsByTotalPoints(teamSet);

        String outputFile = FILES_PATH + "final-results.csv";
        HandleFiles.writeTeamsOnCSVFile(finalResultsList, outputFile);
    }
}
