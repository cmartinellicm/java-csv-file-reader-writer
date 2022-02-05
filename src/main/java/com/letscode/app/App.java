package com.letscode.app;

import com.letscode.app.utils.Match;
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

public class App
{
    public static void main(String[] args) throws IOException {
        String filePath = "/Users/cassia/Documents/mod7-santander811matchesResult.csv";

        List<Match> tournamentResults = readCSVFile(filePath);

        writeCSVFile(tournamentResults);
    }

    private static void writeCSVFile(List<Match> results) throws IOException {

        FileWriter out = new FileWriter("/Users/cassia/Documents/new-test-file-object.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("time_1(mandante)", "time_2(visitante)", "placar_time_1", "placar_time_1", "data"))) {
            results.stream().forEach(match -> {
                try {
                    printer.printRecord(match.getFirstTeam(), match.getSecondTeam(), match.getFirstTeamResult(), match.getSecondTeamResult(), match.getMatchDate());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static List<Match> readCSVFile(String filePath) {
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
}
