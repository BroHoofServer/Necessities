plugins {
    java
}

group "moe.lumii.spigot"
version "1.0.0-release.1"

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/") // "spigotmc-repo"
    maven(url = "https://oss.sonatype.org/content/groups/public/") // "sonatype"
}

dependencies {
    implementation(group = "org.spigotmc", name = "spigot-api", version = "1.19-R0.1-SNAPSHOT")
    
    annotationProcessor(group = "org.spigotmc", name = "plugin-annotations", version = "1.2.3-SNAPSHOT")
    implementation(group = "org.spigotmc", name = "plugin-annotations", version = "1.2.3-SNAPSHOT")

    annotationProcessor(group = "org.jetbrains", name = "annotations", version = "23.0.0")
    implementation(group = "org.jetbrains", name = "annotations", version = "23.0.0")
}

/*
def targetJavaVersion = 17
java {
    def javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
}

tasks.withType(JavaCompile).configureEach {
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
        options.release = targetJavaVersion
    }
}

processResources {
    def props = [version: version]
    inputs.properties props
    filteringCharset "UTF-8"
    filesMatching("plugin.yml") {
        expand props
    }
}*/
