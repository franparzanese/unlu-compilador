package flex;

import java_cup.runtime.Symbol;


%%


%cup
%public
%class Lexico
%line
%column
%char

LETRA = [a-zA-Z]
DIGITO = [0-9]
ESPACIO = \ |\t|\f
FIN_LINEA = \r|\n|\r\n
COM = \"
PUNTO = \.
COMA = \,
COM_ABRE = \<\/
COM_CIE = \/\>
ID = {LETRA}({LETRA}|{DIGITO}|_)*
CONST_INT = {DIGITO}+
CONST_STRING = {COM}({LETRA}|{DIGITO}|{ESPACIO}|{PUNTO})*{COM}
CONST_FLOAT = {DIGITO}+{PUNTO}{DIGITO}*|{PUNTO}{DIGITO}+
COMENTARIO = {COM_ABRE}({LETRA}|{DIGITO}|{ESPACIO}|{FIN_LINEA}|{COM}|{PUNTO}|{COMA})*{COM_CIE}


%%


<YYINITIAL> {

"("					{return new Symbol(sym.PAR_ABRE, yytext());}
")"					{return new Symbol(sym.PAR_CIE, yytext());}
"{"					{return new Symbol(sym.LLAV_ABRE, yytext());}
"}"					{return new Symbol(sym.LLAV_CIE, yytext());}
"["					{return new Symbol(sym.COR_ABRE, yytext());}
"]"					{return new Symbol(sym.COR_CIE, yytext());}
">"					{return new Symbol(sym.OP_MAY, yytext());}
"<"					{return new Symbol(sym.OP_MEN, yytext());}
">="				{return new Symbol(sym.OP_MAYIG, yytext());}
"<="				{return new Symbol(sym.OP_MENIG, yytext());}
"=="				{return new Symbol(sym.OP_IGUAL, yytext());}
"<>"				{return new Symbol(sym.OP_DIS, yytext());}
"and"				{return new Symbol(sym.AND, yytext());}
"or"				{return new Symbol(sym.OR, yytext());}
"+"					{return new Symbol(sym.OP_SUMA, yytext());}
"-"					{return new Symbol(sym.OP_RES, yytext());}
"*"					{return new Symbol(sym.OP_MUL, yytext());}
"/"					{return new Symbol(sym.OP_DIV, yytext());}
"="					{return new Symbol(sym.IGUAL, yytext());}
":="				{return new Symbol(sym.DOSPUN_IG, yytext());}
","					{return new Symbol(sym.COMA, yytext());}
":"					{return new Symbol(sym.DOSPUNTOS, yytext());}
"BEGIN.PROGRAM"		{return new Symbol(sym.BEGINPROGRAM, yytext());}
"END.PROGRAM"		{return new Symbol(sym.ENDPROGRAM, yytext());}
"DECLARE"			{return new Symbol(sym.DECLARE, yytext());}
"ENDDECLARE"		{return new Symbol(sym.ENDDECLARE, yytext());}
"while"				{return new Symbol(sym.MIENTRAS, yytext());}
"if"				{return new Symbol(sym.IF, yytext());}
"else"				{return new Symbol(sym.ELSE, yytext());}
"PRINT"				{return new Symbol(sym.PRINT, yytext());}
"LET"				{return new Symbol(sym.LET, yytext());}
"DEFAULT"			{return new Symbol(sym.DEFAULT, yytext());}
"FLOAT"				{return new Symbol(sym.FLOAT, yytext());}
"INT"				{return new Symbol(sym.INT, yytext());}
"STRING"			{return new Symbol(sym.STRING, yytext());}
{ESPACIO}			{}
{COMENTARIO}		{}
{FIN_LINEA}			{}
{ID}				{
						TS ts = TS.getInstance();
						ts.addSymbol(yytext(), "ID", "-", "-", "-");
						return new Symbol(sym.ID, yytext());
					}
{CONST_INT}			{
						TS ts = TS.getInstance();
						ts.addSymbol("_" + yytext(), "CONST_INT", "-", yytext(), "-");
						return new Symbol(sym.CONST_INT, yytext());
					}
{CONST_STRING}		{
						if (yytext().length() <= 32) {
							TS ts = TS.getInstance();
							ts.addSymbol("_" + yytext(), "CONST_STRING", "-", yytext(), Integer.toString(yytext().length()));
							return new Symbol(sym.CONST_STRING, yytext());
						} else {
							throw new Error("La constante string <" + yytext() + "> en la línea " + yyline + " supera el límite de 30 caracteres: tiene " + (yytext().length() - 2));
						}
					}
{CONST_FLOAT}		{
						TS ts = TS.getInstance();
						ts.addSymbol("_" + yytext(), "CONST_FLOAT", "-", yytext(), "-");
						return new Symbol(sym.CONST_FLOAT, yytext());
					}

}

[^]					{throw new Error("Caracter no permitido: <" + yytext() + "> en la línea " + yyline);}
<<EOF>>				{return new Symbol(sym.EOF);}
