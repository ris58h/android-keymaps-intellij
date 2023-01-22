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
LABEL=[A-Z][A-Z_]*

KEY_KEYWORD="key"
USAGE_KEYWORD="usage"
AXIS_KEYWORD="axis"
INVERT_KEYWORD="invert"
SPLIT_KEYWORD="split"
FLAT_KEYWORD="flat"
LED_KEYWORD="led"
SENSOR_KEYWORD="sensor"
RKC_KEYWORD="requires_kernel_config"

%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return KlTypes.COMMENT; }

<YYINITIAL> {NUMBER}                                        { yybegin(YYINITIAL); return KlTypes.NUMBER; }
<YYINITIAL> {LABEL}                                         { yybegin(YYINITIAL); return KlTypes.LABEL; }

<YYINITIAL> {KEY_KEYWORD}                                   { yybegin(YYINITIAL); return KlTypes.KEY_KEYWORD; }
<YYINITIAL> {USAGE_KEYWORD}                                 { yybegin(YYINITIAL); return KlTypes.USAGE_KEYWORD; }

<YYINITIAL> {AXIS_KEYWORD}                                  { yybegin(YYINITIAL); return KlTypes.AXIS_KEYWORD; }
<YYINITIAL> {INVERT_KEYWORD}                                { yybegin(YYINITIAL); return KlTypes.INVERT_KEYWORD; }
<YYINITIAL> {SPLIT_KEYWORD}                                 { yybegin(YYINITIAL); return KlTypes.SPLIT_KEYWORD; }
<YYINITIAL> {FLAT_KEYWORD}                                  { yybegin(YYINITIAL); return KlTypes.FLAT_KEYWORD; }

<YYINITIAL> {LED_KEYWORD}                                   { yybegin(YYINITIAL); return KlTypes.LED_KEYWORD; }

<YYINITIAL> {SENSOR_KEYWORD}                                { yybegin(YYINITIAL); return KlTypes.SENSOR_KEYWORD; }

<YYINITIAL> {RKC_KEYWORD}                                   { yybegin(YYINITIAL); return KlTypes.RKC_KEYWORD; }

({EOL}|{WHITE_SPACE})+                                      { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
