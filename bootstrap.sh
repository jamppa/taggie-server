#!/bin/bash
sudo docker build -t taggie-server .
sudo docker run -t -i --name taggie-server-dev -v `pwd`:/home/taggie/dev -P taggie-server