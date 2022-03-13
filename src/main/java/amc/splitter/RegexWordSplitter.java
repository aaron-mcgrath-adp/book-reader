package amc.splitter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexWordSplitter implements WordSplitter {

  private String regex;
  
  private Pattern pattern;
  
  public RegexWordSplitter(String regex) {
    setRegex(regex);
  }
  
  @Override
  public List<String> splitLineIntoWords(String line) {
    List<String> result = new ArrayList<>();
    
    Matcher matcher = getPattern().matcher(line);
    matcher.matches();
    
    while(matcher.find()) {
      result.add(matcher.group(1));
    }
    return result;
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
    setPattern(Pattern.compile(getRegex()));
  }

  public Pattern getPattern() {
    return pattern;
  }

  public void setPattern(Pattern pattern) {
    this.pattern = pattern;
  }

}
