package com.samsung.android.gallery.support.utils;

import android.content.SharedPreferences;
import android.util.Pair;
import java.util.function.Consumer;

/* renamed from: com.samsung.android.gallery.support.utils.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0675m implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharedPreferences.Editor e;

    public /* synthetic */ C0675m(SharedPreferences.Editor editor, int i2) {
        this.d = i2;
        this.e = editor;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SharedPreferences.Editor editor = this.e;
        switch (i2) {
            case 0:
                editor.remove((String) obj);
                return;
            case 1:
                editor.putBoolean((String) ((Pair) obj).first, ((Boolean) ((Pair) obj).second).booleanValue());
                return;
            case 2:
                editor.putString((String) ((Pair) obj).first, (String) ((Pair) obj).second);
                return;
            case 3:
                editor.putLong((String) ((Pair) obj).first, ((Long) ((Pair) obj).second).longValue());
                return;
            case 4:
                editor.putInt((String) ((Pair) obj).first, ((Integer) ((Pair) obj).second).intValue());
                return;
            case 5:
                editor.putFloat((String) ((Pair) obj).first, ((Float) ((Pair) obj).second).floatValue());
                return;
            default:
                editor.putString((String) ((Pair) obj).first, (String) ((Pair) obj).second);
                return;
        }
    }
}
