import java.util.Scanner;

public class CRCImplementation {
    // XOR function to perform bitwise XOR between two binary strings
    public static String xor(String a, String b) {
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

    // CRC division function to calculate the remainder (CRC)
    public static String crcDivide(String data, String polynomial) {
        int m = polynomial.length();
        int n = data.length();
        // Append (m-1) zeros to the data
        String paddedData = data + "0".repeat(m - 1);
        String remainder = paddedData.substring(0, m); // Get first m bits

        // Perform the division process
        for (int i = m; i <= n + m - 2; i++) {
            if (remainder.charAt(0) == '1') {
                remainder = xor(remainder, polynomial) + paddedData.charAt(i);
            } else {
                remainder = xor(remainder, "0".repeat(m)) + paddedData.charAt(i);
            }
            remainder = remainder.substring(1); // Remove the first bit after XOR
        }

        // Final XOR if remainder starts with 1
        if (remainder.charAt(0) == '1') {
            remainder = xor(remainder, polynomial);
        }

        // Return the last (m-1) bits as the CRC
        return remainder.substring(1, m);
    }

    // Function to calculate the CRC
    public static String calculateCRC(String data, String polynomial) {
        return crcDivide(data, polynomial);
    }

    // Function to check if the received data (data + CRC) is valid
    public static boolean checkCRC(String data, String polynomial) {
        String remainder = crcDivide(data, polynomial);
        // If remainder is all zeros, data is valid
        return remainder.equals("0".repeat(polynomial.length() - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input from user
        System.out.println("Enter the data (binary):");
        String data = scanner.nextLine();

        System.out.println("Enter the CRC polynomial (binary):");
        String polynomial = scanner.nextLine();

        // Calculate the CRC
        String crc = calculateCRC(data, polynomial);
        System.out.println("CRC code: " + crc);

        // Append CRC to the original data
        String dataWithCRC = data + crc;
        System.out.println("Data with CRC: " + dataWithCRC);

        // Check if the received data is valid
        if (checkCRC(dataWithCRC, polynomial)) {
            System.out.println("Data received correctly (no errors detected).");
        } else {
            System.out.println("Data received with errors.");
        }

        scanner.close();
    }
}