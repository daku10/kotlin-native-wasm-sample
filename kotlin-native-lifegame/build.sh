#!/usr/bin/env bash

DIR=$(pwd)

mkdir -p $DIR/build/bin
mkdir -p $DIR/build/klib

jsinterop -pkg kotlinx.interop.wasm.dom \
          -o $DIR/build/klib/dom -target wasm32  || exit 1

konanc $DIR/src/main/kotlin \
        -r $DIR/build/klib -l dom \
        -o $DIR/build/bin/lifegame -target wasm32 || exit 1

