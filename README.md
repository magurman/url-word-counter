The program takes two arguments: a filepath to urls.tx and a filepath to words.txt, contianing the urls and words to count, respectively.

I defined three custom objects: Word, URLValidator, WordCounter
	Word: 
		store the string representation of a word and the count of that word in each URL and globally
	URLValidator:
		URL is validated and contains a method to parse URL and return an array of Strings -- the words in the url html body
	WordCounter:
		An instance is created for each URL and contains the word list. This is where the program iterates through the url and counts the words.

the program parses the urls and words, creates the word counter object for each url, and counts each word for each url.


Run the program by executing the command: java -jar url-word-counter.jar urls.txt words.txt


Open Source Libraries Used:
	jsoup for parsing the urls

Assumptions:
	urls.txt and words.txt are delimited by \n
	counting is case insensitive
	  
