package com.samsung.android.vexfwk.sdk.imagetagger;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.common.util.concurrent.ListenableFuture;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase;
import com.samsung.android.vexfwk.sdk.common.a;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;
import ud.d;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\tJ!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\t\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u0002H\fH\u0002¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\u0010\u001a\u00020\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetagger/VexFwkImageTaggerV2;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBase;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "configure", "Lcom/google/common/util/concurrent/ListenableFuture;", "detectImageTagsImpl", "Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerV2Result;", "T", "buffer", "(Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;", "detectImageTags", "image", "Landroid/graphics/Bitmap;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageTaggerV2 extends VexFwkHelperBase<VexFwkImageTaggerV2> {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_IMAGE = 0;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkImageTaggerV2";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkHelperBase.Companion.isAvailable(VexFwkUsecase.IMAGE_TAGGER_V2);
    private final Context context;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\n\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetagger/VexFwkImageTaggerV2$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_IMAGE", "", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkImageTaggerV2.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    public VexFwkImageTaggerV2(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final <T> ListenableFuture detectImageTagsImpl(T t) {
        return supplyAsyncProcess(new a(t, (C1227c) null, 1));
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    public final ListenableFuture configure() {
        return supplyAsyncConfiguration(new d(2, (C1227c) null, 0));
    }

    public final ListenableFuture detectImageTags(Bitmap bitmap) {
        j.e(bitmap, "image");
        return detectImageTagsImpl(bitmap);
    }

    public Context getContext() {
        return this.context;
    }
}
