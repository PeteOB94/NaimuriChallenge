package main;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PrefixTreeNode {
	Map<Character, PrefixTreeNode> children;
	Set<String> words;
	
	public PrefixTreeNode() {
		this.children = new HashMap<Character, PrefixTreeNode>();
		this.words = new TreeSet<String>();
	}
	
	public PrefixTreeNode addWordsToTree(String word, PrefixTreeNode currentNode) {
		PrefixTreeNode root = currentNode;
		for(int i = 0; i < word.length(); i++) {
			Character currentChar = word.charAt(i);
			if(!currentNode.children.containsKey(currentChar)) {
				currentNode.children.put(currentChar, new PrefixTreeNode());
			}
			currentNode = currentNode.children.get(currentChar);
			currentNode.words.add(word);
		}
		return root;
	}
}
