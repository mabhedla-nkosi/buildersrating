package com.buildersrating.buildersrating.security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.salt.SaltGenerator;

public class ZamamboSecurity {
    public static String decrypt(String value) {
        String decryptedPassword = null;
        try {
            //String secret = UkhiyeWemfihlo.buyisaUkhiyeWeSalt();

            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            SimplePBEConfig simplePBEConfig = new SimplePBEConfig();
            SaltGenerator saltGenerator = new RandomSaltGenerator();

            saltGenerator.generateSalt(16);
            simplePBEConfig.setAlgorithm(AppKey.ALGORITHM_KEY);
            simplePBEConfig.setKeyObtentionIterations(10000);
            simplePBEConfig.setSaltGenerator(saltGenerator);
            simplePBEConfig.setPassword(AppKey.ALGORITHM_SECRETE);
            encryptor.setConfig(simplePBEConfig);
            encryptor.initialize();

            decryptedPassword = encryptor.decrypt(value);
        }catch(Exception e) {
            decryptedPassword = "Error, Failed to decrypt value.";
        }
        return decryptedPassword;
    }

    public static String encrypt(String value) {
        String encryptedPassword = null;
        try {
            //String secret = UkhiyeWemfihlo.buyisaUkhiyeWeSalt();

            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            SimplePBEConfig simplePBEConfig = new SimplePBEConfig();
            SaltGenerator saltGenerator = new RandomSaltGenerator();

            saltGenerator.generateSalt(16);
            simplePBEConfig.setAlgorithm(AppKey.ALGORITHM_KEY);
            simplePBEConfig.setKeyObtentionIterations(10000);
            simplePBEConfig.setSaltGenerator(saltGenerator);
            simplePBEConfig.setPassword(AppKey.ALGORITHM_SECRETE);
            encryptor.setConfig(simplePBEConfig);
            encryptor.initialize();

            encryptedPassword = encryptor.encrypt(value);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            encryptedPassword = "Error, Failed to encrypt value.";
        }
        return encryptedPassword;
    }
}
