package androidx.sqlite.db;

import android.database.Cursor;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"androidx/sqlite/db/SupportSQLiteCompat$Api23Impl", "", "<init>", "()V", "Landroid/database/Cursor;", "cursor", "Landroid/os/Bundle;", "extras", "Lme/x;", "setExtras", "(Landroid/database/Cursor;Landroid/os/Bundle;)V", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SupportSQLiteCompat$Api23Impl {
    public static final SupportSQLiteCompat$Api23Impl INSTANCE = new SupportSQLiteCompat$Api23Impl();

    private SupportSQLiteCompat$Api23Impl() {
    }

    public static final void setExtras(Cursor cursor, Bundle bundle) {
        j.e(cursor, "cursor");
        j.e(bundle, "extras");
        cursor.setExtras(bundle);
    }
}
