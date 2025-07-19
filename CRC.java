import java.util.Scanner;

public class CRC {
    
    // Function to perform XOR operation
    public static String XOR(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < b.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                result.append("0");
            } else {
                result.append("1");
            }
        }
        return result.toString();
    }

    // Function to perform CRC Division
    public static String CRC_Divide(String data, String polynomial) {
        int m = polynomial.length();
        int n = data.length();
        
        // Append (m-1) zeros to the data
        String paddedData = data + "0".repeat(m - 1);
        
        // Get first m bits of the padded data as remainder
        String remainder = paddedData.substring(0, m);

        // Perform division
        for (int i = m; i <= n + m - 1; i++) {
            if (remainder.charAt(0) == '1') {
                remainder = XOR(remainder, polynomial) + (i < paddedData.length() ? paddedData.charAt(i) : "");
            } else {
                remainder = XOR(remainder, "0".repeat(m)) + (i < paddedData.length() ? paddedData.charAt(i) : "");
            }
            remainder = remainder.substring(1); // Remove the first bit
        }

        return remainder; // Return CRC (last m-1 bits)
    }

    // Function to calculate CRC
    public static String Calculate_CRC(String data, String polynomial) {
        return CRC_Divide(data, polynomial);
    }

    // Function to check if received data has errors
    public static boolean Check_CRC(String data, String polynomial) {
        String remainder = CRC_Divide(data, polynomial);
        return remainder.equals("0".repeat(polynomial.length() - 1)); // True if no errors
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get data from user
        System.out.print("Enter the data (binary): ");
        String data = scanner.next();

        // Get polynomial from user
        System.out.print("Enter the CRC polynomial (binary): ");
        String polynomial = scanner.next();
        
        // Calculate CRC
        String crc = Calculate_CRC(data, polynomial);
        System.out.println("CRC Code: " + crc);

        // Create transmitted data (data + CRC)
        String dataWithCRC = data + crc;
        System.out.println("Data with CRC: " + dataWithCRC);

        // Check if received data is valid
        if (Check_CRC(dataWithCRC, polynomial)) {
            System.out.println("Data received correctly (no errors detected).");
        } else {
            System.out.println("Data received with errors.");
        }

        scanner.close();
    }
}