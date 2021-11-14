#!/bin/bash
#
# Script stops fsesearch-app service if it is running

set -e

if (( $(ps -ef | grep -v grep | grep fsesearch-app | wc -l) > 0 ))
then
    service fsesearch-app stop
    echo "Service stopped."
fi