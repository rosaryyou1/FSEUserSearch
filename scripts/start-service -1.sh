#!/bin/bash
#
# Script starts fsesearch-app service

set -e
ln -s /var/fsesearch-app/FSEUserSearch-0.0.1-SNAPSHOT.jar /etc/init.d/fsesearch-app
service fsesearch-app start
echo "Service started."