## Intro
This is an integration applictions consisting of 3 components:
 - product-reviews (java,maven,one module)
 - warehouse-service (java,maven,multiple modules)
 - e2e-tests (node,tests only)

## Building
 1. download sealights agent jars: `./download-agents.sh`
 2. put agent token into `sl-token.txt` file
    the files structure should look like this
    `.../integration-application-demo`
	 `/sl-token.txt`
	 `/sl-build-scanner.jar`
	 `/sl-test-listener.jar`
	 `/...`
 3. build the components
   - product-reviews: "mvn -f product-reviews/pom.xml clean package -Dsl.build=xxxyyy -Dsl.token=sl-token.txt -Dsl.test-listener.path=`realpath sl-test-listener.jar` -Dsl.build-scanner.path=`realpath sl-build-scanner.jar` -Psl"
   - warehouse-service: "mvn -f warehouse-service/pom.xml clean package -Dsl.build=xxxyyy -Dsl.token=sl-token.txt -Dsl.test-listener.path=`realpath sl-test-listener.jar` -Dsl.build-scanner.path=`realpath sl-build-scanner.jar` -Psl"
  (each produced jar contains the build number in its name)
 4. save the built jars to repository: `./save-jars.sh` (repository is just a folder)

## Running integrtion build
 1. Modify the `components.json` file by providing the desired component versions
 2. Run the jars with test-listener attached: `./run-jars.sh` (the jars versions will be selected based on `components.json`)
 3. Start the integration build (this will report integration build and execute e2e tests): `./run-integration.sh integrationBuildNumber`
 4. Stop the jars: `./stop-jars.sh`
 


