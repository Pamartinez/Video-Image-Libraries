package i;

import android.app.appfunctions.AppFunctionException;
import android.app.appfunctions.ExecuteAppFunctionResponse;
import android.app.appsearch.EmbeddingVector;
import android.app.appsearch.GenericDocument;
import android.os.Bundle;

/* renamed from: i.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class C0213b {
    public static /* synthetic */ AppFunctionException a(int i2, String str, Bundle bundle) {
        return new AppFunctionException(i2, str, bundle);
    }

    public static /* synthetic */ ExecuteAppFunctionResponse b(GenericDocument genericDocument, Bundle bundle) {
        return new ExecuteAppFunctionResponse(genericDocument, bundle);
    }

    public static /* synthetic */ EmbeddingVector c(float[] fArr, String str) {
        return new EmbeddingVector(fArr, str);
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: MethodInlineVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.MethodInlineVisitor.inlineMth(MethodInlineVisitor.java:57)
        	at jadx.core.dex.visitors.MethodInlineVisitor.visit(MethodInlineVisitor.java:47)
        */
    public static /* synthetic */ void j() {
        /*
            android.app.appfunctions.AppFunctionException r0 = new android.app.appfunctions.AppFunctionException
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: i.C0213b.j():void");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: MethodInlineVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.MethodInlineVisitor.inlineMth(MethodInlineVisitor.java:57)
        	at jadx.core.dex.visitors.MethodInlineVisitor.visit(MethodInlineVisitor.java:47)
        */
    public static /* synthetic */ void q() {
        /*
            android.app.appfunctions.ExecuteAppFunctionResponse r0 = new android.app.appfunctions.ExecuteAppFunctionResponse
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: i.C0213b.q():void");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: MethodInlineVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.MethodInlineVisitor.inlineMth(MethodInlineVisitor.java:57)
        	at jadx.core.dex.visitors.MethodInlineVisitor.visit(MethodInlineVisitor.java:47)
        */
    public static /* synthetic */ void r() {
        /*
            android.app.appsearch.EmbeddingVector r0 = new android.app.appsearch.EmbeddingVector
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: i.C0213b.r():void");
    }
}
