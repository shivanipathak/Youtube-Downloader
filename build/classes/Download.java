package youtubedownloader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.*;

public class Download
{

    Download()
    {

        try
        {
            URL url = new URL("http://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            DataInputStream dis = new DataInputStream(connection.getInputStream());
            long l = connection.getContentLength();
            byte b[] = new byte[1024];
            String extension = connection.getContentType();
            System.out.println(extension);

            extension = extension.substring(extension.indexOf("/") + 1);
            System.out.println(extension);

            FileOutputStream fos = new FileOutputStream("C:\\Users\\Dell\\Desktop\\abc." + extension);
            int count = 0;
            while (true)
            {
                int r = dis.read(b, 0, b.length);
                fos.write(b, 0, r);
                count = count + r;

                if (count == l)
                {
                    break;
                }
            }
            fos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
