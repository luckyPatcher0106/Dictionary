package com.example.dictionarydemo.service;




import com.example.dictionarydemo.UseForServices.Word;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DictionaryManagement {
    private List<Word> words = new ArrayList<>();

    public List<Word> getWords() {
        return words;
    }

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng từ vựng: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập từ tiếng Anh: ");
            String wordTarget = scanner.nextLine();
            System.out.print("Nhập giải thích bằng tiếng Việt: ");
            String wordExplain = scanner.nextLine();
            Word word = new Word(wordTarget, wordExplain, new ArrayList<>(), "");
            words.add(word);
        }
    }

    private List<Word> sortASC() {
        List<Word> sortedList = new ArrayList<>(List.copyOf(words));
        sortedList.sort(Comparator.comparing(Word::getWordTarget));
        return sortedList;
    }

    public void showAllWords() {
        List<Word> sortedList = sortASC();
        int cnt = 1;
        System.out.printf("%-5s| %-20s| %-20s\n", "No", "English", "Vietnamese");
        for (Word w : sortedList) {
            System.out.printf("%-5d| %-20s| %-20s\n", cnt++, w.getWordTarget(), w.getWordExplain());
        }
    }

    public void insertFromFile() {
        this.words = FileService.readAllWordFromFile(CommonConstant.FILE_NAME_DICTIONARY);
    }

    //dictionaryLookup() có chức năng tra cứu từ điển bằng dòng lệnh.
    public Word dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ cần tra cứu: ");
        String wordTarget = scanner.nextLine();
        for (Word w : words) {
            if (w.getWordTarget().equals(wordTarget)) {
                return w;
            }
        }
        return null;
    }

    public Boolean editWordFromCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ cần sửa: ");
        String wordTarget = scanner.nextLine();
        for (Word w : words) {
            if (w.getWordTarget().equals(wordTarget)) {
                System.out.print("Nhập từ mới: ");
                String newWordTarget = scanner.nextLine();
                System.out.print("Nhập giải thích mới: ");
                String newWordExplain = scanner.nextLine();
                w.setWordTarget(newWordTarget);
                w.setWordExplain(newWordExplain);
                return true;
            }
        }
        return false;
    }

    public boolean deleteWordFromCommand(String wordTarget) {
        for (Word w : words) {
            if (w.getWordTarget().equals(wordTarget)) {
                words.remove(w);
                return true;
            }
        }
        return false;
    }

    //dictionarySearcher() có chức năng tìm kiếm các từ.
    public List<Word> binarySearch(List<Word> words, String searchWord) {
        List<Word> result = new ArrayList<>();
        int left = 0;
        int right = words.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Word word = words.get(mid);
            String wordTarget = word.getWordTarget();

            if (wordTarget.startsWith(searchWord)) {
                result.add(word);

                int i = mid - 1;
                while (i >= 0 && words.get(i).getWordTarget().startsWith(searchWord)) {
                    result.add(words.get(i));
                    i--;
                }

                int j = mid + 1;
                while (j < words.size() && words.get(j).getWordTarget().startsWith(searchWord)) {
                    result.add(words.get(j));
                    j++;
                }

                break;
            } else if (searchWord.compareTo(wordTarget) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public void dictionaryExportToFile() {
        FileService.writeWordsToFile(CommonConstant.FILE_NAME_DICTIONARY, words);
    }

    public void showAllWordsByList(List<Word> list) {
        int cnt = 1;
        System.out.printf("%-5s| %-20s| %-20s\n", "No", "English", "Vietnamese");
        for (Word w : list) {
            System.out.printf("%-5d| %-20s| %-20s\n", cnt++, w.getWordTarget(), w.getWordExplain());
        }
    }


    public Word getWordByWordTarget(String selectedItem) {
        for (Word w : words) {
            if (w.getWordTarget().equals(selectedItem)) {
                return w;
            }
        }
        return null;
    }

    public List<Word> filterByKey(String key) {
        return words.stream().filter(w -> w.getWordTarget().toLowerCase().contains(key.toLowerCase())).collect(Collectors.toList());
    }

    public void addNewWord(Word newWord) {
        this.words.add(newWord);
        FileService.writeWordsToFile(CommonConstant.FILE_NAME_DICTIONARY, words);
    }

    public void updateWords() {
        FileService.writeWordsToFile(CommonConstant.FILE_NAME_DICTIONARY, words);
    }
}
