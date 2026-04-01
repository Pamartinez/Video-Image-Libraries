package com.samsung.android.gallery.crossapp.function;

import Ae.c;
import Vf.A;
import androidx.appfunctions.AppFunctionContext;
import java.util.List;
import kotlin.Metadata;
import me.x;
import p1.C0253a;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$2", f = "SearchFunctions.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchFunctions$findPhotosInternal$2 extends i implements c {
    final /* synthetic */ AppFunctionContext $appFunctionContext;
    final /* synthetic */ FindContentsParams $findContentsParams;
    final /* synthetic */ C0253a $mediaCollectionBuilder;
    final /* synthetic */ List<MediaItem> $mediaItems;
    int label;
    final /* synthetic */ SearchFunctions this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SearchFunctions$findPhotosInternal$2(SearchFunctions searchFunctions, AppFunctionContext appFunctionContext, FindContentsParams findContentsParams, List<MediaItem> list, C0253a aVar, C1227c cVar) {
        super(2, cVar);
        this.this$0 = searchFunctions;
        this.$appFunctionContext = appFunctionContext;
        this.$findContentsParams = findContentsParams;
        this.$mediaItems = list;
        this.$mediaCollectionBuilder = aVar;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new SearchFunctions$findPhotosInternal$2(this.this$0, this.$appFunctionContext, this.$findContentsParams, this.$mediaItems, this.$mediaCollectionBuilder, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((SearchFunctions$findPhotosInternal$2) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005c, code lost:
        B1.a.g(r8, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.String r0 = "#query result."
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r7.label
            if (r1 != 0) goto L_0x0062
            L2.a.A(r8)
            com.samsung.android.gallery.crossapp.function.SearchFunctions r8 = r7.this$0
            androidx.appfunctions.AppFunctionContext r1 = r7.$appFunctionContext
            android.content.Context r1 = r1.getContext()
            com.samsung.android.gallery.crossapp.function.FindContentsParams r2 = r7.$findContentsParams
            android.database.Cursor r8 = r8.queryContents(r1, r2)
            if (r8 == 0) goto L_0x0060
            java.io.Closeable r8 = (java.io.Closeable) r8
            com.samsung.android.gallery.crossapp.function.SearchFunctions r1 = r7.this$0
            java.util.List<com.samsung.android.gallery.crossapp.function.MediaItem> r2 = r7.$mediaItems
            p1.a r7 = r7.$mediaCollectionBuilder
            r3 = r8
            android.database.Cursor r3 = (android.database.Cursor) r3     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = "CrossApp#Search"
            int r5 = r3.getCount()     // Catch:{ all -> 0x0052 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r6.<init>(r0)     // Catch:{ all -> 0x0052 }
            r6.append(r5)     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x0052 }
            com.samsung.android.gallery.support.utils.Log.e(r4, r0)     // Catch:{ all -> 0x0052 }
            boolean r0 = r3.moveToFirst()     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0054
        L_0x0041:
            com.samsung.android.gallery.module.data.MediaItem r0 = com.samsung.android.gallery.module.data.MediaItemBuilder.create((android.database.Cursor) r3)     // Catch:{ all -> 0x0052 }
            kotlin.jvm.internal.j.b(r0)     // Catch:{ all -> 0x0052 }
            r1.composeMediaItem(r0, r2, r7)     // Catch:{ all -> 0x0052 }
            boolean r0 = r3.moveToNext()     // Catch:{ all -> 0x0052 }
            if (r0 != 0) goto L_0x0041
            goto L_0x0054
        L_0x0052:
            r7 = move-exception
            goto L_0x005a
        L_0x0054:
            r8.close()
            me.x r7 = me.x.f4917a
            return r7
        L_0x005a:
            throw r7     // Catch:{ all -> 0x005b }
        L_0x005b:
            r0 = move-exception
            B1.a.g(r8, r7)
            throw r0
        L_0x0060:
            r7 = 0
            return r7
        L_0x0062:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
