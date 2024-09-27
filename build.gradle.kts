plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.46.1.0")

    implementation("com.github.gwenn:sqlite-dialect:0.1.4")
    implementation("org.hibernate:hibernate-core:5.5.7.Final")

    implementation("com.google.code.gson:gson:2.8.9")

    //testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}