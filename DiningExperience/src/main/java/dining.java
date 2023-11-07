import java.util.Scanner;
public class dining {

    private static final double BASE_COST = 5.00;
    private static final double DISCOUNT_5_MEALS = 0.10;
    private static final double DISCOUNT_10_MEALS = 0.20;
    private static final double SPECIAL_OFFER_DISCOUNT_50 = 10.00;
    private static final double SPECIAL_OFFER_DISCOUNT_100 = 25.00;
    private static final int MAXIMUM_ORDER_QUANTITY = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display menu
        displayMenu();

        // Get user's meal selections and quantities
        Meal[] meals = getUserMealSelections(scanner);

        // Validate meal availability and quantities
        if (!validateMeals(meals)) {
            return;
        }

        // Calculate total cost
        double totalCost = calculateTotalCost(meals);

        // Apply discounts
        totalCost = applyDiscounts(totalCost);

        // Apply special offers
        totalCost = applySpecialOffers(totalCost);

        // Display order confirmation
        System.out.println("\nOrder Confirmation:");
        for (Meal meal : meals) {
            System.out.println(meal.toString());
        }
        System.out.printf("\nTotal Cost: $%.2f", totalCost);

        // Confirm order
        System.out.println("\nDo you wish to confirm your order? (y/n)");
        String confirmation = scanner.nextLine();
        if (!confirmation.equalsIgnoreCase("y")) {
            System.out.println("Order canceled.");
            return;
        }

        // Display final cost
        System.out.printf("\nYour final cost is: $%.2f", totalCost);
    }

    private static void displayMenu() {
        System.out.println("\nWelcome to the Dining Experience Manager!");
        System.out.println("\nMenu:");
        System.out.println("--------------------------------------------------");
        System.out.println("|   Meal   |   Price   |   Description          |");
        System.out.println("--------------------------------------------------");
        System.out.println("|   1.    |   $10.00  |   Appetizer Sampler     |");
        System.out.println("|   2.    |   $12.00  |   Salad                  |");
        System.out.println("|   3.    |   $18.00  |   Main Course           |");
        System.out.println("|   4.    |   $8.00   |   Dessert                |");
        System.out.println("|   5.    |   $5.00   |   Beverage              |");
        System.out.println("--------------------------------------------------");
        System.out.println("\nPlease enter the meal number and quantity (separated by a space) for each item you wish to order:");
    }

    private static Meal[] getUserMealSelections(Scanner scanner) {
        String input;
        Meal[] meals = new Meal[0];

        do {
            System.out.print("\nEnter your selections: ");
            input = scanner.nextLine();

            String[] selections = input.split(" ");
            for (String selection : selections) {
                if (selection.isEmpty()) {
                    continue;
                }

                int mealNumber = Integer.parseInt(selection.split(" ")[0]);
                int quantity = Integer.parseInt(selection.split(" ")[1]);

                if (mealNumber < 1 || mealNumber > 5 || quantity < 1 || quantity > MAXIMUM_ORDER_QUANTITY) {
                    System.out.println("Invalid meal selection or quantity. Please re-enter.");
                    break;
                }

                Meal meal = new Meal(mealNumber, quantity);
                meals = addMealToArray(meals, meal);
            }
        } while (meals.length == 0);

        return meals;
    }

    private static Meal[] addMealToArray(Meal[] array, Meal meal) {
        Meal[] newArray = new Meal[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[newArray.length - 1] = meal;
        return newArray;
    }

    private static boolean validateMeals(Meal[] meals) {
        // Validate meal availability
        for (Meal meal : meals) {
            if (meal.getMealNumber() < 1 || meal.getMealNumber() > 5) {
                System.out.println("Invalid meal selection. Please re-enter.");
                return false;
            }
        }

        // Validate meal quantities
        for (Meal meal : meals) {
            if (meal.getQuantity() < 1 || meal.getQuantity() > MAXIMUM_ORDER_QUANTITY) {
                System.out.println("Invalid meal quantity. Please re-enter.");
                return false;
            }
        }

        // Return true if all meals are valid
        return true;
    }

    private static double calculateTotalCost(Meal[] meals) {
        double totalCost = 0;

        for (Meal meal : meals) {
            totalCost += meal.getPrice() * meal.getQuantity();
        }

        return totalCost;
    }

    private static double applyDiscounts(double totalCost) {
        if (totalCost > 5 && totalCost <= 10) {
            totalCost -= totalCost * DISCOUNT_5_MEALS;
        } else if (totalCost > 10) {
            totalCost -= totalCost * DISCOUNT_10_MEALS;
        }

        return totalCost;
    }

    private static double applySpecialOffers(double totalCost) {
        if (totalCost > SPECIAL_OFFER_DISCOUNT_50) {
            totalCost -= SPECIAL_OFFER_DISCOUNT_50;
        } else if (totalCost > SPECIAL_OFFER_DISCOUNT_100) {
            totalCost -= SPECIAL_OFFER_DISCOUNT_100;
        }

        return totalCost;
    }

}
