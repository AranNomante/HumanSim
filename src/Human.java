import java.util.ArrayList;
import java.util.Arrays;

class Human {
    static String name = "0";
    private static String[] directions = new String[]{"up", "down", "left", "right"};
    private static int time = 1000;
    private boolean objectiveCompleted = false;
    private String sex;
    private boolean isDead;
    private int[][] map;
    private int[] currentLocation;
    private int[][] memory;
    private int movablesCount;
    private AiPanel aiPanel;

    Human(String name, AiPanel aiPanel, int[][] memory) {
        Human.name = name;
        this.sex = (Math.random() > 0.5) ? "male" : "female";
        this.map = Maps.map;
        this.aiPanel = aiPanel;
        //counts movable locations
        for (int[] rows :
                map) {
            for (int k :
                    rows) {
                if (k != 0) movablesCount++;
            }
        }
        //map is always a rectangular shape
        //humans always believe it can move everywhere on map unless it inherits it's ancestor's memory
        if (memory == null) {
            this.memory = new int[map.length][map[0].length];
            for (int[] ints : this.memory) {
                for (int i = 0; i < ints.length; i++) {
                    ints[i] = 5;
                }
            }
        } else {
            this.memory = memory;
        }
        currentLocation = new int[1];
        //spawns human at a random point
        ArrayList<int[]> spawnPoints = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 2) {
                    spawnPoints.add(new int[]{i, j});
                }
            }
        }
        currentLocation = spawnPoints.get((int) (Math.random() * spawnPoints.size()));
        this.memory[currentLocation[0]][currentLocation[1]] = 2;
        aiPanel.setTiles(this.memory);
        System.out.println(Arrays.toString(currentLocation));
    }

    int[] getCurrentLocation() {
        return currentLocation;
    }

    int[][] getMemory() {
        return memory;
    }


    //moves if movable and checks status
    private void move() {
        String selection = directions[(int) (Math.random() * directions.length)];
        boolean okPath = availablePath(selection);
        while (!okPath) {
            selection = directions[(int) (Math.random() * directions.length)];
            okPath = availablePath(selection);
        }
        switch (selection) {
            case "up":
                currentLocation[0] = currentLocation[0] - 1;
                break;
            case "down":
                currentLocation[0] = currentLocation[0] + 1;
                break;
            case "left":
                currentLocation[1] = currentLocation[1] - 1;
                break;
            case "right":
                currentLocation[1] = currentLocation[1] + 1;
                break;
        }
        healthCheck();
    }

    //checks path availability will return true as long as there is a path and it is not marked 0 in the memory
    //todo improve this algorithm to prevent wandering around and make human follow the path that leads to
    // unknown while trying to find 3
    private boolean availablePath(String selection) {
        switch (selection) {
            case "up":
                if (currentLocation[0] - 1 < 0) return false;
                else return memory[currentLocation[0] - 1][currentLocation[1]] != 0;
            case "down":
                if (currentLocation[0] + 1 >= map.length) return false;
                else return memory[currentLocation[0] + 1][currentLocation[1]] != 0;
            case "left":
                if (currentLocation[1] - 1 < 0) return false;
                else return memory[currentLocation[0]][currentLocation[1] - 1] != 0;
            case "right":
                if (currentLocation[1] + 1 >= map[currentLocation[0]].length) return false;
                else return memory[currentLocation[0]][currentLocation[1] + 1] != 0;
            default:
                return false;
        }
    }

    private void printHuman() {
        System.out.printf("%s: sex:%s\n"
                , name, sex);
    }

    //checks if stepped on 0 or 3 and else then updates
    private void healthCheck() {
        if (map[currentLocation[0]][currentLocation[1]] == 0) {
            isDead = true;
            memory[currentLocation[0]][currentLocation[1]] = map[currentLocation[0]][currentLocation[1]];
        } else {
            memory[currentLocation[0]][currentLocation[1]] = map[currentLocation[0]][currentLocation[1]];
            if (memory[currentLocation[0]][currentLocation[1]] == 3) {
                System.out.println(name + " found the exit");
                objectiveCompleted = true;
            }
        }
    }

    void basicSimulation() {
        aiPanel.repaint();
        while (!this.isDead && !this.objectiveCompleted) {
            try {
                Thread.sleep(time);
                this.move();
                aiPanel.repaint();
                if (time > 10) time -= 10;
                System.out.println("timeToWait:" + time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("----------------------------");
        if (isDead) {
            System.out.println(name + " is dead");
            int count = 0;
            for (int[] rows :
                    memory) {
                for (int i :
                        rows) {
                    if (i != 0 && i != 5) count++;
                }
            }
            int rate = (int) ((count / (double) movablesCount) * 100);
            System.out.println("Match rate is: " + rate);
            printHuman();
            printInfo();
            aiPanel.setHuman(new Human("" + (Integer.parseInt(name) + 1), aiPanel, this.getMemory()));
            aiPanel.human.basicSimulation();
        } else {
            printHuman();
            printInfo();
            Driver.dialogue();
        }
    }

    private void printInfo() {
        System.out.println("-------------");
        System.out.println("Memory of " + name);
        for (int[] memLoc :
                memory) {
            System.out.println(Arrays.toString(memLoc));
        }
        System.out.println("-------------");
        System.out.println("Map");
        for (int[] mapLoc :
                map) {
            System.out.println(Arrays.toString(mapLoc));
        }
        System.out.println("-------------");
    }
}
