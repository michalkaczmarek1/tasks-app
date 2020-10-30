#!/usr/bin/env bash

failInfo() {
  echo "There were errors"
}

successInfo() {
  echo "Get tasks successfull"
}

openBrowse() {
  /usr/bin/firefox --new-window http://localhost:8080/crud/v1/task/getTasks
}

if ./runcrud.sh; then
  openBrowse
  successInfo
else
  failInfo
fi

