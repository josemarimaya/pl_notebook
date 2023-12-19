parser grammar Anasint;

options{
tokenVocab=Analex;
}

programa: PROGRAMA (instruccion)*

instruccion: PARENTESISABIERTO tipo PARENTESISCERRADO expresion PUNTOYCOMA

tipo: NAT | BOOL ;

expresion: expresion1 MAS expresion
| expresion1 MENOS expresion
| expresion1 O expresion
| expresion1

expresion1: expresion2 POR expresion1
| expresion2 Y expresion1
| expresion2

expresion2: PARENTESISABIERTO expresion PARENTESISCERRADO
| NO expresion
| NUMERO
| CIERTO
| FALSO