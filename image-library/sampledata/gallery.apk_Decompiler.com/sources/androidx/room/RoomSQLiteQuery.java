package androidx.room;

import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u000b\b\u0007\u0018\u0000 >2\u00020\u00012\u00020\u0002:\u0001>B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0013\u0010\u0006J\u001f\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\nH\u0016¢\u0006\u0004\b \u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\f\n\u0004\b\u0004\u0010!\u001a\u0004\b\"\u0010#R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\b\u0010$R\u001a\u0010&\u001a\u00020%8\u0006X\u0004¢\u0006\f\n\u0004\b&\u0010'\u0012\u0004\b(\u0010\u000eR\u001a\u0010*\u001a\u00020)8\u0006X\u0004¢\u0006\f\n\u0004\b*\u0010+\u0012\u0004\b,\u0010\u000eR\"\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070-8\u0006X\u0004¢\u0006\f\n\u0004\b.\u0010/\u0012\u0004\b0\u0010\u000eR\"\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0-8\u0006X\u0004¢\u0006\f\n\u0004\b1\u00102\u0012\u0004\b3\u0010\u000eR\u001a\u00105\u001a\u0002048\u0002X\u0004¢\u0006\f\n\u0004\b5\u00106\u0012\u0004\b7\u0010\u000eR$\u00109\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00038\u0016@RX\u000e¢\u0006\f\n\u0004\b9\u0010!\u001a\u0004\b:\u0010#R\u0014\u0010=\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006?"}, d2 = {"Landroidx/room/RoomSQLiteQuery;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "Landroidx/sqlite/db/SupportSQLiteProgram;", "", "capacity", "<init>", "(I)V", "", "query", "initArgCount", "Lme/x;", "init", "(Ljava/lang/String;I)V", "release", "()V", "statement", "bindTo", "(Landroidx/sqlite/db/SupportSQLiteProgram;)V", "index", "bindNull", "", "value", "bindLong", "(IJ)V", "", "bindDouble", "(ID)V", "bindString", "(ILjava/lang/String;)V", "", "bindBlob", "(I[B)V", "close", "I", "getCapacity", "()I", "Ljava/lang/String;", "", "longBindings", "[J", "getLongBindings$annotations", "", "doubleBindings", "[D", "getDoubleBindings$annotations", "", "stringBindings", "[Ljava/lang/String;", "getStringBindings$annotations", "blobBindings", "[[B", "getBlobBindings$annotations", "", "bindingTypes", "[I", "getBindingTypes$annotations", "<set-?>", "argCount", "getArgCount", "getSql", "()Ljava/lang/String;", "sql", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RoomSQLiteQuery implements SupportSQLiteQuery, SupportSQLiteProgram {
    public static final Companion Companion = new Companion((e) null);
    public static final TreeMap<Integer, RoomSQLiteQuery> queryPool = new TreeMap<>();
    private int argCount;
    private final int[] bindingTypes;
    public final byte[][] blobBindings;
    private final int capacity;
    public final double[] doubleBindings;
    public final long[] longBindings;
    private volatile String query;
    public final String[] stringBindings;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\r\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\f\u0010\u0003R\u0014\u0010\u000e\u001a\u00020\u00068\u0002XT¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00068\u0002XT¢\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00068\u0002XT¢\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00068\u0002XT¢\u0006\u0006\n\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00068\u0002XT¢\u0006\u0006\n\u0004\b\u0013\u0010\u000f¨\u0006\u0014"}, d2 = {"Landroidx/room/RoomSQLiteQuery$Companion;", "", "<init>", "()V", "", "query", "", "argumentCount", "Landroidx/room/RoomSQLiteQuery;", "acquire", "(Ljava/lang/String;I)Landroidx/room/RoomSQLiteQuery;", "Lme/x;", "prunePoolLocked$room_runtime_release", "prunePoolLocked", "BLOB", "I", "DOUBLE", "LONG", "NULL", "STRING", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final RoomSQLiteQuery acquire(String str, int i2) {
            j.e(str, Contract.QUERY);
            TreeMap<Integer, RoomSQLiteQuery> treeMap = RoomSQLiteQuery.queryPool;
            synchronized (treeMap) {
                Map.Entry<Integer, RoomSQLiteQuery> ceilingEntry = treeMap.ceilingEntry(Integer.valueOf(i2));
                if (ceilingEntry != null) {
                    treeMap.remove(ceilingEntry.getKey());
                    RoomSQLiteQuery value = ceilingEntry.getValue();
                    value.init(str, i2);
                    return value;
                }
                RoomSQLiteQuery roomSQLiteQuery = new RoomSQLiteQuery(i2, (e) null);
                roomSQLiteQuery.init(str, i2);
                return roomSQLiteQuery;
            }
        }

        public final void prunePoolLocked$room_runtime_release() {
            TreeMap<Integer, RoomSQLiteQuery> treeMap = RoomSQLiteQuery.queryPool;
            if (treeMap.size() > 15) {
                int size = treeMap.size() - 10;
                Iterator<Integer> it = treeMap.descendingKeySet().iterator();
                j.d(it, "queryPool.descendingKeySet().iterator()");
                while (true) {
                    int i2 = size - 1;
                    if (size > 0) {
                        it.next();
                        it.remove();
                        size = i2;
                    } else {
                        return;
                    }
                }
            }
        }

        private Companion() {
        }
    }

    public /* synthetic */ RoomSQLiteQuery(int i2, e eVar) {
        this(i2);
    }

    public static final RoomSQLiteQuery acquire(String str, int i2) {
        return Companion.acquire(str, i2);
    }

    public void bindBlob(int i2, byte[] bArr) {
        j.e(bArr, "value");
        this.bindingTypes[i2] = 5;
        this.blobBindings[i2] = bArr;
    }

    public void bindDouble(int i2, double d) {
        this.bindingTypes[i2] = 3;
        this.doubleBindings[i2] = d;
    }

    public void bindLong(int i2, long j2) {
        this.bindingTypes[i2] = 2;
        this.longBindings[i2] = j2;
    }

    public void bindNull(int i2) {
        this.bindingTypes[i2] = 1;
    }

    public void bindString(int i2, String str) {
        j.e(str, "value");
        this.bindingTypes[i2] = 4;
        this.stringBindings[i2] = str;
    }

    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        j.e(supportSQLiteProgram, "statement");
        int argCount2 = getArgCount();
        if (1 <= argCount2) {
            int i2 = 1;
            while (true) {
                int i7 = this.bindingTypes[i2];
                if (i7 == 1) {
                    supportSQLiteProgram.bindNull(i2);
                } else if (i7 == 2) {
                    supportSQLiteProgram.bindLong(i2, this.longBindings[i2]);
                } else if (i7 == 3) {
                    supportSQLiteProgram.bindDouble(i2, this.doubleBindings[i2]);
                } else if (i7 == 4) {
                    String str = this.stringBindings[i2];
                    if (str != null) {
                        supportSQLiteProgram.bindString(i2, str);
                    } else {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                } else if (i7 == 5) {
                    byte[] bArr = this.blobBindings[i2];
                    if (bArr != null) {
                        supportSQLiteProgram.bindBlob(i2, bArr);
                    } else {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                }
                if (i2 != argCount2) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public int getArgCount() {
        return this.argCount;
    }

    public String getSql() {
        String str = this.query;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public final void init(String str, int i2) {
        j.e(str, Contract.QUERY);
        this.query = str;
        this.argCount = i2;
    }

    public final void release() {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = queryPool;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.capacity), this);
            Companion.prunePoolLocked$room_runtime_release();
        }
    }

    private RoomSQLiteQuery(int i2) {
        this.capacity = i2;
        int i7 = i2 + 1;
        this.bindingTypes = new int[i7];
        this.longBindings = new long[i7];
        this.doubleBindings = new double[i7];
        this.stringBindings = new String[i7];
        this.blobBindings = new byte[i7][];
    }

    public void close() {
    }
}
