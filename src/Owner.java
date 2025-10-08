/*
 * Created on 2025-10-03
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

public class Owner {

    private String name;
    private Cat cat;

    public Owner(String name) {
        this(name, null);
    }

    public Owner(String name, Cat ownedCat) {
        this.name = name;
        this.cat = ownedCat;
    }

    // Getters & Setters
    public String getName() {
        return this.name;
    }

    public Cat getCat() {
        return this.cat;
    }

    public void setCat(Cat cat) {
        if (cat == null) {
            System.out.println("Cannot assign a null cat to " + name + ".");
            return;
        }

        if (this.cat == null) {
            this.cat = cat;
        } else {
            System.out.println(name + " already owns a cat: " + this.cat.getName() + ".");
        }
    }

    // Owner behavior
    public void adoptCat(Cat cat) {
        if (cat == null) {
            System.out.println(name + " cannot adopt a null cat.");
            return;
        }

        if (this.cat != null) {
            System.out.println(name + " already owns a cat: " + this.cat.getName() + ".");
            return;
        }

        if (!cat.isAdoptable()) {
            System.out.println(name + " cannot adopt " + cat.getName() +
                    " because it is already owned by " + cat.getOwner().getName() + ".");
            return;
        }

        // Assign new ownership
        this.cat = cat;
        cat.assignOwner(this);

        System.out.println(name + " just adopted " + cat.getName() + ".");
    }

    @Override
    public String toString() {
        return String.format("Owner{name='%s', cat=%s}", name,
                (cat != null ? cat.getName() : "none"));
    }
}
