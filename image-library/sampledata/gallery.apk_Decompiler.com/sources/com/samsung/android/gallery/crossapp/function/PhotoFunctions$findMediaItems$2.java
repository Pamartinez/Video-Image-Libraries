package com.samsung.android.gallery.crossapp.function;

import Ae.c;
import L2.a;
import Vf.A;
import android.database.Cursor;
import androidx.appfunctions.AppFunctionContext;
import com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams;
import com.google.android.appfunctions.schema.photos.v1.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import java.io.Closeable;
import java.util.Map;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.gallery.crossapp.function.PhotoFunctions$findMediaItems$2", f = "PhotoFunctions.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PhotoFunctions$findMediaItems$2 extends i implements c {
    final /* synthetic */ AppFunctionContext $appFunctionContext;
    final /* synthetic */ FindMediaItemsParams $findMediaItemsParams;
    final /* synthetic */ Map<String, MediaItem> $mediaItems;
    int label;
    final /* synthetic */ PhotoFunctions this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PhotoFunctions$findMediaItems$2(FindMediaItemsParams findMediaItemsParams, PhotoFunctions photoFunctions, AppFunctionContext appFunctionContext, Map<String, MediaItem> map, C1227c cVar) {
        super(2, cVar);
        this.$findMediaItemsParams = findMediaItemsParams;
        this.this$0 = photoFunctions;
        this.$appFunctionContext = appFunctionContext;
        this.$mediaItems = map;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new PhotoFunctions$findMediaItems$2(this.$findMediaItemsParams, this.this$0, this.$appFunctionContext, this.$mediaItems, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((PhotoFunctions$findMediaItems$2) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            String str = this.$findMediaItemsParams.f1297c;
            Log.d("CrossApp#Photo", "findMediaItems " + str + "");
            Cursor access$queryContents = this.this$0.queryContents(this.$appFunctionContext.getContext(), this.$findMediaItemsParams);
            if (access$queryContents == null) {
                return null;
            }
            Closeable closeable = access$queryContents;
            Map<String, MediaItem> map = this.$mediaItems;
            try {
                Cursor cursor = (Cursor) closeable;
                if (cursor.moveToFirst()) {
                    do {
                        MediaItem buildSecItem = CrossAppUtils.Companion.buildSecItem(cursor);
                        if (buildSecItem != null) {
                            map.put(buildSecItem.f1298a, buildSecItem);
                        }
                    } while (cursor.moveToNext());
                }
                closeable.close();
                return x.f4917a;
            } catch (Throwable th) {
                B1.a.g(closeable, th);
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
