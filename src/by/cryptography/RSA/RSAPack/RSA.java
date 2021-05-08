package by.cryptography.RSA.RSAPack;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    private final Scanner scanner;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private BigInteger getPrimeNumber() {
        while (true) {
            long input = scanner.nextLong();
            BigInteger bigInteger = BigInteger.valueOf(input);

            if (RSAUtils.isPrime(bigInteger)) {
                return bigInteger;
            }

            System.out.format("Looks like %d isn't a prime number. Try again\n", bigInteger);
        }
    }

    private void startCalculations(BigInteger p, BigInteger q) {
        BigInteger r = p.multiply(q);
        BigInteger f = RSAUtils.calculateTheEulerFunction(p, q);

        BigInteger e = RSAUtils.generateE(f);
        BigInteger d = RSAUtils.generateD(f, e);

        System.out.format("p: %d\nq: %d\nr: %d\ne: %d\nd: %d\n", p, q, r, e, d);

        publicKey = new PublicKey(d, r);
        privateKey = new PrivateKey(e, r);

        System.out.println("Enter a message");
        scanner.nextLine();
        String message = scanner.nextLine();

        BigInteger hashCode = RSAUtils.calculateHashCode(message);
        System.out.println("Hash code : " + hashCode);
        BigInteger digitalSignature = privateKey.calculateDigitalSignature(hashCode);
        System.out.println("Digital signature : " + digitalSignature);

        System.out.println();

        BigInteger hashImageFromMessage = publicKey.calculateHash(message);
        System.out.println("Hash image from received message : " + hashImageFromMessage);
        BigInteger hashImageFromDigitalSignature = publicKey.calculateHash(digitalSignature);
        System.out.println("Hash image from received digital signature : " + hashImageFromDigitalSignature);

        if (Objects.equals(hashImageFromMessage, hashImageFromDigitalSignature)) {
            System.out.println("Signature validated!");
        } else {
            System.out.println("Ops...signature invalidated");
        }
    }

    public RSA(Scanner scanner) {
        this.scanner = scanner;
    }

    public void manualGeneration() {
        System.out.println("Enter a prime number for 'p'");
        BigInteger p = getPrimeNumber();

        System.out.println("Enter a prime number for 'q'");
        BigInteger q = getPrimeNumber();

        if (p.equals(q)) {
            System.out.println("Numbers 'p' and 'q' must be unique. Try again");
            return;
        }

        startCalculations(p, q);
    }

    public void automaticGeneration() {

        Random random = new Random();

        BigInteger p = BigInteger.probablePrime(32, random);
        BigInteger q = BigInteger.probablePrime(32, random);

        startCalculations(p, q);
    }

}
