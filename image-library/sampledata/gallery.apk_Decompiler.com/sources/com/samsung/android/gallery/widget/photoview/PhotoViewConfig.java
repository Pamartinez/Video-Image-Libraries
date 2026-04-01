package com.samsung.android.gallery.widget.photoview;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PhotoViewConfig {
    static final boolean DEBUG_REGION_DECODING = PocFeatures.isEnabled(PocFeatures.RegionDecodingInfo);
    static final boolean SUPPORT_PPP_BLENDING = Features.isEnabled(Features.SUPPORT_PPP_BLENDING);
    Paint mBitmapPaint;
    boolean mBlockPendingScale;
    boolean mBlockPinchShrink;
    boolean mBlockRegionDecoding;
    boolean mHorizontalDisplayChanged;
    public boolean mInitializedBound;
    boolean mIsKeepCenterCrop;
    boolean mIsUnlimitedDoubleTapZoom;
    boolean mIsViewInitialCenterCrop;
    boolean mIsZoomEnabled = true;
    long mLastDrawnPixels;
    final Matrix mMatrix = new Matrix();
    float mMaxScale;
    int mMinSize;
    int mMinimumTileDpi = -1;
    Float mPendingScale;
    boolean mPhotoHdrCandidate;
    boolean mPhotoHdrSupported;
    int mRoundedCornerRadius;
    PointF mSPendingCenter;
    ScaleAndTranslate mSatTemp;
    boolean mSupportCustomCrop;
    boolean mViewBlockMotionControl;
    boolean mZoomState;
}
