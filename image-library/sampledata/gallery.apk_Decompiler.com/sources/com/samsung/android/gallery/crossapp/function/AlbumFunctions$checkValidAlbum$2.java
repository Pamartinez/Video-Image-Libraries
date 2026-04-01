package com.samsung.android.gallery.crossapp.function;

import Ae.c;
import Vf.A;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.gallery.crossapp.function.AlbumFunctions$checkValidAlbum$2", f = "AlbumFunctions.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AlbumFunctions$checkValidAlbum$2 extends i implements c {
    final /* synthetic */ String $albumId;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlbumFunctions$checkValidAlbum$2(String str, C1227c cVar) {
        super(2, cVar);
        this.$albumId = str;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new AlbumFunctions$checkValidAlbum$2(this.$albumId, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((AlbumFunctions$checkValidAlbum$2) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0059, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005a, code lost:
        B1.a.g(r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005d, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.String r0 = "checkValidAlbum#query "
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r5.label
            if (r1 != 0) goto L_0x005e
            L2.a.A(r6)
            com.samsung.android.gallery.database.dal.local.LocalAlbumsApi r6 = new com.samsung.android.gallery.database.dal.local.LocalAlbumsApi
            r6.<init>()
            java.lang.String r5 = r5.$albumId
            int r5 = java.lang.Integer.parseInt(r5)
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r5)
            java.util.List r5 = o1.C0246a.e0(r1)
            r1 = 1
            android.database.Cursor r5 = r6.getAlbumCursor(r1, r5)
            java.io.Closeable r5 = (java.io.Closeable) r5
            r6 = r5
            android.database.Cursor r6 = (android.database.Cursor) r6     // Catch:{ all -> 0x0057 }
            java.lang.String r2 = "CrossApp#Album"
            java.lang.String r3 = com.samsung.android.gallery.support.utils.Logger.getCursorInfo(r6)     // Catch:{ all -> 0x0057 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0057 }
            r4.<init>(r0)     // Catch:{ all -> 0x0057 }
            r4.append(r3)     // Catch:{ all -> 0x0057 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0057 }
            java.lang.String r3 = ""
            java.lang.Object[] r3 = new java.lang.Object[]{r3}     // Catch:{ all -> 0x0057 }
            com.samsung.android.gallery.support.utils.Log.d(r2, r0, r3)     // Catch:{ all -> 0x0057 }
            boolean r6 = r6.moveToFirst()     // Catch:{ all -> 0x0057 }
            r0 = 0
            if (r6 == 0) goto L_0x0051
            B1.a.g(r5, r0)
            me.x r5 = me.x.f4917a
            return r5
        L_0x0051:
            androidx.appfunctions.AppFunctionElementNotFoundException r6 = new androidx.appfunctions.AppFunctionElementNotFoundException     // Catch:{ all -> 0x0057 }
            r6.<init>(r0, r1, r0)     // Catch:{ all -> 0x0057 }
            throw r6     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r0 = move-exception
            B1.a.g(r5, r6)
            throw r0
        L_0x005e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.AlbumFunctions$checkValidAlbum$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
