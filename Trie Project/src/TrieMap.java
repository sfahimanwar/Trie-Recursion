//Note: All of your TrieMapInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TrieMapInterface
import java.util.ArrayList;
public class TrieMap implements TrieMapInterface {
	TrieMapNode root = new TrieMapNode();

	public TrieMap() {

	}

	// Indirectly recursive method to meet definition of interface
	public void put(String key, String value) {
		put(root, key, value); // call recursive helper
	}

	// Recursive method
	// Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
	public void put(TrieMapNode current, String curKey, String value) {
		if (curKey.isEmpty()) // if end reached
			current.setValue(value); // set value
		else {
			// get next child and call recursively
			char next = curKey.charAt(0);
			put(current.getEvenIfAbsent(next), curKey.substring(1), value);
		}
	}

	// Indirectly recursive method to meet definition of interface
	public String get(String key) {
		TrieMapNode node = findNode(root, key); // find the node with key
		return node == null ? null : node.getValue(); // return value if found
	}

	// Indirectly recursive method to meet definition of interface
	public boolean containsKey(String key) {
		TrieMapNode node = findNode(root, key); // find the node with key
		return node != null && node.hasValue(); // returns true if the node has a value
	}

	// Indirectly recursive method to meet definition of interface
	public ArrayList<String> getKeysForPrefix(String prefix) {
		TrieMapNode startNode = findNode(root, prefix); // move to node with prefix
		ArrayList<String> keys = new ArrayList<>(); // create list to store keys
		getSubtreeKeys(startNode, prefix, keys); // get keys in subtree
		return keys;
	}

	// Recursive helper function to find node that matches a prefix
	// Note: only a suggestion, you may solve the problem is any recursive manner
	public TrieMapNode findNode(TrieMapNode current, String curKey) {
		if (current == null) // if reached past tree
			return null;
		else if (curKey.isEmpty()) // reached destination
			return current;
		else {
			// go to next child
			char next = curKey.charAt(0);
			return findNode(current.get(next), curKey.substring(1));
		}

	}

	// Recursive helper function to get all keys in a node's subtree
	// Note: only a suggestion, you may solve the problem is any recursive manner
	public void getSubtreeKeys(TrieMapNode current, String currPrefix, ArrayList<String> keys) {
		if(current == null) // if no start node
			return;
		
		if(current.hasValue()) // if has value
			keys.add(currPrefix);
		
		// get keys in all children
		for (Character c : current.getChildren().keySet()) {
			getSubtreeKeys(current.get(c), currPrefix + c, keys);
		}
	}

	// Indirectly recursive method to meet definition of interface
	public void print() {
		print(root);
	}

	// Recursive method to print values in tree
	public void print(TrieMapNode current) {
		if (current == null)
			return;
		
		// if has value
		if (current.hasValue())
			System.out.println(current.getValue());
		
		// print all children
		for (TrieMapNode child : current.getChildren().values()) {
			print(child);
		}

	}

	public static void main(String[] args) {
		// create map
		String[] words = {"ATE", "A", "AT", "ARE", "BE", "BET", "BETTER", "SIT", "SO", "SOD", "SODA"};
		TrieMap map = new TrieMap();
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], words[i]);
		}
		
		// print map
		map.print();
		
		// test get
		System.out.println(map.get("ARE"));
		System.out.println(map.get("BET"));
		System.out.println(map.get("BETTER"));
		
		// tests if it contains the key
		System.out.println(map.containsKey("BET"));
		System.out.println(map.containsKey("EAT"));
		System.out.println(map.containsKey("BETTERMENT"));
		
		// test prefix
		System.out.println(map.getKeysForPrefix("A"));
		System.out.println(map.getKeysForPrefix("S"));
		System.out.println(map.getKeysForPrefix("SO"));
		System.out.println(map.getKeysForPrefix("BET"));
		System.out.println(map.getKeysForPrefix("T"));
	}
}