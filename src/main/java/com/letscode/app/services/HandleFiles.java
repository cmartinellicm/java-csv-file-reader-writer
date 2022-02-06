package com.letscode.app.services;

import com.letscode.app.App;
import com.letscode.app.utils.Match;
import com.letscode.app.utils.Team;
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

public class HandleFiles
{
    public static Set<Match> readCSVFile(String filePath) {
        Set<Match> results = new HashSet<>();

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

    public static void generateTeamFile(List<Match> teamMatches, String team) throws IOException {
        FileWriter out = new FileWriter(App.FILES_PATH + "teamFiles/" + team + "-history.txt");

        try (PrintWriter printer = new PrintWriter(out)) {
            teamMatches.stream().forEach(match -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yy");
                String dateString = match.getMatchDate().format(formatter);
                printer.println(dateString + ": " + match.getFirstTeam() + " " + match.getFirstTeamResult() + " x " + match.getSecondTeamResult() + " " + match.getSecondTeam());
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
