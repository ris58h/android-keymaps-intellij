plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.13.0"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = "ris58h.androidkeymaps"
version = "2.1"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)
}

intellij {
    version.set("2020.3") // Build against 'since' version
//    version.set("LATEST-EAP-SNAPSHOT") // Check against 'latest' version
    type.set("IC") // Target IDE Platform

    updateSinceUntilBuild.set(false)
}

val generatedSrcGrammarPath = buildDir.resolve("generated-src/grammar").path

tasks {
    fun generateLexerTask(lang: String): Task {
        val dir = lang.toLowerCase()
        return task<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generate${lang}Lexer") {
            source.set("src/main/grammar/ris58h/androidkeymaps/intellij/${dir}/${lang}.flex")
            targetDir.set("${generatedSrcGrammarPath}/ris58h/androidkeymaps/intellij/${dir}")
            targetClass.set("${lang}Lexer")
            purgeOldFiles.set(true)
        }
    }

    fun generateParserTask(lang: String): Task {
        val dir = lang.toLowerCase()
        return task<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generate${lang}Parser"){
            source.set("src/main/grammar/ris58h/androidkeymaps/intellij/${dir}/${lang}.bnf")
            targetRoot.set(generatedSrcGrammarPath)
            pathToParser.set("/ris58h/androidkeymaps/intellij/${dir}/parser/${lang}Parser.java")
            pathToPsiRoot.set("/ris58h/androidkeymaps/intellij/${dir}/psi")
            purgeOldFiles.set(true)
        }
    }

    val generateIdcLexer = generateLexerTask("Idc")
    val generateIdcParser = generateParserTask("Idc")
    val generateKlLexer = generateLexerTask("Kl")
    val generateKlParser = generateParserTask("Kl")
    val generateKcmLexer = generateLexerTask("Kcm")
    val generateKcmParser = generateParserTask("Kcm")

    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }

        dependsOn(
            generateIdcLexer, generateIdcParser,
            generateKlLexer, generateKlParser,
            generateKcmLexer, generateKcmParser,
        )
    }

    buildSearchableOptions {
        enabled = false
    }
}

sourceSets["main"].java.srcDir(generatedSrcGrammarPath)
