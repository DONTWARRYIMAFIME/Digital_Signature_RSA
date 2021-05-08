package by.cryptography.RSA.RSAPack;

import java.math.BigInteger;

public class PrivateKey {
    private final BigInteger d;
    private final BigInteger r;

    public PrivateKey(BigInteger d, BigInteger r) {
        this.d = d;
        this.r = r;
    }

    public BigInteger calculateDigitalSignature(BigInteger m) {
        return RSAUtils.modularPower(m, d, r);
    }

}
