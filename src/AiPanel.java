import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AiPanel extends JPanel {

    Human human;
    private int[][] tiles;

    AiPanel() {
        human = new Human("0", this, null);
        tiles = human.getMemory();
        setSize(tiles[0].length * 50, tiles.length * 50);
        setPreferredSize(new Dimension(tiles[0].length * 50, tiles.length * 50));
        setBackground(Color.darkGray);
    }

    void setTiles(int[][] tiles) {
        this.tiles = tiles;
    }

    void setHuman(Human human) {
        this.human = human;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 40;
        int y = 40;
        for (int i = 0; i < tiles.length; i++) {
            int SIZE = 40;
            for (int j = 0; j < tiles[i].length; j++) {
                int columns = tiles[i][j];
                if (columns == 0) g.setColor(Color.RED);
                else if (columns == 1) g.setColor(Color.GREEN);
                else if (columns == 2) g.setColor(Color.YELLOW);
                else if (columns == 3) g.setColor(Color.BLUE);
                else g.setColor(Color.BLACK);
                g.fillRect(x, y, SIZE, SIZE);
                if (Arrays.equals(human.getCurrentLocation(), new int[]{i, j})) {
                    g.setColor(Color.MAGENTA);
                    g.fillOval(x + 10, y + 10, 20, 20);
                }
                x += SIZE;
            }
            x = 40;
            y += SIZE;
        }
    }

}
