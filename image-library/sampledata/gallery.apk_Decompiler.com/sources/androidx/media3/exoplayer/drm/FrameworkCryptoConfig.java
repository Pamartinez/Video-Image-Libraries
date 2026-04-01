package androidx.media3.exoplayer.drm;

import android.os.Build;
import androidx.media3.decoder.CryptoConfig;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FrameworkCryptoConfig implements CryptoConfig {
    public static final boolean WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC;
    @Deprecated
    public final boolean forceAllowInsecureDecoderComponents;
    public final byte[] sessionId;
    public final UUID uuid;

    static {
        boolean z;
        if ("Amazon".equals(Build.MANUFACTURER)) {
            String str = Build.MODEL;
            if ("AFTM".equals(str) || "AFTB".equals(str)) {
                z = true;
                WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC = z;
            }
        }
        z = false;
        WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC = z;
    }

    public FrameworkCryptoConfig(UUID uuid2, byte[] bArr) {
        this(uuid2, bArr, false);
    }

    @Deprecated
    public FrameworkCryptoConfig(UUID uuid2, byte[] bArr, boolean z) {
        this.uuid = uuid2;
        this.sessionId = bArr;
        this.forceAllowInsecureDecoderComponents = z;
    }
}
