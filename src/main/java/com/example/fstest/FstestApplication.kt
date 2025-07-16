package com.example.fstest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
object FstestApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(FstestApplication::class.java, *args)
    }
}
