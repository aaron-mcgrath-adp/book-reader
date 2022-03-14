package amc.splitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CustomWordSplitterTest {

private String testLine = "£1.99 1,000,000 13/03/2020 13-03-2020.";
  
  private CustomWordSplitter splitter;
  
  private String token = " ";
  
  @Before
  public void setUp() throws Exception {
    splitter = new CustomWordSplitter(token);
  }
  
  @Test
  public void testCustomExecution() {
    List<String> splitLineIntoWords = splitter.splitLineIntoWords(testLine);
    assertEquals(4, splitLineIntoWords.size());
  }
  
  @Test
  public void testCustomNullInput() {
    try {
      splitter.splitLineIntoWords(null);
      fail("Should fail on null input.");
    } catch (Exception ex) {
      // expected
    }
  }
}
