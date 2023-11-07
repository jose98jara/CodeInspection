public class Meal {

    private static final double[] prices = {
        10.00,
        12.00,
        18.00,
        8.00,
        5.00
    };

    private int mealNumber;
    private int quantity;

    public Meal(int mealNumber, int quantity) {
        this.mealNumber = mealNumber;
        this.quantity = quantity;
    }

    public int getMealNumber() {
        return mealNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return Meal.prices[mealNumber - 1];
    }

    @Override
    public String toString() {
        return String.format("Meal Number: %d, Quantity: %d", mealNumber, quantity);
    }
}
