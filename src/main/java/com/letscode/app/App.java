package com.letscode.app;

import com.letscode.app.services.HandleCSVFiles;
import com.letscode.app.utils.Match;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main(String[] args) throws IOException {
        final String FILES_PATH = "/Users/cassia/Documents/";
        String inputFile = FILES_PATH + "mod7-santander811matchesResult.csv";

        List<Match> resultsList = HandleCSVFiles.readCSVFile(inputFile);
        List<Match> validResults = removeDuplicates(resultsList);

        String outputFile = FILES_PATH + "output-file2.csv";
        HandleCSVFiles.writeDataOnCSVFile(validResults, outputFile);
    }

    private static List<Match> removeDuplicates(List<Match> resultsList) {
        List<Match> nonDuplicatedResults = new ArrayList<>();
        boolean isNewMatch;

        nonDuplicatedResults.add(resultsList.get(0));

        for (int i = 1; i < resultsList.size(); i++) {
            isNewMatch = true;
            for (int j = 0; j < nonDuplicatedResults.size(); j++) {
                if (checkIfEqual(resultsList.get(i), nonDuplicatedResults.get(j))) {
                    isNewMatch = false;
                    break;
                }
            }
            if (isNewMatch) nonDuplicatedResults.add(resultsList.get(i));
        }

        return nonDuplicatedResults;
    }

    private static boolean checkIfEqual(Match match1, Match match2) {
        if (match1.getFirstTeam().equals(match2.getFirstTeam()) &&
                match1.getSecondTeam().equals(match2.getSecondTeam()) &&
                match1.getFirstTeamResult() == match2.getFirstTeamResult() &&
                match1.getSecondTeamResult() == match2.getSecondTeamResult() &&
                match1.getMatchDate().isEqual( match2.getMatchDate())) {
            return true;
        }
        return false;
    }
}
