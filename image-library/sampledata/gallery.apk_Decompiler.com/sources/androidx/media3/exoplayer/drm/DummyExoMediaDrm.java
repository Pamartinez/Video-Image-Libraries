package androidx.media3.exoplayer.drm;

import android.media.MediaDrmException;
import androidx.media3.common.DrmInitData;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DummyExoMediaDrm implements ExoMediaDrm {
    public CryptoConfig createCryptoConfig(byte[] bArr) {
        throw new IllegalStateException();
    }

    public int getCryptoType() {
        return 1;
    }

    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] bArr, List<DrmInitData.SchemeData> list, int i2, HashMap<String, String> hashMap) {
        throw new IllegalStateException();
    }

    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        throw new IllegalStateException();
    }

    public byte[] openSession() {
        throw new MediaDrmException("Attempting to open a session using a dummy ExoMediaDrm.");
    }

    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    public void provideProvisionResponse(byte[] bArr) {
        throw new IllegalStateException();
    }

    public Map<String, String> queryKeyStatus(byte[] bArr) {
        throw new IllegalStateException();
    }

    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    public void release() {
    }

    public void closeSession(byte[] bArr) {
    }

    public void setOnEventListener(ExoMediaDrm.OnEventListener onEventListener) {
    }
}
