package com.letscode.app.services;

import com.letscode.app.entities.Match;
import com.letscode.app.entities.Team;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.letscode.app.App.OUTPUT_FILES_PATH;

public class HandleFiles
{
    public static Set<Match> readCSVFile(String filePath) {
        Set<Match> matchSet = new HashSet<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath));
            CSVParser records = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
            ) {
            for(CSVRecord record : records) {
                String firstTeam = record.get("time_1(mandante)");
                String secondTeam = record.get("time_2(visitante)");
                int firstTeamResult = Integer.parseInt(record.get("placar_time_1"));
                int secondTeamResult = Integer.parseInt(record.get("placar_time_2"));
                LocalDate matchDate = LocalDate.parse(record.get("data"));

                Match newMatch = new Match(firstTeam, secondTeam, firstTeamResult, secondTeamResult, matchDate);
                matchSet.add(newMatch);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return matchSet;
    }

    public static void writeTeamTxtFile(List<Match> matchList, Team team) throws IOException {
        String teamName = team.getName();
        FileWriter out = new FileWriter(OUTPUT_FILES_PATH + teamName + "-history.txt");

        try (PrintWriter printer = new PrintWriter(out)) {
            matchList.stream().forEach(match -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yy");
                String dateString = match.getMatchDate().format(formatter);
                printer.println(dateString + ": " + match.getFirstTeam() + " " + match.getFirstTeamResult() + " x " + match.getSecondTeamResult() + " " + match.getSecondTeam());
            });
        }
    }

    public static void writeResultsCsvFile(List<Team> finalResultsTable, String filePath) throws IOException {
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
