package com.virtualminds.test.backend.utils.heplers;

import com.virtualminds.test.backend.entities.Caisse;
import com.virtualminds.test.backend.utils.error_managment.exceptions.CSVFileEmptyException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CSVHelper {

    // check for csv type
    public static String TYPE = "text/csv";
    // csv headers
    public static HashMap<String, String> HEADERS =
            new HashMap<String, String>() {
                {
                    // Adding elements to the Map
                    // using standard put() method
                    put("libelle", "libellé");
                    put("amountIn", "recettes");
                    put("amountOut", "dépenses");
                    put("operationDate", "date d'opération");
                }
            };


    //check if a file is a csv
    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    // transfor csv to caisse list
    public static List<Caisse> csvToCaisses(InputStream is) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try (
                BufferedReader fileReader =
                        new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        ) {

            List<Caisse> caisses = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            long size = csvRecords.spliterator().getExactSizeIfKnown();
            if (size <= 0) {
                throw new CSVFileEmptyException("Your CSV file is empty");
            }

            for (CSVRecord csvRecord : csvRecords) {

                Caisse caisse = new Caisse(
                        (csvRecord.get(HEADERS.get("libelle")) == null || csvRecord.get(HEADERS.get("libelle")).length() == 0) ? "Ajouter un libellé " : csvRecord.get(HEADERS.get("libelle")),
                        csvRecord.get(HEADERS.get("amountIn")).matches("\\d+(\\.\\d+)?") ? Double.parseDouble(csvRecord.get(HEADERS.get("amountIn"))) : 0,
                        csvRecord.get(HEADERS.get("amountOut")).matches("\\d+(\\.\\d+)?") ? Double.parseDouble(csvRecord.get(HEADERS.get("amountOut"))) : 0,
                        csvRecord.get(HEADERS.get("operationDate")).matches("\\d{4}-\\d{2}-\\d{2}") ? formatter.parse(csvRecord.get(HEADERS.get("operationDate"))) : java.sql.Date.valueOf(LocalDate.now()));

                caisses.add(caisse);
            }

            return caisses;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());

        }
    }
}
