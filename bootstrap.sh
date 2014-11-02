#!/bin/bash
sudo docker stop mongodb
sudo docker rm mongodb
sudo docker run -d -p 27017:27017 -v `pwd`/db:/data/db --name mongodb dockerfile/mongodb
sudo docker run -ti --rm --name taggie_server -v `pwd`:/home/taggie/dev -v ~/.m2:/home/taggie/.m2 --link mongodb:db -p 3000:3000 jamppa/taggie_server