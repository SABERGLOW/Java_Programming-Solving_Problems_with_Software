import edu.duke.*;
import java.io.*;

public class Part4
{
    public void printUrl(String URL)
    {
        URLResource UR = new URLResource(URL);
        for(String WORDS: UR.words())
        {
            if(WORDS.toLowerCase().contains("youtube.com")) //... means we have found youtube.com
            {
                int startIndex = WORDS.indexOf("\"");
                int endIndex = WORDS.lastIndexOf("\"");
                System.out.println(WORDS.substring(startIndex+1, endIndex));
            }
        }
    }

    public void testUrl()
    {
        printUrl("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }

    public static void main (String[] args)
    {
        Part4 URL = new Part4();
        URL.testUrl();
    }
}

