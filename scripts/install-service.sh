#!/bin/bash
#
# Script installs fsesearch-app as init.d service

set -e

chown fsesearch-app-user:fsesearch-app-user /var/fsesearch-app/FSEUserSearch-0.0.1-SNAPSHOT.jar

# protect application from modifications
chmod 500 /var/fsesearch-app/FSEUserSearch-0.0.1-SNAPSHOT.jar
#chattr +i /var/sample-app/sample-app.jar

# create symlink to init.d
ln -s /var/fsesearch-app/FSEUserSearch-0.0.1-SNAPSHOT.jar /etc/init.d/fsesearch-app
sudo chmod 500 /etc/init.d/fsesearch-app
chkconfig fsesearch-app on
echo "Service installed."