lexer grammar Analex;

BLANCO: ' ' ->skip;
TABULADOR: '\t'->skip;
FIN_LINEA: '\r'?'\n' ->skip;


fragment DIGITO: [0-9];
fragment LETRA:[a-zA-Z];


NUMERO : DIGITO+;
IDENT : LETRA(LETRA|DIGITO)*;

PARATODO: 'PARATODO';
EXISTE: 'EXISTE';
Y: 'Y';
O: 'O';
NO: 'NO';
MAYOR: '>';
MENOR: '=<';
MAYORQUE: '>=';
MENORQUE: '<';
MAS: '+';
MENOS: '-';
POR: '*';
DIV: '/';
IMPLICA: '=>';
DOSPUNTOS: ':';
DISTINTO: '!=';
IGUAL: '==';
COMA: ',';

PA: '(';
PC: ')';
