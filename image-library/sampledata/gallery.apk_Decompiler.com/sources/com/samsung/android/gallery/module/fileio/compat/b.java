package com.samsung.android.gallery.module.fileio.compat;

import android.content.ContentProviderOperation;
import com.samsung.android.gallery.module.data.ContentUri;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3014a;

    public /* synthetic */ b(int i2) {
        this.f3014a = i2;
    }

    public final Object apply(Object obj) {
        FileOpJob fileOpJob = (FileOpJob) obj;
        switch (this.f3014a) {
            case 0:
                return fileOpJob.target;
            default:
                return ContentProviderOperation.newUpdate(ContentUri.getWritableUri(fileOpJob.source)).withValues(MediaStoreApi.buildRename(fileOpJob.target)).build();
        }
    }
}
