package by.cryptography.RSA.RSAPack;

import java.math.BigInteger;

public class PublicKey {
    private final BigInteger e;
    private final BigInteger r;

    public PublicKey(BigInteger e, BigInteger r) {
        this.e = e;
        this.r = r;
    }

    public BigInteger calculateHash(String message) {
        return RSAUtils.calculateHashCode(message);
    }
    public BigInteger calculateHash(BigInteger m) {
        return RSAUtils.modularPower(m, e, r);
    }

}
