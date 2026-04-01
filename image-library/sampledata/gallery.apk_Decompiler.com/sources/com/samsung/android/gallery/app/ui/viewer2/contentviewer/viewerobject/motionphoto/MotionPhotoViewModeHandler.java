package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto;

import A4.C0375j;
import Da.f;
import F7.c;
import F7.d;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSpinner;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoViewModeHandler extends ViewerObject implements FragmentLifeCycle {
    /* access modifiers changed from: private */
    public MotionPhotoViewMode mLastSelectedViewMode;
    private IMediaPlayerView mMediaPlayerView;
    private View mViewModeLayout;
    private AppCompatSpinner mViewModeSpinner;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SpinnerListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewModeAdapter extends ArrayAdapter<MotionPhotoViewMode> {
        private final SpinnerListener mListener;
        private final AppCompatSpinner mSpinner;

        public ViewModeAdapter(Context context, AppCompatSpinner appCompatSpinner, List<MotionPhotoViewMode> list, SpinnerListener spinnerListener) {
            super(context, R.layout.motion_photo_view_mode_item, list);
            this.mSpinner = appCompatSpinner;
            this.mListener = spinnerListener;
        }

        private View getViewInternal(int i2, int i7, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(i2, viewGroup, false);
            }
            MotionPhotoViewMode motionPhotoViewMode = (MotionPhotoViewMode) getItem(i7);
            ImageView imageView = (ImageView) view.findViewById(R.id.type_icon);
            imageView.setImageResource(motionPhotoViewMode.getIconResId());
            TextView textView = (TextView) view.findViewById(R.id.title);
            textView.setText(view.getResources().getString(motionPhotoViewMode.getTitleResId()));
            textView.setTextColor(getContext().getColor(R.color.motion_photo_view_mode_text_color));
            Optional.ofNullable(imageView.getDrawable()).ifPresent(new b(this));
            return view;
        }

        /* access modifiers changed from: private */
        public void lambda$getDropDownView$0(int i2, View view) {
            ((a) this.mListener).f2579a.lambda$initSpinner$3(view, i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$getViewInternal$3(Drawable drawable) {
            drawable.setTint(getContext().getColor(R.color.motion_photo_view_mode_icon_color));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateDropDownViewBasedOnSelect$1(boolean z, Drawable drawable) {
            int i2;
            Context context = getContext();
            if (z) {
                i2 = R.color.motion_photo_view_mode_selected_color;
            } else {
                i2 = R.color.motion_photo_view_mode_icon_color;
            }
            drawable.setTint(context.getColor(i2));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$updateDropDownViewBasedOnSelect$2(boolean z, Drawable drawable) {
            int i2;
            Context context = getContext();
            if (z) {
                i2 = R.color.motion_photo_view_mode_selected_color;
            } else {
                i2 = R.color.motion_photo_view_mode_icon_color;
            }
            drawable.setTint(context.getColor(i2));
        }

        private void updateDropDownViewBasedOnSelect(int i2, View view) {
            boolean z;
            int i7;
            MotionPhotoViewMode motionPhotoViewMode = (MotionPhotoViewMode) getItem(i2);
            ImageView imageView = (ImageView) view.findViewById(R.id.type_icon);
            TextView textView = (TextView) view.findViewById(R.id.title);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.check);
            int i8 = 0;
            if (motionPhotoViewMode == this.mSpinner.getSelectedItem()) {
                z = true;
            } else {
                z = false;
            }
            Context context = getContext();
            if (z) {
                i7 = R.color.motion_photo_view_mode_selected_color;
            } else {
                i7 = R.color.motion_photo_view_mode_text_color;
            }
            textView.setTextColor(context.getColor(i7));
            Optional.ofNullable(imageView.getDrawable()).ifPresent(new d(this, z, 0));
            Optional.ofNullable(imageView2.getDrawable()).ifPresent(new d(this, z, 1));
            if (!z) {
                i8 = 8;
            }
            ViewUtils.setVisibility(imageView2, i8);
        }

        public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
            View viewInternal = getViewInternal(R.layout.motion_photo_view_mode_dropdown_item, i2, (View) null, viewGroup);
            viewInternal.setOnClickListener(new c(this, i2));
            setBackgroundFromAttribute(viewInternal, 16843504);
            updateDropDownViewBasedOnSelect(i2, viewInternal);
            return viewInternal;
        }

        public View getView(int i2, View view, ViewGroup viewGroup) {
            return getViewInternal(R.layout.motion_photo_view_mode_item, i2, view, viewGroup);
        }

        public void setBackgroundFromAttribute(View view, int i2) {
            if (view != null) {
                TypedValue typedValue = new TypedValue();
                view.getContext().getTheme().resolveAttribute(i2, typedValue, true);
                int i7 = typedValue.resourceId;
                if (i7 != 0) {
                    view.setBackgroundResource(i7);
                } else {
                    view.setBackgroundColor(typedValue.data);
                }
            }
        }
    }

    private List<MotionPhotoViewMode> getModes() {
        return (List) Arrays.stream(MotionPhotoViewMode.values()).filter(new C0375j(24)).collect(Collectors.toList());
    }

    private void inflate(MediaItem mediaItem) {
        if (support(mediaItem)) {
            this.mActionInvoker.invoke(ViewerAction.MOTION_PHOTO_VIEW_MODE_VIEW_INFLATE, new Object[0]);
            setInitialViewMode();
            return;
        }
        ViewUtils.setVisibility(this.mViewModeLayout, 8);
    }

    private void initSpinner() {
        if (this.mViewModeSpinner == null) {
            this.mViewModeSpinner = (AppCompatSpinner) this.mViewModeLayout.findViewById(R.id.view_mode_spinner);
        }
        if (this.mViewModeSpinner != null) {
            ViewModeAdapter viewModeAdapter = new ViewModeAdapter(this.mModel.getContext(), this.mViewModeSpinner, getModes(), new a(this));
            this.mViewModeSpinner.seslSetDropDownGravity(8388613);
            this.mViewModeSpinner.setAdapter((SpinnerAdapter) viewModeAdapter);
            this.mViewModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
                    StringCompat access$000 = MotionPhotoViewModeHandler.this.TAG;
                    StringBuilder o2 = C0086a.o(i2, "spinner onItemSelected : ", ArcCommonLog.TAG_COMMA);
                    o2.append(MotionPhotoViewModeHandler.this.mLastSelectedViewMode);
                    Log.d(access$000, o2.toString());
                    MotionPhotoViewModeHandler.this.onViewModeSelected(i2);
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            setSpinnerBackground();
            ViewUtils.setVisibility(this.mViewModeLayout, 0);
        }
    }

    private boolean isAutoPlayMode(MotionPhotoViewMode motionPhotoViewMode) {
        if (motionPhotoViewMode == MotionPhotoViewMode.BOOMERANG || motionPhotoViewMode == MotionPhotoViewMode.SLOW_MO) {
            return true;
        }
        return false;
    }

    private static boolean isBlurEnabled() {
        if (Features.isEnabled(Features.IS_THEME_INSTALLED) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY) || !Features.isEnabled(Features.SUPPORT_REALTIME_BLUR)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        if (MediaItemUtil.isMotionPhotoAutoPlayViewMode(this.mModel.getMediaItem())) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_STOP, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSpinner$3(View view, int i2) {
        AppCompatSpinner appCompatSpinner = this.mViewModeSpinner;
        if (appCompatSpinner != null) {
            appCompatSpinner.setSelection(i2);
            this.mViewModeSpinner.seslDismissPopup();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageSelected$2() {
        this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_START, new Object[0]);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onViewModeSelected$4(MediaItem mediaItem, MotionPhotoViewMode motionPhotoViewMode) {
        if (mediaItem.isPrivateItem()) {
            PrivateDatabase.getInstance().updateMpViewMode(mediaItem.getFileId(), motionPhotoViewMode.ordinal());
        } else {
            FilesApi.Cache.updateMotionPhotoViewMode(AppResources.getAppContext(), mediaItem.getFileId(), motionPhotoViewMode.ordinal());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewModeSelected$5() {
        this.mModel.setVideoSeekPosition(0);
        this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_START, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onViewModeSelected(int i2) {
        MotionPhotoViewMode motionPhotoViewMode = (MotionPhotoViewMode) this.mViewModeSpinner.getAdapter().getItem(i2);
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null && motionPhotoViewMode != this.mLastSelectedViewMode) {
            DetailsData of2 = DetailsData.of(mediaItem);
            of2.motionPhotoViewMode = motionPhotoViewMode;
            of2.motionPhotoPlaybacks = null;
            SimpleThreadPool.getInstance().execute(new f(10, mediaItem, motionPhotoViewMode));
            postAnalyticsLog(AnalyticsScreenId.SCREEN_DETAIL_VIEW_MOTION_PHOTO, AnalyticsEventId.EVENT_MOTION_PHOTO_VIEW_MODE_SELECT, motionPhotoViewMode.getAnalyticsDetail());
            if (this.mMediaPlayerView.isInPlayState()) {
                this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_STOP, new Object[0]);
            }
            this.mActionInvoker.invoke(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, motionPhotoViewMode.getPlayViewer(), Integer.valueOf(this.mModel.getPosition()));
            this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
            if (isAutoPlayMode(motionPhotoViewMode)) {
                ThreadUtil.postOnBgThreadDelayed(new d(this, 1), 500);
            }
            this.mLastSelectedViewMode = motionPhotoViewMode;
        }
    }

    /* access modifiers changed from: private */
    public void onViewReady(Object... objArr) {
        this.mViewModeLayout = objArr[0];
        initSpinner();
    }

    private void setInitialViewMode() {
        if (this.mViewModeSpinner == null) {
            initSpinner();
        }
        if (this.mViewModeSpinner != null && this.mModel.getMediaItem() != null) {
            MotionPhotoViewMode motionPhotoViewMode = MediaItemUtil.getMotionPhotoViewMode(this.mModel.getMediaItem());
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "setInitialViewMode : " + motionPhotoViewMode + ArcCommonLog.TAG_COMMA + this.mViewModeSpinner.getSelectedItem() + ArcCommonLog.TAG_COMMA + this.mLastSelectedViewMode);
            if (this.mViewModeSpinner.getSelectedItem() != motionPhotoViewMode) {
                this.mViewModeSpinner.setSelection(((ViewModeAdapter) this.mViewModeSpinner.getAdapter()).getPosition(motionPhotoViewMode));
            }
            this.mLastSelectedViewMode = motionPhotoViewMode;
            if (motionPhotoViewMode != MotionPhotoViewMode.ON) {
                this.mActionInvoker.invoke(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, motionPhotoViewMode.getPlayViewer(), Integer.valueOf(this.mModel.getPosition()));
            }
        }
    }

    private void setSpinnerBackground() {
        if (this.mViewModeSpinner != null && !isBlurEnabled()) {
            this.mViewModeSpinner.setPopupBackgroundResource(R.drawable.motion_photo_view_mode_popup_bg);
        }
    }

    private boolean support(MediaItem mediaItem) {
        if (mediaItem == null || !supportCloudOnly(mediaItem) || mediaItem.isSharing() || mediaItem.isDrm()) {
            return false;
        }
        return true;
    }

    private boolean supportCloudOnly(MediaItem mediaItem) {
        if (mediaItem.isLocal()) {
            return true;
        }
        if (!mediaItem.isCloudOnly() || !MediaItemUtil.supportCloudPreviewPlay(mediaItem)) {
            return false;
        }
        return true;
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new c(this, 0));
        this.mActionInvoker.add(ViewerAction.MOTION_PHOTO_VIEW_MODE_VIEW, new c(this, 1));
        this.mActionInvoker.add(ViewerAction.CLOSE_MOTION_PHOTO, new c(this, 2));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (this.mViewModeSpinner == null) {
            inflate(mediaItem);
        }
    }

    public void onPageSelected() {
        super.onPageSelected();
        if (!this.mMediaPlayerView.isInPlayState() && isAutoPlayMode(this.mLastSelectedViewMode)) {
            ThreadUtil.postOnBgThreadDelayed(new d(this, 0), 300);
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        inflate(this.mModel.getMediaItem());
    }

    public void onViewRecycled() {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onViewRecycled : " + this.mLastSelectedViewMode);
        super.onViewRecycled();
        AppCompatSpinner appCompatSpinner = this.mViewModeSpinner;
        if (appCompatSpinner != null) {
            appCompatSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
            this.mViewModeSpinner = null;
        }
        this.mLastSelectedViewMode = null;
    }
}
