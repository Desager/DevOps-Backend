package ru.devops.backend.api.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

@CrossOrigin(origins = ["*"])
@RestController
class BasicController {

    private val path = Paths.get("data.txt")

    @PostMapping("/save")
    fun saveData(
        @RequestBody data: String
    ): ResponseEntity<String> {
        try {
            Files.write(
                path,
                (data + System.lineSeparator()).toByteArray(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            )

            return ResponseEntity.ok("Data: '$data' saved")
        } catch (e: IOException) {
            return ResponseEntity.status(500).body("Error saving data: '$data'")
        }
    }
}
