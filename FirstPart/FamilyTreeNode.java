package FirstPart;

// Class representing a node in the family tree.
class FamilyTreeNode {
    String name;
    FamilyTreeNode partner;
    FamilyTreeNode nextSibling;
    FamilyTreeNode firstChild;

    // Constructor for FamilyTreeNode
    FamilyTreeNode(String name) {
        this.name = name;
        this.partner = null;
        this.nextSibling = null;
        this.firstChild = null;
    }
}

