import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class server {

    ServerSocket sersock;
    FileOutputStream fos;
    FileInputStream fis;
    String filename;

    server() {
        try {
            sersock = new ServerSocket(8000);
            while (true) {
                Socket sock = sersock.accept();
                Clienthandler obj = new Clienthandler(sock);
                Thread t = new Thread(obj);
                t.start();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    class Clienthandler implements Runnable {

        Socket sock;

    DataInputStream dis;
    DataOutputStream dos;
        Clienthandler(Socket sock) {
            this.sock = sock;
        }

        @Override
        public void run()
        {
            try {
                dis = new DataInputStream(sock.getInputStream());
                dos = new DataOutputStream(sock.getOutputStream());

                String s = dis.readLine();
                System.out.println(s);
                if (s.equals("Hello Server")) 
                {
                    dos.writeBytes("Hello Client \r\n");

                    System.out.println(s);
                }
                else if (s.equals("signuprequest"))
                {
                    System.out.println("if");
                    signup(dis,dos);
                }
                else if(s.equals("loginrequest"))
                {
                    login(dis,dos);
                }
                else if(s.equals("changepassword"))
                {
                    changepassword(dis,dos);
                }
                else if(s.equals("addtofavourites"))
                {
                    favourites(dis,dos);
                }
                else if(s.equals("watchlater"))
                {
                    System.out.println("working");
                    try
          {
              String name= dis.readLine();
              String videoid = dis.readLine();
              String title = dis.readLine();
              
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from watchlater where username1='" + name + "' and videoid1='"+videoid+"'");
              System.out.println("in");
              System.out.println(videoid);
            if(rs.next())
            {
                System.out.println("chlta hai");
                dos.writeBytes("alreadyadded\r\n");
            }
            
            else
            {
                rs.moveToInsertRow();
                rs.updateString("username1", name);
                rs.updateString("videoid1",videoid);
                rs.updateString("title1", title);
                
                rs.insertRow();
                System.out.println("kya ye chla?");
                dos.writeBytes("added\r\n");
            }
          }
          catch(Exception ex)
          {
              ex.printStackTrace();
          }
                }
                else if(s.equals("watchlist"))
                {
                    watchlist(dis,dos);
                }
                else if(s.equals("favouritelist"))
                {
                    favouriteslist(dis,dos);
                }
                else if(s.equals("delete"))
                {
                    System.out.println("pagal kahin ki");
                    delfavourites(dis, dos);
                }
                        
                            
                        

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    void signup(DataInputStream dis,DataOutputStream dos) {

        try {
            dos.writeBytes("Send data\r\n");
            String name = dis.readLine();
            String password = dis.readLine();
            String email = dis.readLine();
            String phone = dis.readLine();

            byte b[] = new byte[100000];
            long size = Long.parseLong(dis.readLine());
            filename = dis.readLine();
            String path = "C:\\uploads\\" + name;
            int count = 0;
            fos = new FileOutputStream(path);

            while (true) {
                int r = dis.read(b, 0, 100000);
                count += r;
                fos.write(b, 0, r);
                if (count == size) {
                    break;
                }
                
                
            }
            fos.close();

            System.out.println(name);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from signup where username='" + name + "'");

            if (rs.next()) {
                dos.writeBytes("Failed\r\n");
            } else {
                rs.moveToInsertRow();
                rs.updateString("username", name);
                rs.updateString("password", password);
                rs.updateString("email", email);
                rs.updateString("phone", phone);
                rs.updateString("path", path);
                rs.insertRow();
                dos.writeBytes("Signup successful\r\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
     void login(DataInputStream dis,DataOutputStream dos)
    {
        try
        {
//            dos.writeBytes("send data\r\n");
            String name= dis.readLine();
            String password = dis.readLine();
            System.out.println(name+" "+password);
            
            
            
             
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from signup where username='" + name + "' and password='"+password+"'");
            
           
                if(rs.next())
                {
                    dos.writeBytes("successful\r\n");
                    
                    String s = rs.getString("path");
                    
                    byte b[] = new byte[100000];
                    
                    fis = new FileInputStream(s);
                    long size=fis.available();
                    System.out.println("the size"+size);
                    dos.writeBytes(size+"\r\n");
                    
                    
                    
                    while (true) {

                            int r = fis.read(b, 0, 100000);
                            if (r == -1) {
                                break;
                            }
                            dos.write(b, 0, r);
                    
                   
                    }
                    
                    fis.close();
                    
                }    
                
                else
                {
                            dos.writeBytes("failed\r\n");
               }
                    
                }   
    
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
     
        
    }
     
      void changepassword(DataInputStream dis,DataOutputStream dos)
     {
         try
         {
         String name = dis.readLine();
         String oldpassword = dis.readLine();
         String newpassword = dis.readLine();
         
         
          Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from signup where username='" + name + "' and password='"+oldpassword+"'");
            
            if(rs.next())
            {
                rs.updateString("password", newpassword);
                rs.updateRow();
                dos.writeBytes("success\r\n");
            }
            
            else
            {
                dos.writeBytes("fail\r\n");
            }
         
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
         
     }
      
      void favourites(DataInputStream dis,DataOutputStream dos)
      {
        try 
        {
            String name = dis.readLine();
            String videoid = dis.readLine();
            String title =dis.readLine();
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from favorites where username='" + name + "' and videoid='"+videoid+"'");
            
            if(rs.next())
            {
                dos.writeBytes("alreadyadded\r\n");
            }
            else
            {
                rs.moveToInsertRow();
                rs.updateString("username", name);
                rs.updateString("videoid",videoid);
                rs.updateString("title", title);
                rs.insertRow();
                dos.writeBytes("added\r\n");
            }
        } 
       catch (Exception ex) 
        {
            ex.printStackTrace();
        }
            
      }
      
      
      void watchlater(DataInputStream dis,DataOutputStream dos)
      {
          
      }
      
      
      void watchlist(DataInputStream dis,DataOutputStream dos)
      {
        try 
        {
            String name = dis.readLine();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from watchlater where username1='" + name +"'");
            
            while(rs.next())
            {
                dos.writeBytes("sending\r\n");
                dos.writeBytes(rs.getString("videoid1")+"\r\n");
                dos.writeBytes(rs.getString("title1")+"\r\n");
            }
            
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
          
      }
      
      void favouriteslist(DataInputStream dis,DataOutputStream dos)
      {
        
          try 
        {
            String name = dis.readLine();
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from favorites where username='" + name +"'");
            
            
            while(rs.next())
            {
                dos.writeBytes("sending\r\n");
                dos.writeBytes(rs.getString("videoid")+"\r\n");
                dos.writeBytes(rs.getString("title")+"\r\n");
            }  
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
      }
      
      void delfavourites(DataInputStream dis,DataOutputStream dos)
      {
          
            
                
            try 
            {
                String videoid = dis.readLine();
                String name = dis.readLine();
                System.out.println("chll pda");
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
                Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs1 = stmt1.executeQuery("select * from favorites where username='" + name +"' and videoid='"+videoid+"'");
                if(rs1.next())
                {
                    rs1.deleteRow();
                }
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            
            
      }
      void delwatchlist(DataInputStream dis,DataOutputStream dos)
      {
            try 
            {
                String videoid = dis.readLine();
                String name = dis.readLine();
                System.out.println("chll pda");
                
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube", "root", "system");
                Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs1 = stmt1.executeQuery("select * from watchlater where username='" + name +"' and videoid='"+videoid+"'");
                if(rs1.next())
                {
                    rs1.deleteRow();
                }
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
          
          
      }

    public static void main(String[] args) {

        server obj = new server();

    }

}
