
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YtRating;
import com.google.gdata.data.youtube.YtStatistics;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.sql.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.BadLocationException;
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

public class LinksExtract {
   public static float avgRating=0;
public  static String link;
public static long fbviews = 0;
public static long twviews = 0;
public static long mobviews = 0;
public static long viewcount = 0;
public static long nLikes = 0;
public static long nDislikes = 0;



  public static void main(String[] args) throws Exception{
 String link2crawl = "http://www.youtube.com/watch?v=W2Cv5hZfOmk&feature=relmfu";
visitLinks(link2crawl);
getLinksDB();
        
  }

  private static String RetrieveVideoId(String site) {
       String ytreplace = "http://www.youtube.com/watch";
ytreplace = site.replaceAll(ytreplace+"\\?v=","");
StringBuffer sb = new StringBuffer(ytreplace);
StringBuffer VideoId = sb.delete(11,ytreplace.length());
   return VideoId.toString();
    }

public static List<String>extractLinks(String url) throws IOException {
    final ArrayList<String> result = new ArrayList<String>();

    Document doc = Jsoup.connect(url).get();

    Elements links = doc.select("a[href]");

    for (Element link : links) {
      result.add(link.attr("abs:href"));
    }
    
  return result;  
  }
public static void visitLinks(String site) throws AuthenticationException, IOException, BadLocationException, ServiceException{
String clientId="Mohammad";
String developerkey = "AI39si62bwLP6OjQs0znOXEmrXCzEPhasnvzg_PcP8AaogxM-FhJjHDg7UOYZ9hgSr_9Z74ySKeGomoJF7IPjEAcV2c14fhvfw";
YouTubeService service = new YouTubeService(clientId , developerkey);
service.setUserCredentials("mohammadx3@gmail.com", "ninjagaidensx3");

    
    String VideoID = RetrieveVideoId(site);
    List<String> links = LinksExtract.extractLinks(site);
    for (String link : links) {
           if(link.contains("watch?v")&&!link.contains(site))
        {
            VideoID = RetrieveVideoId(link);
            String videoEntryUrl = "http://gdata.youtube.com/feeds/api/videos/"+VideoID;
VideoEntry videoEntry = service.getEntry(new URL(videoEntryUrl), VideoEntry.class);
 Rating rating = videoEntry.getRating();
YtStatistics stats = videoEntry.getStatistics();
            String VideoLink = links.toString();
         YtRating ytrating = videoEntry.getYtRating();
            
            {
            if(stats != null && stats.getViewCount()>100000 ) {
             FBViews.fbview(VideoID);
             fbviews = FBViews.fbv;
             mobviews = FBViews.mobv;
             twviews = FBViews.twv;
             viewcount = stats.getViewCount();
             avgRating = rating.getAverage();
             nLikes = ytrating.getNumLikes();
             nDislikes = ytrating.getNumDislikes();
  
             
             String title =  videoEntry.getTitle().getPlainText();    
         try{
   
                  Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/linkdb","root","password");
    Statement stat = (Statement) con.createStatement();
    
    String insert = "Insert into links(title,vidid,totalViews,fbviews,mobviews,twviews,avgRating,nLikes,nDisLikes) values('"+title+"','"+VideoID+"','"+viewcount+"','"+fbviews+"','"+mobviews+"','"+twviews+"','"+avgRating+"','"+nLikes+"','"+nDislikes+"')";
    stat.executeUpdate(insert);
    
    }catch(Exception E){}
          
              System.out.println("   "+videoEntry.getTitle().getPlainText()+"\n"+link+"  View count: " + stats.getViewCount());
            }
          
      }
            }   
          }
}

private static void getLinksDB(){
    
try{
    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/linkdb","root","password");
    Statement stat = (Statement) con.createStatement();
    
    String getvidID = "Select vidid from links ORDER by id";
    ResultSet vidID =  stat.executeQuery(getvidID);
    while(vidID.next()){
       
           String dbLink = "http://www.youtube.com/watch?v="+vidID.getString("vidid");        
      System.out.println("New Crawl "+dbLink);
    visitLinks(dbLink);
    }
  getLinksDB();
}catch(Exception ex){}
}
}