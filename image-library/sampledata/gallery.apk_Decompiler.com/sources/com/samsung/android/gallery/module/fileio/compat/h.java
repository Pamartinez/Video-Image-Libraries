package com.samsung.android.gallery.module.fileio.compat;

import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3018a;
    public final /* synthetic */ RestoreUserData.BulkOperation b;

    public /* synthetic */ h(RestoreUserData.BulkOperation bulkOperation, int i2) {
        this.f3018a = i2;
        this.b = bulkOperation;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f3018a;
        RestoreUserData.BulkOperation bulkOperation = this.b;
        switch (i2) {
            case 0:
                ((RestoreUserData.CapturedBulkOperation) bulkOperation).lambda$buildOperations$0((String) obj, obj2);
                return;
            case 1:
                ((RestoreUserData.FavoriteBulkOperation) bulkOperation).lambda$buildOperations$0((String) obj, obj2);
                return;
            default:
                ((RestoreUserData.TagBulkOperation) bulkOperation).lambda$buildOperations$0((String) obj, obj2);
                return;
        }
    }
}
