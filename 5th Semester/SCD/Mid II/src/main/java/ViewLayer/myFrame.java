/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewLayer;

import javax.swing.*;
import java.awt.*;
import dataLayer.imageData;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 * @author ALI
 */
public class myFrame extends JFrame{
    private java.util.List<imageData> myImages;
    private int currIndex=0;
    public myFrame(java.util.List<imageData> myImages){
        this.myImages=myImages;
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        customImage IMG=new customImage();
        IMG.setPreferredSize(new Dimension(getWidth(),500));
        add(IMG);
        JTextArea textField=new JTextArea(myImages.get(currIndex).getDesc());
        JButton btn1=new JButton("PREV");
        JButton btn2=new JButton("NEXT");
        add(textField);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btn1);
        panel.add(btn2);
        add(panel);
        btn1.addActionListener(e->{
            if(currIndex!=0){
                currIndex--;
                IMG.revalidate();
                IMG.repaint();
                textField.setText(myImages.get(currIndex).getDesc());
            }
        });
        btn2.addActionListener(e->{
             if(currIndex<myImages.size()){
                currIndex++;
                IMG.revalidate();
                IMG.repaint();
                textField.setText(myImages.get(currIndex).getDesc());
            }
        });
        
        setVisible(true);
        pack();
    }
    private class customImage extends JComponent{
        BufferedImage img;
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            try{
                img=ImageIO.read(new File(myImages.get(currIndex).getPath()));
                g.drawImage(img, 0, 0, null);
            }
            catch(Exception ex){
            }
        }
        
        
        
    }
    
}
