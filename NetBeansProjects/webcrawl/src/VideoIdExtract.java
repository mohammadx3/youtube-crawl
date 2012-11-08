
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YtRating;
import com.google.gdata.data.youtube.YtStatistics;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
public class VideoIdExtract {
   


  public final static void main(String[] args) throws Exception{

//printVideoEntry(videoEntry, true);
String site = "http://www.youtube.com/watch?v=W2Cv5hZfOmk&feature=relmfu";
String clientId="Mohammad";
String developerkey = "AI39si62bwLP6OjQs0znOXEmrXCzEPhasnvzg_PcP8AaogxM-FhJjHDg7UOYZ9hgSr_9Z74ySKeGomoJF7IPjEAcV2c14fhvfw";
YouTubeService service = new YouTubeService(clientId , developerkey);
service.setUserCredentials("mohammadx3@gmail.com", "**************");

    
    String VideoID = RetrieveVideoId(site);
    List<String> links = LinksExtract.extractLinks(site);
    for (String link : links) {
           if(link.contains("watch?v")&& !link.contains(site))
        {
            VideoID = RetrieveVideoId(link);
            String videoEntryUrl = "http://gdata.youtube.com/feeds/api/videos/"+VideoID;
VideoEntry videoEntry = service.getEntry(new URL(videoEntryUrl), VideoEntry.class);
YtStatistics stats = videoEntry.getStatistics();
            String VideoLink = links.toString();
           Rating rating = videoEntry.getRating();
            YtRating ytrating = videoEntry.getYtRating();
            {
            if(stats != null && stats.getViewCount()>100000 ) {
               FBViews.fbview(VideoID);
             long fbviews = FBViews.k;
             long mobviews = FBViews.mobv;
             long twviews = FBViews.twv;
             long nLikes = ytrating.getNumLikes(); 
             long nDisLikes = ytrating.getNumDislikes();
             long viewcount = stats.getViewCount();
              String title =  videoEntry.getTitle().getPlainText();    
             float avgRating = rating.getAverage();
    
         try{
   
                  Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/linkdb","root","password");
    Statement stat = (Statement) con.createStatement();
    
    String insert = "Insert into links(title,vidid,totalViews,fbviews,mobviews,twviews,avgRating,nLikes,nDislikes) values('"+title+"','"+VideoID+"','"+viewcount+"','"+fbviews+"','"+mobviews+"','"+twviews+"','"+avgRating+"','"+nLikes+"','"+nDisLikes+"')";
    stat.executeUpdate(insert);
    
    }catch(Exception E){}
          
              System.out.println("   "+videoEntry.getTitle().getPlainText()+"\n"+link+"  View count: " + stats.getViewCount());
            }
          
      }
            }   
          }
        
   
  }
    private static String RetrieveVideoId(String site) {
       String ytreplace = "http://www.youtube.com/watch";
ytreplace = site.replaceAll(ytreplace+"\\?v=","");
StringBuffer sb = new StringBuffer(ytreplace);
StringBuffer VideoId = sb.delete(11,ytreplace.length());
   return VideoId.toString();
    }


private static void addLinksDB(String title, String vidId, long views){
    try{
    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/linkdb","root","password");
    Statement stat = (Statement) con.createStatement();
    String insert = "Insert into links values('"+title+"',"+vidId+",'"+views+"')";
    stat.executeUpdate(insert);
    System.out.println(title+vidId+views);
    }catch(Exception E){}
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
}
