/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaki;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jiaqi Wang(Kaki)
 */
public class GUI extends JPanel {

    private Image myImage;
    private String origin;
    private String[] data;
    private static int Width = 800;
    private static int Height = 800;
    private static JFrame frame = new JFrame();

// constructor, have deleted any unused parameters since it always fills the whole frame.

    public GUI(String path ) {
        
        this.myImage = readImage(path);
    }

    public static void ShowImage() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Height, Width);
        frame.setTitle(Data.getTitle(FileUtils.readFile("res/tigers.csv")));
        GUI content = new GUI("res/tiger.png");
        frame.add(content);
        frame.setVisible(true);
    }
// get the current size of frame
    public static void rate() {
        Height = frame.getHeight();
        Width = frame.getWidth();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        rate();
        origin = FileUtils.readFile("res/tigers.csv");
        // prepare data, which is an array.
        data = Data.toArray(origin);
        //set my Background color
        g.setColor(new Color(255, 238, 221));
        g.fillRect(0, 0, Width, Height);
        //prepare the title's font
        g.setFont(new Font("Tahoma", 1, Height / 30));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);

        String str = Data.getTitle(origin);
        int stringWidth = fm.stringWidth(str);
        // make my title center
        g.drawString(str, Width / 2 - g.getFontMetrics().stringWidth(str) / 2, Height / 18);
        g.setFont(new Font("Tahoma", 0, Height / 40));
        int stringHeight = fm.getHeight();
        //different sizes of icons
        int big = Height / 10;
        int mid = Height / 15;
        int small = Height / 25;
        // start to draw all the stuff
        for (int i = 0; i < data.length; i += 2) {
            str = data[i];
            // the frequently used constant
            int generalHeight = (i + 2) * Height / 18;

            g.drawString(str, Width / 14, generalHeight + fm.getHeight());
            // draw the lines spliting the elements
            g.drawLine(0, generalHeight, Width, generalHeight);
            // draw the last line
            g.drawLine(0, (2 + data.length) * Height / 18, Width, (2 + data.length) * Height / 18);
            // get numbers of the tigers
            int num = Integer.parseInt(data[i + 1]);
            // kilo for the number of biggest size of icon, hund for the mid , ten for the small
            int kilo = num / 1000;
            int hund = (num - kilo * 1000) / 100;
            int ten = (num - kilo * 1000 - hund * 100) / 10;

            // xborder : fixed space for images
            int xborder = Width / 7 * 2;
            // use if to print big, mid and small icons, 
            if (num >= 1000) {

                num -= kilo * 1000;

                for (int t = 0; t < kilo; t++) {
                    g.drawImage(myImage, xborder + t * big, generalHeight, big, big, this);
                }
            }
            if (num >= 100) {

                num -= hund * 100;
                for (int t = 0; t < hund; t++) {
                    g.drawImage(myImage, xborder + kilo * big + t * mid, generalHeight, mid, mid, this);
                }
            }
            if (num >= 10) {
                num -= ten * 10;
                System.out.println(num);
                for (int t = 0; t < ten; t++) {
                    g.drawImage(myImage, xborder + kilo * big + hund * mid + t * small, generalHeight, small, small, this);

                }
            }
            // for fracs 
            if (num == 2 || num == 3) {

                g.drawImage(readImage("res/tiger-25%.png"), xborder + kilo * big + hund * mid + ten * small, generalHeight, small / 2, small / 2, this);
            }
            if (num == 4 || num == 5 || num == 6) {

                g.drawImage(readImage("res/tiger-50%.png"), xborder + kilo * big + hund * mid + ten * small, generalHeight, small, small / 2, this);
            }
            if (num == 7 || num == 8) {

                g.drawImage(readImage("res/tiger-75%.png"), xborder + kilo * big + hund * mid + ten * small, generalHeight, small, small, this);
            }
            if (num == 9) {

                g.drawImage(myImage, xborder + kilo * big + hund * mid + ten * small, generalHeight, small, small, this);
            }

            // draw the legend description
            g.drawImage(myImage, Width / 16, (data.length + 2) * Height / 18, big, big, this);
            g.drawString("= 1000", Width / 16 + big + Width / 40, (data.length + 2) * Height / 18 + fm.getHeight());
            g.drawImage(myImage, Width / 3 + 20, (data.length + 2) * Height / 18, mid, mid, this);
            g.drawString("= 100", Width / 3 + mid + Width / 40, (data.length + 2) * Height / 18 + fm.getHeight());
            g.drawImage(myImage, Width / 14 * 9, (data.length + 2) * Height / 18, small, small, this);
            g.drawString("= 10", Width / 14 * 9 + small + Width / 60, (data.length + 2) * Height / 18 + fm.getHeight());

        }
    }
    // readImage part
    public static BufferedImage readImage(String filename) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
