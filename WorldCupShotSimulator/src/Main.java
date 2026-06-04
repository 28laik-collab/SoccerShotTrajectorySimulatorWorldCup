import java.util.Scanner;
// Import scanner object for users inputs

public class Main {

    public static void main(String[] args) {
        // Create scanner object for users inputs
        Scanner input = new Scanner(System.in);
        // Create ShotHistory object
        ShotHistory history = new ShotHistory();
        // Set a boolean value of running to true, to represent a loop
        boolean running = true;

        System.out.println("=================================");
        System.out.println(" World Cup Shot Simulator");
        System.out.println("=================================");

        // While "running" is true, display the options of:
        // 1. Taking a shot, 2. Seeing shot history, 3. Seeing stats, 4. Exiting, or 5. Optimize a shot
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Take a shot");
            System.out.println("2. View shot history");
            System.out.println("3. View statistics");
            System.out.println("4. Exit");
            System.out.println("5. Auto optimize best shot");
            System.out.print("Choose an option: ");
            // Read the user's choice

            int choice = input.nextInt();

            if (choice == 1) {
                // If the user's input is 1, then:
                // Ask the user for the shot angle
                System.out.print("Enter shot angle in degrees: ");
                // Store that value
                double angle = input.nextDouble();

                // Ask the user for the shot power
                System.out.print("Enter shot power in m/s: ");
                // Store that value
                double power = input.nextDouble();

                // Ask the user for the distance to the goal (in meters)
                System.out.print("Enter distance to goal in meters: ");
                // Store that value
                double targetDistance = input.nextDouble();

                // Ask the user for the horizontal offset (in meters)
                System.out.print("Enter left/right offset in meters (0 = center, negative = left, positive = right): ");
                // Store that value
                double horizontalOffset = input.nextDouble();

                // Create a new shot object from those values as parameters
                Shot shot = new Shot(angle, power, targetDistance, horizontalOffset);
                // Go to PhysicsEngine and use the simulateShot method with the Shot object as the parameter
                PhysicsEngine.simulateShot(shot);
                // Store that shot value to ShotHistory
                history.addShot(shot);

                // Display the shot results
                System.out.println("\nShot complete!");
                System.out.println(shot);

                // Open the trajectory visualization window
                TrajectoryView.showTrajectoryWindow(shot);

                // If the choice is 2, then display all previous shots
            } else if (choice == 2) {
                history.displayAllShots();

                // If the choice is 3, then display the shot statistics
            } else if (choice == 3) {
                history.displayStats();

                // If the choice is 4, set running to false and quit the program
            } else if (choice == 4) {
                running = false;
                System.out.println("Thanks for using the World Cup Shot Simulator!");

                // If the choice is 5:
            } else if (choice == 5) {
                // Ask the user for the distance to the goal (in meters)
                System.out.print("Enter distance to goal in meters: ");
                double targetDistance = input.nextDouble();

                // Ask the user for the horizontal offset (in meters)
                System.out.print("Enter left/right offset in meters (0 = center): ");
                double horizontalOffset = input.nextDouble();

                // Using PhysicsEngine, find the best possible shot.
                Shot bestShot = PhysicsEngine.findBestShot(targetDistance, horizontalOffset);

                // If no valid shot exists, then
                if (bestShot == null) {
                    // Display an error message
                    System.out.println("No successful shot found for this situation.");
                } else {
                    // If else, then:
                    // Display the reccomended shot
                    System.out.println("\nRecommended shot:");
                    System.out.println(bestShot);
                    // Add the shot to the shot history
                    history.addShot(bestShot);
                    // Open the trajectory visualization window
                    TrajectoryView.showTrajectoryWindow(bestShot);
                }

            } else {
                // If none of the options worked, then print an error statement, ask the user to input again
                System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
            }
        }

        // Close scanner input
        input.close();
    }
}