#!/usr/bin/env bash

GO111MODULE=on
go version

#  $GO clean -testcache

go build -v ./...
go test -v ./...