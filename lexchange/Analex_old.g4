lexer grammar Analex;
BLANCO: ' ' -> skip;
TABULADOR: '\t' -> skip;
FIN_LINEA: '\r?'?'\n' -> skip;

fragment DIGITO:[0-9];
fragment LETRA:[a-zA-Z];
ESQUEMA: 'ESQUEMA';
FUENTE: 'FUENTE';
DESTINO: 'DESTINO';
RESTRICCIONES: 'RESTRICCIONES';
DATOS: 'DATOS';
VAR: 'VAR';
IMPLICA: 'implica';

IDENT: LETRA(LETRA|DIGITO)*;
NUMERO: (DIGITO)+;
DOSPUNTOS: ':';
PA: '(';
PC: ')';
COMA: ',';
PYC: ';';








