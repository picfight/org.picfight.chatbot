#!/usr/bin/env bash

set GO111MODULE=on
go build ./...
go clean -testcache
go test -v ./...