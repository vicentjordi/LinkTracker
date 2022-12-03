package com.jordivicent.linktracker.Utils;

import com.jordivicent.linktracker.Model.WebPage;

import java.nio.file.Path;
import java.util.List;

public class FileUtils {

    public static List<WebPage> loadPages(Path file){
        System.out.println(file);
        try{

        }catch (Exception e){
            MessageUtils.errorFormato(file);
        }
        return null;
    }//end_loadPages
}
