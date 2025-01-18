plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.sonarqube") version "6.0.1.5171"
}

sonar {
  properties {
    property("sonar.projectKey", "scouting-app")
    property("sonar.organization", "packofparts")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}
