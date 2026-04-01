package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paResult;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.visual.ai.sdkcommon.b;
import com.samsung.android.visual.ai.sdkcommon.c;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paClientSaveToCacheEmbedToFileRunnable extends TaskRunnable<C2paResult> {
    private static final String TAG = "C2paClientSaveToCacheEmbedToFileRunnable";
    c mCallback = new b() {
        public void onError(String str) {
            C2paClientSaveToCacheEmbedToFileRunnable.this.mSource.setResult(new C2paResult.Builder().setSuccess(false).setError(str).build());
        }

        public void onSuccess() {
            C2paClientSaveToCacheEmbedToFileRunnable.this.mSource.setResult(new C2paResult.Builder().setSuccess(true).build());
        }
    };
    List<String> mIngredientPaths;
    private String mJsonStr;
    String mParentPath;
    private final C2paServiceExecutor mServiceExecutor;
    private String mTargetPath;

    public C2paClientSaveToCacheEmbedToFileRunnable(C2paServiceExecutor c2paServiceExecutor) {
        this.mServiceExecutor = c2paServiceExecutor;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() {
        /*
            r5 = this;
            java.lang.String r0 = TAG
            java.lang.String r1 = "execute embedManifestToFile()"
            com.samsung.android.sdk.scs.base.utils.Log.d(r0, r1)
            java.lang.String r0 = r5.mParentPath     // Catch:{ Exception -> 0x00ea }
            java.lang.String r0 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getFileExtension(r0)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r1 = r5.mParentPath     // Catch:{ Exception -> 0x00ea }
            android.os.ParcelFileDescriptor r1 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getParcelFileDescriptor(r1)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r2 = r5.mTargetPath     // Catch:{ Exception -> 0x00ea }
            android.os.ParcelFileDescriptor r2 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getParcelFileDescriptor(r2)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r3 = r5.mTargetPath     // Catch:{ Exception -> 0x00ea }
            java.lang.String r3 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getFileExtension(r3)     // Catch:{ Exception -> 0x00ea }
            if (r2 == 0) goto L_0x00ec
            if (r1 == 0) goto L_0x00ec
            if (r0 == 0) goto L_0x00ec
            if (r3 == 0) goto L_0x00ec
            java.lang.String r4 = r5.mJsonStr     // Catch:{ Exception -> 0x00ea }
            if (r4 == 0) goto L_0x00ec
            java.lang.String r4 = r5.mTargetPath     // Catch:{ Exception -> 0x00ea }
            if (r4 == 0) goto L_0x00ec
            java.lang.String r4 = r5.mParentPath     // Catch:{ Exception -> 0x00ea }
            if (r4 != 0) goto L_0x0035
            goto L_0x00ec
        L_0x0035:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$SaveToCacheParamBuilder r4 = new com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$SaveToCacheParamBuilder     // Catch:{ Exception -> 0x00ea }
            r4.<init>()     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$SaveToCacheParamBuilder r1 = r4.setPfd(r1)     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$SaveToCacheParamBuilder r0 = r1.setExtensionType(r0)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r1 = r5.mParentPath     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$SaveToCacheParamBuilder r0 = r0.setFilePath(r1)     // Catch:{ Exception -> 0x00ea }
            android.os.Bundle r0 = r0.build()     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paServiceExecutor r1 = r5.mServiceExecutor     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.visual.ai.sdkcommon.o r1 = r1.getC2PAService()     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.visual.ai.sdkcommon.m r1 = (com.samsung.android.visual.ai.sdkcommon.m) r1     // Catch:{ Exception -> 0x00ea }
            java.lang.String r0 = r1.i(r0)     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = new com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder     // Catch:{ Exception -> 0x00ea }
            r1.<init>()     // Catch:{ Exception -> 0x00ea }
            java.lang.String r4 = r5.mJsonStr     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setManifestJson(r4)     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setTargetPFD(r2)     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setTargetExtensionType(r3)     // Catch:{ Exception -> 0x00ea }
            java.lang.String r2 = r5.mTargetPath     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setTargetPath(r2)     // Catch:{ Exception -> 0x00ea }
            r2 = 0
            if (r0 != 0) goto L_0x0076
            r3 = r2
            goto L_0x007a
        L_0x0076:
            android.os.ParcelFileDescriptor r3 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getParcelFileDescriptor(r0)     // Catch:{ Exception -> 0x00ea }
        L_0x007a:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setParentPFD(r3)     // Catch:{ Exception -> 0x00ea }
            if (r0 != 0) goto L_0x0082
            r3 = r2
            goto L_0x0086
        L_0x0082:
            java.lang.String r3 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getFileExtension(r0)     // Catch:{ Exception -> 0x00ea }
        L_0x0086:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setParentExtensionType(r3)     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r1.setParentPath(r0)     // Catch:{ Exception -> 0x00ea }
            java.util.List<java.lang.String> r1 = r5.mIngredientPaths     // Catch:{ Exception -> 0x00ea }
            if (r1 != 0) goto L_0x0094
            r1 = r2
            goto L_0x00ac
        L_0x0094:
            java.util.stream.Stream r1 = r1.stream()     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.a r3 = new com.samsung.android.sdk.scs.ai.visual.c2pa.a     // Catch:{ Exception -> 0x00ea }
            r4 = 0
            r3.<init>(r4)     // Catch:{ Exception -> 0x00ea }
            java.util.stream.Stream r1 = r1.map(r3)     // Catch:{ Exception -> 0x00ea }
            java.util.stream.Collector r3 = java.util.stream.Collectors.toList()     // Catch:{ Exception -> 0x00ea }
            java.lang.Object r1 = r1.collect(r3)     // Catch:{ Exception -> 0x00ea }
            java.util.List r1 = (java.util.List) r1     // Catch:{ Exception -> 0x00ea }
        L_0x00ac:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setIngredientPFD(r1)     // Catch:{ Exception -> 0x00ea }
            java.util.List<java.lang.String> r1 = r5.mIngredientPaths     // Catch:{ Exception -> 0x00ea }
            if (r1 != 0) goto L_0x00b5
            goto L_0x00ce
        L_0x00b5:
            java.util.stream.Stream r1 = r1.stream()     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.a r2 = new com.samsung.android.sdk.scs.ai.visual.c2pa.a     // Catch:{ Exception -> 0x00ea }
            r3 = 1
            r2.<init>(r3)     // Catch:{ Exception -> 0x00ea }
            java.util.stream.Stream r1 = r1.map(r2)     // Catch:{ Exception -> 0x00ea }
            java.util.stream.Collector r2 = java.util.stream.Collectors.toList()     // Catch:{ Exception -> 0x00ea }
            java.lang.Object r1 = r1.collect(r2)     // Catch:{ Exception -> 0x00ea }
            r2 = r1
            java.util.List r2 = (java.util.List) r2     // Catch:{ Exception -> 0x00ea }
        L_0x00ce:
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setIngredientExtensionTypes(r2)     // Catch:{ Exception -> 0x00ea }
            java.util.List<java.lang.String> r1 = r5.mIngredientPaths     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r0 = r0.setIngredientPaths(r1)     // Catch:{ Exception -> 0x00ea }
            android.os.Bundle r0 = r0.build()     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paServiceExecutor r1 = r5.mServiceExecutor     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.visual.ai.sdkcommon.o r1 = r1.getC2PAService()     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.visual.ai.sdkcommon.c r2 = r5.mCallback     // Catch:{ Exception -> 0x00ea }
            com.samsung.android.visual.ai.sdkcommon.m r1 = (com.samsung.android.visual.ai.sdkcommon.m) r1     // Catch:{ Exception -> 0x00ea }
            r1.d(r0, r2)     // Catch:{ Exception -> 0x00ea }
            return
        L_0x00ea:
            r0 = move-exception
            goto L_0x0119
        L_0x00ec:
            if (r2 == 0) goto L_0x00fe
            java.io.FileDescriptor r0 = r2.getFileDescriptor()     // Catch:{ Exception -> 0x00fc }
            boolean r0 = r0.valid()     // Catch:{ Exception -> 0x00fc }
            if (r0 == 0) goto L_0x00fe
            r2.close()     // Catch:{ Exception -> 0x00fc }
            goto L_0x00fe
        L_0x00fc:
            r0 = move-exception
            goto L_0x010e
        L_0x00fe:
            if (r1 == 0) goto L_0x0111
            java.io.FileDescriptor r0 = r1.getFileDescriptor()     // Catch:{ Exception -> 0x00fc }
            boolean r0 = r0.valid()     // Catch:{ Exception -> 0x00fc }
            if (r0 == 0) goto L_0x0111
            r1.close()     // Catch:{ Exception -> 0x00fc }
            goto L_0x0111
        L_0x010e:
            r0.printStackTrace()     // Catch:{ Exception -> 0x00ea }
        L_0x0111:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x00ea }
            java.lang.String r1 = "Target PFD/Extension/JSON is NULL"
            r0.<init>(r1)     // Catch:{ Exception -> 0x00ea }
            throw r0     // Catch:{ Exception -> 0x00ea }
        L_0x0119:
            r0.printStackTrace()
            com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource<TResult> r5 = r5.mSource
            r5.setException(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.visual.c2pa.C2paClientSaveToCacheEmbedToFileRunnable.execute():void");
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
