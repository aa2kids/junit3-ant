package andrew.assignment3.ant;

public class BinaryTreeApp {

	Node root;

	public BinaryTreeApp() {
		root = null;
	}

	public BinaryTreeApp(String initialString) {
		root = null;
		populateTree(initialString);
	}

	public void populateTree(String initializer) {
		String[] tokens = initializer.split("\\s*,\\s*");
		for (String token : tokens) {
			add(token);
		}
	}

	public Node add(String value) {
		if (root == null) {
			root = new Node(value);
			return root;
		} else
			return root.add(value);
	}

	public String inOrder(Node node){
		String result = "";
		if (node != null) {
			result += inOrder(node.left);
			result += node.value + " ";
			result += inOrder(node.right);
		}
		return result;
	}

	public String frontToBack(Node node, String value) {
		String result = "";
		if (node != null) {
			if (value.compareToIgnoreCase(node.value) > 0) {
				result += frontToBack(node.right, value);
				result += node.value + " ";
				result += frontToBack(node.left, value);
			}
			else if (value.compareToIgnoreCase(node.value) < 0) {
				result += frontToBack(node.left, value);
				result += node.value + " ";
				result += frontToBack(node.right, value);
			} else {
				result += frontToBack(node.left, value);
				result += frontToBack(node.right, value);
			}
		}
		return result;
	}

	public Node delSubTreesandValue(Node node) {
		if (node != null) {
			delSubTreesandValue(node.left);
			delSubTreesandValue(node.right);
			node.value = null;
			node.left = null;
			node.right = null;
		}
		return node;
	}

	public Node delBTree() {
		if (root != null) {
			delSubTreesandValue(root);
			root = null;
		}
		return root;
	}

	public int countNode(Node node) {
		int nodeCount = 0;
		if (node != null) {
			nodeCount += countNode(node.left);
			if (node.left != null || node.right != null)
				nodeCount++;
			nodeCount += countNode(node.right);
		}
	    return nodeCount;
	}

	public int countLeaf(Node node) {
		int leafCount = 0;
		if (node != null) {
			leafCount += countLeaf(node.left);
			if (node.left == null && node.right == null)
				leafCount++;
			leafCount += countLeaf(node.right);
		}
	    return leafCount;
	}

	public void traverseTree() {
		System.out.println(" Traversing tree in ascending order");
		System.out.println("====================================");
		System.out.println(inOrder(root));
	}

	public void printFrontToBack(String value) {
		System.out.println("Traversing forward and backward around value: " + value);
		System.out.println("==================================");
		System.out.println(frontToBack(root,value));
	}

}
