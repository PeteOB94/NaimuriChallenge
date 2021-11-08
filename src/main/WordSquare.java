package main;
import java.io.IOException;

public class WordSquare {
	
	public static void main(String[] args) {
		Game currentGame = new Game();
		currentGame.getInput();
		try {
			currentGame.setupDictionary();
		} catch (IOException e) {
			System.out.println("Could not setup dictionary.");
		}
		currentGame.getValidWords(currentGame.dictionary, currentGame.letters);
		currentGame.start();
		if(currentGame.validCombinations.size() > 0) {
			for(String word : currentGame.validCombinations.iterator().next()) {
				System.out.println(word);
			}
		}
	}
}
