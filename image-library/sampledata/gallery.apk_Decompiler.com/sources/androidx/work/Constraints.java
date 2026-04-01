package androidx.work;

import android.net.NetworkRequest;
import android.net.Uri;
import androidx.work.impl.utils.NetworkRequestCompat;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 +2\u00020\u0001:\u0003*+,B/\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bB9\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\nB]\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0002\u0010\u0011Be\b\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0002\u0010\u0014B\u000f\b\u0017\u0012\u0006\u0010\u0015\u001a\u00020\u0000¢\u0006\u0002\u0010\u0016J\u0013\u0010$\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010%\u001a\u00020\u0005H\u0007J\b\u0010&\u001a\u00020'H\u0017J\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\t\u001a\u00020\u0005H\u0007J\u0006\u0010\u0007\u001a\u00020\u0005J\b\u0010(\u001a\u00020)H\u0017R\u0016\u0010\r\u001a\u00020\f8GX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u000b\u001a\u00020\f8GX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8GX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d8G¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0012\u001a\u00020\u00138\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0010\u0010\u0006\u001a\u00020\u00058\u0002X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0002X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0002X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Landroidx/work/Constraints;", "", "requiredNetworkType", "Landroidx/work/NetworkType;", "requiresCharging", "", "requiresBatteryNotLow", "requiresStorageNotLow", "(Landroidx/work/NetworkType;ZZZ)V", "requiresDeviceIdle", "(Landroidx/work/NetworkType;ZZZZ)V", "contentTriggerUpdateDelayMillis", "", "contentTriggerMaxDelayMillis", "contentUriTriggers", "", "Landroidx/work/Constraints$ContentUriTrigger;", "(Landroidx/work/NetworkType;ZZZZJJLjava/util/Set;)V", "requiredNetworkRequestCompat", "Landroidx/work/impl/utils/NetworkRequestCompat;", "(Landroidx/work/impl/utils/NetworkRequestCompat;Landroidx/work/NetworkType;ZZZZJJLjava/util/Set;)V", "other", "(Landroidx/work/Constraints;)V", "getContentTriggerMaxDelayMillis", "()J", "getContentTriggerUpdateDelayMillis", "getContentUriTriggers", "()Ljava/util/Set;", "requiredNetworkRequest", "Landroid/net/NetworkRequest;", "getRequiredNetworkRequest", "()Landroid/net/NetworkRequest;", "getRequiredNetworkRequestCompat$work_runtime_release", "()Landroidx/work/impl/utils/NetworkRequestCompat;", "getRequiredNetworkType", "()Landroidx/work/NetworkType;", "equals", "hasContentUriTriggers", "hashCode", "", "toString", "", "Builder", "Companion", "ContentUriTrigger", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Constraints {
    public static final Companion Companion = new Companion((e) null);
    public static final Constraints NONE = new Constraints((NetworkType) null, false, false, false, 15, (e) null);
    private final long contentTriggerMaxDelayMillis;
    private final long contentTriggerUpdateDelayMillis;
    private final Set<ContentUriTrigger> contentUriTriggers;
    private final NetworkRequestCompat requiredNetworkRequestCompat;
    private final NetworkType requiredNetworkType;
    private final boolean requiresBatteryNotLow;
    private final boolean requiresCharging;
    private final boolean requiresDeviceIdle;
    private final boolean requiresStorageNotLow;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u00020\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\n\u0010\tR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\tR\u0016\u0010\u0012\u001a\u00020\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\tR\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/work/Constraints$Builder;", "", "<init>", "()V", "Landroidx/work/Constraints;", "build", "()Landroidx/work/Constraints;", "", "requiresCharging", "Z", "requiresDeviceIdle", "Landroidx/work/impl/utils/NetworkRequestCompat;", "requiredNetworkRequest", "Landroidx/work/impl/utils/NetworkRequestCompat;", "Landroidx/work/NetworkType;", "requiredNetworkType", "Landroidx/work/NetworkType;", "requiresBatteryNotLow", "requiresStorageNotLow", "", "triggerContentUpdateDelay", "J", "triggerContentMaxDelay", "", "Landroidx/work/Constraints$ContentUriTrigger;", "contentUriTriggers", "Ljava/util/Set;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Set<ContentUriTrigger> contentUriTriggers = new LinkedHashSet();
        private NetworkRequestCompat requiredNetworkRequest = new NetworkRequestCompat((Object) null, 1, (e) null);
        private NetworkType requiredNetworkType = NetworkType.NOT_REQUIRED;
        private boolean requiresBatteryNotLow;
        private boolean requiresCharging;
        private boolean requiresDeviceIdle;
        private boolean requiresStorageNotLow;
        private long triggerContentMaxDelay = -1;
        private long triggerContentUpdateDelay = -1;

        public final Constraints build() {
            Set p12 = C1194l.p1(this.contentUriTriggers);
            return new Constraints(this.requiredNetworkRequest, this.requiredNetworkType, this.requiresCharging, this.requiresDeviceIdle, this.requiresBatteryNotLow, this.requiresStorageNotLow, this.triggerContentUpdateDelay, this.triggerContentMaxDelay, p12);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/work/Constraints$Companion;", "", "()V", "NONE", "Landroidx/work/Constraints;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Landroidx/work/Constraints$ContentUriTrigger;", "", "uri", "Landroid/net/Uri;", "isTriggeredForDescendants", "", "(Landroid/net/Uri;Z)V", "()Z", "getUri", "()Landroid/net/Uri;", "equals", "other", "hashCode", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ContentUriTrigger {
        private final boolean isTriggeredForDescendants;
        private final Uri uri;

        public ContentUriTrigger(Uri uri2, boolean z) {
            j.e(uri2, OCRServiceConstant.KEY_PARAM_URI);
            this.uri = uri2;
            this.isTriggeredForDescendants = z;
        }

        public boolean equals(Object obj) {
            Class<?> cls;
            if (this == obj) {
                return true;
            }
            if (obj != null) {
                cls = obj.getClass();
            } else {
                cls = null;
            }
            if (!ContentUriTrigger.class.equals(cls)) {
                return false;
            }
            j.c(obj, "null cannot be cast to non-null type androidx.work.Constraints.ContentUriTrigger");
            ContentUriTrigger contentUriTrigger = (ContentUriTrigger) obj;
            if (j.a(this.uri, contentUriTrigger.uri) && this.isTriggeredForDescendants == contentUriTrigger.isTriggeredForDescendants) {
                return true;
            }
            return false;
        }

        public final Uri getUri() {
            return this.uri;
        }

        public int hashCode() {
            return Boolean.hashCode(this.isTriggeredForDescendants) + (this.uri.hashCode() * 31);
        }

        public final boolean isTriggeredForDescendants() {
            return this.isTriggeredForDescendants;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Constraints(NetworkType networkType, boolean z, boolean z3, boolean z7, int i2, e eVar) {
        this((i2 & 1) != 0 ? NetworkType.NOT_REQUIRED : networkType, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z3, (i2 & 8) != 0 ? false : z7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Constraints.class.equals(obj.getClass())) {
            return false;
        }
        Constraints constraints = (Constraints) obj;
        if (this.requiresCharging == constraints.requiresCharging && this.requiresDeviceIdle == constraints.requiresDeviceIdle && this.requiresBatteryNotLow == constraints.requiresBatteryNotLow && this.requiresStorageNotLow == constraints.requiresStorageNotLow && this.contentTriggerUpdateDelayMillis == constraints.contentTriggerUpdateDelayMillis && this.contentTriggerMaxDelayMillis == constraints.contentTriggerMaxDelayMillis && j.a(getRequiredNetworkRequest(), constraints.getRequiredNetworkRequest()) && this.requiredNetworkType == constraints.requiredNetworkType) {
            return j.a(this.contentUriTriggers, constraints.contentUriTriggers);
        }
        return false;
    }

    public final long getContentTriggerMaxDelayMillis() {
        return this.contentTriggerMaxDelayMillis;
    }

    public final long getContentTriggerUpdateDelayMillis() {
        return this.contentTriggerUpdateDelayMillis;
    }

    public final Set<ContentUriTrigger> getContentUriTriggers() {
        return this.contentUriTriggers;
    }

    public final NetworkRequest getRequiredNetworkRequest() {
        return this.requiredNetworkRequestCompat.getNetworkRequest();
    }

    public final NetworkRequestCompat getRequiredNetworkRequestCompat$work_runtime_release() {
        return this.requiredNetworkRequestCompat;
    }

    public final NetworkType getRequiredNetworkType() {
        return this.requiredNetworkType;
    }

    public final boolean hasContentUriTriggers() {
        if (!this.contentUriTriggers.isEmpty()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        long j2 = this.contentTriggerUpdateDelayMillis;
        long j3 = this.contentTriggerMaxDelayMillis;
        int hashCode = (this.contentUriTriggers.hashCode() + (((((((((((((this.requiredNetworkType.hashCode() * 31) + (this.requiresCharging ? 1 : 0)) * 31) + (this.requiresDeviceIdle ? 1 : 0)) * 31) + (this.requiresBatteryNotLow ? 1 : 0)) * 31) + (this.requiresStorageNotLow ? 1 : 0)) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31)) * 31;
        NetworkRequest requiredNetworkRequest = getRequiredNetworkRequest();
        if (requiredNetworkRequest != null) {
            i2 = requiredNetworkRequest.hashCode();
        } else {
            i2 = 0;
        }
        return hashCode + i2;
    }

    public final boolean requiresBatteryNotLow() {
        return this.requiresBatteryNotLow;
    }

    public final boolean requiresCharging() {
        return this.requiresCharging;
    }

    public final boolean requiresDeviceIdle() {
        return this.requiresDeviceIdle;
    }

    public final boolean requiresStorageNotLow() {
        return this.requiresStorageNotLow;
    }

    public String toString() {
        return "Constraints{requiredNetworkType=" + this.requiredNetworkType + ", requiresCharging=" + this.requiresCharging + ", requiresDeviceIdle=" + this.requiresDeviceIdle + ", requiresBatteryNotLow=" + this.requiresBatteryNotLow + ", requiresStorageNotLow=" + this.requiresStorageNotLow + ", contentTriggerUpdateDelayMillis=" + this.contentTriggerUpdateDelayMillis + ", contentTriggerMaxDelayMillis=" + this.contentTriggerMaxDelayMillis + ", contentUriTriggers=" + this.contentUriTriggers + ", }";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Constraints(NetworkType networkType, boolean z, boolean z3, boolean z7) {
        this(networkType, z, false, z3, z7);
        j.e(networkType, "requiredNetworkType");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Constraints(NetworkType networkType, boolean z, boolean z3, boolean z7, boolean z9) {
        this(networkType, z, z3, z7, z9, -1, 0, (Set) null, 192, (e) null);
        j.e(networkType, "requiredNetworkType");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Constraints(androidx.work.NetworkType r3, boolean r4, boolean r5, boolean r6, boolean r7, long r8, long r10, java.util.Set r12, int r13, kotlin.jvm.internal.e r14) {
        /*
            r2 = this;
            r14 = r13 & 1
            if (r14 == 0) goto L_0x0006
            androidx.work.NetworkType r3 = androidx.work.NetworkType.NOT_REQUIRED
        L_0x0006:
            r14 = r13 & 2
            r0 = 0
            if (r14 == 0) goto L_0x000c
            r4 = r0
        L_0x000c:
            r14 = r13 & 4
            if (r14 == 0) goto L_0x0011
            r5 = r0
        L_0x0011:
            r14 = r13 & 8
            if (r14 == 0) goto L_0x0016
            r6 = r0
        L_0x0016:
            r14 = r13 & 16
            if (r14 == 0) goto L_0x001b
            r7 = r0
        L_0x001b:
            r14 = r13 & 32
            r0 = -1
            if (r14 == 0) goto L_0x0022
            r8 = r0
        L_0x0022:
            r14 = r13 & 64
            if (r14 == 0) goto L_0x0027
            r10 = r0
        L_0x0027:
            r13 = r13 & 128(0x80, float:1.794E-43)
            if (r13 == 0) goto L_0x002d
            ne.v r12 = ne.v.d
        L_0x002d:
            r14 = r12
            r12 = r10
            r10 = r8
            r8 = r6
            r9 = r7
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10, r12, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Constraints.<init>(androidx.work.NetworkType, boolean, boolean, boolean, boolean, long, long, java.util.Set, int, kotlin.jvm.internal.e):void");
    }

    public Constraints(NetworkType networkType, boolean z, boolean z3, boolean z7, boolean z9, long j2, long j3, Set<ContentUriTrigger> set) {
        j.e(networkType, "requiredNetworkType");
        j.e(set, "contentUriTriggers");
        this.requiredNetworkRequestCompat = new NetworkRequestCompat((Object) null, 1, (e) null);
        this.requiredNetworkType = networkType;
        this.requiresCharging = z;
        this.requiresDeviceIdle = z3;
        this.requiresBatteryNotLow = z7;
        this.requiresStorageNotLow = z9;
        this.contentTriggerUpdateDelayMillis = j2;
        this.contentTriggerMaxDelayMillis = j3;
        this.contentUriTriggers = set;
    }

    public Constraints(NetworkRequestCompat networkRequestCompat, NetworkType networkType, boolean z, boolean z3, boolean z7, boolean z9, long j2, long j3, Set<ContentUriTrigger> set) {
        j.e(networkRequestCompat, "requiredNetworkRequestCompat");
        j.e(networkType, "requiredNetworkType");
        j.e(set, "contentUriTriggers");
        this.requiredNetworkRequestCompat = networkRequestCompat;
        this.requiredNetworkType = networkType;
        this.requiresCharging = z;
        this.requiresDeviceIdle = z3;
        this.requiresBatteryNotLow = z7;
        this.requiresStorageNotLow = z9;
        this.contentTriggerUpdateDelayMillis = j2;
        this.contentTriggerMaxDelayMillis = j3;
        this.contentUriTriggers = set;
    }

    public Constraints(Constraints constraints) {
        j.e(constraints, "other");
        this.requiresCharging = constraints.requiresCharging;
        this.requiresDeviceIdle = constraints.requiresDeviceIdle;
        this.requiredNetworkRequestCompat = constraints.requiredNetworkRequestCompat;
        this.requiredNetworkType = constraints.requiredNetworkType;
        this.requiresBatteryNotLow = constraints.requiresBatteryNotLow;
        this.requiresStorageNotLow = constraints.requiresStorageNotLow;
        this.contentUriTriggers = constraints.contentUriTriggers;
        this.contentTriggerUpdateDelayMillis = constraints.contentTriggerUpdateDelayMillis;
        this.contentTriggerMaxDelayMillis = constraints.contentTriggerMaxDelayMillis;
    }
}
