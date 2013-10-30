package andrew.assignment3.ant;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.TestResult;

public class BinaryTreeTestCase extends TestCase {

	BinaryTreeApp TestTree;
	String TestString;

	public BinaryTreeTestCase(String name) {
		super(name);
	}

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	protected void setUp() throws Exception {
		super.setUp();
		TestString = ("John, Sally, Andy, Karen, William, Harry, Ben");
		TestTree = new BinaryTreeApp();

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		TestTree.delBTree();
		TestString = null;
	}


	public final void testBinaryTreeApp() {
		assertNotNull(TestTree); //previous setUp call should've created TestTree
		assertNull(TestTree.root); //previous setUp call should have root = null
	}

	public final void testBinaryTreeAppString() {
		TestTree = null; //unset TestTree obj
		assertNull(TestTree);
		TestTree = new BinaryTreeApp("one,two,three"); //create TestTree w/ String
		assertNotNull(TestTree.root); //root should now not be null
		assertNotNull(TestTree.root.right); //right child should populate
		assertNull(TestTree.root.left); //left child should be null
		assertEquals("one", TestTree.root.value); //check value of root string
	}

	public final void testPopulateTree_rootNull() {
		TestTree.populateTree("c , a, d,b,e");
		assertNotNull(TestTree.root); //root should not be null
		assertEquals("c", TestTree.root.value); //check value of root string
		assertEquals("a", TestTree.root.left.value); //check value of root left child
		assertEquals("d", TestTree.root.right.value); //check value of root right child
	}

	public final void testPopulateTree_rootNotNull() {
		assertNull(TestTree.root); //root should be null
		TestTree.add("c");
		assertNotNull(TestTree.root); //root should not be null
		assertEquals("c", TestTree.root.value); //check value of root string
		TestTree.populateTree("a , d, b,e");
		assertEquals("a", TestTree.root.left.value); //check value of root left child
		assertEquals("d", TestTree.root.right.value); //check value of root right child
	}

	public final void testAdd_rootNull() {
		assertNull(TestTree.root); //root should be null
		assertNotNull(TestTree.add("c")); //object created
		assertNotNull(TestTree.root); //root should not be null
		assertEquals("c", TestTree.root.value); //check value of root string
	}

	public final void testAdd_rootNotNull() {
		TestTree.add("c"); //object created as root
		assertNotNull(TestTree.root); //root should not be null
		Node TempObj = TestTree.add("a");
		assertNotNull(TempObj); //Node object created
		assertNotSame(TempObj, TestTree.root); //Node object not root
		assertEquals("c", TestTree.root.value); //root value is "c"
		assertEquals("a", TempObj.value); //Node object value is "a"

		TempObj = null; //set var ref to null for garbage collection
	}

	public final void testAdd_duplicate_value() { //should not add the duplicate
		TestTree.populateTree("c, a, b, f, e, z"); //populated Tree
		assertEquals(3, TestTree.countNode(TestTree.root)); //this tree has 3 nodes
		assertEquals(3, TestTree.countLeaf(TestTree.root)); // this tree has 3 leafs
		assertNull(TestTree.add("e")); //attempt add duplicate string value to tree
		/* number nodes and leafs should be unchanged so following asserts should be same */
		assertEquals(3, TestTree.countNode(TestTree.root)); //this tree has 3 nodes
		assertEquals(3, TestTree.countLeaf(TestTree.root)); // this tree has 3 leafs
	}

	public final void testInOrder() {
		TestTree.populateTree(TestString);
		assertEquals("Andy Ben Harry John Karen Sally William ",
				TestTree.inOrder(TestTree.root)); //InOrder traverse from root
	}

	public final void testFrontToBack() {
		TestTree.populateTree(TestString);
		assertEquals("Karen Sally William John Harry Ben Andy ",
				TestTree.frontToBack(TestTree.root, "Kim"));
	}

	public final void testCountNode() {
		TestTree.populateTree(TestString);
		assertEquals(4, TestTree.countNode(TestTree.root)); //test Node count
	}

	public final void testCountLeaf() {
		TestTree.populateTree(TestString);
		assertEquals(3, TestTree.countLeaf(TestTree.root)); //test Leaf count
	}

	public final void testDelSubTreesandValue() {
		TestTree.populateTree("b,c,a");
		assertEquals("b", TestTree.root.value); //root value
		assertNotNull(TestTree.root.left); //root has left child
		assertNotNull(TestTree.root.right); //root has right child
		assertNotNull(TestTree.delSubTreesandValue(TestTree.root)); //root still exists
		assertNull(TestTree.root.value); //root value now null
		assertNull(TestTree.root.left); // root left now null
		assertNull(TestTree.root.right); // root right now null
	}

	public final void testDelBTree() {
		TestTree.populateTree("b, c, a");
		assertNotNull(TestTree.root); //root not null
		assertNull(TestTree.delBTree()); //null returned as root
		assertNull(TestTree.root); //root now null
	}

/*

	public final void testTraverseTree() {

	// No need to test as TraverseTree is special case of InOrder from root node

	}
*/

/*

	public final void testPrintFrontToBack() {

	// No need to test as PrintFrontToBack is special case of FrontToBack from root around a  value

	}
*/


    public static Test suite() {
		TestSuite suite = new TestSuite(BinaryTreeTestCase.class);
		return suite;
    }


	public static void main(String[] args) {
	//	junit.textui.TestRunner.run(BinaryTreeTestCase.class);
	    TestResult result = new TestResult();
	    BinaryTreeTestCase.suite().run(result);
	    System.out.println("Number of TestCases Run: " + result.runCount());
	    System.out.println("Number of Tests Failed: " + result.failureCount());
	    System.out.println("Number of Tests Failed with Error: " + result.errorCount());
	}

}
