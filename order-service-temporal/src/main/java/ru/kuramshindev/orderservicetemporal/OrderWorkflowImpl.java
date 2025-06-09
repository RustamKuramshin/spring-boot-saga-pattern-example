package ru.kuramshindev.orderservicetemporal;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

import java.time.Duration;


public class OrderWorkflowImpl implements OrderWorkflow {

    private final ActivityOptions options =
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .build();

    private final OrderActivities activities = Workflow.newActivityStub(OrderActivities.class, options);

    @Override
    public void processOrder() {
        Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(false).build();
        Saga saga = new Saga(sagaOptions);

        try {
            activities.makePayment();
            saga.addCompensation(activities::cancelPayment);

            activities.reserveInventory();
            saga.addCompensation(activities::releaseInventory);

            activities.createShipping();
            saga.addCompensation(activities::cancelShipping);

        } catch (Exception e) {
            saga.compensate();
            throw Workflow.wrap(e);
        }
    }
}