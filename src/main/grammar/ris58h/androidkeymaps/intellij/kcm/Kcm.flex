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
NUMBER=(("0x")[0-9a-fA-F]*)|([0-9][^x][0-9]*)
LABEL=[A-Z][A-Z_]*

TYPE_KEYWORD="type"
MAP_KEYWORD="map"
KEY_KEYWORD="key"
USAGE_KEYWORD="usage"

%%

<YYINITIAL> {
    {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return KcmTypes.COMMENT; }

    {NUMBER}                                        { yybegin(YYINITIAL); return KcmTypes.NUMBER; }
    {LABEL}                                         { yybegin(YYINITIAL); return KcmTypes.LABEL; }

    {TYPE_KEYWORD}                                  { yybegin(YYINITIAL); return KcmTypes.TYPE_KEYWORD; }
    {MAP_KEYWORD}                                   { yybegin(YYINITIAL); return KcmTypes.MAP_KEYWORD; }
    {KEY_KEYWORD}                                   { yybegin(YYINITIAL); return KcmTypes.KEY_KEYWORD; }
    {USAGE_KEYWORD}                                 { yybegin(YYINITIAL); return KcmTypes.USAGE_KEYWORD; }
}

({EOL}|{WHITE_SPACE})+                              { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                 { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
