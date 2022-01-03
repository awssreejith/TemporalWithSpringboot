#!/bin/bash

mvn clean install -Dmaven.test.skip

echo "Creating new images"
pushd ./MainService
sudo docker build -t mainservice .
popd

pushd ./ProviderService
sudo docker build -t providerservice .
popd
