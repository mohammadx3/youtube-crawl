
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YtRating;
import com.google.gdata.data.youtube.YtStatistics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammad Abbas
 */
public class UploaderDetails {
  // public static String VideoID;

public static String UserName;
public static String Category;
public static String uploadDate;
  public static void getDetails(String VideoID) throws Exception{
try {
      URL my_url = new URL("http://www.youtube.com/watch?v="+VideoID);
    HTMLEditorKit kit = new HTMLEditorKit();
    HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
    doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
    Reader HTMLReader = new InputStreamReader(my_url.openConnection().getInputStream());
    kit.read(HTMLReader, doc, 0);
    String title = (String) doc.getProperty(javax.swing.text.Document.TitleProperty);
    System.out.println(title);
     final ArrayList<String> result = new ArrayList<String>();   
        try {
         BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));
        
        String strTemp = "";

         while(null != (strTemp = br.readLine())){
      
             result.add(strTemp);
         }
        }
         
         catch (Exception ex) {
            ex.printStackTrace();
        }
        
     
Iterator  iter = result.iterator();
String curItem="";
int posUser = 0;
int posDate = 0;
int posCategory = 0;
int length = 0;
UserName = "";
Thread.sleep(500);
while ( iter.hasNext() == true ) {
      posUser++;
      posDate++;
      posCategory++;
curItem =(String) iter .next();

if(curItem.contains("Uploaded by")){
   UserName = result.get(posUser-1).toString().replaceAll("</a>", "");
   StringBuilder sb = new StringBuilder(UserName);
  Scanner scan = new Scanner(sb.toString());
  scan.findInLine("href=");
  UserName = scan.next().toString().replaceAll("\"/user/","");
  UserName = UserName.replaceAll("\"","");
   uploadDate = result.get(posUser).toString();  
   uploadDate = uploadDate.replaceAll("<span id=\"eow-date\" class=\"watch-video-date\" >",""); 
   uploadDate = uploadDate.replaceAll("on\\s+","");

   uploadDate = uploadDate.replaceAll("</span>","");   
   System.out.println("Uploader:"+UserName+" Upload Date:"+uploadDate);
    
}
  if(curItem.contains("Category")){
   Category = result.get(posCategory+1).toString().replaceAll("</a></p>","");
   StringBuilder sb = new StringBuilder(Category);
   Scanner scan2 = new Scanner(sb.toString());
  scan2.findInLine("href=");
  Category = scan2.next().toString().replaceAll("/","");
  Category = Category.replaceAll("\"","");
  Category = Category.replaceAll(">","");
  length = Category.length()/2;
Category = Category.substring(length);
    System.out.println("Category:"+Category);
    
} 
      
  }

   
     
   
//                  Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/linkdb","root","password");
//    Statement stat = (Statement) con.createStatement();
//    
//    String update1 ="UPDATE links SET uploader = '"+UserName+"' where vidid ='"+VideoID+"'";
//    String update2 ="UPDATE links SET category = '"+Category+"' where vidid ='"+VideoID+"'";
//    String update3 ="UPDATE links SET uploadDate = '"+uploadDate+"' where vidid ='"+VideoID+"'";
//  stat.executeUpdate(update1);
//  stat.executeUpdate(update2);
//  stat.executeUpdate(update3);
    }catch(Exception E){
    E.printStackTrace();
    } 
   
  }
public static void VideoID() {
try{
    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/linkdb","root","password");
    Statement stat = (Statement) con.createStatement();
    
    String getvidID = "Select vidid from links where uploader is NULL";
    ResultSet vidID =  stat.executeQuery(getvidID);
    int i = 1;
    vidID.next();
    while(vidID.next()){
       
        String VideoID = vidID.getString("vidid");   
    
       System.out.println(i+": "+VideoID);
    i++;
    getDetails(VideoID);
    } 
         
            }catch(Exception ex){ex.printStackTrace();}

}
//public static void main(String args[]){
//VideoID();
//}
public static void setDetails(String VidID,String user,String category){


}
}