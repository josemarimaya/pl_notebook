lexer grammar Analex;

PROGRAMA:'PROGRAMA';
VARIABLES:'VARIABLES';
INSTRUCCIONES:'INSTRUCCIONES';
SI:'si';
ENTONCES:'entonces';
SINO:'sino';
FINSI:'fsi';
LEER:'leer';

BLANCO: ' ' ->skip ;
TAB: '\t' ->skip ;
NL: '\r'?'\n' ->skip ;
COMLINEA: '//'(.)*? NL ->skip ;
COM: '/*'(.)*? '*/' ->skip ;

fragment DIGITO: [0-9];
fragment LETRA: [a-zA-Z];

IDENT: LETRA(LETRA|DIGITO)*;
NUMERO: (DIGITO)+;
ASIG:'=';
PARENTESISABIERTO: '(';
PARENTESISCERRADO: ')' ;
COMA:',';
PUNTOYCOMA: ';';
MAS:'+';
MENOS:'-';
POR:'*';
MAYOR:'>';
MENOR:'<';
MAYORIGUAL:'>=';
MENORIGUAL:'<=';
IGUAL:'==';
DISTINTO:'!=';