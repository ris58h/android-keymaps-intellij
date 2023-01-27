package ris58h.androidkeymaps.intellij.kcm;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypes;
import com.intellij.psi.TokenType;

%%

%class KcmLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

/*
    Key Character Map
    https://source.android.com/docs/core/interaction/input/key-character-map-files
    https://android.googlesource.com/platform/frameworks/native/+/master/libs/input/KeyCharacterMap.cpp
 */

EOL=\n
WHITE_SPACE=[\ \t\r]
END_OF_LINE_COMMENT=("#")[^\r\n]*
LABEL=[A-Z][A-Z_0-9]*
NUMBER=("0x")[0-9a-fA-F]*|[0-9]+

TYPE_KEYWORD="type"
MAP_KEYWORD="map"
KEY_KEYWORD="key"
USAGE_KEYWORD="usage"

LBRACE="{"
RBRACE="}"
COMMA=","
PROPERTY_NAME="label"|"number"
BASE_MODIFIER="base"
MODIFIER="shift"
    |"lshift"
    |"rshift"
    |"alt"
    |"lalt"
    |"ralt"
    |"ctrl"
    |"lctrl"
    |"rctrl"
    |"meta"
    |"lmeta"
    |"rmeta"
    |"sym"
    |"fn"
    |"capslock"
    |"numlock"
    |"scrolllock"
PLUS="+"
SEMICOLON=":"
CHARACTER_LITERAL=\'.\'
    | (\'\\n\')
    | (\'\\t\')
    | (\'\\\\\')
    | (\'\\\'\')
    | (\'\\\"\')
    | (\'\\u[0-9a-fA-F]{4}\')
NONE_KEYWORD="none"
FALLBACK_KEYWORD="fallback"
REPLACE_KEYWORD="replace"

%%

<YYINITIAL> {
    {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return KcmTypes.COMMENT; }

    {NUMBER}                                        { yybegin(YYINITIAL); return KcmTypes.NUMBER; }
    {LABEL}                                         { yybegin(YYINITIAL); return KcmTypes.LABEL; }

    {TYPE_KEYWORD}                                  { yybegin(YYINITIAL); return KcmTypes.TYPE_KEYWORD; }
    {MAP_KEYWORD}                                   { yybegin(YYINITIAL); return KcmTypes.MAP_KEYWORD; }
    {KEY_KEYWORD}                                   { yybegin(YYINITIAL); return KcmTypes.KEY_KEYWORD; }
    {USAGE_KEYWORD}                                 { yybegin(YYINITIAL); return KcmTypes.USAGE_KEYWORD; }

    {LBRACE}                                        { yybegin(YYINITIAL); return KcmTypes.LBRACE; }
    {RBRACE}                                        { yybegin(YYINITIAL); return KcmTypes.RBRACE; }
    {COMMA}                                         { yybegin(YYINITIAL); return KcmTypes.COMMA; }
    {PROPERTY_NAME}|{BASE_MODIFIER}|({MODIFIER}({PLUS}{MODIFIER})*) { yybegin(YYINITIAL); return KcmTypes.PROPERTY_KEY; }
    {SEMICOLON}                                     { yybegin(YYINITIAL); return KcmTypes.SEMICOLON; }

    {CHARACTER_LITERAL}                             { yybegin(YYINITIAL); return KcmTypes.CHARACTER_LITERAL; }
    {NONE_KEYWORD}                                  { yybegin(YYINITIAL); return KcmTypes.NONE_KEYWORD; }
    {FALLBACK_KEYWORD}                              { yybegin(YYINITIAL); return KcmTypes.FALLBACK_KEYWORD; }
    {REPLACE_KEYWORD}                               { yybegin(YYINITIAL); return KcmTypes.REPLACE_KEYWORD; }
}

({EOL}|{WHITE_SPACE})+                              { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                 { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
