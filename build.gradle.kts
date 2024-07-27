plugins {
    id("java-library")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:RC-37")
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
}

group = "io.github.thebusybiscuit"
version = "UNOFFICIAL"
description = "MobCapturer"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.shadowJar {
    fun doRelocate(from: String) {
        val last = from.split(".").last()
        relocate(from, "io.github.thebusybiscuit.mobcapturer.libs.$last")
    }
    doRelocate("org.bstats")
    doRelocate("javax.annotation")
    minimize()
    archiveClassifier = ""
}

bukkit {
    main = "io.github.thebusybiscuit.mobcapturer.MobCapturer"
    apiVersion = "1.18"
    authors = listOf("TheBusyBiscuit", "ybw0014")
    description = "A Slimefun Addon that adds a tool that allows you to capture mobs"
    website = "https://github.com/Slimefun-Addon-Community/MobCapturer"
    depend = listOf("Slimefun")
}
