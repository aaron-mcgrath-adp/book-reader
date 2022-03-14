# book-reader
A sample maven project to give word stats on a plain text file. 

## Pre-requisites

This project has been created with core Java 1.8 and JavaFX.

Therefore, we'll be needing a JVM with the JavaFX add-on.  In later installations of Java we could simply add the maven dependency for JavaFX and we're good!  But for Java 8, some installs came pre-packed with JavaFX and others, especially the open JDK flavours did not.

I've been using the Azul zulu builds for a few years without issue, so here are a couple of links to builds that include the JavaFX component.

[Windows Azul Zulu 1.8 u322 b06 FX](https://cdn.azul.com/zulu/bin/zulu8.60.0.21-ca-fx-jdk8.0.322-win_x64.zip)

[MacOS Azul Zulu 1.8 u322 b06 FX](https://cdn.azul.com/zulu/bin/zulu8.60.0.21-ca-fx-jdk8.0.322-macosx_x64.zip)

## To get started:

Being a maven project we'll need to perform the standard maven magic at the root of the project directory to compile, test and create our final artefact.

You may need to set your JAVA_HOME to the new FX build installed above.
````
> mvn clean package
````

Now we can simply execute the resulting jar file like so;

````
> java -jar target\book-reader-0.0.1-SNAPSHOT.jar
````
![Image](image/book-reader.png)

## Make it work

Simply type or copy/paste your file location into the file path field, then simply choose your method of splitting words.

The program works by reading a file line, by line.  Each line is then split into "words", for which basic statistics are built up. 
We have 4 methods that will take a line of text and strip the "words" from each line.

- Regex
- String Split()
- String Tokenizer
- Custom

Once selected and configured with the regex/tokens, simply hit the __Scan Book__ button.  Most of the available stats are shown on screen, but a further output dump can now be seen on the command line;
````
Word Count 9
Words with 1 letters: 1
Words with 2 letters: 1
Words with 3 letters: 1
Words with 4 letters: 2
Words with 5 letters: 2
Words with 7 letters: 1
Words with 10 letters: 1
Average word length 4.556
The most frequently occurring word length is 2, for word lengths of 5 & 4
````

### Regex

This method utilises the __java.util.regex.Matcher__ and __java.util.regex.Pattern__ classes.
When you choose the Regex implementation, simply type your expression into the Regex/Tokens field, such as:
````
([A-Za-z0-9\/\&-]+)
````

__Note:__ This implementation will consider the actual word in the first group of any match.  For example, this expression will not work so well;
````
\W+
````
Why?  because we have not set a capture group.  Change it to this and voila;
````
(\W+)
````

### Split()

This method uses the simpler __String.split()__ method.  So here you can simply type a valid string expression or basic regular expression such as;
````
\W+
````

### Tokenizer

The tokenizer method will take a list of single character delimiters for which it will use as the splitting tokens.  Simply type a list of commonly used word delimiters such as;
````
., !?
````

### Custom

The custom implementation will use a tokenizer splitter first, then attempt to clean the returned words, stripping them of any punctuation.
Simply passing in a "space" as the token will get us some way there to clean spit words, but there are still some edge cases, such as hyphenated words or even web addresses.

## A few notes on usage

Regex Java 1.8 vs Java 11.

The strangest thing!  If you run the following regex implementation;
````
([A-Za-z0-9\/\&-]+)
````
Using Java 8, then it ignores the first word/character in the document.  Switch to Java 11 and voila!  Everything is fine again.  A bug?  A known update?

Longer books can take a short while to process.  With a little more time, the GUI would give you some feedback that it is actually doing something, but right now, it does not, just be patient.

The basic regular expression shown;
````
([A-Za-z0-9\/\&-]+)
````
Will catch most words including "&", but do you also consider "=" to be a word?  What about currency such as "£1.99".

I assumed a tailored regular expression would do the job and allow for easier maintenance when new edge cases are found.  However, I've just seen a few sample regular expressions for validating formatted currency;
````
// Requires a decimal and commas ^\$?(([1-9]\d{0,2}(,\d{3})*)|0)?\.\d{1,2}$ 
// Allows a decimal, requires commas (?=.*\d)^\$?(([1-9]\d{0,2}(,\d{3})*)|0)?(\.\d{1,2})?$ 
// Decimal and commas optional (?=.*?\d)^\$?(([1-9]\d{0,2}(,\d{3})*)|\d+)?(\.\d{1,2})?$ 
// Decimals required, commas optional ^\$?(([1-9]\d{0,2}(,\d{3})*)|0)?\.\d{1,2}$
````
And have since changed my mind, perhaps a pure Java custom implementation might be better.