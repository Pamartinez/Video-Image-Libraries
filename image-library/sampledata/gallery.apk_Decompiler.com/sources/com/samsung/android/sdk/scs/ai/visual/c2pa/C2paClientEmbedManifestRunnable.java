package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paResult;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.visual.ai.sdkcommon.b;
import com.samsung.android.visual.ai.sdkcommon.c;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paClientEmbedManifestRunnable extends TaskRunnable<C2paResult> {
    private static final String TAG = "C2paClientEmbedManifestRunnable";
    c mCallback = new b() {
        public void onError(String str) {
            C2paClientEmbedManifestRunnable.this.mSource.setResult(new C2paResult.Builder().setSuccess(false).setError(str).build());
        }

        public void onSuccess() {
            C2paClientEmbedManifestRunnable.this.mSource.setResult(new C2paResult.Builder().setSuccess(true).build());
        }
    };
    List<String> mIngredientPaths;
    private String mJsonStr;
    String mParentPath;
    private final C2paServiceExecutor mServiceExecutor;
    private String mTargetPath;

    public C2paClientEmbedManifestRunnable(C2paServiceExecutor c2paServiceExecutor) {
        this.mServiceExecutor = c2paServiceExecutor;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() {
        /*
            r5 = this;
            java.lang.String r0 = TAG
            java.lang.String r1 = "execute embedManifestToFile()"
            com.samsung.android.sdk.scs.base.utils.Log.d(r0, r1)
            java.lang.String r0 = r5.mTargetPath     // Catch:{ Exception -> 0x00b9 }
            android.os.ParcelFileDescriptor r0 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getParcelFileDescriptor(r0)     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r1 = r5.mTargetPath     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r1 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getFileExtension(r1)     // Catch:{ Exception -> 0x00b9 }
            if (r0 == 0) goto L_0x00bb
            if (r1 == 0) goto L_0x00bb
            java.lang.String r2 = r5.mJsonStr     // Catch:{ Exception -> 0x00b9 }
            if (r2 == 0) goto L_0x00bb
            java.lang.String r2 = r5.mTargetPath     // Catch:{ Exception -> 0x00b9 }
            if (r2 != 0) goto L_0x0021
            goto L_0x00bb
        L_0x0021:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r2 = new com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder     // Catch:{ Exception -> 0x00b9 }
            r2.<init>()     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r3 = r5.mJsonStr     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r2 = r2.setManifestJson(r3)     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r2.setTargetPFD(r0)     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setTargetExtensionType(r1)     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r1 = r5.mTargetPath     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setTargetPath(r1)     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r1 = r5.mParentPath     // Catch:{ Exception -> 0x00b9 }
            r2 = 0
            if (r1 != 0) goto L_0x0041
            r1 = r2
            goto L_0x0045
        L_0x0041:
            android.os.ParcelFileDescriptor r1 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getParcelFileDescriptor(r1)     // Catch:{ Exception -> 0x00b9 }
        L_0x0045:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setParentPFD(r1)     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r1 = r5.mParentPath     // Catch:{ Exception -> 0x00b9 }
            if (r1 != 0) goto L_0x004f
            r1 = r2
            goto L_0x0053
        L_0x004f:
            java.lang.String r1 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getFileExtension(r1)     // Catch:{ Exception -> 0x00b9 }
        L_0x0053:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setParentExtensionType(r1)     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r1 = r5.mParentPath     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setParentPath(r1)     // Catch:{ Exception -> 0x00b9 }
            java.util.List<java.lang.String> r1 = r5.mIngredientPaths     // Catch:{ Exception -> 0x00b9 }
            if (r1 != 0) goto L_0x0063
            r1 = r2
            goto L_0x007b
        L_0x0063:
            java.util.stream.Stream r1 = r1.stream()     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.a r3 = new com.samsung.android.sdk.scs.ai.visual.c2pa.a     // Catch:{ Exception -> 0x00b9 }
            r4 = 0
            r3.<init>(r4)     // Catch:{ Exception -> 0x00b9 }
            java.util.stream.Stream r1 = r1.map(r3)     // Catch:{ Exception -> 0x00b9 }
            java.util.stream.Collector r3 = java.util.stream.Collectors.toList()     // Catch:{ Exception -> 0x00b9 }
            java.lang.Object r1 = r1.collect(r3)     // Catch:{ Exception -> 0x00b9 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ Exception -> 0x00b9 }
        L_0x007b:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setIngredientPFD(r1)     // Catch:{ Exception -> 0x00b9 }
            java.util.List<java.lang.String> r1 = r5.mIngredientPaths     // Catch:{ Exception -> 0x00b9 }
            if (r1 != 0) goto L_0x0084
            goto L_0x009d
        L_0x0084:
            java.util.stream.Stream r1 = r1.stream()     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.a r2 = new com.samsung.android.sdk.scs.ai.visual.c2pa.a     // Catch:{ Exception -> 0x00b9 }
            r3 = 1
            r2.<init>(r3)     // Catch:{ Exception -> 0x00b9 }
            java.util.stream.Stream r1 = r1.map(r2)     // Catch:{ Exception -> 0x00b9 }
            java.util.stream.Collector r2 = java.util.stream.Collectors.toList()     // Catch:{ Exception -> 0x00b9 }
            java.lang.Object r1 = r1.collect(r2)     // Catch:{ Exception -> 0x00b9 }
            r2 = r1
            java.util.List r2 = (java.util.List) r2     // Catch:{ Exception -> 0x00b9 }
        L_0x009d:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setIngredientExtensionTypes(r2)     // Catch:{ Exception -> 0x00b9 }
            java.util.List<java.lang.String> r1 = r5.mIngredientPaths     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setIngredientPaths(r1)     // Catch:{ Exception -> 0x00b9 }
            android.os.Bundle r0 = r0.build()     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paServiceExecutor r1 = r5.mServiceExecutor     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.visual.ai.sdkcommon.o r1 = r1.getC2PAService()     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.visual.ai.sdkcommon.c r2 = r5.mCallback     // Catch:{ Exception -> 0x00b9 }
            com.samsung.android.visual.ai.sdkcommon.m r1 = (com.samsung.android.visual.ai.sdkcommon.m) r1     // Catch:{ Exception -> 0x00b9 }
            r1.d(r0, r2)     // Catch:{ Exception -> 0x00b9 }
            return
        L_0x00b9:
            r0 = move-exception
            goto L_0x00d7
        L_0x00bb:
            if (r0 == 0) goto L_0x00cf
            java.io.FileDescriptor r1 = r0.getFileDescriptor()     // Catch:{ Exception -> 0x00cb }
            boolean r1 = r1.valid()     // Catch:{ Exception -> 0x00cb }
            if (r1 == 0) goto L_0x00cf
            r0.close()     // Catch:{ Exception -> 0x00cb }
            goto L_0x00cf
        L_0x00cb:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x00b9 }
        L_0x00cf:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x00b9 }
            java.lang.String r1 = "Target PFD/Extension/JSON is NULL"
            r0.<init>(r1)     // Catch:{ Exception -> 0x00b9 }
            throw r0     // Catch:{ Exception -> 0x00b9 }
        L_0x00d7:
            r0.printStackTrace()
            com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource<TResult> r5 = r5.mSource
            r5.setException(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.visual.c2pa.C2paClientEmbedManifestRunnable.execute():void");
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_C2PA;
    }

    public void setIngredientPaths(List<String> list) {
        this.mIngredientPaths = list;
    }

    public void setJson(String str) {
        this.mJsonStr = str;
    }

    public void setParentPath(String str) {
        this.mParentPath = str;
    }

    public void setTargetPath(String str) {
        this.mTargetPath = str;
    }
}
