plugins {
  `java-library`
}

group = "net.pl3x.backondeath"
version = "0.0.1"
description = "Adds a /back command to get back to your last death location"

java {
  toolchain.languageVersion = JavaLanguageVersion.of(25)
}

repositories {
  mavenCentral()
}

dependencies {
  // todo - replace with maven repo when available
  compileOnly(files(System.getenv("HYTALE_SERVER")))

  compileOnly("org.jetbrains:annotations:26.0.2-1")
}

tasks {
  compileJava {
    options.encoding = Charsets.UTF_8.name()
    options.release = 25
  }

  processResources {
    filteringCharset = Charsets.UTF_8.name()

    // work around IDEA-296490
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    with(copySpec {
      include("manifest.json")
      from("src/main/resources") {
        expand(
          "artifact" to "${rootProject.name}",
          "group" to "${project.group}",
          "version" to "${project.version}",
          "description" to "${project.description}",
        )
      }
    })
  }
}
