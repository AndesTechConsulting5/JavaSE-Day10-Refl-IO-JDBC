package org.andestech.learning.rfb19.g4.files;

import org.andestech.learning.rfb19.g4.utils.Book;

import java.io.*;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


        //-------------------

        File file5 = new File("e:\\datas4\\datas.bin");

        try( DataOutputStream dos = new DataOutputStream(new FileOutputStream(file5)))
        {
           int data1 = 1231231333;
           long data2 = 9847981479479847L;
           byte data3 = 116;

           dos.writeLong(data2);
           dos.writeByte(data3);
           dos.writeInt(data1);
        }
        catch (IOException ex){ex.printStackTrace();}


        try( DataInputStream dis = new DataInputStream(new FileInputStream(file5)))
        {
            int data1;
            long data2;
            byte data3;

            data2 = dis.readLong();
            data3 = dis.readByte();
            data1 = dis.readInt();

            System.out.println("Number: " + data1 + ", " +data2 +", " + data3);
        }
        catch (IOException ex){ex.printStackTrace();}


      // random

       File file6 = new File("e:\\datas4\\raf.txt");
echo('~');
       try( RandomAccessFile raf = new RandomAccessFile(file6,"rw"))
       {

         // raf.write("1234567\nABCDEF\nАБВГДЕЁ".getBytes());
          byte[] barr = new byte[(int)raf.length()];
          raf.read(barr);
          System.out.println(new String(barr));
          //-----------
          echo('>');
          raf.seek(0);
          byte[] barr2 = new byte[10];
          raf.read(barr2,0,10);
          System.out.println(new String(barr2));

          raf.write("CCCCCCD".getBytes());

       }
       catch (IOException ex){ex.printStackTrace();}

// Object IO

        List<Book> library = new ArrayList<>(10);
        library.add(new Book("Белое безмолвие","Джек Лондон",1899));
        library.add(new Book("Крейцерова соната","Лев Толстой",1904));
        library.add(new Book("Аэлита","Алексей Толстой",1932));

        File file7 = new File("e:\\datas4\\library.bin");

        try( ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(file7)))
        {
            dos.writeObject(library);
        }
        catch (IOException ex){ex.printStackTrace();}


       // List<Book> library2 = new ArrayList<>();
        List<Book> library2 = null;

        try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file7)))
        {
          library2 = (List<Book>)ois.readObject();
        }
        catch (IOException | ClassNotFoundException ex){ex.printStackTrace();}

        System.out.println(library2);




    }

}
