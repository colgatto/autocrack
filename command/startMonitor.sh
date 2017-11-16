#!/bin/sh
airmon-ng check kill | airmon-ng start $1 | grep "monitor mode" | awk '{print $9}' | cut -d']' -f2 | sed 's/.$//'
