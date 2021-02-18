import java.util.HashMap;

public class TrieMapNode {
	// Keys are characters
	// Values are other TrieMapNodes
	// This way, you can use the next character in a String to determine the next
	// node
	// This allows you to progress deeper into the tree
	private HashMap<Character, TrieMapNode> children;
	private String value;

	public TrieMapNode() {
		children = new HashMap<Character, TrieMapNode>();
		value = null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String newVal) {
		value = newVal;
	}

	public HashMap<Character, TrieMapNode> getChildren() {
		return children;
	}

	
	// returns the children even if it's absent
	public TrieMapNode getEvenIfAbsent(char c) {
		if (children.get(c) == null) // if no children, create node
			children.put(c, new TrieMapNode());

		return children.get(c);
	}

	public TrieMapNode get(char c) {
		return children.get(c);
	}

	// check if the node has value
	public boolean hasValue() {
		return value != null;
	}
}