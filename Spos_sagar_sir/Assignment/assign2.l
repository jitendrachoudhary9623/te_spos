%{
FILE *fp;

%}

%%

(" "[aA][nN][Dd]" ") {printf("%s is a compount Statement\n",yytext);}

%%

int main()
{
yyin=fopen("a2.txt","r");
fp=fopen("o2.txt","w");
yylex();

return 0;
}

int yywrap()
{


return 1;
}
