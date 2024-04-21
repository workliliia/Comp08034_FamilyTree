package SecondPart;

import java.util.HashMap;

class FamilyTree {
    private FamilyTreeNode ancestor;
    private HashMap<Integer, FamilyTreeNode> members = new HashMap<>();

    FamilyTree(String ancestorName) {
        this.ancestor = new FamilyTreeNode(ancestorName);
        members.put(this.ancestor.id, this.ancestor);
    }

    public void addPartner(int partnerId, String partnerName) throws Exception {
        FamilyTreeNode member = members.get(partnerId);
        if (member == null) {
            throw new Exception("No such member found");
        }
        if (member.partner != null) {
            throw new Exception("Member already has a partner");
        }
        member.partner = new FamilyTreeNode(partnerName);
        member.partner.partner = member;
        members.put(member.partner.id, member.partner);
    }

    public void addChild(int parentId, String childName) throws Exception {
        FamilyTreeNode parent = members.get(parentId);
        if (parent == null || parent.partner == null) {
            throw new Exception("Parent not found or has no partner");
        }

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
        members.put(newChild.id, newChild);
    }
    private FamilyTreeNode findParent(FamilyTreeNode node, String parentName) {
        if (node == null) {
            return null;
        }
        if (node.name.equalsIgnoreCase(parentName) || (node.partner != null && node.partner.name.equalsIgnoreCase(parentName))) {
            return node;
        }
        FamilyTreeNode childParent = findParent(node.firstChild, parentName);
        return childParent != null ? childParent : findParent(node.nextSibling, parentName);
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        printFamilyTree(ancestor, "", builder);
        return builder.toString();
    }

    private void printFamilyTree(FamilyTreeNode node, String indent, StringBuilder builder) {
        if (node != null) {
            builder.append(indent).append(node.name).append(" (identifier ").append(node.id).append(")").append(node.partner != null ? " partner " + node.partner.name + " (identifier " + node.partner.id + ")" : " has no partner").append("\n");
            FamilyTreeNode child = node.firstChild;
            while (child != null) {
                printFamilyTree(child, indent + "  ", builder);
                child = child.nextSibling;
            }
        }
    }
    ///-----------------Find Member by ID-----------------///
    public FamilyTreeNode findFamilyMemberByName(String name) {
        return findNodeByName(ancestor, name);
    }

    private FamilyTreeNode findNodeByName(FamilyTreeNode node, String name) {
        if (node == null) {
            return null;
        }
        if (node.name.equalsIgnoreCase(name) || (node.partner != null && node.partner.name.equalsIgnoreCase(name))) {
            return node;
        }
        FamilyTreeNode foundNode = findNodeByName(node.firstChild, name);
        if (foundNode == null) {
            foundNode = findNodeByName(node.nextSibling, name);
        }
        if (foundNode == null && node.partner != null) {
            foundNode = findNodeByName(node.partner.nextSibling, name);
        }
        return foundNode;
    }

    ///-----------------Family Tree starts from the specific ID-----------------///
    String printFamilyTreeById(Integer id) {
        FamilyTreeNode node = findNodeById(ancestor, id);
        StringBuilder builder = new StringBuilder();
        if (node != null) {
            printFamilyTreeId(node, "", builder);
        }
        return builder.toString();
    }


    private void printFamilyTreeId(FamilyTreeNode node, String prefix, StringBuilder builder) {
        if (node == null) {
            return;
        }
        for (FamilyTreeNode sibling = node; sibling != null; sibling = sibling.nextSibling) {
            builder.append(prefix).append(sibling.name).append(" (identifier ").append(sibling.id).append(")").append(sibling.partner != null ? " partner " + sibling.partner.name + " (identifier " + sibling.partner.id + ")" : " has no partner").append("\n");
            printFamilyTree(sibling.firstChild, prefix + "  ", builder);
        }
    }
    private FamilyTreeNode findNodeById(FamilyTreeNode node, int id) {
        if (node == null) {
            return null;
        }
        if (node.id == id) {
            return node;
        }
        if (node.partner != null && node.partner.id == id) {
            return node.partner;
        }
        FamilyTreeNode foundNode = null;
        for (FamilyTreeNode child = node.firstChild; child != null; child = child.nextSibling) {
            foundNode = findNodeById(child, id);
            if (foundNode != null) {
                break;
            }
        }
        return foundNode;
    }
    ///-----------------End Method-----------------///
}