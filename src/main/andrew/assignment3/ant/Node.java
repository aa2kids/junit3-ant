package andrew.assignment3.ant;

public class Node {

	Node left;
	Node right;
	String value;

	public Node(String value) {
		this.value = value;
		left = null;
		right = null;
	}

	public int compareNode(String value) {
		return value.compareToIgnoreCase(this.value);
	}

	public Node add(String value) {
		int result = compareNode(value);
		if (result == 0)
            return null;
		if (result < 0) {
            if (left == null) {
                  left = new Node(value);
                  return left;
            } else
                  return left.add(value);
        } else {
            if (right == null) {
                  right = new Node(value);
                  return right;
            } else
                  return right.add(value);
        }
	}


}
