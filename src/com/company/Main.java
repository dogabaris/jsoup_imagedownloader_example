package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
import java.net.URL;
import java.io.File;

public class Main {

    public static void main(String[] args) throws IOException {
        int iterator=1;
        Document doc = null;
        String s = "dini-dovme-modelleri";
        String src = null,imgSrc = null;

        File dir = new File("D:\\dovme\\"+s);
        dir.mkdirs();

        while (true) {
            try{
                if(iterator<10)
                    doc = Jsoup.connect("http://www.dovmemodelleri.com/"+s+"/"+ s +"-00" + iterator + ".jpg.php").get();
                else if(iterator<100)
                    doc = Jsoup.connect("http://www.dovmemodelleri.com/"+s+"/"+ s +"-0" + iterator + ".jpg.php").get();
                else if(iterator<1000)
                    doc = Jsoup.connect("http://www.dovmemodelleri.com/"+s+"/"+ s +"-" + iterator + ".jpg.php").get();
                else
                    doc = Jsoup.connect("http://www.dovmemodelleri.com/"+s+"/"+ s +"-" + iterator + ".jpg.php").get();


            Element img = doc.select("div#image img").first();
            imgSrc = img.attr("src");
            System.out.println("http://www.dovmemodelleri.com/" + imgSrc);
            src = "http://www.dovmemodelleri.com/" + imgSrc;
            }
            catch (java.lang.NullPointerException e)
            {  }

            int indexname = 0;
            if (src != null) {
                indexname = src.lastIndexOf("/");
            }

            if (src != null && indexname == src.length()) {
                src = src.substring(1, indexname);
            }


            if (src != null) {
                indexname = src.lastIndexOf("/") + 1;
            }

            String imageName = null;
            if (src != null) {
                imageName = src.substring(indexname, src.length());
            }


            System.out.println("Dosya Adı : " + imageName);

            URL url = new URL("http://www.dovmemodelleri.com/" + imgSrc);


            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(dir + "\\" + imageName));
                 InputStream in = url.openStream()) {
                for (int b; (b = in.read()) != -1; ) {
                    out.write(b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            iterator++;

        }

    }
}
