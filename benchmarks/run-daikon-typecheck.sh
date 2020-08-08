#!/bin/bash

echo "This script compiles test project daikon"

WORKING_DIR=$(cd $(dirname "$0") && pwd)

## Fetch daikon projects
if [ -d $WORKING_DIR/daikon ] ; then
    (cd $WORKING_DIR/daikon)
else
    (cd $WORKING_DIR && git clone https://github.com/txiang61-benchmarks/daikon.git)
fi

echo "Compile PUnits"
: $(update-alternatives --config java <<< 2)
(cd $PUNITS && ./gradlew assemble)

echo "Run type check on Daikon"
(cd $WORKING_DIR/daikon/java && make check-units)
