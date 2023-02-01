plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("org.jetbrains.intellij") version "1.12.0"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = "ris58h.androidkeymaps"
version = "1.1"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
//    version.set("2020.1") // Build against 'since' version
    version.set("LATEST-EAP-SNAPSHOT") // Check against 'latest' version
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))

    updateSinceUntilBuild.set(false)
}

tasks {
    val generateIdcLexer = task<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generateIdcLexer") {
        source.set("src/main/grammar/ris58h/androidkeymaps/intellij/idc/Idc.flex")
        targetDir.set("src/main/gen/ris58h/androidkeymaps/intellij/idc")
        targetClass.set("IdcLexer")
        purgeOldFiles.set(true)
    }

    val generateIdcParser = task<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generateIdcParser"){
        source.set("src/main/grammar/ris58h/androidkeymaps/intellij/idc/Idc.bnf")
        targetRoot.set("src/main/gen")
        pathToParser.set("/ris58h/androidkeymaps/intellij/idc/parser/IdcParser.java")
        pathToPsiRoot.set("/ris58h/androidkeymaps/intellij/idc/psi")
        purgeOldFiles.set(true)
    }

    val generateKlLexer = task<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generateKlLexer") {
        source.set("src/main/grammar/ris58h/androidkeymaps/intellij/kl/Kl.flex")
        targetDir.set("src/main/gen/ris58h/androidkeymaps/intellij/kl")
        targetClass.set("KlLexer")
        purgeOldFiles.set(true)
    }

    val generateKlParser = task<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generateKlParser"){
        source.set("src/main/grammar/ris58h/androidkeymaps/intellij/kl/Kl.bnf")
        targetRoot.set("src/main/gen")
        pathToParser.set("/ris58h/androidkeymaps/intellij/kl/parser/KlParser.java")
        pathToPsiRoot.set("/ris58h/androidkeymaps/intellij/kl/psi")
        purgeOldFiles.set(true)
    }

    val generateKcmLexer = task<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generateKcmLexer") {
        source.set("src/main/grammar/ris58h/androidkeymaps/intellij/kcm/Kcm.flex")
        targetDir.set("src/main/gen/ris58h/androidkeymaps/intellij/kcm")
        targetClass.set("KcmLexer")
        purgeOldFiles.set(true)
    }

    val generateKcmParser = task<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generateKcmParser"){
        source.set("src/main/grammar/ris58h/androidkeymaps/intellij/kcm/Kcm.bnf")
        targetRoot.set("src/main/gen")
        pathToParser.set("/ris58h/androidkeymaps/intellij/kcm/parser/KcmParser.java")
        pathToPsiRoot.set("/ris58h/androidkeymaps/intellij/kcm/psi")
        purgeOldFiles.set(true)
    }

    // Set the JVM compatibility versions
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
