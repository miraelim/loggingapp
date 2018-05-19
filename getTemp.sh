#!/bin/bash
while :
    do
    cpuTemp0=$(cat /sys/class/thermal/thermal_zone0/temp)
    cpuTemp1=$(($cpuTemp0/1000))
    cpuTemp2=$(($cpuTemp0/100))
    cpuTempM=$(($cpuTemp2 % $cpuTemp1))
    uUID=$(uuidgen -t)
date=$(date)

    echo CPU temp"="$cpuTemp1"."$cpuTempM", " GPU $(/opt/vc/bin/vcgencmd measure_temp)", " UUID"="$uUID"," date"="$date

#echo GPU $(/opt/vc/bin/vcgencmd measure_temp)
#    echo UUID"="$uUI
#   echo date"="$date
    sleep 1
    done
