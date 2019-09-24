#!/usr/bin/env bash

# Default GOVERSION
[[ ! "$GOVERSION" ]] && GOVERSION=1.11
REPO=pfcd

 GO=go
  if [[ $GOVERSION == 1.10 ]]; then
    GO=vgo
  fi

  $GO version

  # binary needed for RPC tests
  env CC=gcc $GO build
  cp "$REPO" "$GOPATH/bin/"

  # run tests on all modules
  ROOTPATH=$($GO list -m -f {{.Dir}} 2>/dev/null)
  ROOTPATHPATTERN=$(echo $ROOTPATH | sed 's/\\/\\\\/g' | sed 's/\//\\\//g')
  MODPATHS=$($GO list -m -f {{.Dir}} all 2>/dev/null | grep "^$ROOTPATHPATTERN"\
    | sed -e "s/^$ROOTPATHPATTERN//" -e 's/^\\//' -e 's/^\///')
  MODPATHS=". $MODPATHS"
  for module in $MODPATHS; do
    echo "==> ${module}"
    (cd $module && env GORACE='halt_on_error=1' CC=gcc $GO test ./...)
  done

  # check linters
  if [[ $GOVERSION != 1.10 ]]; then
    # linters do not work with modules yet
    go mod vendor
    unset GO111MODULE

    gometalinter --vendor --disable-all --deadline=10m \
      --enable=gofmt \
      --enable=gosimple \
      --enable=unconvert \
      --enable=ineffassign \
      ./...
    if [ $? != 0 ]; then
      echo 'gometalinter has some complaints'
      exit 1
    fi
  fi

  echo "------------------------------------------"
  echo "Tests completed successfully!"