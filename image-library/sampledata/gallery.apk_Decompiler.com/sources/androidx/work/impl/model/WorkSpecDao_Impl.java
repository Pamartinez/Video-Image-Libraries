package androidx.work.impl.model;

import Yf.g;
import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.NetworkRequestCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkSpecDao_Impl implements WorkSpecDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<WorkSpec> __insertionAdapterOfWorkSpec;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfIncrementGeneration;
    private final SharedSQLiteStatement __preparedStmtOfIncrementPeriodCount;
    private final SharedSQLiteStatement __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfMarkWorkSpecScheduled;
    private final SharedSQLiteStatement __preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast;
    private final SharedSQLiteStatement __preparedStmtOfResetScheduledState;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecNextScheduleTimeOverride;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfSetCancelledState;
    private final SharedSQLiteStatement __preparedStmtOfSetLastEnqueueTime;
    private final SharedSQLiteStatement __preparedStmtOfSetNextScheduleTimeOverride;
    private final SharedSQLiteStatement __preparedStmtOfSetOutput;
    private final SharedSQLiteStatement __preparedStmtOfSetState;
    private final SharedSQLiteStatement __preparedStmtOfSetStopReason;
    private final EntityDeletionOrUpdateAdapter<WorkSpec> __updateAdapterOfWorkSpec;

    public WorkSpecDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkSpec = new EntityInsertionAdapter<WorkSpec>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`next_schedule_time_override`,`next_schedule_time_override_generation`,`stop_reason`,`trace_tag`,`required_network_type`,`required_network_request`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkSpec workSpec) {
                supportSQLiteStatement.bindString(1, workSpec.id);
                WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                supportSQLiteStatement.bindLong(2, (long) WorkTypeConverters.stateToInt(workSpec.state));
                supportSQLiteStatement.bindString(3, workSpec.workerClassName);
                supportSQLiteStatement.bindString(4, workSpec.inputMergerClassName);
                supportSQLiteStatement.bindBlob(5, Data.toByteArrayInternalV1(workSpec.input));
                supportSQLiteStatement.bindBlob(6, Data.toByteArrayInternalV1(workSpec.output));
                supportSQLiteStatement.bindLong(7, workSpec.initialDelay);
                supportSQLiteStatement.bindLong(8, workSpec.intervalDuration);
                supportSQLiteStatement.bindLong(9, workSpec.flexDuration);
                supportSQLiteStatement.bindLong(10, (long) workSpec.runAttemptCount);
                supportSQLiteStatement.bindLong(11, (long) WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                supportSQLiteStatement.bindLong(12, workSpec.backoffDelayDuration);
                supportSQLiteStatement.bindLong(13, workSpec.lastEnqueueTime);
                supportSQLiteStatement.bindLong(14, workSpec.minimumRetentionDuration);
                supportSQLiteStatement.bindLong(15, workSpec.scheduleRequestedAt);
                supportSQLiteStatement.bindLong(16, workSpec.expedited ? 1 : 0);
                supportSQLiteStatement.bindLong(17, (long) WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
                supportSQLiteStatement.bindLong(18, (long) workSpec.getPeriodCount());
                supportSQLiteStatement.bindLong(19, (long) workSpec.getGeneration());
                supportSQLiteStatement.bindLong(20, workSpec.getNextScheduleTimeOverride());
                supportSQLiteStatement.bindLong(21, (long) workSpec.getNextScheduleTimeOverrideGeneration());
                supportSQLiteStatement.bindLong(22, (long) workSpec.getStopReason());
                if (workSpec.getTraceTag() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, workSpec.getTraceTag());
                }
                Constraints constraints = workSpec.constraints;
                supportSQLiteStatement.bindLong(24, (long) WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                supportSQLiteStatement.bindBlob(25, WorkTypeConverters.fromNetworkRequest$work_runtime_release(constraints.getRequiredNetworkRequestCompat$work_runtime_release()));
                supportSQLiteStatement.bindLong(26, constraints.requiresCharging() ? 1 : 0);
                supportSQLiteStatement.bindLong(27, constraints.requiresDeviceIdle() ? 1 : 0);
                supportSQLiteStatement.bindLong(28, constraints.requiresBatteryNotLow() ? 1 : 0);
                supportSQLiteStatement.bindLong(29, constraints.requiresStorageNotLow() ? 1 : 0);
                supportSQLiteStatement.bindLong(30, constraints.getContentTriggerUpdateDelayMillis());
                supportSQLiteStatement.bindLong(31, constraints.getContentTriggerMaxDelayMillis());
                supportSQLiteStatement.bindBlob(32, WorkTypeConverters.setOfTriggersToByteArray(constraints.getContentUriTriggers()));
            }
        };
        this.__updateAdapterOfWorkSpec = new EntityDeletionOrUpdateAdapter<WorkSpec>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `WorkSpec` SET `id` = ?,`state` = ?,`worker_class_name` = ?,`input_merger_class_name` = ?,`input` = ?,`output` = ?,`initial_delay` = ?,`interval_duration` = ?,`flex_duration` = ?,`run_attempt_count` = ?,`backoff_policy` = ?,`backoff_delay_duration` = ?,`last_enqueue_time` = ?,`minimum_retention_duration` = ?,`schedule_requested_at` = ?,`run_in_foreground` = ?,`out_of_quota_policy` = ?,`period_count` = ?,`generation` = ?,`next_schedule_time_override` = ?,`next_schedule_time_override_generation` = ?,`stop_reason` = ?,`trace_tag` = ?,`required_network_type` = ?,`required_network_request` = ?,`requires_charging` = ?,`requires_device_idle` = ?,`requires_battery_not_low` = ?,`requires_storage_not_low` = ?,`trigger_content_update_delay` = ?,`trigger_max_content_delay` = ?,`content_uri_triggers` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkSpec workSpec) {
                supportSQLiteStatement.bindString(1, workSpec.id);
                WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                supportSQLiteStatement.bindLong(2, (long) WorkTypeConverters.stateToInt(workSpec.state));
                supportSQLiteStatement.bindString(3, workSpec.workerClassName);
                supportSQLiteStatement.bindString(4, workSpec.inputMergerClassName);
                supportSQLiteStatement.bindBlob(5, Data.toByteArrayInternalV1(workSpec.input));
                supportSQLiteStatement.bindBlob(6, Data.toByteArrayInternalV1(workSpec.output));
                supportSQLiteStatement.bindLong(7, workSpec.initialDelay);
                supportSQLiteStatement.bindLong(8, workSpec.intervalDuration);
                supportSQLiteStatement.bindLong(9, workSpec.flexDuration);
                supportSQLiteStatement.bindLong(10, (long) workSpec.runAttemptCount);
                supportSQLiteStatement.bindLong(11, (long) WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                supportSQLiteStatement.bindLong(12, workSpec.backoffDelayDuration);
                supportSQLiteStatement.bindLong(13, workSpec.lastEnqueueTime);
                supportSQLiteStatement.bindLong(14, workSpec.minimumRetentionDuration);
                supportSQLiteStatement.bindLong(15, workSpec.scheduleRequestedAt);
                supportSQLiteStatement.bindLong(16, workSpec.expedited ? 1 : 0);
                supportSQLiteStatement.bindLong(17, (long) WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
                supportSQLiteStatement.bindLong(18, (long) workSpec.getPeriodCount());
                supportSQLiteStatement.bindLong(19, (long) workSpec.getGeneration());
                supportSQLiteStatement.bindLong(20, workSpec.getNextScheduleTimeOverride());
                supportSQLiteStatement.bindLong(21, (long) workSpec.getNextScheduleTimeOverrideGeneration());
                supportSQLiteStatement.bindLong(22, (long) workSpec.getStopReason());
                if (workSpec.getTraceTag() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, workSpec.getTraceTag());
                }
                Constraints constraints = workSpec.constraints;
                supportSQLiteStatement.bindLong(24, (long) WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                supportSQLiteStatement.bindBlob(25, WorkTypeConverters.fromNetworkRequest$work_runtime_release(constraints.getRequiredNetworkRequestCompat$work_runtime_release()));
                supportSQLiteStatement.bindLong(26, constraints.requiresCharging() ? 1 : 0);
                supportSQLiteStatement.bindLong(27, constraints.requiresDeviceIdle() ? 1 : 0);
                supportSQLiteStatement.bindLong(28, constraints.requiresBatteryNotLow() ? 1 : 0);
                supportSQLiteStatement.bindLong(29, constraints.requiresStorageNotLow() ? 1 : 0);
                supportSQLiteStatement.bindLong(30, constraints.getContentTriggerUpdateDelayMillis());
                supportSQLiteStatement.bindLong(31, constraints.getContentTriggerMaxDelayMillis());
                supportSQLiteStatement.bindBlob(32, WorkTypeConverters.setOfTriggersToByteArray(constraints.getContentUriTriggers()));
                supportSQLiteStatement.bindString(33, workSpec.id);
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetState = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET state=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetCancelledState = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET stop_reason = CASE WHEN state=1 THEN 1 ELSE -256 END, state=5 WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementPeriodCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET period_count=period_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetLastEnqueueTime = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET last_enqueue_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetNextScheduleTimeOverride = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET next_schedule_time_override=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET next_schedule_time_override=9223372036854775807 WHERE (id=? AND next_schedule_time_override_generation=?)";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
        this.__preparedStmtOfIncrementGeneration = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET generation=generation+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetStopReason = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET stop_reason=? WHERE id=?";
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    public int countNonFinishedContentUriTriggerWorkers() {
        int i2 = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select COUNT(*) FROM workspec WHERE LENGTH(content_uri_triggers)<>0 AND state NOT IN (2, 3, 5)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                i2 = query.getInt(0);
            }
            return i2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public void delete(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
        acquire.bindString(1, str);
        try {
            this.__db.beginTransaction();
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(acquire);
        } catch (Throwable th) {
            this.__preparedStmtOfDelete.release(acquire);
            throw th;
        }
    }

    public List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z;
        String string;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?", 1);
        acquire.bindLong(1, (long) i2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override_generation");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "stop_reason");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "trace_tag");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "required_network_request");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i7 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.getString(columnIndexOrThrow);
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    String string3 = query.getString(columnIndexOrThrow3);
                    String string4 = query.getString(columnIndexOrThrow4);
                    Data fromByteArray = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                    Data fromByteArray2 = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                    long j2 = query.getLong(columnIndexOrThrow7);
                    long j3 = query.getLong(columnIndexOrThrow8);
                    long j8 = query.getLong(columnIndexOrThrow9);
                    int i8 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j10 = query.getLong(columnIndexOrThrow12);
                    long j11 = query.getLong(columnIndexOrThrow13);
                    int i10 = i7;
                    long j12 = query.getLong(i10);
                    int i11 = columnIndexOrThrow;
                    int i12 = columnIndexOrThrow15;
                    long j13 = query.getLong(i12);
                    columnIndexOrThrow15 = i12;
                    int i13 = columnIndexOrThrow16;
                    if (query.getInt(i13) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    columnIndexOrThrow16 = i13;
                    int i14 = columnIndexOrThrow17;
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i14));
                    columnIndexOrThrow17 = i14;
                    int i15 = columnIndexOrThrow18;
                    int i16 = query.getInt(i15);
                    columnIndexOrThrow18 = i15;
                    int i17 = columnIndexOrThrow19;
                    int i18 = query.getInt(i17);
                    columnIndexOrThrow19 = i17;
                    int i19 = columnIndexOrThrow20;
                    long j14 = query.getLong(i19);
                    columnIndexOrThrow20 = i19;
                    int i20 = columnIndexOrThrow21;
                    int i21 = query.getInt(i20);
                    columnIndexOrThrow21 = i20;
                    int i22 = columnIndexOrThrow22;
                    int i23 = query.getInt(i22);
                    columnIndexOrThrow22 = i22;
                    int i24 = columnIndexOrThrow23;
                    if (query.isNull(i24)) {
                        string = null;
                    } else {
                        string = query.getString(i24);
                    }
                    columnIndexOrThrow23 = i24;
                    int i25 = columnIndexOrThrow24;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i25));
                    columnIndexOrThrow24 = i25;
                    int i26 = columnIndexOrThrow25;
                    NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(query.getBlob(i26));
                    columnIndexOrThrow25 = i26;
                    int i27 = columnIndexOrThrow26;
                    if (query.getInt(i27) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    columnIndexOrThrow26 = i27;
                    int i28 = columnIndexOrThrow27;
                    if (query.getInt(i28) != 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    columnIndexOrThrow27 = i28;
                    int i29 = columnIndexOrThrow28;
                    if (query.getInt(i29) != 0) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    columnIndexOrThrow28 = i29;
                    int i30 = columnIndexOrThrow29;
                    if (query.getInt(i30) != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    columnIndexOrThrow29 = i30;
                    int i31 = columnIndexOrThrow30;
                    long j15 = query.getLong(i31);
                    columnIndexOrThrow30 = i31;
                    int i32 = columnIndexOrThrow31;
                    long j16 = query.getLong(i32);
                    columnIndexOrThrow31 = i32;
                    int i33 = columnIndexOrThrow32;
                    columnIndexOrThrow32 = i33;
                    arrayList.add(new WorkSpec(string2, intToState, string3, string4, fromByteArray, fromByteArray2, j2, j3, j8, new Constraints(networkRequest$work_runtime_release, intToNetworkType, z3, z7, z9, z10, j15, j16, WorkTypeConverters.byteArrayToSetOfTriggers(query.getBlob(i33))), i8, intToBackoffPolicy, j10, j11, j12, j13, z, intToOutOfQuotaPolicy, i16, i18, j14, i21, i23, string));
                    columnIndexOrThrow = i11;
                    i7 = i10;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<WorkSpec> getEligibleWorkForScheduling(int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z;
        String string;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND LENGTH(content_uri_triggers)=0 AND state NOT IN (2, 3, 5))", 1);
        acquire.bindLong(1, (long) i2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override_generation");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "stop_reason");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "trace_tag");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "required_network_request");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i7 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.getString(columnIndexOrThrow);
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    String string3 = query.getString(columnIndexOrThrow3);
                    String string4 = query.getString(columnIndexOrThrow4);
                    Data fromByteArray = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                    Data fromByteArray2 = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                    long j2 = query.getLong(columnIndexOrThrow7);
                    long j3 = query.getLong(columnIndexOrThrow8);
                    long j8 = query.getLong(columnIndexOrThrow9);
                    int i8 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j10 = query.getLong(columnIndexOrThrow12);
                    long j11 = query.getLong(columnIndexOrThrow13);
                    int i10 = i7;
                    long j12 = query.getLong(i10);
                    int i11 = columnIndexOrThrow;
                    int i12 = columnIndexOrThrow15;
                    long j13 = query.getLong(i12);
                    columnIndexOrThrow15 = i12;
                    int i13 = columnIndexOrThrow16;
                    if (query.getInt(i13) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    columnIndexOrThrow16 = i13;
                    int i14 = columnIndexOrThrow17;
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i14));
                    columnIndexOrThrow17 = i14;
                    int i15 = columnIndexOrThrow18;
                    int i16 = query.getInt(i15);
                    columnIndexOrThrow18 = i15;
                    int i17 = columnIndexOrThrow19;
                    int i18 = query.getInt(i17);
                    columnIndexOrThrow19 = i17;
                    int i19 = columnIndexOrThrow20;
                    long j14 = query.getLong(i19);
                    columnIndexOrThrow20 = i19;
                    int i20 = columnIndexOrThrow21;
                    int i21 = query.getInt(i20);
                    columnIndexOrThrow21 = i20;
                    int i22 = columnIndexOrThrow22;
                    int i23 = query.getInt(i22);
                    columnIndexOrThrow22 = i22;
                    int i24 = columnIndexOrThrow23;
                    if (query.isNull(i24)) {
                        string = null;
                    } else {
                        string = query.getString(i24);
                    }
                    columnIndexOrThrow23 = i24;
                    int i25 = columnIndexOrThrow24;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i25));
                    columnIndexOrThrow24 = i25;
                    int i26 = columnIndexOrThrow25;
                    NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(query.getBlob(i26));
                    columnIndexOrThrow25 = i26;
                    int i27 = columnIndexOrThrow26;
                    if (query.getInt(i27) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    columnIndexOrThrow26 = i27;
                    int i28 = columnIndexOrThrow27;
                    if (query.getInt(i28) != 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    columnIndexOrThrow27 = i28;
                    int i29 = columnIndexOrThrow28;
                    if (query.getInt(i29) != 0) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    columnIndexOrThrow28 = i29;
                    int i30 = columnIndexOrThrow29;
                    if (query.getInt(i30) != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    columnIndexOrThrow29 = i30;
                    int i31 = columnIndexOrThrow30;
                    long j15 = query.getLong(i31);
                    columnIndexOrThrow30 = i31;
                    int i32 = columnIndexOrThrow31;
                    long j16 = query.getLong(i32);
                    columnIndexOrThrow31 = i32;
                    int i33 = columnIndexOrThrow32;
                    columnIndexOrThrow32 = i33;
                    arrayList.add(new WorkSpec(string2, intToState, string3, string4, fromByteArray, fromByteArray2, j2, j3, j8, new Constraints(networkRequest$work_runtime_release, intToNetworkType, z3, z7, z9, z10, j15, j16, WorkTypeConverters.byteArrayToSetOfTriggers(query.getBlob(i33))), i8, intToBackoffPolicy, j10, j11, j12, j13, z, intToOutOfQuotaPolicy, i16, i18, j14, i21, i23, string));
                    columnIndexOrThrow = i11;
                    i7 = i10;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<WorkSpec> getEligibleWorkForSchedulingWithContentUris() {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z;
        String string;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 AND LENGTH(content_uri_triggers)<>0 ORDER BY last_enqueue_time", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override_generation");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "stop_reason");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "trace_tag");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "required_network_request");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.getString(columnIndexOrThrow);
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    String string3 = query.getString(columnIndexOrThrow3);
                    String string4 = query.getString(columnIndexOrThrow4);
                    Data fromByteArray = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                    Data fromByteArray2 = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                    long j2 = query.getLong(columnIndexOrThrow7);
                    long j3 = query.getLong(columnIndexOrThrow8);
                    long j8 = query.getLong(columnIndexOrThrow9);
                    int i7 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j10 = query.getLong(columnIndexOrThrow12);
                    long j11 = query.getLong(columnIndexOrThrow13);
                    int i8 = i2;
                    long j12 = query.getLong(i8);
                    int i10 = columnIndexOrThrow;
                    int i11 = columnIndexOrThrow15;
                    long j13 = query.getLong(i11);
                    columnIndexOrThrow15 = i11;
                    int i12 = columnIndexOrThrow16;
                    if (query.getInt(i12) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    columnIndexOrThrow16 = i12;
                    int i13 = columnIndexOrThrow17;
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i13));
                    columnIndexOrThrow17 = i13;
                    int i14 = columnIndexOrThrow18;
                    int i15 = query.getInt(i14);
                    columnIndexOrThrow18 = i14;
                    int i16 = columnIndexOrThrow19;
                    int i17 = query.getInt(i16);
                    columnIndexOrThrow19 = i16;
                    int i18 = columnIndexOrThrow20;
                    long j14 = query.getLong(i18);
                    columnIndexOrThrow20 = i18;
                    int i19 = columnIndexOrThrow21;
                    int i20 = query.getInt(i19);
                    columnIndexOrThrow21 = i19;
                    int i21 = columnIndexOrThrow22;
                    int i22 = query.getInt(i21);
                    columnIndexOrThrow22 = i21;
                    int i23 = columnIndexOrThrow23;
                    if (query.isNull(i23)) {
                        string = null;
                    } else {
                        string = query.getString(i23);
                    }
                    columnIndexOrThrow23 = i23;
                    int i24 = columnIndexOrThrow24;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i24));
                    columnIndexOrThrow24 = i24;
                    int i25 = columnIndexOrThrow25;
                    NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(query.getBlob(i25));
                    columnIndexOrThrow25 = i25;
                    int i26 = columnIndexOrThrow26;
                    if (query.getInt(i26) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    columnIndexOrThrow26 = i26;
                    int i27 = columnIndexOrThrow27;
                    if (query.getInt(i27) != 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    columnIndexOrThrow27 = i27;
                    int i28 = columnIndexOrThrow28;
                    if (query.getInt(i28) != 0) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    columnIndexOrThrow28 = i28;
                    int i29 = columnIndexOrThrow29;
                    if (query.getInt(i29) != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    columnIndexOrThrow29 = i29;
                    int i30 = columnIndexOrThrow30;
                    long j15 = query.getLong(i30);
                    columnIndexOrThrow30 = i30;
                    int i31 = columnIndexOrThrow31;
                    long j16 = query.getLong(i31);
                    columnIndexOrThrow31 = i31;
                    int i32 = columnIndexOrThrow32;
                    columnIndexOrThrow32 = i32;
                    arrayList.add(new WorkSpec(string2, intToState, string3, string4, fromByteArray, fromByteArray2, j2, j3, j8, new Constraints(networkRequest$work_runtime_release, intToNetworkType, z3, z7, z9, z10, j15, j16, WorkTypeConverters.byteArrayToSetOfTriggers(query.getBlob(i32))), i7, intToBackoffPolicy, j10, j11, j12, j13, z, intToOutOfQuotaPolicy, i15, i17, j14, i20, i22, string));
                    columnIndexOrThrow = i10;
                    i2 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<Data> getInputsFromPrerequisites(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(Data.fromByteArray(query.getBlob(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<WorkSpec> getRecentlyCompletedWork(long j2) {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z;
        String string;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC", 1);
        acquire.bindLong(1, j2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override_generation");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "stop_reason");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "trace_tag");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "required_network_request");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.getString(columnIndexOrThrow);
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    String string3 = query.getString(columnIndexOrThrow3);
                    String string4 = query.getString(columnIndexOrThrow4);
                    Data fromByteArray = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                    Data fromByteArray2 = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                    long j3 = query.getLong(columnIndexOrThrow7);
                    long j8 = query.getLong(columnIndexOrThrow8);
                    long j10 = query.getLong(columnIndexOrThrow9);
                    int i7 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j11 = query.getLong(columnIndexOrThrow12);
                    long j12 = query.getLong(columnIndexOrThrow13);
                    int i8 = i2;
                    long j13 = query.getLong(i8);
                    int i10 = columnIndexOrThrow;
                    int i11 = columnIndexOrThrow15;
                    long j14 = query.getLong(i11);
                    columnIndexOrThrow15 = i11;
                    int i12 = columnIndexOrThrow16;
                    if (query.getInt(i12) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    columnIndexOrThrow16 = i12;
                    int i13 = columnIndexOrThrow17;
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i13));
                    columnIndexOrThrow17 = i13;
                    int i14 = columnIndexOrThrow18;
                    int i15 = query.getInt(i14);
                    columnIndexOrThrow18 = i14;
                    int i16 = columnIndexOrThrow19;
                    int i17 = query.getInt(i16);
                    columnIndexOrThrow19 = i16;
                    int i18 = columnIndexOrThrow20;
                    long j15 = query.getLong(i18);
                    columnIndexOrThrow20 = i18;
                    int i19 = columnIndexOrThrow21;
                    int i20 = query.getInt(i19);
                    columnIndexOrThrow21 = i19;
                    int i21 = columnIndexOrThrow22;
                    int i22 = query.getInt(i21);
                    columnIndexOrThrow22 = i21;
                    int i23 = columnIndexOrThrow23;
                    if (query.isNull(i23)) {
                        string = null;
                    } else {
                        string = query.getString(i23);
                    }
                    columnIndexOrThrow23 = i23;
                    int i24 = columnIndexOrThrow24;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i24));
                    columnIndexOrThrow24 = i24;
                    int i25 = columnIndexOrThrow25;
                    NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(query.getBlob(i25));
                    columnIndexOrThrow25 = i25;
                    int i26 = columnIndexOrThrow26;
                    if (query.getInt(i26) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    columnIndexOrThrow26 = i26;
                    int i27 = columnIndexOrThrow27;
                    if (query.getInt(i27) != 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    columnIndexOrThrow27 = i27;
                    int i28 = columnIndexOrThrow28;
                    if (query.getInt(i28) != 0) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    columnIndexOrThrow28 = i28;
                    int i29 = columnIndexOrThrow29;
                    if (query.getInt(i29) != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    columnIndexOrThrow29 = i29;
                    int i30 = columnIndexOrThrow30;
                    long j16 = query.getLong(i30);
                    columnIndexOrThrow30 = i30;
                    int i31 = columnIndexOrThrow31;
                    long j17 = query.getLong(i31);
                    columnIndexOrThrow31 = i31;
                    int i32 = columnIndexOrThrow32;
                    columnIndexOrThrow32 = i32;
                    arrayList.add(new WorkSpec(string2, intToState, string3, string4, fromByteArray, fromByteArray2, j3, j8, j10, new Constraints(networkRequest$work_runtime_release, intToNetworkType, z3, z7, z9, z10, j16, j17, WorkTypeConverters.byteArrayToSetOfTriggers(query.getBlob(i32))), i7, intToBackoffPolicy, j11, j12, j13, j14, z, intToOutOfQuotaPolicy, i15, i17, j15, i20, i22, string));
                    columnIndexOrThrow = i10;
                    i2 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<WorkSpec> getRunningWork() {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z;
        String string;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override_generation");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "stop_reason");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "trace_tag");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "required_network_request");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.getString(columnIndexOrThrow);
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    String string3 = query.getString(columnIndexOrThrow3);
                    String string4 = query.getString(columnIndexOrThrow4);
                    Data fromByteArray = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                    Data fromByteArray2 = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                    long j2 = query.getLong(columnIndexOrThrow7);
                    long j3 = query.getLong(columnIndexOrThrow8);
                    long j8 = query.getLong(columnIndexOrThrow9);
                    int i7 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j10 = query.getLong(columnIndexOrThrow12);
                    long j11 = query.getLong(columnIndexOrThrow13);
                    int i8 = i2;
                    long j12 = query.getLong(i8);
                    int i10 = columnIndexOrThrow;
                    int i11 = columnIndexOrThrow15;
                    long j13 = query.getLong(i11);
                    columnIndexOrThrow15 = i11;
                    int i12 = columnIndexOrThrow16;
                    if (query.getInt(i12) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    columnIndexOrThrow16 = i12;
                    int i13 = columnIndexOrThrow17;
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i13));
                    columnIndexOrThrow17 = i13;
                    int i14 = columnIndexOrThrow18;
                    int i15 = query.getInt(i14);
                    columnIndexOrThrow18 = i14;
                    int i16 = columnIndexOrThrow19;
                    int i17 = query.getInt(i16);
                    columnIndexOrThrow19 = i16;
                    int i18 = columnIndexOrThrow20;
                    long j14 = query.getLong(i18);
                    columnIndexOrThrow20 = i18;
                    int i19 = columnIndexOrThrow21;
                    int i20 = query.getInt(i19);
                    columnIndexOrThrow21 = i19;
                    int i21 = columnIndexOrThrow22;
                    int i22 = query.getInt(i21);
                    columnIndexOrThrow22 = i21;
                    int i23 = columnIndexOrThrow23;
                    if (query.isNull(i23)) {
                        string = null;
                    } else {
                        string = query.getString(i23);
                    }
                    columnIndexOrThrow23 = i23;
                    int i24 = columnIndexOrThrow24;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i24));
                    columnIndexOrThrow24 = i24;
                    int i25 = columnIndexOrThrow25;
                    NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(query.getBlob(i25));
                    columnIndexOrThrow25 = i25;
                    int i26 = columnIndexOrThrow26;
                    if (query.getInt(i26) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    columnIndexOrThrow26 = i26;
                    int i27 = columnIndexOrThrow27;
                    if (query.getInt(i27) != 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    columnIndexOrThrow27 = i27;
                    int i28 = columnIndexOrThrow28;
                    if (query.getInt(i28) != 0) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    columnIndexOrThrow28 = i28;
                    int i29 = columnIndexOrThrow29;
                    if (query.getInt(i29) != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    columnIndexOrThrow29 = i29;
                    int i30 = columnIndexOrThrow30;
                    long j15 = query.getLong(i30);
                    columnIndexOrThrow30 = i30;
                    int i31 = columnIndexOrThrow31;
                    long j16 = query.getLong(i31);
                    columnIndexOrThrow31 = i31;
                    int i32 = columnIndexOrThrow32;
                    columnIndexOrThrow32 = i32;
                    arrayList.add(new WorkSpec(string2, intToState, string3, string4, fromByteArray, fromByteArray2, j2, j3, j8, new Constraints(networkRequest$work_runtime_release, intToNetworkType, z3, z7, z9, z10, j15, j16, WorkTypeConverters.byteArrayToSetOfTriggers(query.getBlob(i32))), i7, intToBackoffPolicy, j10, j11, j12, j13, z, intToOutOfQuotaPolicy, i15, i17, j14, i20, i22, string));
                    columnIndexOrThrow = i10;
                    i2 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<WorkSpec> getScheduledWork() {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z;
        String string;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override_generation");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "stop_reason");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "trace_tag");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "required_network_request");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    String string2 = query.getString(columnIndexOrThrow);
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    String string3 = query.getString(columnIndexOrThrow3);
                    String string4 = query.getString(columnIndexOrThrow4);
                    Data fromByteArray = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                    Data fromByteArray2 = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                    long j2 = query.getLong(columnIndexOrThrow7);
                    long j3 = query.getLong(columnIndexOrThrow8);
                    long j8 = query.getLong(columnIndexOrThrow9);
                    int i7 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j10 = query.getLong(columnIndexOrThrow12);
                    long j11 = query.getLong(columnIndexOrThrow13);
                    int i8 = i2;
                    long j12 = query.getLong(i8);
                    int i10 = columnIndexOrThrow;
                    int i11 = columnIndexOrThrow15;
                    long j13 = query.getLong(i11);
                    columnIndexOrThrow15 = i11;
                    int i12 = columnIndexOrThrow16;
                    if (query.getInt(i12) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    columnIndexOrThrow16 = i12;
                    int i13 = columnIndexOrThrow17;
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(i13));
                    columnIndexOrThrow17 = i13;
                    int i14 = columnIndexOrThrow18;
                    int i15 = query.getInt(i14);
                    columnIndexOrThrow18 = i14;
                    int i16 = columnIndexOrThrow19;
                    int i17 = query.getInt(i16);
                    columnIndexOrThrow19 = i16;
                    int i18 = columnIndexOrThrow20;
                    long j14 = query.getLong(i18);
                    columnIndexOrThrow20 = i18;
                    int i19 = columnIndexOrThrow21;
                    int i20 = query.getInt(i19);
                    columnIndexOrThrow21 = i19;
                    int i21 = columnIndexOrThrow22;
                    int i22 = query.getInt(i21);
                    columnIndexOrThrow22 = i21;
                    int i23 = columnIndexOrThrow23;
                    if (query.isNull(i23)) {
                        string = null;
                    } else {
                        string = query.getString(i23);
                    }
                    columnIndexOrThrow23 = i23;
                    int i24 = columnIndexOrThrow24;
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(i24));
                    columnIndexOrThrow24 = i24;
                    int i25 = columnIndexOrThrow25;
                    NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(query.getBlob(i25));
                    columnIndexOrThrow25 = i25;
                    int i26 = columnIndexOrThrow26;
                    if (query.getInt(i26) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    columnIndexOrThrow26 = i26;
                    int i27 = columnIndexOrThrow27;
                    if (query.getInt(i27) != 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    columnIndexOrThrow27 = i27;
                    int i28 = columnIndexOrThrow28;
                    if (query.getInt(i28) != 0) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    columnIndexOrThrow28 = i28;
                    int i29 = columnIndexOrThrow29;
                    if (query.getInt(i29) != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    columnIndexOrThrow29 = i29;
                    int i30 = columnIndexOrThrow30;
                    long j15 = query.getLong(i30);
                    columnIndexOrThrow30 = i30;
                    int i31 = columnIndexOrThrow31;
                    long j16 = query.getLong(i31);
                    columnIndexOrThrow31 = i31;
                    int i32 = columnIndexOrThrow32;
                    columnIndexOrThrow32 = i32;
                    arrayList.add(new WorkSpec(string2, intToState, string3, string4, fromByteArray, fromByteArray2, j2, j3, j8, new Constraints(networkRequest$work_runtime_release, intToNetworkType, z3, z7, z9, z10, j15, j16, WorkTypeConverters.byteArrayToSetOfTriggers(query.getBlob(i32))), i7, intToBackoffPolicy, j10, j11, j12, j13, z, intToOutOfQuotaPolicy, i15, i17, j14, i20, i22, string));
                    columnIndexOrThrow = i10;
                    i2 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public WorkInfo$State getState(String str) {
        Integer num;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        WorkInfo$State workInfo$State = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                if (query.isNull(0)) {
                    num = null;
                } else {
                    num = Integer.valueOf(query.getInt(0));
                }
                if (num != null) {
                    WorkTypeConverters workTypeConverters = WorkTypeConverters.INSTANCE;
                    workInfo$State = WorkTypeConverters.intToState(num.intValue());
                }
            }
            return workInfo$State;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<String> getUnfinishedWorkWithName(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<String> getUnfinishedWorkWithTag(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public WorkSpec getWorkSpec(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        WorkSpec workSpec;
        boolean z;
        String string;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE id=?", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "worker_class_name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "input_merger_class_name");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "input");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "initial_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "interval_duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "flex_duration");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "backoff_policy");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "backoff_delay_duration");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "last_enqueue_time");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "schedule_requested_at");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "run_in_foreground");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "out_of_quota_policy");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "period_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "generation");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "next_schedule_time_override_generation");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "stop_reason");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "trace_tag");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "required_network_type");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "required_network_request");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "requires_charging");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "requires_device_idle");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "requires_battery_not_low");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "requires_storage_not_low");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "trigger_content_update_delay");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "trigger_max_content_delay");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "content_uri_triggers");
                if (query.moveToFirst()) {
                    String string2 = query.getString(columnIndexOrThrow);
                    WorkInfo$State intToState = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                    String string3 = query.getString(columnIndexOrThrow3);
                    String string4 = query.getString(columnIndexOrThrow4);
                    Data fromByteArray = Data.fromByteArray(query.getBlob(columnIndexOrThrow5));
                    Data fromByteArray2 = Data.fromByteArray(query.getBlob(columnIndexOrThrow6));
                    long j2 = query.getLong(columnIndexOrThrow7);
                    long j3 = query.getLong(columnIndexOrThrow8);
                    long j8 = query.getLong(columnIndexOrThrow9);
                    int i2 = query.getInt(columnIndexOrThrow10);
                    BackoffPolicy intToBackoffPolicy = WorkTypeConverters.intToBackoffPolicy(query.getInt(columnIndexOrThrow11));
                    long j10 = query.getLong(columnIndexOrThrow12);
                    long j11 = query.getLong(columnIndexOrThrow13);
                    long j12 = query.getLong(columnIndexOrThrow14);
                    long j13 = query.getLong(columnIndexOrThrow15);
                    if (query.getInt(columnIndexOrThrow16) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    OutOfQuotaPolicy intToOutOfQuotaPolicy = WorkTypeConverters.intToOutOfQuotaPolicy(query.getInt(columnIndexOrThrow17));
                    int i7 = query.getInt(columnIndexOrThrow18);
                    int i8 = query.getInt(columnIndexOrThrow19);
                    long j14 = query.getLong(columnIndexOrThrow20);
                    int i10 = query.getInt(columnIndexOrThrow21);
                    int i11 = query.getInt(columnIndexOrThrow22);
                    int i12 = columnIndexOrThrow23;
                    if (query.isNull(i12)) {
                        string = null;
                    } else {
                        string = query.getString(i12);
                    }
                    NetworkType intToNetworkType = WorkTypeConverters.intToNetworkType(query.getInt(columnIndexOrThrow24));
                    NetworkRequestCompat networkRequest$work_runtime_release = WorkTypeConverters.toNetworkRequest$work_runtime_release(query.getBlob(columnIndexOrThrow25));
                    if (query.getInt(columnIndexOrThrow26) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (query.getInt(columnIndexOrThrow27) != 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    if (query.getInt(columnIndexOrThrow28) != 0) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    if (query.getInt(columnIndexOrThrow29) != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    workSpec = new WorkSpec(string2, intToState, string3, string4, fromByteArray, fromByteArray2, j2, j3, j8, new Constraints(networkRequest$work_runtime_release, intToNetworkType, z3, z7, z9, z10, query.getLong(columnIndexOrThrow30), query.getLong(columnIndexOrThrow31), WorkTypeConverters.byteArrayToSetOfTriggers(query.getBlob(columnIndexOrThrow32))), i2, intToBackoffPolicy, j10, j11, j12, j13, z, intToOutOfQuotaPolicy, i7, i8, j14, i10, i11, string);
                } else {
                    workSpec = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return workSpec;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(new WorkSpec.IdAndState(query.getString(0), WorkTypeConverters.intToState(query.getInt(1))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public g hasUnfinishedWorkFlow() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"workspec"}, new Callable<Boolean>() {
            public void finalize() {
                acquire.release();
            }

            public Boolean call() {
                Boolean bool;
                boolean z = false;
                Cursor query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    if (query.moveToFirst()) {
                        if (query.getInt(0) != 0) {
                            z = true;
                        }
                        bool = Boolean.valueOf(z);
                    } else {
                        bool = Boolean.FALSE;
                    }
                    return bool;
                } finally {
                    query.close();
                }
            }
        });
    }

    public void incrementPeriodCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfIncrementPeriodCount.acquire();
        acquire.bindString(1, str);
        try {
            this.__db.beginTransaction();
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementPeriodCount.release(acquire);
        } catch (Throwable th) {
            this.__preparedStmtOfIncrementPeriodCount.release(acquire);
            throw th;
        }
    }

    public int incrementWorkSpecRunAttemptCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        acquire.bindString(1, str);
        try {
            this.__db.beginTransaction();
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(acquire);
            return executeUpdateDelete;
        } catch (Throwable th) {
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(acquire);
            throw th;
        }
    }

    public void insertWorkSpec(WorkSpec workSpec) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkSpec.insert(workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public int markWorkSpecScheduled(String str, long j2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        acquire.bindLong(1, j2);
        acquire.bindString(2, str);
        try {
            this.__db.beginTransaction();
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfMarkWorkSpecScheduled.release(acquire);
            return executeUpdateDelete;
        } catch (Throwable th) {
            this.__preparedStmtOfMarkWorkSpecScheduled.release(acquire);
            throw th;
        }
    }

    public int resetScheduledState() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetScheduledState.acquire();
        try {
            this.__db.beginTransaction();
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfResetScheduledState.release(acquire);
            return executeUpdateDelete;
        } catch (Throwable th) {
            this.__preparedStmtOfResetScheduledState.release(acquire);
            throw th;
        }
    }

    public void resetWorkSpecNextScheduleTimeOverride(String str, int i2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride.acquire();
        acquire.bindString(1, str);
        acquire.bindLong(2, (long) i2);
        try {
            this.__db.beginTransaction();
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride.release(acquire);
        } catch (Throwable th) {
            this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride.release(acquire);
            throw th;
        }
    }

    public int resetWorkSpecRunAttemptCount(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        acquire.bindString(1, str);
        try {
            this.__db.beginTransaction();
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(acquire);
            return executeUpdateDelete;
        } catch (Throwable th) {
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(acquire);
            throw th;
        }
    }

    public int setCancelledState(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetCancelledState.acquire();
        acquire.bindString(1, str);
        try {
            this.__db.beginTransaction();
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfSetCancelledState.release(acquire);
            return executeUpdateDelete;
        } catch (Throwable th) {
            this.__preparedStmtOfSetCancelledState.release(acquire);
            throw th;
        }
    }

    public void setLastEnqueueTime(String str, long j2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetLastEnqueueTime.acquire();
        acquire.bindLong(1, j2);
        acquire.bindString(2, str);
        try {
            this.__db.beginTransaction();
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfSetLastEnqueueTime.release(acquire);
        } catch (Throwable th) {
            this.__preparedStmtOfSetLastEnqueueTime.release(acquire);
            throw th;
        }
    }

    public void setOutput(String str, Data data) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetOutput.acquire();
        acquire.bindBlob(1, Data.toByteArrayInternalV1(data));
        acquire.bindString(2, str);
        try {
            this.__db.beginTransaction();
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfSetOutput.release(acquire);
        } catch (Throwable th) {
            this.__preparedStmtOfSetOutput.release(acquire);
            throw th;
        }
    }

    public int setState(WorkInfo$State workInfo$State, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetState.acquire();
        acquire.bindLong(1, (long) WorkTypeConverters.stateToInt(workInfo$State));
        acquire.bindString(2, str);
        try {
            this.__db.beginTransaction();
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfSetState.release(acquire);
            return executeUpdateDelete;
        } catch (Throwable th) {
            this.__preparedStmtOfSetState.release(acquire);
            throw th;
        }
    }

    public void setStopReason(String str, int i2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetStopReason.acquire();
        acquire.bindLong(1, (long) i2);
        acquire.bindString(2, str);
        try {
            this.__db.beginTransaction();
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfSetStopReason.release(acquire);
        } catch (Throwable th) {
            this.__preparedStmtOfSetStopReason.release(acquire);
            throw th;
        }
    }

    public void updateWorkSpec(WorkSpec workSpec) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWorkSpec.handle(workSpec);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
