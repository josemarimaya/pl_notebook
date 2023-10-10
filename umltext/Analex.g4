lexer grammar Analex;
ESPACIO: ' ' -> skip;
TABULADOR: '\t'-> skip;
SALTOLINEA: '\r'?'\n' -> skip;

fragment DIGITO:[0-9];
fragment LETRA:[a-zA-Z];

BEGIN_CLASS: 'BEGIN_CLASS_DIAGRAM';
END_CLASS: 'END_CLASS_DIAGRAM';
BEGIN_OBJECT: 'BEGIN_OBJECT_DIAGRAM';
END_OBJECT: 'END_OBJECT_DIAGRAM';

CLASS: 'Class';
OBJECT: 'Object';

LINK: 'Link';
LINKEDOBJECTS: 'LinkedObjects';

ASSOCIATION: 'Association';
BETWEEN: 'between';
MULTIPLICITY: 'Multiplicity';
ON: 'on';
IS: 'is';

R: 'R';

AND: 'and';
OR: 'or';
IGUALDAD: '=';
MAYORIGUAL: '>=';
MENORIGUAL:'<=';

IDENT: LETRA(LETRA|DIGITO)*;
NUMERO: (DIGITO)+;
COMA: ',';
PyC: ';';
PA: '(';
PC:')';