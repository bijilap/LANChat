package Main;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import voiceChat.*;

/*
 * ClientGUI.java
 *
 * Created on October 26, 2012, 7:05 PM
 */

/**
 *
 * @author  Bijil Abraham Philip
 */
public class ClientGUI extends javax.swing.JFrame {
    String userid;
    Socket socket;
    BufferedReader stdin;
    PrintWriter stdout;
    ArrayList<String> userList = new ArrayList<String>();
    Boolean isConnected=false;
    /** Creates new form NewJFrame */
    public ClientGUI() {
        initComponents();
    }
    public class DataAnalyser implements Runnable{

        public void run() {
            String stream;
            String[] data;
            String done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = stdin.readLine()) != null) {

                    data = stream.split("\21");

                     if (data[2].equals(chat)) {

                        outputText.append(data[0] + ": " + data[1] + "\n");

                    } else if (data[2].equals(connect)){

                        outputText.removeAll();
                        userList.add(data[0]);

                    } else if (data[2].equals(disconnect)) {


                    	outputText.append(data[0] + " has logged out.\n");

                    } else if (data[2].equals(done)) {


                        chatRoomUsers.setText("");
                        //writeUsers();
                        for(int i=0;i<userList.size();i++)
                        {
                        	chatRoomUsers.append(userList.get(i)+"\n");
                        }
                        userList.clear();

                    }
                 
                }
           }catch(Exception ex) {
           }
        }
    }
    
    public void ListenThread() 
    {
        Thread DataAnalyser = new Thread(new DataAnalyser());
        DataAnalyser.start();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    public void Disconnect() 
    {

        try {
               outputText.append("Disconnected....You have been successfully Logged out!\n");
               socket.close();
        } catch(Exception ex) {
               outputText.append("Oops! Log out failed \n");
        }
        isConnected = false;
        usernameField.setEditable(true);
        ipAddress.setEditable(true);
        chatRoomUsers.setText("");

      }
    
    public void performLogOut() {

        String bye = (userid + "\21 \21Disconnect");
         try{
             stdout.println(bye); // Sends server the signal to log out.
             stdout.flush(); // flushes the buffer
         } catch (Exception e) {
             outputText.append("Error occured while sending log out message\n");
         }

       }
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        inputText = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatRoomUsers = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        usernameField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        audioChatButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ipAddress = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inputText.setColumns(20);
        inputText.setRows(5);
        jScrollPane1.setViewportView(inputText);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        chatRoomUsers.setEditable(false);
        jScrollPane2.setViewportView(chatRoomUsers);

        outputText.setColumns(20);
        outputText.setEditable(false);
        outputText.setRows(5);
        jScrollPane3.setViewportView(outputText);

        usernameField.setText("username");

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        
        audioChatButton.setText("Voice");
        audioChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	audioChatButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Chat Room");

        ipAddress.setText("IP Address");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ipAddress, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(audioChatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(sendButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginButton)
                            .addComponent(logoutButton)
                            .addComponent(audioChatButton)
                            .addComponent(ipAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                             

    	performLogOut();
    	Disconnect();
    }                                            
    private void audioChatButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {                                             

    	AudioGUI aud=new AudioGUI();
    	aud.audioChat();
    }      
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        // TODO add your handling code here:
            if (isConnected == false) {
            userid = usernameField.getText();
            usernameField.setEditable(false);
            ipAddress.setEditable(false);

            try {
                socket = new Socket(ipAddress.getText(), 7000);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                stdin = new BufferedReader(streamreader);
                stdout = new PrintWriter(socket.getOutputStream());
                stdout.println(userid + "\21has logged in.\21Connect"); // Displays to everyone that user connected.
                stdout.flush(); // flushes the buffer
                isConnected = true; // Used to see if the client is connected.
            } catch (Exception ex) {
                outputText.append("Failed to connect to Server! Try Again. \n");
                usernameField.setEditable(true);
                ipAddress.setEditable(true);
            }
            ListenThread();
        } else if (isConnected == true) {
            outputText.append("You are already part of the chat. \n");
        }
    }
    
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
    	if((inputText.getText()).equals(""))
    	{
    		inputText.setText("");
    		inputText.requestFocus();
    	}
    	else {
            try {
               stdout.println(userid + "\21" + inputText.getText() + "\21" + "Chat");
               stdout.flush(); // flushes the buffer
            } catch (Exception ex) {
                chatRoomUsers.append("Message was not sent. \n");
            }
            inputText.setText("");
            inputText.requestFocus();
        }

        inputText.setText("");
        inputText.requestFocus();
    }
   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new ClientGUI().setVisible(true);
            }
        });
    }
    
                      
    private javax.swing.JTextArea chatRoomUsers;
    private javax.swing.JTextArea inputText;
    private javax.swing.JTextField ipAddress;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton audioChatButton;
    private javax.swing.JTextArea outputText;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField usernameField;                 
    
}