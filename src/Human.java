class Human {
    private double weight;
    private double height;
    private String sex;
    private String name;
    private int age;
    private int hoursLived;
    private boolean wellRested;
    private boolean wellFed;
    private double currency;
    private int skill;
    private boolean isDead;

    //2 constructors because this class both creates humans and simulates their life cycle
    Human() {
    }

    private Human(String name, int skill) {
        this.name = name;
        this.age = 1;
        this.skill = skill;
        this.currency = 0;
        this.height = Math.floor(Math.random() * 20 + 5);
        this.weight = Math.floor(Math.random() * 8 + 2);
        this.sex = (Math.random() > 0.5) ? "male" : "female";
    }

    //skill inheritance chance is 1% objective is to create humans and stop creating them if one has achieved
    //the target skill or a greater skill value in it's life
    void simulate(int targetFactor) {
        Human human = new Human("0", 1);
        while (human.getSkill() < targetFactor) {
            human.basicSimulation();
            if (human.getSkill() < targetFactor) {
                System.out.println(human.getName() + " skill: " + human.getSkill());
                int newFactor = (Math.random() > 0.99) ? human.getSkill() : 1;
                human = new Human("" + (Integer.parseInt(human.getName()) + 1), newFactor);
            }
        }
        System.out.println(human.getName() + " is successful with a skill of: " + human.getSkill());

    }

    private String getName() {
        return name;
    }

    private int getSkill() {
        return skill;
    }

    private void printHuman() {
        System.out.printf("%s: sex:%s, age:%d, weight:%.1f kg, height:%.1f cm, earned currency: %.1f\n"
                , name, sex, age, weight, height, currency);
    }

    private void eat() {
        wellFed = (Math.random() > 0.5);
        weight += (Math.random() > 0.95) ? 0.1 : 0;
        hoursLived += 1;
    }

    private void workout() {
        if (weight < 80) return;
        weight -= (Math.random() > 0.95) ? 0.1 : 0;
        hoursLived += 2;
    }

    private void sleep() {
        wellRested = (Math.random() > 0.7);
        hoursLived += 8;
    }

    private void age() {
        if (age < 19) {
            double fed = (wellFed) ? 0.05 : 0;
            double rested = (wellRested) ? 0.05 : 0;
            height += (Math.random() > (0.95 - fed - rested)) ? 0.11 : 0;
        }
        age = hoursLived / 8765;
    }

    private void work() {
        if (age < 18) return;
        currency += (Math.random() * skill);
        if (age >= 18 && age <= 40) {
            skill += (Math.random() > 0.99) ? 1 : 0;
        }
        hoursLived += 8;
    }

    private void healthCheck() {
        double overweight = (weight > 90) ? 0.0099 : 0;
        double old = (age > 50) ? 0.0900 : 0;
        isDead = Math.random() > (0.9999 - overweight - old);
    }

    private void basicSimulation() {
        double w = this.weight;
        double h = this.height;
        while (!this.isDead) {
            this.work();
            this.eat();
            this.workout();
            this.sleep();
            this.age();
            this.healthCheck();
        }
        System.out.println("----------------------------");
        System.out.println(this.name + " is dead");
        this.printHuman();
        System.out.printf("total weight gain: %.1f, total height gain %.1f\n", this.weight - w, this.height - h);
        System.out.println("----------------------------");
    }
}
