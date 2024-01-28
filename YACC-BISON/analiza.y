%{
#include <stdio.h>
#include <stdlib.h>

#define YYDEBUG 1
%}

%token IDENTIFIER
%token CONSTANT
%token PUBLIC
%token CLASS
%token CLASSMAIN
%token MAIN
%token STATIC
%token VOID
%token INT
%token DOUBLE
%token STRING
%token ARGS
%token IF
%token ELSE
%token WHILE
%token SYSTEM
%token PRINTLN
%token OUT
%token NEW
%token NUMAR
%token IN
%token FOR
%token COLON
%token SEMI_COLON
%token COMA
%token DOT
%token PLUS
%token MINUS
%token MULTIPLY
%token DIVISION
%token LEFT_ROUND_PARENTHESIS
%token RIGHT_ROUND_PARENTHESIS
%token LEFT_SQUARE_PARENTHESIS
%token RIGHT_SQUARE_PARENTHESIS
%token LEFT_BRACE_PARENTHESIS
%token RIGHT_BRACE_PARENTHESIS
%token LESS_THAN
%token GREATER_THAN
%token LESS_OR_EQUAL_THAN
%token GREATER_OR_EQUAL_THAN
%token DIFFERENT
%token EQUAL
%token DIF
%token ASSIGNMENT
%token BAR

%start program

%%

program : PUBLIC CLASS CLASSMAIN LEFT_BRACE_PARENTHESIS PUBLIC STATIC VOID MAIN LEFT_ROUND_PARENTHESIS STRING LEFT_SQUARE_PARENTHESIS RIGHT_SQUARE_PARENTHESIS ARGS RIGHT_ROUND_PARENTHESIS LEFT_BRACE_PARENTHESIS decl RIGHT_BRACE_PARENTHESIS RIGHT_BRACE_PARENTHESIS;
decl : type IDENTIFIER EQUAL statementList ;
type : INT | DOUBLE ; 
statementList : statement SEMI_COLON statementList | statement ;
statement : simpleStatement | structStatement ;
simpleStatement : assignemntStatement ;
assignemntStatement : IDENTIFIER EQUAL expression SEMI_COLON ;
expression : term | term PLUS expression | term MINUS expression | term MULTIPLY expression | term DIVISION expression | term DIF expression;
term : IDENTIFIER | CONSTANT ;
structStatement : ifStatement | whileStatement ;
ifStatement : IF condition expression SEMI_COLON ELSE expression SEMI_COLON ;
whileStatement : WHILE RIGHT_ROUND_PARENTHESIS condition LEFT_ROUND_PARENTHESIS ;
condition : expression relation expression ;
relation : LESS_THAN | GREATER_THAN | LESS_OR_EQUAL_THAN | GREATER_OR_EQUAL_THAN | DIF | EQUAL ;

%%

yyerror(char *s)
{
  printf("%s\n", s);
}

extern FILE *yyin;

main(int argc, char **argv)
{
  if (argc > 1) 
    yyin = fopen(argv[1], "r");
  if ( (argc > 2) && ( !strcmp(argv[2], "-d") ) ) 
    yydebug = 1;
  if ( !yyparse() ) 
    fprintf(stderr,"\t GOOOOOD !!!\n");
  else
    fprintf(stderr,"\t BAAAAAD !!!\n");
}