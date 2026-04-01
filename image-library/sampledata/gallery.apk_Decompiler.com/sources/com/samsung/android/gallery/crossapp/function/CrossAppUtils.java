package com.samsung.android.gallery.crossapp.function;

import B1.a;
import android.content.Context;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.appfunctions.AppFunctionPermissionRequiredException;
import com.google.android.appfunctions.schema.photos.v1.MediaItem;
import com.google.android.appfunctions.schema.types.v1.Date;
import com.google.android.appfunctions.schema.types.v1.DateTime;
import com.google.android.appfunctions.schema.types.v1.TimeOfDay;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/CrossAppUtils;", "", "Companion", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CrossAppUtils {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static final Uri MediaStoreUri;

    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u0004¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010 \u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0018¢\u0006\u0004\b \u0010!J\u0015\u0010#\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0018¢\u0006\u0004\b#\u0010$J\u001f\u0010%\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b%\u0010&J\u001f\u0010(\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u001c2\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b(\u0010)J\u001f\u0010+\u001a\u00020*2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b+\u0010,J\u001f\u0010.\u001a\u00020-2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b.\u0010/J\u0015\u00101\u001a\u0002002\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b1\u00102R\u0014\u00103\u001a\u00020\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0006XT¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\u00188\u0002XT¢\u0006\u0006\n\u0004\b:\u00109¨\u0006;"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/CrossAppUtils$Companion;", "", "<init>", "()V", "", "time", "Landroid/icu/util/TimeZone;", "timeZone", "Landroid/icu/util/Calendar;", "getCalendar", "(JLandroid/icu/util/TimeZone;)Landroid/icu/util/Calendar;", "Landroid/database/Cursor;", "cursor", "Lcom/google/android/appfunctions/schema/photos/v1/MediaItem;", "buildSecItem", "(Landroid/database/Cursor;)Lcom/google/android/appfunctions/schema/photos/v1/MediaItem;", "Lcom/samsung/android/gallery/module/data/MediaItem;", "item", "buildSecItemWith", "(Lcom/samsung/android/gallery/module/data/MediaItem;)Lcom/google/android/appfunctions/schema/photos/v1/MediaItem;", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "uri", "", "getMimeType", "(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;", "dateTaken", "Lcom/google/android/appfunctions/schema/types/v1/DateTime;", "buildDateTime", "(J)Lcom/google/android/appfunctions/schema/types/v1/DateTime;", "mediaItemType", "buildDummyItem", "(Ljava/lang/String;)Lcom/google/android/appfunctions/schema/photos/v1/MediaItem;", "mediaItemId", "buildUri", "(Ljava/lang/String;)Landroid/net/Uri;", "convertTimeInMillisToDateTime", "(JLandroid/icu/util/TimeZone;)Lcom/google/android/appfunctions/schema/types/v1/DateTime;", "dateTime", "convertDateTimeToTimeInMillis", "(Lcom/google/android/appfunctions/schema/types/v1/DateTime;Landroid/icu/util/TimeZone;)J", "Lcom/google/android/appfunctions/schema/types/v1/Date;", "convertTimeInMillisToDate", "(JLandroid/icu/util/TimeZone;)Lcom/google/android/appfunctions/schema/types/v1/Date;", "Lcom/google/android/appfunctions/schema/types/v1/TimeOfDay;", "convertTimeInMillisToTimeOfDay", "(JLandroid/icu/util/TimeZone;)Lcom/google/android/appfunctions/schema/types/v1/TimeOfDay;", "Lme/x;", "checkMediaAccessPermission", "(Landroid/content/Context;)V", "MediaStoreUri", "Landroid/net/Uri;", "", "USE_MEDIA_ID_AS_URI", "Z", "LOCAL_STORAGE", "Ljava/lang/String;", "CLOUD_STORAGE", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public static /* synthetic */ DateTime convertTimeInMillisToDateTime$default(Companion companion, long j2, TimeZone timeZone, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                timeZone = TimeZone.getDefault();
            }
            return companion.convertTimeInMillisToDateTime(j2, timeZone);
        }

        private final Calendar getCalendar(long j2, TimeZone timeZone) {
            Calendar instance = Calendar.getInstance(timeZone);
            instance.setTimeInMillis(j2);
            return instance;
        }

        public final DateTime buildDateTime(long j2) {
            return convertTimeInMillisToDateTime$default(this, j2, (TimeZone) null, 2, (Object) null);
        }

        public final MediaItem buildDummyItem(String str) {
            j.e(str, "mediaItemType");
            String uri = CrossAppUtils.MediaStoreUri.buildUpon().appendPath("0").build().toString();
            j.b(uri);
            return new MediaItem(uri, str, buildDateTime(System.currentTimeMillis()), "broken", Boolean.FALSE, IdentityApiContract.Parameter.DEVICE, "0");
        }

        public final MediaItem buildSecItem(Cursor cursor) {
            j.e(cursor, "cursor");
            com.samsung.android.gallery.module.data.MediaItem create = MediaItemBuilder.create(cursor);
            j.b(create);
            return buildSecItemWith(create);
        }

        public final MediaItem buildSecItemWith(com.samsung.android.gallery.module.data.MediaItem mediaItem) {
            String str;
            j.e(mediaItem, "item");
            String uriString = ContentUri.getUriString(mediaItem);
            String str2 = "PHOTO";
            if (uriString == null) {
                return buildDummyItem(str2);
            }
            if (!mediaItem.isImage()) {
                str2 = "VIDEO";
            }
            String str3 = str2;
            DateTime buildDateTime = CrossAppUtils.Companion.buildDateTime(mediaItem.getDateTaken());
            String title = mediaItem.getTitle();
            Boolean valueOf = Boolean.valueOf(mediaItem.isFavourite());
            if (mediaItem.isLocal()) {
                str = IdentityApiContract.Parameter.DEVICE;
            } else {
                str = "cloud";
            }
            return new MediaItem(uriString, str3, buildDateTime, title, valueOf, str, String.valueOf(mediaItem.getBucketID()));
        }

        public final Uri buildUri(String str) {
            j.e(str, "mediaItemId");
            Uri parse = Uri.parse(str);
            j.d(parse, "parse(...)");
            return parse;
        }

        public final void checkMediaAccessPermission(Context context) {
            j.e(context, "context");
            if (!RuntimePermissionUtil.hasPermission(context, RuntimePermissionUtil.DEFAULT_PERMISSION_GROUP)) {
                throw new AppFunctionPermissionRequiredException("There have no Image/Video access permission.");
            }
        }

        public final long convertDateTimeToTimeInMillis(DateTime dateTime, TimeZone timeZone) {
            j.e(dateTime, "dateTime");
            j.e(timeZone, "timeZone");
            Calendar instance = Calendar.getInstance(timeZone);
            Date date = dateTime.b;
            int i2 = date.f1325c;
            TimeOfDay timeOfDay = dateTime.f1327c;
            instance.set(date.f1324a, date.b - 1, i2, timeOfDay.f1329a, timeOfDay.b, timeOfDay.f1330c);
            return instance.getTimeInMillis();
        }

        public final Date convertTimeInMillisToDate(long j2, TimeZone timeZone) {
            j.e(timeZone, "timeZone");
            Calendar calendar = getCalendar(j2, timeZone);
            return new Date(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
        }

        public final DateTime convertTimeInMillisToDateTime(long j2, TimeZone timeZone) {
            j.e(timeZone, "timeZone");
            String displayName = timeZone.getDisplayName();
            j.d(displayName, "getDisplayName(...)");
            return new DateTime(displayName, convertTimeInMillisToDate(j2, timeZone), convertTimeInMillisToTimeOfDay(j2, timeZone));
        }

        public final TimeOfDay convertTimeInMillisToTimeOfDay(long j2, TimeZone timeZone) {
            j.e(timeZone, "timeZone");
            Calendar calendar = getCalendar(j2, timeZone);
            return new TimeOfDay(calendar.get(11), calendar.get(12), calendar.get(13), calendar.get(14));
        }

        public final String getMimeType(Context context, Uri uri) {
            Uri uri2;
            Throwable th;
            j.e(context, "context");
            j.e(uri, OCRServiceConstant.KEY_PARAM_URI);
            try {
                uri2 = uri;
                try {
                    Cursor query = context.getContentResolver().query(uri2, (String[]) null, (String) null, (String[]) null, (String) null);
                    if (query == null) {
                        return null;
                    }
                    Closeable closeable = query;
                    try {
                        Cursor cursor = (Cursor) closeable;
                        if (cursor.moveToFirst()) {
                            String string = cursor.getString(cursor.getColumnIndex("mime_type"));
                            closeable.close();
                            return string;
                        }
                        closeable.close();
                        return null;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        a.g(closeable, th);
                        throw th3;
                    }
                } catch (Exception e) {
                    e = e;
                    String message = e.getMessage();
                    Log.e("CrossApp#Photo", "getMimeType#{" + uri2 + "} query failed." + message);
                    return null;
                }
            } catch (Exception e7) {
                e = e7;
                uri2 = uri;
                String message2 = e.getMessage();
                Log.e("CrossApp#Photo", "getMimeType#{" + uri2 + "} query failed." + message2);
                return null;
            }
        }

        private Companion() {
        }
    }

    static {
        Uri contentUri = MediaStore.Files.getContentUri("external");
        j.d(contentUri, "getContentUri(...)");
        MediaStoreUri = contentUri;
    }
}
