/*
 * Copyright (c) 2021. Kyle McBurnett
 */

plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "com.github.KyleMcB.orderedcollections"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("OrderedCollections") {
            from(components["java"])
            artifactId = "impl"
        }
    }
}
repositories {
    mavenLocal()
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
dependencies {
    implementation(project(":core"))
    implementation(kotlin("stdlib"))
    implementation(project(":OrderedCollectionTests"))
    testImplementation(platform("org.junit:junit-bom:5.8.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
}