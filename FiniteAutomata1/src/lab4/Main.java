package lab4;

import lab4.logic.AF;
import lab4.model.Transaction;

import java.util.*;

public class Main {

    public static void menu(){
        System.out.println();
        System.out.println("1. Display the alphabet of states");
        System.out.println("2. Display the input alphabet");
        System.out.println("3. Display the transactions sets");
        System.out.println("4. Display the final states ");
        System.out.println("5. Check sequence validity in AF");
        System.out.println("6. The longest valid prefix in AF");
        System.out.println("0. Exit");
        System.out.println();
    }

    public static void main(String[] args) {

        AF af = new AF();

        System.out.println("1. Reading keyboard data");
        System.out.println("2. Read data from the file");
        System.out.println();
        System.out.print("Enter the command: ");

        int command = new Scanner(System.in).nextInt();
        if (command == 1) {
            List<String> alphabeticalStates = new ArrayList<>();
            HashSet<String> inputAlphabet = new HashSet<>();
            List<Transaction> transactions = new ArrayList<>();
            String initialState = "";
            List<String> finalState = new ArrayList<>();

            String[] line;

            // reading the alphabetical states
            System.out.print("Enter the alphabet of states (each element separated by space): ");
            line = new Scanner(System.in).nextLine().split(" ");
            Collections.addAll(alphabeticalStates, line);

            // reading the type of each element of the alphabet
            System.out.print("Enter the type of each element of the state alphabet " +
                    "(1 - initial state, 2 - final state, 0 - the rest): ");
            List<Integer> types = new ArrayList<>();
            line = new Scanner(System.in).nextLine().split(" ");
            for (String type : line) {
                types.add(Integer.parseInt(type));
            }

            // setting the initial and final state
            for (int i = 0; i < alphabeticalStates.size(); i++) {
                if (types.get(i) == 1) {
                    initialState = alphabeticalStates.get(i);
                }
                if (types.get(i) == 2) {
                    finalState.add(alphabeticalStates.get(i));
                }
            }

            // reading the transactions
            System.out.print("Enter the number of transaction: ");
            int numberOfTransaction = new Scanner(System.in).nextInt();
            System.out.println("Enter transactions (<initial status, final status, value>): ");
            for (int i = 0; i < numberOfTransaction; i++) {
                line = new Scanner(System.in).nextLine().split(" ");
                transactions.add(new Transaction(line[2], line[0], line[1]));
                inputAlphabet.add(line[2]);
            }

            af = new AF(alphabeticalStates, inputAlphabet, transactions, initialState, finalState);
        }
        if (command == 2) {
            af = new AF("src/lab4.File/file3.txt");
        }


        while (true) {
            menu();
            System.out.print("Enter the command: ");
            command = new Scanner(System.in).nextInt();

            if (command == 1) {
                System.out.print("The alphabetical states is: ");
                for (String state : af.getAlphabeticalStates()) {
                    System.out.print(state + " ");
                }
            }
            else if (command == 2) {
                System.out.print("The input alphabet is: ");
                for (String input : af.getInputAlphabet()) {
                    System.out.print(input + " ");
                }
            }
            else if (command == 3) {
                System.out.println("The set of transactions is: ");
                for (Transaction t : af.getTransactions()) {
                    System.out.println(t.getInitialState() + " --" + t.getValue() + "--> " + t.getFinalState());
                }
            }
            else if (command == 4) {
                System.out.print("The final states is: ");
                for (String stare : af.getFinalStates()) {
                    System.out.print(stare);
                }
            }
            else if (command == 5) {
                System.out.print("Enter the sequence: ");
                String sequence = new Scanner(System.in).next();
                if (af.checkSequence(sequence)) System.out.print("The sequence is accepted!");
                else System.out.print("The sequence isn't accepted!");
            }
            else if (command == 6) {
                System.out.print("Enter the sequence: ");
                String sequence = new Scanner(System.in).next();
                System.out.print("The longest prefix accepted by AF is: " + af.prefix(sequence));
            }
            else if (command == 0) {
                break;
            }
            else {
                System.out.print("Invalid command!");
            }
            System.out.println();
        }
    }
}
