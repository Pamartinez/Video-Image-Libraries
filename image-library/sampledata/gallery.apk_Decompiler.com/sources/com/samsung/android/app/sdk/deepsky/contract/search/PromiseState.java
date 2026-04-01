package com.samsung.android.app.sdk.deepsky.contract.search;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/PromiseState;", "", "id", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "PENDING", "FULFILLED", "REJECTED", "UNKNOWN", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PromiseState {
    PENDING("pending"),
    FULFILLED("fulfilled"),
    REJECTED("rejected"),
    UNKNOWN("unknown");
    
    public static final Companion Companion = null;
    private final String id;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/PromiseState$Companion;", "", "<init>", "()V", "fromId", "Lcom/samsung/android/app/sdk/deepsky/contract/search/PromiseState;", "id", "", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final PromiseState fromId(String str) {
            PromiseState promiseState;
            j.e(str, "id");
            PromiseState[] values = PromiseState.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    promiseState = null;
                    break;
                }
                promiseState = values[i2];
                if (j.a(promiseState.getId(), str)) {
                    break;
                }
                i2++;
            }
            if (promiseState == null) {
                return PromiseState.UNKNOWN;
            }
            return promiseState;
        }

        private Companion() {
        }
    }

    static {
        PromiseState[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private PromiseState(String str) {
        this.id = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getId() {
        return this.id;
    }
}
