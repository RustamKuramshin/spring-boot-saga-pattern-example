package ru.kuramshindev.mockservices;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class FaultInjectionService {

    private final Random random = new Random();

    @Value("${fault.injection.disabled:false}")
    private boolean faultInjectionDisabled;

    @SneakyThrows
    public ResponseEntity<String> handle(String successMessage) {

        log.info("ORDER SERVICE REQUEST: {}", successMessage);

        if (faultInjectionDisabled) {
            return ResponseEntity.ok("{\"message\": \"" + successMessage + "\"}");
        }

        if (random.nextDouble() < 0.5) {
            int delay = 100 + random.nextInt(901);
            Thread.sleep(delay);
        }

        double chance = random.nextDouble();
        if (chance < 0.1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request Error");
        } else if (chance < 0.2) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource Not Found");
        } else if (chance < 0.3) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

        return ResponseEntity.ok("{\"message\": \"" + successMessage + "\"}");
    }
}
