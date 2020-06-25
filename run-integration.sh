mkdir -p integ-workspace

cd e2e-tests
rm buildSessionId
npm i
npm run tsc

./node_modules/.bin/slnodejs config --tokenfile ../sl-token.txt --appname "demo-integration-build" --branch "master" --build $1
./node_modules/.bin/slnodejs build --tokenFile ../sl-token.txt --buildsessionid `cat buildSessionId` --dependenciesFile ../components.json --workspacepath ../integ-workspace --scm none


./node_modules/.bin/slnodejs mocha --tokenfile ../sl-token.txt --buildSessionIdFile buildSessionId  --labid lab1 --teststage "e2e" -- tsOutputs/ --recursive --timeout 0 


#./node_modules/.bin/slnodejs end --tokenfile ../sl-token.txt --labid lab1 --buildsessionidfile buildSessionId




