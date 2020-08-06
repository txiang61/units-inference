#!/bin/bash

echo "This script compiles test project daikon"

WORKING_DIR=$(cd $(dirname "$0") && pwd)
export CHECKERFRAMEWORK=/home/jsr308/checker-framework

## Fetch checker-framework-inference
if [ -d $WORKING_DIR/daikon ] ; then
    (cd $WORKING_DIR/daikon && git pull)
else
    BRANCH=master
    echo "Cloning from branch" $BRANCH
    (cd $WORKING_DIR && git clone -b $BRANCH --depth 1 https://github.com/txiang61/daikon.git)
fi

(cd $WORKING_DIR/daikon/java && make check-units)
