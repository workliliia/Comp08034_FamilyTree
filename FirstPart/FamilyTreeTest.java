package FirstPart;

// Main class to test the FamilyTree implementation.
public class FamilyTreeTest {
    public static void main(String[] args) {
        try {
            FamilyTree familyTree = new FamilyTree("James", "Mary");
            Integer option;
            do {
                // Display the menu options
                System.out.println("0: quit");
                System.out.println("1: input child details");
                System.out.println("2: display family");
                // Get the user's choice
                option = Input.getInteger("option: ");
                switch (option) {
                    case 0:
                        // Quit the program
                        System.out.println("Quitting program.");
                        break;
                    case 1:
                        // Add a child to a family member
                        try {
                            String parentName = Input.getString("Parent name: ");
                            String childName = Input.getString("Child name: ");
                            if (containsNumbersChild(childName)){
                                throw new RuntimeException("Input contains numbers.");
                            }
                            familyTree.addChild(parentName, childName);
                        } catch (Exception e) {
                            System.out.println("Error adding child: " + e.getMessage());
                        }
                        break;
                    case 2:
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
    private static boolean containsNumbersChild(String childName) {
        return childName.matches(".*\\d.*");
    }
}