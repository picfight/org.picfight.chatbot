#!/usr/bin/env bash

go clean -testcache

set GO111MODULE=on

go build ./...

go test -v ./...

echo "------------------------------------------"
echo "Tests completed successfully!"
