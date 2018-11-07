package com.epam.javatraining.textcomposite;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class MainTest
{
    public static Logger logger = LogManager.getLogger("TextCompositeSuite");
    /**
     * Rigorous Test :-)
     */

    private static String readAllBytes(String filePath)
    {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        }
        return content;
    }

    public String removeOccurrencesFirstOrLastLetter(String content, boolean first) {

        Text text = new Text(content);

        for(int i = 0; i < text.size(); i++) {
            Paragraph paragraph = text.get(i);

            if(paragraph.isSeparator()) {
                continue;
            }

            for(int j = 0; j < paragraph.size(); j++) {

                Sentence sentence = paragraph.get(j);
                if(sentence.isSeparator()) {
                    continue;
                }

                for(int k = 0; k < sentence.size(); k++) {

                    Word word = sentence.get(k);
                    if(word.isSeparator()) {
                        continue;
                    }

                    Symbol symbol = (first) ? word.get(0) : word.get( word.size()-1 );
                    int offset = (first) ? 1 : 0;
                    int m = offset;
                    offset--;
                    while( m < word.size() + offset) {

                        if(word.isSeparator()) {
                            continue;
                        }

                        if(word.get(m).equals(symbol)) {
                            word.remove(m);
                        } else {
                            m++;
                        }
                    }
                }
            }
        }

        return text.toString();
    }

    public String exchangeExtremeWords(String content) {
        Text text = new Text(content);

        for(int i = 0; i < text.size(); i++) {
            Paragraph paragraph = text.get(i);

            if(paragraph.isSeparator()) {
                continue;
            }

            for(int j = 0; j < paragraph.size(); j++) {

                Sentence sentence = paragraph.get(j);
                if(sentence.isSeparator() && sentence.countNotSeparator() <= 1) {
                    continue;
                }

                int offset = sentence.size() - 1;
                int firstWord = sentence.get(0).isSeparator() ? 1 : 0;
                int lastWord = sentence.get(offset).isSeparator() ? offset - 1 : offset;
                Word tmp = sentence.get(firstWord);
                sentence.set(firstWord, sentence.get(lastWord));
                sentence.set(lastWord, tmp);
            }
        }

        return text.toString();
    }

    public String sentenceWithMaxWordFreq(String content) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        Text text = new Text(content);

        // Collect all sentence in one big list
        for(int i = 0; i < text.size(); i++) {
            Paragraph paragraph = text.get(i);

            if (paragraph.isSeparator()) {
                continue;
            }

            for (int j = 0; j < paragraph.size(); j++) {

                Sentence sentence = paragraph.get(j);
                if (sentence.isSeparator()) {
                    continue;
                }
                sentences.add(sentence);
            }
        }

        // Calculate frequency for each word
        Word maxFreqWord = null;
        int maxFreq = 1;

        for(int i = 0; i < sentences.size(); i++) {
            Sentence sentence = sentences.get(i);
            for (int j = 0; j < sentence.size(); j++) {

                Word word = sentence.get(j);
                if (word.isSeparator()) {
                    continue;
                }

                int freq = 1;
                for (int k = i; k < sentences.size(); k++) {
                    if (sentences.get(k).contains(word)) {
                        freq++;
                    }
                }

                if( freq > maxFreq ) {
                    maxFreq = freq;
                    maxFreqWord = word;
                } else if (maxFreqWord == null) {
                    maxFreqWord = word;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Word: " + maxFreqWord + "\nFrequency: " + maxFreq + "\nSentences:\n");
        for(Sentence sentence : sentences) {
            if(sentence.contains(maxFreqWord)) {
                result.append(sentence + "\n");
            }
        }

        return result.toString();
    }

    @Test
    public void Task15() {
        String content = readAllBytes("./src/test/resources/text.txt");

        logger.trace("\n" + content);
        logger.trace("\n\n" + removeOccurrencesFirstOrLastLetter(content, true) );
        logger.trace("\n\n" + removeOccurrencesFirstOrLastLetter(content, false) );
    }

    @Test
    void Task5() {
        String content = readAllBytes("./src/test/resources/text.txt");

        logger.trace("\n" + content);
        logger.trace("\n\n" + exchangeExtremeWords(content) );
    }

    @Test
    void Task1() {
        String content = readAllBytes("./src/test/resources/wordfreq.txt");

        logger.trace("\n" + content);
        logger.trace("\n\n" + sentenceWithMaxWordFreq(content) );
    }
}
