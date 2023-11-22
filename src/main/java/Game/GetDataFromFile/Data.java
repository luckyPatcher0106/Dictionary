package Game.GetDataFromFile;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Data {

    private Set<Integer> Total = new HashSet<>();

    int index = 0;

    private List<String> word;

    private String SingleWord ;

    private Random rand = new Random();

    private static Data instance = null;

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }


    public String getSingleWord(){
        return SingleWord;
    }

    public void setSingleWord(String singleWord) {
        SingleWord = singleWord;
    }

    public boolean validWord(String s){
        return word.stream().anyMatch((e -> e.equals(s)));
    }

    public Set<Integer> getTotal() {
        return Total;
    }

    private Data() {
        System.out.println("building word data");
        File file = new File("wordlist.txt"); // Đường dẫn tương đối
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            word = br.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void setRandWord() {
        do{
            index = rand.nextInt(word.size());
        }while(!Total.add(index));
        {
            SingleWord = word.get(index);
        }
    }
}
