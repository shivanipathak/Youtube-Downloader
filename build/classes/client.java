
import java.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class client {

    public static void main(String[] args) 
    {
       
        try
        {
             Socket sock= new Socket("127.0.0.1",9000);
            DataInputStream dis = new DataInputStream(sock.getInputStream());
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            
            String s = dis.readLine();
            System.out.println(s);
            dos.writeBytes("Hello Server \r\n");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }
        

    }

}
