#!/bin/sh
{ airodump-ng $1 --output-format pcap,csv -w cap/generalDump --write-interval 1 2> /dev/null; } & PID=$!
sleep $2
kill -TERM $PID
