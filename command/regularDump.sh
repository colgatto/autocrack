#!/bin/sh
{ airodump-ng $1 --bssid $2 -c $3  --output-format pcap,csv -w $4; } & PID=$!
sleep $5
kill -TERM $PID
