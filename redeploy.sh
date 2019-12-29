#!/bin/bash
find "$PWD" -type f -name "*.sh" -exec $SHELL -c '
    for i in "$@" ; do
        source "$i"
    done
' {} +

