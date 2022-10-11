// Analizador l�xico lenguaje K
lexer grammar Analex;

BLANCO: ' ' ->skip;
TABULADOR: '\t'->skip;
FIN_LINEA: '\r'?'\n' ->skip;
COMENTARIO_BLOQUE : '/*' .*? '*/' -> skip ;
COMENTARIO_LINEA : '//' .*?  FIN_LINEA -> skip ;

fragment DIGITO: [0-9];
fragment LETRA:[a-zA-Z];

Y:'Y';
O:'O';
NO:'NO';
IMPLICA:'=>';
EQUIVALENCIA: '<=>';
PARATODO:'PARATODO';
EXISTE:'EXISTE';
EXISTEUNO:'EXISTEUNO';
FALSO:'falso';
CIERTO:'cierto';

IDENT: LETRA(LETRA|DIGITO)*;
NUMERO: (DIGITO)+;

MAYOR:'>';
MENOR:'<';
MAYORIGUAL:'>=';
MENORIGUAL:'<=';
IGUAL:'==';
DISTINTO:'!=';

MAS:'+';
MENOS:'-';
POR:'*';
DIVISION:'/';

PA:'(';
PC:')';
DP:':';
COMA:',';
