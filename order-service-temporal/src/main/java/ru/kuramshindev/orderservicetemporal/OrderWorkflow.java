package ru.kuramshindev.orderservicetemporal;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderWorkflow {
    @WorkflowMethod
    void processOrder();
}
