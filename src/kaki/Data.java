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
public class Data {
    // read from csv and transform the data into Array.
    public static String[] toArray(String originalData) {
        String[] cacheTable = originalData.split(",");
        // cacheTable contains title, so we need to pass the country and the coreponding number into the pureDataTable
        String[] pureDataTable = new String[cacheTable.length - 2];
        // use loop to pass in all the values
        for (int i = 0; i < pureDataTable.length; i++) {
            pureDataTable[i] = cacheTable[i + 2];
        }
        return pureDataTable;
    }
    // get title from the file, which is the first line before the comma.
    public static String getTitle(String originalData) {
        //initialiaze the title
        String title = "";
        // extract title from the file by examining the first time when the comma appears.
        title = originalData.substring(0,originalData.indexOf(',')).trim();
        return title;
    }
}
