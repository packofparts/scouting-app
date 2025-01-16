buildscript {
    val agp_version by extra("8.7.3")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.sonarqube") version "4.4.1.3373"
}

sonar {
  properties {
    property("sonar.projectKey", "FRC-1294_scouting-app")
    property("sonar.organization", "packofparts")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}
