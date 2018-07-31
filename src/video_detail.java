
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class video_detail extends javax.swing.JFrame {

    static String videoid;
    String name, title;
    ArrayList<String> url,info;
    CheckboxGroup group;
    String downloadurl;
    //JProgressBar jpb ;

    public video_detail(String videoid, String name, String title) {
        initComponents();
        this.videoid = videoid;
        this.name = name;
        this.title = title;
        setSize(1000, 1000);
        setVisible(true);
        lb1.setText(title);
//        this.add(jpb);
  //      jpb.setBounds(280, 760, 20, 30);
        
        System.out.println(title);
        System.out.println("videoid"+videoid);
    }
    
    
    public class Download implements Runnable
    {

        @Override
        public void run() 
        {
            
        try
        {
            URL url = new URL(downloadurl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            System.out.println("reached");
            DataInputStream dis = new DataInputStream(connection.getInputStream());
            long l = connection.getContentLength();
            byte b[] = new byte[10000];
            int percent;
      //      jpb= new JProgressBar(0, 100);
            String extension = connection.getContentType();
            System.out.println(extension);

            extension = extension.substring(extension.indexOf("/") + 1);
            System.out.println(extension);

            FileOutputStream fos = new FileOutputStream("C:\\Users\\Shivani Pathak\\Downloads\\abc1"+"." + extension);
            int count = 0;
            while (true)
            {
                int r = dis.read(b, 0, b.length);
                fos.write(b, 0, r);
                count = count + r;
                percent=(int)(((count*1.0)/l)*100);
                jProgressBar1.setValue(percent);
                
                System.out.println(count);
                if (count == l)
                {
                    break;
                }
            }
            JOptionPane.showMessageDialog(video_detail.this, "Download Complete");
            fos.close();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
           
        
    }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        playbt = new javax.swing.JButton();
        watchlaterbt = new javax.swing.JButton();
        downloadsbt = new javax.swing.JButton();
        favouritebt = new javax.swing.JButton();
        lb1 = new javax.swing.JLabel();
        checkboxpanel = new javax.swing.JPanel();
        downloadbt = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1);
        jPanel1.setBounds(30, 90, 640, 280);

        playbt.setText("PLAY");
        playbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playbtActionPerformed(evt);
            }
        });
        getContentPane().add(playbt);
        playbt.setBounds(530, 400, 160, 40);

        watchlaterbt.setText("WATCH LATER");
        watchlaterbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                watchlaterbtActionPerformed(evt);
            }
        });
        getContentPane().add(watchlaterbt);
        watchlaterbt.setBounds(350, 400, 150, 40);

        downloadsbt.setText("DOWNLOADS");
        downloadsbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadsbtActionPerformed(evt);
            }
        });
        getContentPane().add(downloadsbt);
        downloadsbt.setBounds(190, 400, 150, 40);

        favouritebt.setText("ADD TO FAVOURITES");
        favouritebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favouritebtActionPerformed(evt);
            }
        });
        getContentPane().add(favouritebt);
        favouritebt.setBounds(10, 400, 160, 40);

        lb1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(lb1);
        lb1.setBounds(280, 0, 330, 60);

        javax.swing.GroupLayout checkboxpanelLayout = new javax.swing.GroupLayout(checkboxpanel);
        checkboxpanel.setLayout(checkboxpanelLayout);
        checkboxpanelLayout.setHorizontalGroup(
            checkboxpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        checkboxpanelLayout.setVerticalGroup(
            checkboxpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        getContentPane().add(checkboxpanel);
        checkboxpanel.setBounds(10, 510, 700, 120);

        downloadbt.setText("DOWNLOAD");
        downloadbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadbtActionPerformed(evt);
            }
        });
        getContentPane().add(downloadbt);
        downloadbt.setBounds(240, 660, 230, 50);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(30, 640, 650, 14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void favouritebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favouritebtActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Socket sock = new Socket("localhost", 8000);
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                    DataInputStream dis = new DataInputStream(sock.getInputStream());

                    dos.writeBytes("addtofavourites\r\n");

                    dos.writeBytes(name + "\r\n");
                    dos.writeBytes(videoid + "\r\n");
                    dos.writeBytes(title+"\r\n");

                    String response = dis.readLine();
                    if (response.equals("alreadyadded")) {
                        JOptionPane.showMessageDialog(video_detail.this, "Already Added");
                    } else {
                        JOptionPane.showMessageDialog(video_detail.this, "Successfully Added");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }).start();
    }//GEN-LAST:event_favouritebtActionPerformed

    private void watchlaterbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_watchlaterbtActionPerformed

        new Thread(new Runnable() {

            @Override
            public void run() {
                Socket sock;
                try {
                    sock = new Socket("localhost", 8000);
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                    DataInputStream dis = new DataInputStream(sock.getInputStream());

                    dos.writeBytes("watchlater\r\n");
                    System.out.println(videoid);
                    dos.writeBytes(name + "\r\n");
                    dos.writeBytes(videoid + "\r\n");
                    dos.writeBytes(title+"\r\n");

                    String response = dis.readLine();
                    if (response.equals("alreadyadded")) {
                        JOptionPane.showMessageDialog(video_detail.this, "Already added");
                    } else {
                        JOptionPane.showMessageDialog(video_detail.this, "Added successfully");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
        ).start();


    }//GEN-LAST:event_watchlaterbtActionPerformed

    private void downloadsbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadsbtActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URLConnection conn = new URL("https://www.youtube.com/watch?v=" + videoid).openConnection();
                    DataInputStream dis = new DataInputStream(conn.getInputStream());
                    StringBuffer sb = new StringBuffer("");
                    while (true) {
                        String line = dis.readLine();
                        if (line == null) {
                            break;
                        } else {
                            sb.append(line);
                        }

                    }
                    //System.out.println(sb);
                    String mainscript;
                    int start = sb.indexOf("<script>var ytplayer");
                    int end = sb.indexOf("</script>", start) + 9;
                    mainscript = sb.substring(start, end);
                    //System.out.println(mainscript);
                     url = new ArrayList<>();
                     info = new ArrayList<>();
                    String s = "";
                    int startnew = mainscript.indexOf("url=");
                
                    String encodearr[]= new String[]{"%3A","%2F","%3F","%3D","%26","%25","%2C","%3B","%22"};
                    String decodearr[] = new String[]{":","/","?","=","&","%",",",";","\""};
                    while (true)
                    {
                        int endnew = mainscript.indexOf("url=", startnew + 1);
                        if (endnew == -1) 
                        {
                            break;
                        }
                        s = mainscript.substring(startnew, endnew);
                        startnew = endnew;
                        for(int i=0;i<encodearr.length;i++)
                        {
                            s = s.replaceAll(encodearr[i], decodearr[i]);
                        }
                        if(s.contains("signature") && s.contains("type=video") && s.contains("quality_label") || s.contains("ratebypass")  )
                        {
                            System.out.println("in it");
                            int last = s.lastIndexOf("\\u0026")+6;
                            int beg = s.indexOf("http");
                            int finish = s.indexOf("\\u0026",beg);
                            String s1 = s.substring(beg, finish);
                            String infos = s.substring(finish+1,last);
                            System.out.println(infos);
                            int qualitybeg = infos.indexOf("quality_label=")+14;
                            String quality = infos.substring(qualitybeg,infos.indexOf("p",qualitybeg)+1);
                            String  type =infos.substring(infos.indexOf("type=video")+11,infos.indexOf(";",infos.indexOf("type=video")+11));
                            
                        
                            url.add(s1);
                           info.add(quality+","+type);
                        }
                    }
                    group = new CheckboxGroup();
                    Checkbox jcb[]= new Checkbox[url.size()];
                    int y=10;
//                     if(url.size()==0||info.size()==0)
//                        {
//                            JOptionPane.showMessageDialog(video_detail.this, "video cannot be downloaded");
//                        }

                    for (int i = 0; i < url.size(); i++) 
                    {
                        System.out.println(url.get(i));
                        System.out.println("*******************************\n\n");
                        
                        System.out.println(info.get(i));
                        System.out.println("###################################\n\n");
                        
                       
                        jcb[i] =   new Checkbox(info.get(i), false, group);
                        jcb[i].setBounds(y, 10, 45, 30);
                        checkboxpanel.add(jcb[i]);
                        final int a=i;
                        jcb[i].addMouseListener(new MouseAdapter()
                     {
                          @Override
                        public void mouseClicked(MouseEvent e) 
                        {

                              try 
                              {
                                 downloadurl = url.get(a);
                                  System.out.println(downloadurl);
                                  
                                  
                              } 
                              catch (Exception ex) 
                              {
                                  ex.printStackTrace();
                                  
                              }
                        
                        }
                        
                        
                        
                    });
                                                y+=70;

                   
                }
                }catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(video_detail.this, "Restricted video");
                }

            }
        }).start();

    }//GEN-LAST:event_downloadsbtActionPerformed

    private void downloadbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadbtActionPerformed
        Download obj = new Download();
        
        Thread t = new Thread(obj);
        t.start();
        
        
    }//GEN-LAST:event_downloadbtActionPerformed

    private void playbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playbtActionPerformed

new Thread(new play1()).start();        
// TODO add your handling code here:
    }//GEN-LAST:event_playbtActionPerformed

     
    class play1 implements Runnable
    {

        public void run()
        {
            NativeInterface.open();
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    //  JFrame frame = new JFrame("YouTube Viewer");

                    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    jPanel1.add(getBrowserPanel(), BorderLayout.CENTER);
                    setSize(800, 600);
                    //frame.setLocationByPlatform(true);
                    setVisible(true);

                }

            });
            NativeInterface.runEventPump();
            // don't forget to properly close native components
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    NativeInterface.close();
                }
            }));
        }
    }
     public static JPanel getBrowserPanel()
    {
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowser.setBarsVisible(false);

        webBrowser.navigate("https://www.youtube.com/v/"+videoid+"?autoplay=1");
        return webBrowserPanel;
    }
    
    
    
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
            java.util.logging.Logger.getLogger(video_detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(video_detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(video_detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(video_detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel checkboxpanel;
    private javax.swing.JButton downloadbt;
    private javax.swing.JButton downloadsbt;
    private javax.swing.JButton favouritebt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lb1;
    private javax.swing.JButton playbt;
    private javax.swing.JButton watchlaterbt;
    // End of variables declaration//GEN-END:variables
}
