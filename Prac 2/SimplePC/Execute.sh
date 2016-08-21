#!/bin/bash

javac -cp .:jcsp-1.1-rc4/jcsp.jar Producer.java
javac -cp .:jcsp-1.1-rc4/jcsp.jar Consumer.java
javac -cp .:jcsp-1.1-rc4/jcsp.jar Buffer.java
javac -cp .:jcsp-1.1-rc4/jcsp.jar PCMain.java

java -cp .:jcsp-1.1-rc4/jcsp.jar PCMain
