package org.andestech.learning.rfb19.g4.files;

import java.io.*;
import java.util.Date;

import static org.andestech.learning.rfb19.g4.utils.Utils.echo;

public class AppFiles {

    public static void main(String[] args)
    {
     // File file = new File("e:\\datas4\\inner\\22\\33");
     // file.mkdirs();

        File file = new File("e:\\datas4\\hello.txt");

        try {
            System.out.println(file.createNewFile());
        }catch (IOException ex){ex.printStackTrace();}


        System.out.println(file.exists());


        File file2 = new File("e:\\datas4\\hello3.txt");

        try(FileWriter fw = new FileWriter(file2))
        {
            fw.write("Hi!!\r\nПривет!\r\n"+ new Date() + "\r\n");
        }
        catch (IOException ex){ex.printStackTrace();}


        try(FileReader fr = new FileReader(file2)) {
            int c;
            while ((c = fr.read()) != -1)
            {
                System.out.print((char)c);
            }

        }
        catch (IOException ex){ex.printStackTrace();}


    //---------------------------- Streams ------------------------

        File file3 = new File("e:\\datas4\\fosdata.txt");

        try(FileOutputStream fos = new FileOutputStream(file3))
        {
            byte[] barr = ("Hi!!\r\nПривет!\r\n"+ new Date() + "\r\n").
                  //  getBytes("866");
                          getBytes("utf-8");
            fos.write(barr);
        }
        catch (IOException ex){ex.printStackTrace();}

        echo('*');

        try(FileInputStream fis = new FileInputStream(file3)) {
            //1
            // byte[] barr = new byte[(int)file3.length()];

            //2
            byte[] barr = new byte[fis.available()];

            fis.read(barr);

            System.out.println(new String(barr));

        }
        catch (IOException ex){ex.printStackTrace();}


    }

}
