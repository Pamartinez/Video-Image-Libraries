package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state;

import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "", "<init>", "()V", "Collapsed", "Expanded", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState$Collapsed;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState$Expanded;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ExpandState {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState$Collapsed;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Collapsed extends ExpandState {
        public static final Collapsed INSTANCE = new Collapsed();

        private Collapsed() {
            super((e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof Collapsed)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -124397091;
        }

        public String toString() {
            return "Collapsed";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState$Expanded;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Expanded extends ExpandState {
        public static final Expanded INSTANCE = new Expanded();

        private Expanded() {
            super((e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof Expanded)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1726669709;
        }

        public String toString() {
            return "Expanded";
        }
    }

    public /* synthetic */ ExpandState(e eVar) {
        this();
    }

    private ExpandState() {
    }
}
