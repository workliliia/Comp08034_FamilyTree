package FirstPart;

import java.util.HashSet;
import java.util.Set;

// Class representing the family tree structure.
class FamilyTree {
    FamilyTreeNode ancestor;

    // Constructor initializes the family tree with an ancestor and their partner.
    FamilyTree(String ancestorName, String partnerName) {
        this.ancestor = new FamilyTreeNode(ancestorName);
        this.ancestor.partner = new FamilyTreeNode(partnerName);
        this.ancestor.partner.partner = this.ancestor;  // Set reciprocal partnership.
    }

    // Method to add a child to a specified parent in the family tree.
    public void addChild(String parentName, String childName) throws Exception {
        FamilyTreeNode parent = findParent(ancestor, parentName);
        if (parent == null) {
            throw new Exception("Parent not found");
        }

        // Check for unique sibling name
        if (!isUniqueName(parent.firstChild, childName)) {
            throw new Exception("Child name must be unique");
        }

        FamilyTreeNode newChild = new FamilyTreeNode(childName);
        if (parent.firstChild == null) {
            parent.firstChild = newChild;
        } else {
            FamilyTreeNode current = parent.firstChild;
            while (current.nextSibling != null) {
                current = current.nextSibling;
            }
            current.nextSibling = newChild;
        }
    }

    // Helper method to find a parent node by name, recursively checking children.
    private FamilyTreeNode findParent(FamilyTreeNode node, String parentName) {
        if (node == null) {
            return null;
        }
        if (node.name.equalsIgnoreCase(parentName) ||
                (node.partner != null && node.partner.name.equalsIgnoreCase(parentName))) {
            return node;
        }
        return findParent(node.firstChild, parentName);
    }

    // Helper method to ensure that each child has a unique name.
    private boolean isUniqueName(FamilyTreeNode sibling, String name) {
        while (sibling != null) {
            if (sibling.name.equalsIgnoreCase(name)) {
                return false;  // Return false if a duplicate name is found.
            }
            sibling = sibling.nextSibling;
        }
        return true;
    }

    // Returns a string representation of the family tree, formatted for readability.
    public String toString() {
        StringBuilder builder = new StringBuilder();
        printFamilyTree(ancestor, builder, new HashSet<>(), "");
        return builder.toString();
    }

    // Helper method to print the family tree in a flat format
    private void printFamilyTree(FamilyTreeNode node, StringBuilder builder, Set<String> visited, String indentation) {
        if (node != null && !visited.contains(node.name)) {
            visited.add(node.name);
            // Print parent and partner's name if partner exists
            if (node.partner != null) {
                builder.append(indentation).append(node.name).append(" partner ").append(node.partner.name).append("\n");
                // Print all children names only if partner hasn't been visited yet
                if (!visited.contains(node.partner.name)) {
                    FamilyTreeNode child = node.firstChild;
                    while (child != null) {
                        builder.append(indentation).append("    ").append(child.name).append("\n");
                        child = child.nextSibling;
                    }
                }
            } else {
                builder.append(indentation).append(node.name).append("\n");
            }
            // Avoid printing partner again if already visited
            if (node.partner != null && !visited.contains(node.partner.name)) {
                printFamilyTree(node.partner, builder, visited, indentation);
            }
            // Recursive call to print children as new parents if they have children
            FamilyTreeNode child = node.firstChild;
            while (child != null) {
                printFamilyTree(child, builder, visited, indentation + "    ");
                child = child.nextSibling;
            }
        }
    }
}