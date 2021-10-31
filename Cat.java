public class Cat extends Pet {
int miceCaught;
public Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);
        this.miceCaught = miceCaught >= 0 ? miceCaught : 0;
}
public Cat(String name, double health, int painLevel) {
        this(name, health, painLevel, 0);
}

public int getMiceCaught() {
        return miceCaught;
}
int timeToHealInt;
public int treat() {
        double minutesToHealDouble;
        if (miceCaught < 4) {
                minutesToHealDouble = (double)(painLevel *2)/health;
        }
        else if (miceCaught > 7) {
                minutesToHealDouble = (double)painLevel/health;
        }
        else {
                minutesToHealDouble = (double)painLevel/(health*2);
        }
        timeToHealInt = (int)(minutesToHealDouble + 1);
        heal();
        return timeToHealInt;
}

public void speak() {
        super.speak();
        String meow = "meow ";
        String amountMeow = painLevel > 5 ? meow.repeat(miceCaught).toUpperCase() : meow.repeat(miceCaught);
        System.out.println(amountMeow.toUpperCase());
}

public boolean equals(Object o) {
        if (o instanceof Dog) {
                Cat cat = (Cat) o;
                if (this.getMiceCaught() == cat.getMiceCaught()) {
                        return super.equals(cat);
                }

        }
        return false;
}
}
