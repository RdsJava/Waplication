package com.example.waplication;

import com.example.waplication.maps.MapRadasteid;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextToAudioRadasteid {

    public void textToAudioRadasteid(String text) throws IOException {

        Concatenate concatenate = new Concatenate();
        Duration duration = new Duration();
        MapRadasteid mapRadasteid = new MapRadasteid();
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


            for (int i = 0; i < line.length(); i++) {
                if (Character.isUpperCase(line.charAt(i))) {  //Проверка строки на наличие Прописных букв и добавление символа '#' перед ними
                    stringBuilder.append("#").append(line.charAt(i));
                } else if (line.charAt(i) == '.') {
                    stringBuilder.append(line.charAt(i)).append("$"); // Добовляет символ окончания Предложения
                } else if (line.charAt(i) == '!') {
                    stringBuilder.append(line.charAt(i)).append("$"); // Добовляет символ окончания Предложения
                } else if (line.charAt(i) == '?') {
                    stringBuilder.append(line.charAt(i)).append("$"); // Добовляет символ окончания Предложения
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

        System.out.println("string " + string);
        String[] radadasteid = new String[string.length()];
        String ss;
        for (
                int i = 0; i < string.length(); i++) {
            ss = mapRadasteid.replaceRadasteid(string.charAt(i));
            radadasteid[i] = ss;
        }

        try {
            concatenate.concatenateFiles(radadasteid, filePathName + fileName + "Радастеид ");
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        File fileR100 = new File(filePathName + fileName + "Радастеид ");

        renameFileF.rename(filePathName + fileName + "Радастеид ", duration.durationFileOnly48kGh(fileR100), ".wav");

        String listString = String.join(",", radadasteid);
        listString = listString.replace(".wav,F:\\textToAudio/Radasteid_AV", "");

        listString = listString.replace(".wav,F:\\textToAudio/Radasteid_AV", "");


        System.out.println("Радастеид-100 с удалением путей++ " + listString);
    }
}

