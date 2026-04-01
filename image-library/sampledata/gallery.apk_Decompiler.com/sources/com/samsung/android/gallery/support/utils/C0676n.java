package com.samsung.android.gallery.support.utils;

import android.content.SharedPreferences;
import java.util.function.BiConsumer;

/* renamed from: com.samsung.android.gallery.support.utils.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0676n implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3181a;
    public final /* synthetic */ SharedPreferences.Editor b;

    public /* synthetic */ C0676n(SharedPreferences.Editor editor, int i2) {
        this.f3181a = i2;
        this.b = editor;
    }

    public final void accept(Object obj, Object obj2) {
        String str = (String) obj;
        switch (this.f3181a) {
            case 0:
                this.b.putBoolean(str, ((Boolean) obj2).booleanValue());
                return;
            case 1:
                this.b.putString(str, (String) obj2);
                return;
            case 2:
                this.b.putLong(str, ((Long) obj2).longValue());
                return;
            default:
                this.b.putInt(str, ((Integer) obj2).intValue());
                return;
        }
    }
}
