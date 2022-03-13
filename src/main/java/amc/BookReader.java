package amc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import amc.splitter.TokenizerWordSplitter;
import amc.splitter.WordSplitter;

public class BookReader {
  
  private WordSplitter wordSplitter;
    
  public BookReader() {
    setWordSplitter(new TokenizerWordSplitter(",. "));
  }
  
  public WordStatistics readBook(File bookFile) throws IOException {
    WordStatistics result = new WordStatistics();
    
    if(bookFile.exists() && bookFile.isFile()) {
      FileReader fileReader = new FileReader(bookFile);
      BufferedReader bufferedReader = null;
      try {
        bufferedReader = new BufferedReader(fileReader);
        
        String line;
        while((line = bufferedReader.readLine()) != null) {
          List<String> splits = getWordSplitter().splitLineIntoWords(line);
          
          for(String word: splits) {
            System.out.println("Adding word: " + word);
            result.addWord(word);
          }
        }
      } finally {
        bufferedReader.close();
      }
      
    } else {
      throw new FileNotFoundException(bookFile.getAbsolutePath() + " does not exist.");
    }
    
    return result;
  }

  public WordSplitter getWordSplitter() {
    return wordSplitter;
  }

  public void setWordSplitter(WordSplitter wordSplitter) {
    this.wordSplitter = wordSplitter;
  }
}
