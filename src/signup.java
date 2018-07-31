
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class signup extends javax.swing.JFrame {

    Socket sock;
    FileInputStream fis;
    File f;

    public signup(Socket sock) {
        initComponents();
        setVisible(true);
        setSize(1000, 800);
        this.sock = sock;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        signupbutton = new javax.swing.JButton();
        phone = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        path = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("SIGN UP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(370, 0, 220, 50);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("USERNAME");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(90, 120, 260, 50);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("PASSWORD");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(90, 190, 300, 50);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("E-MAIL");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 270, 310, 50);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("PHONE NUMBER");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(90, 350, 270, 50);

        signupbutton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        signupbutton.setText("SIGN UP");
        signupbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(signupbutton);
        signupbutton.setBounds(300, 530, 250, 50);
        getContentPane().add(phone);
        phone.setBounds(440, 350, 340, 50);
        getContentPane().add(email);
        email.setBounds(440, 270, 340, 50);
        getContentPane().add(username);
        username.setBounds(440, 120, 340, 50);
        getContentPane().add(password);
        password.setBounds(440, 200, 340, 50);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("BROWSE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(90, 430, 130, 40);
        getContentPane().add(path);
        path.setBounds(440, 430, 340, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        JFileChooser jfc = new JFileChooser();

        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            f = jfc.getSelectedFile();
            path.setText(f.getPath());

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void signupbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupbuttonActionPerformed

        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    Socket sock = new Socket("localhost", 8000);
                    DataInputStream dis = new DataInputStream(sock.getInputStream());
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                    System.out.println("signup");

                    int flag = 0;

                    if (phone.getText().length() != 10) {
                        JOptionPane.showMessageDialog(signup.this, "phone no. should contain 10 numbers");

                        for (int i = 0; i < phone.getText().length(); i++) {
                            
                            int c=phone.getText().charAt(i);
                            if (c > 48 && c < 57) {

                                JOptionPane.showMessageDialog(signup.this, "Phone no. should contains only numbers");
                                flag = 1;
                                break;
                            }
                        }

                    }

                    if (flag == 0) {
                        dos.writeBytes("signuprequest\r\n");

                        String s = dis.readLine();
                        System.out.println(s);

                        if (s.equals("Send data")) {
                            dos.writeBytes(username.getText() + "\r\n");
                            dos.writeBytes(password.getText() + "\r\n");
                            dos.writeBytes(email.getText() + "\r\n");
                            dos.writeBytes(phone.getText() + "\r\n");
                  // dos.writeBytes("path.getText()\r\n");

                            //uploading
                            fis = new FileInputStream(path.getText());
                            byte b[] = new byte[100000];

                            long size = f.length();
                            dos.writeBytes(size + "\r\n");
                            String filename = f.getName();
                            dos.writeBytes(filename + "\r\n");
                            while (true) {

                                int r = fis.read(b, 0, 100000);
                                if (r == -1) {
                                    break;
                                }
                                dos.write(b, 0, r);
                            }
                            fis.close();

                        }

                        String s1 = dis.readLine();
                        if (s1.equals("Failed")) {
                            JOptionPane.showMessageDialog(signup.this, "user already exists");
                        } else {
                            JOptionPane.showMessageDialog(signup.this, "Signup successful");
                        }
                    }
                    
                    
                    
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();

                }

            }

        }).start();


    }//GEN-LAST:event_signupbuttonActionPerformed

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
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField path;
    private javax.swing.JTextField phone;
    private javax.swing.JButton signupbutton;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
