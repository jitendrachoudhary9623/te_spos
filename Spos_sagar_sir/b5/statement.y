%{
#include<stdio.h>
%}
%union {
	
	char *String;
}
%token compounder,word,space
%token <String> compounder,word
%%
S:A|B;
A:W C W;
B:W W|W;
C:compounder{printf("\n\nCompound Statement made by \" %s \" word \n\n",$1);};
W:W word1|word1{printf(" Simple Statement ");};
word1:word;
%%
#include <stdio.h>
extern int yylex();
extern int yyparse();
extern FILE *yyin;

void yyerror(){printf("Invalid Syntax");}
int yywrap(){return 0;}
int main()
{
FILE *myfile = fopen("abc.txt", "r");
yyin = myfile;
do {
yyparse();
} 
while (!feof(yyin));
return 0;
}
