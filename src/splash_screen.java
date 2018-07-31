
import java.awt.Image;
import java.io.File;
import static java.lang.Thread.sleep;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class splash_screen extends javax.swing.JFrame {

    
    public splash_screen() {
        setUndecorated(true);
        setLocation(100, 100);
        initComponents();
        setLayout(null);
        setVisible(true);
        setSize(800, 800);
        setResizable(false);
        fullscreen.setBounds(0, 0,800, 800);
        try
        {
        Image img=ImageIO.read(new File("C:\\Users\\Shivani Pathak\\Desktop\\ytd1.jpg"));
        Image img1=img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        fullscreen.setIcon(new ImageIcon(img1));
        }
        catch(Exception ae)
        {
            ae.printStackTrace();
        }
        new Thread(new screen()).start();
    }
    
    
    class screen implements Runnable
    {

        @Override
        public void run()
        {
            try
            {
                for(int i =1 ; i<100;i++)
                {
                    jProgressBar1.setValue(i);
                    sleep(100);
                }
                
                clientf obj = new clientf();
                obj.setVisible(true);
                splash_screen.this.dispose();
                
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
         
        }
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        fullscreen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(100, 170, 270, 20);
        getContentPane().add(fullscreen);
        fullscreen.setBounds(0, 0, 490, 420);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(splash_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(splash_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(splash_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(splash_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new splash_screen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fullscreen;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
