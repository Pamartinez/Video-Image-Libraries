package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import android.text.TextUtils;
import com.samsung.android.gallery.support.hash.SHA256;
import java.io.File;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScpmMessage {
    public final String data;
    String signature;
    public final String tag;
    public final long version;

    public static ScpmMessage fromJson(File file) {
        ScpmMessage scpmMessage = (ScpmMessage) GsonTool.fromJsonFile(ScpmMessage.class, file.getPath());
        if (scpmMessage == null || scpmMessage.tag == null || scpmMessage.version == 0 || !scpmMessage.matchSignature()) {
            return null;
        }
        return scpmMessage;
    }

    public String decryptSignature(String str) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzmZZ3lBopBAXPsM7glsdTw8VgPWPnfC3D/WlZTd3kEscazJCSFvqqMVJdwJiQ7sfuxvnFLe+bceLYmNOVIp/Vdp0aLGevgdPgtDy+CRvQiTq09K56LdfbFP/0lnVZ58bGAE32YTkkZjcVrXoUVI+baIT2b6X3t7WT4thDcbYBHQIDAQAB")));
            Cipher instance = Cipher.getInstance("RSA");
            instance.init(2, generatePublic);
            return new String(instance.doFinal(Base64.getDecoder().decode(str)), "UTF-8");
        } catch (Exception e) {
            a.s(e, j.k("decrypt failed [", str, "] failed. e="), "ScpmMessage");
            return "";
        }
    }

    public String getDataHash() {
        return Base64.getEncoder().encodeToString(new SHA256().hashBytes(toString()));
    }

    public boolean matchSignature() {
        if (TextUtils.isEmpty(this.signature)) {
            return false;
        }
        String dataHash = getDataHash();
        String decryptSignature = decryptSignature(this.signature);
        if (dataHash.equals(decryptSignature)) {
            return true;
        }
        Log.e((CharSequence) "ScpmMessage", "matchSignature failed", dataHash, decryptSignature);
        return false;
    }

    public String toString() {
        return this.tag + ";" + this.version + ";" + this.data;
    }

    public static ScpmMessage fromJson(String str) {
        ScpmMessage scpmMessage = (ScpmMessage) GsonTool.fromJsonString(ScpmMessage.class, str);
        if (scpmMessage == null || scpmMessage.tag == null || scpmMessage.version == 0 || !scpmMessage.matchSignature()) {
            return null;
        }
        return scpmMessage;
    }
}
