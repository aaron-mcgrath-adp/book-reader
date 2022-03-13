package amc.splitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StringSplitWordSplitterTest {

  private String testLine = "The quick brown fox jumps over the lazy dog.";
  
  private StringSplitWordSplitter splitter;
  
  private String split = "\\W+";
  
  @Before
  public void setUp() throws Exception {
    splitter = new StringSplitWordSplitter(split);
  }
  
  @Test
  public void testSplitterExecution() {
    List<String> splitLineIntoWords = splitter.splitLineIntoWords(testLine);
    assertEquals(9, splitLineIntoWords.size());
  }
  
  @Test
  public void testSplitterNullInput() {
    try {
      splitter.splitLineIntoWords(null);
      fail("Should fail on null input.");
    } catch (Exception ex) {
      // expected
    }
  }
  
  @Test
  public void testSplitterNullSplit() {
    try {
      splitter = new StringSplitWordSplitter(null);
      splitter.splitLineIntoWords(testLine);
      fail("Should fail on null input.");
    } catch (Exception ex) {
      // expected
    }
  }
}
