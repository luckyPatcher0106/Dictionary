package com.example.dictionarydemo.service;



import com.example.dictionarydemo.UseForServices.Word;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    private static PrintWriter printWriter;
    private static Scanner scanner;

    public static void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeFileAfterWrite() {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openFileToRead(String fileName) {
        try {
            scanner = new Scanner(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeFileAfterRead() {
        scanner.close();
    }

    public static Word createWordFromData(String data) {
        String[] datas = data.split("\t");
        if (datas.length >= 4) {
            return new Word(datas[0], datas[1], convertToList(datas[2]), datas[3]);
        } else if (datas.length == 3) {
            return new Word(datas[0], datas[1], convertToList(datas[2]), "");
        } else {
            return new Word(datas[0], datas[1], new ArrayList<>(), "");
        }
    }

    private static List<String> convertToList(String data) {
        if (data.isEmpty()) {
            return new ArrayList<>();
        }
        return List.of(data.split("\\|"));
    }

    //TODO: Read all word from file FILE_NAME trong CommonConstant
    public static List<Word> readAllWordFromFile(String fileName) {
        openFileToRead(fileName);
        List<Word> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(createWordFromData(scanner.nextLine()));
        }
        closeFileAfterRead();
        return list;
    }

    public static void writeWordsToFile(String fileName, List<Word> words) {
        openFileToWrite(fileName);
        for (Word w : words) {
            printWriter.println(w.getWordTarget() + "\t" + w.getWordExplain() + "\t" + String.join("|",
                    w.getInterjections()) + "\t" + w.getRelation());
        }
        closeFileAfterWrite();
    }

    public static void writeScoreToFile(String fileName, String score) {
        openFileToWrite(fileName);
        printWriter.println(score);
        closeFileAfterWrite();
    }
//CommonConstant la phuong thuc chua cac hang so string ten file
    public static List<String> getScoreInfo() {
        openFileToRead(CommonConstant.FILE_NAME_SCORE);
        String finalScore = "";
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                finalScore = line;
            }
        }
        closeFileAfterRead();
        return new ArrayList<>(List.of(finalScore.split("\\|")));
    }


}
