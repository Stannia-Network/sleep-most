plugins {
    kotlin("jvm") version "1.7.0"
    java
}

repositories {
    mavenCentral()
    maven(url = "https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven(url = "https://repo.codemc.org/repository/maven-public")
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.0")

    compileOnly("org.spigotmc:spigot-api:1.19-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.10.9")
    compileOnly("org.apache.commons:commons-lang3:3.1")
    compileOnly("org.jetbrains:annotations:23.0.0")

    //implementation("org.bstats:bstats-bukkit:1.8")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.3")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-inline:4.4.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.4.0")
    testImplementation("org.mockito:mockito-core:4.4.0")
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(
        configurations.runtimeClasspath.get()
            //.filter { !it.toString().contains(".gradle") }
            .onEach { println("add from dependencies: ${it.name}") }
            .map { if (it.isDirectory) it else zipTree(it) }
    )
}