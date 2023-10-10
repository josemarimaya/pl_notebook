parser grammar Anasint;

options{
tokenVocab=Analex;
}
programa: clase objeto EOF;

clase: BEGIN_CLASS (decl_class)* (decl_aso)* (decl_restr)* END_CLASS;

decl_class: PA CLASS IDENT PC;

decl_aso: PA ASSOCIATION R BETWEEN IDENT (AND|OR) IDENT PC;

decl_restr: PA MULTIPLICITY R ON IDENT IS (IGUALDAD|MAYORIGUAL|MENORIGUAL) NUMERO PC;

objeto: BEGIN_OBJECT (decl_obj)* (link_obj)* END_OBJECT;

decl_obj: PA OBJECT IDENT CLASS IDENT PC;

link_obj: PA LINK IDENT ASSOCIATION R LINKEDOBJECTS IDENT IDENT PC;
