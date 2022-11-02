plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    id("org.jetbrains.intellij") version "1.9.0"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = "ris58h.androidkeymaps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2021.3.3")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    generateLexer {
        source.set("src/main/grammar/ris58h/androidkeymaps/intellij/idc/Idc.flex")
        targetDir.set("src/gen/java/ris58h/androidkeymaps/intellij/idc")
        targetClass.set("IdcLexer")
        purgeOldFiles.set(true)
    }

    generateParser {
        source.set("src/main/grammar/ris58h/androidkeymaps/intellij/idc/Idc.bnf")
        targetRoot.set("src/gen/java")
        pathToParser.set("/ris58h/androidkeymaps/intellij/idc/parser/IdcParser.java")
        pathToPsiRoot.set("/ris58h/androidkeymaps/intellij/idc/psi")
        purgeOldFiles.set(true)
    }

    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"

        dependsOn(generateLexer, generateParser)
    }

    patchPluginXml {
        sinceBuild.set("213")
        untilBuild.set("223.*")
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

sourceSets["main"].java.srcDirs("src/gen/java")
