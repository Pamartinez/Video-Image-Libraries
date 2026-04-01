package com.samsung.android.gallery.support.hash;

import com.samsung.scsp.framework.core.util.HashUtil;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SHA256 implements HashAlgorithm {
    public byte[] hashBytes(String str) {
        try {
            return MessageDigest.getInstance(HashUtil.SHA256).digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String hashString(String str) {
        byte[] hashBytes = hashBytes(str);
        StringBuilder sb2 = new StringBuilder();
        if (hashBytes != null) {
            for (byte b : hashBytes) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb2.append('0');
                }
                sb2.append(hexString);
            }
        }
        return sb2.toString();
    }
}
