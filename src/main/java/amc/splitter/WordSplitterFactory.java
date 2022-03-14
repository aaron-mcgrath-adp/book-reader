package amc.splitter;

public enum WordSplitterFactory {
  Regex {
    @Override
    public WordSplitter createWordSplitter(String config) {
      return new RegexWordSplitter(config);
    }
  },
  
  StringSplit {
    @Override
    public WordSplitter createWordSplitter(String config) {
      return new StringSplitWordSplitter(config);
    }
  },
  
  Tokenizer {
    @Override
    public WordSplitter createWordSplitter(String config) {
      return new TokenizerWordSplitter(config);
    }
  },
  
  Custom {
    @Override
    public WordSplitter createWordSplitter(String config) {
      return new CustomWordSplitter(config);
    }
  };
  
  public abstract WordSplitter createWordSplitter(String config);
}
