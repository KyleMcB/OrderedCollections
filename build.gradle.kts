plugins {
    `java-library`
    id("org.jetbrains.kotlin.libs.publisher") version "0.0.60-dev-32"
    kotlin("jvm") version "1.5.31"
    `maven-publish`
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
dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}