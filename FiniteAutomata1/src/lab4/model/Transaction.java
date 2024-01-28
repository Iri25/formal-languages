package lab4.model;

public class Transaction {

    private final String value;
    private final String initialState;
    private final String finalState;

    /**
     * Constructor with parameters
     * @param value - the value
     * @param initialState - the initial state of automatic
     * @param finalState - the final state of automatic
     */
    public Transaction(String value, String initialState, String finalState) {
        this.value = value;
        this.initialState = initialState;
        this.finalState = finalState;
    }

    /**
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @return the initial state
     */
    public String getInitialState() {
        return initialState;
    }

    /**
     *
     * @return the final State
     */
    public String getFinalState() {
        return finalState;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "value='" + value + '\'' +
                ", initialState='" + initialState + '\'' +
                ", finalState='" + finalState + '\'' +
                '}';
    }
}
