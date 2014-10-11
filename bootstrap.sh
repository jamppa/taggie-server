#!/bin/bash
sudo docker run -t -i --name taggie_server_dev -v `pwd`:/home/taggie/dev -P jamppa/taggie_server