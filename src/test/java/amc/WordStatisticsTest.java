package amc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WordStatisticsTest {

  private WordStatistics stats;
  
  @Before
  public void setUp() throws Exception {
    stats = new WordStatistics();
  }
  
  @Test
  public void testWordAdded() {
    stats.addWord("word");
    
    assertTrue(stats.getWordMap().containsKey(4));
    assertTrue(stats.getWordMap().get(4) == 1);
    assertEquals(stats.getWordCount(), 1);
    assertEquals(stats.getCharacterCount(), 4);
  }
  
  @Test
  public void testWordStatsAverageLength() {
    stats.addWord("word");
    stats.addWord("drow");
    
    assertTrue(stats.calculateAverageWordLength() == 4);
    
    stats.addWord("a");
    assertTrue(stats.calculateAverageWordLength() < 4);
    
    stats.addWord("a very long word.");
    assertTrue(stats.calculateAverageWordLength() > 4);
  }
  
  @Test
  public void testWordStatsAverageLengthDivideByZero() {
    assertTrue(stats.calculateAverageWordLength() == 0);
  }
  
  @Test
  public void testWordStatsFrequency() {
    stats.addWord("word");
    stats.addWord("drow");
    
    assertEquals(stats.getPopularWordLength().wordLength, 4);
    assertEquals(stats.getPopularWordLength().wordCount, 2);
  }
  
  @Test
  public void testWordStatsFrequencyOneWinner() {
    stats.addWord("word");
    stats.addWord("drow");
    stats.addWord("words");
    
    assertEquals(stats.getPopularWordLength().wordLength, 4);
    assertEquals(stats.getPopularWordLength().wordCount, 2);
    assertTrue(stats.getPopularWordLength().getAll().contains(4));
    assertFalse(stats.getPopularWordLength().getAll().contains(5));
  }
  
  @Test
  public void testWordStatsFrequencyMultiWinner() {
    stats.addWord("word");
    stats.addWord("drow");
    stats.addWord("words");
    stats.addWord("drows");
    
    assertEquals(stats.getPopularWordLength().wordLength, 4);
    assertEquals(stats.getPopularWordLength().wordCount, 2);
    assertTrue(stats.getPopularWordLength().getAll().contains(4));
    assertTrue(stats.getPopularWordLength().getAll().contains(5));
  }
  
  
  
}
