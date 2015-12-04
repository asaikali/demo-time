# demo-time
Demo SpringBoot App that prints out the time to demonstrate Blue Green Deloyments on Cloud Foundry


```shell
# production app settings 
APP_PROD_ROUTE=demo-time
APP_PROD_NAME=demo 

# define app properties
APP_NAME=demo-$BUILD_NUMBER
APP_JAR=$WORKSPACE/demo-time/target/demo-time-0.0.1-SNAPSHOT.jar
APP_TEMP_ROUTE=demo-time-$BUILD_NUMBER 


# Environment Settings
ORG=demo
SPACE=prod
DOMAIN=cfapps.io

# target an environment where all the services are already defined 
cf target -o $ORG -s $SPACE

# push the application with a manifest that binds all required services 
cf push $APP_NAME -p $APP_JAR -n $APP_TEMP_ROUTE -b java_buildpack_offline -m 256M

# Run Tests on the newly deployed app check that it is okay
echo ""
curl http://$APP_TEMP_ROUTE.$DOMAIN
echo ""

# start directing traffic to the new app instance 
cf map-route $APP_NAME $DOMAIN -n $APP_PROD_ROUTE

# scale up the new app instance
cf scale $APP_NAME -i 2 

# scale down the proi app instances 
cf scale $APP_PROD_NAME -i 1

# stop taking traffic on the current prod instance  
cf unmap-route $APP_PROD_NAME $DOMAIN -n $APP_PROD_ROUTE  

# decommission the old app 
cf stop $APP_PROD_NAME
cf rename $APP_PROD_NAME replaced-with-build-$BUILD_NUMBER

# make the current app the new prod application 
cf unmap-route $APP_NAME $DOMAIN -n $APP_TEMP_ROUTE 
cf rename $APP_NAME $APP_PROD_NAME 
```
