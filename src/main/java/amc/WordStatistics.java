package amc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import amc.util.NumberUtil;

public class WordStatistics {
  
  private Map<Integer, Integer> wordMap;
  
  private int wordCount;
  
  private int characterCount;
  
  private PopularWordLength popularWordLength;
  
  public WordStatistics() {
    setWordMap(new HashMap<Integer, Integer>());
    setPopularWordLength(new PopularWordLength());
  }

  public void addWord(String word) {
    addWord(word.length());
  }
  
  public void addWord(int letterCount) {
    if(wordMap.containsKey(letterCount)) {
      wordMap.put(letterCount, wordMap.get(letterCount) + 1);
    } else {
      wordMap.put(letterCount, 1);
    }
    
    wordCount ++;
    characterCount += letterCount;
    if(wordMap.get(letterCount) > popularWordLength.wordCount) {
      popularWordLength.clear();
      popularWordLength.add(letterCount);
      popularWordLength.wordLength = letterCount;
      popularWordLength.wordCount = wordMap.get(letterCount); 
    } else if(wordMap.get(letterCount) == popularWordLength.wordCount) {
      popularWordLength.add(letterCount);
    }
  }
  
  public double calculateAverageWordLength() {
    if(getWordCount() == 0)
      return 0;
    return NumberUtil.round((double) getCharacterCount() / (double)getWordCount(), 3);
  }
  
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("\n\nWord Count " + getWordCount());
    sb.append("\n");
    getWordMap().keySet().forEach( key -> {
      sb.append("Words with " + key + " letters: ");
      sb.append(getWordMap().get(key));
      sb.append("\n");
    });
    sb.append("Average word length " + calculateAverageWordLength());
    sb.append("\n");
    sb.append("The most frequently occurring word length is " + getPopularWordLength().wordCount);
    sb.append(", for word lengths of");
    
    for(int index = 0; index < getPopularWordLength().getAll().size(); index ++) {
      if(index > 0)
        sb.append(" &");
      sb.append(" " + getPopularWordLength().getAll().get(index));
    }
    
    return sb.toString();
  }
  
  public Map<Integer, Integer> getWordMap() {
    return wordMap;
  }

  public void setWordMap(Map<Integer, Integer> wordMap) {
    this.wordMap = wordMap;
  }

  public int getWordCount() {
    return wordCount;
  }

  public void setWordCount(int wordCount) {
    this.wordCount = wordCount;
  }

  public int getCharacterCount() {
    return characterCount;
  }

  public void setCharacterCount(int totalCharacters) {
    this.characterCount = totalCharacters;
  }
  
  public PopularWordLength getPopularWordLength() {
    return popularWordLength;
  }

  public void setPopularWordLength(PopularWordLength popularWordLength) {
    this.popularWordLength = popularWordLength;
  }

  class PopularWordLength {
    List<Integer> wordLengths = new ArrayList<>();
    int wordCount;
    int wordLength;
    
    void clear() {
      wordLengths.clear();
    }
    
    void add(int wordLength) {
      wordLengths.add(wordLength);
    }
    
    public List<Integer> getAll() {
      return wordLengths;
    }
    
  }
}
