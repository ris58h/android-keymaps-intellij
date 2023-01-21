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

//TODO: take a look at
//  https://source.android.com/docs/core/interaction/input/input-device-configuration-files
//  https://android.googlesource.com/platform/frameworks/base.git/+/master/tools/validatekeymaps/Main.cpp
//  https://android.googlesource.com/platform/frameworks/native/+/master/libs/input/PropertyMap.cpp
//  https://android.googlesource.com/platform/system/core/+/master/libutils/include/utils/Tokenizer.h
EOL=\n
WHITE_SPACE=[\ \t\r]
END_OF_LINE_COMMENT=("#")[^\r\n]*
//TODO check unsupported chars in keys
KEY_CHARACTER=[^=\ \n\t\r]
SEPARATOR=[=]
//TODO check unsupported chars in values
VALUE_CHARACTER=[^\ \n]

%state WAITING_VALUE
%state WAITING_EOL

%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return IdcTypes.COMMENT; }

<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return IdcTypes.KEY; }

<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return IdcTypes.SEPARATOR; }

<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }

<WAITING_VALUE> {VALUE_CHARACTER}+                          { yybegin(WAITING_EOL); return IdcTypes.VALUE; }

<WAITING_EOL> {WHITE_SPACE}+                                { yybegin(WAITING_EOL); return TokenType.WHITE_SPACE; }

<WAITING_EOL> {EOL}({EOL}|{WHITE_SPACE})+                   { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

<WAITING_EOL> [^\n\ \t\r]+                                  { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }

({EOL}|{WHITE_SPACE})+                                      { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
