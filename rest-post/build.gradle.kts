plugins {
    id("java")
    id("io.freefair.lombok") version "9.5.0"
}

group = "com.prog.web"
version = "unspecified"

repositories {
    mavenCentral()
}

val weldVersion = "6.0.4.Final"
val hibernateVersion = "7.4.1.Final"
val restEasyVersion = "7.0.2.Final"
val deltaSpikeVersion= "2.0.1"

dependencies {

    implementation("jakarta.ws.rs:jakarta.ws.rs-api:4.0.0")
    //CDI
    implementation("org.jboss.weld:weld-core-impl:${weldVersion}")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.1.0")

    //JPA
    implementation("org.hibernate.orm:hibernate-core:${hibernateVersion}")
    implementation("org.postgresql:postgresql:42.7.11")

    //REST
    implementation("org.jboss.resteasy:resteasy-core:${restEasyVersion}")
    //implementation("org.jboss.resteasy:resteasy-undertow:${restEasyVersion}")
    implementation("org.jboss.resteasy:resteasy-undertow-cdi:${restEasyVersion}")
    implementation("org.jboss.resteasy:resteasy-json-binding-provider:${restEasyVersion}")

    // Source: https://mvnrepository.com/artifact/org.apache.deltaspike.core/core-project
    implementation("org.apache.deltaspike.modules:deltaspike-data-module-api:${deltaSpikeVersion}")
    implementation("org.apache.deltaspike.modules:deltaspike-data-module-impl:${deltaSpikeVersion}")

}

tasks.withType<JavaCompile> {
    options.release.set(21)
}

sourceSets {
    main {
        output.setResourcesDir(
            file("${buildDir}/classes/java/main")
        )
    }
}