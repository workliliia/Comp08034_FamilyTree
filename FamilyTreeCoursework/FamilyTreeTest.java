package FamilyTreeCoursework;

public class FamilyTreeTest {
    public static void main(String[] args) {
        Integer option;
        String ancestorName = Input.getString("input the ancestor's name: ");
        FamilyTree familyTree = new FamilyTree(ancestorName);
        
        do {
            // options menu
            System.out.println("0: quit");
            System.out.println("1: display the full family tree");
            System.out.println("2: display the family tree from an identifier");
            System.out.println("3: add a child");
            System.out.println("4: add a partner");
            option = Input.getInteger("input option: ");

            switch (option) {
                case 1:
                    // 1: display the full family tree
                    System.out.println(familyTree);
                    break;
                case 2:
                    // 2: display the family tree from an identifier
                    // display the family tree with identifiers
                    System.out.println(familyTree.displayFamilyTreeIdentifiers());

                    // ask for the identifier
                    Integer rootIdentifier = Input.getInteger("input the identifier to display the tree of: ");
                    FamilyTreeNode rootNode;
                    try {
                        rootNode = familyTree.getNodeFromIdentifier(rootIdentifier);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    // get the family tree of that family node
                    System.out.println(familyTree.getFamilyTreeFromNode(rootNode));
                    break;
                case 3:
                    // 3: add a child
                    System.out.println(familyTree.displayFamilyTreeIdentifiers());

                    // select family member
                    Integer parentIdentifier = Input.getInteger("input the identifier of the parent: ");
                    FamilyTreeNode parentNode;
                    try {
                        parentNode = familyTree.getNodeFromIdentifier(parentIdentifier);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    // check for partner, do not ask if member has no partner
                    if (parentNode.partner == null) {
                        System.out.println("the parent has no partner, cannot add a child");
                        break;
                    }

                    // add child
                    String childName = Input.getString("input the new child name: ");
                    try {
                        familyTree.addChild(parentNode, childName);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    // 4: add a partner
                    System.out.println(familyTree.displayFamilyTreeIdentifiers());

                    // select family member
                    Integer identifier = Input.getInteger("input the identifier to add a partner to: ");
                    FamilyTreeNode node;
                    try {
                        node = familyTree.getNodeFromIdentifier(identifier);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    // check for partner and don't ask if there is one
                    if (node.partner != null) {
                        System.out.println("the node already has a partner");
                        break;
                    }

                    // add partner
                    String newPartnerName = Input.getString("input the new partner's name: ");
                    try {
                        familyTree.addPartner(node, newPartnerName);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        } while (option != 0);    
    }
}
