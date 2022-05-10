# IoT Digital Twin DEMO - Java - Event Sourced

## Prerequisite
- Java 11 or later<br>
- Apache Maven 3.6 or higher<br>
- Kalix:
    - Register account: [Register](https://console.kalix.io/register)
    - `kalix` tool installed: [Kalix CLI](https://docs.kalix.io/kalix/install-kalix.html)
    - `kalix` login
    -  project `demo` created and set for `kalix`
- Docker 20.10.8 or higher (engine and client)<br>
- Docker Hub account (configured with Docker)<br>
  Access to the `gcr.io/kalix-public` container registry<br>
  cURL<br>
  IDE / editor<br>

## Generate Java project (terminal)

```
mvn archetype:generate \
-DarchetypeGroupId=io.kalix \
-DarchetypeArtifactId=kalix-maven-archetype \
-DarchetypeVersion=LATEST
```

```
Define value for property 'groupId': com.example
Define value for property 'artifactId': digitaltwin
Define value for property 'version' 1.0-SNAPSHOT: :
Define value for property 'package' com.example: : com.example.digitaltwin
```

## Import in IDE

## Cleanup (IDE)

Delete:<br>
`src/main/proto/com/example/shoppingcart/counter_api.proto`<br>
`src/main/proto/com/example/shoppingcart/domain/counter_domain.proto`

## API descriptor - endpoints (IDE)

Note: For code snippet insertion use command+J (MAC)<br>

1. Create file `digitaltwin_api.proto` in `src/main/proto/com/example/digitaltwin` folder.<br>
2. Edit `src/main/proto/com/example/digitaltwin/digitaltwin_api.proto` in IDE <br>
3. Insert header snippet: `aheader`
4. Insert commands snippet: `acmd`
5. Insert state snippet: `astate`
6. Insert service snippet: `asrv`
7. Add functions to service snippet (place cursor inside brackets `service DigitalTwinService { }`): `afunc`

## API descriptor - domain (IDE)

1. Create file `digitaltwin_domain.proto` in `src/main/proto/com/example/digitaltwin/domain` folder.<br>
2. Edit `src/main/proto/com/example/digitaltwin/domain/digitaltwin_domain.proto` in IDE <br>
3. Insert header snippet: `dheader`
4. Insert events snippet: `devts`
5. Insert state snippet: `dstate`

## API descriptor - codegen annotations (IDE)

1. Edit `src/main/proto/com/example/digitaltwin/digitaltwin_api.proto`
2. Insert codegen annotations (place cursor under `service DigitalTwinService {` ): `acodegen`

## Codegen

1. Code generation (terminal):
```
mvn compile
```
2. Refresh project (IDE)
3. Trigger Maven sync (IDE)


## Business logic implementation (IDE)

1. Edit `src/main/java/com/example/digitaltwin/domain/DigitalTwin` class
2. Delete class body
3. Insert code snippet (delete everything under constructor): `eall`

## Implement unit test
1. Edit `src/test/java/com/example/digitaltwin/domain/DigitalTwinTest` class
2. Delete class body
3. Insert code snippet (delete everything under constructor): `ut`

## Run unit test (terminal)
```
mvn test
```

## Implement integration test (IDE)
1. Edit `src/it/java/com/example/digitaltwin/DigitalTwinIntegrationTest` class
2. Delete everything under the constructor
3. Insert code snippet (delete everything under constructor): `it`

## Run integration test (terminal)
```
mvn -Pit verify
```
## Run locally
??

## Package & deploy
1. Edit `pom.xml` and update `my-docker-repo` in `<dockerImage>my-docker-repo/${project.artifactId}</dockerImage>`
2. Execute in terminal:
```
mvn deploy
```

## Expose service
```
kalix services expose digitaltwin
```
```
Service 'digitaltwin' was successfully exposed at: winter-bonus-0316.eu-central-1.kalix.app
```
Note: HOSTNAME to use for external access

## Test service in production
1. Create digital twin
```
curl -XPOST -d '{
  "name": "DT1",
  "metric_value_alert_threshold": "10"
}' https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1/create -H "Content-Type: application/json"
```
2. Add OK metric
```
curl -XPOST -d '{
  "metric_value": "5"
}' https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1/add-metric -H "Content-Type: application/json"
```
3. Get cart
```
curl -XGET https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1 -H "Content-Type: application/json"
```
4. Add ALERT metric
```
curl -XPOST -d '{
  "metric_value": "11"
}' https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1/add-metric -H "Content-Type: application/json"
```
5. Get cart
```
curl -XGET https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1 -H "Content-Type: application/json"
```

## Eventing (optional)

1. Create file `digitaltwin_topic.proto` in `src/main/proto/com/example/digitaltwin` folder.<br>
2. Edit `src/main/proto/com/example/digitaltwin/digitaltwin_topic.proto` in IDE <br>
3. Insert header snippet: `theader`
4. Insert events snippet: `tevts`
5. Insert service snippet: `tsrv`

8. Code generation (terminal):
```
mvn compile
```
9. Refresh project (IDE)
10. Trigger Maven sync (IDE)
11. Edit `src/main/java/com/example/digitaltwin/DigitalTwinToTopicAction` class
12. Delete class body
13. Insert code snippet (delete everything under constructor): `tall`


## Copy-paste list
```
mvn archetype:generate \
-DarchetypeGroupId=io.kalix \
-DarchetypeArtifactId=kalix-maven-archetype \
-DarchetypeVersion=LATEST
```
```
com.example
```
```
digitaltwin
```
```
com.example.digitaltwin
```
```
digitaltwin_api.proto
```
```
digitaltwin_domain.proto
```
```
mvn compile
```
```
mvn test
```
```
mvn -Pit verify
```
```
mvn deploy
```
```
curl -XPOST -d '{
  "name": "DT1",
  "metric_value_alert_threshold": "10"
}' https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1/create -H "Content-Type: application/json"
```
```
curl -XPOST -d '{
  "metric_value": "5"
}' https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1/add-metric -H "Content-Type: application/json"
```
```
curl -XGET https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1 -H "Content-Type: application/json"
```
```
curl -XPOST -d '{
  "metric_value": "11"
}' https://winter-bonus-0316.eu-central-1.kalix.app/digitaltwin/1/add-metric -H "Content-Type: application/json"
```
```
digitaltwin_topic.proto
```