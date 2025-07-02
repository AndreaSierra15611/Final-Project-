public class Main 
{
    // this is the main method that will run first
    public static void main(String[] args) 
    {
        // this allows the program to use the information from the text files
        BarManager manager = new BarManager();
        manager.loadData("availableDrinks.txt", "soldOutDrinks.txt");

        // this will allow the program to run until user input '4' to exit
        boolean running = true;
        while (running) 
        {
            System.out.println("\n--- Bar Inventory Menu ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Drink");
            System.out.println("3. Sell Drink");
            System.out.println("4. Save & Exit");

            int choice = InputValidator.getValidInteger("Choose an option (1-4): ", 1, 4);

            switch (choice) 
            {
                case 1 -> manager.viewInventory();
                case 2 -> manager.addDrink();
                case 3 -> manager.sellDrink();
                case 4 -> 
                {
                    manager.saveData("availableDrinks.txt", "soldOutDrinks.txt");
                    System.out.println("Inventory saved. Goodbye!");
                    running = false;
                }
            }
        }
    }
}