package Server.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVUtil {
  private CSVReader csvReader;
  private CSVWriter csvWriter;
  private String path;

  public CSVUtil(String path) {
    try {
      csvReader = new CSVReader(new FileReader(path));
      csvWriter = new CSVWriter(new FileWriter(path, true));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<String[]> read() {
    List<String[]> lines = null;
    try {
      lines = csvReader.readAll();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return lines;
  }

  public void addLine(String... line) {
    // add line without quotes
    csvWriter.writeNext(line, false);
    try {
      csvWriter.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
