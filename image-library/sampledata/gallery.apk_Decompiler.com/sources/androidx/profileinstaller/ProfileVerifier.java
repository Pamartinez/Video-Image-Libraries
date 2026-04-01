package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import androidx.concurrent.futures.ResolvableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ProfileVerifier {
    private static final Object SYNC_OBJ = new Object();
    private static CompilationStatus sCompilationStatus = null;
    private static final ResolvableFuture<CompilationStatus> sFuture = ResolvableFuture.create();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api33Impl {
        public static PackageInfo getPackageInfo(PackageManager packageManager, Context context) {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Cache {
        final long mInstalledCurrentProfileSize;
        final long mPackageLastUpdateTime;
        final int mResultCode;
        final int mSchema;

        public Cache(int i2, int i7, long j2, long j3) {
            this.mSchema = i2;
            this.mResultCode = i7;
            this.mPackageLastUpdateTime = j2;
            this.mInstalledCurrentProfileSize = j3;
        }

        public static Cache readFromFile(File file) {
            Throwable th;
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                Cache cache = new Cache(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
                dataInputStream.close();
                return cache;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof Cache)) {
                Cache cache = (Cache) obj;
                if (this.mResultCode == cache.mResultCode && this.mPackageLastUpdateTime == cache.mPackageLastUpdateTime && this.mSchema == cache.mSchema && this.mInstalledCurrentProfileSize == cache.mInstalledCurrentProfileSize) {
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.mResultCode), Long.valueOf(this.mPackageLastUpdateTime), Integer.valueOf(this.mSchema), Long.valueOf(this.mInstalledCurrentProfileSize)});
        }

        public void writeOnFile(File file) {
            file.delete();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(this.mSchema);
                dataOutputStream.writeInt(this.mResultCode);
                dataOutputStream.writeLong(this.mPackageLastUpdateTime);
                dataOutputStream.writeLong(this.mInstalledCurrentProfileSize);
                dataOutputStream.close();
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CompilationStatus {
        private final boolean mHasCurrentProfile;
        private final boolean mHasEmbeddedProfile;
        private final boolean mHasReferenceProfile;
        final int mResultCode;

        public CompilationStatus(int i2, boolean z, boolean z3, boolean z7) {
            this.mResultCode = i2;
            this.mHasCurrentProfile = z3;
            this.mHasReferenceProfile = z;
            this.mHasEmbeddedProfile = z7;
        }
    }

    private static long getPackageLastUpdateTime(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getPackageInfo(packageManager, context).lastUpdateTime;
        }
        return packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    private static CompilationStatus setCompilationStatus(int i2, boolean z, boolean z3, boolean z7) {
        CompilationStatus compilationStatus = new CompilationStatus(i2, z, z3, z7);
        sCompilationStatus = compilationStatus;
        sFuture.set(compilationStatus);
        return sCompilationStatus;
    }

    public static CompilationStatus writeProfileVerification(Context context, boolean z) {
        boolean z3;
        boolean z7;
        boolean z9;
        Cache cache;
        Cache cache2;
        AssetFileDescriptor openFd;
        Throwable th;
        CompilationStatus compilationStatus;
        if (!z && (compilationStatus = sCompilationStatus) != null) {
            return compilationStatus;
        }
        synchronized (SYNC_OBJ) {
            if (!z) {
                CompilationStatus compilationStatus2 = sCompilationStatus;
                if (compilationStatus2 != null) {
                    return compilationStatus2;
                }
            }
            int i2 = 0;
            try {
                openFd = context.getAssets().openFd("dexopt/baseline.prof");
                if (openFd.getLength() > 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                openFd.close();
            } catch (IOException unused) {
                z3 = false;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            if (Build.VERSION.SDK_INT == 30) {
                CompilationStatus compilationStatus3 = setCompilationStatus(262144, false, false, z3);
                return compilationStatus3;
            }
            File file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
            long length = file.length();
            if (!file.exists() || length <= 0) {
                z7 = false;
            } else {
                z7 = true;
            }
            File file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
            long length2 = file2.length();
            if (!file2.exists() || length2 <= 0) {
                z9 = false;
            } else {
                z9 = true;
            }
            try {
                long packageLastUpdateTime = getPackageLastUpdateTime(context);
                File file3 = new File(context.getFilesDir(), "profileInstalled");
                if (file3.exists()) {
                    try {
                        cache = Cache.readFromFile(file3);
                    } catch (IOException unused2) {
                        return setCompilationStatus(131072, z7, z9, z3);
                    }
                } else {
                    cache = null;
                }
                if (cache != null && cache.mPackageLastUpdateTime == packageLastUpdateTime) {
                    int i7 = cache.mResultCode;
                    if (i7 != 2) {
                        i2 = i7;
                        if (z && z9 && i2 != 1) {
                            i2 = 2;
                        }
                        if (cache != null && cache.mResultCode == 2 && i2 == 1 && length < cache.mInstalledCurrentProfileSize) {
                            i2 = 3;
                        }
                        int i8 = i2;
                        cache2 = new Cache(1, i8, packageLastUpdateTime, length2);
                        if (cache == null || !cache.equals(cache2)) {
                            cache2.writeOnFile(file3);
                        }
                        CompilationStatus compilationStatus4 = setCompilationStatus(i8, z7, z9, z3);
                        return compilationStatus4;
                    }
                }
                if (!z3) {
                    i2 = 327680;
                } else if (z7) {
                    i2 = 1;
                } else if (z9) {
                    i2 = 2;
                }
                i2 = 2;
                i2 = 3;
                int i82 = i2;
                cache2 = new Cache(1, i82, packageLastUpdateTime, length2);
                try {
                    cache2.writeOnFile(file3);
                } catch (IOException unused3) {
                    i82 = 196608;
                }
                CompilationStatus compilationStatus42 = setCompilationStatus(i82, z7, z9, z3);
                return compilationStatus42;
            } catch (PackageManager.NameNotFoundException unused4) {
                return setCompilationStatus(65536, z7, z9, z3);
            }
        }
        throw th;
    }
}
