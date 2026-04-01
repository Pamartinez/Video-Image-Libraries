package com.samsung.android.gallery.module.fileio.redact;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ FileEditor d;
    public final /* synthetic */ FileItemInterface e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f3021h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Runnable f3022i;

    public /* synthetic */ g(FileEditor fileEditor, FileItemInterface fileItemInterface, ArrayList arrayList, int i2, boolean z, Runnable runnable) {
        this.d = fileEditor;
        this.e = fileItemInterface;
        this.f = arrayList;
        this.g = i2;
        this.f3021h = z;
        this.f3022i = runnable;
    }

    public final void accept(Object obj) {
        this.d.lambda$runGroupItems$5(this.e, this.f, this.g, this.f3021h, this.f3022i, (Boolean) obj);
    }
}
