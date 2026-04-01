package androidx.room;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"androidx/room/InvalidationTracker$refreshRunnable$1", "Ljava/lang/Runnable;", "", "", "checkUpdatedTable", "()Ljava/util/Set;", "Lme/x;", "run", "()V", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class InvalidationTracker$refreshRunnable$1 implements Runnable {
    final /* synthetic */ InvalidationTracker this$0;

    public InvalidationTracker$refreshRunnable$1(InvalidationTracker invalidationTracker) {
        this.this$0 = invalidationTracker;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0065, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        B1.a.g(r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0069, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.Set<java.lang.Integer> checkUpdatedTable() {
        /*
            r5 = this;
            androidx.room.InvalidationTracker r0 = r5.this$0
            oe.i r1 = new oe.i
            r1.<init>()
            androidx.room.RoomDatabase r0 = r0.getDatabase$room_runtime_release()
            androidx.sqlite.db.SimpleSQLiteQuery r2 = new androidx.sqlite.db.SimpleSQLiteQuery
            java.lang.String r3 = "SELECT * FROM room_table_modification_log WHERE invalidated = 1;"
            r2.<init>(r3)
            r3 = 2
            r4 = 0
            android.database.Cursor r0 = androidx.room.RoomDatabase.query$default(r0, r2, r4, r3, r4)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = r0
            android.database.Cursor r2 = (android.database.Cursor) r2     // Catch:{ all -> 0x0030 }
        L_0x001d:
            boolean r3 = r2.moveToNext()     // Catch:{ all -> 0x0030 }
            if (r3 == 0) goto L_0x0032
            r3 = 0
            int r3 = r2.getInt(r3)     // Catch:{ all -> 0x0030 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0030 }
            r1.add(r3)     // Catch:{ all -> 0x0030 }
            goto L_0x001d
        L_0x0030:
            r5 = move-exception
            goto L_0x0064
        L_0x0032:
            B1.a.g(r0, r4)
            oe.i r0 = B1.a.d(r1)
            oe.f r1 = r0.d
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0063
            androidx.room.InvalidationTracker r1 = r5.this$0
            androidx.sqlite.db.SupportSQLiteStatement r1 = r1.getCleanupStatement$room_runtime_release()
            java.lang.String r2 = "Required value was null."
            if (r1 == 0) goto L_0x005d
            androidx.room.InvalidationTracker r5 = r5.this$0
            androidx.sqlite.db.SupportSQLiteStatement r5 = r5.getCleanupStatement$room_runtime_release()
            if (r5 == 0) goto L_0x0057
            r5.executeUpdateDelete()
            return r0
        L_0x0057:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            r5.<init>(r2)
            throw r5
        L_0x005d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            r5.<init>(r2)
            throw r5
        L_0x0063:
            return r0
        L_0x0064:
            throw r5     // Catch:{ all -> 0x0065 }
        L_0x0065:
            r1 = move-exception
            B1.a.g(r0, r5)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker$refreshRunnable$1.checkUpdatedTable():java.util.Set");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0080, code lost:
        if (r0 != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0082, code lost:
        r0.decrementCountAndScheduleClose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a3, code lost:
        if (r0 == null) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b8, code lost:
        if (r0 == null) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00bf, code lost:
        if (r2.isEmpty() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c1, code lost:
        r0 = r4.this$0.getObserverMap$room_runtime_release();
        r4 = r4.this$0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c9, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r4 = r4.getObserverMap$room_runtime_release().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d6, code lost:
        if (r4.hasNext() == false) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d8, code lost:
        ((androidx.room.InvalidationTracker.ObserverWrapper) ((java.util.Map.Entry) r4.next()).getValue()).notifyByTableInvalidStatus$room_runtime_release(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ea, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r4 = this;
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.RoomDatabase r0 = r0.getDatabase$room_runtime_release()
            java.util.concurrent.locks.Lock r0 = r0.getCloseLock$room_runtime_release()
            r0.lock()
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            boolean r1 = r1.ensureInitialization$room_runtime_release()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            if (r1 != 0) goto L_0x0024
            r0.unlock()
            androidx.room.InvalidationTracker r4 = r4.this$0
            androidx.room.AutoCloser r4 = r4.autoCloser
            if (r4 == 0) goto L_0x00ee
            r4.decrementCountAndScheduleClose()
            return
        L_0x0024:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            java.util.concurrent.atomic.AtomicBoolean r1 = r1.getPendingRefresh()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            r2 = 1
            r3 = 0
            boolean r1 = r1.compareAndSet(r2, r3)     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            if (r1 != 0) goto L_0x0041
            r0.unlock()
            androidx.room.InvalidationTracker r4 = r4.this$0
            androidx.room.AutoCloser r4 = r4.autoCloser
            if (r4 == 0) goto L_0x00ee
            r4.decrementCountAndScheduleClose()
            return
        L_0x0041:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            androidx.room.RoomDatabase r1 = r1.getDatabase$room_runtime_release()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            boolean r1 = r1.inTransaction()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            if (r1 == 0) goto L_0x005c
            r0.unlock()
            androidx.room.InvalidationTracker r4 = r4.this$0
            androidx.room.AutoCloser r4 = r4.autoCloser
            if (r4 == 0) goto L_0x00ee
            r4.decrementCountAndScheduleClose()
            return
        L_0x005c:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            androidx.room.RoomDatabase r1 = r1.getDatabase$room_runtime_release()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            androidx.sqlite.db.SupportSQLiteOpenHelper r1 = r1.getOpenHelper()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            androidx.sqlite.db.SupportSQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            r1.beginTransactionNonExclusive()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            java.util.Set r2 = r4.checkUpdatedTable()     // Catch:{ all -> 0x008c }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x008c }
            r1.endTransaction()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00bb
        L_0x0082:
            r0.decrementCountAndScheduleClose()
            goto L_0x00bb
        L_0x0086:
            r1 = move-exception
            goto L_0x00ef
        L_0x0088:
            r1 = move-exception
            goto L_0x0091
        L_0x008a:
            r1 = move-exception
            goto L_0x00a6
        L_0x008c:
            r2 = move-exception
            r1.endTransaction()     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
            throw r2     // Catch:{ IllegalStateException -> 0x008a, SQLiteException -> 0x0088 }
        L_0x0091:
            java.lang.String r2 = "ROOM"
            java.lang.String r3 = "Cannot run invalidation tracker. Is the db closed?"
            android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x0086 }
            ne.v r2 = ne.v.d     // Catch:{ all -> 0x0086 }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00bb
            goto L_0x0082
        L_0x00a6:
            java.lang.String r2 = "ROOM"
            java.lang.String r3 = "Cannot run invalidation tracker. Is the db closed?"
            android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x0086 }
            ne.v r2 = ne.v.d     // Catch:{ all -> 0x0086 }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00bb
            goto L_0x0082
        L_0x00bb:
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x00ee
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.arch.core.internal.SafeIterableMap r0 = r0.getObserverMap$room_runtime_release()
            androidx.room.InvalidationTracker r4 = r4.this$0
            monitor-enter(r0)
            androidx.arch.core.internal.SafeIterableMap r4 = r4.getObserverMap$room_runtime_release()     // Catch:{ all -> 0x00e8 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x00e8 }
        L_0x00d2:
            boolean r1 = r4.hasNext()     // Catch:{ all -> 0x00e8 }
            if (r1 == 0) goto L_0x00ea
            java.lang.Object r1 = r4.next()     // Catch:{ all -> 0x00e8 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x00e8 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x00e8 }
            androidx.room.InvalidationTracker$ObserverWrapper r1 = (androidx.room.InvalidationTracker.ObserverWrapper) r1     // Catch:{ all -> 0x00e8 }
            r1.notifyByTableInvalidStatus$room_runtime_release(r2)     // Catch:{ all -> 0x00e8 }
            goto L_0x00d2
        L_0x00e8:
            r4 = move-exception
            goto L_0x00ec
        L_0x00ea:
            monitor-exit(r0)
            goto L_0x00ee
        L_0x00ec:
            monitor-exit(r0)
            throw r4
        L_0x00ee:
            return
        L_0x00ef:
            r0.unlock()
            androidx.room.InvalidationTracker r4 = r4.this$0
            androidx.room.AutoCloser r4 = r4.autoCloser
            if (r4 == 0) goto L_0x00fd
            r4.decrementCountAndScheduleClose()
        L_0x00fd:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker$refreshRunnable$1.run():void");
    }
}
