package sea;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class searchdemo {

    public searchdemo() {
        new Thread(new search1()).start();
    }

    public static void main(String[] args) {
        new searchdemo();
    }
}

class search1 implements Runnable {

    public void run() {

        {
            try {

                String keyword = "cartoon";

                String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=40&order=viewCount&q=" + keyword + "&key=" + "AIzaSyDCVRAsSAkZ9K6sl8L6WWsVwrUX30uJS7s" + "";// ehde to video andiya ne kinya chadiya ne ohh vekh sakde haaaaaa

                Document doc = null;  // next 15 line connect with youtube
                try {
                    doc = Jsoup.connect(url).ignoreContentType(true).get();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                String getJson = doc.text();

                JSONArray object_array = new JSONArray();
                if (getJson != null) {
                    // your code goes here to parse and display the list video data

                    //code to parse the json file
                    JSONParser parser = new JSONParser();

                    try {

                        JSONObject jobj = (JSONObject) parser.parse(getJson);

                        object_array = (JSONArray) jobj.get("items");

                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }

                for (int i = 0; i < object_array.size(); i++) // eh video list de panel di working aa
                {

                    JSONObject item = (JSONObject) object_array.get(i);

                    //extract title and description from objects
                    JSONObject snippet = (JSONObject) item.get("snippet");

                    String title = (String) snippet.get("title");
                    String description = (String) snippet.get("description");
                    JSONObject id = (JSONObject) item.get("id");
                    String videoid = (String) id.get("videoId");
                    
                    
                    System.out.println(title);
                    
   
                    }
                }
             catch (Exception ae) {
                ae.printStackTrace();
            }
        }
    }

}
