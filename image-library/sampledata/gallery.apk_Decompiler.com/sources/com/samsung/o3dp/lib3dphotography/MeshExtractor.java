package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import android.util.Log;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.mesh.AnimatedMesh;
import com.samsung.o3dp.lib3dphotography.mesh.ExtractOption;
import com.samsung.o3dp.lib3dphotography.mesh.Heif3dDecoder;
import com.samsung.o3dp.lib3dphotography.mesh.Heif3dException;
import com.samsung.o3dp.lib3dphotography.mesh.HeifDecodingResult;
import com.samsung.o3dp.lib3dphotography.mesh.Jpeg3dDecoder;
import com.samsung.o3dp.lib3dphotography.mesh.JpegDecodingResult;
import com.samsung.o3dp.lib3dphotography.mesh.MeshSefManager;
import com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension;
import com.samsung.o3dp.lib3dphotography.mesh.exception.MeshDecodingException;
import com.samsung.o3dp.lib3dphotography.utils.FileUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MeshExtractor {
    private static final String TAG = "MeshExtractor";

    /* renamed from: com.samsung.o3dp.lib3dphotography.MeshExtractor$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$ExtractOption;
        static final /* synthetic */ int[] $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        static {
            /*
                com.samsung.o3dp.lib3dphotography.mesh.ExtractOption[] r0 = com.samsung.o3dp.lib3dphotography.mesh.ExtractOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$ExtractOption = r0
                r1 = 1
                com.samsung.o3dp.lib3dphotography.mesh.ExtractOption r2 = com.samsung.o3dp.lib3dphotography.mesh.ExtractOption.SEF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$ExtractOption     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.o3dp.lib3dphotography.mesh.ExtractOption r3 = com.samsung.o3dp.lib3dphotography.mesh.ExtractOption.JPEG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension[] r2 = com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension = r2
                com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension r3 = com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension.MP4     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension r2 = com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension.JPEG     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension r1 = com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension.HEIF     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.MeshExtractor.AnonymousClass1.<clinit>():void");
        }
    }

    public static AnimatedMesh extract(Context context, String str, SupportedExtension supportedExtension) {
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            throw new IOException("Cannot read file, please check file exists, or file is readable");
        }
        try {
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$SupportedExtension[supportedExtension.ordinal()];
            if (i2 == 1) {
                return MeshSefManager.extract(str);
            }
            if (i2 == 2) {
                return extractFromJpeg3d(str);
            }
            if (i2 == 3) {
                return extractFromHeif3d(context, str);
            }
            throw new IncompatibleClassChangeError();
        } catch (MeshDecodingException e) {
            LogUtil.e(TAG, "Failed to decode mesh", e);
            return null;
        }
    }

    private static AnimatedMesh extractFromHeif3d(Context context, String str) {
        LogUtil.i(TAG, "extract mesh from heif3d");
        try {
            try {
                HeifDecodingResult decode = new Heif3dDecoder().decode(context, FileUtil.readFileToByteArray(str));
                byte[] mesh = decode.mesh();
                byte[] cameraAnimation = decode.cameraAnimation();
                if (mesh == null || cameraAnimation == null) {
                    return null;
                }
                return new AnimatedMesh(mesh, new Animation(new String(cameraAnimation, StandardCharsets.UTF_8)));
            } catch (Heif3dException unused) {
                LogUtil.w(TAG, "Failed to decode heif. Given file can be a normal heif, not heif3d");
                return null;
            }
        } catch (IOException e) {
            Log.w(TAG, "Failed to read image when extracting heif3d", e);
            return null;
        }
    }

    private static AnimatedMesh extractFromJpeg3d(String str) {
        LogUtil.i(TAG, "extract mesh from jpeg3d");
        JpegDecodingResult decode = new Jpeg3dDecoder().decode(FileUtil.readFileToByteArray(str));
        byte[] mesh = decode.mesh();
        byte[] cameraAnimation = decode.cameraAnimation();
        if (mesh == null || cameraAnimation == null) {
            return null;
        }
        return new AnimatedMesh(mesh, new Animation(new String(cameraAnimation, StandardCharsets.UTF_8)));
    }

    public static AnimatedMesh extract(String str, ExtractOption extractOption) {
        try {
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$o3dp$lib3dphotography$mesh$ExtractOption[extractOption.ordinal()];
            if (i2 == 1) {
                return MeshSefManager.extract(str);
            }
            if (i2 == 2) {
                return extractFromJpeg3d(str);
            }
            throw new IncompatibleClassChangeError();
        } catch (MeshDecodingException e) {
            LogUtil.e(TAG, "Failed to decode mesh", e);
            return null;
        }
    }
}
