#!/bin/bash

# Set the start time
start_time=$(date +%s)

for i in $(seq 60); do

  curl -sI http://localhost | grep HTTP

  sleep 1
done

# Calculate the end time
end_time=$(date +%s)


