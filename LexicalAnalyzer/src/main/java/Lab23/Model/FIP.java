package Lab23.Model;

public class FIP {

    private final Integer codAtom;
    private final Integer codTS;

    public FIP(Integer codAtom, Integer codTS) {
        this.codAtom = codAtom;
        this.codTS = codTS;
    }

    public static Boolean checkID(String word) {

        return word.matches("[a-zA-Z]+") && word.length() <= 8;
    }

    public static Boolean checkCONST(String word) {

        return word.matches("[0-9]{1,12}(\\.[0-9]*)?");
    }

    @Override
    public String toString() {
        return "FIP{" +
                "codAtom=" + codAtom +
                ", codTS=" + codTS +
                '}';
    }
}
