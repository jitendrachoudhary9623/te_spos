%{
#include<stdio.h>
%}
%token datatype comma semicolon variable next
%union {
	
	char *sval;
}

%token <sval> datatype,variable
%%
statement:datatype1 var semicolon next{printf("Valid Syntax");}
|
var:var comma variable1|variable1
|
datatype1:datatype{printf("Valid Datatype %s \n\n");}
|
variable1:variable{printf("Valid Variable %s \n\n");}


%%
void yyerror(){printf("Invalid Syntax");}
int yywrap(){return 0;}
int main()
{
yyparse();
return 0;
}
