package com.samsung.android.gallery.support.utils;

import android.content.Context;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.support.utils.SignatureChecker;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class P implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ P(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((SignatureChecker.SignatureHashHolder.AnonymousClass1) obj2).lambda$new$0((SignatureSystem) obj);
                return;
            case 1:
                ((SystemEnvironment.Observer) obj).listener.onEnvironmentChange((Context) obj2);
                return;
            case 2:
                FileUtils.lambda$loadMountedVolumes$3((StringJoiner) obj2, (String) obj);
                return;
            case 3:
                ((ConcurrentHashMap) obj2).remove((Integer) obj);
                return;
            case 4:
                ((ThreadWatchDog) obj2).onLog((String) obj);
                return;
            default:
                ((Button) obj).setTextColor(Utils.getPermanentDeleteButtonTextColor(((AlertDialog) obj2).getContext()));
                return;
        }
    }
}
