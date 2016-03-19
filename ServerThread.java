/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intracom_03.pkg18;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Shadan_2
 */
public class ServerThread extends Thread {
    JPanel jp1,temp;
    JButton send;
    JTextArea display;
    JTextField NewMsg;
    
    ServerThread(JPanel jp)
    {
        jp1=new JPanel();
        display=new JTextArea("welcome");
        send=new JButton("Send");
        NewMsg=new  JTextField("Message");
        jp1.add(display);
        jp1.add(send);       
        temp=jp;
       
        
        
    }
   public void run()
   {
       try {
           ServerSocket ss=new ServerSocket(3333);
           Socket s=ss.accept();
           System.out.println(s+"\n"+ss);
         if(s.isConnected())
         {
             
               temp.add(jp1);
             send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ((e.getSource() == send) && (NewMsg.getText() != "")) {
                            
                            DataOutputStream dos = null;
                            try {
                                display.setText(display.getText() + '\n' + "Me:"+ NewMsg.getText());
                                dos = new DataOutputStream(s.getOutputStream());
                                dos.writeUTF(NewMsg.getText());
                            } catch (IOException ex) {
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                try {
                                    dos.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        
                    }
                });  
           while (true) {
   
    DataInputStream dis = new DataInputStream(s.getInputStream());
    String string = dis.readUTF();
    display.setText(display.getText() + '\n' + "Client:"
      + string);
   
           }
         }
       } catch (IOException ex) {
           Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
}
