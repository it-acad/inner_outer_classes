package jom.com.softserve.s3.task1;

class Pizza {

    // Fields representing pizza ingredients
    private String cheese;
    private String meat;
    private String seafood;
    private String vegetable;
    private String mushroom;

    // Private constructor to restrict direct object creation
    private Pizza() {
    }

    public String getCheese() {
        return cheese;
    }

    public String getMeat() {
        return meat;
    }

    public String getSeafood() {
        return seafood;
    }

    public String getVegetable() {
        return vegetable;
    }

    public String getMushroom() {
        return mushroom;
    }
    // Method to create an instance of PizzaBuilder
//	public static PizzaBuilder base() {
//		return new PizzaBuilder();
//	}

    // Define the PizzaBuilder class here

}

// The Oven class is responsible for cooking pizza
class Oven {
    public static Pizza cook() {
        // Create and return an instance of the Pizza class
        return null;
    }
}
