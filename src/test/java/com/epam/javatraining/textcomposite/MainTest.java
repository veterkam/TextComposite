package com.epam.javatraining.textcomposite;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class MainTest
{
    public static Logger logger = LogManager.getLogger("TextCompositeSuite");
    /**
     * Rigorous Test :-)
     */
    @Test
    public void strEq()
    {
        String separators = "[\\s.,?!'\":;_+=(){}\\[\\]|<>&-]+";
        String text = "?!.,a,:_123bd 45c:'\"678d , hello my frend!\n How are you?!!!! 'I'm fine',.";

        TextCutter cutter = new TextCutter(text, separators);
        List first = cutter.isSeparatorFirst() ? cutter.getSeparators() : cutter.getElements();
        List second = !cutter.isSeparatorFirst() ? cutter.getSeparators() : cutter.getElements();

        int fi = 0;
        int si = 0;
        while( fi < first.size() && si < second.size()) {
            logger.trace("["+first.get(fi)+"]");
            logger.trace("["+second.get(si)+"]");
            fi++;
            si++;
        }

        if(fi < first.size()) {
            logger.trace("["+first.get(fi)+"]");
        }

        if(si < second.size()) {
            logger.trace("["+second.get(si)+"]");
        }

        assertTrue( "abcd".compareTo("abcd") == 0 );
    }

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

            if(!paragraph.isSeparator()) {

                for(int j = 0; j < paragraph.size(); j++) {

                    Sentence sentence = paragraph.get(j);
                    if(!sentence.isSeparator()) {

                        for(int k = 0; k < sentence.size(); k++) {

                            Word word = sentence.get(k);
                            if(!word.isSeparator()) {

                                Symbol symbol = (first) ? word.get(0) : word.get( word.size()-1 );
                                int offset = (first) ? 1 : 0;
                                int m = offset;
                                offset--;
                                 while( m < word.size() + offset) {
                                    if(word.get(m).equal(symbol)) {
                                        word.remove(m);
                                    } else {
                                        m++;
                                    }
                                 }
                            }
                        }
                    }
                }
            }
        }

        return text.toString();
    }

    @Test
    public void Task15() {
        String content = readAllBytes("./src/test/resources/text.txt");

        logger.trace("\n" + content);
        logger.trace("\n\n" + removeOccurrencesFirstOrLastLetter(content, true) );
        logger.trace("\n\n" + removeOccurrencesFirstOrLastLetter(content, false) );
    }
}
