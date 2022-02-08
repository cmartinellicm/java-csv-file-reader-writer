package com.letscode.app;

import com.letscode.app.services.HandleFiles;
import com.letscode.app.services.HandleData;
import com.letscode.app.entities.Match;
import com.letscode.app.entities.Team;

import java.io.IOException;
import java.util.*;

public class App
{
    public static final String INPUT_FILES_PATH = "src/main/resources/";
    public static final String OUTPUT_FILES_PATH = "target/output-files/";

    public static void main(String[] args) throws IOException {
        String inputFile = INPUT_FILES_PATH + "championshipResult2022.csv";

        Set<Match> validMatchesSet = HandleFiles.readCSVFile(inputFile);
        List<Match> matchListByDate = HandleData.sortMatchesByDateTeam1Team2(validMatchesSet);

        Set<Team> teamSet = HandleData.extractTeamSet(matchListByDate);

        for (Team team : teamSet) {
            List<Match> teamMatchesList = HandleData.extractTeamMatches(matchListByDate, team);

            HandleFiles.writeTeamTxtFile(teamMatchesList, team);
            HandleData.updateTeamPoints(teamMatchesList, team);
        }

        List<Team> finalResultsList = HandleData.sortTeamsByTotalPoints(teamSet);

        String outputFile = OUTPUT_FILES_PATH + "final-results.csv";
        HandleFiles.writeResultsCsvFile(finalResultsList, outputFile);
    }
}
