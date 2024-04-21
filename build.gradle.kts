plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.shadow)
    alias(libs.plugins.paperweight)
    alias(libs.plugins.run.paper) // Adds runServer and runMojangMappedServer tasks for testing
}

group = providers.gradleProperty("group").get()
version = providers.gradleProperty("plugin_version").get()
description = providers.gradleProperty("plugin_description").get()

val serverVersion = providers.gradleProperty("server_version").get()

ext {
    set ("kotlin_version", libs.versions.kotlin.get())
    set ("kotlinx_coroutines_version", libs.versions.kotlinx.coroutines.get())
}

repositories {
    mavenLocal()
    mavenCentral()
    maven ("https://jitpack.io")
    maven ("https://repo.papermc.io/repository/maven-public/")
    maven ("https://oss.sonatype.org/content/groups/public/")
    maven ("https://repo.william278.net/releases")
    maven ("https://repo.flyte.gg/releases")
    maven ("https://repo.minebench.de/")
}

dependencies {
    paperweight.paperDevBundle(serverVersion)
    compileOnly (libs.kotlin.stdlib)
    compileOnly (libs.kotlinx.coroutines)

// Libs
    implementation(libs.bstats)
    implementation(libs.configlib.yaml)


// Twilight Dev API
    implementation (libs.twilight)

// Cloud Command Framework

    implementation (libs.cloud.paper)
    implementation (libs.cloud.annotations)


// Lamp Command Framework
    implementation(libs.lamp.common)
    implementation(libs.lamp.bukkit)
    implementation(libs.lamp.brigadier)

// External libraries
//    implementation(libs.netty.codec)
//    implementation(libs.netty.codec.http)
//    implementation(libs.netty.handler)
//    implementation(libs.mysql.connector)
}
val pluginName = providers.gradleProperty("plugin_name").get()
val minecraftVersion = providers.gradleProperty("minecraft_version").get()
val javaVersion: Int = providers.gradleProperty("java_version").get().toInt()

tasks {
    build { dependsOn(shadowJar) }
    test {useJUnitPlatform() }
    runServer { minecraftVersion(minecraftVersion) }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
        filesMatching(listOf("paper-plugin.yml", "plugin.yml")) {
            expand(rootProject.properties)
        }
    }

    shadowJar {
        archiveFileName.set("$pluginName-$version.jar")

        val libraryPath = "io.lcydev.libraries"
        relocate("io.github", "$libraryPath.github")
        relocate("gg.flyte.twilight", "$libraryPath.twilight")
        relocate("revxrsal.commands", "$libraryPath.revxrsal.commands")
        relocate("com.mongodb", "$libraryPath.com.mongodb")
        relocate("com.google", "$libraryPath.com.google")
        relocate("org.bson", "$libraryPath.org.bson")
        relocate("org.jetbrains", "$libraryPath.org.jetbrains")
        minimize()
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    setSourceCompatibility(javaVersion)
    setTargetCompatibility(javaVersion)
}

kotlin {
    jvmToolchain(javaVersion)
}