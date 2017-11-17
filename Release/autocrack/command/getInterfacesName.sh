#!/bin/sh
iw dev | grep "Interface" | awk '{ print $2}'