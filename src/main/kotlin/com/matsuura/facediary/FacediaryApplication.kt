package com.matsuura.facediary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FacediaryApplication

fun main(args: Array<String>) {
	runApplication<FacediaryApplication>(*args)
}
