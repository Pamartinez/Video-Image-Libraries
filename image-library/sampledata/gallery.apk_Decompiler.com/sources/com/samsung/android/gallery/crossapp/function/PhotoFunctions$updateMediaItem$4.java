package com.samsung.android.gallery.crossapp.function;

import Ae.c;
import Vf.A;
import android.content.ContentValues;
import android.net.Uri;
import androidx.appfunctions.AppFunctionContext;
import com.google.android.appfunctions.schema.photos.v1.MediaItem;
import com.google.android.appfunctions.schema.photos.v1.UpdateMediaItemParams;
import java.util.List;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$4", f = "PhotoFunctions.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PhotoFunctions$updateMediaItem$4 extends i implements c {
    final /* synthetic */ AppFunctionContext $appFunctionContext;
    final /* synthetic */ ContentValues $cv;
    final /* synthetic */ List<MediaItem> $mediaItems;
    final /* synthetic */ Uri $mediaUri;
    final /* synthetic */ UpdateMediaItemParams $updateMediaItemParams;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PhotoFunctions$updateMediaItem$4(AppFunctionContext appFunctionContext, Uri uri, ContentValues contentValues, UpdateMediaItemParams updateMediaItemParams, List<MediaItem> list, C1227c cVar) {
        super(2, cVar);
        this.$appFunctionContext = appFunctionContext;
        this.$mediaUri = uri;
        this.$cv = contentValues;
        this.$updateMediaItemParams = updateMediaItemParams;
        this.$mediaItems = list;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new PhotoFunctions$updateMediaItem$4(this.$appFunctionContext, this.$mediaUri, this.$cv, this.$updateMediaItemParams, this.$mediaItems, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((PhotoFunctions$updateMediaItem$4) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0068, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        B1.a.g(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r0 = r4.label
            if (r0 != 0) goto L_0x0079
            L2.a.A(r5)
            androidx.appfunctions.AppFunctionContext r5 = r4.$appFunctionContext
            android.content.Context r5 = r5.getContext()
            android.content.ContentResolver r5 = r5.getContentResolver()
            android.net.Uri r0 = r4.$mediaUri
            android.content.ContentValues r1 = r4.$cv
            r2 = 0
            int r5 = r5.update(r0, r1, r2, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "updateMediaItem "
            r0.<init>(r1)
            r0.append(r5)
            java.lang.String r5 = " "
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = "CrossApp#Photo"
            com.samsung.android.gallery.support.utils.Log.d(r0, r5)
            com.samsung.android.gallery.database.dal.abstraction.query.QueryParams r5 = new com.samsung.android.gallery.database.dal.abstraction.query.QueryParams
            java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES
            r5.<init>((java.lang.String) r1)
            android.net.Uri r1 = r4.$mediaUri
            r5.addUri((android.net.Uri) r1)
            android.database.Cursor r5 = com.samsung.android.gallery.database.dal.DbCompat.query((com.samsung.android.gallery.database.dal.abstraction.query.QueryParams) r5)     // Catch:{ Exception -> 0x0065 }
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ Exception -> 0x0065 }
            java.util.List<com.google.android.appfunctions.schema.photos.v1.MediaItem> r4 = r4.$mediaItems     // Catch:{ Exception -> 0x0065 }
            r1 = r5
            android.database.Cursor r1 = (android.database.Cursor) r1     // Catch:{ all -> 0x005f }
            if (r1 == 0) goto L_0x0061
            boolean r3 = r1.moveToFirst()     // Catch:{ all -> 0x005f }
            if (r3 == 0) goto L_0x0061
            com.samsung.android.gallery.crossapp.function.CrossAppUtils$Companion r3 = com.samsung.android.gallery.crossapp.function.CrossAppUtils.Companion     // Catch:{ all -> 0x005f }
            com.google.android.appfunctions.schema.photos.v1.MediaItem r1 = r3.buildSecItem(r1)     // Catch:{ all -> 0x005f }
            if (r1 == 0) goto L_0x0061
            r4.add(r1)     // Catch:{ all -> 0x005f }
            goto L_0x0061
        L_0x005f:
            r4 = move-exception
            goto L_0x0067
        L_0x0061:
            B1.a.g(r5, r2)     // Catch:{ Exception -> 0x0065 }
            goto L_0x0076
        L_0x0065:
            r4 = move-exception
            goto L_0x006d
        L_0x0067:
            throw r4     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r1 = move-exception
            B1.a.g(r5, r4)     // Catch:{ Exception -> 0x0065 }
            throw r1     // Catch:{ Exception -> 0x0065 }
        L_0x006d:
            java.lang.String r4 = r4.getMessage()
            java.lang.String r5 = "updateMediaItem#get updated item failed."
            A.a.u(r5, r4, r0)
        L_0x0076:
            me.x r4 = me.x.f4917a
            return r4
        L_0x0079:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$4.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
