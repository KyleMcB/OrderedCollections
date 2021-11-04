plugins {
    kotlin("jvm") version "1.5.31"
    `maven-publish`
}

group = "com.github.KyleMcB"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}