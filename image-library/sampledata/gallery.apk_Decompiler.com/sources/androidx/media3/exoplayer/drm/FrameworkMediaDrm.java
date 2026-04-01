package androidx.media3.exoplayer.drm;

import K.d;
import K.e;
import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import android.media.metrics.LogSessionId;
import android.os.Build;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FrameworkMediaDrm implements ExoMediaDrm {
    public static final ExoMediaDrm.Provider DEFAULT_PROVIDER = new Object();
    private final MediaDrm mediaDrm;
    private int referenceCount = 1;
    private final UUID uuid;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api31 {
        public static void setLogSessionIdOnMediaDrmSession(MediaDrm mediaDrm, byte[] bArr, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            LogSessionId unused = LogSessionId.LOG_SESSION_ID_NONE;
            if (!logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                e.h(Assertions.checkNotNull(mediaDrm.getPlaybackComponent(bArr))).setLogSessionId(logSessionId);
            }
        }
    }

    private FrameworkMediaDrm(UUID uuid2) {
        Assertions.checkNotNull(uuid2);
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(uuid2), "Use C.CLEARKEY_UUID instead");
        this.uuid = uuid2;
        MediaDrm mediaDrm2 = new MediaDrm(adjustUuid(uuid2));
        this.mediaDrm = mediaDrm2;
        if (C.WIDEVINE_UUID.equals(uuid2) && needsForceWidevineL3Workaround()) {
            forceWidevineL3(mediaDrm2);
        }
    }

    private static byte[] addLaUrlAttributeIfMissing(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        short readLittleEndianShort = parsableByteArray.readLittleEndianShort();
        short readLittleEndianShort2 = parsableByteArray.readLittleEndianShort();
        if (readLittleEndianShort == 1 && readLittleEndianShort2 == 1) {
            short readLittleEndianShort3 = parsableByteArray.readLittleEndianShort();
            Charset charset = StandardCharsets.UTF_16LE;
            String readString = parsableByteArray.readString(readLittleEndianShort3, charset);
            if (readString.contains("<LA_URL>")) {
                return bArr;
            }
            int indexOf = readString.indexOf("</DATA>");
            if (indexOf == -1) {
                Log.w("FrameworkMediaDrm", "Could not find the </DATA> tag. Skipping LA_URL workaround.");
            }
            String str = readString.substring(0, indexOf) + "<LA_URL>https://x</LA_URL>" + readString.substring(indexOf);
            int i2 = readLittleEndianInt + 52;
            ByteBuffer allocate = ByteBuffer.allocate(i2);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(i2);
            allocate.putShort((short) readLittleEndianShort);
            allocate.putShort((short) readLittleEndianShort2);
            allocate.putShort((short) (str.length() * 2));
            allocate.put(str.getBytes(charset));
            return allocate.array();
        }
        Log.i("FrameworkMediaDrm", "Unexpected record count or type. Skipping LA_URL workaround.");
        return bArr;
    }

    private String adjustLicenseServerUrl(String str) {
        if ("<LA_URL>https://x</LA_URL>".equals(str)) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 33 && "https://default.url".equals(str)) {
            String propertyString = getPropertyString("version");
            if (Objects.equals(propertyString, "1.2") || Objects.equals(propertyString, "aidl-1")) {
                return "";
            }
        }
        return str;
    }

    private static byte[] adjustRequestData(UUID uuid2, byte[] bArr) {
        if (C.CLEARKEY_UUID.equals(uuid2)) {
            return ClearKeyUtil.adjustRequestData(bArr);
        }
        return bArr;
    }

    private static byte[] adjustRequestInitData(UUID uuid2, byte[] bArr) {
        byte[] parseSchemeSpecificData;
        PsshAtomUtil.PsshAtom parsePsshAtom;
        UUID uuid3 = C.PLAYREADY_UUID;
        if (uuid3.equals(uuid2)) {
            byte[] parseSchemeSpecificData2 = PsshAtomUtil.parseSchemeSpecificData(bArr, uuid2);
            if (parseSchemeSpecificData2 != null) {
                bArr = parseSchemeSpecificData2;
            }
            bArr = PsshAtomUtil.buildPsshAtom(uuid3, addLaUrlAttributeIfMissing(bArr));
        }
        if (cdmRequiresCommonPsshUuid(uuid2) && (parsePsshAtom = PsshAtomUtil.parsePsshAtom(bArr)) != null) {
            bArr = PsshAtomUtil.buildPsshAtom(C.COMMON_PSSH_UUID, parsePsshAtom.keyIds, parsePsshAtom.schemeData);
        }
        if (uuid3.equals(uuid2) && "Amazon".equals(Build.MANUFACTURER)) {
            String str = Build.MODEL;
            if (("AFTB".equals(str) || "AFTS".equals(str) || "AFTM".equals(str) || "AFTT".equals(str)) && (parseSchemeSpecificData = PsshAtomUtil.parseSchemeSpecificData(bArr, uuid2)) != null) {
                return parseSchemeSpecificData;
            }
        }
        return bArr;
    }

    private static UUID adjustUuid(UUID uuid2) {
        if (cdmRequiresCommonPsshUuid(uuid2)) {
            return C.COMMON_PSSH_UUID;
        }
        return uuid2;
    }

    private static boolean cdmRequiresCommonPsshUuid(UUID uuid2) {
        return false;
    }

    private static void forceWidevineL3(MediaDrm mediaDrm2) {
        mediaDrm2.setPropertyString("securityLevel", "L3");
    }

    private static DrmInitData.SchemeData getSchemeData(UUID uuid2, List<DrmInitData.SchemeData> list) {
        if (!C.WIDEVINE_UUID.equals(uuid2)) {
            return list.get(0);
        }
        if (list.size() > 1) {
            DrmInitData.SchemeData schemeData = list.get(0);
            int i2 = 0;
            int i7 = 0;
            while (i2 < list.size()) {
                DrmInitData.SchemeData schemeData2 = list.get(i2);
                byte[] bArr = (byte[]) Assertions.checkNotNull(schemeData2.data);
                if (Objects.equals(schemeData2.mimeType, schemeData.mimeType) && Objects.equals(schemeData2.licenseServerUrl, schemeData.licenseServerUrl) && PsshAtomUtil.isPsshAtom(bArr)) {
                    i7 += bArr.length;
                    i2++;
                }
            }
            byte[] bArr2 = new byte[i7];
            int i8 = 0;
            for (int i10 = 0; i10 < list.size(); i10++) {
                byte[] bArr3 = (byte[]) Assertions.checkNotNull(list.get(i10).data);
                int length = bArr3.length;
                System.arraycopy(bArr3, 0, bArr2, i8, length);
                i8 += length;
            }
            return schemeData.copyWithData(bArr2);
        }
        for (int i11 = 0; i11 < list.size(); i11++) {
            DrmInitData.SchemeData schemeData3 = list.get(i11);
            if (PsshAtomUtil.parseVersion((byte[]) Assertions.checkNotNull(schemeData3.data)) == 1) {
                return schemeData3;
            }
        }
        return list.get(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnEventListener$1(ExoMediaDrm.OnEventListener onEventListener, MediaDrm mediaDrm2, byte[] bArr, int i2, int i7, byte[] bArr2) {
        onEventListener.onEvent(this, bArr, i2, i7, bArr2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ExoMediaDrm lambda$static$0(UUID uuid2) {
        try {
            return newInstance(uuid2);
        } catch (UnsupportedDrmException unused) {
            Log.e("FrameworkMediaDrm", "Failed to instantiate a FrameworkMediaDrm for uuid: " + uuid2 + ".");
            return new DummyExoMediaDrm();
        }
    }

    private static boolean needsForceWidevineL3Workaround() {
        return "ASUS_Z00AD".equals(Build.MODEL);
    }

    public static FrameworkMediaDrm newInstance(UUID uuid2) {
        try {
            return new FrameworkMediaDrm(uuid2);
        } catch (UnsupportedSchemeException e) {
            throw new UnsupportedDrmException(1, e);
        } catch (Exception e7) {
            throw new UnsupportedDrmException(2, e7);
        }
    }

    public void closeSession(byte[] bArr) {
        this.mediaDrm.closeSession(bArr);
    }

    public int getCryptoType() {
        return 2;
    }

    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] bArr, List<DrmInitData.SchemeData> list, int i2, HashMap<String, String> hashMap) {
        DrmInitData.SchemeData schemeData;
        String str;
        byte[] bArr2;
        if (list != null) {
            schemeData = getSchemeData(this.uuid, list);
            bArr2 = adjustRequestInitData(this.uuid, (byte[]) Assertions.checkNotNull(schemeData.data));
            str = adjustRequestMimeType(this.uuid, schemeData.mimeType);
        } else {
            schemeData = null;
            bArr2 = null;
            str = null;
        }
        MediaDrm.KeyRequest keyRequest = this.mediaDrm.getKeyRequest(bArr, bArr2, str, i2, hashMap);
        byte[] adjustRequestData = adjustRequestData(this.uuid, keyRequest.getData());
        String adjustLicenseServerUrl = adjustLicenseServerUrl(keyRequest.getDefaultUrl());
        if (TextUtils.isEmpty(adjustLicenseServerUrl) && schemeData != null && !TextUtils.isEmpty(schemeData.licenseServerUrl)) {
            adjustLicenseServerUrl = schemeData.licenseServerUrl;
        }
        return new ExoMediaDrm.KeyRequest(adjustRequestData, adjustLicenseServerUrl, keyRequest.getRequestType());
    }

    public String getPropertyString(String str) {
        return this.mediaDrm.getPropertyString(str);
    }

    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        MediaDrm.ProvisionRequest provisionRequest = this.mediaDrm.getProvisionRequest();
        return new ExoMediaDrm.ProvisionRequest(provisionRequest.getData(), provisionRequest.getDefaultUrl());
    }

    public byte[] openSession() {
        return this.mediaDrm.openSession();
    }

    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) {
        if (C.CLEARKEY_UUID.equals(this.uuid)) {
            bArr2 = ClearKeyUtil.adjustResponseData(bArr2);
        }
        return this.mediaDrm.provideKeyResponse(bArr, bArr2);
    }

    public void provideProvisionResponse(byte[] bArr) {
        this.mediaDrm.provideProvisionResponse(bArr);
    }

    public Map<String, String> queryKeyStatus(byte[] bArr) {
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    public synchronized void release() {
        int i2 = this.referenceCount - 1;
        this.referenceCount = i2;
        if (i2 == 0) {
            this.mediaDrm.release();
        }
    }

    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        this.mediaDrm.restoreKeys(bArr, bArr2);
    }

    public void setOnEventListener(ExoMediaDrm.OnEventListener onEventListener) {
        d dVar;
        MediaDrm mediaDrm2 = this.mediaDrm;
        if (onEventListener == null) {
            dVar = null;
        } else {
            dVar = new d(this, onEventListener);
        }
        mediaDrm2.setOnEventListener(dVar);
    }

    public void setPlayerIdForSession(byte[] bArr, PlayerId playerId) {
        if (Build.VERSION.SDK_INT >= 31) {
            try {
                Api31.setLogSessionIdOnMediaDrmSession(this.mediaDrm, bArr, playerId);
            } catch (UnsupportedOperationException unused) {
                Log.w("FrameworkMediaDrm", "setLogSessionId failed.");
            }
        }
    }

    public FrameworkCryptoConfig createCryptoConfig(byte[] bArr) {
        return new FrameworkCryptoConfig(adjustUuid(this.uuid), bArr);
    }

    private static String adjustRequestMimeType(UUID uuid2, String str) {
        return str;
    }
}
