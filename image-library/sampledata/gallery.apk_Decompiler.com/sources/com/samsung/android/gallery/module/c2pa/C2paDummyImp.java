package com.samsung.android.gallery.module.c2pa;

import A.a;
import H3.l;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NamedThreadHandler;
import com.samsung.android.visual.ai.sdkcommon.c;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paDummyImp implements C2paImp {
    private final NamedThreadHandler mThreadHandler = new NamedThreadHandler("C2paDummy");

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$create$0(c cVar) {
        try {
            cVar.onSuccess();
        } catch (Exception e) {
            a.s(e, new StringBuilder("exception create "), "C2paDummyImp");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$update$1(Function function, FileItemInterface fileItemInterface, c cVar) {
        try {
            if (((Boolean) function.apply(fileItemInterface)).booleanValue()) {
                cVar.onSuccess();
            } else {
                cVar.onError("error on updateFunc");
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("exception update "), "C2paDummyImp");
        }
    }

    public void create(String str, FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2, boolean z, c cVar) {
        this.mThreadHandler.run(new l(19, cVar));
    }

    public void extract(String str, Consumer<C2paInfo> consumer) {
        Log.e("C2paDummyImp", "Not support");
        consumer.accept(C2paInfo.EMPTY);
    }

    public void update(String str, FileItemInterface fileItemInterface, Function<FileItemInterface, Boolean> function, c cVar) {
        this.mThreadHandler.run(new J6.c(function, fileItemInterface, cVar, 5));
    }

    public void close() {
    }

    public void open() {
    }
}
