package androidx.sqlite.db;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0007¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"androidx/sqlite/db/SupportSQLiteCompat$Api29Impl", "", "<init>", "()V", "Landroid/database/Cursor;", "cursor", "Landroid/content/ContentResolver;", "cr", "", "Landroid/net/Uri;", "uris", "Lme/x;", "setNotificationUris", "(Landroid/database/Cursor;Landroid/content/ContentResolver;Ljava/util/List;)V", "getNotificationUris", "(Landroid/database/Cursor;)Ljava/util/List;", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SupportSQLiteCompat$Api29Impl {
    public static final SupportSQLiteCompat$Api29Impl INSTANCE = new SupportSQLiteCompat$Api29Impl();

    private SupportSQLiteCompat$Api29Impl() {
    }

    public static final List<Uri> getNotificationUris(Cursor cursor) {
        j.e(cursor, "cursor");
        List<Uri> notificationUris = cursor.getNotificationUris();
        j.b(notificationUris);
        return notificationUris;
    }

    public static final void setNotificationUris(Cursor cursor, ContentResolver contentResolver, List<? extends Uri> list) {
        j.e(cursor, "cursor");
        j.e(contentResolver, "cr");
        j.e(list, "uris");
        cursor.setNotificationUris(contentResolver, list);
    }
}
