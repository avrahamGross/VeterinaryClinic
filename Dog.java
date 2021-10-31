public class Dog extends Pet {
double droolRate;

public Dog(String name, double health, int painlevel, double droolRate) {
        super(name, health, painlevel);
        this.droolRate = droolRate > 0? droolRate : .5;
}
public Dog(String name, double health, int painlevel) {
        this(name, health, painlevel, 5.0);
}

public double getDroolRate() {
        return droolRate;
}
int timeToHealInt;
public int treat() {
        double minutesToHealDouble;
        if (droolRate < 3.5) {
                minutesToHealDouble = ((double)painLevel*2) /health;
        }
        else if (droolRate >= 3.5 && droolRate <= 7.5) {
                minutesToHealDouble = (double)painLevel/health;
        }
        else {
                minutesToHealDouble = (double)painLevel/(health*2);
        }
        timeToHealInt = (int)(minutesToHealDouble +1);
        heal();
        return timeToHealInt;
}

public void speak() {
        super.speak();
        String barks ="bark ";
        String amountBarks = barks.repeat(painLevel);
        if (painLevel > 5) {
                System.out.println(amountBarks.toUpperCase());
        }
        else{
                System.out.println(amountBarks);
        }
}
public boolean equals(Object o) {
        if (o instanceof Dog)  {
                Dog dog = (Dog) o;
                if (this.droolRate == dog.droolRate) {
                        return super.equals(dog);
                }
        }
        return false;
}
public String toString() {
        return name+", "+health+", "+painLevel+", "+droolRate;
}
}
