package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpMotionPhotoClipView;
import com.samsung.android.gallery.support.utils.PreferenceCache;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoClipApi extends BaseImpl {
    public MotionPhotoClipApi() {
        super(new QueryParams());
    }

    private void excludeDisallowed(MpMotionPhotoClipView mpMotionPhotoClipView) {
        String string = PreferenceCache.MotionPhotoCleanOutExcluded.getString();
        if (!TextUtils.isEmpty(string)) {
            mpMotionPhotoClipView.excludeFileIds(string);
        }
    }

    public Cursor getMotionPhotoClipCoverCursor() {
        MpMotionPhotoClipView mpMotionPhotoClipView = new MpMotionPhotoClipView(this.mParams);
        excludeDisallowed(mpMotionPhotoClipView);
        mpMotionPhotoClipView.resetDefaultProjectionForCover();
        mpMotionPhotoClipView.limit(8);
        mpMotionPhotoClipView.groupByFileId();
        return this.mQueryExecutor.getCursor(mpMotionPhotoClipView.buildSelectQuery(), "getMotionPhotoClipCoverCursor");
    }

    public Cursor getMotionPhotoClipCursor() {
        MpMotionPhotoClipView mpMotionPhotoClipView = new MpMotionPhotoClipView(this.mParams);
        mpMotionPhotoClipView.groupByFileId();
        excludeDisallowed(mpMotionPhotoClipView);
        return this.mQueryExecutor.getCursor(mpMotionPhotoClipView.buildSelectQuery(), "getMotionPhotoClipCursor");
    }

    public String tag() {
        return "MotionPhotoClipApi";
    }
}
