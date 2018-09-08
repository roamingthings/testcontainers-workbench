package de.roamingthings.workbench.testcontainers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestcontainersWorkbenchApplication

fun main(args: Array<String>) {
    runApplication<TestcontainersWorkbenchApplication>(*args)
}
