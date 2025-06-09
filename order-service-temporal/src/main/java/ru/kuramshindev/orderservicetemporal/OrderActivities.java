package ru.kuramshindev.orderservicetemporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;


@ActivityInterface
public interface OrderActivities {

    @ActivityMethod
    void makePayment();

    @ActivityMethod
    void cancelPayment();

    @ActivityMethod
    void reserveInventory();

    @ActivityMethod
    void releaseInventory();

    @ActivityMethod
    void createShipping();

    @ActivityMethod
    void cancelShipping();
}
