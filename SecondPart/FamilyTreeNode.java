package SecondPart;

class FamilyTreeNode {
    int id;
    static int nextId = 1;
    String name;
    FamilyTreeNode partner;
    FamilyTreeNode nextSibling;
    FamilyTreeNode firstChild;

    FamilyTreeNode(String name) {
        this.name = name;
        this.id = nextId++;
        this.partner = null;
        this.nextSibling = null;
        this.firstChild = null;
    }
}

