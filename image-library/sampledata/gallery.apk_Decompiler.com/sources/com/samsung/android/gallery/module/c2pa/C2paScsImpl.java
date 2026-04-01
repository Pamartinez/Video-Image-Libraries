package com.samsung.android.gallery.module.c2pa;

import A.a;
import A4.C0371f;
import A4.H;
import B8.k;
import K8.C0573a;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.MalformedJsonException;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paClient;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paError;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestsCallback;
import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.visual.ai.sdkcommon.c;
import com.samsung.android.visual.ai.sdkcommon.m;
import com.samsung.android.visual.ai.sdkcommon.o;
import j.C0217b;
import java.io.File;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paScsImpl implements C2paImp {
    protected final String TAG = getClass().getSimpleName();
    protected volatile C2paClient mC2paClient;

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008f, code lost:
        r7.onSuccess();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0092, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
        if (com.samsung.android.gallery.module.data.DetailsData.of(r5).hasC2pa != false) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001c, code lost:
        if (((com.samsung.android.visual.ai.sdkcommon.m) r2).f(r5.getPath()) != false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createFile(com.samsung.android.visual.ai.sdkcommon.o r2, java.lang.String r3, com.samsung.android.gallery.database.dbtype.FileItemInterface r4, com.samsung.android.gallery.database.dbtype.FileItemInterface r5, boolean r6, com.samsung.android.visual.ai.sdkcommon.c r7) {
        /*
            r1 = this;
            com.samsung.android.gallery.support.utils.PocFeatures r1 = com.samsung.android.gallery.support.utils.PocFeatures.C2paSecMp
            boolean r1 = com.samsung.android.gallery.support.utils.PocFeatures.isEnabled(r1)
            if (r1 == 0) goto L_0x0011
            com.samsung.android.gallery.module.data.DetailsData r1 = com.samsung.android.gallery.module.data.DetailsData.of(r5)
            boolean r1 = r1.hasC2pa
            if (r1 == 0) goto L_0x008f
            goto L_0x001e
        L_0x0011:
            java.lang.String r1 = r5.getPath()
            r0 = r2
            com.samsung.android.visual.ai.sdkcommon.m r0 = (com.samsung.android.visual.ai.sdkcommon.m) r0
            boolean r1 = r0.f(r1)
            if (r1 == 0) goto L_0x008f
        L_0x001e:
            java.lang.String r1 = r5.getPath()
            java.lang.String r4 = r4.getPath()
            if (r4 == 0) goto L_0x0089
            if (r1 != 0) goto L_0x002b
            goto L_0x0089
        L_0x002b:
            if (r6 == 0) goto L_0x0077
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r5 = new com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder     // Catch:{ Exception -> 0x0075 }
            r5.<init>()     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r3 = r5.setManifestJson(r3)     // Catch:{ Exception -> 0x0075 }
            android.os.ParcelFileDescriptor r5 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getParcelFileDescriptor(r4)     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r3 = r3.setTargetPFD(r5)     // Catch:{ Exception -> 0x0075 }
            java.lang.String r5 = com.samsung.android.gallery.support.utils.FileUtils.getExtension(r4)     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r3 = r3.setTargetExtensionType(r5)     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r3 = r3.setTargetPath(r4)     // Catch:{ Exception -> 0x0075 }
            android.os.ParcelFileDescriptor r4 = com.samsung.android.sdk.scs.ai.visual.c2pa.C2paUtils.getParcelFileDescriptor(r1)     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r3 = r3.setParentPFD(r4)     // Catch:{ Exception -> 0x0075 }
            java.lang.String r4 = com.samsung.android.gallery.support.utils.FileUtils.getExtension(r1)     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r3 = r3.setParentExtensionType(r4)     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r3.setParentPath(r1)     // Catch:{ Exception -> 0x0075 }
            r3 = 0
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setIngredientPFD(r3)     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setIngredientExtensionTypes(r3)     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.sdk.scs.ai.visual.c2pa.C2paParam$EmbedParamBuilder r1 = r1.setIngredientPaths(r3)     // Catch:{ Exception -> 0x0075 }
            android.os.Bundle r1 = r1.build()     // Catch:{ Exception -> 0x0075 }
            com.samsung.android.visual.ai.sdkcommon.m r2 = (com.samsung.android.visual.ai.sdkcommon.m) r2     // Catch:{ Exception -> 0x0075 }
            r2.d(r1, r7)     // Catch:{ Exception -> 0x0075 }
            return
        L_0x0075:
            r1 = move-exception
            goto L_0x0081
        L_0x0077:
            com.samsung.android.visual.ai.sdkcommon.m r2 = (com.samsung.android.visual.ai.sdkcommon.m) r2     // Catch:{ Exception -> 0x0075 }
            java.lang.String r1 = r2.h(r1)     // Catch:{ Exception -> 0x0075 }
            r2.c(r3, r4, r1, r7)     // Catch:{ Exception -> 0x0075 }
            return
        L_0x0081:
            java.lang.String r1 = r1.getMessage()
            r7.onError(r1)
            return
        L_0x0089:
            java.lang.String r1 = "invalid path"
            r7.onError(r1)
            return
        L_0x008f:
            r7.onSuccess()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.c2pa.C2paScsImpl.createFile(com.samsung.android.visual.ai.sdkcommon.o, java.lang.String, com.samsung.android.gallery.database.dbtype.FileItemInterface, com.samsung.android.gallery.database.dbtype.FileItemInterface, boolean, com.samsung.android.visual.ai.sdkcommon.c):void");
    }

    private void executeIDpsC2pa(Consumer<o> consumer) {
        if (this.mC2paClient == null) {
            Log.e(this.TAG, "execute failed. no client");
        } else {
            this.mC2paClient.getC2paService().addOnCompleteListener(new C0217b(0), new H(26, (Object) this, (Object) consumer));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$create$1(c cVar, String str, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2, boolean z, o oVar) {
        Exception exc;
        C2paScsImpl c2paScsImpl;
        if (oVar == null) {
            try {
                Log.e(this.TAG, "create failed. no service");
                cVar.onError("c2pa is null");
            } catch (Exception e) {
                exc = e;
                c2paScsImpl = this;
                a.s(exc, new StringBuilder("create failed. e="), c2paScsImpl.TAG);
            }
        } else {
            c2paScsImpl = this;
            try {
                c2paScsImpl.createFile(oVar, str, fileItemInterface, fileItemInterface2, z, cVar);
            } catch (Exception e7) {
                exc = e7;
                a.s(exc, new StringBuilder("create failed. e="), c2paScsImpl.TAG);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeIDpsC2pa$3(Consumer consumer, Task task) {
        o oVar = (o) task.getResult();
        if (oVar != null) {
            consumer.accept(oVar);
        } else {
            Log.e(this.TAG, "execute failed. no service");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extract$0(String str, final Consumer consumer, o oVar) {
        String str2;
        if (oVar != null) {
            try {
                m mVar = (m) oVar;
                if (mVar.f(str)) {
                    mVar.e(new C2paParam.ExtractParamBuilder().setPfd(ParcelFileDescriptor.open(new File(str), 268435456)).setExtensionType(FileUtils.getExtension(str)).setFilePath(str).build(), new C2paManifestsCallback() {
                        public void onError(String str) {
                            String str2 = C2paScsImpl.this.TAG;
                            Log.w(str2, "extract#onError " + C2paError.Companion.fromErrorString(str));
                            consumer.accept(C2paInfo.EMPTY);
                        }

                        public void onResult(String str, boolean z, boolean z3) {
                            try {
                                consumer.accept(new C2paInfo(C2paClient.fromJson(str), z));
                            } catch (MalformedJsonException e) {
                                String str2 = C2paScsImpl.this.TAG;
                                Log.w(str2, "extract#exception " + e.getMessage());
                                consumer.accept(C2paInfo.EMPTY);
                            }
                        }
                    });
                    return;
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("extract failed. e="), this.TAG);
                consumer.accept(C2paInfo.EMPTY);
                return;
            }
        }
        String str3 = this.TAG;
        if (oVar == null) {
            str2 = "no service";
        } else {
            str2 = "no data";
        }
        Log.d(str3, "extract c2pa failed", str2);
        consumer.accept(C2paInfo.EMPTY);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$2(c cVar, String str, FileItemInterface fileItemInterface, Function function, o oVar) {
        Exception exc;
        C2paScsImpl c2paScsImpl;
        if (oVar == null) {
            try {
                Log.e(this.TAG, "update failed. no service");
                cVar.onError("c2pa is null");
            } catch (Exception e) {
                exc = e;
                c2paScsImpl = this;
                a.s(exc, new StringBuilder("update failed. e="), c2paScsImpl.TAG);
            }
        } else {
            c2paScsImpl = this;
            try {
                c2paScsImpl.updateFile(oVar, str, fileItemInterface, function, cVar);
            } catch (Exception e7) {
                exc = e7;
                a.s(exc, new StringBuilder("update failed. e="), c2paScsImpl.TAG);
            }
        }
    }

    private void updateFile(o oVar, String str, FileItemInterface fileItemInterface, Function<FileItemInterface, Boolean> function, c cVar) {
        if (!updateRawFile(oVar, fileItemInterface, function, cVar)) {
            m mVar = (m) oVar;
            String h5 = mVar.h(fileItemInterface.getPath());
            if (TextUtils.isEmpty(h5) || !function.apply(fileItemInterface).booleanValue()) {
                String str2 = this.TAG;
                Log.e(str2, "updateFunc fail file = " + Logger.getEncodedString(h5));
                this.mC2paClient.clearManifestsFromCache(fileItemInterface.getPath());
                cVar.onError("updateFunc fail");
                return;
            }
            Log.d(this.TAG, "updateFunc success");
            mVar.c(str, fileItemInterface.getPath(), h5, cVar);
        }
    }

    private boolean updateRawFile(o oVar, FileItemInterface fileItemInterface, Function<FileItemInterface, Boolean> function, c cVar) {
        if (!PocFeatures.isEnabled(PocFeatures.C2paSecMp)) {
            if (((m) oVar).f(fileItemInterface.getPath())) {
                return false;
            }
        } else if (DetailsData.of(fileItemInterface).hasC2pa) {
            return false;
        }
        if (function.apply(fileItemInterface).booleanValue()) {
            Log.d(this.TAG, "updateFunc raw success");
            cVar.onSuccess();
            return true;
        }
        cVar.onError("updateFunc raw fail");
        return true;
    }

    public void close() {
        if (this.mC2paClient != null) {
            this.mC2paClient.release();
            this.mC2paClient = null;
        }
    }

    public void create(String str, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2, boolean z, c cVar) {
        executeIDpsC2pa(new C0573a(this, cVar, str, fileItemInterface, fileItemInterface2, z));
    }

    public void extract(String str, Consumer<C2paInfo> consumer) {
        executeIDpsC2pa(new C0371f((Object) this, (Object) str, (Object) consumer, 7));
    }

    public void open() {
        if (this.mC2paClient == null) {
            this.mC2paClient = new C2paClient(AppResources.getAppContext());
        }
    }

    public void update(String str, FileItemInterface fileItemInterface, Function<FileItemInterface, Boolean> function, c cVar) {
        executeIDpsC2pa(new k(this, cVar, str, fileItemInterface, function, 1));
    }
}
