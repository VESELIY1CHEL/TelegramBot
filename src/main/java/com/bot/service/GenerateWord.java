package com.bot.service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class GenerateWord {
    String word;
    List<String> listOfWords;
    public GenerateWord(){

        try(InputStream is = this.getClass().getResourceAsStream("/wordCrocodile.txt")) {
           String thi = new String(is.readAllBytes());
           listOfWords = Arrays.asList(thi.split(","));
           word = listOfWords.get(ThreadLocalRandom.current().nextInt(0,listOfWords.size())).trim();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public String getWord(){
        return word;
    }
    public String changeWord(){
        word = listOfWords.get(ThreadLocalRandom.current().nextInt(0,listOfWords.size())).trim();
        return word;
    }


}
