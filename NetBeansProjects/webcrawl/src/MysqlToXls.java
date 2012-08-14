
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import org.jsoup.Jsoup;
import java.io.*;
import java.sql.*;
import org.apache.poi.hssf.usermodel.*;
import java.util.*;
import javax.swing.JOptionPane;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammad Abbas
 */

  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author raj
 */


public class MysqlToXls 
{
  public MysqlToXls()throws ClassNotFoundException, SQLException 
  {
      try
                {
                    HSSFWorkbook xlsWorkbook = new HSSFWorkbook();
                    HSSFSheet xlsSheet = xlsWorkbook.createSheet();
                    short rowIndex = 0;
                         Class.forName("com.mysql.jdbc.Driver");
                       Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost/linkdb","root","password");
                         PreparedStatement stmt = conn.prepareStatement("select * from links where mobviews=0 and fbviews=0 and twviews = 0 order by uploadDate;");
                         ResultSet rs = stmt.executeQuery();
                    ResultSetMetaData colInfo = rs.getMetaData();
                    List<String> colNames = new ArrayList<String>();
                    HSSFRow titleRow = xlsSheet.createRow(rowIndex++);

    for (int i = 1; i <= colInfo.getColumnCount(); i++) 
                 {
            colNames.add(colInfo.getColumnName(i));
            titleRow.createCell((short) (i-1)).setCellValue(
            new HSSFRichTextString(colInfo.getColumnName(i)));
            xlsSheet.setColumnWidth((short) (i-1), (short) 4000);
    }

    while (rs.next()) 
    {
      HSSFRow dataRow = xlsSheet.createRow(rowIndex++);
      short colIndex = 0;
      for (String colName : colNames) {
          dataRow.createCell(colIndex++).setCellValue(
          new HSSFRichTextString(rs.getString(colName)));
      }
    }
    xlsWorkbook.write(new FileOutputStream(new File("C:/Users/Mohammad Abbas/Desktop/no-FB-MOBViews1.xls")));
    conn.close();
                                          
                }
                catch(Exception r)
                {
                    JOptionPane.showMessageDialog(null, r);
                }
  }
  public static void main(String[] args) 
  {
    try {
      MysqlToXls mysqlToXls = new MysqlToXls();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}