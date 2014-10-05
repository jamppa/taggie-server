#!/bin/bash
sudo docker build -t groupie-server .
sudo docker run -t -i --name groupie-server-dev -v `pwd`:/home/groupie/dev -P groupie-server