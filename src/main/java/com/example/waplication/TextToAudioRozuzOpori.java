package com.example.waplication;

import com.example.waplication.maps.MapRozus_Op;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextToAudioRozuzOpori {

    public void textToAudioRozuzOpori(String text) throws IOException {

        Concatenate concatenateR100 = new Concatenate();
        Duration duration = new Duration();
        MapRozus_Op mapRozus_Op = new MapRozus_Op();
        RenameFile renameFileF = new RenameFile();
        IfDigitalInWords ifDigital =new IfDigitalInWords();

        String filePathName = "F:\\textToAudio/готовое/";
        List<String> lines = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();

        FileReader fr = new FileReader("F:\\textToAudio/doc.txt");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("F:\\textToAudio/docR100.txt");
        String line;

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
                if (Character.isUpperCase(line.charAt(i))) {  //Проверка строки на наличие Прописных букв и добавление символа '^' перед ними
                    stringBuilder.append("^").append(line.charAt(i));
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

        string = "<" + string; //Открытие языка
        string = string + ">"; // Закрытие языка

        System.out.println("string " + string);
        String[] rozuzOpor = new String[string.length()];
        String ss;
        for (
                int i = 0; i < string.length(); i++) {
            ss = mapRozus_Op.replace(string.charAt(i));
            rozuzOpor[i] = ss;
        }


        try {
            concatenateR100.concatenateFiles(rozuzOpor, filePathName + fileName + "_Розуз_опоры ");
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        File fileR100 = new File(filePathName + fileName + "_Розуз_опоры ");
        renameFileF.rename(filePathName + fileName + "_Розуз_опоры ", duration.durationFileOnly48kGh(fileR100), ".wav");

        String listString = String.join(",", rozuzOpor);
        listString = listString.replace(",F:\\YandexDisk/textToAudio/Rozuz_Op_AV/", "").
                replace("_Отделение слов", "_");

        listString = listString.replace(".wav,F:\\YandexDisk/textToAudio/Rozuz_OP_AV/", "").
                replaceAll("\\d", ""); //replaceAll("\\d", "") удаляет все цифры

        listString = listString.replace("F:\\YandexDisk/textToAudio/Rozuz_OP_AV/","");
        listString = listString.replace("_Начало переизлучения","< ");
        listString = listString.replace("_Конец переизлучения"," >");
        listString = listString.replace("_Заглавные буквы","^");
        listString = listString.replace("_Знаки препинания","$");
        listString = listString.replace(".wav","");


        //System.out.println(stringBuilderCheck);
        System.out.println("Розуз-опоры с удалением путей++ " + listString);
    }
}
