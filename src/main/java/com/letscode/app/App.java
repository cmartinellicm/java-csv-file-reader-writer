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

        Set<Match> validResults = HandleFiles.readCSVFile(inputFile);
        List<Match> sortedResults = HandleData.sortResultsByDateTeam1Team2(validResults);

        Set<Team> teamSet = HandleData.extractTeamsList(sortedResults);

        teamSet.stream().forEach(team -> {
            List<Match> teamMatches = HandleData.extractTeamMatches(sortedResults, team.getName());
            try {
                HandleFiles.generateTeamFile(teamMatches, team.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            HandleData.updateTeamPunctuation(teamMatches, team);
        });

        List<Team> finalResultsTable = HandleData.sortTeamsByTotalPoints(teamSet);

        String outputFile = FILES_PATH + "output-final.csv";
        HandleFiles.writeTeamsOnCSVFile(finalResultsTable, outputFile);
    }
}
