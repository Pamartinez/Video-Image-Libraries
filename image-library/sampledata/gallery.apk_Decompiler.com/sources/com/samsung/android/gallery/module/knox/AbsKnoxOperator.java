package com.samsung.android.gallery.module.knox;

import android.content.Context;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsKnoxOperator {
    protected final Blackboard mBlackboard;
    protected final KnoxOperationCallback mCallback;
    protected final int mContainerId;
    protected final KnoxUtil.MoveType mMoveType;

    public AbsKnoxOperator(Blackboard blackboard, KnoxUtil.MoveType moveType, int i2, KnoxOperationCallback knoxOperationCallback) {
        this.mBlackboard = blackboard;
        this.mMoveType = moveType;
        this.mContainerId = i2;
        this.mCallback = knoxOperationCallback;
    }

    public abstract void operate(Context context, MediaItem[] mediaItemArr);

    public final void operateInternal(Context context, List<MediaItem> list) {
        new KnoxOperationTask(this.mBlackboard, list, this.mContainerId, this.mMoveType, this.mCallback).operate(context);
    }
}
