package com.samsung.scsp.framework.core.identity;

import com.samsung.scsp.common.ContextFactory;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.util.DeviceUtil;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeviceId {
    private String generateStrongDeviceIDHash(String str) {
        return (String) FaultBarrier.get(new b(5, this, str), null).obj;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$generateStrongDeviceIDHash$0(String str) {
        return toHex(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), "LINDOR".getBytes(StandardCharsets.UTF_8), 30, 128)).getEncoded());
    }

    private String toHex(byte[] bArr) {
        String bigInteger = new BigInteger(1, bArr).toString(16);
        int length = (bArr.length * 2) - bigInteger.length();
        if (length <= 0) {
            return bigInteger;
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            sb2.append("0");
        }
        return sb2 + bigInteger;
    }

    public String getClientDeviceId() {
        String str = ScspCorePreferences.get().cdid.get();
        if (!StringUtil.isEmpty(str)) {
            return str;
        }
        String generateStrongDeviceIDHash = generateStrongDeviceIDHash(DeviceUtil.getAndroidId(ContextFactory.getApplicationContext()));
        if (!StringUtil.isEmpty(generateStrongDeviceIDHash)) {
            ScspCorePreferences.get().cdid.accept(generateStrongDeviceIDHash);
        }
        return generateStrongDeviceIDHash;
    }

    public String getPhysicalDeviceId(DeviceIdSupplier deviceIdSupplier) {
        String str = ScspCorePreferences.get().pdid.get();
        if (!StringUtil.isEmpty(str)) {
            return str;
        }
        String generateStrongDeviceIDHash = generateStrongDeviceIDHash(DeviceUtil.getDeviceUniqueId(ContextFactory.getApplicationContext(), deviceIdSupplier));
        if (!StringUtil.isEmpty(generateStrongDeviceIDHash)) {
            ScspCorePreferences.get().pdid.accept(generateStrongDeviceIDHash);
        }
        return generateStrongDeviceIDHash;
    }
}
