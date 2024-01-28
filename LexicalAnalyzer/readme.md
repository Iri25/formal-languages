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

