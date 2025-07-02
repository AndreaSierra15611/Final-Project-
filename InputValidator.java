import java.util.Scanner;

// this declares the Input Validator class 
public class InputValidator 
{
    // this scanner method allow a user to input infromation
    private static final Scanner scanner = new Scanner(System.in);

    public static int getValidInteger(String prompt, int min, int max) 
    {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try 
        {
            // this wil ensure the use input a value between 1-4 or it will print out a error
            int value = Integer.parseInt(input);
            if (value < min || value > max) 
            {
                throw new IllegalArgumentException("Input out of range (" + min + " to " + max + ").");
            }
            return value;
        } catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
            return getValidInteger(prompt, min, max); 
        }
    }

    public static String getNonEmptyString(String prompt) 
    {
        // this will esure the user enters something into the system
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) 
        {
            System.out.println("Input cannot be empty.");
            return getNonEmptyString(prompt);
        }
        return input;
    }
}