#!/bin/bash

rmiregistry 2001 &

javac MessageStorage.java
javac MessageServer.java
javac MessageClient.java

java MessageServer
java MessageClient
