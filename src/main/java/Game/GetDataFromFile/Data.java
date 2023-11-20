package Game.GetDataFromFile;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Data {

    private Set<Integer> Total = new HashSet<Integer>();

    int index = 0;

    private List<String> word;

    private String SingleWord ;

    private Random rand = new Random();

    public Data() {
        this.Total = new HashSet<Integer>();
        this.word = null;
    }

    public Data(Set<Integer> Total, List<String> word) {
        this.Total = Total;
        this.word = word;
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

    public void GetData() {
        try(InputStream is = Data.class.getResourceAsStream("/Game/wordlist.txt");) {
            word = new java.io.BufferedReader(new java.io.InputStreamReader(is)).lines().collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
