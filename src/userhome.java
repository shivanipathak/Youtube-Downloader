
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class userhome extends javax.swing.JFrame {
    
    String name;
   

   
    
    public userhome(String name) {
        initComponents();
        setSize(1000,1000);
        setVisible(true);
        this.name=name;
        searchbt.setVisible(false);
        searchtf.setVisible(false);
         imagelb.setIcon(new ImageIcon("C:\\downloads\\" + name+".jpg"));
         jPanel5.setPreferredSize(new Dimension(jScrollPane5.getWidth(),800));
         photolb.setBounds(0, 0, 1000, 1000);
         photolb.setIcon(new ImageIcon("C:\\Users\\Shivani Pathak\\Desktop\\ytd.jpg"));

        
    }
     class search1 implements Runnable{

    public void run() {

        {
            try {
                jPanel5.removeAll();

                String keyword = searchtf.getText();

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
                int x = 10;
                JPanel[] jp = new JPanel[object_array.size()];
                JLabel[] lbp = new JLabel[object_array.size()];
                JLabel lbt[] = new JLabel[object_array.size()];
    
                for (int i = 0; i < object_array.size(); i++) // eh video list de panel di working aa
                {

                    JSONObject item = (JSONObject) object_array.get(i);

                    //extract title and description from objects
                    JSONObject snippet = (JSONObject) item.get("snippet");

                     String title = (String) snippet.get("title");
                    String description = (String) snippet.get("description");
                    JSONObject id = (JSONObject) item.get("id");
                     String videoid = (String) id.get("videoId");
                    if (videoid != null) // j video id null aa
                    {                    
                    jp[i] = new JPanel();
                    lbp[i] = new JLabel();
                    lbt[i] = new JLabel();
                            

            
            jp[i].setBackground(Color.WHITE);
            jp[i].setLayout(null);
            
            jp[i].setVisible(true);
            jp[i].setBounds(50, x, 600, 50);

            try
            {
                URL url2 = new URL("https://i.ytimg.com/vi/"+videoid+"/default.jpg");
                Image img1 = ImageIO.read(url2);
                lbp[i].setIcon(new ImageIcon(img1));
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            lbp[i].setBounds(5,5,50,30);
            lbt[i].setBounds(80, 5, 300, 20);
            
            lbt[i].setText(title);
            
            lbt[i].setVisible(true);
            lbp[i].setVisible(true);
            jp[i].add(lbp[i]);
            jp[i].add(lbt[i]);
            jPanel5.add(jp[i]);
            jp[i].addMouseListener(new MouseAdapter(){

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println(videoid);
                        new video_detail(videoid,name,title).setVisible(true);
                        
                        }
                
            });
            x = x + 60;
                    
                    
                    System.out.println(title);
                    
   
                    }
                    
                                repaint();

                }
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
    }



}

    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        changebt = new javax.swing.JButton();
        searchbt = new javax.swing.JButton();
        searchtf = new javax.swing.JTextField();
        imagelb = new javax.swing.JLabel();
        searchbutton = new javax.swing.JButton();
        watchlistbutton = new javax.swing.JButton();
        addtofavbutton = new javax.swing.JButton();
        photolb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 958, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(jPanel5);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(50, 260, 960, 480);

        changebt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        changebt.setText("CHANGE PASSWORD");
        changebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changebtActionPerformed(evt);
            }
        });
        getContentPane().add(changebt);
        changebt.setBounds(790, 20, 180, 60);

        searchbt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        searchbt.setText("SEARCH");
        searchbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtActionPerformed(evt);
            }
        });
        getContentPane().add(searchbt);
        searchbt.setBounds(350, 180, 90, 30);
        getContentPane().add(searchtf);
        searchtf.setBounds(70, 180, 250, 30);
        getContentPane().add(imagelb);
        imagelb.setBounds(20, 30, 130, 110);

        searchbutton.setText("SEARCH");
        searchbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(searchbutton);
        searchbutton.setBounds(180, 20, 180, 60);

        watchlistbutton.setText("WATCH LIST");
        watchlistbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                watchlistbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(watchlistbutton);
        watchlistbutton.setBounds(390, 20, 160, 60);

        addtofavbutton.setText(" FAVOURITES");
        addtofavbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtofavbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(addtofavbutton);
        addtofavbutton.setBounds(580, 20, 180, 60);

        photolb.setText("jLabel1");
        getContentPane().add(photolb);
        photolb.setBounds(0, 0, 1090, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtActionPerformed
       
         changepassword obj = new changepassword(name);
        obj.setSize(500, 500);
        obj.setVisible(true);
    }//GEN-LAST:event_changebtActionPerformed

    private void searchbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtActionPerformed
        
        new Thread(new search1()).start();
        
    }//GEN-LAST:event_searchbtActionPerformed

    private void watchlistbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_watchlistbuttonActionPerformed
        
        new Thread(new Runnable() {

            @Override
            public void run()
            {
                try
            {
                jPanel5.removeAll();
                Socket sock = new Socket("localhost",8000);
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                DataInputStream dis = new DataInputStream(sock.getInputStream());
                
                dos.writeBytes("watchlist\r\n");
                dos.writeBytes(name+"\r\n");
                
                int x=10;
                while(dis.readLine().equals("sending"))
                {
                    String videoid = dis.readLine();
                    String title = dis.readLine();
                    System.out.println(videoid);
                    System.out.println(title);
                    
                    JPanel jp = new JPanel();
                    JLabel lbp = new JLabel();
                    JLabel lbt = new JLabel();
                    JLabel cancel = new JLabel();
                    
                    
                    
                    
                    jp.setBackground(Color.WHITE);
                    jp.setLayout(null);
                    jp.setVisible(true);
                    jp.setBounds(50, x, 600, 50);
                    
                    
                    
                    lbp.setBounds(5,5,50,30);
                    lbp.setVisible(true); 
                    lbt.setBounds(80, 5, 300, 20);
                    lbt.setText(title);
                    lbt.setVisible(true);
                    cancel.setVisible(true);
                    cancel.setBounds(550, 5, 40, 20);
                    cancel.setIcon(new ImageIcon("C:\\Users\\Shivani Pathak\\Desktop\\cross1.jpg"));
                    
                    try
                     {
                        URL url2 = new URL("https://i.ytimg.com/vi/"+videoid+"/default.jpg");
                         Image img2 = ImageIO.read(url2);
                        lbp.setIcon(new ImageIcon(img2));
                     }
                     catch(Exception ex)
                     {
                        ex.printStackTrace();
                     }
                    
                    
            
                     
                     
                     
                     
                     cancel.addMouseListener(new MouseAdapter()
                     {
                          @Override
                        public void mouseClicked(MouseEvent e) 
                        {

                              try 
                              {
                                  Socket sock1 = new Socket("localhost", 8000);
                                  DataOutputStream dos1= new DataOutputStream(sock.getOutputStream());
                                  jPanel5.remove(jp);
                                  dos1.writeBytes("delete\r\n");
                                  dos1.writeBytes(videoid+"\r\n");
                                  dos1.writeBytes(name+"\r\n");
                                  
                                  repaint();
                              } 
                              catch (Exception ex) 
                              {
                                  ex.printStackTrace();
                              }
                        
                        }
                         
                     });
                     
                     jp.add(lbp);
                     jp.add(lbt);
                     jp.add(cancel);
                     
                     jp.addMouseListener(new MouseAdapter(){

                        @Override
                        public void mouseClicked(MouseEvent e) {

                        new video_detail(videoid,name,title).setVisible(true);
                        
                        }
                        
                       
                
            });
                     
                     
            jPanel5.add(jp);         
            x = x + 60;
                    
           

                repaint();    
                    
    
                }
                
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
                
            }
        
               
            }
        }
        ).start();                      
        
    }//GEN-LAST:event_watchlistbuttonActionPerformed

    private void searchbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbuttonActionPerformed
        searchbt.setVisible(true);
        searchtf.setVisible(true);
    }//GEN-LAST:event_searchbuttonActionPerformed

    private void addtofavbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtofavbuttonActionPerformed
        
        
        new Thread(new Runnable() {

            @Override
            public void run()
            {
                try
            {
                jPanel5.removeAll();
                Socket sock = new Socket("localhost",8000);
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                DataInputStream dis = new DataInputStream(sock.getInputStream());
                
                dos.writeBytes("favouritelist\r\n");
                dos.writeBytes(name+"\r\n");
                
                int x=10;
                while(dis.readLine().equals("sending"))
                {
                    String videoid = dis.readLine();
                    String title = dis.readLine();
                    System.out.println(videoid);
                    System.out.println(title);
                    
                    JPanel jp = new JPanel();
                    JLabel lbp = new JLabel();
                    JLabel lbt = new JLabel();
                    JLabel cancel = new JLabel();
                    
                    
                    
                    jp.setBackground(Color.WHITE);
                    jp.setLayout(null);
                    jp.setVisible(true);
                    jp.setBounds(50, x, 600, 50);
                    
                    
                    
                    lbp.setBounds(5,5,50,30);
                    lbp.setVisible(true); 
                    lbt.setBounds(80, 5, 300, 20);
                    lbt.setText(title);
                    lbt.setVisible(true);
                    cancel.setVisible(true);
                    cancel.setBounds(550, 5, 40, 20);
                    cancel.setIcon(new ImageIcon("C:\\Users\\Shivani Pathak\\Desktop\\cross1.jpg"));
                    
                    try
                     {
                        URL url2 = new URL("https://i.ytimg.com/vi/"+videoid+"/default.jpg");
                         Image img2 = ImageIO.read(url2);
                        lbp.setIcon(new ImageIcon(img2));
                     }
                     catch(Exception ex)
                     {
                        ex.printStackTrace();
                     }
                    
                    
                    cancel.addMouseListener(new MouseAdapter()
                     {
                          @Override
                        public void mouseClicked(MouseEvent e) 
                        {

                              try 
                              {
                                  Socket sock1 = new Socket("localhost", 8000);
                                  DataOutputStream dos1 =  new DataOutputStream(sock1.getOutputStream());
                                  jPanel5.remove(jp);
                                  dos1.writeBytes("delete\r\n");
                                  System.out.println("chalne ki koshish");
                                  dos1.writeBytes(videoid+"\r\n");
                                  dos1.writeBytes(name+"\r\n");
                                  
                                  
                                  repaint();
                              } 
                              catch (Exception ex) 
                              {
                                  ex.printStackTrace();
                              }
                        
                        }
                         
                     });
                    
                    
            
                     
                     jp.add(lbp);
                     jp.add(lbt);
                     jp.add(cancel);
                     
                     jp.addMouseListener(new MouseAdapter(){

                        @Override
                        public void mouseClicked(MouseEvent e) {

                        new video_detail(videoid,name,title).setVisible(true);
                        
                        }
                
            });
                     
            jPanel5.add(jp);         
            x = x + 60;
                    
           

                repaint();    
                    
    
                }
                
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
                
            }
        
               
            }
        }
        ).start();                      
        
    }//GEN-LAST:event_addtofavbuttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(userhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addtofavbutton;
    private javax.swing.JButton changebt;
    private javax.swing.JLabel imagelb;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel photolb;
    private javax.swing.JButton searchbt;
    private javax.swing.JButton searchbutton;
    private javax.swing.JTextField searchtf;
    private javax.swing.JButton watchlistbutton;
    // End of variables declaration//GEN-END:variables
}
