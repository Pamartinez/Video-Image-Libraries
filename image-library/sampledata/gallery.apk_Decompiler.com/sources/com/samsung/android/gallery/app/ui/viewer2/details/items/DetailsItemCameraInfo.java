package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsDataRefiner;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemCameraInfo extends DetailsItem {
    /* access modifiers changed from: private */
    public List<String> mCommonInfo;
    /* access modifiers changed from: private */
    public String mLensInfo;
    /* access modifiers changed from: private */
    public List<String> mMediaInfo;
    /* access modifiers changed from: private */
    public List<String> mTotalInfo = new ArrayList();
    /* access modifiers changed from: private */
    public String mVideoShotType;
    /* access modifiers changed from: private */
    public String mVirtualApertureInfo;

    public DetailsItemCameraInfo(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    private DetailsViewUpdater createFileDataUpdater() {
        return new DetailsViewUpdater() {
            public void refinedItemInternal(MediaItem mediaItem) {
                DetailsItemCameraInfo detailsItemCameraInfo = DetailsItemCameraInfo.this;
                detailsItemCameraInfo.mCommonInfo = detailsItemCameraInfo.parseCommonValue(mediaItem);
                if (mediaItem.isImage()) {
                    DetailsItemCameraInfo detailsItemCameraInfo2 = DetailsItemCameraInfo.this;
                    detailsItemCameraInfo2.mLensInfo = DetailsDataRefiner.getLensInfo(mediaItem, detailsItemCameraInfo2.mEventContext.getContext());
                    DetailsItemCameraInfo detailsItemCameraInfo3 = DetailsItemCameraInfo.this;
                    detailsItemCameraInfo3.mMediaInfo = detailsItemCameraInfo3.parseImageValue(mediaItem);
                    DetailsItemCameraInfo detailsItemCameraInfo4 = DetailsItemCameraInfo.this;
                    detailsItemCameraInfo4.mVirtualApertureInfo = detailsItemCameraInfo4.parseVirtualApertureValue(mediaItem);
                } else if (mediaItem.isVideo()) {
                    DetailsItemCameraInfo.this.mLensInfo = DetailsDataRefiner.getVideoLensInfo(mediaItem);
                    DetailsItemCameraInfo.this.mVideoShotType = DetailsDataRefiner.getVideoShotType(mediaItem);
                    DetailsItemCameraInfo detailsItemCameraInfo5 = DetailsItemCameraInfo.this;
                    detailsItemCameraInfo5.mMediaInfo = detailsItemCameraInfo5.parseVideoValue(mediaItem);
                }
                DetailsItemCameraInfo detailsItemCameraInfo6 = DetailsItemCameraInfo.this;
                detailsItemCameraInfo6.mTotalInfo = detailsItemCameraInfo6.rebuildMetadata(mediaItem);
            }

            public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
                DetailsItemCameraInfo.this.setTextAndVisibility((int) R.id.camera_name, (CharSequence) DetailsDataRefiner.getCameraInfoTitle(mediaItem));
                DetailsItemCameraInfo.this.setMultiColumnTextView(R.id.details_capture_mode_list, R.layout.multi_column_textview_capture_mode, DetailsDataRefiner.getCaptureModeList(mediaItem));
                DetailsItemCameraInfo detailsItemCameraInfo = DetailsItemCameraInfo.this;
                detailsItemCameraInfo.setMultiColumnTextView(R.id.moreinfo_metadata, detailsItemCameraInfo.mTotalInfo);
                DetailsItemCameraInfo.this.updateVirtualApertureInfo();
                DetailsItemCameraInfo.this.updateCameraLayoutPadding();
            }
        };
    }

    private DetailsViewUpdater createFpsDataUpdater() {
        return new DetailsViewUpdater() {
            public void refinedItemInternal(MediaItem mediaItem) {
                if (mediaItem.isVideo()) {
                    DetailsItemCameraInfo detailsItemCameraInfo = DetailsItemCameraInfo.this;
                    detailsItemCameraInfo.mMediaInfo = detailsItemCameraInfo.parseVideoValue(mediaItem);
                    DetailsItemCameraInfo detailsItemCameraInfo2 = DetailsItemCameraInfo.this;
                    detailsItemCameraInfo2.mTotalInfo = detailsItemCameraInfo2.rebuildMetadata(mediaItem);
                }
            }

            public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
                if (mediaItem.isVideo()) {
                    DetailsItemCameraInfo detailsItemCameraInfo = DetailsItemCameraInfo.this;
                    detailsItemCameraInfo.setMultiColumnTextView(R.id.moreinfo_metadata, detailsItemCameraInfo.mTotalInfo);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public List<String> parseCommonValue(MediaItem mediaItem) {
        return Arrays.asList(new String[]{DetailsDataRefiner.getFileSizeString(mediaItem), DetailsDataRefiner.getResolutionString(mediaItem), DetailsDataRefiner.getResolutionSpecString(mediaItem)});
    }

    /* access modifiers changed from: private */
    public List<String> parseImageValue(MediaItem mediaItem) {
        return Arrays.asList(new String[]{DetailsDataRefiner.getIso(mediaItem), DetailsDataRefiner.getFocalLength(mediaItem), DetailsDataRefiner.getExposureBias(mediaItem), DetailsDataRefiner.getAperture(mediaItem), DetailsDataRefiner.getExposureTime(mediaItem), DetailsDataRefiner.getWhiteBalance(mediaItem), DetailsDataRefiner.getFlashOn(mediaItem)});
    }

    /* access modifiers changed from: private */
    public List<String> parseVideoValue(MediaItem mediaItem) {
        return Arrays.asList(new String[]{DetailsDataRefiner.getVideoDuration(mediaItem), DetailsDataRefiner.getVideoCodec(mediaItem), DetailsDataRefiner.getAudioCodec(mediaItem), DetailsDataRefiner.getFrameRateString(mediaItem)});
    }

    /* access modifiers changed from: private */
    public String parseVirtualApertureValue(MediaItem mediaItem) {
        return DetailsDataRefiner.getVirtualAperture(mediaItem);
    }

    /* access modifiers changed from: private */
    public List<String> rebuildMetadata(MediaItem mediaItem) {
        ArrayList arrayList = new ArrayList();
        if (mediaItem.isVideo() && !TextUtils.isEmpty(this.mVideoShotType)) {
            arrayList.add(this.mVideoShotType);
            arrayList.add("\n");
        }
        if (!TextUtils.isEmpty(this.mLensInfo)) {
            arrayList.add(this.mLensInfo);
            arrayList.add("\n");
        }
        List<String> list = this.mCommonInfo;
        if (list != null && !list.isEmpty()) {
            arrayList.addAll(this.mCommonInfo);
            arrayList.add("\n");
        }
        List<String> list2 = this.mMediaInfo;
        if (list2 != null && !list2.isEmpty()) {
            arrayList.addAll(this.mMediaInfo);
        }
        if (!arrayList.isEmpty() && "\n".equals(arrayList.get(arrayList.size() - 1))) {
            arrayList.remove(arrayList.size() - 1);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void updateCameraLayoutPadding() {
        int i2;
        if (!(this.mItemView instanceof ViewStub)) {
            View findViewById = this.mDetailsView.findViewById(R.id.moreinfo_item_edit_btn);
            View findViewById2 = this.mItemView.findViewById(R.id.camera_layout);
            if (supportLargeScreenHorizontalGui()) {
                i2 = ViewUtils.getMeasuredWidth(findViewById);
            } else {
                i2 = 0;
            }
            ViewMarginUtils.setEndPadding(findViewById2, i2);
        }
    }

    /* access modifiers changed from: private */
    public void updateVirtualApertureInfo() {
        String str = this.mVirtualApertureInfo;
        if (str == null || str.isEmpty()) {
            ViewUtils.setVisibility(getView(R.id.virtual_aperture_info_layout), 8);
            return;
        }
        ViewUtils.setVisibility(getView(R.id.virtual_aperture_info_layout), 0);
        ViewUtils.setText((TextView) getView(R.id.virtual_aperture_title), " - " + this.mEventContext.getContext().getString(R.string.shot_with_virtual_aperture));
        ViewUtils.setText((TextView) getView(R.id.virtual_aperture_info), this.mVirtualApertureInfo);
    }

    public int getLayoutId() {
        return R.id.moreinfo_camerainfo;
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.FILE_DATA, createFileDataUpdater());
        this.mViewUpdaterMap.put(DetailsUpdateKey.FPS, createFpsDataUpdater());
    }

    public boolean supportItem(MediaItem mediaItem) {
        return !mediaItem.isSharing();
    }

    public void updateLayout() {
        super.updateLayout();
        updateCameraLayoutPadding();
    }
}
