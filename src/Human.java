import java.util.ArrayList;

class Human {
    private String sex;
    private String name;
    private boolean isDead;
    private int[][] map;
    private int[] currentLocation;
    private int[][] memory;

    //2 constructors because this class both creates humans and simulates them
    Human() {
    }

    private Human(String name) {
        this.name = name;
        this.sex = (Math.random() > 0.5) ? "male" : "female";
        this.map = Maps.map;
        //map is always a rectangular shape
        //humans always believe it can move everywhere on map unless it inherits it's ancestor's memory
        memory = new int[map.length][map[0].length];
        currentLocation = new int[1];
        ArrayList<int[]> spawnPoints = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 2) {
                    spawnPoints.add(new int[]{i, j});
                }
            }
        }
        currentLocation = spawnPoints.get((int) (Math.random() * spawnPoints.size()));
    }

    public int[][] getMemory() {
        return memory;
    }

    public void setMemory(int[][] memory) {
        this.memory = memory;
    }

    void simulate() {

    }
    //todo human will check it's memory and can move to
    // South,West,North and East if memory does not point that it will harm it
    // Also it will not go to spawn points which are dead ends
     private void move(){

    }

    private void printHuman() {
        System.out.printf("%s: sex:%s\n"
                , name, sex);
    }

    private void healthCheck() {
        if (map[currentLocation[0]][currentLocation[1]] == 10) isDead = true;
    }

    private void basicSimulation() {
        while (!this.isDead) {
            this.healthCheck();
        }
        System.out.println("----------------------------");
        System.out.println(this.name + " is dead");
        this.printHuman();
        System.out.println("----------------------------");
    }
}
