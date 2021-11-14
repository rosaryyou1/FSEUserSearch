#!/bin/bash
#
# Script installs Java and creates user and folder for fsesearch-app service

set -e

# yum update -y

# install java if needed
# TODO java8
#{ which java; } || { yum install java -y; }
{ which java; } || {yum install java-11-openjdk}
# create app user
useradd --shell /sbin/nologin --system --user-group fsesearch-app-user

# create app directory
mkdir -p /var/fsesearch-app
chown fsesearch-app-user /var/fsesearch-app
chgrp fsesearch-app-user /var/fsesearch-app