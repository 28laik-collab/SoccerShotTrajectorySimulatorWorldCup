import java.util.ArrayList;

// Create the ShotHistory class
public class ShotHistory {

    // Create an ArrayList to store the Shot objects
    private ArrayList<Shot> shots;

    // When a ShotHistory is created:
    public ShotHistory() {
        // Initialize the ArrayList
        shots = new ArrayList<Shot>();
    }

    // Add the given Shot object to the ArrayList
    public void addShot(Shot shot) {
        shots.add(shot);
    }

    public void displayAllShots() {
        // If there are no shots inputted
        if (shots.size() == 0) {
            // Display "No shots have been recorded yet."
            System.out.println("No shots have been recorded yet.");
            // End the method
            return;
        }

        // Display the tile for the "shot history"
        System.out.println("\n --- Shot History ---");

        // For each shot in the ArrayList
        for (int index = 0; index < shots.size(); index++) {
            // Display the Shot number (index + 1) and the shot information
            System.out.println((index + 1) + ". " + shots.get(index));
        }
    }

    // This method would return the size of the ArrayList, aka the total number of shots
    public int getTotalShots() {
        return shots.size();
    }


    public int getTotalGoals() {
        // First sets the value of goals to 0.
        int goals = 0;

        // For each shot in the ArrayList,
        for (Shot shot : shots) {
            // If the shot is a goal
            if (shot.isGoal()) {
                // Increment goals.
                goals++;
            }
        }

        // Return the total goals
        return goals;
    }

    public double getScoringPercentage() {
        // If there are no shots,
        if (shots.size() == 0) {
            // return 0
            return 0;
        }

        // Calculate the total goals divided by total shots times 100 to get the percentage. Cast as double.
        // Return the scoring percentage
        return (double) getTotalGoals() / shots.size() * 100;
    }


    public Shot getBestShot() {
        // If there are no shots
        if (shots.size() == 0) {
            // Return null
            return null;
        }

        // Set the first shot as the best shot.
        Shot bestShot = shots.get(0);
        double bestDifference = Math.abs(bestShot.getCalculatedDistance() - bestShot.getTargetDistance());

        // For each shot in the ArrayList
        for (Shot shot : shots) {
            // Calculate how close the shot distance is to the target distance
            double difference = Math.abs(shot.getCalculatedDistance() - shot.getTargetDistance());

            // If this shot is closer than the current bestShot
            if (difference < bestDifference) {
                bestDifference = difference;
                // Update the bestShot
                bestShot = shot;
            }
        }

        // Return the bestShot
        return bestShot;
    }

    public void displayStats() {
        System.out.println("\n--- Shot Statistics ---");
        // Display the total shots, total goals and the scoring percentage
        System.out.println("Total shots: " + getTotalShots());
        System.out.println("Total goals: " + getTotalGoals());
        System.out.println("Scoring percentage: " + String.format("%.2f", getScoringPercentage()) + "%");

        // Find the bestShot
        Shot bestShot = getBestShot();

        // If the bestShot is NOT null (aka if it exists), display bestShot.
        if (bestShot != null) {
            System.out.println("Best shot: " + bestShot);
        }
    }
}