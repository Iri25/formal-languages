package lab6.model;

public class TS {

    private final String symbol;
    private final Integer codTS;
    public static Integer cod = 18;

    public TS(String symbol, Integer codTS) {
        this.symbol = symbol;
        this.codTS = codTS;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getCodTS() {
        return codTS;
    }

    @Override
    public String toString() {
        return "TS{" +
                "symbol='" + symbol + '\'' +
                ", codTS=" + codTS +
                '}';
    }
}
