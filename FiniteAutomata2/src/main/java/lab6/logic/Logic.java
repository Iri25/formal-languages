package lab6.logic;

import lab6.model.FIP;
import lab6.model.TAD;
import lab6.model.TS;
import lab6.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {

    /**
     * The function of create table of FIP manual
     * @return FIP table
     */
    public static Map<String, Integer> createTable() {
        Map<String, Integer> table = new HashMap<>();
        table.put("ID", 1);
        table.put("CONST", 2);
        table.put("public", 3);
        table.put("class", 4);
        table.put("Main", 5);
        table.put("{", 6);
        table.put("}", 7);
        table.put("static", 8);
        table.put("void", 9);
        table.put("main", 10);
        table.put("(", 11);
        table.put(")", 12);
        table.put("String", 13);
        table.put("[]", 14);
        table.put("args", 15);
        table.put("int", 16);
        table.put(";", 17);
        table.put("=", 18);
        table.put("+", 19);
        return table;
    }

    /**
     * The function of insert in table of symbols
     * @param symbolTable - symbol table
     * @param word - word to introduce
     * @return position of the word that was entered
     */
    public static Integer insertTS(TAD symbolTable, String word) {

        if (symbolTable.size() == 0) {
            symbolTable.add(0, new TS(word, TS.cod++));
            return 0;
        }
        else if (word.compareTo(symbolTable.get(0).getSymbol()) < 0) {
            symbolTable.add(0, new TS(word, TS.cod++));
            return 0;
        }
        else if (word.compareTo(symbolTable.get(symbolTable.size() - 1).getSymbol()) > 0) {
            symbolTable.add(symbolTable.size(), new TS(word, TS.cod++));
            return symbolTable.size() - 1;
        }
        else {
            int i = 0;
            while (word.compareTo(symbolTable.get(i).getSymbol()) > 0)
                i++;
            symbolTable.add(i, new TS(word, TS.cod++));
            return i;
        }
    }

    /**
     * The function to search for a keyword in the symbol list
     * @param symbolTable - symbol table
     * @param word - word to search
     * @return position of word
     */
    public static int findTS(TAD symbolTable, String word) {
        int position = -1;
        for (int i = 0; i < symbolTable.size(); i++) {
            if (symbolTable.get(i).getSymbol().equals(word)) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * The function to generate FIP table and symbols table
     */
    public static void generate() throws Exception {
        Map<String, Integer> table = createTable();
        List<String> words = Utils.readFile("source.txt");
        List<FIP> fip = new ArrayList<>();
        TAD tad = new TAD(20);
        boolean ok = false;
        for (String word : words) {
            if (table.containsKey(word)) {
                fip.add(new FIP(table.get(word), 0));
                ok = true;
            }
            else {
                int position;
                if (tad.size() == 0)
                    position = insertTS(tad, word);
                else {
                    position = findTS(tad, word);
                    if (position == -1)
                        position = insertTS(tad, word);
                }
                if (FIP.checkID(word))
                    fip.add(new FIP(table.get("ID"), tad.get(position).getCodTS()));
                if (FIP.checkCONST(word))
                    fip.add(new FIP(table.get("CONST"), tad.get(position).getCodTS()));
                ok = true;
            }
            if(!ok)
                throw new Exception("Lexical Error!");
        }

        Utils.writeFile("FIP.txt", (ArrayList<?>) fip);
        Utils.writeFile("TS.txt", tad.getArray());
    }
}
