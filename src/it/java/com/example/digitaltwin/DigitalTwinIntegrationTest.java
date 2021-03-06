package com.example.digitaltwin;

import com.example.digitaltwin.domain.DigitalTwinDomain;
import com.google.protobuf.Empty;
import kalix.javasdk.testkit.junit.KalixTestKitResource;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.UUID;

import static java.util.concurrent.TimeUnit.*;

// This class was initially generated based on the .proto definition by Kalix tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

// Example of an integration test calling our service via the Kalix proxy
// Run all test classes ending with "IntegrationTest" using `mvn verify -Pit`
public class DigitalTwinIntegrationTest {

  /**
   * The test kit starts both the service container and the Kalix proxy.
   */
  @ClassRule
  public static final KalixTestKitResource testKit =
    new KalixTestKitResource(Main.createKalix());

  /**
   * Use the generated gRPC client to call the service through the Kalix proxy.
   */
  private final DigitalTwinService client;

  public DigitalTwinIntegrationTest() {
    client = testKit.getGrpcClient(DigitalTwinService.class);
  }

  @Test
  public void happyPath() throws Exception {
      String dtId =  UUID.randomUUID().toString();
      String name = "name";
      long metricValueAlertThreshold = 10l;
      long metricValueOk = 5;
      long metricValueAlert = 11;

      DigitalTwinApi.CreateCommand create = DigitalTwinApi.CreateCommand.newBuilder()
              .setDtId(dtId)
              .setMetricValueAlertThreshold(metricValueAlertThreshold)
              .setName(name)
              .build();
      client.create(create).toCompletableFuture().get();

      DigitalTwinApi.GetDigitalTwinStateCommand get = DigitalTwinApi.GetDigitalTwinStateCommand.newBuilder().setDtId(dtId).build();
      DigitalTwinApi.DigitalTwinState state = client.getDigitalTwinState(get).toCompletableFuture().get();
      Assert.assertEquals(false, state.getMetricAlertActive());

      DigitalTwinApi.AddMetricCommand addOk = DigitalTwinApi.AddMetricCommand.newBuilder()
              .setDtId(dtId)
              .setMetricValue(metricValueOk)
              .build();
      client.addMetric(addOk);

      state = client.getDigitalTwinState(get).toCompletableFuture().get();
      Assert.assertEquals(false, state.getMetricAlertActive());

      DigitalTwinApi.AddMetricCommand addAlert = DigitalTwinApi.AddMetricCommand.newBuilder()
              .setDtId(dtId)
              .setMetricValue(metricValueAlert)
              .build();
      client.addMetric(addAlert);

      state = client.getDigitalTwinState(get).toCompletableFuture().get();
      Assert.assertEquals(true, state.getMetricAlertActive());
  }
}
