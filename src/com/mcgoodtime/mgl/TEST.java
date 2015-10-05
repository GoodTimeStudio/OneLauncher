package com.mcgoodtime.mgl;

import java.io.File;
import java.io.IOException;

/**
 * Created by suhao on 2015.9.23.0023.
 */
public class TEST {

    public static void main(String args[]) {
        File file = new File("test.txt");
        try {
            file.createNewFile();
            file.deleteOnExit();
            /*
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();*/
            Thread.sleep(10000);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
