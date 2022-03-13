package amc.splitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RegexWordSplitterTest {

  private String testLine = "The quick brown fox jumps over the lazy dog.";
  
  private RegexWordSplitter splitter;
  
  private String regex = "(\\W+)";
  
  @Before
  public void setUp() throws Exception {
    splitter = new RegexWordSplitter(regex);
  }
  
  @Test
  public void testInvalidRegexConstructor() {
    try {
      splitter = new RegexWordSplitter("[");
      fail("Should fail with invalid regex.");
    } catch (Exception ex) {
      // expected
    }
  }
  
  @Test
  public void testSetInvalidRegex() {
    try {
      splitter.setRegex("[");
      fail("Should fail with invalid regex.");
    } catch (Exception ex) {
      // expected
    }
  }
  
  @Test
  public void testRegexExecution() {
    List<String> splitLineIntoWords = splitter.splitLineIntoWords(testLine);
    assertEquals(9, splitLineIntoWords.size());
  }
  
  @Test
  public void testRegexNullInput() {
    try {
      splitter.splitLineIntoWords(null);
      fail("Should fail on null input.");
    } catch (Exception ex) {
      // expected
    }
  }
}
