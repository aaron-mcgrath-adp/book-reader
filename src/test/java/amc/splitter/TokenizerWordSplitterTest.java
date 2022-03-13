package amc.splitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TokenizerWordSplitterTest {

  private String testLine = "The quick brown fox jumps over the lazy dog.";
  
  private TokenizerWordSplitter splitter;
  
  private String tokens = ",. ";
  
  @Before
  public void setUp() throws Exception {
    splitter = new TokenizerWordSplitter(tokens);
  }
  
  @Test
  public void testTokenizerExecution() {
    List<String> splitLineIntoWords = splitter.splitLineIntoWords(testLine);
    assertEquals(9, splitLineIntoWords.size());
  }
  
  @Test
  public void testTokenizerNullInput() {
    try {
      splitter.splitLineIntoWords(null);
      fail("Should fail on null input.");
    } catch (Exception ex) {
      // expected
    }
  }
}
