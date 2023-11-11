package com.example.dictionarydemo.UseForServices;

import java.util.List;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private List<String> interjections;
    private String relation;

    public Word(String wordTarget, String wordExplain, List<String> interjections, String relation) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.interjections = interjections;
        this.relation = relation;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void setInterjection(List<String> interjections) {
        this.interjections = interjections;
    }

    public List<String> getInterjections() {
        return interjections;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordTarget='" + wordTarget + '\'' +
                ", wordExplain='" + wordExplain + '\'' +
                ", interjections=" + interjections +
                ", relation='" + relation + '\'' +
                '}';
    }
}
