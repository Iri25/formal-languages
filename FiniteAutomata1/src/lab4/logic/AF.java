package lab4.logic;

import lab4.model.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class AF {

    private List<String> alphabeticalStates = new ArrayList<>();
    private HashSet<String> inputAlphabet = new HashSet<>();
    private List<Transaction> transactions = new ArrayList<>();
    private String initialState;
    private List<String> finalStates = new ArrayList<>();

    /**
     * Default constructor
     */
    public AF() {}

    /**
     * Constructors with parameters
     * @param alphabeticalStates - the alphabetical states
     * @param inputAlphabet - the input alphabet
     * @param transactions - the transactions from AF
     * @param initialState - the initial state of automatic
     * @param finalState - the final state of automatic
     */
    public AF(List<String> alphabeticalStates, HashSet<String> inputAlphabet, List<Transaction> transactions, String initialState, List<String> finalState) {
        this.alphabeticalStates = alphabeticalStates;
        this.inputAlphabet = inputAlphabet;
        this.transactions = transactions;
        this.initialState = initialState;
        this.finalStates = finalState;
    }

    /**
     * Constructor with parameter
     * @param fileName - the name of the input file
     */
    public AF(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String[] line;

            // reading alphabetical states
            line = bufferedReader.readLine().split(" ");
            Collections.addAll(alphabeticalStates, line);

            // reading each type of alphabet
            List<Integer> types = new ArrayList<>();
            line = bufferedReader.readLine().split(" ");
            for (String type : line) {
                types.add(Integer.parseInt(type));
            }

            // setting the initial and final state
            for (int i = 0; i < alphabeticalStates.size(); i++) {
                if (types.get(i) == 1) {
                    initialState = alphabeticalStates.get(i);
                }
                if (types.get(i) == 2) {
                    finalStates.add(alphabeticalStates.get(i));
                }
            }

            // reading transactions
            int numberOfTransaction = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < numberOfTransaction; i++) {
                line = bufferedReader.readLine().split(" ");
                transactions.add(new Transaction(line[2], line[0], line[1]));
                inputAlphabet.add(line[2]);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAlphabeticalStates() {
        return alphabeticalStates;
    }

    public HashSet<String> getInputAlphabet() {
        return inputAlphabet;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    /**
     * Function to check if a sequence is valid in AF
     * @param sequence - the sequence to check
     * @return true - if sequence is valid
     *         false - if sequence isn't valid
     */
    public boolean checkSequence(String sequence) {

        String initialSate = this.initialState;
        String finalState = "";
        boolean ok = true;

        // iterating until the prefix isn't coincide with one a valid transaction.
        for (int i = 0; i < sequence.length() && ok; i++) {

            // check character with character
            String character = sequence.substring(i, i + 1), next = "";

            for (Transaction transaction : transactions) {
                if (transaction.getInitialState().equals(initialSate) && transaction.getValue().equals(character)) {
                    next = transaction.getFinalState();
                    break;
                }
            }
            if (next.equals("")) ok = false;

            if (finalStates.contains(next) && i == sequence.length() - 1) {
                ok = true;
                finalState = next;
                break;
            }

            // we move on to the next initial and final state
            initialSate = next;
            finalState = next;
        }

        // we check if the final state is the true final state
        if (!finalStates.contains(finalState))
            ok = false;
        return ok;
    }

    /**
     * Function for the longest prefix in a sequence
     * @param sequence - the given sequence
     * @return the longest prefix in a sequence that is accepted by AF
     */
    public String prefix(String sequence) {

        String initialState = this.initialState;
        String finalState = "";
        boolean ok = true;
        String result = "";
        String backup = "";

        // iterating until the prefix isn't coincide with one a valid transaction.
        for (int i = 0; i < sequence.length() && ok; i++) {

            // check character with character
            String character = sequence.substring(i, i + 1), next = "";

            for (Transaction transaction : transactions) {
                if (transaction.getInitialState().equals(initialState) && transaction.getValue().equals(character)) {
                    next = transaction.getFinalState();
                    result += transaction.getValue();
                    break;
                }
            }
            // if we find no state, then we stop
            if (next.equals("")) {
                ok = false;
                break;
            }

            // if the prefix finish and the last one character is final state, then we stop
            if (finalStates.contains(next)) {
                backup = result;
            }

            if (finalStates.contains(next) && i == sequence.length() - 1) {
                ok = true;
                finalState = next;
                break;
            }

            // we move on to the next initial state and retain the final state
            initialState = next;
            finalState = next;
        }

        // we check if the final state is the true final state
        if (!finalStates.contains(finalState))
            return backup;
        return result;
    }

}
