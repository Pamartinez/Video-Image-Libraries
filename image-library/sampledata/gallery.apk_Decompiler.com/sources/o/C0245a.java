package o;

import android.app.appsearch.AppSearchManager;
import android.app.appsearch.AppSearchResult;
import android.app.appsearch.GetByDocumentIdRequest;
import android.app.appsearch.SearchResult;
import android.app.appsearch.SearchResults;

/* renamed from: o.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class C0245a {
    public static /* bridge */ /* synthetic */ AppSearchManager d(Object obj) {
        return (AppSearchManager) obj;
    }

    public static /* bridge */ /* synthetic */ AppSearchResult e(Object obj) {
        return (AppSearchResult) obj;
    }

    public static /* synthetic */ GetByDocumentIdRequest.Builder h(String str) {
        return new GetByDocumentIdRequest.Builder(str);
    }

    public static /* bridge */ /* synthetic */ SearchResult k(Object obj) {
        return (SearchResult) obj;
    }

    public static /* bridge */ /* synthetic */ SearchResults m(Object obj) {
        return (SearchResults) obj;
    }

    public static /* bridge */ /* synthetic */ Class n() {
        return AppSearchManager.class;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: MethodInlineVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.MethodInlineVisitor.inlineMth(MethodInlineVisitor.java:57)
        	at jadx.core.dex.visitors.MethodInlineVisitor.visit(MethodInlineVisitor.java:47)
        */
    public static /* synthetic */ void u() {
        /*
            android.app.appsearch.GetByDocumentIdRequest$Builder r0 = new android.app.appsearch.GetByDocumentIdRequest$Builder
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o.C0245a.u():void");
    }
}
