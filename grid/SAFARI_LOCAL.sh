#!/usr/bin/env bash
java -jar selenium-server-standalone-3.11.0.jar -role node -browser platform=MAC,browserName=safari,maxInstances=5,seleniumProtocol=WebDriver,acceptSslCerts=true -hub http://localhost:4444/grid/register -port 5553
