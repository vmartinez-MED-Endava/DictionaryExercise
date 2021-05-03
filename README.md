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
### Project Structure
The project is organized following the overall constitution of a POM (Page Object Model)
simulating the most common behaviors, methods and attributes of this design pattern:
* A BasePage dictates the most common methods all pages should have as a minimum.
During this exercise no other pages were required, so the scheme of the parent class
  was defined as abstract, bearing in mind the necessary methods to accomplish the 
  required task.
* A BaseTest functions as a wrapper for complementing the customized behaviors under Test.

<img src="repo.images/ProjectStructure.PNG" alt="Project Structure" height="600"/>

* POM is declared to keep on track of the required dependencies : TestNG and Log4j.

* Two types of DataProviders were included : <strong>SearchPattern</strong> & <strong>ValidationData</strong>. 

* Persistence Layer : The function isEnglishWord() made use of a mocked DataBase in charge of
storing the required dictionary. As many dictionaries could be included, not just an English one, 
  an implementation named BaseDictionary dictates the common methods any dictionary should be displayed.
  
* A Logger class keep track on each event ocurred during the Test Suite execution. These logs were stored
in a filed name dictionary_logs.txt and shown in console, accordingly. 
  
* <strong>Dictionary Class</strong> only verifies if certain word exists in a customized dictionary (i.e. English Dictionary).
The String processing is carried out by the String Manager.

* Multiple exceptions were included for limiting the forbidden states : EmptyInputParameterException,
IncorrectInputParameter and OutOFMemoryException. 

### SubString Engine
The task for obtaining String subsets based on an *Original String* was attacked following a Divide & Conquer 
approach : All the different combinations were obtaining inferring a tree scheme. This approach is contained
in the String Manager and saved us Memory resources as we avoid the O(n) = n^2 and implemented a n*log(n).

<img src="repo.images/divide_and_conquer.jpg" alt="Divide & Conquer" height="400"/>



  


