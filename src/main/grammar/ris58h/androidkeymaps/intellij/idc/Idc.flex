package ris58h.androidkeymaps.intellij.idc;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes;
import com.intellij.psi.TokenType;

%%

%class IdcLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

/*
    Input Device Configuration
    https://source.android.com/docs/core/interaction/input/input-device-configuration-files
    https://android.googlesource.com/platform/frameworks/native/+/master/libs/input/PropertyMap.cpp
 */

EOL=\n
WHITE_SPACE=[\ \t\r]
END_OF_LINE_COMMENT=("#")[^\r\n]*
KEY_CHARACTER=[^=\ \n\t\r]
SEPARATOR=[=]
FIRST_VALUE_CHARACTER=[^\ \n]
VALUE_CHARACTER=[^\n]

%state WAITING_VALUE

%%

<YYINITIAL> {
    {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return IdcTypes.COMMENT; }
    {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return IdcTypes.KEY; }
    {SEPARATOR}                                     { yybegin(WAITING_VALUE); return IdcTypes.SEPARATOR; }
}

<WAITING_VALUE> {
    {WHITE_SPACE}+                                  { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
    {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*       { yybegin(YYINITIAL); return IdcTypes.VALUE; }
}

({EOL}|{WHITE_SPACE})+                              { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
