public abstract class Pet {
String name;
double health;
int painLevel;

public Pet(String name, double health, int painLevel) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        if (health > 1.0) {
                this.health = 1.0;
        }
        else if (health < 0.0) {
                this.health = 0;
        }
        else {
                this.health = health;
        }

        if (painLevel > 10) {
                this.painLevel = 10;
        }
        else if (painLevel < 1) {
                this.painLevel = 1;
        }
        else {
                this.painLevel = painLevel;
        }
}

public String getName() {
        return name;
}
public double getHealth() {
        return health;
}
public int getPainLevel() {
        return painLevel;
}

public abstract int treat();

public void speak() {
        String helloSpeak = "Hello! My name is " + name;
        if (painLevel > 5) {
                System.out.println(helloSpeak.toUpperCase());
        }
        else{
                System.out.println(helloSpeak);
        }
}

public boolean equals(Object o) {
        if (o instanceof Pet) {
                Pet pet = (Pet) o;
                return this.name.equals(pet.name);
        }
        return false;
}

protected void heal() {
        health = 1.0;
        painLevel = 1;
}
}
