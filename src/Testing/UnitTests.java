package Testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.Dictionary;
import main.Game;

public class UnitTests {
	
	Game newGame;
	Dictionary dictionary;
	
	@Before
	public void setUp() {
		newGame = new Game();
		dictionary = new Dictionary();
		newGame.setLetters("eeeeddoonnnsssrv");
		newGame.setColumns(4);
		try {
			dictionary.setupDictionary(newGame);
		} catch (IOException e) {
			System.out.println("Error creating dictionary for testing.");
		}
		newGame.getValidWords(dictionary, newGame.getLetters());
		newGame.start();
	}
	
	@Test
	public void testResult() {
		assertNotNull(newGame.getValidWordSquare());
		boolean validWordSquare = true;
		int cols = newGame.getColumns();
		List<String> firstWordSquare = newGame.getValidWordSquare().iterator().next();
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < cols; j++) {
				if(firstWordSquare.get(i).charAt(j) != firstWordSquare.get(j).charAt(i)) {
					validWordSquare = false;
				}
			}
		}
		assertTrue(validWordSquare);
	}
}
