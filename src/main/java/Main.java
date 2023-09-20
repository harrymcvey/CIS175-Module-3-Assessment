import controller.CatHelper;
import model.Cat;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Create an instance of CatHelper to interact with the database
        CatHelper catHelper = new CatHelper();

        while (true) {
            // Display menu options to the user
            System.out.println("Options:");
            System.out.println("1. Add a new cat");
            System.out.println("2. Show all cats");
            System.out.println("3. Update a cat");
            System.out.println("4. Delete a cat");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add a new cat to the database based on user input
                    System.out.print("Enter cat's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter cat's breed: ");
                    String breed = scanner.nextLine();
                    System.out.print("Enter cat's age: ");
                    int age = scanner.nextInt();

                    Cat newCat = new Cat(name, breed, age);
                    catHelper.insertCat(newCat);
                    System.out.println("Cat added successfully.");
                    break;

                case 2:
                    // Retrieve and display all cats from the database
                    List<Cat> allCats = catHelper.showAllCats();
                    System.out.println("All Cats:");
                    for (Cat cat : allCats) {
                        System.out.println(cat.getName() + " - " + cat.getBreed() + " - " + cat.getAge() + " years old");
                    }
                    break;

                case 3:
                    // Update a cat's age based on user input
                    System.out.print("Enter cat ID to update: ");
                    int catId = scanner.nextInt();
                    Cat catToUpdate = catHelper.findCatById(catId);

                    if (catToUpdate != null) {
                        System.out.print("Enter new age for the cat: ");
                        int newAge = scanner.nextInt();
                        catToUpdate.setAge(newAge);
                        catHelper.updateCat(catToUpdate);
                        System.out.println("Cat updated successfully.");
                    } else {
                        System.out.println("Cat not found.");
                    }
                    break;

                case 4:
                    // Delete a cat based on user input
                    System.out.print("Enter cat ID to delete: ");
                    int catIdToDelete = scanner.nextInt();
                    Cat catToDelete = catHelper.findCatById(catIdToDelete);

                    if (catToDelete != null) {
                        catHelper.deleteCat(catToDelete);
                        System.out.println("Cat deleted successfully.");
                    } else {
                        System.out.println("Cat not found.");
                    }
                    break;

                case 5:
                    // Clean up resources and exit the program
                    catHelper.cleanUp();
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
