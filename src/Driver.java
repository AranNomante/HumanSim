import javax.swing.*;
import java.awt.*;

public class Driver extends JFrame {
    private static AiPanel aiPanel = new AiPanel();
    private static boolean firstRun = true;
    private static String generation = "0";

    private Driver() {
        setTitle("PRIMITIVE PATH FINDING AI");
        setResizable(false);
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(aiPanel.getWidth() + 100, aiPanel.getHeight() + 100);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(aiPanel);
    }

    static void dialogue() {
        JOptionPane.showMessageDialog(null, "Welcome to primitive path finding ai simulator!");
        JOptionPane.showMessageDialog(null, "Simulation will run faster over time and fastest" +
                " after first sim.");
        JOptionPane.showMessageDialog(null, "Check below for what colors on screen mean");
        if (JOptionPane.showConfirmDialog(null,
                "Would you like to proceed?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (firstRun) {
                firstRun = false;
            } else {
                aiPanel.setHuman(new Human("0", aiPanel,
                        null));
                aiPanel.human.basicSimulation();
            }
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Driver window = new Driver();
        window.setVisible(true);
        dialogue();
        Thread thread = new Thread(() -> {
            System.out.println("Thread running");
            while (true) {
                if (!generation.equals(Human.name)) {
                    generation = Human.name;
                    window.repaint();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        aiPanel.human.basicSimulation();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int y = this.getHeight() - 25;
        int x = 10;
        Font font = new Font("Ariel", Font.BOLD, 14);
        /*
          Haven't put much thought into lines below
        */
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Undiscovered", x, y);
        x += 12 * 10;
        g.setColor(Color.RED);
        g.drawString("Unreachable", x, y);
        x += 11 * 10;
        g.setColor(Color.YELLOW);
        g.drawString("Spawn", x, y);
        x += 5 * 10;
        g.setColor(Color.GREEN);
        g.drawString("Path", x, y);
        x += 4 * 10;
        g.setColor(Color.BLUE);
        g.drawString("Objective", x, y);
        x += 8 * 10;
        g.setColor(Color.ORANGE);
        g.drawString("Generation:" + generation, x, y);
        //g.drawString("Black:Undiscovered, Red:Unreachable, Yellow:Spawn, Green:Path, Blue:Objective ",x,y);
    }
}