
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ytstats {
public static String fbviews;
public static String mobviews;
public static String twviews;
public static String subviews;
public static long fbv;
public static long mobv;
public static long twv;
public static long tb;
public static long subv;
public static boolean fblogic = false;
public static boolean twlogic = false;
public static boolean moblogic = false;
public static boolean sublogic = false;
public static String strTemp="";
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) throws IOException, BadLocationException  {
       
    URL my_url = new URL("http://www.youtube.com/insight_ajax?action_get_statistics_and_data=1&v=mlGxBDRDRH0");
    HTMLEditorKit kit = new HTMLEditorKit();
    HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
    doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
    Reader HTMLReader = new InputStreamReader(my_url.openConnection().getInputStream());
    kit.read(HTMLReader, doc, 0);
    String title = (String) doc.getProperty(Document.TitleProperty);
    System.out.println(title);
     final ArrayList<String> result = new ArrayList<String>();   
        try {
         BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));
        
        String strTemp = "";

         while(null != (strTemp = br.readLine())){
      
             result.add(strTemp);
    
    //  printLinks(scan,strTemp);
       
         }
        
        }
         
         catch (Exception ex) {
            ex.printStackTrace();
        }
Iterator  iter = result.iterator();
String curItem="";
                       

int posfb=0;
            int postw=0;
                        int posmob=0;

while ( iter.hasNext() == true ) 
{
     posmob=posmob+1;
     posfb=posfb+1;
     postw=postw+1;
 curItem =(String) iter .next();
//System.out.println(curItem);
 if (curItem.contains("facebook"))
{
int fbvloc = posfb+3;
fbviews = result.get(fbvloc).toString().replaceAll("views", "");
StringBuffer sb = new StringBuffer (fbviews);
sb = sb.delete(0,40);
fbviews = sb.toString();
fbviews = fbviews.replaceAll(",", "");
fbviews = fbviews.replaceAll("\\s+", "");
 tb = fbv;
fbv = Long.parseLong(fbviews);
System.out.println("Facebook Views:"+fbv);
fblogic=true;
}
if (curItem.contains("mobile"))
{
int mobloc = posmob+3;
//System.out.println("MOB LOC"+result.get(posmob+3));
mobviews = result.get(mobloc).toString().replaceAll("views", "");
StringBuffer sb = new StringBuffer (mobviews);
sb = sb.delete(0,40);
mobviews = sb.toString();
mobviews = mobviews.replaceAll(",","");
mobviews = mobviews.replaceAll("\\s+","");
mobv = Long.parseLong(mobviews);
System.out.println("Mobile Views:"+mobv);
moblogic=true;
}

if (curItem.contains("twitter"))
{
int twloc = postw+3;
System.out.println(result.get(twloc));
twviews = result.get(twloc).toString().replaceAll("views", "");
StringBuffer sb = new StringBuffer (twviews);
sb = sb.delete(0,40);
twviews = sb.toString();
twviews = twviews.replaceAll(",","");
twviews = twviews.replaceAll("\\s+","");
twv = Long.parseLong(twviews);
System.out.println("Twitter Views:"+twv);
twlogic=true;
}
if (curItem.contains("Subscriber"))
{
int subloc = postw+3;
subviews = result.get(subloc).toString().replaceAll("views", "");
StringBuffer sb = new StringBuffer (subviews);
sb = sb.delete(0,40);
subviews = sb.toString();
subviews = subviews.replaceAll(",","");
subviews = subviews.replaceAll("\\s+","");
subv = Long.parseLong(subviews);
System.out.println("Subscriber Views:"+subv);
sublogic=true;
}

if(fblogic==false){
    fbv = 0;
}
        
if(twlogic==false){
    twv=0;
}
if(moblogic==false){
    mobv=0;
}
      
   
    }

}
}

   
    
   