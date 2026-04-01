package com.samsung.android.gallery.module.publisher;

import A8.C0545b;
import D3.b;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.function.Function;

/* renamed from: com.samsung.android.gallery.module.publisher.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0632l implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3068a;

    public /* synthetic */ C0632l(int i2) {
        this.f3068a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3068a) {
            case 0:
                return Boolean.valueOf(Arrays.stream(((String) obj).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).mapToInt(new C0545b(3)).anyMatch(new b(1)));
            case 1:
                return AlbumDataPublisher.lambda$getFolderBucketIdsFromCursor$22((Integer) obj);
            default:
                return DataChangeEventPublisher.lambda$deferChangeEvent$1((Integer) obj);
        }
    }
}
