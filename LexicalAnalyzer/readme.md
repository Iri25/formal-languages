### Mini Programming Language

Specifying the Java miniimage according to the language standard:

````
<program> “public” “class” NAME { “public” “static” “void” “main”( “String”[] “args”) {
<decl><list_instr>}}
<NAME> → <Main> | <class_name>
<Class_Name> → A | B| … | Z| to | b| … | z
<decl> → <data_type> <variable>
<data_type> → <int> | <double>
<int> → “0” | ([“+” | “-”] digitN{digit})
<double> → [“+” | “-”] digit “.” digit{digit}"d"
<variable> → ID | constant
<CONST> → <int> | <double>
<instr_list> → <instr> “;” <instr_list>
<instr_list> → <instr>
<instr> → <assignment>
<instr> → <instr_if_else>
<instr> → <instr_while>
<assignment> → ID = <expr>
<expr> → <variable> * <variable> * CONST
<expr> → <variable>
<expr> → <expr> - <variable>
<expr> → <expr> + <variable>
<instr_if_else> → “if” (<expr>) <expr> “else” <expr>
<instr_while> → “while” (<expr>) <instr_if_else>
<instr_while> → “while” (<expr>) <list_instr>
<read> → “Scanner” “in” = “new” “Scanner” (“System.in”); <data_type> <variable> = “in”
"." "nextInt" () ";"
<display> → “System” “.” "out" "." "println" (<text> + <variable> | <expr>) ";"
````

### Mini programs
1. Calculate the perimeter and area of the circle with a given radius
````   
Input data: PI, R
Output data: 2*R*PI, R*R*PI
Data type: double
Specification: calculates the perimeter and area of the circle
````
2. Determine the cmdc of 2 natural numbers
```
Input data: a, b
Output data: a
Data type: int
Specification: calculates the lowest common divisor of two numbers
```

3. Calculates the sum of n numbers read from the keyboard
```
Input data: s, n, x
Output data: s
Data type: int
Specification: calculates the sum of n numbers entered from the keyboard
```

### Errors according to the mini programming language
```
A program that contains two errors that are at the same time errors in the original language (for which a subset is defined).
A program that contains two errors, but which are not errors in the original language. It is required that it be compiled and executed in the original language chosen.
```

### Implementation of the lexical analyzer
```
Restrictions: aab
- Identifiers with a maximum length of 8 characters
- Unique symbol table for identifiers and constants
- Organization of symbol tables as a binary tree of search
```
