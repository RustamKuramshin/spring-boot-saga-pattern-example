package ru.kuramshindev.orderservicetemporal;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {

    @PostMapping("/api/orders")
    public String startOrderSaga() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);

        OrderWorkflow workflow = client.newWorkflowStub(
                OrderWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue("ORDER_TASK_QUEUE")
                        .setWorkflowId("order-" + UUID.randomUUID())
                        .build()
        );

        WorkflowClient.start(workflow::processOrder);
        return "Saga started";
    }
}
