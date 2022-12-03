package com.jordivicent.linktracker.Utils;

import com.jordivicent.linktracker.Model.WebPage;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    public static List<WebPage> loadPages(Path file){
        System.out.println(file);
        try{
             return Files.lines(Paths.get(String.valueOf(file)))
                    .map(line -> new WebPage(line.split(";")[0],
                            line.split(";")[1]))
                    .collect(Collectors.toList());
        }catch (Exception e){
            MessageUtils.errorFormato(file);
            return null;
        }//end_try/catch
    }//end_loadPages
}
