package Server.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

/* 
  * Classe utilitária para manipulação de arquivos CSV
*/
public class CSVUtil {
  private CSVReader csvReader;
  private CSVWriter csvWriter;
  private String path;

  public CSVUtil(String path) {

    try {
      csvReader = new CSVReader(new FileReader(path));
      csvWriter = new CSVWriter(new FileWriter(path, true));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public List<String[]> read() {
    // Leitura de arquivo CSV
    List<String[]> lines = null;
    try {
      lines = csvReader.readAll();
    } catch (IOException | CsvException e) {
      e.printStackTrace();
    }

    return lines;
  }

  public void addLine(String... line) {
    // Adiciona linha (sem aspas)
    csvWriter.writeNext(line, false);
    try {
      csvWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
