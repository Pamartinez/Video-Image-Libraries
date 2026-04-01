package com.samsung.android.gallery.widget.abstraction;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionData {
    private final Blackboard mBlackboard;
    private final int mEnterTransitionId;
    private final SharedTransition.TransitionListener mListener;
    private final boolean mReparentWithOverlay;
    private final int mReturnTransitionId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public Blackboard blackboard;
        /* access modifiers changed from: private */
        public int enterTransitionId;
        /* access modifiers changed from: private */
        public SharedTransition.TransitionListener listener;
        /* access modifiers changed from: private */
        public boolean reparentWithOverlay = false;
        /* access modifiers changed from: private */
        public int returnTransitionId;

        public TransitionData build() {
            return new TransitionData(this, 0);
        }

        public Builder setBlackboard(Blackboard blackboard2) {
            this.blackboard = blackboard2;
            return this;
        }

        public Builder setEnterTransitionId(int i2) {
            this.enterTransitionId = i2;
            return this;
        }

        public Builder setListener(SharedTransition.TransitionListener transitionListener) {
            this.listener = transitionListener;
            return this;
        }

        public Builder setReparentWithOverlay(boolean z) {
            this.reparentWithOverlay = z;
            return this;
        }

        public Builder setReturnTransitionId(int i2) {
            this.returnTransitionId = i2;
            return this;
        }
    }

    public /* synthetic */ TransitionData(Builder builder, int i2) {
        this(builder);
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public SharedTransition.TransitionListener getListener() {
        return this.mListener;
    }

    public boolean getReparentWithOverlay() {
        return this.mReparentWithOverlay;
    }

    public int getTransitionId(boolean z) {
        if (z) {
            return this.mEnterTransitionId;
        }
        return this.mReturnTransitionId;
    }

    private TransitionData(Builder builder) {
        this.mBlackboard = builder.blackboard;
        this.mListener = builder.listener;
        this.mEnterTransitionId = builder.enterTransitionId;
        this.mReturnTransitionId = builder.returnTransitionId;
        this.mReparentWithOverlay = builder.reparentWithOverlay;
    }
}
