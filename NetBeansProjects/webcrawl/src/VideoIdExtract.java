
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohammad Abbas
 */
public class VideoIdExtract {
    public static void main(String[] arg){
String site = "http://www.youtube.com/watch?v=5Isr7YZj3DI&list=UUVtFOytbRpEvzLjvqGG5gxQ&index=2&feature=plcp";     
String ytreplace = "http://www.youtube.com/watch";

ytreplace = site.replaceAll(ytreplace+"\\?v=","");
    //    String[] VideoId=ytreplace.split(" ");
StringBuffer sb = new StringBuffer(ytreplace);
StringBuffer VideoId = sb.delete(11,ytreplace.length());
System.out.println(VideoId.toString());

}
}