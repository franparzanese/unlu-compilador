package pruebaast;
import java_cup.runtime.Symbol;

%%
%state A
%cupsym Simbolo
%cup
%public
%class MiLexer
%line
%char

LineTerminator = \r|\n|\r\n

WhiteSpace     = {LineTerminator} | [ \t\f]

identificador = [a-z]([a-z]|[0-9]|\-)*([a-z]|[0-9])

constEntera = [0-9]+

constReal = [0-9]+"."[0-9]+

comilla = \"

constString = {comilla}({WhiteSpace}|{signo}|{operador}|[a-z]|[A-Z]|[0-9]|\.|\!|\¡|ñ|Ñ)*{comilla}

operador = (\+|-|\/|\*|>|<|\!=|<=|>=|=){1}

signo = ,|:|;

comentario = \/\/.

%%

<YYINITIAL> {

        "INICIO"               {return new Symbol(Simbolo.inicio, yycolumn, yyline);}
        "FIN"                  {return new Symbol(Simbolo.fin, yycolumn, yyline);}
        "if"                   {return new Symbol(Simbolo.palabraif, yycolumn, yyline);}
        "else"                 {return new Symbol(Simbolo.palabraelse, yycolumn, yyline);}
        "while"                {return new Symbol(Simbolo.palabrawhile, yycolumn, yyline);}
        "write"                {return new Symbol(Simbolo.write, yycolumn, yyline);}
        "avg"                  {return new Symbol(Simbolo.palabraavg, yycolumn, yyline);}
        "not"                  {return new Symbol(Simbolo.not, yycolumn, yyline);}
        "=="                   {return new Symbol(Simbolo.igual, yycolumn, yyline);}
        "<"                    {return new Symbol(Simbolo.menor, yycolumn, yyline);}
        ">"                    {return new Symbol(Simbolo.mayor, yycolumn, yyline);}
        "<="                   {return new Symbol(Simbolo.menorigual, yycolumn, yyline);}
        ">="                   {return new Symbol(Simbolo.mayorigual, yycolumn, yyline);}
        "!="                   {return new Symbol(Simbolo.distinto, yycolumn, yyline);} 
        ":"                    {return new Symbol(Simbolo.asigna, yycolumn, yyline);}
        ":="                    {return new Symbol(Simbolo.asigna, yycolumn, yyline);}
        "+"                    {return new Symbol(Simbolo.mas, yycolumn, yyline);}
        "-"                    {return new Symbol(Simbolo.menos, yycolumn, yyline);}
        "/"                    {return new Symbol(Simbolo.dividido, yycolumn, yyline);}
        "*"                    {return new Symbol(Simbolo.por, yycolumn, yyline);}
        "("                    {return new Symbol(Simbolo.parentesisA, yychar, yyline);}
	")"                    {return new Symbol(Simbolo.parentesisC, yychar, yyline);}
        "{"                    {return new Symbol(Simbolo.llaveA, yychar, yyline);}
        "}"                    {return new Symbol(Simbolo.llaveC, yychar, yyline);}
        "]"                    {return new Symbol(Simbolo.corcheteC, yychar, yyline);}
        "["                    {return new Symbol(Simbolo.corcheteA, yychar, yyline);}
        ";"                    {return new Symbol(Simbolo.eol, yychar, yyline);}
        ","                    {return new Symbol(Simbolo.coma, yychar, yyline);}
        "&&"                   {return new Symbol(Simbolo.and, yychar, yyline);}
        "||"                   {return new Symbol(Simbolo.or, yychar, yyline);}
        
        {identificador}        {return new Symbol(Simbolo.id, yychar, yyline,new String(yytext()));}
        {constEntera}          {return new Symbol(Simbolo.cte, yychar, yyline,new String(yytext()));}
        {constReal}            {return new Symbol(Simbolo.cte, yychar, yyline,new String(yytext()));}
        {constString}          {return new Symbol(Simbolo.constString, yychar, yyline,new String(yytext()));}
	{WhiteSpace}           { /* Así ignora los espacios en blanco */ }
        {comentario}           {/* Así ignora los comentarios */  }

}

[^]                    { throw new Error("Caracter no permitido: <" + yytext() + ">"); }

