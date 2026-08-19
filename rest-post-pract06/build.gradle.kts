plugins {
    id("java")
    id("io.freefair.lombok") version "9.5.0"
}

group = "com.prog.web"
version = "unspecified"

repositories {
    mavenCentral()
}

var weldVersion = "6.0.4.Final"
var deltaVersion = "2.0.1"
var restVersion = "7.0.2.Final"
var hibernateVersion = "7.4.1.Final"

dependencies {
    //REST
    implementation("org.jboss.resteasy:resteasy-core:${restVersion}")
    implementation("org.jboss.resteasy:resteasy-undertow-cdi:${restVersion}")
    implementation("org.jboss.resteasy:resteasy-json-binding-provider:${restVersion}")

    //Delta Spike
    implementation("org.apache.deltaspike.modules:deltaspike-data-module-api:${deltaVersion}")
    implementation("org.apache.deltaspike.modules:deltaspike-data-module-impl:${deltaVersion}")

    //JPA
    implementation("org.hibernate.orm:hibernate-core:${hibernateVersion}")
    implementation("org.postgresql:postgresql:42.7.11")

    //CDI
    implementation("org.jboss.weld:weld-core-impl:${weldVersion}")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.1.0")

    //implementation("jakarta.ws.rs:jakarta.ws.rs-api:4.0.0")
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