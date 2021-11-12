/*
 * Copyright (c) 2021. Kyle McBurnett
 */

plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "com.xingpeds.orderedcollections"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("OrderedCollections") {
                from(components["java"])
                groupId = "com.github.KyleMcB.orderedcollections"
                artifactId = "tests"
            }
        }
    }
    repositories {
        mavenLocal()
    }
}
tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
dependencies {
    implementation(project(":interfaces"))
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    implementation(kotlin("test"))
    testImplementation(platform("org.junit:junit-bom:5.8.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}