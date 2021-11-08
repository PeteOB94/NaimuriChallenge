package main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
	
	int columns = 0;
	List<Character> letters = new ArrayList<Character>();
	Dictionary dictionary = new Dictionary();
	List<String> validWords = new ArrayList<>();
	Set<List<String>> validCombinations = new HashSet<>();
	
	public void getInput() {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Please enter number of columns and the letters:");
		String input = inputScanner.nextLine();
		String[] inputSplit = input.split(" ");
		setColumns(Integer.parseInt(inputSplit[0]));
		setLetters(inputSplit[1]);
		inputScanner.close();
	}

	void setupDictionary() throws IOException {
		this.dictionary.setupDictionary(this);
	}
	
	public void getValidWords(Dictionary dictionary, List<Character> charList) {
		for(String word : dictionary.words) {
			List<Character> currentCharList = new ArrayList<>(charList);
			boolean viable = true;
			for(int i = 0; i < word.length(); i++) {
				if(!currentCharList.contains(word.charAt(i))) {
					viable = false;
				}
				else {
					currentCharList.remove((Object) word.charAt(i));
				}
			}
			if(viable) {
				this.validWords.add(word);
			}
		}
	}
	
	public Set<List<String>> getValidWordSquare() {
		return this.validCombinations;
	}
	
	public void start() {
		PrefixTreeNode root = new PrefixTreeNode();
		for(String word : this.validWords) {
			root = root.addWordsToTree(word, root);
		}
		for(String word : this.validWords) {
			List<String> currentWords = new ArrayList<String>();
			currentWords.add(word);
			this.getWordSquare(1, currentWords, root);
		}
	}
	
	private void getWordSquare(int index, List<String> currentWords, PrefixTreeNode root) {
		if(!(index == this.columns)) {
			String prefix = "";
			for(String word : currentWords) {
				prefix += word.charAt(index);
			}
			
			Set<String> validWords = this.getWords(prefix, root);
			for(String word : validWords) {
				currentWords.add(word);
				this.getWordSquare(index + 1, currentWords, root);
				currentWords.remove(word);
			}
		}
		else {
			this.validCombinations.add(new ArrayList<>(currentWords));
		}
	}
	
	private Set<String> getWords(String prefix, PrefixTreeNode root) {
		PrefixTreeNode currentNode = root;
		for(int i = 0; i < prefix.length(); i++) {
			if(!(currentNode.children.containsKey(prefix.charAt(i)))) {
				return new HashSet<>();
			}
			currentNode = currentNode.children.get(prefix.charAt(i));
		}
		
		return currentNode.words;
	}
	
	public void setLetters(String letters) {
		this.letters = letters.chars().mapToObj(e->(char)e).collect(Collectors.toList());
	}
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public List<Character> getLetters() {
		return this.letters;
	}
	
	public int getColumns() {
		return this.columns;
	}
}
