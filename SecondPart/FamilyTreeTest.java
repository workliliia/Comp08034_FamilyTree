package SecondPart;

public class FamilyTreeTest {
    public static void main(String[] args) {
        try {
            FamilyTree familyTree = null;
            Integer option;
            do {
                // Display the menu options
                System.out.println("0: quit");
                System.out.println("1: input ancestor details");
                System.out.println("2: input partner details");
                System.out.println("3: input child details");
                System.out.println("4: find a family member by name");
                System.out.println("5: display family tree by id");
                System.out.println("6: display family");
                // Get the user's choice
                option = Input.getInteger("option: ");
                switch (option) {
                    case 0:
                        // Quit the program
                        System.out.println("Quitting program.");
                        break;
                    case 1:
                        // Add an ancestor to the family tree
                        try {
                            String ancestorName = Input.getString("Ancestor name: ");
                            familyTree = new FamilyTree(ancestorName);
                        } catch (Exception e) {
                            System.out.println("Error adding ancestor: " + e.getMessage());
                        }
                        break;
                    case 2:
                        // Add a partner to the root member
                        try {
                            int partnerId = Input.getInteger("Partner id: ");
                            String partnerName = Input.getString("Partner name: ");
                            familyTree.addPartner(partnerId, partnerName); // Assumes root is ID 1 for the partner action.
                        } catch (Exception e) {
                            System.out.println("Error adding partner: " + e.getMessage());
                        }
                        break;
                    case 3:
                        // Add a child to a family member
                        try {
                            int partnerId = Input.getInteger("Partner id: ");
                            String childName = Input.getString("Child name: ");
                            familyTree.addChild(partnerId, childName);
                        } catch (Exception e) {
                            System.out.println("Error adding child: " + e.getMessage());
                        }
                        break;
                    case 4:
                        // Find a family member by name
                        try {
                            String name = Input.getString("Name to find a member: ");
                            FamilyTreeNode member = familyTree.findFamilyMemberByName(name);
                            if (member != null) {
                                System.out.println("Found member: " + member.name + " with id: " + member.id);
                            } else {
                                System.out.println("No member found with name: " + name);
                            }
                        } catch (Exception e) {
                            System.out.println("Error finding family member: " + e.getMessage());
                        }
                        break;
                    case 5:
                        // Display details of a specific family tree
                        try {
                            int memberId = Input.getInteger("Member for the family tree id: ");
                            String details = familyTree.printFamilyTreeById(memberId);
                            System.out.println(details);
                        } catch (Exception e) {
                            System.out.println("Error displaying family member details: " + e.getMessage());
                        }
                        break;
                    case 6:
                        // Display the family tree
                        System.out.println(familyTree);
                        break;
                    default:
                        // Invalid option
                        System.out.println("Invalid option");
                }
            } while (option != 0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}