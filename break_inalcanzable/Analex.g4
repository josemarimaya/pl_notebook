lexer grammar Analex;

ESPACIO: ' ' -> skip;
TABULADOR: '\t'-> skip;
SALTOLINEA: '\r'?'\n' -> skip;
fragment DIGITO:[0-9];
fragment LETRA:[a-zA-Z];

NUMERO : ('-')?DIGITO+;
IDENT : LETRA(LETRA|DIGITO)*;

WHILE: 'while';
BREAK:'break';
IF: 'if';

MAS:'+';
POR:'*';
MENOS: '-';
DIV: '/';


ASIG: '==';
DISTINTO: '!=';
MENORIGUAL:'<=';
MAYORIGUAL:'>=';
MENOR: '<';
MAYOR: '>';
IGUAL: '=';

LLA: '{';
LLC: '}';
COMA: ',';
PyC: ';';
PA: '(';
PC:')';