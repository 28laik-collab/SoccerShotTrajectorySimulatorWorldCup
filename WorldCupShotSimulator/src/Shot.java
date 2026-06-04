public class Shot {
    // Create the new shot class

    // Store the input values for: the angle, power, target distance, horizontal offset,
    private double angle;
    private double power;
    private double targetDistance;
    private double horizontalOffset;

    // Store the input values for: the calculated distance, the height at the goal, the goal result, and the placement zone.
    private double calculatedDistance;
    private double heightAtGoal;
    private boolean goal;

    private String placementZone;

    // Create the constructor -> when a shot object is created:
    public Shot(double angle, double power, double targetDistance, double horizontalOffset)  {
        // Save the input values for: The angle, power, target distance, horizontal offset, the placement zone, the calculated distance, and if the result is a goal or not
        this.angle = angle;
        this.power = power;
        this.targetDistance = targetDistance;
        this.horizontalOffset = horizontalOffset;

        placementZone = null;
        calculatedDistance = 0;
        goal = false;
    }

    // Getters for each variable -> so that other classes can read the shot data
    public double getAngle() {
        return angle;
    }

    public double getPower() {
        return power;
    }

    public double getTargetDistance() {
        return targetDistance;
    }

    public double getHorizontalOffset() {
        return horizontalOffset;
    }

    public double getCalculatedDistance() {
        return calculatedDistance;
    }

    public double getHeightAtGoal() { return heightAtGoal;}

    public boolean isGoal() {
        return goal;
    }

    public String getPlacementZone() {
        return placementZone;
    }

    // Setters for each variable -> so that PhysicsEngine can update calculated results
    public void setAngle(double angle) {this.angle = angle;}

    public void setPower(double power) {this.power = power; }

    public void setTargetDistance(double targetDistance) {this.targetDistance = targetDistance; }

    public void setHorizontalOffset(double horizontalOffset) {this.horizontalOffset = horizontalOffset; }

    public void setCalculatedDistance(double calculatedDistance) {
        this.calculatedDistance = calculatedDistance;
    }

    public void setHeightAtGoal(double heightAtGoal) { this.heightAtGoal = heightAtGoal; }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public void setPlacementZone(String placementZone) {
        this.placementZone = placementZone;
    }

    // When a shot is printed, return a formatted string to the console.
    public String toString() {
        return "Angle: " + angle +
                "° | Power: " + power +
                " m/s | Target: " + targetDistance +
                " m | Offset: " + horizontalOffset +
                " m | Distance: " + String.format("%.2f", calculatedDistance) +
                " m | Height at Goal: " + String.format("%.2f", heightAtGoal) +
                " | Placement: " + placementZone +
                " m | Result: " + (goal ? "GOAL" : "MISS");
    }
}