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
CONST_STRING = \"[^\"\r\n]*\"
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
"not"               {return new Symbol(sym.NOT, yytext());}
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
"while" 			{ return new Symbol(sym.WHILE, yytext()); }
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

{CONST_FLOAT}		{
						TS ts = TS.getInstance();
						ts.addSymbol( "_cte" + yytext().replace(".", "_"), "CONST_FLOAT", "NUMERIC", yytext(), "-");
						return new Symbol(sym.CONST_FLOAT, Float.parseFloat(yytext()));
					}
{CONST_INT}			{
						TS ts = TS.getInstance();
						ts.addSymbol("_cte" + yytext(), "CONST_INT", "NUMERIC", yytext(), "-");
						return new Symbol(sym.CONST_INT, Integer.parseInt(yytext()));
					}
{CONST_STRING} 		{
						String texto = yytext();
						String contenido = texto.substring(1, texto.length() - 1);

						if (contenido.length() <= 30) {

							TS ts = TS.getInstance();

							String nombre = "_" + contenido
									.replaceAll("[^a-zA-Z0-9_]", "_");

							ts.addSymbol(
									nombre,
									"CONST_STRING",
									"STRING",
									texto,
									Integer.toString(contenido.length())
							);

							return new Symbol(sym.CONST_STRING, texto);

						} else {
							throw new Error(
									"La constante string <" + texto + "> en la línea "
									+ (yyline + 1)
									+ " supera el límite de 30 caracteres: tiene "
									+ contenido.length()
							);
						}
					}


}

[^]					{throw new Error("Caracter no permitido: <" + yytext() + "> en la línea " + (yyline + 1));}
<<EOF>>				{return new Symbol(sym.EOF);}
