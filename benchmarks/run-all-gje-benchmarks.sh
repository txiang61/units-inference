#!/bin/bash

./clean-up-projects.sh GJEprojects

gradle jar

# python run-units-infer-on-corpus.py --corpus-file=projects.yml
time python run-units-infer-on-corpus.py --corpus-file GJEprojects.yml