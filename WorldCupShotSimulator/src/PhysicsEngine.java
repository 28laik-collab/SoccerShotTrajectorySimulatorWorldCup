public class PhysicsEngine {
    // Create the PhysicsEngine class

    // Store the constant values for: Gravity, the goal height, and the goal width
    private static final double GRAVITY = 9.81;
    private static final double GOAL_WIDTH = 7.32;
    private static final double GOAL_HEIGHT = 2.44;

    // Create the simulateShot method
    public static void simulateShot(Shot shot) {
        // Convert the shot angle from degrees to radians (using toRadians method)
        double angleRadians = Math.toRadians(shot.getAngle());
        // Get the value of the shot power
        double power = shot.getPower();

        // Calculate the total distance returned using the projectile motion formula
        double range = (Math.pow(power, 2)
                * Math.sin(2 * angleRadians))
                / GRAVITY;

        // Set the new value of the total distance (from the equation above) to calculatedDistance
        shot.setCalculatedDistance(range);

        // Get the target distance to the goal
        double x = shot.getTargetDistance();

        // Calculate the ball's height at when it reaches the goal line
        double heightAtGoal = x * Math.tan(angleRadians)
                - (GRAVITY * Math.pow(x, 2))
                / (2 * Math.pow(power * Math.cos(angleRadians), 2));

        // Set that value calculated as the value of heightAtGoal
        shot.setHeightAtGoal(heightAtGoal);

        // Check if the shot is horizontally inside the goal width
        boolean accurateShot =
                Math.abs(shot.getHorizontalOffset()) <= (GOAL_WIDTH / 2);

        // Check if the ball reaches the goal line above the ground (doesn't land too early)
        boolean reachesGoal = heightAtGoal >= 0;
        // Check if the ball is below the crossbar or not
        boolean underCrossbar = heightAtGoal <= GOAL_HEIGHT;

        // Calculate the placement zone using the calculatePlacementZone method
        String placementZone = calculatePlacementZone(shot);
        // Store the PlacementZone inside the Shot object
        shot.setPlacementZone(placementZone);

        // If the shot is accurate horizontally AND the ball reaches the goal
        // AND the ball is scored under the crossbar:
        boolean goal = accurateShot && reachesGoal && underCrossbar;
        // Set the goal result to true
        shot.setGoal(goal);
        // If not, keep the goal result at false
    }

    private static String calculatePlacementZone(Shot shot) {
        // Get the value of the Height at the goal and set it to a variable named height
        double height = shot.getHeightAtGoal();
        // Get the value of the horizontal offset and set it to a variable named offset
        double offset = shot.getHorizontalOffset();

        // if the height is below 0:
        if (height < 0) {
            // Return "Short / Grounded Before Goal"
            return "Short / Grounded Before Goal";
        }

        // If the height is above the crossbar (goal height)
        if (height > GOAL_HEIGHT) {
            // Return "Over Crossbar"
            return "Over Crossbar";
        }

        // If the horizontal offset is outside the goal width:
        if (Math.abs(offset) > GOAL_WIDTH / 2) {
            // And if the offset is a negative value (left)
            if (offset < 0) {
                // Return "Wide Left"
                return "Wide Left";
                // If else,
            } else {
                // Return "Wide Right"
                return "Wide Right";
            }
        }

        // Create a String named verticalZone
        String verticalZone;

        // If the height is in the upper half of the goal
        if (height >= GOAL_HEIGHT / 2) {
            // Set the verticalZone to "Top"
            verticalZone = "Top";
            // If else:
        } else {
            // Set the verticalZone to "Bottom"
            verticalZone = "Bottom";
        }

        // Now create another String named horizontalZone
        String horizontalZone;
        // Create a double named thirdWidth and which divides the goal width into thirds
        double thirdWidth = GOAL_WIDTH / 3;

        // If the offset is in the left section
        if (offset < -thirdWidth / 2) {
            // Set horizontalZone to "Left"
            horizontalZone = "Left";
            // If the offset is in the right section
        } else if (offset > thirdWidth / 2) {
            // Set horizontalZone to "Right"
            horizontalZone = "Right";
            // If else:
        } else {
            // Set horizontalZone to center
            horizontalZone = "Center";
        }

        // Return the verticalZone and horizontalZone
        return verticalZone + " " + horizontalZone;
    }

    // The findBestShot with the targetDistance and horizontalOffset parameter
    public static Shot findBestShot(double targetDistance, double horizontalOffset) {
        // Set bestShot to null
        Shot bestShot = null;
        // Set bestHeightDifference to some very large number (using MAX_VALUE)
        double bestHeightDifference = Double.MAX_VALUE;

        // For each angle from 5 to 60
        for (int angleIndex = 5; angleIndex <= 60; angleIndex++) {
            // Nested loop - for each power from 5 to 50
            for (int powerIndex = 5; powerIndex <= 50; powerIndex++) {
                // Create a tester shot (testShot) with the angle and power during that for loop
                Shot testShot = new Shot(angleIndex, powerIndex, targetDistance, horizontalOffset);
                // Simulate the testShot
                simulateShot(testShot);

                // Calculate how close the shot height is to the ideal goal height
                double heightDifference = Math.abs(testShot.getHeightAtGoal() - 1.2);

                // If the testShot is a goal AND its height difference is better (closer) than the current best:
                if (testShot.isGoal() && heightDifference < bestHeightDifference) {
                    // Update the bestHeightDifference
                    bestHeightDifference = heightDifference;
                    // Save this test shot as the bestShot
                    bestShot = testShot;
                }
            }
        }
        // Return the best shot
        return bestShot;
    }
}