lexer grammar Analex;

fragment DIGITO:[0-9];
fragment LETRA:[a-zA-Z];

DOSPUNTOS: ':';
PA: '(';
PC: ')';
COMA: ',';
PYC: ';';

IDENT: LETRA(LETRA|DIGITO)*;
NUMERO: (DIGITO)+;

IMPLICA: 'implica';

ESQUEMA: 'ESQUEMA';
FUENTE: 'FUENTE';
DESTINO: 'DESTINO';
RESTRICCIONES: 'RESTRICCIONES';
DATOS: 'DATOS';
VAR: 'VAR';



BLANCO: ' ' -> skip;
TABULADOR: '\t' -> skip;
FIN_LINEA: '\r?\n' -> skip;
COMENTARIO_BLOQUE: '/*' .*? '*/' -> skip;
COMENTARIO_LINEA: '//' .*? -> skip;
