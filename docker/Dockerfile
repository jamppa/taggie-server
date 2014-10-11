FROM ubuntu:14.04
MAINTAINER Jani Arvonen <jani.arvonen@gmail.com>

# Update
RUN apt-get update

# Add developer user
RUN adduser taggie --home /home/taggie --gecos "" --disabled-password

# Prepare apt-get
RUN DEBIAN_FRONTEND=noninteractive apt-get -y install python-software-properties
RUN DEBIAN_FRONTEND=noninteractive apt-get -y install software-properties-common

# Install Java 8
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | /usr/bin/debconf-set-selections
RUN DEBIAN_FRONTEND=noninteractive apt-get -y install oracle-java8-installer

# Install Leiningen
RUN wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein -O /bin/lein && chmod +x /bin/lein

# Install MongoDB
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
RUN echo 'deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen' | tee /etc/apt/sources.list.d/10gen.list
RUN apt-get update && apt-get install -y mongodb-org=2.6.1 mongodb-org-server=2.6.1 mongodb-org-shell=2.6.1 mongodb-org-mongos=2.6.1 mongodb-org-tools=2.6.1
RUN mkdir -p /data/db && chown taggie:taggie /data/db

EXPOSE 27017
EXPOSE 28017
EXPOSE 3000

USER taggie
RUN mkdir -p dev
RUN lein
WORKDIR /home/taggie/dev

CMD /bin/bash
