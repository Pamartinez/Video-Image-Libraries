package com.samsung.android.gallery.app.provider;

import E2.i;
import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.app.provider.GallerySearchProvider;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.lib.galaxyfinder.search.api.SamsungSearchProvider;
import com.sec.android.gallery3d.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import nc.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GallerySearchProvider2 extends SamsungSearchProvider {
    private static final int[] DURATION_FORMAT = {R.string.details_ms, R.string.details_hms};
    private final String TAG;
    final SearchProviderImpl mImpl = new SearchProviderImpl();

    public GallerySearchProvider2() {
        String makeTag = Trace.makeTag(this);
        this.TAG = makeTag;
        Trace.endSection();
        Log.d(makeTag, makeTag + "#construct");
    }

    private Intent createIntent(String str, boolean z, Uri uri, String str2) {
        String str3;
        if (z) {
            str3 = "com.android.gallery.action.SEARCH_VIEW";
        } else {
            str3 = "android.intent.action.VIEW";
        }
        Intent intent = new Intent(str3);
        if (z) {
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        }
        intent.addFlags(268435456);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(uri, str2);
        intent.putExtra("searchItem", str);
        return intent;
    }

    private String getContentDescription(MediaItem mediaItem) {
        int i2;
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(mediaItem.getTitle());
        sb2.append(ArcCommonLog.TAG_COMMA);
        Context context = getContext();
        if (mediaItem.isImage()) {
            i2 = R.string.image;
        } else {
            i2 = R.string.video;
        }
        sb2.append(context.getString(i2));
        if (mediaItem.isImage()) {
            str = " ";
        } else {
            str = TimeUtil.formatDurationForAccessibility(getContext(), mediaItem.getFileDuration(), DURATION_FORMAT);
        }
        sb2.append(str);
        return sb2.toString();
    }

    private Cursor getContentsCursor(String str, int i2) {
        Context context = getContext();
        if (context == null) {
            Log.e(this.TAG, "Couldn't get contents : Context is null");
            return null;
        }
        try {
            Cursor search = this.mImpl.createSearchEngine(context).search(new SearchFilter.Builder(str).setLimit(i2).filterOutCloudVideo().build(context));
            String str2 = this.TAG;
            Log.d(str2, "getContentsCursor : " + Logger.getCursorInfo(search), "");
            return search;
        } catch (Exception e) {
            j.v("Couldn't get contents.", e, this.TAG);
            return null;
        }
    }

    private boolean isGalaxyFinderUri(Uri uri) {
        if (!uri.getPath().isEmpty() || !uri.getQueryParameterNames().contains(Contract.QUERY)) {
            return false;
        }
        return true;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException(this.TAG + " delete not supported " + uri);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mImpl.dump(fileDescriptor, printWriter, strArr);
    }

    public a getSearchResult(String str, int i2, CancellationSignal cancellationSignal) {
        Cursor contentsCursor;
        Throwable th;
        String str2;
        kotlin.jvm.internal.j.e(str, Contract.QUERY);
        a aVar = new a(str);
        if (i2 == -1) {
            i2 = 200;
        }
        try {
            contentsCursor = getContentsCursor(str, i2);
            if (contentsCursor != null) {
                if (contentsCursor.moveToFirst()) {
                    int i7 = 0;
                    do {
                        MediaItem create = MediaItemBuilder.create(contentsCursor);
                        String str3 = create.getFileId() + "_" + create.getDateTaken();
                        Uri uri = ContentUri.getUri(create);
                        if (create.isVideo()) {
                            str2 = TimeUtil.getIsoLocalTime((long) create.getFileDuration());
                        } else {
                            str2 = null;
                        }
                        aVar.b.add(new oc.a(str3, uri, str2, getContentDescription(create), new i(createIntent(str3, create.isImage(), uri, create.getMimeType()))));
                        i7++;
                        if (!contentsCursor.moveToNext()) {
                            break;
                        }
                    } while (i7 < i2);
                }
            }
            if (contentsCursor != null) {
                contentsCursor.close();
            }
            return aVar;
        } catch (Exception e) {
            j.v("failed to getSearchResult().", e, this.TAG);
            return aVar;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public String getType(Uri uri) {
        if (isGalaxyFinderUri(uri)) {
            return null;
        }
        return this.mImpl.getType(uri);
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException(this.TAG + " insert not supported " + uri);
    }

    public Intent makeAppLaunchIntent() {
        return null;
    }

    public Intent makeInAppSearchIntent() {
        Intent intent = new Intent("com.android.gallery.action.SEARCH_FROM_GALAXY_FINDER_VIEW");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        return intent;
    }

    public boolean onCreate() {
        this.mImpl.attachInfo(getContext(), (ProviderInfo) null);
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (isGalaxyFinderUri(uri)) {
            return super.query(uri, strArr, str, strArr2, str2);
        }
        return this.mImpl.query(uri, strArr, str, strArr2, str2);
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException(this.TAG + " update not supported " + uri);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        if (isGalaxyFinderUri(uri)) {
            return super.query(uri, strArr, str, strArr2, str2, cancellationSignal);
        }
        return this.mImpl.query(uri, strArr, str, strArr2, str2);
    }

    public Cursor query(Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) {
        FileUtils.initializeIfAbsent(getContext());
        return super.query(uri, strArr, bundle, cancellationSignal);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SearchProviderImpl extends GallerySearchProvider {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class UriMatcherHolder2 {
            static final UriMatcher URI_MATCHER;

            static {
                UriMatcher uriMatcher = GallerySearchProvider.UriMatcherHolder.URI_MATCHER;
                URI_MATCHER = uriMatcher;
                uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider2", "search_suggest_regex_query", 4);
                uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider2", "search_suggest_regex_query/*", 4);
                uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider2", "search_suggest_tag_query", 6);
                uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider2", "search_suggest_tag_query/*", 6);
                uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider2", "search_suggest_history", 7);
                uriMatcher.addURI("com.sec.android.gallery3d.provider.GallerySearchProvider2", "search_suggest_history/*", 7);
            }
        }

        public int match(Uri uri) {
            return UriMatcherHolder2.URI_MATCHER.match(uri);
        }

        public void assertSystemPackage() {
        }
    }
}
