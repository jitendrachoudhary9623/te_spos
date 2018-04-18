%{
FILE *fp;


%}

%%

[A-Z] {printf("%c",yytext[0]+32);}
[a-z] {printf("%c",yytext[0]-32);}
%%


int main()
{
yyin=fopen("a1.txt","r");

yylex();

return 0;
}

int yywrap()
{


return 1;
}
