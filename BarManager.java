import java.util.ArrayList;
import java.io.*;

public class BarManager 
{
    // this allows for variety in the list of drinks available 
    private ArrayList<String> availableDrinks = new ArrayList<>();

    //this show how many drinks are sold and caps the sold-out list at 50 drinks
    private String[] soldOutDrinks = new String[50];
    private int soldOutCount = 0;

    // this allows available drinks and sold out drink to be stored into seperate text files 
    public void loadData(String availableFile, String soldOutFile) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(availableFile))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                availableDrinks.add(line);
            }
        } catch (IOException e) 
        {
            System.out.println("Error loading available drinks: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(soldOutFile))) 
        {
            String line;
            while ((line = reader.readLine()) != null && soldOutCount < soldOutDrinks.length) 
            {
                soldOutDrinks[soldOutCount++] = line;
            }
        } catch (IOException e) 
        {
            System.out.println("Error loading sold-out drinks: " + e.getMessage());
        }
    }

    // this saves any changes made to the available and sold out inventory into the text files
    public void saveData(String availableFile, String soldOutFile) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(availableFile))) 
        {
            for (String drink : availableDrinks) 
            {
                writer.write(drink);
                writer.newLine();
            }
        } catch (IOException e) 
        {
            System.out.println("Error saving available drinks: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(soldOutFile))) 
        {
            for (int i = 0; i < soldOutCount; i++) 
            {
                writer.write(soldOutDrinks[i]);
                writer.newLine();
            }
        } catch (IOException e) 
        {
            System.out.println("Error saving sold-out drinks: " + e.getMessage());
        }
    }

    // this allows the user to add cocktails 
    public void addDrink() 
    {
        String drink = InputValidator.getNonEmptyString("Enter the name of the drink to add: ");
        availableDrinks.add(drink);
        System.out.println(drink + " added to inventory.");
    }

    // this allows the user to input what cocktails were sold 
    public void sellDrink() 
    {
        if (availableDrinks.isEmpty()) 
        {
            System.out.println("No drinks available to sell.");
            return;
        }

        // this displays the inventory for sold out drinks
        viewInventory();
        int index = InputValidator.getValidInteger("Enter the index of the drink to sell: ", 0, availableDrinks.size() - 1);

        if (soldOutCount >= soldOutDrinks.length) 
        {
            System.out.println("Sold-out drink storage is full.");
            return;
        }

        String sold = availableDrinks.remove(index);
        soldOutDrinks[soldOutCount++] = sold;
        System.out.println(sold + " has been marked as sold out.");
    }

    public void viewInventory() 
    {
        System.out.println("\nAvailable Drinks:");
        for (int i = 0; i < availableDrinks.size(); i++) 
        {
            System.out.println(i + ": " + availableDrinks.get(i));
        }

        System.out.println("\nSold-Out Drinks:");
        for (int i = 0; i < soldOutCount; i++) 
        {
            System.out.println("- " + soldOutDrinks[i]);
        }
    }
}