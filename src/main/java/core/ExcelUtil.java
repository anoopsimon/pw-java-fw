package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

public class ExcelUtil {

    public static void read(String file) throws Exception {

        try (InputStream is = new FileInputStream(new File(file)); ReadableWorkbook wb = new ReadableWorkbook(is)) {
            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows = sheet.openStream()) {
                rows.forEach(r -> {
                    String value = r.getCellAsString(1).orElse(null);
                    System.out.println("Value " + value);
                });
            }
        }

    }

}
