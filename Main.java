/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intracom_03.pkg18;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Shadan_2
 */
public class Main extends JFrame {
    JTextField ip;
    JButton conn;
    JPanel jp1;
    
    public Main() {
        ip=new JTextField(15);
        conn=new JButton("Connect");
        jp1=new JPanel();
        add(ip);
        add(conn);
        add(jp1);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
                ServerThread s=new ServerThread(jp1);
        s.start();
        conn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientThread c=new ClientThread(jp1,ip.getText());
                c.start();
            }
        });
    }
    
    
    public static void main(String args[]) {
    
        new Main();
    }
}
