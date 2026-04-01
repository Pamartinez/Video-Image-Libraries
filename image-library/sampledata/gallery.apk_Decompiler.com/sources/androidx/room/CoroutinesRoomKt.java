package androidx.room;

import Vf.C0886x;
import Vf.D;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00008@X\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003¨\u0006\u0006"}, d2 = {"Landroidx/room/RoomDatabase;", "LVf/x;", "getQueryDispatcher", "(Landroidx/room/RoomDatabase;)LVf/x;", "getTransactionDispatcher", "transactionDispatcher", "room-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CoroutinesRoomKt {
    public static final C0886x getQueryDispatcher(RoomDatabase roomDatabase) {
        Map<String, Object> backingFieldMap = roomDatabase.getBackingFieldMap();
        Object obj = backingFieldMap.get("QueryDispatcher");
        if (obj == null) {
            obj = D.h(roomDatabase.getQueryExecutor());
            backingFieldMap.put("QueryDispatcher", obj);
        }
        return (C0886x) obj;
    }

    public static final C0886x getTransactionDispatcher(RoomDatabase roomDatabase) {
        Map<String, Object> backingFieldMap = roomDatabase.getBackingFieldMap();
        Object obj = backingFieldMap.get("TransactionDispatcher");
        if (obj == null) {
            obj = D.h(roomDatabase.getTransactionExecutor());
            backingFieldMap.put("TransactionDispatcher", obj);
        }
        return (C0886x) obj;
    }
}
