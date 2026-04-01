package com.samsung.android.gallery.module.fileio.compat;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FileOpJob f3015a;
    public final /* synthetic */ String b;

    public /* synthetic */ d(FileOpJob fileOpJob, String str) {
        this.f3015a = fileOpJob;
        this.b = str;
    }

    public final Object apply(Object obj) {
        return new FileOpJob((FileItemInterface) obj, this.f3015a.targetDir, this.f3015a.operation, false).inSameStorage(this.b);
    }
}
