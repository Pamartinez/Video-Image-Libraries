package com.samsung.android.gallery.app.ui.viewer2.model;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Size;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.xmp.XmpUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelStateHelper {
    private final ContainerModel mModel;

    public ModelStateHelper(ContainerModel containerModel) {
        this.mModel = containerModel;
    }

    private boolean isMotionPreviewEnabledViewMode(MediaItem mediaItem) {
        if (MediaItemUtil.getMotionPhotoViewMode(mediaItem) == MotionPhotoViewMode.ON) {
            return true;
        }
        return false;
    }

    private boolean isSkipMotionPreviewByPresentationTime(MediaItem mediaItem) {
        boolean z = true;
        if (mediaItem != null) {
            long motionPhotoPresentationTime = XmpUtils.getMotionPhotoPresentationTime(mediaItem);
            if (motionPhotoPresentationTime < 0 || motionPhotoPresentationTime >= 30) {
                z = false;
            }
            if (z) {
                Log.d("ModelStateHelper", "skipPreview pt=" + motionPhotoPresentationTime);
            }
        }
        return z;
    }

    public int getBackgroundColorResource() {
        if (!this.mModel.isFlipCoverGallery() || LaunchIntent.isFlipCoverWidgetContentsPickerTheme(this.mModel.getBlackboard())) {
            return R.color.daynight_default_background;
        }
        return R.color.black_color;
    }

    public boolean isContainerResumed() {
        Fragment containerFragment = this.mModel.getContainerFragment();
        if (containerFragment == null || !containerFragment.isResumed()) {
            return false;
        }
        return true;
    }

    public boolean isDecorViewEnabled() {
        if (this.mModel.isSelectMode() || LocationKey.isFromExpand(this.mModel.getLocationKey()) || isForViewing()) {
            return false;
        }
        return true;
    }

    public boolean isEnabledViewPagerTouch() {
        if (LocationKey.isSecondDepthGroupPanelView(this.mModel.getLocationKey())) {
            return !this.mModel.isSelectMode();
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r2v8, types: [androidx.recyclerview.widget.RecyclerView$ViewHolder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isFilmExpanded(int r3) {
        /*
            r2 = this;
            com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel r2 = r2.mModel
            android.view.View r2 = r2.getFilmStripLayout()
            r0 = 0
            if (r2 == 0) goto L_0x0013
            r1 = 2131297221(0x7f0903c5, float:1.821238E38)
            android.view.View r2 = r2.findViewById(r1)
            com.samsung.android.gallery.widget.filmstrip3.FilmStripView r2 = (com.samsung.android.gallery.widget.filmstrip3.FilmStripView) r2
            goto L_0x0014
        L_0x0013:
            r2 = r0
        L_0x0014:
            if (r2 == 0) goto L_0x001d
            androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r2.findViewHolderForAdapterPosition(r3)
            r0 = r2
            com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder r0 = (com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder) r0
        L_0x001d:
            if (r0 == 0) goto L_0x0027
            boolean r2 = r0.isExpanded()
            if (r2 == 0) goto L_0x0027
            r2 = 1
            return r2
        L_0x0027:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.model.ModelStateHelper.isFilmExpanded(int):boolean");
    }

    public boolean isFoldMainScreen() {
        Resources resources;
        Activity activity = this.mModel.getActivity();
        Configuration configuration = null;
        if (activity != null) {
            resources = activity.getResources();
        } else {
            resources = null;
        }
        if (resources != null) {
            configuration = resources.getConfiguration();
        }
        if (!Features.isEnabled(Features.IS_FOLDABLE_TYPE_FOLD) || !SeApiCompat.isMainScreen(configuration)) {
            return false;
        }
        return true;
    }

    public boolean isForViewing() {
        LaunchIntent launchIntent;
        boolean z;
        ContainerModel containerModel = this.mModel;
        if (containerModel.mIsForViewing == null) {
            Blackboard blackboard = containerModel.getBlackboard();
            if (blackboard != null) {
                launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
            } else {
                launchIntent = null;
            }
            ContainerModel containerModel2 = this.mModel;
            if (launchIntent == null || (!launchIntent.isFromSmartManager() && !launchIntent.isFromCrossPicker())) {
                z = false;
            } else {
                z = true;
            }
            containerModel2.mIsForViewing = Boolean.valueOf(z);
        }
        return this.mModel.mIsForViewing.booleanValue();
    }

    public boolean isForcePlayVideoInGallery() {
        return ArgumentsUtil.getArgValue(this.mModel.getLocationKey(), "forcePlayVideoInGallery", false);
    }

    public boolean isFromCamera() {
        return LaunchIntent.isFromCamera(this.mModel.getBlackboard());
    }

    public boolean isFromList() {
        boolean z;
        if (LocationKey.isTempList(this.mModel.getLocationKey()) || LocationKey.isTempFile(this.mModel.getLocationKey())) {
            z = true;
        } else {
            z = false;
        }
        if (isQuickView() || z || isQuickCropQuickView()) {
            return false;
        }
        return true;
    }

    public boolean isFullScreenInFlipCover(MediaItem mediaItem, Size size) {
        float width;
        int height;
        if (SdkConfig.atLeast(SdkConfig.SEM.B)) {
            Rect cutouts = this.mModel.getCutouts();
            int dimensionPixelOffset = ResourceCompat.getDimensionPixelOffset(this.mModel.getActivity(), R.dimen.viewer_flip_cover_fullscreen_cutout_standard, 0);
            if (cutouts.left > dimensionPixelOffset || cutouts.right > dimensionPixelOffset || cutouts.top > dimensionPixelOffset || cutouts.bottom > dimensionPixelOffset) {
                return true;
            }
        }
        if (mediaItem != null) {
            if (mediaItem.getOrientation() == 90 || mediaItem.getOrientation() == 270) {
                width = (float) mediaItem.getWidth();
                height = mediaItem.getHeight();
            } else {
                width = (float) mediaItem.getHeight();
                height = mediaItem.getWidth();
            }
            float f = width / ((float) height);
            if (((double) (Math.abs(f - (((float) size.getHeight()) / ((float) size.getWidth()))) / f)) < 0.01d) {
                return true;
            }
        }
        return false;
    }

    public boolean isGroupItemLoading() {
        ContentModel contentModel = this.mModel.getContentModel();
        if (contentModel == null || !contentModel.isGroupItemLoading()) {
            return false;
        }
        return true;
    }

    public boolean isLargeScreen() {
        if (isFoldMainScreen() || this.mModel.getSystemUi().isTabletDpi()) {
            return true;
        }
        return false;
    }

    public boolean isMotionPreviewEnabled(ContentModel contentModel) {
        MediaItem mediaItem = contentModel.getMediaItem();
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.AutoPlayMotionPhoto) || Features.isEnabled(Features.IS_SEP_LITE) || mediaItem == null || contentModel.isFirstView() || !SheetBehaviorCompat.isClosed(contentModel.getContainerModel().getDetailsState()) || contentModel.getContainerModel().isRemoteConnected() || RemoteDisplayState.getInstance().isConnected()) {
            return false;
        }
        if ((mediaItem.isLocal() || (mediaItem.isCloudOnly() && MediaItemUtil.supportCloudPreviewPlay(mediaItem))) && !contentModel.getContainerModel().isAppTransitionRunning() && !isSkipMotionPreviewByPresentationTime(contentModel.getMediaItem()) && !TrashData.isTrash(mediaItem) && isMotionPreviewEnabledViewMode(mediaItem) && contentModel.isFragmentResumed() && !isFilmExpanded(contentModel.getPosition())) {
            return true;
        }
        return false;
    }

    public boolean isQuickCropQuickView() {
        if (ArgumentsUtil.getArgValue(this.mModel.getLocationKey(), "quickCropId", -1) != -1) {
            return true;
        }
        return false;
    }

    public boolean isQuickView() {
        if (ArgumentsUtil.getArgValue(this.mModel.getLocationKey(), "quick_view", false) || LocationKey.isQuickView(this.mModel.getLocationKey())) {
            return true;
        }
        return false;
    }

    public boolean isQuickViewShrink() {
        if (!isFromCamera() || !BlackboardUtils.isViewerShrinkToCamera(this.mModel.getBlackboard())) {
            return false;
        }
        return true;
    }

    public boolean isRevitalization() {
        return LocationKey.isRevitalizationView(this.mModel.getLocationKey());
    }

    public boolean isTargetViewHalfOfWidth(boolean z) {
        Activity activity = this.mModel.getActivity();
        if (activity == null || supportDetailsLargeScreen()) {
            return false;
        }
        Resources resources = activity.getResources();
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_TABLE_MODE);
        boolean z3 = resources.getBoolean(R.bool.isTabletDpi);
        if (!z || (!isEnabled && !z3 && !SystemUi.isInMultiWindowMode(activity))) {
            return false;
        }
        return true;
    }

    public boolean isTransitionToList() {
        if (!isFromList() || !LocationKey.isPictures(this.mModel.getLocationKey()) || ArgumentsUtil.getArgValue(this.mModel.getLocationKey(), "from_viewer", false) || ArgumentsUtil.getArgValue(this.mModel.getLocationKey(), "forceDisableReturnTransition", false)) {
            return false;
        }
        return true;
    }

    public boolean supportDetails() {
        if (this.mModel.isSelectMode() || LocationKey.isFromExpand(this.mModel.getLocationKey()) || isForViewing() || LocationKey.isTempFile(this.mModel.getLocationKey())) {
            return false;
        }
        return true;
    }

    public boolean supportDetailsLandscapeMode() {
        boolean z;
        if (LocationKey.isSecondDepthGroupPanelView(this.mModel.getLocationKey())) {
            return supportGroupPanelLandscapeMode();
        }
        Activity activity = this.mModel.getActivity();
        if (activity != null) {
            z = activity.getResources().getBoolean(R.bool.supportDetailsLandScapeMode);
        } else {
            z = true;
        }
        if (this.mModel.getSystemUi().isPortrait() || !z || this.mModel.isTableMode()) {
            return false;
        }
        return true;
    }

    public boolean supportDetailsLargeScreen() {
        if (SystemUi.isInMultiWindowMode(this.mModel.getActivity()) || !isLargeScreen() || supportGroupPanelLandscapeMode()) {
            return false;
        }
        return true;
    }

    public boolean supportEditDetailsFitsToDetails() {
        return supportDetailsLargeScreen();
    }

    public boolean supportGroupPanelLandscapeMode() {
        if (!LocationKey.isSecondDepthGroupPanelView(this.mModel.getLocationKey()) || this.mModel.getSystemUi().isPortrait() || SystemUi.isInMultiWindowMode(this.mModel.getActivity()) || this.mModel.isTableMode()) {
            return false;
        }
        return true;
    }

    public boolean supportInstantSlowMoPlay(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO || !InstantSlowMoUtils.supportInstantSlowMoPlay(mediaItem, true) || SystemUi.isInMultiWindowMode(this.mModel.getActivity()) || FoldUtils.isFlipCoverScreen(this.mModel.getActivity())) {
            return false;
        }
        return true;
    }

    public boolean supportPinchShrink() {
        ContainerModel containerModel = this.mModel;
        if (!containerModel.mSupportPinchShrink || containerModel.isTableMode() || LocationKey.isAllDayTimeLapse(this.mModel.getLocationKey()) || LocationKey.isLongExposure(this.mModel.getLocationKey()) || this.mModel.getSystemUi().isFromLockScreen() || LocationKey.isFromHoverView(this.mModel.getLocationKey())) {
            return false;
        }
        return true;
    }
}
