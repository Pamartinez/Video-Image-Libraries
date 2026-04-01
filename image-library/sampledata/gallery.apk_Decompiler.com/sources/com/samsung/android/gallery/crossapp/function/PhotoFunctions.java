package com.samsung.android.gallery.crossapp.function;

import A.a;
import Tf.v;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.text.TextUtils;
import androidx.appfunctions.AppFunctionContext;
import androidx.appfunctions.AppFunctionElementNotFoundException;
import androidx.appfunctions.AppFunctionInvalidArgumentException;
import com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import com.samsung.android.gallery.crossapp.R$string;
import com.samsung.android.gallery.crossapp.function.CrossAppUtils;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.BaseSearchEngine;
import com.samsung.android.gallery.module.search.engine.SearchEngineFactory;
import com.samsung.android.gallery.module.search.filter.SearchFilterUtil;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;
import qe.C1227c;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ,2\u00020\u00012\u00020\u00012\u00020\u00012\u00020\u0001:\u0001,B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\f\u0010\rJ+\u0010\u0012\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0019\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ&\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u0004H@¢\u0006\u0004\b\u001f\u0010 J \u0010\"\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u000eH@¢\u0006\u0004\b\"\u0010#J \u0010&\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020$H@¢\u0006\u0004\b&\u0010'J,\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u001d2\u0006\u0010\u001c\u001a\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001dH@¢\u0006\u0004\b*\u0010+¨\u0006-"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/PhotoFunctions;", "", "<init>", "()V", "Lcom/google/android/appfunctions/schema/photos/v1/FindMediaItemsParams;", "findMediaItemsParams", "", "isValidParams", "(Lcom/google/android/appfunctions/schema/photos/v1/FindMediaItemsParams;)Z", "Landroid/content/Context;", "context", "Landroid/database/Cursor;", "queryContents", "(Landroid/content/Context;Lcom/google/android/appfunctions/schema/photos/v1/FindMediaItemsParams;)Landroid/database/Cursor;", "", "oldSelectedFilter", "findMediaItemsParam", "intelligentSearchIndexField", "getSelectedFilter", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "Lcom/samsung/android/gallery/module/search/engine/BaseSearchEngine;", "createSearchEngine", "(Landroid/content/Context;)Lcom/samsung/android/gallery/module/search/engine/BaseSearchEngine;", "mediaItemId", "Landroid/content/Intent;", "loadMedia", "(Landroid/content/Context;Ljava/lang/String;)Landroid/content/Intent;", "Landroidx/appfunctions/AppFunctionContext;", "appFunctionContext", "", "Lcom/google/android/appfunctions/schema/photos/v1/MediaItem;", "findMediaItems", "(Landroidx/appfunctions/AppFunctionContext;Lcom/google/android/appfunctions/schema/photos/v1/FindMediaItemsParams;Lqe/c;)Ljava/lang/Object;", "Landroid/app/PendingIntent;", "showMediaItem", "(Landroidx/appfunctions/AppFunctionContext;Ljava/lang/String;Lqe/c;)Ljava/lang/Object;", "Lcom/google/android/appfunctions/schema/photos/v1/UpdateMediaItemParams;", "updateMediaItemParams", "updateMediaItem", "(Landroidx/appfunctions/AppFunctionContext;Lcom/google/android/appfunctions/schema/photos/v1/UpdateMediaItemParams;Lqe/c;)Ljava/lang/Object;", "mediaItemIds", "Landroid/net/Uri;", "getMediaItemContentUris", "(Landroidx/appfunctions/AppFunctionContext;Ljava/util/List;Lqe/c;)Ljava/lang/Object;", "Companion", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PhotoFunctions {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/PhotoFunctions$Companion;", "", "<init>", "()V", "TAG", "", "VIDEO_ACTIVITY", "GALLERY_ACTIVITY", "MAX_LIMIT", "", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    private final BaseSearchEngine createSearchEngine(Context context) {
        BaseSearchEngine create = SearchEngineFactory.create(context);
        create.clearCache();
        return create;
    }

    private final String getSelectedFilter(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        FilterResultsEntity filterResultsEntity = new FilterResultsEntity(str2, str3);
        filterResultsEntity.addRawLabel(str2);
        String addSelectedFilter = SearchFilterUtil.addSelectedFilter(str, filterResultsEntity);
        j.d(addSelectedFilter, "addSelectedFilter(...)");
        return addSelectedFilter;
    }

    private final boolean isValidParams(FindMediaItemsParams findMediaItemsParams) {
        DateTime dateTime = findMediaItemsParams.f1296a;
        return true;
    }

    private final Intent loadMedia(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        CrossAppUtils.Companion companion = CrossAppUtils.Companion;
        Uri buildUri = companion.buildUri(str);
        String mimeType = companion.getMimeType(context, buildUri);
        if (mimeType != null) {
            intent.setDataAndType(buildUri, mimeType);
            return intent;
        }
        throw new AppFunctionElementNotFoundException((String) null, 1, (e) null);
    }

    /* access modifiers changed from: private */
    public final Cursor queryContents(Context context, FindMediaItemsParams findMediaItemsParams) {
        String str;
        Long l;
        Long l8;
        if (context == null) {
            Log.e("CrossApp#Photo", "fineMediaItem#query skip. null context");
            return null;
        }
        if (!TextUtils.isEmpty(findMediaItemsParams.f1297c)) {
            str = findMediaItemsParams.f1297c;
        } else {
            str = context.getString(R$string.contents);
        }
        DateTime dateTime = findMediaItemsParams.f1296a;
        if (dateTime != null) {
            CrossAppUtils.Companion companion = CrossAppUtils.Companion;
            TimeZone timeZone = TimeZone.getDefault();
            j.d(timeZone, "getDefault(...)");
            l = Long.valueOf(companion.convertDateTimeToTimeInMillis(dateTime, timeZone));
        } else {
            l = null;
        }
        DateTime dateTime2 = findMediaItemsParams.b;
        if (dateTime2 != null) {
            CrossAppUtils.Companion companion2 = CrossAppUtils.Companion;
            TimeZone timeZone2 = TimeZone.getDefault();
            j.d(timeZone2, "getDefault(...)");
            l8 = Long.valueOf(companion2.convertDateTimeToTimeInMillis(dateTime2, timeZone2));
        } else {
            l8 = null;
        }
        String selectedFilter = getSelectedFilter(getSelectedFilter("", findMediaItemsParams.d, "facet_location"), findMediaItemsParams.f, "bucket_id");
        int i2 = findMediaItemsParams.g;
        int i7 = 30;
        if (i2 != 0) {
            if (i2 > 30) {
                i2 = 30;
            }
            i7 = i2;
        }
        SearchFilter build = new SearchFilter.Builder(str).setLimit(i7).selectedFilter(selectedFilter).filterOutCloudVideo().build(context);
        if (l != null && l8 != null && l.longValue() > 0 && l8.longValue() > 0) {
            build.setPeriod(new long[]{l.longValue(), l8.longValue()});
        }
        try {
            Cursor search = createSearchEngine(context).search(build);
            Log.d("CrossApp#Photo", "findMediaItems#query " + Logger.getCursorInfo(search), "");
            return search;
        } catch (Exception e) {
            a.u("findMediaItems#query failed.", e.getMessage(), "CrossApp#Photo");
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object findMediaItems(androidx.appfunctions.AppFunctionContext r11, com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams r12, qe.C1227c r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof com.samsung.android.gallery.crossapp.function.PhotoFunctions$findMediaItems$1
            if (r0 == 0) goto L_0x0013
            r0 = r13
            com.samsung.android.gallery.crossapp.function.PhotoFunctions$findMediaItems$1 r0 = (com.samsung.android.gallery.crossapp.function.PhotoFunctions$findMediaItems$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.gallery.crossapp.function.PhotoFunctions$findMediaItems$1 r0 = new com.samsung.android.gallery.crossapp.function.PhotoFunctions$findMediaItems$1
            r0.<init>(r10, r13)
        L_0x0018:
            java.lang.Object r13 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r10 = r0.L$1
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r0.L$0
            r12 = r11
            com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams r12 = (com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams) r12
            L2.a.A(r13)
            goto L_0x006f
        L_0x0030:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0038:
            L2.a.A(r13)
            if (r11 == 0) goto L_0x0087
            if (r12 == 0) goto L_0x0087
            boolean r13 = r10.isValidParams(r12)
            if (r13 == 0) goto L_0x0087
            com.samsung.android.gallery.crossapp.function.CrossAppUtils$Companion r13 = com.samsung.android.gallery.crossapp.function.CrossAppUtils.Companion
            android.content.Context r2 = r11.getContext()
            r13.checkMediaAccessPermission(r2)
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap
            r8.<init>()
            eg.f r13 = Vf.M.f3843a
            eg.e r13 = eg.e.d
            com.samsung.android.gallery.crossapp.function.PhotoFunctions$findMediaItems$2 r4 = new com.samsung.android.gallery.crossapp.function.PhotoFunctions$findMediaItems$2
            r9 = 0
            r6 = r10
            r7 = r11
            r5 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            r0.L$0 = r5
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r10 = Vf.D.w(r13, r4, r0)
            if (r10 != r1) goto L_0x006d
            return r1
        L_0x006d:
            r12 = r5
            r10 = r8
        L_0x006f:
            java.util.Collection r11 = r10.values()
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            int r13 = r10.size()
            int r12 = r12.g
            if (r13 < r12) goto L_0x007e
            goto L_0x0082
        L_0x007e:
            int r12 = r10.size()
        L_0x0082:
            java.util.List r10 = ne.C1194l.h1(r11, r12)
            return r10
        L_0x0087:
            androidx.appfunctions.AppFunctionInvalidArgumentException r10 = new androidx.appfunctions.AppFunctionInvalidArgumentException
            r11 = 0
            r10.<init>(r11, r3, r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.PhotoFunctions.findMediaItems(androidx.appfunctions.AppFunctionContext, com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams, qe.c):java.lang.Object");
    }

    public Object getMediaItemContentUris(AppFunctionContext appFunctionContext, List<String> list, C1227c cVar) {
        if (appFunctionContext == null || list == null) {
            throw new AppFunctionInvalidArgumentException((String) null, 1, (e) null);
        }
        Log.d("CrossApp#Photo", "getMediaItemContentUris " + list);
        Iterable<String> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (String buildUri : iterable) {
            arrayList.add(CrossAppUtils.Companion.buildUri(buildUri));
        }
        return arrayList;
    }

    public Object showMediaItem(AppFunctionContext appFunctionContext, String str, C1227c cVar) {
        Log.d("CrossApp#Photo", "showMediaItem " + str);
        Intent loadMedia = loadMedia(appFunctionContext.getContext(), str);
        String type = loadMedia.getType();
        if (type == null || !v.t0(type, "video/")) {
            loadMedia.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        } else {
            loadMedia.setClassName("com.samsung.android.video", "com.samsung.android.video.player.activity.MoviePlayer");
        }
        loadMedia.addFlags(268435457);
        PendingIntent activity = PendingIntent.getActivity(appFunctionContext.getContext(), 0, loadMedia, 67108864);
        j.d(activity, "getActivity(...)");
        return activity;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updateMediaItem(androidx.appfunctions.AppFunctionContext r12, com.google.android.appfunctions.schema.photos.v1.UpdateMediaItemParams r13, qe.C1227c r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$1
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$1 r0 = (com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$1 r0 = new com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$1
            r0.<init>(r11, r14)
        L_0x0018:
            java.lang.Object r11 = r0.result
            re.a r14 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L_0x0034
            if (r1 != r2) goto L_0x002c
            java.lang.Object r12 = r0.L$0
            java.util.List r12 = (java.util.List) r12
            L2.a.A(r11)
            goto L_0x00a6
        L_0x002c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0034:
            L2.a.A(r11)
            com.google.android.appfunctions.schema.types.v1.SetField r11 = r13.b
            java.lang.String r1 = r13.f1305a
            com.google.android.appfunctions.schema.types.v1.SetField r3 = r13.d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "updateMediaItem "
            r4.<init>(r5)
            r4.append(r11)
            java.lang.String r11 = " "
            r4.append(r11)
            r4.append(r3)
            r4.append(r11)
            r4.append(r1)
            java.lang.String r11 = r4.toString()
            java.lang.String r3 = "CrossApp#Photo"
            com.samsung.android.gallery.support.utils.Log.d(r3, r11)
            android.content.ContentValues r7 = new android.content.ContentValues
            r7.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            com.google.android.appfunctions.schema.types.v1.SetField r11 = r13.f1306c
            if (r11 == 0) goto L_0x0075
            java.lang.Object r11 = r11.f1328a
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            java.lang.String r3 = "is_favorite"
            r7.put(r3, r11)
        L_0x0075:
            com.google.android.appfunctions.schema.types.v1.SetField r11 = r13.b
            if (r11 == 0) goto L_0x0082
            java.lang.Object r11 = r11.f1328a
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r3 = "_display_name"
            r7.put(r3, r11)
        L_0x0082:
            com.samsung.android.gallery.crossapp.function.CrossAppUtils$Companion r11 = com.samsung.android.gallery.crossapp.function.CrossAppUtils.Companion
            android.net.Uri r6 = r11.buildUri(r1)
            int r11 = r7.size()
            if (r11 <= 0) goto L_0x00a7
            eg.f r11 = Vf.M.f3843a
            eg.e r11 = eg.e.d
            com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$4 r4 = new com.samsung.android.gallery.crossapp.function.PhotoFunctions$updateMediaItem$4
            r10 = 0
            r5 = r12
            r8 = r13
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r0.L$0 = r9
            r0.label = r2
            java.lang.Object r11 = Vf.D.w(r11, r4, r0)
            if (r11 != r14) goto L_0x00a5
            return r14
        L_0x00a5:
            r12 = r9
        L_0x00a6:
            r9 = r12
        L_0x00a7:
            int r11 = r9.size()
            if (r11 == 0) goto L_0x00b3
            r11 = 0
            java.lang.Object r11 = r9.get(r11)
            return r11
        L_0x00b3:
            androidx.appfunctions.AppFunctionAppUnknownException r11 = new androidx.appfunctions.AppFunctionAppUnknownException
            r12 = 0
            r11.<init>(r12, r2, r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.PhotoFunctions.updateMediaItem(androidx.appfunctions.AppFunctionContext, com.google.android.appfunctions.schema.photos.v1.UpdateMediaItemParams, qe.c):java.lang.Object");
    }
}
