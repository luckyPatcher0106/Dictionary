package com.example.dictionarydemo.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class File_link {
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    private static PrintWriter printWriter;
    private static Scanner scanner;

    public static void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
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
        return new Word(datas[0], datas[1]);
    }

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
            printWriter.println(w.getWordTarget() + "\t" + w.getWordExplain());
        }
        closeFileAfterWrite();
    }

    public static List<ques> readAllQuestionFromFile(String fileName) {
        openFileToRead(fileName);
        List<ques> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(createQuestionFromData(scanner.nextLine()));
        }
        closeFileAfterRead();
        return list;
    }

    private static ques createQuestionFromData(String data) {
        String[] datas = data.split("\t");
        return new ques(datas[0], Integer.parseInt(datas[1]), createAnswersFromData(Integer.parseInt(datas[1]),
                datas[2]));
    }

    private static List<ans> createAnswersFromData(Integer correctPosition, String data) {
        String[] datas = data.split("\\|");
        List<ans> answers = new ArrayList<>();

        for (int i = 0; i < datas.length; i++) {
            answers.add(new ans(datas[i], correctPosition - 1 == i));
        }

        return answers;
    }

}

