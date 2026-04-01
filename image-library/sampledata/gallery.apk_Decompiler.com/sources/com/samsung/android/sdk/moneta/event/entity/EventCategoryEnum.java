package com.samsung.android.sdk.moneta.event.entity;

import Ad.C0721b;
import L1.d;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import gg.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.f;
import me.h;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "UNKNOWN", "SOCIAL", "BUSINESS", "TRIP", "RESERVATION", "COUPON", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum EventCategoryEnum {
    UNKNOWN(0),
    SOCIAL(1),
    BUSINESS(2),
    TRIP(3),
    RESERVATION(4),
    COUPON(5);
    
    /* access modifiers changed from: private */
    public static final f $cachedSerializer$delegate = null;
    public static final Companion Companion = null;
    private final int value;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum$Companion;", "", "<init>", "()V", "Lgg/a;", "Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "serializer", "()Lgg/a;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        private Companion() {
        }

        private final /* synthetic */ a get$cachedSerializer() {
            return (a) EventCategoryEnum.$cachedSerializer$delegate.getValue();
        }

        public final a serializer() {
            return get$cachedSerializer();
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }
    }

    static {
        EventCategoryEnum[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
        $cachedSerializer$delegate = d.p(h.PUBLICATION, new C0721b(2));
    }

    private EventCategoryEnum(int i2) {
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
