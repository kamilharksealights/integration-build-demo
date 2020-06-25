## Intro
This is an integration applictions consisting of 3 components:
 - product-reviews (java,maven,one module)
 - warehouse-service (java,maven,multiple modules)
 - e2e-tests (node,tests only)

## Building
(all the commands should be executed from the project root dir)

 1. download sealights agent jars: `./download-agents.sh`
 2. put agent token into `sl-token.txt` file
    the files structure should look like this
    
    ```
     ls .../integration-application-demo
	     sl-token.txt
	     sl-build-scanner.jar
	     sl-test-listener.jar
	     ...
    ```
 3. build the components
   - product-reviews: ```mvn -f product-reviews/pom.xml clean package -Dsl.build=xxxyyy -Dsl.token=sl-token.txt -Dsl.test-listener.path=`realpath sl-test-listener.jar` -Dsl.build-scanner.path=`realpath sl-build-scanner.jar` -Psl```
   - warehouse-service: ```mvn -f warehouse-service/pom.xml clean package -Dsl.build=xxxyyy -Dsl.token=sl-token.txt -Dsl.test-listener.path=`realpath sl-test-listener.jar` -Dsl.build-scanner.path=`realpath sl-build-scanner.jar` -Psl```
  (each produced jar contains the build number in its name)
 4. save the built jars to repository: `./save-jars.sh` (repository is just a folder)

## Running integration build
 1. Modify the `components.json` file by providing the desired component versions
 2. Run the applications with test-listener attached: `./run-jars.sh` (the jars versions will be selected based on `components.json`)
 3. Start the integration build (this will report integration build and execute e2e tests): `./run-integration.sh integrationBuildNumber`
 4. Stop the applications: `./stop-jars.sh`
 

## Example
 1. Build `product-reviews` application, build=pr-1:  ```mvn -f product-reviews/pom.xml clean package -Dsl.build=pr-1 -Dsl.token=sl-token.txt -Dsl.test-listener.path=`realpath sl-test-listener.jar` -Dsl.build-scanner.path=`realpath sl-build-scanner.jar` -Psl```
 2. Build `warehouse-service` application, build=ws-1:  ```mvn -f warehouse-service/pom.xml clean package -Dsl.build=ws-1 -Dsl.token=sl-token.txt -Dsl.test-listener.path=`realpath sl-test-listener.jar` -Dsl.build-scanner.path=`realpath sl-build-scanner.jar` -Psl```
 3. Save produced jars into the repository: `./save-jars.sh`
 4. Modify `components.json`, it should look like this:
 ```json
[
  {"appName":"warehouse-service","branch":"master","build":"ws-1"},
  {"appName":"product-reviews","branch":"master","build":"pr-1"}
]
```
 5. Start the applications: `./run-jars.sh` 
 6. Start the integration build, build=it-1:  `./run-integration.sh it-1`
 7. Stop the applications: `./stop-jars.sh`
 8. Inspect the Sealights dashboard, there should be 3 new applications shown: `product-reviews`, `warehouse-service` and `demo-integration-build`.
   - `warehouse-service` should have some code coverage for `Unit Tests` test stage
   - `demo-integration-build` should have some code coverage for `e2e` test stage.  

## Troubleshooting

 1. `product-reviews` and/or `warehouse-service` are not visible on the dashboard: 
   - make sure the `sl-token.txt` is present and populated with proper value
   - inspect the maven logs
 2. code coverage is not being shown:
   - inspect the maven logs
   - inspect the logs of applications: `warehouse-service.log` and `product-reviews.log` 
