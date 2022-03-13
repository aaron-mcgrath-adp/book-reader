package amc.splitter;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class TokenizerWordSplitter implements WordSplitter {

  private String delimeters;
  
  public TokenizerWordSplitter(String delimeters) {
    setDelimeters(delimeters);
  }
  @Override
  public List<String> splitLineIntoWords(String line) {
    return Collections.list(new StringTokenizer(line, delimeters))
        .stream()
        .map(token -> (String) token)
        .collect(Collectors.toList());
  }

  public String getDelimeters() {
    return delimeters;
  }

  public void setDelimeters(String delimeters) {
    this.delimeters = delimeters;
  }

}
