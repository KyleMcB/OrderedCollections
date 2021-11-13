/*
 * Copyright (c) 2021. Kyle McBurnett
 */

plugins {
    `java-library`
    id("org.jetbrains.kotlin.libs.publisher") version "0.0.60-dev-32"
    kotlin("jvm") version "1.5.31"
    `maven-publish`
    jacoco

}

group = "com.xingpeds.orderedcollections"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("OrderedCollections") {
                from(components["java"])
                groupId = "com.github.KyleMcB"
                artifactId = "orderedcollections"
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

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}
dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(platform("org.junit:junit-bom:5.8.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}