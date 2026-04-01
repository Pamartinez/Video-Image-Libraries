package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ a(MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mediaItem;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((QueryParams) obj).setFileId(this.e.getFileId()).setDynamicViewingInfo(true);
                return;
            case 1:
                ((QueryParams) obj).setGroupTypes(GroupType.SIMILAR).setGroupMediaFilter(this.e.getAlbumID(), this.e.getGroupMediaId());
                return;
            case 2:
                ((QueryParams) obj).setGroupTypes(GroupType.SINGLE_TAKEN).setGroupMediaFilter(this.e.getAlbumID(), this.e.getGroupMediaId());
                return;
            case 3:
                this.e.mShotMode = ((ShotMode) obj).type;
                return;
            case 4:
                this.e.mShotMode = ((ShotMode) obj).type;
                return;
            case 5:
                this.e.mShotMode = ((ShotMode) obj).type;
                return;
            case 6:
                this.e.setExtra(ExtrasID.TOTAL_SMART_CROP_RATIO, (String) obj);
                return;
            case 7:
                this.e.setExtra(ExtrasID.TOTAL_SMART_CROP_DEVICE_RATIO, (String) obj);
                return;
            case 8:
                this.e.setExtra(ExtrasID.SPACE_COVER_RECT_RATIO, (String) obj);
                return;
            case 9:
                SharingMediaItemBuilder.lambda$applyItem$1(this.e, (Integer) obj);
                return;
            case 10:
                this.e.setVideoColorFormat(((Integer) obj).intValue());
                return;
            case 11:
                SharingMediaItemBuilder.lambda$applyItem$2(this.e, (Integer) obj);
                return;
            case 12:
                this.e.mOrientation = ((Integer) obj).intValue();
                return;
            case 13:
                this.e.mOrientationTag = ((Integer) obj).intValue();
                return;
            case 14:
                this.e.mFileSize = ((Long) obj).longValue();
                return;
            case 15:
                this.e.mLatitude = ((Double) obj).doubleValue();
                return;
            case 16:
                this.e.mShotMode = ((ShotMode) obj).type;
                return;
            case 17:
                this.e.mLongitude = ((Double) obj).doubleValue();
                return;
            case 18:
                this.e.mSefFileType = ((Integer) obj).intValue();
                return;
            case 19:
                this.e.mSefFileSubType = ((Integer) obj).intValue();
                return;
            case 20:
                this.e.mDateTaken = ((Long) obj).longValue();
                return;
            case 21:
                this.e.mGroupType = ((Integer) obj).intValue();
                return;
            case 22:
                this.e.mRecordingMode = ((Integer) obj).intValue();
                return;
            case 23:
                this.e.m360Video = ((Boolean) obj).booleanValue();
                return;
            case 24:
                this.e.mFileDuration = ((Integer) obj).intValue();
                return;
            case 25:
                this.e.mPath = (String) obj;
                return;
            case 26:
                this.e.mCropRectRatioList = new ArrayList<>((ArrayList) obj);
                return;
            default:
                this.e.mSmartCropRectRatio = (RectF) obj;
                return;
        }
    }
}
