package com.letscode.app.services;

import com.letscode.app.App;
import com.letscode.app.utils.Match;
import com.letscode.app.utils.Team;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HandleFiles
{
    public static List<Match> readCSVFile(String filePath) {
        List<Match> results = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath));
            CSVParser records = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
            ) {
            for(CSVRecord record : records) {
                String firstTeam = record.get("time_1(mandante)");
                String secondTeam = record.get("time_2(visitante)");
                int firstTeamResult = Integer.parseInt(record.get("placar_time_1"));
                int secondTeamResult = Integer.parseInt(record.get("placar_time_2"));
                LocalDate matchDate = LocalDate.parse(record.get("data"));

                Match match = new Match(firstTeam, secondTeam, firstTeamResult, secondTeamResult, matchDate);

                results.add(match);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

//    public static void writeDataOnCSVFile(List<Match> results, String filePath) throws IOException {
//        FileWriter out = new FileWriter(filePath);
//
//        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withDelimiter(';').withHeader("time_1(mandante)", "time_2(visitante)", "placar_time_1", "placar_time_1", "data"))) {
//            results.stream().forEach(match -> {
//                try {
//                    printer.printRecord(match.getFirstTeam(), match.getSecondTeam(), match.getFirstTeamResult(), match.getSecondTeamResult(), match.getMatchDate());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//    }

    public static void generateTeamFile(List<Match> teamMatches, String team) throws IOException {
        // TODO: change to txt file and lines format
        FileWriter out = new FileWriter(App.FILES_PATH + "teamFiles/" + team + "-results.csv");

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withDelimiter(';').withHeader("time_1(mandante)", "time_2(visitante)", "placar_time_1", "placar_time_1", "data"))) {
            teamMatches.stream().forEach(match -> {
                try {
                    printer.printRecord(match.getFirstTeam(), match.getSecondTeam(), match.getFirstTeamResult(), match.getSecondTeamResult(), match.getMatchDate());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void writeTeamsOnCSVFile(List<Team> finalResultsTable, String filePath) throws IOException {
        FileWriter out = new FileWriter(filePath);

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withDelimiter(';').withHeader("Time", "Vitórias", "Empates", "Derrotas", "Pontos"))) {
            finalResultsTable.stream().forEach(team -> {
                try {
                    printer.printRecord(team.getName(), team.getVictories(), team.getDraws(), team.getDefeats(),team.getTotalPoints());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
