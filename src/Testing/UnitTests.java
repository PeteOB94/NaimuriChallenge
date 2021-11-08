package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.Dictionary;
import main.Game;
import main.PrefixTreeNode;

public class UnitTests {
	
	Game newGame;
	Dictionary dictionary;
	
	@Test
	public void testColumnInput() {
		newGame.setColumns(1);
		assertEquals(1, newGame.getColumns());
	}
	
	@Test
	public void testLetterInput() {
		newGame.setLetters("a");
		assertEquals(1, newGame.getLetters().size());
	}
	
	@Test
	public void testDictionarySetup() throws IOException {
		dictionary = new Dictionary();
		dictionary.setupDictionary(0);
		assertTrue(dictionary.getWords().size() == 0);
	}
	
	@Test
	public void testDictionarySetupWithWords() throws IOException {
		dictionary = new Dictionary();
		dictionary.setupDictionary(4);
		assertTrue(dictionary.getWords().size() > 0);
	}
	
	@Test
	public void testValidWords() throws IOException {
		newGame = new Game();
		newGame.setLetters("a");
		newGame.setColumns(4);
		dictionary = new Dictionary();
		dictionary.setupDictionary(4);
		newGame.getValidWords(dictionary, newGame.getLetters());
		assertTrue(newGame.validWords.size() == 0);
	}
	
	@Test
	public void testValidWordsNotEmpty() throws IOException {
		newGame = new Game();
		newGame.setLetters("eeeeddoonnnsssrv");
		newGame.setColumns(4);
		dictionary = new Dictionary();
		dictionary.setupDictionary(4);
		newGame.getValidWords(dictionary, newGame.getLetters());
		assertTrue(newGame.validWords.size() > 0);
	}
	
	@Test
	public void testPrefixTreeNodeSetupNoInput() throws IOException {
		PrefixTreeNode node = new PrefixTreeNode();
		node.addWordsToTree("", node);
		assertTrue(node.children.size() == 0);
		assertTrue(node.words.size() == 0);
	}
	
	@Test
	public void testPrefixTreeNodeSetupWithInput() throws IOException {
		PrefixTreeNode node = new PrefixTreeNode();
		node.addWordsToTree("ab", node);
		assertTrue(node.children.size() == 1);
		assertTrue(node.words.size() == 0);
	}
	
	@Before
	public void setUp() {
		newGame = new Game();
		dictionary = new Dictionary();
		newGame.setLetters("eeeeddoonnnsssrv");
		newGame.setColumns(4);
		try {
			dictionary.setupDictionary(newGame.getColumns());
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
