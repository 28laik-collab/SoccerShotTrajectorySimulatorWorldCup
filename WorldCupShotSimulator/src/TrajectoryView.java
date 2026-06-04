import javax.swing.*;
import java.awt.*;

// Create the TrajectoryView class as a custom JPanel
public class TrajectoryView extends JPanel {

    //Store that shot object
    private Shot shot ;

    // While the TrajectoryView is created,
    public TrajectoryView(Shot shot) {
        // Store the shot object
        this.shot = shot;
        // Set the panel size
        setPreferredSize(new Dimension(900, 650));
        // And set the background color (green)
        setBackground(new Color(25, 130, 25));
    }

    @Override
    // When the panel is drawn:
    protected void paintComponent(Graphics g) {
        // Clear the entire panel
        super.paintComponent(g);
        // Draw the side view category
        drawSideView(g);
        // Draw the top-down view category
        drawTopDownView(g);
    }

    // Method to draw the side view
    private void drawSideView(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Draw the side-view ground and starting position
        int groundY = 300;
        int startX = 60;

        // Draw the labels for the side-view
        g2.setColor(Color.WHITE);
        g2.drawString("Side View: Height vs Distance", 60, 30);
        g2.drawLine(startX, groundY, 820, groundY);
        g2.drawString("Distance", 430, 325);
        g2.drawString("Height", 15, 160);

        // Set the location of the goal
        int goalX = 760;
        // Drawing the goal
        g2.drawRect(goalX, groundY - 90, 50, 90);
        // Draw the goal label (String)
        g2.drawString("Goal", goalX, groundY - 100);

        // Convert the shot angle to radians
        double angleRadians = Math.toRadians(shot.getAngle());
        // Set the variable velocity to the power given.
        double velocity = shot.getPower();
        // Set gravity as the real-life gravitational value (approx. 9.81)
        double gravity = 9.81;

        g2.setColor(Color.YELLOW);

        // For each x-position along the shot distance:
        for (double x = 0; x <= shot.getCalculatedDistance(); x += 0.5) {
            // Calculate the ball height using the projectile motion formulas
            double y = x * Math.tan(angleRadians)- (gravity * Math.pow(x, 2))
                    / (2 * Math.pow(velocity * Math.cos(angleRadians), 2));

            // Convert the real life x and y values into screen pixels
            int screenX = (int) (startX + x * 10);
            int screenY = (int) (groundY - y * 10);

            // If the point is inside the visible screen area:
            if (screenX <= 820 && screenY >= 40 && screenY <= groundY) {
                // Draw a small yellow circle at that specific point

                 g2.fillOval(screenX, screenY, 6, 6);
            }
        }

        // Display the shot information of the angle, power, distance and result.
        g2.setColor(Color.WHITE);
        g2.drawString(

                "Angle: " + shot.getAngle()
                        + "°  Power: " + shot.getPower()
                        + " m/s   Distance: "
                        + String.format("%.2f", shot.getCalculatedDistance())
                        + " m   Result: "
                        + (shot.isGoal() ? "GOAL" : "MISS"),
                60,
                55
        );
    }

    // For the top-down view:
    private void drawTopDownView(Graphics g) {
        // Start by creating a new field for the view
        Graphics2D g2 = (Graphics2D) g;

        // Set the positions of the field
        int fieldX = 60;
        int fieldY = 380;
        int fieldWidth = 760;
        int fieldHeight = 220;

        // Set labels to visualize on the screen
        g2.setColor(Color.WHITE);

        g2.drawString("Top-Down View: Direction / Accuracy", fieldX, fieldY - 15);
        g2.drawRect(fieldX, fieldY, fieldWidth, fieldHeight);

        // State the starting points of the drawing
        int startX = fieldX + 30;
        int startY = fieldY + fieldHeight / 2;

        // draw the goals as rectangles
        int goalX = fieldX + fieldWidth - 40;
        int goalTop = fieldY + fieldHeight / 2 - 35;
        int goalBottom = fieldY + fieldHeight / 2 + 35;

        // Label the goals
        g2.drawLine(goalX, goalTop, goalX, goalBottom);
        g2.drawString("Goal Width", goalX - 30, goalTop - 10);

        // Calculate the ball's ending position by using the horizontalOffset
        int endX = goalX;
        int endY = startY - (int) (shot.getHorizontalOffset() * 15);

        g2.setColor(Color.YELLOW);
        g2.drawLine(startX, startY, endX, endY);

        // Draw a small circle for the starting and ending position
        g2.fillOval(startX - 5, startY - 5, 10, 10);
        g2.fillOval(endX - 5, endY - 5, 10, 10);

        g2.setColor(Color.WHITE);
        // Label the start and ending position
        g2.drawString("Shot Start", startX - 20, startY + 25);
        g2.drawString("Ball Landing Direction", endX - 120, endY - 10);

        // If the horizontal offset is inside the goal width:
        if (Math.abs(shot.getHorizontalOffset()) <= 3.66) {
            // Display "Direction: On Target"
            g2.drawString("Direction: On Target", fieldX, fieldY + fieldHeight + 25);
            // If not,
        } else {
            // Display "Direction: Off Target"
            g2.drawString("Direction: Off Target", fieldX, fieldY + fieldHeight + 25);
        }
    }

    public static void showTrajectoryWindow(Shot shot) {
        // Create a new window
        JFrame frame = new JFrame("Shot Trajectory Visualization");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Add a new Trajectory Panel to the window
        frame.add(new TrajectoryView(shot));
        // Make the window fit the panel
        frame.pack();
        frame.setLocationRelativeTo(null);
        // Display the window
        frame.setVisible(true);
    }
}