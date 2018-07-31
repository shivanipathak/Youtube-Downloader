
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class login extends javax.swing.JFrame {
    
    Socket sock;
    DataInputStream dis;
    DataOutputStream dos;
    FileInputStream fis;
    FileOutputStream fos;

    public login(Socket sock) {
        initComponents();
        setVisible(true);
        setSize(1000, 800);
        this.sock=sock;
        photolb.setBounds(0, 0, 1000, 800);
        photolb.setIcon(new ImageIcon("C:\\Users\\Shivani Pathak\\Desktop\\signup1.jpg"));
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernametf = new javax.swing.JTextField();
        passwordtf = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loginbutton = new javax.swing.JButton();
        photolb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        usernametf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernametfActionPerformed(evt);
            }
        });
        getContentPane().add(usernametf);
        usernametf.setBounds(300, 160, 410, 50);
        getContentPane().add(passwordtf);
        passwordtf.setBounds(300, 300, 410, 50);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("LOGIN PAGE");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(370, 0, 350, 70);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("USERNAME");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(300, 110, 140, 40);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("PASSWORD");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(300, 240, 150, 40);

        loginbutton.setBackground(new java.awt.Color(204, 204, 204));
        loginbutton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        loginbutton.setText("LOGIN");
        loginbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(loginbutton);
        loginbutton.setBounds(430, 410, 130, 50);
        getContentPane().add(photolb);
        photolb.setBounds(0, 0, 990, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernametfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernametfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernametfActionPerformed

    private void loginbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbuttonActionPerformed
        
         new Thread(new Runnable(){

            @Override
            public void run() {
        try
        {
            Socket sock = new Socket("localhost", 8000);
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
            
            String name = usernametf.getText();
            String password = passwordtf.getText();
            
            dos.writeBytes("loginrequest\r\n");
            
            dos.writeBytes(name+"\r\n");
            dos.writeBytes(password+"\r\n");
            
            String response= dis.readLine();
            if(response.equals("successful"))
            {
                JOptionPane.showMessageDialog(login.this, "login successful");
                userhome obj= new userhome(name);
                obj.setVisible(true);
                obj.setSize(500,500);
                
                
                
                 byte b[] = new byte[100000];
            long size = Long.parseLong(dis.readLine());
                System.out.println("the size"+size);
                System.out.println("the name"+name);
            int count = 0;
            fos = new FileOutputStream("C:\\downloads\\"+name+".jpg");
                
                
                 while (true) {
                        int r = dis.read(b, 0, 100000);
                        count += r;
                        fos.write(b, 0, r);
                        if (count == size) {
                            break;
                        }
                
                
            }
                 fos.close();
            }
        
            else
            {
                JOptionPane.showMessageDialog(login.this, "incorrect username/password");
            }
            
            
        
            }
            
         
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }
        
        }
         }).start();  
        
    }//GEN-LAST:event_loginbuttonActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginbutton;
    private javax.swing.JPasswordField passwordtf;
    private javax.swing.JLabel photolb;
    private javax.swing.JTextField usernametf;
    // End of variables declaration//GEN-END:variables
}
