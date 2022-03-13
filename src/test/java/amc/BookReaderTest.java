package amc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Matchers.anyString;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import amc.splitter.WordSplitter;

public class BookReaderTest {

  private String testFilePath = "src/test/resources/test.txt";
  
  private BookReader reader;
  
  @Mock private WordSplitter mockWordSplitter;
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    reader = new BookReader();
  }
  
  @Test
  public void testFileNotFound() {
    try {
      reader.readBook(new File("zyx.zyx"));
      fail("Expected IOE");
    } catch (IOException ex) {
      // expected
    }
  }
  
  @Test
  public void testFileIsDir() {
    try {
      reader.readBook(new File("src/test"));
      fail("Expected IOE");
    } catch (IOException ex) {
      // expected
    }
  }
  
  @Test
  public void testReadsFileReturnsResult() throws Exception {
    assertNotNull(reader.readBook(new File(testFilePath)));
  }
  
  @Test
  public void testReadsFileWithCustomSplitter() throws Exception {
    reader.setWordSplitter(mockWordSplitter);
    
    reader.readBook(new File(testFilePath));
    
    verify(mockWordSplitter, atLeast(1)).splitLineIntoWords(anyString());
  }
  
  
}
