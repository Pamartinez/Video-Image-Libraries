package androidx.profileinstaller;

import a6.C0418a;
import android.content.res.AssetManager;
import android.os.Build;
import androidx.profileinstaller.ProfileInstaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeviceProfileWriter {
    private final String mApkName;
    private final AssetManager mAssetManager;
    private final File mCurProfile;
    private final byte[] mDesiredVersion;
    private boolean mDeviceSupportsAotProfile = false;
    private final ProfileInstaller.DiagnosticsCallback mDiagnostics;
    private final Executor mExecutor;
    private DexProfileData[] mProfile;
    private final String mProfileMetaSourceLocation;
    private final String mProfileSourceLocation;
    private byte[] mTranscodedProfile;

    public DeviceProfileWriter(AssetManager assetManager, Executor executor, ProfileInstaller.DiagnosticsCallback diagnosticsCallback, String str, String str2, String str3, File file) {
        this.mAssetManager = assetManager;
        this.mExecutor = executor;
        this.mDiagnostics = diagnosticsCallback;
        this.mApkName = str;
        this.mProfileSourceLocation = str2;
        this.mProfileMetaSourceLocation = str3;
        this.mCurProfile = file;
        this.mDesiredVersion = desiredVersion();
    }

    private DeviceProfileWriter addMetadata(DexProfileData[] dexProfileDataArr, byte[] bArr) {
        InputStream openStreamFromAssets;
        try {
            openStreamFromAssets = openStreamFromAssets(this.mAssetManager, this.mProfileMetaSourceLocation);
            if (openStreamFromAssets != null) {
                this.mProfile = ProfileTranscoder.readMeta(openStreamFromAssets, ProfileTranscoder.readHeader(openStreamFromAssets, ProfileTranscoder.MAGIC_PROFM), bArr, dexProfileDataArr);
                openStreamFromAssets.close();
                return this;
            }
            if (openStreamFromAssets != null) {
                openStreamFromAssets.close();
            }
            return null;
        } catch (FileNotFoundException e) {
            this.mDiagnostics.onResultReceived(9, e);
        } catch (IOException e7) {
            this.mDiagnostics.onResultReceived(7, e7);
        } catch (IllegalStateException e8) {
            this.mProfile = null;
            this.mDiagnostics.onResultReceived(8, e8);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void assertDeviceAllowsProfileInstallerAotWritesCalled() {
        if (!this.mDeviceSupportsAotProfile) {
            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
        }
    }

    private static byte[] desiredVersion() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 31) {
            return ProfileVersion.V015_S;
        }
        if (i2 != 30) {
            return null;
        }
        return ProfileVersion.V010_P;
    }

    private InputStream getProfileInputStream(AssetManager assetManager) {
        try {
            return openStreamFromAssets(assetManager, this.mProfileSourceLocation);
        } catch (FileNotFoundException e) {
            this.mDiagnostics.onResultReceived(6, e);
            return null;
        } catch (IOException e7) {
            this.mDiagnostics.onResultReceived(7, e7);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$result$0(int i2, Object obj) {
        this.mDiagnostics.onResultReceived(i2, obj);
    }

    private InputStream openStreamFromAssets(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message != null && message.contains("compressed")) {
                this.mDiagnostics.onDiagnosticReceived(5, (Object) null);
            }
            return null;
        }
    }

    private DexProfileData[] readProfileInternal(InputStream inputStream) {
        try {
            DexProfileData[] readProfile = ProfileTranscoder.readProfile(inputStream, ProfileTranscoder.readHeader(inputStream, ProfileTranscoder.MAGIC_PROF), this.mApkName);
            try {
                inputStream.close();
                return readProfile;
            } catch (IOException e) {
                this.mDiagnostics.onResultReceived(7, e);
                return readProfile;
            }
        } catch (IOException e7) {
            this.mDiagnostics.onResultReceived(7, e7);
            try {
                inputStream.close();
            } catch (IOException e8) {
                this.mDiagnostics.onResultReceived(7, e8);
            }
            return null;
        } catch (IllegalStateException e9) {
            this.mDiagnostics.onResultReceived(8, e9);
            inputStream.close();
            return null;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e10) {
                this.mDiagnostics.onResultReceived(7, e10);
            }
            throw th;
        }
    }

    private static boolean requiresMetadata() {
        if (Build.VERSION.SDK_INT >= 31) {
            return true;
        }
        return false;
    }

    private void result(int i2, Object obj) {
        this.mExecutor.execute(new C0418a((Object) this, i2, obj, 2));
    }

    public boolean deviceAllowsProfileInstallerAotWrites() {
        if (this.mDesiredVersion == null) {
            result(3, Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        if (!this.mCurProfile.exists()) {
            try {
                if (!this.mCurProfile.createNewFile()) {
                    result(4, (Object) null);
                    return false;
                }
            } catch (IOException unused) {
                result(4, (Object) null);
                return false;
            }
        } else if (!this.mCurProfile.canWrite()) {
            result(4, (Object) null);
            return false;
        }
        this.mDeviceSupportsAotProfile = true;
        return true;
    }

    public DeviceProfileWriter read() {
        DeviceProfileWriter addMetadata;
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        if (this.mDesiredVersion != null) {
            InputStream profileInputStream = getProfileInputStream(this.mAssetManager);
            if (profileInputStream != null) {
                this.mProfile = readProfileInternal(profileInputStream);
            }
            DexProfileData[] dexProfileDataArr = this.mProfile;
            if (dexProfileDataArr == null || !requiresMetadata() || (addMetadata = addMetadata(dexProfileDataArr, this.mDesiredVersion)) == null) {
                return this;
            }
            return addMetadata;
        }
        return this;
    }

    public DeviceProfileWriter transcodeIfNeeded() {
        ByteArrayOutputStream byteArrayOutputStream;
        DexProfileData[] dexProfileDataArr = this.mProfile;
        byte[] bArr = this.mDesiredVersion;
        if (!(dexProfileDataArr == null || bArr == null)) {
            assertDeviceAllowsProfileInstallerAotWritesCalled();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                ProfileTranscoder.writeHeader(byteArrayOutputStream, bArr);
                if (!ProfileTranscoder.transcodeAndWriteBody(byteArrayOutputStream, bArr, dexProfileDataArr)) {
                    this.mDiagnostics.onResultReceived(5, (Object) null);
                    this.mProfile = null;
                    byteArrayOutputStream.close();
                    return this;
                }
                this.mTranscodedProfile = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                this.mProfile = null;
            } catch (IOException e) {
                this.mDiagnostics.onResultReceived(7, e);
            } catch (IllegalStateException e7) {
                this.mDiagnostics.onResultReceived(8, e7);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return this;
        throw th;
    }

    public boolean write() {
        ByteArrayInputStream byteArrayInputStream;
        FileChannel channel;
        FileLock tryLock;
        byte[] bArr = this.mTranscodedProfile;
        if (bArr == null) {
            return false;
        }
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            FileOutputStream fileOutputStream = new FileOutputStream(this.mCurProfile);
            try {
                channel = fileOutputStream.getChannel();
                tryLock = channel.tryLock();
                Encoding.writeAll(byteArrayInputStream, fileOutputStream, tryLock);
                result(1, (Object) null);
                if (tryLock != null) {
                    tryLock.close();
                }
                channel.close();
                fileOutputStream.close();
                byteArrayInputStream.close();
                this.mTranscodedProfile = null;
                this.mProfile = null;
                return true;
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } catch (FileNotFoundException e) {
            result(6, e);
            this.mTranscodedProfile = null;
            this.mProfile = null;
            return false;
        } catch (IOException e7) {
            result(7, e7);
            this.mTranscodedProfile = null;
            this.mProfile = null;
            return false;
        } catch (Throwable th2) {
            this.mTranscodedProfile = null;
            this.mProfile = null;
            throw th2;
        }
        throw th;
        throw th;
        throw th;
    }
}
