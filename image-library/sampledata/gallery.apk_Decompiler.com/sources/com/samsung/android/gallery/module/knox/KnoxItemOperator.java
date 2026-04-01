package com.samsung.android.gallery.module.knox;

import android.content.Context;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KnoxItemOperator extends AbsKnoxOperator {
    public KnoxItemOperator(Blackboard blackboard, KnoxUtil.MoveType moveType, int i2, KnoxOperationCallback knoxOperationCallback) {
        super(blackboard, moveType, i2, knoxOperationCallback);
    }

    public void operate(Context context, MediaItem[] mediaItemArr) {
        operateInternal(context, new ArrayList(Arrays.asList(mediaItemArr)));
    }
}
