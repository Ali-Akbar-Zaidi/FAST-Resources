/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helloswing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author amyaw
 */
public class MyWindow extends JFrame {
    
    JLabel label;
    JButton button;
    public MyWindow(){
        label = new JLabel("Hello Swing");
        button = new JButton("Close");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); 
                dispose();
            }
        });

        LayoutManager layout = new FlowLayout();
        this.setLayout(layout);
        
        add(label);
        add(button);
        setSize(500, 500);
        

        
    }
    
}
