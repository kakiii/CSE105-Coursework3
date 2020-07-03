/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaki;

/**
 *
 * @author Kaki
 */
public class Coursework3 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args)  {
               GUI.ShowImage();
               System.out.println(Data.getTitle(FileUtils.readFile("res/tigers.csv")));
        

    }

}
