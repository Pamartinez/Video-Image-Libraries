package androidx.work.impl.model;

import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\"\u001a\u00020!2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0007¢\u0006\u0004\b\"\u0010#J\u001d\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010$\u001a\u00020!H\u0007¢\u0006\u0004\b%\u0010&J\u0017\u0010*\u001a\u00020'2\u0006\u0010$\u001a\u00020!H\u0001¢\u0006\u0004\b(\u0010)J\u0017\u0010.\u001a\u00020!2\u0006\u0010+\u001a\u00020'H\u0001¢\u0006\u0004\b,\u0010-¨\u0006/"}, d2 = {"Landroidx/work/impl/model/WorkTypeConverters;", "", "<init>", "()V", "Landroidx/work/WorkInfo$State;", "state", "", "stateToInt", "(Landroidx/work/WorkInfo$State;)I", "value", "intToState", "(I)Landroidx/work/WorkInfo$State;", "Landroidx/work/BackoffPolicy;", "backoffPolicy", "backoffPolicyToInt", "(Landroidx/work/BackoffPolicy;)I", "intToBackoffPolicy", "(I)Landroidx/work/BackoffPolicy;", "Landroidx/work/NetworkType;", "networkType", "networkTypeToInt", "(Landroidx/work/NetworkType;)I", "intToNetworkType", "(I)Landroidx/work/NetworkType;", "Landroidx/work/OutOfQuotaPolicy;", "policy", "outOfQuotaPolicyToInt", "(Landroidx/work/OutOfQuotaPolicy;)I", "intToOutOfQuotaPolicy", "(I)Landroidx/work/OutOfQuotaPolicy;", "", "Landroidx/work/Constraints$ContentUriTrigger;", "triggers", "", "setOfTriggersToByteArray", "(Ljava/util/Set;)[B", "bytes", "byteArrayToSetOfTriggers", "([B)Ljava/util/Set;", "Landroidx/work/impl/utils/NetworkRequestCompat;", "toNetworkRequest$work_runtime_release", "([B)Landroidx/work/impl/utils/NetworkRequestCompat;", "toNetworkRequest", "requestCompat", "fromNetworkRequest$work_runtime_release", "(Landroidx/work/impl/utils/NetworkRequestCompat;)[B", "fromNetworkRequest", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkTypeConverters {
    public static final WorkTypeConverters INSTANCE = new WorkTypeConverters();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|(2:19|20)|21|23|24|(2:25|26)|27|29|30|31|32|33|34|35|36|37|38|39|41|42|(2:43|44)|45|47) */
        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|(2:19|20)|21|23|24|25|26|27|29|30|31|32|33|34|35|36|37|38|39|41|42|43|44|45|47) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0077 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x007f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0098 */
        static {
            /*
                androidx.work.WorkInfo$State[] r0 = androidx.work.WorkInfo$State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                androidx.work.WorkInfo$State r2 = androidx.work.WorkInfo$State.ENQUEUED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                androidx.work.WorkInfo$State r3 = androidx.work.WorkInfo$State.RUNNING     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                r3 = 3
                androidx.work.WorkInfo$State r4 = androidx.work.WorkInfo$State.SUCCEEDED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                r4 = 4
                androidx.work.WorkInfo$State r5 = androidx.work.WorkInfo$State.FAILED     // Catch:{ NoSuchFieldError -> 0x002b }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r0[r5] = r4     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                r5 = 5
                androidx.work.WorkInfo$State r6 = androidx.work.WorkInfo$State.BLOCKED     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r0[r6] = r5     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                androidx.work.WorkInfo$State r6 = androidx.work.WorkInfo$State.CANCELLED     // Catch:{ NoSuchFieldError -> 0x003d }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r7 = 6
                r0[r6] = r7     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                androidx.work.BackoffPolicy[] r0 = androidx.work.BackoffPolicy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.work.BackoffPolicy r6 = androidx.work.BackoffPolicy.EXPONENTIAL     // Catch:{ NoSuchFieldError -> 0x004e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r0[r6] = r1     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                androidx.work.BackoffPolicy r6 = androidx.work.BackoffPolicy.LINEAR     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r0[r6] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                $EnumSwitchMapping$1 = r0
                androidx.work.NetworkType[] r0 = androidx.work.NetworkType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.work.NetworkType r6 = androidx.work.NetworkType.NOT_REQUIRED     // Catch:{ NoSuchFieldError -> 0x0067 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0067 }
                r0[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0067 }
            L_0x0067:
                androidx.work.NetworkType r6 = androidx.work.NetworkType.CONNECTED     // Catch:{ NoSuchFieldError -> 0x006f }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
                r0[r6] = r2     // Catch:{ NoSuchFieldError -> 0x006f }
            L_0x006f:
                androidx.work.NetworkType r6 = androidx.work.NetworkType.UNMETERED     // Catch:{ NoSuchFieldError -> 0x0077 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0077 }
                r0[r6] = r3     // Catch:{ NoSuchFieldError -> 0x0077 }
            L_0x0077:
                androidx.work.NetworkType r3 = androidx.work.NetworkType.NOT_ROAMING     // Catch:{ NoSuchFieldError -> 0x007f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r3] = r4     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                androidx.work.NetworkType r3 = androidx.work.NetworkType.METERED     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r0[r3] = r5     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                $EnumSwitchMapping$2 = r0
                androidx.work.OutOfQuotaPolicy[] r0 = androidx.work.OutOfQuotaPolicy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.work.OutOfQuotaPolicy r3 = androidx.work.OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST     // Catch:{ NoSuchFieldError -> 0x0098 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0098 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0098 }
            L_0x0098:
                androidx.work.OutOfQuotaPolicy r1 = androidx.work.OutOfQuotaPolicy.DROP_WORK_REQUEST     // Catch:{ NoSuchFieldError -> 0x00a0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a0 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a0 }
            L_0x00a0:
                $EnumSwitchMapping$3 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.WhenMappings.<clinit>():void");
        }
    }

    private WorkTypeConverters() {
    }

    public static final int backoffPolicyToInt(BackoffPolicy backoffPolicy) {
        j.e(backoffPolicy, "backoffPolicy");
        int i2 = WhenMappings.$EnumSwitchMapping$1[backoffPolicy.ordinal()];
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        throw new RuntimeException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        B1.a.g(r8, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004b, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.Set<androidx.work.Constraints.ContentUriTrigger> byteArrayToSetOfTriggers(byte[] r8) {
        /*
            java.lang.String r0 = "bytes"
            kotlin.jvm.internal.j.e(r8, r0)
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet
            r0.<init>()
            int r1 = r8.length
            if (r1 != 0) goto L_0x000e
            return r0
        L_0x000e:
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r8)
            java.io.ObjectInputStream r8 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0044 }
            r8.<init>(r1)     // Catch:{ IOException -> 0x0044 }
            int r2 = r8.readInt()     // Catch:{ all -> 0x003c }
            r3 = 0
        L_0x001d:
            if (r3 >= r2) goto L_0x003e
            java.lang.String r4 = r8.readUTF()     // Catch:{ all -> 0x003c }
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ all -> 0x003c }
            boolean r5 = r8.readBoolean()     // Catch:{ all -> 0x003c }
            androidx.work.Constraints$ContentUriTrigger r6 = new androidx.work.Constraints$ContentUriTrigger     // Catch:{ all -> 0x003c }
            java.lang.String r7 = "uri"
            kotlin.jvm.internal.j.d(r4, r7)     // Catch:{ all -> 0x003c }
            r6.<init>(r4, r5)     // Catch:{ all -> 0x003c }
            r0.add(r6)     // Catch:{ all -> 0x003c }
            int r3 = r3 + 1
            goto L_0x001d
        L_0x003c:
            r2 = move-exception
            goto L_0x0046
        L_0x003e:
            r8.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x004f
        L_0x0042:
            r8 = move-exception
            goto L_0x0053
        L_0x0044:
            r8 = move-exception
            goto L_0x004c
        L_0x0046:
            throw r2     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r3 = move-exception
            B1.a.g(r8, r2)     // Catch:{ IOException -> 0x0044 }
            throw r3     // Catch:{ IOException -> 0x0044 }
        L_0x004c:
            r8.printStackTrace()     // Catch:{ all -> 0x0042 }
        L_0x004f:
            r1.close()
            return r0
        L_0x0053:
            throw r8     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r0 = move-exception
            B1.a.g(r1, r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.byteArrayToSetOfTriggers(byte[]):java.util.Set");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005c, code lost:
        B1.a.g(r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final byte[] fromNetworkRequest$work_runtime_release(androidx.work.impl.utils.NetworkRequestCompat r7) {
        /*
            java.lang.String r0 = "requestCompat"
            kotlin.jvm.internal.j.e(r7, r0)
            android.net.NetworkRequest r7 = r7.getNetworkRequest()
            r0 = 0
            if (r7 != 0) goto L_0x000f
            byte[] r7 = new byte[r0]
            return r7
        L_0x000f:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x0052 }
            r2.<init>(r1)     // Catch:{ all -> 0x0052 }
            int[] r3 = androidx.work.impl.utils.NetworkRequestCompatKt.getTransportTypesCompat(r7)     // Catch:{ all -> 0x0031 }
            int[] r7 = androidx.work.impl.utils.NetworkRequestCompatKt.getCapabilitiesCompat(r7)     // Catch:{ all -> 0x0031 }
            int r4 = r3.length     // Catch:{ all -> 0x0031 }
            r2.writeInt(r4)     // Catch:{ all -> 0x0031 }
            int r4 = r3.length     // Catch:{ all -> 0x0031 }
            r5 = r0
        L_0x0027:
            if (r5 >= r4) goto L_0x0033
            r6 = r3[r5]     // Catch:{ all -> 0x0031 }
            r2.writeInt(r6)     // Catch:{ all -> 0x0031 }
            int r5 = r5 + 1
            goto L_0x0027
        L_0x0031:
            r7 = move-exception
            goto L_0x0054
        L_0x0033:
            int r3 = r7.length     // Catch:{ all -> 0x0031 }
            r2.writeInt(r3)     // Catch:{ all -> 0x0031 }
            int r3 = r7.length     // Catch:{ all -> 0x0031 }
        L_0x0038:
            if (r0 >= r3) goto L_0x0042
            r4 = r7[r0]     // Catch:{ all -> 0x0031 }
            r2.writeInt(r4)     // Catch:{ all -> 0x0031 }
            int r0 = r0 + 1
            goto L_0x0038
        L_0x0042:
            r2.close()     // Catch:{ all -> 0x0052 }
            r1.close()
            byte[] r7 = r1.toByteArray()
            java.lang.String r0 = "outputStream.toByteArray()"
            kotlin.jvm.internal.j.d(r7, r0)
            return r7
        L_0x0052:
            r7 = move-exception
            goto L_0x005a
        L_0x0054:
            throw r7     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r0 = move-exception
            B1.a.g(r2, r7)     // Catch:{ all -> 0x0052 }
            throw r0     // Catch:{ all -> 0x0052 }
        L_0x005a:
            throw r7     // Catch:{ all -> 0x005b }
        L_0x005b:
            r0 = move-exception
            B1.a.g(r1, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.fromNetworkRequest$work_runtime_release(androidx.work.impl.utils.NetworkRequestCompat):byte[]");
    }

    public static final BackoffPolicy intToBackoffPolicy(int i2) {
        if (i2 == 0) {
            return BackoffPolicy.EXPONENTIAL;
        }
        if (i2 == 1) {
            return BackoffPolicy.LINEAR;
        }
        throw new IllegalArgumentException(C0212a.j(i2, "Could not convert ", " to BackoffPolicy"));
    }

    public static final NetworkType intToNetworkType(int i2) {
        if (i2 == 0) {
            return NetworkType.NOT_REQUIRED;
        }
        if (i2 == 1) {
            return NetworkType.CONNECTED;
        }
        if (i2 == 2) {
            return NetworkType.UNMETERED;
        }
        if (i2 == 3) {
            return NetworkType.NOT_ROAMING;
        }
        if (i2 == 4) {
            return NetworkType.METERED;
        }
        if (i2 == 5) {
            return NetworkType.TEMPORARILY_UNMETERED;
        }
        throw new IllegalArgumentException(C0212a.j(i2, "Could not convert ", " to NetworkType"));
    }

    public static final OutOfQuotaPolicy intToOutOfQuotaPolicy(int i2) {
        if (i2 == 0) {
            return OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
        }
        if (i2 == 1) {
            return OutOfQuotaPolicy.DROP_WORK_REQUEST;
        }
        throw new IllegalArgumentException(C0212a.j(i2, "Could not convert ", " to OutOfQuotaPolicy"));
    }

    public static final WorkInfo$State intToState(int i2) {
        if (i2 == 0) {
            return WorkInfo$State.ENQUEUED;
        }
        if (i2 == 1) {
            return WorkInfo$State.RUNNING;
        }
        if (i2 == 2) {
            return WorkInfo$State.SUCCEEDED;
        }
        if (i2 == 3) {
            return WorkInfo$State.FAILED;
        }
        if (i2 == 4) {
            return WorkInfo$State.BLOCKED;
        }
        if (i2 == 5) {
            return WorkInfo$State.CANCELLED;
        }
        throw new IllegalArgumentException(C0212a.j(i2, "Could not convert ", " to State"));
    }

    public static final int networkTypeToInt(NetworkType networkType) {
        j.e(networkType, "networkType");
        int i2 = WhenMappings.$EnumSwitchMapping$2[networkType.ordinal()];
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 3) {
            return 2;
        }
        if (i2 == 4) {
            return 3;
        }
        if (i2 == 5) {
            return 4;
        }
        if (networkType == NetworkType.TEMPORARILY_UNMETERED) {
            return 5;
        }
        throw new IllegalArgumentException("Could not convert " + networkType + " to int");
    }

    public static final int outOfQuotaPolicyToInt(OutOfQuotaPolicy outOfQuotaPolicy) {
        j.e(outOfQuotaPolicy, "policy");
        int i2 = WhenMappings.$EnumSwitchMapping$3[outOfQuotaPolicy.ordinal()];
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        throw new RuntimeException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        B1.a.g(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005d, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0060, code lost:
        B1.a.g(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0063, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final byte[] setOfTriggersToByteArray(java.util.Set<androidx.work.Constraints.ContentUriTrigger> r4) {
        /*
            java.lang.String r0 = "triggers"
            kotlin.jvm.internal.j.e(r4, r0)
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L_0x0010
            r4 = 0
            byte[] r4 = new byte[r4]
            return r4
        L_0x0010:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x0056 }
            r1.<init>(r0)     // Catch:{ all -> 0x0056 }
            int r2 = r4.size()     // Catch:{ all -> 0x0044 }
            r1.writeInt(r2)     // Catch:{ all -> 0x0044 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0044 }
        L_0x0025:
            boolean r2 = r4.hasNext()     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0046
            java.lang.Object r2 = r4.next()     // Catch:{ all -> 0x0044 }
            androidx.work.Constraints$ContentUriTrigger r2 = (androidx.work.Constraints.ContentUriTrigger) r2     // Catch:{ all -> 0x0044 }
            android.net.Uri r3 = r2.getUri()     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0044 }
            r1.writeUTF(r3)     // Catch:{ all -> 0x0044 }
            boolean r2 = r2.isTriggeredForDescendants()     // Catch:{ all -> 0x0044 }
            r1.writeBoolean(r2)     // Catch:{ all -> 0x0044 }
            goto L_0x0025
        L_0x0044:
            r4 = move-exception
            goto L_0x0058
        L_0x0046:
            r1.close()     // Catch:{ all -> 0x0056 }
            r0.close()
            byte[] r4 = r0.toByteArray()
            java.lang.String r0 = "outputStream.toByteArray()"
            kotlin.jvm.internal.j.d(r4, r0)
            return r4
        L_0x0056:
            r4 = move-exception
            goto L_0x005e
        L_0x0058:
            throw r4     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r2 = move-exception
            B1.a.g(r1, r4)     // Catch:{ all -> 0x0056 }
            throw r2     // Catch:{ all -> 0x0056 }
        L_0x005e:
            throw r4     // Catch:{ all -> 0x005f }
        L_0x005f:
            r1 = move-exception
            B1.a.g(r0, r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.setOfTriggersToByteArray(java.util.Set):byte[]");
    }

    public static final int stateToInt(WorkInfo$State workInfo$State) {
        j.e(workInfo$State, "state");
        switch (WhenMappings.$EnumSwitchMapping$0[workInfo$State.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            default:
                throw new RuntimeException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0055, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0056, code lost:
        B1.a.g(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0059, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final androidx.work.impl.utils.NetworkRequestCompat toNetworkRequest$work_runtime_release(byte[] r6) {
        /*
            java.lang.String r0 = "bytes"
            kotlin.jvm.internal.j.e(r6, r0)
            int r0 = r6.length
            if (r0 != 0) goto L_0x000f
            androidx.work.impl.utils.NetworkRequestCompat r6 = new androidx.work.impl.utils.NetworkRequestCompat
            r0 = 0
            r6.<init>(r0)
            return r6
        L_0x000f:
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r6)
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch:{ all -> 0x004c }
            r6.<init>(r0)     // Catch:{ all -> 0x004c }
            int r1 = r6.readInt()     // Catch:{ all -> 0x002c }
            int[] r2 = new int[r1]     // Catch:{ all -> 0x002c }
            r3 = 0
            r4 = r3
        L_0x0021:
            if (r4 >= r1) goto L_0x002e
            int r5 = r6.readInt()     // Catch:{ all -> 0x002c }
            r2[r4] = r5     // Catch:{ all -> 0x002c }
            int r4 = r4 + 1
            goto L_0x0021
        L_0x002c:
            r1 = move-exception
            goto L_0x004e
        L_0x002e:
            int r1 = r6.readInt()     // Catch:{ all -> 0x002c }
            int[] r4 = new int[r1]     // Catch:{ all -> 0x002c }
        L_0x0034:
            if (r3 >= r1) goto L_0x003f
            int r5 = r6.readInt()     // Catch:{ all -> 0x002c }
            r4[r3] = r5     // Catch:{ all -> 0x002c }
            int r3 = r3 + 1
            goto L_0x0034
        L_0x003f:
            androidx.work.impl.utils.NetworkRequest28 r1 = androidx.work.impl.utils.NetworkRequest28.INSTANCE     // Catch:{ all -> 0x002c }
            androidx.work.impl.utils.NetworkRequestCompat r1 = r1.createNetworkRequestCompat$work_runtime_release(r4, r2)     // Catch:{ all -> 0x002c }
            r6.close()     // Catch:{ all -> 0x004c }
            r0.close()
            return r1
        L_0x004c:
            r6 = move-exception
            goto L_0x0054
        L_0x004e:
            throw r1     // Catch:{ all -> 0x004f }
        L_0x004f:
            r2 = move-exception
            B1.a.g(r6, r1)     // Catch:{ all -> 0x004c }
            throw r2     // Catch:{ all -> 0x004c }
        L_0x0054:
            throw r6     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r1 = move-exception
            B1.a.g(r0, r6)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.toNetworkRequest$work_runtime_release(byte[]):androidx.work.impl.utils.NetworkRequestCompat");
    }
}
