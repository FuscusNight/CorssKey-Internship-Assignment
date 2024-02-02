package crosskey.lucian;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MortgageCalculator {

    public static void main(String[] args) {
        String filePath = "prospects.txt";
        readAndCalculatePayments(filePath);

        System.out.println("Press Enter to exit.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readAndCalculatePayments(String filePath) {
            // feed the file into the file reader with the file path, making it a try to catch potential errors
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // Skips the headers otherwise it tries to parse the Column names there
            String line; // Where we'll feed the data from the txt file
                    // Goes line by line till it hits null, ie end of the prospects text file
            while ((line = reader.readLine()) != null) {
                // Skips the empty lines or lines that do not have at least 4 comma-separated values
                if (line.trim().isEmpty() || line.split(",").length < 4) {
                    continue;
                }

                // Splits the line into an array of strings based on commas"," not within quotes, dealing with "Clarence,Andresson" issue, otherwise We'd get ""Clarencé", "Andersson""
                                                                                                // -1 makes it include empty strings, dealing with the loose dot issue at the end
                String[] customerData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // Then we take the customerData array elements we hacked up , removes any excess whitespace and quotes from the customer name
                // and parse the strings representing numbers into their appropriate numeric types.
                String customerName = customerData[0].trim().replace("\"", "");
                double totalLoan = Double.parseDouble(customerData[1].trim());
                double annualInterestRate = Double.parseDouble(customerData[2].trim());
                int termInYears = Integer.parseInt(customerData[3].trim());

                // Now to pass all that customer info into the calculation function
                double monthlyPayment = calculateMonthlyPayment(totalLoan, annualInterestRate, termInYears);
                printCustomerPayment(customerName, totalLoan, termInYears, monthlyPayment);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
        }
    }

    public static void printCustomerPayment(String customerName, double totalLoan, int termInYears, double monthlyPayment) {
        System.out.printf("Prospect %s wants to borrow %.2f€ for a period of %d years and pay %.2f€ each month%n",
                customerName, totalLoan, termInYears, monthlyPayment);
    }

    public static double calculateMonthlyPayment(double totalLoan, double annualInterestRate, int termInYears) {
        double monthlyInterestRate = annualInterestRate / 100 / 12; // Gotta convert the Annual Rate from the file to a monthly one first
        double termInMonths = termInYears * 12; // then turn yearly term into a monthly one from the prospects customer data file

        double monthlyPayment =
                        (totalLoan * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -termInMonths));

        return monthlyPayment;
    }
}
