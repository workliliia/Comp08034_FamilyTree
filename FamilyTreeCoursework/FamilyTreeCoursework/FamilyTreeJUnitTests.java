package FamilyTreeCoursework;

import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyTreeJUnitTests {
    // Method to create an example family tree for testing purposes
    private static FamilyTree getExampleFamilyTree() {
        // Create a family tree with James as the root
        FamilyTree familyTree = new FamilyTree("James");
        try {
            // Add partner Mary to James
            familyTree.addPartner(familyTree.ancestor, "Mary");
            // Add children to James and Mary
            familyTree.addChild(familyTree.ancestor, "John");
            familyTree.addChild(familyTree.ancestor, "Amy");
            familyTree.addChild(familyTree.ancestor, "Alan");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return familyTree;
    }

    // Method to create an example family tree with additional partner Jim
    private static FamilyTree getExampleFamilyTree2() {
        // Get the example family tree
        FamilyTree familyTree = getExampleFamilyTree();

        // Add partner Jim to Amy
        try {
            FamilyTreeNode amy = familyTree.getNodeFromIdentifier(4);
            familyTree.addPartner(amy, "Jim");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        return familyTree;
    }

    // Test the creation of the example family tree
    @Test
    public void testExampleFamilyTree() {
        FamilyTree familyTree = getExampleFamilyTree();
        assertNotNull(familyTree);
    }

    // Test the toString method of FamilyTree
    @Test
    public void testToString() {
        FamilyTree familyTree = getExampleFamilyTree();
        String result = "James partner Mary\n  John\n  Amy\n  Alan\nMary partner James\n  John\n  Amy\n  Alan\n";
        assertEquals(result, familyTree.toString());
    }

    // Test the displayFamilyTreeIdentifiers method of FamilyTree
    @Test
    public void testIdentifiers() {
        FamilyTree familyTree = getExampleFamilyTree2();
        String result = "James (identifier 1) partner Mary (identifier 2)\n  " +
                "John (identifier 3) has no partner\n  Amy (identifier 4) partner Jim (identifier 6)\n    " +
                "no children\n  Alan (identifier 5) has no partner\n\n";
        assertEquals(result, familyTree.displayFamilyTreeIdentifiers());
    }

    // Test the getFamilyTreeFromNode method of FamilyTree
    @Test
    public void testIdentifiers2() {
        FamilyTree familyTree = getExampleFamilyTree2();
        FamilyTreeNode amy = null;
        try {
            amy = familyTree.getNodeFromIdentifier(4);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String result = "Amy (identifier 4) partner Jim (identifier 6)\n  no children\n";
        assertEquals(result, familyTree.getFamilyTreeFromNode(amy));
    }

    // Test the getFamilyTreeFromNode method of FamilyTree with another node
    @Test
    public void testIdentifiers3() {
        FamilyTree familyTree = getExampleFamilyTree2();
        FamilyTreeNode mary = null;
        try {
            mary = familyTree.getNodeFromIdentifier(2);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String result = "Mary (identifier 2) partner James (identifier 1)\n  " +
                "John (identifier 3) has no partner\n  Amy (identifier 4) partner Jim (identifier 6)\n    " +
                "no children\n  Alan (identifier 5) has no partner\n\n";
        assertEquals(result, familyTree.getFamilyTreeFromNode(mary));
    }

}
