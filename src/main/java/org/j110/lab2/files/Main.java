package org.j110.lab2.files;

import org.j110.lab2.files.files.*;

public class Main {
    public static void main(String[] args) {
        File[] files = {
                new Document("j110-lab2-hiers.docx", 23212, "docx", 2),
                new Image("spb-map.png", 1703527, "image", 1024, 3072),
                new MultimediaFile("06-PrettyGirl.mp3", 7893454, "audio", "Eric Clapton, Pretty Girl", 328),
                new VideoFile("BackToTheFuture1.avi", 1470984192, "video", "Back to the future I, 1985", 6488, 640, 352)
        };
        PrintFiles.printAll(files);
        Image[] images = {
                new Image("Mountain-Flowers.jpg", 3765345, "image", 2880, 1920),
                new Image("dom_zvezdy.jpg", 1303853, "image", 1918, 1280),
                new Image("sea-turtle.jpg", 219218, "image", 1500, 1000)
        };
        PrintFiles.printAll(images);
        /*
        File.printAll method takes an array
        of objects of the File class as an argument.
        Since the Image class is a subclass of the
        File class we can use this method for an array of Image instances.
        * */
    }
}
