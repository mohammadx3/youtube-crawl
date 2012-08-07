
import java.io.*;
import java.net.URL;
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

URL urlLink = new URL("http://www.youtube.com/insight_ajax?action_get_statistics_and_data=1&v=WKXZGlTM2OM");
String site = urlLink.toString();    
HTMLEditorKit kit = new HTMLEditorKit();
       
    HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
    doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
    Reader HTMLReader = new InputStreamReader(urlLink.openConnection().getInputStream());
    kit.read(HTMLReader, doc, 0);  

    BufferedReader br = new BufferedReader(new InputStreamReader(urlLink.openStream()));
            String strTemp = "";
            while(null != (strTemp = br.readLine())){
        
        Scanner scan = new Scanner(strTemp); 
       StringBuffer sb = new StringBuffer(strTemp);
        String html = sb.toString();
        String tableTag = 
     Pattern.compile(".*?<dd.*?event.*?>(.*?)</dd>.*?", Pattern.DOTALL).matcher(strTemp).replaceFirst("$1");

String charSet = "ISO-8859-1";
String innerHtml = Jsoup.parse(scan.toString(),charSet).select("body").html().toString();
System.out.println(tableTag);
}
}
}
