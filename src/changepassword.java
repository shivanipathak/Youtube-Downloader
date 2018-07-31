
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class changepassword extends javax.swing.JFrame {

    String name;
    DataInputStream dis;
    DataOutputStream dos;
    
    
   
    public changepassword(String name) {
        initComponents();
        setVisible(true);
        setSize(800, 500);
        this.name= name;
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        change = new javax.swing.JButton();
        newtf = new javax.swing.JPasswordField();
        confirmtf = new javax.swing.JPasswordField();
        oldtf = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("OLD PASSWORD");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 40, 110, 30);

        jLabel2.setText("NEW PASSWORD");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 100, 100, 40);

        jLabel3.setText("CONFIRM PASSWORD");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 170, 130, 40);

        change.setText("CHANGE");
        change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeActionPerformed(evt);
            }
        });
        getContentPane().add(change);
        change.setBounds(80, 240, 170, 40);
        getContentPane().add(newtf);
        newtf.setBounds(190, 100, 180, 40);
        getContentPane().add(confirmtf);
        confirmtf.setBounds(190, 170, 180, 40);

        oldtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oldtfActionPerformed(evt);
            }
        });
        getContentPane().add(oldtf);
        oldtf.setBounds(190, 40, 180, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeActionPerformed

        
         
        try 
        {
            
            Socket sock = new Socket("localhost", 8000);
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
            
            
        String oldpassword = oldtf.getText();
        String newpassword = newtf.getText();
        String confirmpassword = confirmtf.getText();
        
        
        
        if(oldpassword.equals("")||newpassword.equals("")||confirmpassword.equals(""))
        {
            JOptionPane.showMessageDialog(this, "all fields are mandatory");
        }
        
        else
        {
            if(confirmpassword.equals(newpassword))
            {
                dos.writeBytes("changepassword\r\n");
                dos.writeBytes(name+"\r\n");
                dos.writeBytes(oldpassword+"\r\n");
                dos.writeBytes(newpassword+"\r\n");
                
                
                String response = dis.readLine();
                if(response.equals("success"))
                {
                   
                      JOptionPane.showMessageDialog(this, "successfully updated");
                }
                else
                {
                      JOptionPane.showMessageDialog(this, "old password is wrong");

                }
                
            }
            
            else
            {
                JOptionPane.showMessageDialog(this, "new password and confirm password does not match");
                
            }
        }
        
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        
        
        
     
    }//GEN-LAST:event_changeActionPerformed

    private void oldtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oldtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oldtfActionPerformed

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
            java.util.logging.Logger.getLogger(changepassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(changepassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(changepassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(changepassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton change;
    private javax.swing.JPasswordField confirmtf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField newtf;
    private javax.swing.JPasswordField oldtf;
    // End of variables declaration//GEN-END:variables
}
