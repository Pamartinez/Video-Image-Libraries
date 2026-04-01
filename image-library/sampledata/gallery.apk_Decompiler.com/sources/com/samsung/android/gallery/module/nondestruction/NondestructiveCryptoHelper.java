package com.samsung.android.gallery.module.nondestruction;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NondestructiveCryptoHelper {
    private static final SecureRandom sRandom = new SecureRandom();

    private static SecretKey deriveKey(byte[] bArr) {
        try {
            return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec("VideoEditNDE10".toCharArray(), bArr, 1000, 256)).getEncoded(), "AES");
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] fromBase64(String str) {
        return Base64.getDecoder().decode(str);
    }

    public String decrypt(String str) {
        String[] split = str.split("]");
        if (split.length == 3) {
            byte[] fromBase64 = fromBase64(split[0]);
            byte[] fromBase642 = fromBase64(split[1]);
            byte[] fromBase643 = fromBase64(split[2]);
            SecretKey deriveKey = deriveKey(fromBase64);
            try {
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, deriveKey, new IvParameterSpec(fromBase642));
                return new String(instance.doFinal(fromBase643), StandardCharsets.UTF_8);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Invalid encrypted text format");
        }
    }
}
