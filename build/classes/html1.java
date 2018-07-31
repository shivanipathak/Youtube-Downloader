
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class html1 
{
       public static void main(String[] args)
    {
        try
        {
            File f = new File("C:\\one.html");
            if (!f.exists())
            {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);

            Socket sock = new Socket("www.tutorialspoint.com", 80);
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            DataInputStream dis = new DataInputStream(sock.getInputStream());
            dos.writeBytes("GET / HTTP/1.1\r\n");
            dos.writeBytes("host : www.tutorialspoint.com:80\r\n");
            dos.writeBytes("\r\n");
            int flag=0;

            while (true)
            {
                String s = dis.readLine();
                if (s == null)
                {
                    break;
                } else
                {
                    if(s.equals(""))
                        flag=1;
                    if(flag==1)
                        pw.println(s);
                    
                }
                System.out.println(s);
            }

            pw.close();
            fw.close();
            sock.close();
            dis.close();
            dos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
