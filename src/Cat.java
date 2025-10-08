/*
 * Created on 2025-10-03
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

public class Cat {
    private String name;
    private int age;
    private String sound;
    private String story;

    private Owner owner;
    private boolean isAdoptable = true; // default value

    // Constructors
    public Cat(String name, String sound, int age) {
        this(name, sound, age, "Unknown story", null);
    }

    public Cat(String name, String sound, int age, String story, Owner owner) {
        this.name = name;
        this.age = age;
        this.sound = sound;
        this.story = story;

        if (owner != null) {
            this.owner = owner;
            owner.adoptCat(this);
            this.isAdoptable = false;
        }
    }

    // Getters & Setters
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getSound() {
        return sound;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public String getStory() {
        return this.story;
    }

    public boolean isAdoptable() {
        return this.isAdoptable;
    }

    void assignOwner(Owner owner) {
        this.owner = owner;
        this.isAdoptable = false;
    }

    // Behavior
    public void printFunnyStory() {
        if (story == null || story.equalsIgnoreCase("n/a")) {
            System.out.println(name + " has no story to share.");
            return;
        }
        System.out.printf("Here is a funny story about %s:%n%s%n", name, story);
    }

    @Override
    public String toString() {
        String ownerName = (owner != null) ? owner.getName() : "None";
        return String.format("Cat{name='%s', age=%d, sound='%s', story='%s', owner='%s', adoptable=%b}",
                name, age, sound, story, ownerName, isAdoptable);
    }

}
