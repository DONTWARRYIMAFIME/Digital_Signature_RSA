package by.cryptography.RSA.RSAPack;

import java.math.BigInteger;
import java.util.Random;

public class RSAUtils {

    public static BigInteger calculateTheEulerFunction(BigInteger p, BigInteger q) {
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }

    public static BigInteger modularPower(BigInteger number, BigInteger power, BigInteger module) {
        if (power.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }

        BigInteger temp = modularPower(number, power.divide(BigInteger.TWO), module);

        if (power.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return (temp.multiply(temp)).remainder(module);
        } else {
            return (number.multiply(temp).multiply(temp)).remainder(module);
        }

    }

    public static boolean isPrime(BigInteger number) {
        return number.isProbablePrime(1);
    }

    public static boolean isRelativePrime(BigInteger a, BigInteger b) {
        return a.gcd(b).equals(BigInteger.ONE);
    }

    public static BigInteger generateE(BigInteger f) {

        Random random = new Random();
        int stopNumber = random.nextInt(f.bitLength());

        int iterator = 0;
        BigInteger e = BigInteger.ONE;

        while (true) {
            if (iterator == stopNumber) {

                if (isRelativePrime(e, f)) {
                    return e;
                }

                iterator = 0;
                stopNumber = random.nextInt(f.bitLength());

            }

            iterator++;
            e = e.nextProbablePrime();
        }

    }

    public static BigInteger generateD(BigInteger a, BigInteger b) {
        BigInteger d0 = a;
        BigInteger d1 = b;

        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;

        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;

        while (d1.compareTo(BigInteger.ONE) == 1) {
            BigInteger q = d0.divide(d1);
            BigInteger d2 = d0.remainder(d1);
            BigInteger x2 = x0.subtract(q.multiply(x1));
            BigInteger y2 = y0.subtract(q.multiply(y1));

            d0 = d1; d1 = d2;
            x0 = x1; x1 = x2;
            y0 = y1; y1 = y2;
        }

        return y1.compareTo(BigInteger.ZERO) == 1 ? y1 : y1.add(a);
    }

    public static BigInteger calculateHashCode(String message) {
        return BigInteger.valueOf(message.hashCode());
    }



}
