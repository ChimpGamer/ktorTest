val ktorVersion = "2.3.7"

plugins {
    application
    id("java")
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "nl.chimpgamer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("io.ktor:ktor-server-core:$ktorVersion") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    implementation("io.ktor:ktor-server-jetty:$ktorVersion") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    }
    implementation("io.ktor:ktor-server-auth:$ktorVersion") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    }
    implementation("io.ktor:ktor-server-default-headers:$ktorVersion") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    }
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    }
    implementation("io.ktor:ktor-serialization-gson:$ktorVersion") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
        /*exclude(group = "com.google.code.gson", module = "gson")*/
    }

    implementation("org.apache.logging.log4j:log4j-core:2.22.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.22.1")
}

application {
    mainClass = "nl.chimpgamer.ktortest.KtorTestKt"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    shadowJar {
        archiveFileName.set("${project.name}-v${project.version}.jar")

        mergeServiceFiles()

        relocate("kotlin", "${project.group}.shaded.kotlin")
        relocate("io.ktor", "${project.group}.shaded.ktor")
    }
}