package ru.kuramshindev.mockservices;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FaultInjectionService {

    private final Random random = new Random();

    @SneakyThrows
    public ResponseEntity<String> handle(String successMessage) {
        // Simulate delay
        if (random.nextDouble() < 0.5) { // 50% chance
            int delay = 100 + random.nextInt(901); // 100–1000 ms
            Thread.sleep(delay);
        }

        // Simulate error
        double chance = random.nextDouble();
        if (chance < 0.1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request Error");
        } else if (chance < 0.2) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource Not Found");
        } else if (chance < 0.3) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

        // Success
        return ResponseEntity.ok("{\"message\": \"" + successMessage + "\"}");
    }
}
