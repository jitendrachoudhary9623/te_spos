%{
#include<stdio.h>
#include "y.tab.h"
char *p;
char *a;
%}
%%
int|float|char|String {   p=(char *)calloc(strlen(yytext)+1,sizeof(char));
                       strcpy(p,yytext);
                       yylval.sval=p;return datatype;}

[a-zA-Z]+[a-zA-Z0-9]* {
	a=(char *)calloc(strlen(yytext)+1,sizeof(char));
                       strcpy(a,yytext);
                       yylval.sval=a;

return variable;}
"," return comma;
";" return semicolon;
"\n" return next;
%%
