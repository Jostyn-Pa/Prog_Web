plugins {
    id("java")
    id("io.freefair.lombok") version "9.5.0"
}

group = "com.prog.web"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jboss.resteasy:resteasy-client:7.0.2.Final")
    implementation("org.jboss.resteasy:resteasy-json-binding-provider:7.0.2.Final")
}

tasks.test {
    useJUnitPlatform()
}