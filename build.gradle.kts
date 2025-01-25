// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.8.0" apply false
    id("org.sonarqube") version "6.0.1.5171"
}

sonar {
  properties {
    property("sonar.projectKey", "FRC-1294_scouting-app")
    property("sonar.organization", "packofparts")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}
