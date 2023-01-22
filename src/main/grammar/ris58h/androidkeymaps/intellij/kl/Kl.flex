package ris58h.androidkeymaps.intellij.kl;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ris58h.androidkeymaps.intellij.kl.psi.KlTypes;
import com.intellij.psi.TokenType;

%%

%class KlLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

/*
    Key Layout
    https://source.android.com/docs/core/interaction/input/key-layout-files
    https://android.googlesource.com/platform/frameworks/native/+/master/libs/input/KeyLayoutMap.cpp
 */

EOL=\n
WHITE_SPACE=[\ \t\r]
END_OF_LINE_COMMENT=("#")[^\r\n]*
NUMBER=(("0x")[0-9a-fA-F]*)|([0-9][^x][0-9]*)
IDENTIFIER=[A-Z][A-Z_]*
KEY_KEYWORD="key"
USAGE_KEYWORD="usage"

%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return KlTypes.COMMENT; }

<YYINITIAL> {NUMBER}                                        { yybegin(YYINITIAL); return KlTypes.NUMBER; }
<YYINITIAL> {IDENTIFIER}                                    { yybegin(YYINITIAL); return KlTypes.IDENTIFIER; }

<YYINITIAL> {KEY_KEYWORD}                                   { yybegin(YYINITIAL); return KlTypes.KEY_KEYWORD; }
<YYINITIAL> {USAGE_KEYWORD}                                 { yybegin(YYINITIAL); return KlTypes.USAGE_KEYWORD; }

({EOL}|{WHITE_SPACE})+                                      { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
