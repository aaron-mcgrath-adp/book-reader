package amc.splitter;

import java.util.Arrays;
import java.util.List;

public class StringSplitWordSplitter implements WordSplitter {

  private String regex;
  
  public StringSplitWordSplitter(String regex) {
    setRegex(regex);
  }
  
  @Override
  public List<String> splitLineIntoWords(String line) {
    return Arrays.asList(line.split(getRegex()));
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

}
