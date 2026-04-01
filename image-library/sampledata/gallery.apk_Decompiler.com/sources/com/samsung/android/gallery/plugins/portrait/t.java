package com.samsung.android.gallery.plugins.portrait;

import android.app.appsearch.GenericDocument;
import android.app.appsearch.GlobalSearchSession;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class t {
    public static /* synthetic */ GenericDocument.Builder b() {
        return new GenericDocument.Builder("", "", "");
    }

    public static /* synthetic */ GenericDocument.Builder c(String str) {
        return new GenericDocument.Builder("", "", str);
    }

    public static /* synthetic */ GenericDocument.Builder d(String str, String str2) {
        return new GenericDocument.Builder("", str, str2);
    }

    public static /* bridge */ /* synthetic */ GlobalSearchSession f(Object obj) {
        return (GlobalSearchSession) obj;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: MethodInlineVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.MethodInlineVisitor.inlineMth(MethodInlineVisitor.java:57)
        	at jadx.core.dex.visitors.MethodInlineVisitor.visit(MethodInlineVisitor.java:47)
        */
    public static /* synthetic */ void n() {
        /*
            android.app.appsearch.GenericDocument$Builder r0 = new android.app.appsearch.GenericDocument$Builder
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.plugins.portrait.t.n():void");
    }
}
