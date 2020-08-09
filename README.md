# Units Inference
[![Build Status](https://travis-ci.org/opprop/units-inference.svg?branch=master)](https://travis-ci.org/opprop/units-inference)

Units Inference System

## Dependencies

[Checker Framework](https://github.com/opprop/checker-framework) and [Checker Framework Inference](https://github.com/opprop/checker-framework-inference)

python 2.7, with pip, and packages yaml and mozilla lithium:

sudo apt-get install --yes python python-pip python-yaml

pip install lithium-reducer

microsoft z3

coq v8.8.0

## A Step-by-Step Guide for Using PUnits

1. Go to the PUnits project folder `/home/jsr308/units-inference`

2. Custom base units and aliases (optional step)

  a. Check the currently supported base units with `./experiment-tools/get-num-baseunits.sh`. See if any desired base units are missing.

  b. `src/units/qual` contains all base units and unit aliases used. You can move unneeded base units and aliases to `src/units/notusedqual`.

  c.  Create new base units and new unit aliases. Look at existing files for reference.

3. Annotate JDK specifications and libraries (optional step)

  a. All `.astub` files in `/src/units` are the annotated JDK and libraries

  b. Create your `.astub` files. Look at existing stub files for reference.

  c. Add the files to the `@StubFiles{}` list in `src/units/UnitChecker.java`. You can comment out files that you won't need.

4. Build PUnits with `./gradlew assemble`

5. Run PUnits

  a. Run type-check mode on .java files:
      
      ```/home/opprop/units-inference/script/run-units-typecheck.sh <java files>```

  b. Run type-check mode on a Java project: Go to project folder and remember to clean the project first (to ensure everything is re-checked):
      
      ```/home/opprop/units-inference/script/run-dljc-typecheck.sh "<build command>"```

  c. Run inference mode on .java files:
      
      ```/home/opprop/units-inference/script/run-units-infer.sh false <java files>```

  d. Run inference mode on a Java project: Go to the project folder and remember to clean the project:
      
      ```/home/opprop/units-inference/script/run-dljc-inference.sh false "<build command>"```

  e. Run annotation mode on .java files:
      
      ```/home/opprop/units-inference/script/run-units-infer.sh true <java files>```

  f. Run annotation mode on a Java project: Go to the project folder and remember to clean the project:
      
      ```/home/opprop/units-inference/script/run-dljc-inference.sh true "<build command>"```


## Test command

```
./test-units.sh
```

## Benchmark command

These script creates a folder that has the same name as the YAML file under the `benchmark` directory that contains all the projects we evaluated on. 

To run type-checking benchmarks use:

```
./run-benchmark-typecheck.sh <path to YAML file without the .yml extension>
```

This script runs PUnits in type-check mode on all of the projects in the .yml file from a corpus. 

To run whole-program-inference without annotation mode:

```
./run-benchmark-infer.sh <path to YAML file without the .yml extension>
```

This script runs PUnits in whole-program-inference mode on all of the projects in the .yml file from a corpus.


To run whole-program-inference with annotation mode:

```
./run-benchmark-inference.sh true <path to YAML file without the .yml extension>
```

This script runs PUnits in whole-program-inference with annotation mode on all of the projects in the .yml file from a corpus.


## Analyze benchmark outputs command

To inspect the overview results of type check:

```
./experiment-tools/gen-typecheck-table.sh <path to result folder>
```

To inspect the overview results of whole program inference:

```
./experiment-tools/gen-inference-summary.sh <path to result folder>
./experiment-tools/gen-inference-table.sh <path to result folder>
```

To inspect the detailed report for each project:

```
cat <path to result folder>/<project name>/logs/infer.log
```
