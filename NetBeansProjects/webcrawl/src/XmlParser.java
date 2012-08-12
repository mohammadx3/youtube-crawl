
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import org.jsoup.Jsoup;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammad Abbas
 */
public class XmlParser {
   public static void main(String args[]) throws IOException, BadLocationException{

//try{
    //        URL my_url2 = new URL("https://www.youtube.com/profile?user="+UserName);
    HTMLEditorKit kit2 = new HTMLEditorKit();
    HTMLDocument doc2 = (HTMLDocument) kit2.createDefaultDocument();
    doc2.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
   // Reader HTMLReader2 = new InputStreamReader(my_url2.openConnection().getInputStream());
   // kit2.read(HTMLReader2, doc, 0);
    String title2 = (String) doc2.getProperty(javax.swing.text.Document.TitleProperty);
    System.out.println(title2);
     final ArrayList<String> result2 = new ArrayList<String>();   
     //   try {
  //       BufferedReader br2 = new BufferedReader(new InputStreamReader(my_url2.openStream()));
        
        String strTemp2 = "";

       //  while(null != (strTemp2 = br2.readLine())){
      
             result2.add(strTemp2);
         }
        }
         
     //    catch (Exception ex) {
    //        ex.printStackTrace();
        
 //  }catch(Exception e){
    // e.printStackTrace();
 //  }
  //  try{  
//Iterator  iter2 = result.iterator();
//String curItem2="";
//int posSubscribers = 0;

//Thread.sleep(1000);
//while ( iter2.hasNext() == true ) {
     
//curItem2 =(String) iter2 .next();
//posSubscribers++;
///if(curItem2.contains("subscribers")){
   
//System.out.println("Subscribers:"+result.get(posSubscribers-1));
   
    
//}
 
 // }
//catch(Exception ex){}
    
//}
