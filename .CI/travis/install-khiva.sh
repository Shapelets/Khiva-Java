#!/bin/bash
# Copyright (c) 2019 Shapelets.io
#
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at http://mozilla.org/MPL/2.0/.

function check-error() {
  KHIVA_ERROR=$?
  if [ $KHIVA_ERROR -ne 0 ]; then
      echo "$1: $KHIVA_ERROR"
      exit $KHIVA_ERROR
  fi
}

if [[ "$INSTALL_KHIVA_METHOD" == "installer" ]]; then
   if [[ "$TRAVIS_OS_NAME" == "osx" ]]; then
        if [ ! -e "./installers/khiva-v0.5.0.pkg" ]; then
            wget https://github.com/shapelets/khiva/releases/download/v0.5.0/khiva-v0.5.0-OnlyCPU.pkg -O ./installers/khiva-v0.5.0-OnlyCPU.pkg
        fi
        # Installs Khiva
        sudo installer -pkg ./installers/khiva-v0.5.0-OnlyCPU.pkg -target /
    else
        if [ ! -e "./installers/khiva-v0.5.0-ci.sh" ]; then
            wget https://github.com/shapelets/khiva/releases/download/v0.5.0/khiva-v0.5.0-ci.sh -O ./installers/khiva-v0.5.0-ci.sh
            chmod +x ./installers/khiva-v0.5.0-ci.sh
        fi
        sudo ./installers/khiva-v0.5.0-ci.sh --prefix=/usr/local --skip-license
        sudo ldconfig
    fi

else
    cmake --version
    python3 --version

    conan remote add conan-mpusz https://api.bintray.com/conan/mpusz/conan-mpusz
    if [ $? -ne 0 ]; then
        conan remote update conan-mpusz https://api.bintray.com/conan/mpusz/conan-mpusz
    fi

    # Cloning Github repo into khiva-library folder
    git clone https://github.com/shapelets/khiva.git khiva-library
    cd khiva-library
    git submodule update --init
    mkdir -p build && cd build
    conan profile new --detect --force default
    if [[ "$TRAVIS_OS_NAME" == "osx" ]]; then
        conan profile update settings.compiler.libcxx=libc++ default
        conan profile update settings.compiler=apple-clang default
        conan profile update settings.compiler.version=9.1 default
        conan install .. --build missing
        check-error "Error installing dependencies"
        cmake .. -DKHIVA_ONLY_CPU_BACKEND=ON -DKHIVA_BUILD_DOCUMENTATION=OFF -DKHIVA_BUILD_EXAMPLES=OFF -DKHIVA_BUILD_BENCHMARKS=OFF
        check-error "Error generating CMake configuration"
        make install -j8
        check-error "Error building Khiva"
    else
        conan profile update settings.compiler.libcxx=libstdc++11 default
        conan profile update settings.compiler.version=7 default
        conan install .. --build missing
        check-error "Error installing dependencies"
        cmake .. -DKHIVA_ENABLE_COVERAGE=ON -DKHIVA_BUILD_DOCUMENTATION=OFF -DKHIVA_BUILD_EXAMPLES=OFF -DKHIVA_BUILD_BENCHMARKS=OFF
        check-error "Error generating CMake configuration"
        sudo make install -j8
        check-error "Error building Khiva"
        sudo ldconfig
    fi
    # Switching back to the khiva-python folder
    cd ..
    cd ..
fi
