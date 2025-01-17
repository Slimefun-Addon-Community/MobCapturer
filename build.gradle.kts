plugins {
    id("java")
    id("maven-publish")
    id("io.freefair.lombok") version "8.7.1"
    id("com.gradleup.shadow") version "8.3.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/groups/public/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:RC-37")
    implementation("org.bstats:bstats-bukkit:3.1.0")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("net.guizhanss:guizhanlib-all:2.2.0")
}

group = "io.github.thebusybiscuit"
version = "UNOFFICIAL"
description = "MobCapturer"

java {
    disableAutoTargetJvm()
    sourceCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.javadoc {
    options.encoding = "UTF-8"
}

tasks.shadowJar {
    fun doRelocate(from: String) {
        val last = from.split(".").last()
        relocate(from, "io.github.thebusybiscuit.mobcapturer.libs.$last")
    }
    doRelocate("org.bstats")
    doRelocate("javax.annotation")
    doRelocate("io.papermc.paperlib")
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
