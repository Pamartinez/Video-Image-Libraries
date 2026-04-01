package com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event;

import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ToggleState;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SimpleCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ToggleCapsule;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\t\n\u000bB\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\b\u0001\u0003\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent;", "", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "capsule", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "getCapsule", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "OnClick", "OnToggled", "OnExpanded", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnClick;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnExpanded;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnToggled;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CapsuleEvent {
    private final Capsule capsule;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnClick;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent;", "capsule", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;)V", "getCapsule", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OnClick extends CapsuleEvent {
        private final SimpleCapsule capsule;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OnClick(SimpleCapsule simpleCapsule) {
            super(simpleCapsule, (e) null);
            j.e(simpleCapsule, "capsule");
            this.capsule = simpleCapsule;
        }

        public SimpleCapsule getCapsule() {
            return this.capsule;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnExpanded;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent;", "capsule", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;", "state", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;)V", "getCapsule", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;", "getState", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ExpandState;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OnExpanded extends CapsuleEvent {
        private final ExpandableCapsule capsule;
        private final ExpandState state;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OnExpanded(ExpandableCapsule expandableCapsule, ExpandState expandState) {
            super(expandableCapsule, (e) null);
            j.e(expandableCapsule, "capsule");
            j.e(expandState, "state");
            this.capsule = expandableCapsule;
            this.state = expandState;
        }

        public ExpandableCapsule getCapsule() {
            return this.capsule;
        }

        public final ExpandState getState() {
            return this.state;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent$OnToggled;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent;", "capsule", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;", "state", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;)V", "getCapsule", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;", "getState", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/state/ToggleState;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OnToggled extends CapsuleEvent {
        private final ToggleCapsule capsule;
        private final ToggleState state;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OnToggled(ToggleCapsule toggleCapsule, ToggleState toggleState) {
            super(toggleCapsule, (e) null);
            j.e(toggleCapsule, "capsule");
            j.e(toggleState, "state");
            this.capsule = toggleCapsule;
            this.state = toggleState;
        }

        public ToggleCapsule getCapsule() {
            return this.capsule;
        }

        public final ToggleState getState() {
            return this.state;
        }
    }

    public /* synthetic */ CapsuleEvent(Capsule capsule2, e eVar) {
        this(capsule2);
    }

    private CapsuleEvent(Capsule capsule2) {
        this.capsule = capsule2;
    }
}
