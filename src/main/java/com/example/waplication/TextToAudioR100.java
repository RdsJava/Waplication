package com.example.waplication;

import com.example.waplication.maps.MapR100;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextToAudioR100 {
    public void textToAudioR100(String text) throws IOException {

        Concatenate concatenateR100 = new Concatenate();
        Duration duration = new Duration();
        MapR100 mapR100 = new MapR100();
        RenameFile renameFileF = new RenameFile();
        IfDigitalInWords ifDigital =new IfDigitalInWords();

        String filePathName = "F:\\textToAudio/готовое/";
        List<String> lines = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();

        FileReader fr = new FileReader("F:\\textToAudio/doc.txt");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("F:\\textToAudio/docR100.txt");
        String line = text;

        String firstStringNoWhiteSpaceStartEnd = text.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        String fileName = firstStringNoWhiteSpaceStartEnd.toUpperCase();

        // Удаление пустых строк и пробелов в конце и в начале строк++
        while ((line = br.readLine()) != null) {
            line = line.trim(); // remove leading and trailing whitespace
            line = line.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
            line = line.replaceAll("\\s+", " ").trim().concat("\n");
            line = line.replaceAll("(?m)^[ \t]*\r?\n", ""); //удалением пустых строк
            line = line.replaceAll("\\s", "_"); //Замена пробелов на '_'

            if (!line.equals("")) {
                if (Character.isUpperCase(line.charAt(0))) {
                    stringBuilder.append("^").append(line.charAt(0)); // Проверка первого символа В начале строки на Прописную букву и Добавление символа '@' перед ним
                } else if (Character.isLowerCase(line.charAt(0))) {
                    stringBuilder.append(line.charAt(0)); // Проверка первого символа В начале строки на строчную букву
                } else if (String.valueOf(line.charAt(0)).matches("\\d")) { // Проверка на цифру(от 0 до 9) с возвратом цифруСловом
                    stringBuilder.append(ifDigital.checkDigitalChar(line.charAt(0)));
                }
            }

            for (int i = 1; i < line.length(); i++) {
                if (Character.isUpperCase(line.charAt(i))) {  //Проверка строки на наличие Прописных букв и добавление символа '#' перед ними
                    stringBuilder.append("#").append(line.charAt(i));
                } else if (line.charAt(i) == '.') {
                    stringBuilder.append(line.charAt(i)).append("$");
                } else if (line.charAt(i) == '!') {
                    stringBuilder.append(line.charAt(i)).append("$");
                } else if (line.charAt(i) == '?') {
                    stringBuilder.append(line.charAt(i)).append("$");
                } else if (String.valueOf(line.charAt(i)).matches("-?\\d+(\\.\\d+)?")) { // Проверка на цифру(от 0 до 9) с возвратом цифруСловом

                    
                    stringBuilder.append(ifDigital.checkDigitalChar(line.charAt(i)));
                } else {
                    stringBuilder.append(line.charAt(i));
                }
            }
        }

        System.out.println("line " + lines);
        System.out.println();

        fr.close(); // Закрытие FileReader
        fw.close(); // Закрытие BufferedReader

        System.out.println("stringBuilder " + stringBuilder);
        String string = String.valueOf(stringBuilder);

        if (string.endsWith("_")) {
            string = string.substring(0, string.length() - 1);
        }

        string = ">" + string; //Открытие языка
        string = string + ">"; // Закрытие языка

        System.out.println("string " + string);
        String[] rad100 = new String[string.length()];
        String ss;
        for (
                int i = 0; i < string.length(); i++) {
            ss = mapR100.replaceR100(string.charAt(i));
            rad100[i] = ss;
        }

        try {
            concatenateR100.concatenateFiles(rad100, filePathName + fileName + "_Р100 ");
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        File fileR100 = new File(filePathName + fileName + "_Р100 ");

        renameFileF.rename(filePathName + fileName + "_Р100 ", duration.durationFileOnly48kGh(fileR100), ".wav");

        String listString = String.join(",", rad100);
        listString = listString.replace(".wav,F:\\textToAudio/R100_AV", "").

                replace("ОЗРА", "_");

        listString = listString.replace(".wav,F:\\textToAudio/R100_AV", "").

                replaceAll("\\d", ""); //replaceAll("\\d", "") удаляет все цифры

        //listString = listString.replace("F:\\textToAudio/R100_AV/", "").

               // replace(".wav,", "");
        listString = listString.replace("(Заглавные буквы в начале строки)", "");
        listString = listString.replace("(Заглавные буквы внутри строки)", "");
        listString = listString.replace("(знаки препинания)" , "");
        listString = listString.replace("(отделяет слова)", "");
        listString = listString.replace("(отделяет предложения)", "");
        listString = listString.replace("F:\\textToAudio/R_AV/","");
        listString = listString.replace(".wav","");


        //System.out.println(stringBuilderCheck);
        System.out.println("Радастеид-100 с удалением путей++ " + listString);
    }
}



