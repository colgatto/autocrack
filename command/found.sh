#!/bin/sh
val=$(cowpatty -c -r $1 | sed -n '3p' | awk '{print $1}')
if [ $val = 'Collected' ]; then
	echo 1
else
	echo 0
fi
