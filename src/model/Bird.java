package model;

public class Bird extends Animal {

    protected boolean hasteeth;

    public Bird(int age, String breed, Gender gender, boolean hasteeth) {
        super("Bird", age, breed, gender);
        this.hasteeth = hasteeth;
    }

    @Override
    public void makeSound() {
        System.out.println("chirps: Tweet!");
    }

    @Override
    public void eat() {
        System.out.println("Bird eats seeds");
    }

    public void sing() {
        System.out.println("turip-ip-ip-ip-turip..");
    }

}