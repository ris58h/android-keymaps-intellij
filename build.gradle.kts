plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("org.jetbrains.intellij") version "1.13.0"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = "ris58h.androidkeymaps"
version = "1.1"

repositories {
    mavenCentral()
}

intellij {
    version.set("2020.1") // Build against 'since' version
//    version.set("LATEST-EAP-SNAPSHOT") // Check against 'latest' version
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))

    updateSinceUntilBuild.set(false)
}

tasks {
    fun generateLexerTask(lang: String): Task {
        val dir = lang.toLowerCase()
        return task<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generate${lang}Lexer") {
            source.set("src/main/grammar/ris58h/androidkeymaps/intellij/${dir}/${lang}.flex")
            targetDir.set("src/main/gen/ris58h/androidkeymaps/intellij/${dir}")
            targetClass.set("${lang}Lexer")
            purgeOldFiles.set(true)
        }
    }

    fun generateParserTask(lang: String): Task {
        val dir = lang.toLowerCase()
        return task<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generate${lang}Parser"){
            source.set("src/main/grammar/ris58h/androidkeymaps/intellij/${dir}/${lang}.bnf")
            targetRoot.set("src/main/gen")
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

    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }

        dependsOn(
            generateIdcLexer, generateIdcParser,
            generateKlLexer, generateKlParser,
            generateKcmLexer, generateKcmParser,
        )
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

sourceSets["main"].java.srcDirs("src/main/gen")
