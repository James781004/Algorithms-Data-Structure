package LinkedList;

public class LinkedList<T extends Comparable<T>> implements List<T> {

	private Node<T> root;
	private int sizeCounter;

	// Two pointers: fast go 2 and slow go 1
	public Node<T> getMiddleNode() {
		Node<T> fastPointer = this.root;
		Node<T> slowPointer = this.root;

		// when fast pointer reaches the endint node,
		// slow pointer would be at the middle node
		while (fastPointer.getNextNode() != null && fastPointer.getNextNode().getNextNode() != null) {
			fastPointer = fastPointer.getNextNode().getNextNode();
			slowPointer = slowPointer.getNextNode();
		}
		return slowPointer;
	}

	public void reverse() {
		Node<T> currentNode = this.root;
		Node<T> previousNode = null; // point to the new null node for further operation
		Node<T> nextNode = null; // point to the new null node for further operation

		while (currentNode != null) { // when currentNode points to a null node, it would be the end
			nextNode = currentNode.getNextNode(); // nextNode points to the next of currentNode
			currentNode.setNextNode(previousNode); // currentNode links to the previousNode
			previousNode = currentNode; // previousNode points to currentNode
			currentNode = nextNode; // currentNode points to nextNode for future loops
		}

		this.root = previousNode; // reset the head
	}

	@Override
	public void insert(T data) {
		++this.sizeCounter;

		if (root == null) {
			root = new Node<>(data);
		} else {
			insertDataBeginning(data);
		}
	}

	@Override
	public void remove(T data) {

		if (this.root == null) {
			return;
		}

		--this.sizeCounter;

		if (this.root.getData().compareTo(data) == 0) {
			this.root = this.root.getNextNode();
		} else {
			remove(data, root, root.getNextNode());
		}
	}

	@Override
	public void traverseList() {

		if (this.root == null) {
			return;
		}

		Node<T> node = this.root;

		while (node != null) {
			System.out.print(node + " ");
			node = node.getNextNode();
		}
	}

	// O(1) constant time complexity, update the references
	private void insertDataBeginning(T data) {

		Node<T> newNode = new Node<>(data);
		newNode.setNextNode(root);
		this.root = newNode;
	}

	// O(N) inserting at the end
	private void insertDataEnd(T data, Node<T> node) {

		if (node.getNextNode() != null) {
			insertDataEnd(data, node.getNextNode());
		} else {
			Node<T> newNode = new Node<>(data);
			node.setNextNode(newNode);
		}
	}

	private void remove(T dataToRemove, Node<T> previousNode, Node<T> actualNode) {

		while (actualNode != null) {

			if (actualNode.getData().compareTo(dataToRemove) == 0) {
				previousNode.setNextNode(actualNode.getNextNode());
				actualNode = null;
				return;
			}

			previousNode = actualNode;
			actualNode = actualNode.getNextNode();
		}
	}

	@Override
	public int size() {
		return this.sizeCounter;
	}
}
