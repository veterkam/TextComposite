package com.epam.javatraining.textcomposite;

import java.util.*;

public class TextCutter {
    private ArrayList<String> elements;
    private ArrayList<String> separators;
    private boolean isSeparatorFirst;

    public TextCutter(String text, String regexDelimiter) {
        String[] slices = text.split(regexDelimiter);
        elements = new ArrayList<>();
        separators = new ArrayList<>();

        if(slices.length == 0) {
            if(text.length() != 0) {
                separators.add(text);
            }
        } else {

            isSeparatorFirst = slices[0].isEmpty();
            if(!isSeparatorFirst) {
                elements.add(slices[0]);
            }

            int begin;
            int end = 0;
            int i;
            for(i = 0; i < slices.length - 1 ; i++) {
                // Read delimiter
                begin = end + slices[i].length();
                end = text.indexOf(slices[i+1], begin);
                String delimiter = text.substring(begin, end);
                separators.add(delimiter);
                elements.add(slices[i+1]);
            }

            begin = end + slices[i].length();
            if(begin < text.length()) {
                end = text.length();
                String delimiter = text.substring(begin, end);
                separators.add(delimiter);
            }
        }
    }

    public static List<String> cut(String text, String regexDelimiter) {
        String[] slices = text.split(regexDelimiter);
        List<String>elements = new ArrayList<>();

        if(slices.length == 0) {
            if(text.length() != 0) {
                elements.add(text);
            }
        } else {
            int begin;
            int end = 0;
            for(int i = 0; i < slices.length; i++) {
                // Read element with delimiter
                begin = end;
                end = (i == slices.length - 1) ? text.length() : text.indexOf(slices[i+1], begin);
                String element = text.substring(begin, end);
                elements.add(element);
            }
        }

        return elements;
    }

    public List<String> getElements() {
        return elements;
    }

    public List<String> getSeparators() {
        return separators;
    }

    public boolean isSeparatorFirst() {
        return isSeparatorFirst;
    }
}
