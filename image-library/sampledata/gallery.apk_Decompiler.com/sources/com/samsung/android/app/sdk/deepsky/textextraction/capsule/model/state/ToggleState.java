package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state;

import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;", "", "<init>", "()V", "On", "Off", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState$Off;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState$On;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ToggleState {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState$Off;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Off extends ToggleState {
        public static final Off INSTANCE = new Off();

        private Off() {
            super((e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof Off)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 862561787;
        }

        public String toString() {
            return "Off";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState$On;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class On extends ToggleState {
        public static final On INSTANCE = new On();

        private On() {
            super((e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof On)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 582013907;
        }

        public String toString() {
            return "On";
        }
    }

    public /* synthetic */ ToggleState(e eVar) {
        this();
    }

    private ToggleState() {
    }
}
