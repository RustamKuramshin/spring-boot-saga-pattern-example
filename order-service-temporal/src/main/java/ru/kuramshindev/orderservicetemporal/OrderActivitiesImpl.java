package ru.kuramshindev.orderservicetemporal;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuramshindev.mockserviceclient.services.MockServiceClient;

@RequiredArgsConstructor
@Service
public class OrderActivitiesImpl implements OrderActivities {

    private final MockServiceClient client;

    @Override
    public void makePayment() {
        client.makePayment();
    }

    @Override
    public void cancelPayment() {
        client.cancelPayment();
    }

    @Override
    public void reserveInventory() {
        client.reserveInventory();
    }

    @Override
    public void releaseInventory() {
        client.releaseInventory();
    }

    @Override
    public void createShipping() {
        client.createShipping();
    }

    @Override
    public void cancelShipping() {
        client.cancelShipping();
    }
}
