#!/bin/bash

if ./runcrud.sh; then
  open http://localhost:8080/crud/v1/task/tasks
else
   echo "We have problem."
fi