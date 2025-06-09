package ru.kuramshindev.mockservices;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MockController {

    private final FaultInjectionService faultInjectionService;

    @PostMapping("/payments")
    public ResponseEntity<String> processPayment() {
        return faultInjectionService.handle("payment processed");
    }

    @PostMapping("/payments/cancel")
    public ResponseEntity<String> cancelPayment() {
        return faultInjectionService.handle("payment cancelled");
    }

    @PostMapping("/inventory/reserve")
    public ResponseEntity<String> reserveInventory() {
        return faultInjectionService.handle("inventory reserved");
    }

    @PostMapping("/inventory/release")
    public ResponseEntity<String> releaseInventory() {
        return faultInjectionService.handle("inventory released");
    }

    @PostMapping("/shipping/create")
    public ResponseEntity<String> createShipping() {
        return faultInjectionService.handle("shipping created");
    }

    @PostMapping("/shipping/cancel")
    public ResponseEntity<String> cancelShipping() {
        return faultInjectionService.handle("shipping cancelled");
    }
}
