package FamilyTreeCoursework;

class FamilyTreeNode {
    String name;
    Integer identifier;
    FamilyTreeNode partner;
    FamilyTreeNode nextSibling;
    FamilyTreeNode firstChild;

    // Constructor for FamilyTreeNode
    FamilyTreeNode(String name, Integer identifier) {
        this.name = name;
        this.identifier = identifier;
        this.partner = null;
        this.nextSibling = null;
        this.firstChild = null;
    }
}

