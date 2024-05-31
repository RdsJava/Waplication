package com.example.waplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

import java.io.*;

public class HelloController {

    @FXML
    private CheckBox checkBoxOpHl;
    @FXML
    private CheckBox checkBoxCiklo;
    @FXML
    private CheckBox checkBoxNomernoi;
    @FXML
    private CheckBox checkBoxRitmo;
    @FXML
    private CheckBox checkBoxOpRd;
    @FXML
    private CheckBox checkBoxR100;
    @FXML
    private CheckBox checkBoxRd;
    @FXML
    private CheckBox checkBoxRozOp;


    @FXML
    private TextArea texts;

    @FXML
    private String text;

    @FXML
    void checkBoxInitialize(ActionEvent actionEvent) {

        Thread thread = new Thread(() -> {
            String d = texts.getText();

            //Запись в файл текста:
            try {
                FileWriter writers = new FileWriter("F:\\textToAudio/doc.txt");
                writers.write(d);
                writers.flush();//Данные, которые вы записываете в Writer, иногда временно хранятся в буфере, метод flush() используется для сброса (flush) всех данных из буфера в целевой объект.
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            BufferedReader brTest = null;
            try {
                brTest = new BufferedReader(new FileReader("F:\\textToAudio/doc.txt"));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try {
                text = brTest.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        thread.start();
    }


    @FXML
    void buttonClick(ActionEvent event) {

        TextAudioOporiHladovit textToAudioOporiHladovit = new TextAudioOporiHladovit();
        TextToAudioCiklo textToAudioCiklo = new TextToAudioCiklo();
        TextToAudioNomernoi textToAudioNomernoi = new TextToAudioNomernoi();
        TextToAudioRitmo textToAudioRitmo = new TextToAudioRitmo();
        TextToAudioOporiRadasteid textToAudioOporiRadasteid = new TextToAudioOporiRadasteid();
        TextToAudioR100 textToAudioR100 = new TextToAudioR100();
        TextToAudioRadasteid textToAudioRadasteid = new TextToAudioRadasteid();
        TextToAudioRozuzOpori textToAudioRozuzOpori = new TextToAudioRozuzOpori();


        try {
            if (checkBoxOpHl.isSelected()) {
                textToAudioOporiHladovit.textToAudioOporiHladovit(text);
            }
            if (checkBoxCiklo.isSelected()) {
                textToAudioCiklo.textToAudioCiklo(text);
            }
            if (checkBoxNomernoi.isSelected()) {
                textToAudioNomernoi.textToAudioNomernoi(text);
            }
            if (checkBoxRitmo.isSelected()) {
                textToAudioRitmo.textToAudioRitmo(text);
            }
            if (checkBoxOpRd.isSelected()) {
                textToAudioOporiRadasteid.textToAudioOporiRadasteid(text);
            }
            if (checkBoxR100.isSelected()) {
                textToAudioR100.textToAudioR100(text);
            }
            if (checkBoxRd.isSelected()) {
                textToAudioRadasteid.textToAudioRadasteid(text);
            }
            if (checkBoxRozOp.isSelected()) {
                textToAudioRozuzOpori.textToAudioRozuzOpori(text);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}



