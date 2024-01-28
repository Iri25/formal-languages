package model;

import model.binarytree.BinaryTree;
import model.binarytree.Node;
import model.binarytree.NodeChanged;
import model.binarytree.NodeChangedComparator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Scanner {

    private final List<String> codification;
    private final BinaryTree symbolTable;
    private final List<Pair<Integer, Integer>> pif;

    public Scanner() {
        codification = new ArrayList<>();
        symbolTable = new BinaryTree();
        pif = new ArrayList<>();
        this.getCodif();
    }

    public List<Pair<Integer, Integer>> getPif() {
        return pif;
    }

    /**
     * Run the scanner.
     * return: list of errors
     */
    public List<String> run() {
        // prepare list of potential errors
        List<String> errors = new ArrayList<>();

        // parse each line of the file
        int lineNr = 1;
        try {
            for (String line : Files.readAllLines(Paths.get("src/data/source2.txt"))) {
                line = removeExtraSpaces(line);

                if (!line.equals("")) {
                    // tokenize line
                    List<String> tokens = new LinkedList<>(Arrays.asList(line.split(" ")));
                    tokenizeLine(tokens);

                    // identify tokens and log exceptions
                    for (String token : tokens) {
                        try {
                            identify(token);
                        } catch (Exception e) {
                            errors.add("Line " + lineNr + ": Identifier name \"" + token + "\" too long.");
                        } catch (ExceptionInInitializerError e) {
                            errors.add("Line " + lineNr + ": Illegal token \"" + token + "\".");
                        }
                    }
                }

                lineNr++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return errors;
    }

    public void displayPIFReadable(List<Pair<Integer, Integer>> pif) {
        List<Pair<String, String>> pifReadable = new ArrayList<>();
        for (Pair pair : pif) {
            Integer codificationTableCode = (Integer) pair.getKey();
            Integer symbolTablePosition = (Integer) pair.getValue();

            String codificationTableToken = codification.get(codificationTableCode);
            if (symbolTablePosition == -1) {
                pifReadable.add(new Pair<>(codificationTableToken + " " + codificationTableCode, " from Codifier "));
            } else {
                String symbolTableEntry = symbolTable.get(symbolTablePosition);
                pifReadable.add(new Pair<>(codificationTableToken + " " + codificationTableCode, " " + symbolTableEntry));
            }
        }

        System.out.println();
        System.out.println("PIF: " + pifReadable);
        System.out.println();
    }

    private void tokenizeLine(List<String> tokens) {
        for (int i = 24; i < codification.size(); i++) {
            List<String> tokensCopy = new ArrayList<>(tokens);
            String splitter = "";
            if (codification.get(i).equals("+")) {
                splitter += "\\" + codification.get(i);
            } else if (codification.get(i).equals("*")) {
                splitter += "\\" + codification.get(i);
            } else if (codification.get(i).equals("(") || codification.get(i).equals(")")) {
                splitter += "\\" + codification.get(i);
            } else {
                splitter += codification.get(i);
            }

//            System.out.println(tokens);
            for (String part : tokensCopy) {
                //Check the composed ones
                if (splitter.equals("=") && part.length() != splitter.length()) {
                    splitEqual(part, tokens);
                } else if (splitter.equals(":") && part.length() != 1) {
                    splitColon(part, tokens);
                } else if (splitter.equals("<") && part.length() != 1) {
                    spitLess(part, tokens);
                } else if (splitter.equals(">") && part.length() != 1) {
                    splitGreater(part, tokens);
                } else if (splitter.equals("-") && part.length() != 1) {
                    splitMinus(part, tokens);
                }
                else if (splitter.equals("!=") && part.length() != 1) {
                splitMinus(part, tokens);
            }

                // We add the splitter after every token, excluding the last one
                else if (part.contains(codification.get(i)) && part.length() != codification.get(i).length()) {
                    List<String> partTokens = new LinkedList<>(Arrays.asList(part.split("((?<=" + splitter + ")|(?=" + splitter + "))")));
                    for (int j = 0; j < partTokens.size(); j++) {
                        tokens.add(tokens.lastIndexOf(part), partTokens.get(j));
//                        if (j < partTokens.size() - 1 || partTokens.size() == 1) {
//                            tokens.add(tokens.lastIndexOf(partTokens.get(j)) + 1, codification.get(i));
//                        }
                    }
                    tokens.remove(part);
                }
            }
        }
    }

    private void splitMinus(String s, List<String> tokensAll) {
        if (s.contains("-") && s.length() != 1) {
            List<String> tokens = new LinkedList<>(Arrays.asList(s.split("-")));
            tokens.removeIf(s1 -> s1.equals(""));

            for (int i = 0; i < tokens.size(); i++) {
                tokensAll.add(tokensAll.lastIndexOf(s) + 1, tokens.get(i));
                int position = tokensAll.lastIndexOf(tokens.get(i));
                //                   if (i < tokens.length - 1 || tokens.length == 1) {
                if (!tokensAll.get(position - 1).matches("-?\\d+") && i < tokens.size() - 1) {
                    tokensAll.add(tokensAll.lastIndexOf(tokens.get(i)) + 1, "-" + tokens.get(i + 1));
                    i++;
                } else if (i < tokens.size() - 1 || tokens.size() == 1) {
                    tokensAll.add(tokensAll.lastIndexOf(tokens.get(i)) + 1, "-");
                }
            }
            tokensAll.remove(s);
        }
    }

    private void splitGreater(String s, List<String> tokensAll) {
        if (s.contains(">") && s.length() != 1 && !s.contains(">=") && !s.contains("<>")) {
            String[] tokens = s.split(">");
            for (int i = 0; i < tokens.length; i++) {
                tokensAll.add(tokensAll.lastIndexOf(s), tokens[i]);
                if (i < tokens.length - 1 || tokens.length == 1) {
                    tokensAll.add(tokensAll.lastIndexOf(tokens[i]) + 1, ">");
                }
            }
            tokensAll.remove(s);
        }
    }

    private void spitLess(String s, List<String> tokensAll) {
        if (s.contains("<") && s.length() != 1 && !s.contains("<=") && !s.contains("<>")) {
            String[] tokens = s.split("<");
            for (int i = 0; i < tokens.length; i++) {
                tokensAll.add(tokensAll.lastIndexOf(s), tokens[i]);
                if (i < tokens.length - 1 || tokens.length == 1) {
                    tokensAll.add(tokensAll.lastIndexOf(tokens[i]) + 1, "<");
                }
            }
            tokensAll.remove(s);
        }
    }

    private void splitColon(String s, List<String> tokensAll) {
        if (s.contains(":") && s.length() != 1 && !s.contains(":=")) {
            String[] tokens = s.split(":");
            for (int i = 0; i < tokens.length; i++) {
                tokensAll.add(tokensAll.lastIndexOf(s), tokens[i]);
                if (i < tokens.length - 1 || tokens.length == 1) {
                    tokensAll.add(tokensAll.lastIndexOf(tokens[i]) + 1, ":");
                }
            }
            tokensAll.remove(s);
        }
    }

    private void splitEqual(String s, List<String> tokensAll) {
        if (s.contains("=") && s.length() != 1 && !s.contains(":=") && !s.contains(">=") && !s.contains("<=")) {
            String[] tokens = s.split("=");
            for (int i = 0; i < tokens.length; i++) {
                if (!tokens[i].equals("")) {
                    tokensAll.add(tokensAll.indexOf(s) + 1, tokens[i]);
                }
                if (i % 2 == 0) {
                    tokensAll.add(tokensAll.indexOf(s), "=");
                }
            }
            tokensAll.remove(s);
        }
    }

    private void identify(String part) throws Exception {
        if (codification.contains(part)) {
            pif.add(new Pair(codification.indexOf(part), -1));
        } else {
            if (isStCandidate(part)) {
                if (!symbolTable.contains(part)) {
                    List<NodeChanged> stModifications = addToSt(part);
                    TokenType tokenType = getTokenType(part);
                    syncPif(stModifications, tokenType);
                } else {
                    TokenType tokenType = getTokenType(part);
                    addToPif(tokenType, symbolTable.indexOf(part));
                }
            }
        }
    }

    private String removeExtraSpaces(String line) {
        return line.trim().replaceAll(" {2,}", " ");
    }

    private void syncPif(List<NodeChanged> stModifications, TokenType tokenType) {
        for (NodeChanged stModification : stModifications) {
            if (stModification.getOldPosition() == null) {
                addToPif(tokenType, stModification.getNewPosition());
            } else {
                for (Pair<Integer, Integer> pifEntry : pif) {
                    if (pifEntry.getValue().equals(stModification.getOldPosition())) {
                        pifEntry.setValue(stModification.getNewPosition());
                    }
                }
            }
        }
    }

    private void addToPif(TokenType tokenType, Integer position) {
        if (tokenType == TokenType.IDENTIFIER) {
            pif.add(new Pair<>(0, position));
        } else {
            pif.add(new Pair<>(1, position));
        }
    }

    private List<NodeChanged> addToSt(String part) {
        List<NodeChanged> stModifications = new ArrayList<>();
        int i = 0;
        if (symbolTable.getRoot() != null) {
            for (Node token : symbolTable) {
                stModifications.add(new NodeChanged(token.getValue(), i, null));
                i++;
            }
        }

        symbolTable.add(part);

        i = 0;
        for (Node token : symbolTable) {
            String currentToken = token.getValue();
            for (NodeChanged stModification : stModifications) {
                if (stModification.getName().equals(currentToken)) {
                    stModification.setNewPosition(i);
                }
            }
            i++;
        }

        stModifications.add(new NodeChanged(part, null, symbolTable.indexOf(part)));

        return sortModifications(stModifications);
    }

    private List<NodeChanged> sortModifications(List<NodeChanged> stModifications) {
        Collections.sort(stModifications, new NodeChangedComparator());
        return stModifications;
    }

    private TokenType getTokenType(String part) {
        if (isIdentifier(part)) {
            return TokenType.IDENTIFIER;
        } else {
            return TokenType.CONSTANT;
        }
    }

    private boolean isStCandidate(String part) throws Exception {
        if (isConstant(part)) {
            return true;
        } else {
            if (part.length() <= 8) {
                if (isIdentifier(part)) {
                    return true;
                } else {
                    throw new Exception(part);
                }
            } else {
                throw new Exception(part);
            }
        }
    }

    private boolean isIdentifier(String part) {
        return part.matches("(^[a-zA-Z]+[_0-9a-zA-Z]*)");
    }

    private boolean isConstant(String part) {
        return part.matches("\\-?[1-9]+[0-9]*|0")
                || part.matches("'[1-9a-zA-Z]'")
                || part.matches("\"[1-9a-zA-Z]+\"");
    }

    private void getCodif() {
        try {
            codification.addAll(Files.readAllLines(Paths.get("src/data/codification.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Language codification: " + codification + '\n');
    }
}