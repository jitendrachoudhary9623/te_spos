%{
#include<stdio.h>
#include "y.tab.h"

%}
%%
and|or|but|also|therefore|hence|meanwhile|that {
yylval.String=yytext;return compounder;}

[a-zA-Z]+ {return word;}
[\t] {return space;}
%%
