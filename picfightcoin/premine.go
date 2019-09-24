package picfightcoin

import (
	"encoding/hex"
	"github.com/decred/dcrd/chaincfg"
)

var BlockOneLedgerPicfightCoin = []*chaincfg.TokenPayout{
	//{"RsHzbGt6YajuHpurtpqXXHz57LmYZK8w9tX", 100000 * 1e8},
}

func hexDecode(hexStr string) []byte {
	b, err := hex.DecodeString(hexStr)
	if err != nil {
		panic(err)
	}
	return b
}
