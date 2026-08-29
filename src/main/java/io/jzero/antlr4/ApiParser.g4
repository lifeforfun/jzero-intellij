parser grammar ApiParser;

options {
    tokenVocab = ApiLexer;
}

@parser::members {
boolean isNormalField() {
    org.antlr.v4.runtime.Token t1 = _input.LT(1);
    org.antlr.v4.runtime.Token t2 = _input.LT(2);
    if (t2.getType() == org.antlr.v4.runtime.Token.EOF) {
        return false;
    }
    if (t2.getLine() != t1.getLine()) {
        return false;
    }
    return t1.getType() != ApiLexer.STAR;
}
}

api:
    syntaxLit?
    (
    importStatement
    |infoStatement
    |apiBody
    )*
    EOF;

syntaxLit: 'syntax' '=' VALUE;
apiBody:
    typeStatement
    |serviceStatement;

importStatement:importSpec+;

importSpec: importLit|importGroup;
importLit:IMPORT importValue;
importGroup: IMPORT LPAREN importValue* RPAREN;

// info
infoStatement: INFO LPAREN pair RPAREN;

// types: type / vo / dto
typeStatement: typeSingleSpec|typeGroupSpec;

typeGroupSpec: groupName LPAREN typeGroupBody RPAREN;
groupName: TYPE | VO | DTO;
typeGroupBody:(typeGroupAlias|structType)*;

typeSingleSpec: typeAlias|typeStruct|voSingle|dtoSingle;
typeStruct:TYPE structType;
typeAlias:TYPE structNameId '='? normalFieldType;
voSingle: VO structType;
dtoSingle: DTO (structType | structNameId '='? normalFieldType);

typeGroupAlias:structNameId '='? normalFieldType;

typeFiled:
    ({isNormalField()}? normalField)
    | anonymousField
    | structType;
normalField:fieldName (COMMA fieldName)* fieldType tag?;
fieldType:normalFieldType|starFieldType|mapFieldType|arrayOrSliceType;
anonymousField: STAR? referenceId tag?;
normalFieldType: GOTYPE|referenceId|ANY|(INTERFACE LBRACE RBRACE);
starFieldType: STAR normalFieldType;
mapFieldType: MAP LBRACK fieldType RBRACK fieldType;
arrayOrSliceType: (LBRACK (NUMBER|IDENT)? RBRACK)+ fieldType;
structType: structNameId STRUCT? LBRACE (typeFiled)* RBRACE;
structNameId:IDENT;
fieldName:IDENT;
referenceId:pkg? IDENT;
pkg: IDENT DOT;
tag: RAW_STRING;
body: (LBRACK RBRACK)? STAR? (IDENT|GOTYPE|ANY);

// service
serviceStatement: (serviceServerSpec? serviceSpec);
serviceServerSpec: ATSERVER LPAREN identPair RPAREN;

serviceSpec: SERVICE serviceName LBRACE serviceBody+ RBRACE;
serviceName:(IDENT BAR?)+;
serviceBody:(serviceDoc|serviceDocNew)? (serviceHandler|serviceHandlerNew) serviceRoute;
serviceDoc: ATDOC LPAREN pair RPAREN;
serviceDocNew: ATDOC (docValue|(LPAREN docValue RPAREN));
serviceHandler: ATSERVER LPAREN handlerPair RPAREN;
serviceHandlerNew: ATHANDLER handlerValue;
serviceRoute:httpRoute (LPAREN body? RPAREN)? (RETURNS LPAREN body? RPAREN)? SMICOLON?;
httpRoute:HTTPMETHOD PATH;
identPair:(key COLON (DURATION|identValue|PATH|NUMBER|RAW_STRING|VALUE|IDENT))*;
handlerPair:(key COLON handlerValue)+;
identValue:(IDENT COMMA?)+;
handlerValue:VALUE|RAW_STRING|IDENT;
importValue:VALUE (AS IDENT)?;
docValue:VALUE;
pair:(key COLON (VALUE|RAW_STRING|IDENT)?)*;
key:IDENT;
