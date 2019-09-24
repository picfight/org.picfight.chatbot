package main

import "testing"

func TestFail(t *testing.T) {
	t.Errorf("TestFail")
	t.FailNow()
}
