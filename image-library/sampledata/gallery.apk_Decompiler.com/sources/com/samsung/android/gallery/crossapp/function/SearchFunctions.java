package com.samsung.android.gallery.crossapp.function;

import A.a;
import android.app.KeyguardManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import androidx.appfunctions.AppFunctionAppUnknownException;
import androidx.appfunctions.AppFunctionContext;
import androidx.appfunctions.AppFunctionPermissionRequiredException;
import androidx.appfunctions.AppFunctionUriGrant;
import androidx.core.content.ContextCompat;
import com.samsung.android.gallery.crossapp.R$string;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.engine.BaseSearchEngine;
import com.samsung.android.gallery.module.search.engine.SearchEngineFactory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import p1.C0253a;
import p1.C0254b;
import p1.C0255c;
import p1.C0256d;
import p1.C0257e;
import p1.C0258f;
import p1.C0259g;
import qe.C1227c;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 .2\u00020\u0001:\u0001.B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H@¢\u0006\u0004\b\t\u0010\nJ-\u0010\u0013\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ!\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010\u001d2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\"\u0010#J\u0019\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0004\b'\u0010(J,\u0010,\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u001e2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*H@¢\u0006\u0004\b,\u0010-¨\u0006/"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/SearchFunctions;", "", "<init>", "()V", "Landroidx/appfunctions/AppFunctionContext;", "appFunctionContext", "Lcom/samsung/android/gallery/crossapp/function/FindContentsParams;", "findContentsParams", "Lcom/samsung/android/gallery/crossapp/function/FindPhotosResponse;", "findPhotosInternal", "(Landroidx/appfunctions/AppFunctionContext;Lcom/samsung/android/gallery/crossapp/function/FindContentsParams;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/gallery/module/data/MediaItem;", "galleryItem", "", "Lcom/samsung/android/gallery/crossapp/function/MediaItem;", "mediaItems", "Lp1/a;", "collectionBuilder", "Lme/x;", "composeMediaItem", "(Lcom/samsung/android/gallery/module/data/MediaItem;Ljava/util/List;Lp1/a;)V", "", "isValidParams", "(Lcom/samsung/android/gallery/crossapp/function/FindContentsParams;)Z", "Landroid/content/Context;", "context", "Landroid/database/Cursor;", "queryContents", "(Landroid/content/Context;Lcom/samsung/android/gallery/crossapp/function/FindContentsParams;)Landroid/database/Cursor;", "", "", "getCountryInfo", "(Lcom/samsung/android/gallery/crossapp/function/FindContentsParams;)[Ljava/lang/String;", "Lcom/samsung/android/gallery/module/search/engine/BaseSearchEngine;", "createSearchEngine", "(Landroid/content/Context;)Lcom/samsung/android/gallery/module/search/engine/BaseSearchEngine;", "Ljava/time/LocalDateTime;", "time", "", "convertLocalDateTimeToLong", "(Ljava/time/LocalDateTime;)J", "queryString", "", "maxCount", "findPhotos", "(Landroidx/appfunctions/AppFunctionContext;Ljava/lang/String;Ljava/lang/Integer;Lqe/c;)Ljava/lang/Object;", "Companion", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchFunctions {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/SearchFunctions$Companion;", "", "<init>", "()V", "TAG", "", "MAX_LIMIT", "", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    public final void composeMediaItem(MediaItem mediaItem, List<MediaItem> list, C0253a aVar) {
        C0256d dVar;
        Uri uri = ContentUri.getUri(mediaItem);
        j.b(uri);
        MediaItem mediaItem2 = new MediaItem("", new AppFunctionUriGrant(uri, 65));
        list.add(mediaItem2);
        C0255c i2 = C0259g.i();
        if (mediaItem.isVideo()) {
            dVar = C0256d.VIDEO;
        } else {
            dVar = C0256d.IMAGE;
        }
        i2.c();
        ((C0259g) i2.e).j(dVar);
        String uri2 = mediaItem2.getUri().getUri().toString();
        i2.c();
        ((C0259g) i2.e).k(uri2);
        if (mediaItem.isVideo()) {
            C0257e i7 = C0258f.i();
            long fileDuration = (long) mediaItem.getFileDuration();
            i7.c();
            ((C0258f) i7.e).j(fileDuration);
            i2.c();
            ((C0259g) i2.e).l((C0258f) i7.a());
        }
        aVar.c();
        ((C0254b) aVar.e).j((C0259g) i2.a());
    }

    private final long convertLocalDateTimeToLong(LocalDateTime localDateTime) {
        ZonedDateTime atZone;
        Instant instant;
        if (localDateTime == null || (atZone = localDateTime.atZone(ZoneId.systemDefault())) == null || (instant = atZone.toInstant()) == null) {
            return 0;
        }
        return instant.toEpochMilli();
    }

    private final BaseSearchEngine createSearchEngine(Context context) {
        BaseSearchEngine create = SearchEngineFactory.create(context);
        create.clearCache();
        return create;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0091 A[Catch:{ IOException -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0092 A[Catch:{ IOException -> 0x00be }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b6 A[SYNTHETIC, Splitter:B:29:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object findPhotosInternal(androidx.appfunctions.AppFunctionContext r12, com.samsung.android.gallery.crossapp.function.FindContentsParams r13, qe.C1227c r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$1
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$1 r0 = (com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$1 r0 = new com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$1
            r0.<init>(r11, r14)
        L_0x0018:
            java.lang.Object r14 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r11 = r0.L$1
            p1.a r11 = (p1.C0253a) r11
            java.lang.Object r12 = r0.L$0
            java.util.List r12 = (java.util.List) r12
            L2.a.A(r14)
            goto L_0x006a
        L_0x002f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0037:
            L2.a.A(r14)
            boolean r14 = r11.isValidParams(r13)
            if (r14 == 0) goto L_0x00df
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            p1.a r9 = p1.C0254b.i()
            java.lang.String r14 = "newBuilder(...)"
            kotlin.jvm.internal.j.d(r9, r14)
            eg.f r14 = Vf.M.f3843a
            eg.e r14 = eg.e.d
            com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$2 r4 = new com.samsung.android.gallery.crossapp.function.SearchFunctions$findPhotosInternal$2
            r10 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r3
            java.lang.Object r11 = Vf.D.w(r14, r4, r0)
            if (r11 != r1) goto L_0x0068
            return r1
        L_0x0068:
            r12 = r8
            r11 = r9
        L_0x006a:
            com.samsung.android.gallery.crossapp.function.FindPhotosResponse r13 = new com.samsung.android.gallery.crossapp.function.FindPhotosResponse
            com.google.android.appfunctions.schema.internal.dependencies.l r11 = r11.a()
            p1.b r11 = (p1.C0254b) r11
            androidx.appfunctions.AppFunctionTextResource r14 = new androidx.appfunctions.AppFunctionTextResource
            r0 = 0
            int r0 = r11.a(r0)     // Catch:{ IOException -> 0x00be }
            byte[] r1 = new byte[r0]     // Catch:{ IOException -> 0x00be }
            com.google.android.appfunctions.schema.internal.dependencies.h0 r2 = new com.google.android.appfunctions.schema.internal.dependencies.h0     // Catch:{ IOException -> 0x00be }
            r2.<init>(r1, r0)     // Catch:{ IOException -> 0x00be }
            r11.getClass()     // Catch:{ IOException -> 0x00be }
            com.google.android.appfunctions.schema.internal.dependencies.J r3 = com.google.android.appfunctions.schema.internal.dependencies.J.f1206c     // Catch:{ IOException -> 0x00be }
            java.lang.Class r4 = r11.getClass()     // Catch:{ IOException -> 0x00be }
            com.google.android.appfunctions.schema.internal.dependencies.M r3 = r3.a(r4)     // Catch:{ IOException -> 0x00be }
            com.google.android.appfunctions.schema.internal.dependencies.y r4 = r2.d     // Catch:{ IOException -> 0x00be }
            if (r4 == 0) goto L_0x0092
            goto L_0x0097
        L_0x0092:
            com.google.android.appfunctions.schema.internal.dependencies.y r4 = new com.google.android.appfunctions.schema.internal.dependencies.y     // Catch:{ IOException -> 0x00be }
            r4.<init>(r2)     // Catch:{ IOException -> 0x00be }
        L_0x0097:
            r3.a(r11, r4)     // Catch:{ IOException -> 0x00be }
            int r11 = r2.g     // Catch:{ IOException -> 0x00be }
            int r0 = r0 - r11
            if (r0 != 0) goto L_0x00b6
            r11 = 0
            java.lang.String r11 = android.util.Base64.encodeToString(r1, r11)
            java.lang.String r0 = "encodeToString(...)"
            kotlin.jvm.internal.j.d(r11, r0)
            java.lang.String r0 = "proto/com.google.gemini.ui.preview.protos.MediaCollection"
            r14.<init>(r0, r11)
            java.util.List r11 = o1.C0246a.e0(r14)
            r13.<init>(r12, r11)
            return r13
        L_0x00b6:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ IOException -> 0x00be }
            java.lang.String r12 = "Did not write as much data as expected."
            r11.<init>(r12)     // Catch:{ IOException -> 0x00be }
            throw r11     // Catch:{ IOException -> 0x00be }
        L_0x00be:
            r0 = move-exception
            r11 = r0
            java.lang.RuntimeException r12 = new java.lang.RuntimeException
            java.lang.Class<p1.b> r13 = p1.C0254b.class
            java.lang.String r13 = r13.getName()
            int r14 = r13.length()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r14 = r14 + 72
            r0.<init>(r14)
            java.lang.String r14 = "Serializing "
            java.lang.String r1 = " to a byte array threw an IOException (should never happen)."
            java.lang.String r13 = N2.j.f(r0, r14, r13, r1)
            r12.<init>(r13, r11)
            throw r12
        L_0x00df:
            androidx.appfunctions.AppFunctionInvalidArgumentException r11 = new androidx.appfunctions.AppFunctionInvalidArgumentException
            java.lang.String r12 = "invalid queryString parameter"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.crossapp.function.SearchFunctions.findPhotosInternal(androidx.appfunctions.AppFunctionContext, com.samsung.android.gallery.crossapp.function.FindContentsParams, qe.c):java.lang.Object");
    }

    private final String[] getCountryInfo(FindContentsParams findContentsParams) {
        if (findContentsParams.getCountry() == null || findContentsParams.getCountryCode() == null) {
            return null;
        }
        return new String[]{findContentsParams.getCountry(), findContentsParams.getCountryCode()};
    }

    private final boolean isValidParams(FindContentsParams findContentsParams) {
        if (!TextUtils.isEmpty(findContentsParams.getQueryString()) || findContentsParams.getStartTime() != null || findContentsParams.getEndTime() != null || findContentsParams.getCity() != null) {
            return true;
        }
        if ((findContentsParams.getCountry() == null || findContentsParams.getCountryCode() == null) && findContentsParams.getOrderType() == null && findContentsParams.getMaxCount() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final Cursor queryContents(Context context, FindContentsParams findContentsParams) {
        String str;
        int intValue;
        if (context == null) {
            Log.e("CrossApp#Search", "#query skip. null context");
            return null;
        }
        Integer maxCount = findContentsParams.getMaxCount();
        int i2 = 50;
        if (maxCount != null && (intValue = maxCount.intValue()) <= 50) {
            i2 = intValue;
        }
        if (!TextUtils.isEmpty(findContentsParams.getQueryString())) {
            str = findContentsParams.getQueryString();
        } else {
            str = context.getString(R$string.contents);
            j.d(str, "getString(...)");
        }
        String str2 = "oldest";
        if (!str2.equals(findContentsParams.getOrderType())) {
            str2 = "latest";
        }
        SearchFilter build = new SearchFilter.Builder(str).setLimit(i2).countryInfo(getCountryInfo(findContentsParams)).filterOutCloudVideo().order(str2).build(context);
        long convertLocalDateTimeToLong = convertLocalDateTimeToLong(findContentsParams.getStartTime());
        long convertLocalDateTimeToLong2 = convertLocalDateTimeToLong(findContentsParams.getEndTime());
        if (convertLocalDateTimeToLong > 0 && convertLocalDateTimeToLong2 > 0) {
            build.setPeriod(new long[]{convertLocalDateTimeToLong, convertLocalDateTimeToLong2});
        }
        Log.d("CrossApp#Search", "#query : " + findContentsParams + ", limit=" + i2);
        try {
            Cursor search = createSearchEngine(context).search(build);
            String cursorInfo = Logger.getCursorInfo(search);
            Log.d("CrossApp#Search", "#query " + cursorInfo);
            return search;
        } catch (Exception e) {
            a.u("#query failed.", e.getMessage(), "CrossApp#Search");
            return null;
        }
    }

    public final Object findPhotos(AppFunctionContext appFunctionContext, String str, Integer num, C1227c cVar) {
        KeyguardManager keyguardManager = (KeyguardManager) ContextCompat.getSystemService(appFunctionContext.getContext(), KeyguardManager.class);
        if (keyguardManager != null && keyguardManager.isKeyguardLocked()) {
            throw new AppFunctionAppUnknownException("This function is unavailable while the device is locked.");
        } else if (RuntimePermissionUtil.hasPermission(appFunctionContext.getContext(), RuntimePermissionUtil.DEFAULT_PERMISSION_GROUP)) {
            return findPhotosInternal(appFunctionContext, new FindContentsParams(str, (LocalDateTime) null, (LocalDateTime) null, (String) null, (String) null, (String) null, (String) null, num), cVar);
        } else {
            throw new AppFunctionPermissionRequiredException("There have no Image/Video access permission.");
        }
    }
}
