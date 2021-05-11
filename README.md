# java-training-2021       
##### Victor Martinez

### General Instructions

1. Plan to spend about 2 hours completing this exercise.
2. Please document any assumptions made.
3. This is a real world exercise. You may use libraries and frameworks as you
   see fit. Solutions found by Google Search or on Stack Overflow are
   acceptable – as long as you understand them.
4. You do not need to connect to a real, online dictionary. The idea is to
   create a mock object to simulate this.
5. Please send your solution as a zip file or send a link to your private
   GitHub repository, so that we can evaluate your work.

### The Problem

Using Java, find all of the English words in a given String. For example, if
you are given the word WORKING, you can easily find WORK and KING, but ROW,
RING and KNOW are also in there. You have access to a utility class called
Dictionary, which has one method, isEnglishWord(String word).
Dictionary.isEnglishWord(String word) connects to a (mocked) online dictionary
and returns Boolean true if the String passed to it is an English word, return
false otherwise.

## English Dictionary

### Requirements 
- A Dictionary Class  with method named isEnglishWord(String word)
- A mocked online Dictionary 


### General Assumptions
- The mocking online Dictionary is consumed through a DictionaryImpl interface: The Implementation of this mocked dictionary is known as an EnglishDictionary.

- In this context an EnglishDictionary is a Set of valid English Words stored in the form of a Collection of Strings.

- English Dictionary is reources-limited as it does not contain all the Existent English words. More concisely,
  the English Dictionary stores substrings derived from the "Working" string. Currently, manages up to 12 words.
  More importantly, English Dictionary preserves the ordering of the String, so a chunked version of the combinations will be implemented. 

- As stated above, EnglishDictionary is a set of words. Words inside the set belongs to English language. Otherwise, it is assumed it is not
  an english word.

- UpperCase String is the protocol assumed for storing the Strings in the dictionary, which is represented in this
  context as a set of Strings. All of the Strings are converted to UpperCase before validating its existence inside the EnglishDictionary.

- It is assumed that the longest english word for this exercise is up to 22 characters in length. A longer string will cause distinct
  system failures as delay, irregular behaviors and out of the memory exception.

### Project Structure
The Project follows a General Java project scheme where two big sections can be highlighted as well: Main and Test section.
* A DictionaryImpl interface dictates the necessary methods for a Dictionary instance
  
* A BaseTest defines common adjustments applied before each Test suite

<img src="repo.images/ProjectStructure.PNG" alt="Project Structure" height="600"/>

* POM is declared to keep on track of the required dependencies : TestNG and Log4j.

* TestNG Annotations are implemented for setting a correct test flow, besides of validating some common exceptions shown in some Tests

* Two types of DataProviders were included : <strong>SearchPattern</strong> & <strong>ValidationData</strong>. 

* Persistence Layer : The function isEnglishWord() made use of a mocked DataBase in charge of
storing the required dictionary. As many dictionaries could be included, not just an English one, 
  an implementation named BaseDictionary dictates the common methods any dictionary should display.
  
* Logger instances track each event during the Test Suite execution. These logs were stored
in a filed name propertieslogs.txt and shown in console, accordingly. 
  
* <strong>Dictionary Class</strong> only verifies if certain word exists against a limited and customized dictionary (i.e. English Dictionary).

* Multiple exceptions were included for limiting the forbidden states : EmptyInputParameterException,
IncorrectInputParameter and OutOFMemoryException. 

### SubString Engine
The task for obtaining String subsets based on an *Original String* was attacked following a Divide & Conquer 
approach : All the different combinations were obtaining inferring a tree scheme. This approach is contained
in the String Manager and saved us Memory resources as we avoid the O(n) = n^2 and implemented a n*log(n).

<img src="repo.images/divide_and_conquer.jpg" alt="Divide & Conquer" height="400"/>

This specific method follows a divide and conquer approach. Initially a List is split in half, returning two subLists: the left subList and the right subList.
The left subList is split in half, producing two new subList. This process is repeated up to achieve a unique char level.  The process is repeated by the right subList. 
The process will be stable when all of the possible characters are isolated one from each other. Finally, a combination process is executed over these items to produce 
unique subSets of Strings. 

      *  Arr = {A,B,C}, Result_Before_Combination-> {{A},{B}, {C}}, Result_After_Combination = {A,B,C,AB,AC,BC,ABC}

The combination takes into account the order as the assumption made at this point consist in producing subStrings following the String order.



  


