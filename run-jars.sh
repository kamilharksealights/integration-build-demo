warehouseServiceVersion=`cat components.json | jq '.[] | select(.appName == "warehouse-service") | .build' | tr -d '"'`
productReviewsVersion=`cat components.json | jq '.[] | select(.appName == "product-reviews") | .build' | tr -d '"'`





echo "starting product-reivews, build name = '$productReviewsVersion'"
prStart="repository/product-reviews/product-reviews-${productReviewsVersion}-jar-with-dependencies.jar com.demo.reviews.Application"
nohup java -Dsl.tokenFile=sl-token.txt -Dsl.fileStorage=/tmp/bar  -Dsl.log.level=INFO -Dsl.log.toConsole=true -Dsl.labId=lab1 -javaagent:sl-test-listener.jar -cp $prStart > product-reviews.log &


sleep 10


wsStart="repository/warehouse-service/warehouse-${warehouseServiceVersion}-jar-with-dependencies.jar com.demo.warehouse.Application"
echo "starting warehouse-service, build name = '$warehouseServiceVersion'"
nohup java -Dsl.tokenFile=sl-token.txt -Dsl.fileStorage=/tmp/foo -Dsl.log.level=INFO -Dsl.log.toConsole=true -Dsl.labId=lab1 -javaagent:sl-test-listener.jar -cp $wsStart > warehouse-service.log &





echo 'started'

jps -l | grep com.demo



