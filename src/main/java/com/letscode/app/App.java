package com.letscode.app;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) throws IOException {
        ArrayList<String[]> results = readCSVFile("/Users/cassia/Documents/mod7-santander811matchesResult.csv");

//        for (String match[] : results) {
//            System.out.println(Arrays.toString(match));
//        }

        writeCSVFile(results);
    }

    private static void writeCSVFile(ArrayList<String[]> results) throws IOException {
        FileWriter out = new FileWriter("/Users/cassia/Documents/new-test-file.csv");
//        CSVFormat.DEFAULT.withHeader("time_1(mandante)", "time2(visitante)", "placar_time_1", "placar_time_1", "data").print(out);

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("time_1(mandante)", "time2(visitante)", "placar_time_1", "placar_time_1", "data"))) {
            for (String match[] : results) {
                printer.printRecord(match);
            }
        }
    }

    private static ArrayList<String[]> readCSVFile(String filePath) {
        ArrayList<String[]> records = new ArrayList<>();
        try(
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                CSVParser parser = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
        ) {
            for(CSVRecord record : parser) {
                String[] match = {record.get("time_1(mandante)"), record.get("time2(visitante)"), record.get("placar_time_1"), record.get("placar_time_2"), record.get("data")};
                records.add(match);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return records;
    }
}
