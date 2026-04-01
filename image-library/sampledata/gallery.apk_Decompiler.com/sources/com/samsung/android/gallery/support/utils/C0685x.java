package com.samsung.android.gallery.support.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/* renamed from: com.samsung.android.gallery.support.utils.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0685x implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0685x(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((AtomicReference) this.e).set(((Supplier) this.f).get());
                return;
            case 1:
                PermissionHelper.lambda$showSnackBar$0((Context) this.e, (View) this.f);
                return;
            case 2:
                ((SafeClipboard) this.e).lambda$getPrimaryClip$1((ClipData[]) this.f);
                return;
            case 3:
                ((SafeClipboard) this.e).lambda$hasImageClip$0((AtomicBoolean) this.f);
                return;
            case 4:
                ((SafeClipboard) this.e).lambda$getPrimaryClipDescription$3((ClipDescription[]) this.f);
                return;
            case 5:
                Toast.makeText((Context) this.e, (String) this.f, 0).show();
                return;
            default:
                ((WatchDog) this.e).lambda$createLooperWatchDogLogging$0((Looper) this.f);
                return;
        }
    }
}
