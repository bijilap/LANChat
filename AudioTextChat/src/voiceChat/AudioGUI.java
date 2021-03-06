package voiceChat;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;

/*
 * AudioGUI.java
 *
 * Created on November 13, 2012, 7:11 AM
 */



/**
 *
 * @author  Bijil Abraham Philip
 * @regno 09GAEC3011
 * 
 */

class TxThread extends Thread
{
  String ipaddr;
  
  TxThread(String ip)
  {
	ipaddr=ip;
    start();
  }
  public void run()
  {
	 Transmitter t=new Transmitter();
	 try {
		t.captureAudio(ipaddr);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}

class RxThread extends Thread
{
  RxThread()
  {
    start();
  }
  public void run()
  {
	  try 
	  {
		try {
			Receiver r=new Receiver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  } 
	  catch (LineUnavailableException e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  } 
  }
}

public class AudioGUI extends javax.swing.JFrame {
    
    /** Creates new form AudioGUI */
	 TxThread t1;
	 RxThread t2;
	 
    public AudioGUI() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        IPAddress = new javax.swing.JTextField();
        LoginButton = new javax.swing.JButton();
        LogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel1.setText("Audio Chat Client");

        jLabel2.setText("IP Address");

        LoginButton.setText("Log In");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        LogOut.setText("Log Out");
        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LoginButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LogOut))
                            .addComponent(IPAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(IPAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginButton)
                    .addComponent(LogOut))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) 
    {
        // TODO add your handling code here:
    	t1.stop();
    	t2.stop();
    	IPAddress.setEditable(true);
    }

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        // TODO add your handling code here:
    	String ipaddress=IPAddress.getText();
    	System.out.println(ipaddress);
    	IPAddress.setEditable(false);
        t1=new TxThread(ipaddress);
        t2=new RxThread();
        t1.run();
        t2.run();
    }
    
    /**
     * @param args the command line arguments
     */
    public void audioChat() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AudioGUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify
    private javax.swing.JTextField IPAddress;
    private javax.swing.JButton LogOut;
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration
    
}