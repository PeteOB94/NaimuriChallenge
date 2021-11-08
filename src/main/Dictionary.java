package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
	private static String dictionaryLink = "http://norvig.com/ngrams/enable1.txt";
	Set<String> words;
	
	public void setupDictionary(int columns) throws IOException {
		this.words = new HashSet<String>();
		URL dictionaryURL = new URL(dictionaryLink);
		BufferedReader dictionaryReader = new BufferedReader(new InputStreamReader(dictionaryURL.openStream()));
		
		String dictionaryLine;
		while((dictionaryLine = dictionaryReader.readLine()) != null) {
			if(dictionaryLine.length() == columns) { 
				this.words.add(dictionaryLine);
			}
		}
	}
	
	public Set<String> getWords() {
		return this.words;
	}
}
