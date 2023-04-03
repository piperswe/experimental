plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "6.6.1"
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.lombok") version "1.8.20"

    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.javamodularity.moduleplugin") version "1.8.12"
}

buildscript {
    configurations.classpath {
        resolutionStrategy.activateDependencyLocking()
        dependencyLocking {
            lockMode.set(LockMode.STRICT)
        }
    }
}

group = "me.piperswe"
version = "1.0-SNAPSHOT"

tasks.withType<JavaCompile> {
    sourceCompatibility = "19"
    targetCompatibility = "19"
    options.compilerArgs.addAll(
        listOf(
            "--enable-preview",
            "--add-modules", "jdk.incubator.concurrent"
        )
    )
}

tasks.withType<JavaExec> {
    jvmArgs("--enable-preview")
}

tasks.withType<Test> {
    jvmArgs("--enable-preview")
}

application {
    mainClass.set("me.piperswe.Main")
    applicationDefaultJvmArgs = listOf("--enable-preview")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("ch.qos.logback:logback-core:1.4.5")
    implementation("ch.qos.logback:logback-classic:1.4.5")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

dependencyLocking {
    lockAllConfigurations()
    lockMode.set(LockMode.STRICT)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

javafx {
    version = "19.0.2.1"
    modules("javafx.controls", "javafx.fxml")
}

tasks.getByName<JavaExec>("run") {
    standardInput = System.`in`
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}