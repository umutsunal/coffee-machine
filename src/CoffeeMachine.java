import java.util.Scanner;

public class CoffeeMachine {

    private int moneyInDollars;
    private int waterAmountInMilliliters;
    private int milkAmountInMilliliters;
    private int coffeeBeansAmountInGrams;
    private int disposableCupAmount;

    public CoffeeMachine(){
        this.moneyInDollars = 550;
        this.waterAmountInMilliliters = 400;
        this.milkAmountInMilliliters = 540;
        this.coffeeBeansAmountInGrams = 120;
        this.disposableCupAmount = 9;
    }

    private enum Coffee {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        private final int waterAmount;
        private final int milkAmount;
        private final int coffeeBeansAmount;
        private final int moneyAmount;

        Coffee(int waterAmount, int milkAmount, int coffeeBeansAmount, int moneyAmount) {
            this.waterAmount = waterAmount;
            this.milkAmount = milkAmount;
            this.coffeeBeansAmount = coffeeBeansAmount;
            this.moneyAmount = moneyAmount;
        }

        public int getWaterAmount() {
            return waterAmount;
        }

        public int getMilkAmount() {
            return milkAmount;
        }

        public int getCoffeeBeansAmount() {
            return coffeeBeansAmount;
        }

        public int getMoneyAmount() {
            return moneyAmount;
        }
    }

    public static void main(String[] args) {
        run();
    }

    private static void run(){
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        while(true){
            decideAction(scanner, coffeeMachine);
        }

    }

    private static void printMachineIngredientsStatus(CoffeeMachine coffeeMachine){
        System.out.println("The coffee machine has:\n" +
                + coffeeMachine.getWaterAmountInMilliliters() + " ml of water\n" +
                + coffeeMachine.getMilkAmountInMilliliters() + " ml of milk\n" +
                + coffeeMachine.getCoffeeBeansAmountInGrams() + " g of coffee beans\n" +
                + coffeeMachine.getDisposableCupAmount() + " disposable cups\n" +
                "$" + coffeeMachine.getMoneyInDollars() + " of money\n");
    }

    private static int[] enterMachineIngredients(Scanner scanner){
        int[] result = new int[4];

        System.out.println("Write how many ml of water you want to add: ");
        result[0] = getIntInput(scanner);
        System.out.println("Write how many ml of milk you want to add: ");
        result[1] = getIntInput(scanner);
        System.out.println("Write how many grams of coffee beans you want to add: ");
        result[2] = getIntInput(scanner);
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        result[3] = getIntInput(scanner);

        return result;
    }

    private static int getIntInput(Scanner scanner){
        int intInput = scanner.nextInt();
        return intInput;
    }

    private static String getStringInput(Scanner scanner){
        String stringInput = scanner.nextLine();
        return stringInput;
    }

    private static String getActionInput(Scanner scanner){
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String actionInput = getStringInput(scanner);
        return actionInput;
    }

    private static void decideAction(Scanner scanner, CoffeeMachine coffeeMachine){
        String action = getActionInput(scanner);

        if ("buy".equals(action)){
            buy(scanner, coffeeMachine);
        }
        else if ("fill".equals(action)){
            fill(scanner, coffeeMachine);
        }
        else if ("take".equals(action)){
            take(coffeeMachine);
        }
        else if ("remaining".equals(action)){
            printMachineIngredientsStatus(coffeeMachine);
        }
        else if ("exit".equals(action)){
            System.exit(0);
        }
        else{
            System.out.println("Unknown action \"" + action + "\".\n");
        }
    }

    private static void buy(Scanner scanner, CoffeeMachine coffeeMachine) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffeeTypeInput = getStringInput(scanner);

        if (coffeeTypeInput.equals("1")) {
            buyCoffeeFromMachine(coffeeMachine, Coffee.ESPRESSO);
        }
        else if (coffeeTypeInput.equals("2")){
            buyCoffeeFromMachine(coffeeMachine, Coffee.LATTE);
        }
        else if (coffeeTypeInput.equals("3")){
            buyCoffeeFromMachine(coffeeMachine, Coffee.CAPPUCCINO);
        }
        else if (coffeeTypeInput.equals("back")){
            return;
        }
        else{
            System.out.println("Unknown coffee type \"" + coffeeTypeInput + "\".\n");
        }
    }

    private static void buyCoffeeFromMachine(CoffeeMachine coffeeMachine, Coffee coffee) {
        if (coffeeMachine != null && coffee != null) {
            if (coffeeMachine.getWaterAmountInMilliliters() >= coffee.getWaterAmount() && coffeeMachine.getMilkAmountInMilliliters() >= coffee.getMilkAmount()
                    && coffeeMachine.getCoffeeBeansAmountInGrams() >= coffee.getCoffeeBeansAmount() && coffeeMachine.getDisposableCupAmount() > 0) {

                System.out.println("I have enough resources, making you a coffee!\n");
                coffeeMachine.setWaterAmountInMilliliters(coffeeMachine.getWaterAmountInMilliliters() - coffee.getWaterAmount());
                coffeeMachine.setMilkAmountInMilliliters(coffeeMachine.getMilkAmountInMilliliters() - coffee.getMilkAmount());
                coffeeMachine.setCoffeeBeansAmountInGrams(coffeeMachine.getCoffeeBeansAmountInGrams()- coffee.getCoffeeBeansAmount());
                coffeeMachine.setDisposableCupAmount(coffeeMachine.getDisposableCupAmount() - 1);
                coffeeMachine.setMoneyInDollars(coffeeMachine.getMoneyInDollars() + coffee.getMoneyAmount());
            }
            else if (coffeeMachine.getWaterAmountInMilliliters() < coffee.getWaterAmount()){
                System.out.println("Sorry, not enough water!");
            }
            else if (coffeeMachine.getMilkAmountInMilliliters() < coffee.getMilkAmount()){
                System.out.println("Sorry, not enough milk!");
            }
            else if (coffeeMachine.getCoffeeBeansAmountInGrams() < coffee.getCoffeeBeansAmount()){
                System.out.println("Sorry, not enough coffee beans!");
            }
            else if (coffeeMachine.getDisposableCupAmount() < 1){
                System.out.println("Sorry, not enough disposable cups!");
            }
        }
    }

    private static void fill(Scanner scanner, CoffeeMachine coffeeMachine) {
        int[] result = enterMachineIngredients(scanner);
        coffeeMachine.setWaterAmountInMilliliters(coffeeMachine.getWaterAmountInMilliliters() + result[0]);
        coffeeMachine.setMilkAmountInMilliliters(coffeeMachine.getMilkAmountInMilliliters() + result[1]);
        coffeeMachine.setCoffeeBeansAmountInGrams(coffeeMachine.getCoffeeBeansAmountInGrams() + result[2]);
        coffeeMachine.setDisposableCupAmount(coffeeMachine.getDisposableCupAmount() + result[3]);
    }

    private static void take(CoffeeMachine coffeeMachine) {
        System.out.println("I gave you $" + coffeeMachine.getMoneyInDollars() + "\n");
        coffeeMachine.setMoneyInDollars(0);
    }


    public int getMoneyInDollars() {
        return moneyInDollars;
    }

    public void setMoneyInDollars(int moneyInDollars) {
        this.moneyInDollars = moneyInDollars;
    }

    public int getWaterAmountInMilliliters() {
        return waterAmountInMilliliters;
    }

    public void setWaterAmountInMilliliters(int waterAmountInMilliliters) {
        this.waterAmountInMilliliters = waterAmountInMilliliters;
    }

    public int getMilkAmountInMilliliters() {
        return milkAmountInMilliliters;
    }

    public void setMilkAmountInMilliliters(int milkAmountInMilliliters) {
        this.milkAmountInMilliliters = milkAmountInMilliliters;
    }

    public int getCoffeeBeansAmountInGrams() {
        return coffeeBeansAmountInGrams;
    }

    public void setCoffeeBeansAmountInGrams(int coffeeBeansAmountInGrams) {
        this.coffeeBeansAmountInGrams = coffeeBeansAmountInGrams;
    }

    public int getDisposableCupAmount() {
        return disposableCupAmount;
    }

    public void setDisposableCupAmount(int disposableCupAmount) {
        this.disposableCupAmount = disposableCupAmount;
    }


}
