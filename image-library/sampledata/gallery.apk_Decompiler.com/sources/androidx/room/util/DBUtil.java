package androidx.room.util;

import B1.a;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a/\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\n\u001a\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000e\u001a\u0015\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/room/RoomDatabase;", "db", "Landroidx/sqlite/db/SupportSQLiteQuery;", "sqLiteQuery", "", "maybeCopy", "Landroid/os/CancellationSignal;", "signal", "Landroid/database/Cursor;", "query", "(Landroidx/room/RoomDatabase;Landroidx/sqlite/db/SupportSQLiteQuery;ZLandroid/os/CancellationSignal;)Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "Lme/x;", "dropFtsSyncTriggers", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "Ljava/io/File;", "databaseFile", "", "readVersion", "(Ljava/io/File;)I", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DBUtil {
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005b, code lost:
        B1.a.g(r1, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005e, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void dropFtsSyncTriggers(androidx.sqlite.db.SupportSQLiteDatabase r5) {
        /*
            java.lang.String r0 = "db"
            kotlin.jvm.internal.j.e(r5, r0)
            oe.c r0 = o1.C0246a.R()
            java.lang.String r1 = "SELECT name FROM sqlite_master WHERE type = 'trigger'"
            android.database.Cursor r1 = r5.query((java.lang.String) r1)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = r1
            android.database.Cursor r2 = (android.database.Cursor) r2     // Catch:{ all -> 0x0023 }
        L_0x0014:
            boolean r3 = r2.moveToNext()     // Catch:{ all -> 0x0023 }
            r4 = 0
            if (r3 == 0) goto L_0x0025
            java.lang.String r3 = r2.getString(r4)     // Catch:{ all -> 0x0023 }
            r0.add(r3)     // Catch:{ all -> 0x0023 }
            goto L_0x0014
        L_0x0023:
            r5 = move-exception
            goto L_0x0059
        L_0x0025:
            r2 = 0
            B1.a.g(r1, r2)
            oe.c r0 = o1.C0246a.K(r0)
            java.util.ListIterator r0 = r0.listIterator(r4)
        L_0x0031:
            r1 = r0
            oe.a r1 = (oe.C1212a) r1
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0058
            java.lang.Object r1 = r1.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "triggerName"
            kotlin.jvm.internal.j.d(r1, r2)
            java.lang.String r2 = "room_fts_content_sync_"
            boolean r2 = Tf.v.t0(r1, r2)
            if (r2 == 0) goto L_0x0031
            java.lang.String r2 = "DROP TRIGGER IF EXISTS "
            java.lang.String r1 = r2.concat(r1)
            r5.execSQL(r1)
            goto L_0x0031
        L_0x0058:
            return
        L_0x0059:
            throw r5     // Catch:{ all -> 0x005a }
        L_0x005a:
            r0 = move-exception
            B1.a.g(r1, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.DBUtil.dropFtsSyncTriggers(androidx.sqlite.db.SupportSQLiteDatabase):void");
    }

    public static final Cursor query(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, boolean z, CancellationSignal cancellationSignal) {
        int i2;
        j.e(roomDatabase, "db");
        j.e(supportSQLiteQuery, "sqLiteQuery");
        Cursor query = roomDatabase.query(supportSQLiteQuery, cancellationSignal);
        if (!z || !(query instanceof AbstractWindowedCursor)) {
            return query;
        }
        AbstractWindowedCursor abstractWindowedCursor = (AbstractWindowedCursor) query;
        int count = abstractWindowedCursor.getCount();
        if (abstractWindowedCursor.hasWindow()) {
            i2 = abstractWindowedCursor.getWindow().getNumRows();
        } else {
            i2 = count;
        }
        if (i2 < count) {
            return CursorUtil.copyAndClose(query);
        }
        return query;
    }

    public static final int readVersion(File file) {
        Throwable th;
        j.e(file, "databaseFile");
        FileChannel channel = new FileInputStream(file).getChannel();
        try {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            channel.tryLock(60, 4, true);
            channel.position(60);
            if (channel.read(allocate) == 4) {
                allocate.rewind();
                int i2 = allocate.getInt();
                a.g(channel, (Throwable) null);
                return i2;
            }
            throw new IOException("Bad database header, unable to read 4 bytes at offset 60");
        } catch (Throwable th2) {
            a.g(channel, th);
            throw th2;
        }
    }
}
