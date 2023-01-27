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
LABEL=[A-Z][A-Z_0-9]*
NUMBER=("0x")[0-9a-fA-F]*|[0-9]+

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

{END_OF_LINE_COMMENT}                           { return KlTypes.COMMENT; }

{NUMBER}                                        { return KlTypes.NUMBER; }
{LABEL}                                         { return KlTypes.LABEL; }

{KEY_KEYWORD}                                   { return KlTypes.KEY_KEYWORD; }
{USAGE_KEYWORD}                                 { return KlTypes.USAGE_KEYWORD; }

{AXIS_KEYWORD}                                  { return KlTypes.AXIS_KEYWORD; }
{INVERT_KEYWORD}                                { return KlTypes.INVERT_KEYWORD; }
{SPLIT_KEYWORD}                                 { return KlTypes.SPLIT_KEYWORD; }
{FLAT_KEYWORD}                                  { return KlTypes.FLAT_KEYWORD; }

{LED_KEYWORD}                                   { return KlTypes.LED_KEYWORD; }

{SENSOR_KEYWORD}                                { return KlTypes.SENSOR_KEYWORD; }

{RKC_KEYWORD}                                   { return KlTypes.RKC_KEYWORD; }

({EOL}|{WHITE_SPACE})+                          { return TokenType.WHITE_SPACE; }

[^]                                             { return TokenType.BAD_CHARACTER; }
