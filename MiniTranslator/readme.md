### Design a program that:
1. A translator for a minimal language using the LEX/FLEX & YACC/BISON program generators. The translator will receive a source file and generate the associated ASM 80x86 code.

### The mini-programming language:
Simplified specification of the C++ miniimage according to the language standard.
```
int main()
{
      list_instructions AND list_statements
}
statement_list : statement statement_list OR statement;
instruction : read OR write OR assign;
read: "cin>>" identifier;
writing: "cout<<" identifier OR "cout<<" constant;
assignment: identifier “=” expression
expression : expression operator expression OR operand operator operand OR operand;
operand: identifier OR constant;
operator : '+' OR '-' OR '*' OR '/';
list_declarari : declaration list_declarari OR declaration;
declaration : variable int;
```
### Mini programs
1. example1.txt
```
Input data: x, y
Output data: x
Data type: int
```
2. example2.txt
```
Input data: x, y
Exit dates: z
Data type: int
Specification: The difference between 2 integers
```
