package amc.splitter;

import java.util.List;
import java.util.stream.Collectors;

public class CustomWordSplitter implements WordSplitter {

  private TokenizerWordSplitter firstSplitter;
    
  private String token = " ";
  
  private String punctuation = ".,!?:;()[]{}";
    
  public CustomWordSplitter(String token) {
    setToken(token);
  }
  
  @Override
  public List<String> splitLineIntoWords(String line) {
    setFirstSplitter(new TokenizerWordSplitter(getToken()));
    
    return getFirstSplitter().splitLineIntoWords(line)
        .stream()
        .map(token -> cleanMe(token))
        .filter(token -> token.length() > 0)
        .collect(Collectors.toList());
  }

  // Remove any trailing punctuation and quotes from anywhere.
  private String cleanMe(String word) {
    if(word.length() > 1) {
      // If the last character is punctuation, then lets remove it and recursively call the clean routine again.
      String lastCharacter = word.substring(word.length() - 1);
      if(punctuation.indexOf(lastCharacter) >= 0) {
        word = word.substring(0, word.length() - 1);
        cleanMe(word);
      } else if (punctuation.indexOf(word.substring(0, 1)) >= 0) { // first character?
        word = word.substring(1);
        cleanMe(word);
      }
    }
      
    return word.replace("\"", "").replace("'", "");
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public TokenizerWordSplitter getFirstSplitter() {
    return firstSplitter;
  }

  public void setFirstSplitter(TokenizerWordSplitter firstSplitter) {
    this.firstSplitter = firstSplitter;
  }

  
  
}
