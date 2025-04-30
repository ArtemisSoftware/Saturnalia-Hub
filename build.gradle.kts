import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    kotlin("plugin.serialization") version "1.9.10"
}

group = "com.artemissoftware"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)

    implementation("org.jsoup:jsoup:1.16.1") // For web scraping
    implementation("com.google.code.gson:gson:2.10.1") // For JSON conversion
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    implementation("io.github.darkokoa:datetime-wheel-picker:1.1.0-alpha02-compose1.6")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

    //implementation("androidx.compose.material3:material3:1.4.0-alpha12")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "SaturnaliaHub"
            packageVersion = "1.0.0"
        }
    }
}
