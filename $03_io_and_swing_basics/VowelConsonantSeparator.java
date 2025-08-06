package $03_io_and_swing_basics;

import java.io.*;

public class VowelConsonantSeparator {
    public static void main(String[] args) {
        // Declare all resources outside the try block so they are visible in 'finally'
        BufferedReader reader = null;
        FileWriter vowelWriter = null;
        FileWriter consonantWriter = null;

        try {
            // Step 2: Initialize all resources inside the try block.
            // A BufferedReader makes reading lines of text from the console efficient.
            reader = new BufferedReader(new InputStreamReader(System.in));
            vowelWriter = new FileWriter("VOWELS.TXT");
            consonantWriter = new FileWriter("CONSONANTS.TXT");

            System.out.print("Enter a string to processs: ");
            String input = reader.readLine();

            // handling the case where the user just hits Enter.
            if (input == null) {
                System.out.println("No input provided. Exiting.");
                return;
            }

            input = input.toLowerCase();

            for (char ch : input.toCharArray()) {
                // Ensuring we are only processing alphabetic characters.
                if (Character.isLetter(ch)) {
                    if ("aeiou".indexOf(ch) != -1) // Same as: if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' ||
                                                   // ch == 'u')
                        vowelWriter.write(ch + " ");
                    else
                        consonantWriter.write(ch + " ");
                }
            }

            System.out.println("\n Processing complete.");
            System.out.println("Vowels have been written to VOWELS.TXT");
            System.out.println("Consonants have been written to CONSONANTS.TXT");

        } catch (IOException e) {
            System.err.println("An error occured: " + e.getMessage());
        } finally {
            // Each close operation is in its own try-catch block. This ensures that
            // if closing one resource fails, the program still attempts to close the others.
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                System.err.println("Error closing BufferedReader: " + e.getMessage());
            }
            try {
                if (vowelWriter != null)
                    vowelWriter.close();
            } catch (IOException e) {
                System.err.println("Error closing vowelWriter: " + e.getMessage());
            }
            try {
                if (consonantWriter != null)
                    consonantWriter.close();
            } catch (IOException e) {
                System.err.println("Error closing consonantWriter: " + e.getMessage());
            }
        }
    }
}
