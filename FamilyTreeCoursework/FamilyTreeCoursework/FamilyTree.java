package FamilyTreeCoursework;
class FamilyTree {
    FamilyTreeNode ancestor;
    Integer nextIdentifier = 2;

    // Constructor to set up the ancestor and their partner
    FamilyTree(String ancestorName) {
        this.ancestor = new FamilyTreeNode(ancestorName, 1);
    }

    // Adds a child to the ancestor or their partner
    public void addChild(FamilyTreeNode parent, String childName) throws Exception {
        // Check for unique sibling name
        if (!isUniqueName(parent.firstChild, childName)) {
            throw new Exception("Child name must be unique");
        }

        // Check for partner
        if (parent.partner == null) {
            throw new Exception("family member has no partner");
        }

        // Create the child
        FamilyTreeNode newChild = new FamilyTreeNode(childName, this.nextIdentifier);
        this.nextIdentifier += 1;
        if (parent.firstChild == null) {
            parent.firstChild = newChild;
            // Add also to the partner
            // this is not needed if it's not the first child as the data structure will be the same
            parent.partner.firstChild = newChild;
        } else {
            FamilyTreeNode current = parent.firstChild;
            while (current.nextSibling != null) {
                current = current.nextSibling;
            }
            current.nextSibling = newChild;
        }
    }

    public void addPartner(FamilyTreeNode node, String partnerName) throws Exception {
        // Check if member already has a partner
        if (node.partner != null) {
            throw new Exception("family member already has a partner");
        }

        // Create the partner
        node.partner = new FamilyTreeNode(partnerName, this.nextIdentifier);
        this.nextIdentifier += 1;
        // Make sure the partner's partner is the member
        node.partner.partner = node;
        // Make sure both partners have the same children
        node.partner.firstChild = node.firstChild;
    }

    private boolean isUniqueName(FamilyTreeNode sibling, String name) {
        while (sibling != null) {
            if (sibling.name.equalsIgnoreCase(name)) {
                return false;
            }
            sibling = sibling.nextSibling;
        }
        return true;
    }

    // Returns a string representation of the family tree
    public String toString() {
        StringBuilder builder = new StringBuilder();
        printFamilyTree(ancestor, "", builder);
        if (ancestor.partner != null) {
            printFamilyTree(ancestor.partner, "", builder);
        }
        return builder.toString();
    }

    private void printFamilyTree(FamilyTreeNode node, String indent, StringBuilder builder) {
        if (node != null) {
            builder.append(indent).append(node.name).append(node.partner != null ? " partner " +
                    node.partner.name + "\n" : "\n");
            FamilyTreeNode child = node.firstChild;
            while (child != null) {
                if (child.partner != null || child.firstChild != null) {
                    // This child has a partner or is a parent, so we print their family tree
                    printFamilyTree(node.firstChild, indent + "  ", builder);
                } else {
                    builder.append(indent + "  ").append(child.name).append("\n");
                }
                child = child.nextSibling;
            }
        }
    }

    public String displayFamilyTreeIdentifiers() {
        // Display the full family tree
        return getFamilyTreeIdentifiersString(ancestor, "");
    }

    public String getFamilyTreeFromNode(FamilyTreeNode node) {
        // Get the family tree of a family node
        return getFamilyTreeIdentifiersString(node, "");
    }

    private String getFamilyTreeIdentifiersString(FamilyTreeNode node, String indent) {
        // Function which gives the display of a family tree from a node, and is recursive for children
        String str = "";
        if (node != null) {
            // name
            str += indent + node.name + " (identifier " + node.identifier + ")";

            if (node.partner != null) {
                // partner information
                str += " partner " + node.partner.name + " (identifier " + node.partner.identifier + ")";

                if (node.firstChild != null) {
                    // there is at least one child
                    str += "\n";

                    // loop over the children
                    FamilyTreeNode child = node.firstChild;
                    while (child != null) {
                        // get the string for the child
                        str += getFamilyTreeIdentifiersString(child, indent + "  ");
                        child = child.nextSibling;
                    }
                } else {
                    // no children
                    str += "\n" + indent + "  " + "no children";
                }
            } else {
                // no partner
                str += " has no partner";
            }
            str += "\n";
        }

        // return the result
        return str;
    }

    public FamilyTreeNode getNodeFromIdentifier(Integer identifier) throws Exception {
        // Function to get the FamilyTreeNode from the integer identifier
        FamilyTreeNode node = getNodeFromIdentifierRecursive(ancestor, identifier);
        if (node == null) {
            throw new Exception("identifier not found");
        }
        return node;
    }

    private FamilyTreeNode getNodeFromIdentifierRecursive(FamilyTreeNode node, Integer identifier) {
        // Function to get the FamilyTreeNode, with recursion for children
        if (node != null) {
            // check current node
            if (node.identifier.equals(identifier)) {
                return node;
            }
            // check partner
            if (node.partner != null) {
                if (node.partner.identifier.equals(identifier)) {
                    return node.partner;
                }

                // check children
                FamilyTreeNode child = node.firstChild;
                while (child != null) {
                    FamilyTreeNode childNode = getNodeFromIdentifierRecursive(child, identifier);
                    if (childNode != null) {
                        return childNode;
                    }
                    child = child.nextSibling;
                }
            }
        }
        return null;
    }
}
