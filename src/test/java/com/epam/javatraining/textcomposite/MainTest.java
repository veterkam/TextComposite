package com.epam.javatraining.textcomposite;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

    public Text removeOccurrencesFirstOrLastLetter(Text text, boolean first) {
        for(int i = 0; i < text.size(); i++) {
            Paragraph paragraph = text.get(i);

            for (int j = 0; j < paragraph.size(); j++) {

                Sentence sentence = paragraph.get(j);

                for (int k = 0; k < sentence.size(); k++) {

                    Word word = sentence.get(k);
                    if (word.isSeparator()) {
                        continue;
                    }

                    Symbol symbol = (first) ? word.get(0) : word.get(word.size() - 1);
                    int offset = (first) ? 1 : 0;
                    int m = offset;
                    offset--;
                    while (m < word.size() + offset) {

                        if (word.isSeparator()) {
                            continue;
                        }

                        if (word.get(m).equals(symbol)) {
                            word.remove(m);
                        } else {
                            m++;
                        }
                    }
                }
            }
        }

        return text;
    }

    public Text exchangeExtremeWords(Text text) {
        for(int i = 0; i < text.size(); i++) {
            Paragraph paragraph = text.get(i);

            for(int j = 0; j < paragraph.size(); j++) {

                Sentence sentence = paragraph.get(j);
                if(sentence.countRealWord() <= 1) {
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

        return text;
    }

    public String sentenceWithMaxWordFreq(String content) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        Text text = new Text(content);

        // Collect all sentence in one big list
        for(int i = 0; i < text.size(); i++) {
            Paragraph paragraph = text.get(i);

            for (int j = 0; j < paragraph.size(); j++) {
                Sentence sentence = paragraph.get(j);
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

        Text text1 = removeOccurrencesFirstOrLastLetter( new Text(content), true );
        Text text2 = removeOccurrencesFirstOrLastLetter( new Text(content), false );

        logger.trace("\n" + content);
        logger.trace("\n\n" + text1 );
        logger.trace("\n\n" + text2 );

        Assert.assertTrue(true);
    }

    @Test
    void Task5() {
        String content = readAllBytes("./src/test/resources/text.txt");

        Text text = exchangeExtremeWords(new Text(content));

        logger.trace("\n" + content);
        logger.trace("\n\n" + text );

        Assert.assertTrue(true);
    }

    @Test
    void Task1() {
        String content = readAllBytes("./src/test/resources/wordfreq.txt");

        logger.trace("\n" + content);
        logger.trace("\n\n" + sentenceWithMaxWordFreq(content) );

        Assert.assertTrue(true);
    }

    @Test
    void composite() {
        String content = readAllBytes("./src/test/resources/text.txt");
        Text txt = new Text(content);

        Assert.assertTrue(content.equals(txt.toString()));
    }

    @Test
    void compareWords() {
        Word a = new Word("Brother");
        Word b = new Word("Sister");
        int result = a.compareTo(b);

        Assert.assertEquals(result, -1);

        a = new Word("Zorro");
        b = new Word("Apple");
        result = a.compareTo(b);

        Assert.assertEquals(result, 1);

        a = new Word("composite");
        b = new Word("composite");
        result = a.compareTo(b);

        Assert.assertEquals(result, 0);

    }

    @Test
    void sortWordsInSentence() {
        String expected = "        AppleBrotherCowMotherNorthRockSisterWolfZorro";
        Sentence unsort =   new Sentence("Cow Wolf Sister Apple Zorro Mother Rock Brother North");
        unsort.sort();

        Assert.assertTrue(unsort.toString().equals( expected ));
    }

    @Test
    void sortSentencesInParagraph() {
        String expected = "Apple Zorro. Brother North!Cow. Mother Rock? Sister? Wolf! ";
        Paragraph unsort =   new Paragraph("Cow. Wolf! Sister? Apple Zorro. Mother Rock? Brother North!");
        unsort.sort();

        Assert.assertTrue(unsort.toString().equals( expected.toString() ));
    }

    @Test
    void equals() {
        String content = readAllBytes("./src/test/resources/text.txt");

        Text t1 = new Text(content);
        Text t2 = new Text(content);

        Assert.assertTrue(t1.equals(t2));

        t1 = new Text(content);
        t2 = new Text(content + "test");

        Assert.assertFalse(t1.equals(t2));
    }

    @Test
    void copy() {
        String content = readAllBytes("./src/test/resources/text.txt");

        Text t1 = new Text(content);
        Text t2 = t1.copy();

        Assert.assertTrue(t1 != t2 && t1.equals(t2));
    }

    @Test
    void contains() {
        Sentence sentence = new Sentence("The original and reference implementation Java compilers");
        Word word = new Word("Java");

        Assert.assertTrue(sentence.contains(word));

        String content = readAllBytes("./src/test/resources/text.txt");
        Text text = new Text(content);
        word = new Word("Apache");
        Assert.assertTrue(text.contains(word));

        sentence = new Sentence("Please obtain a copy of the License at web site.");
        Assert.assertTrue(text.contains(sentence));
    }

    @Test
    void removeOccurrencesFirstOrLastLetterTest() {
        String content = "ananas greg drozd perepel vorovka";
        String expected = "anns gre droz pereel voroka";

        Text txt = new Text(content);
        Text exp = new Text(expected);
        txt = removeOccurrencesFirstOrLastLetter(txt, true);
        Assert.assertTrue(txt.equals(exp));

        content = "поп боб жлоб арка молоко";
        expected = "оп об жлоб рка млко";

        txt = new Text(content);
        exp = new Text(expected);
        txt = removeOccurrencesFirstOrLastLetter(txt, false);
        Assert.assertTrue(txt.equals(exp));
    }

    @Test
    void exchangeExtremeWords() {
        String content = "Please obtain a copy of the License at web site. The original and reference implementation Java compilers.";
        String expected = "site obtain a copy of the License at web Please. compilers original and reference implementation Java The.";
        Text txt = new Text(content);
        Text exp = new Text(expected);
        txt = exchangeExtremeWords(txt);
        Assert.assertTrue(txt.equals(exp));
    }
}
