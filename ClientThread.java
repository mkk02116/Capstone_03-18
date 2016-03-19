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
public class ClientThread extends Thread {
    JPanel jp1,temp;
    JButton send;
    JTextArea display;
    String ip;
    JTextField NewMsg;
    
    ClientThread(JPanel jp,String ip1)
    {
        ip=ip1;
        jp1=new JPanel();
        display=new JTextArea("welcome");
        send=new JButton("send");
        jp1.add(display);
        jp1.add(send);
        NewMsg=new  JTextField("Message");
      // jp.add(jp1);
       temp=jp;
       jp1.setBounds(30, 30, 30, 30);
        
        
    }
   public void run()
   {
      
        try {
            Socket s=new Socket(ip,3333);
            System.out.println(s);
            if(s.isConnected())
            {
                System.out.println("intracom_03.pkg18.ClientThread.run()");
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
            }
            
            while (true) {
                
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String string = dis.readUTF();
                display.setText(display.getText() + '\n' + "Client:"
                        + string);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
      
   }
}
