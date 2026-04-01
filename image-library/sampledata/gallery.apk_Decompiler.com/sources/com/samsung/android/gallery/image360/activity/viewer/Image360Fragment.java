package com.samsung.android.gallery.image360.activity.viewer;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.image360.R$drawable;
import com.samsung.android.gallery.image360.R$id;
import com.samsung.android.gallery.image360.R$layout;
import com.samsung.android.gallery.image360.R$menu;
import com.samsung.android.gallery.image360.R$string;
import com.samsung.android.gallery.image360.activity.abstraction.IActivityView;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import com.samsung.android.gallery.image360.engine.view.Gallery360ViewerImpl;
import com.samsung.android.gallery.image360.systemui.BaseSystemUi;
import com.samsung.android.gallery.image360.systemui.SystemUiBos;
import com.samsung.android.gallery.image360.systemui.SystemUiLegacy;
import com.samsung.android.gallery.image360.widget.IImage360FastOptionView;
import com.samsung.android.gallery.image360.widget.Image360FastOptionView;
import com.samsung.android.gallery.image360.widget.Image360FastOptionViewListener;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import o5.C0496a;
import t0.a;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Image360Fragment extends Fragment implements IImage360View, View.OnTouchListener, Image360FastOptionViewListener {
    private IGallery360Viewer m360Viewer;
    private ImageView mAutoPlay;
    private boolean mAutoPlayEnabled = true;
    private final Consumer<Bitmap> mBitmapReceivedListener = new Consumer<Bitmap>() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$accept$0(Bitmap bitmap) {
            FragmentActivity activity = Image360Fragment.this.getActivity();
            if (activity != null) {
                ((ViewStub) activity.findViewById(R$id.bitmap_transition_stub)).inflate();
                ((ImageView) activity.findViewById(R$id.image_360_view)).setImageBitmap(bitmap);
                ((RelativeLayout) activity.findViewById(R$id.image360_holder)).setVisibility(8);
                Image360Fragment.this.mPresenter.launchPlaybackOptionsView();
            }
        }

        public void accept(Bitmap bitmap) {
            ThreadUtil.runOnUiThread(new a(this, bitmap));
        }
    };
    private final Callable<Void> mErrorListener = new a(1, this);
    private Image360FastOptionView mFastOptionLayout = null;
    private GestureDetector mGestureDetector;
    private boolean mIsDestroyed = false;
    private boolean mIsSensorEnabled;
    /* access modifiers changed from: private */
    public Image360Presenter mPresenter;
    private final BaseSystemUi mSystemUi;
    private Toolbar mToolbar;
    private View mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        public /* synthetic */ GestureListener(Image360Fragment image360Fragment, int i2) {
            this();
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            boolean j2 = Image360Fragment.this.isFastOptionLayoutVisible();
            Image360Fragment image360Fragment = Image360Fragment.this;
            if (j2) {
                return image360Fragment.hideBars();
            }
            return image360Fragment.showBars();
        }

        private GestureListener() {
        }
    }

    public Image360Fragment() {
        BaseSystemUi baseSystemUi;
        if (SdkConfig.atLeast(SdkConfig.GED.B)) {
            baseSystemUi = new SystemUiBos();
        } else {
            baseSystemUi = new SystemUiLegacy();
        }
        this.mSystemUi = baseSystemUi;
        Log.d("Image360Fragment", "construct : " + hashCode());
    }

    private void applyDisplayCutOutPaddingForToolbar(View view, DisplayCutout displayCutout, boolean z) {
        boolean z3;
        if (displayCutout == null || displayCutout.getBoundingRects().isEmpty()) {
            view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
            return;
        }
        Rect rect = displayCutout.getBoundingRects().get(0);
        boolean z7 = true;
        if (rect.left != 0 || rect.width() <= 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (!z && (rect.top >= 10 || rect.height() <= 0)) {
            z7 = false;
        }
        if (z3 && z7) {
            view.setPadding(displayCutout.getSafeInsetLeft(), view.getPaddingTop(), 0, view.getPaddingBottom());
        } else if (z3 || !z7) {
            view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
        } else {
            view.setPadding(0, view.getPaddingTop(), displayCutout.getSafeInsetRight(), view.getPaddingBottom());
        }
    }

    private IActivityView getActivityView() {
        return (IActivityView) getActivity();
    }

    private IGallery360Viewer.ViewMode getCurrentViewMode() {
        return this.mPresenter.getCurrentViewMode();
    }

    private void handleConfigurationChange(Configuration configuration) {
        if (configuration == null) {
            Log.e("Image360Fragment", "handleConfigurationChange null Configuration");
            return;
        }
        this.mSystemUi.setOrientation(configuration.orientation);
        Image360FastOptionView image360FastOptionView = this.mFastOptionLayout;
        if (image360FastOptionView != null) {
            image360FastOptionView.configurationChanged();
        }
    }

    /* access modifiers changed from: private */
    public boolean hideBars() {
        this.mSystemUi.hideNavigationBar(getActivity());
        hideDecorView();
        return true;
    }

    private void hideDecorView() {
        Toolbar toolbar = this.mToolbar;
        if (toolbar != null) {
            toolbar.setVisibility(8);
        }
        ImageView imageView = this.mAutoPlay;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        Image360FastOptionView image360FastOptionView = this.mFastOptionLayout;
        if (image360FastOptionView != null) {
            image360FastOptionView.setVisibleViewIconContainer(false);
            this.mFastOptionLayout.setVisibility(8);
        }
    }

    private void initAutoPlay(View view) {
        if (getContext() != null) {
            this.mAutoPlay = (ImageView) view.findViewById(R$id.auto_play);
            if (!this.mAutoPlayEnabled) {
                this.m360Viewer.enableAutoPlay(false);
                this.mAutoPlay.setImageDrawable(getContext().getDrawable(R$drawable.gallery_ic_photoviewer_360play));
            }
            this.mAutoPlay.setOnClickListener(new C0496a(26, this));
        }
    }

    private void initFastOptionLayout(View view) {
        Image360FastOptionView image360FastOptionView = (Image360FastOptionView) view.findViewById(R$id.image_360_fast_option_view);
        this.mFastOptionLayout = image360FastOptionView;
        image360FastOptionView.setVisibleMotionView(this.mPresenter.isSensorSupported());
        this.mFastOptionLayout.updateModeSelector(getCurrentViewMode().ordinal());
        this.mFastOptionLayout.setVisibleSelfieViews(this.mPresenter.isSelfie360());
        this.mFastOptionLayout.setListener(this);
    }

    private void initIntentExtraValues() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mPresenter.setContentInfo(activity.getIntent().getExtras());
        }
    }

    private void initMainViewer(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R$id.image360_holder);
        relativeLayout.setOnTouchListener(this);
        this.mGestureDetector = new GestureDetector(getContext(), new GestureListener(this, 0));
        Gallery360ViewerImpl gallery360ViewerImpl = new Gallery360ViewerImpl(getContext(), this.mPresenter.isSelfie360(), this.mPresenter.getWidth(), this.mPresenter.getHeight());
        this.m360Viewer = gallery360ViewerImpl;
        gallery360ViewerImpl.setErrorListener(this.mErrorListener);
        this.m360Viewer.resetView(this.mPresenter.getPlayBackDirection().ordinal());
        relativeLayout.addView(this.m360Viewer.getView());
        this.m360Viewer.setViewMode(getCurrentViewMode());
        this.mView = this.m360Viewer.getView();
    }

    private void initToolbar(View view) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        Toolbar toolbar = (Toolbar) view.findViewById(R$id.toolbar);
        this.mToolbar = toolbar;
        toolbar.setNavigationIcon(R$drawable.tw_ic_ab_back_mtrl_detailview);
        if (getContext() != null) {
            this.mToolbar.setOverflowIcon(getContext().getDrawable(R$drawable.gallery_ic_ab_more));
        }
        this.mToolbar.setTitle((CharSequence) "");
        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(this.mToolbar);
            this.mToolbar.bringToFront();
        }
    }

    private void initValues(Bundle bundle) {
        initIntentExtraValues();
        initViewerInfo(bundle);
    }

    private void initViewerInfo(Bundle bundle) {
        if (bundle != null) {
            this.mPresenter.initViewerInfo(bundle.getInt("current_view_mode"), bundle.getInt("playback_direction_visibility"), bundle.getBoolean("sensor_state"));
            this.mAutoPlayEnabled = bundle.getBoolean("auto_play_state");
        } else {
            this.mPresenter.initViewerInfo();
        }
        this.mIsSensorEnabled = this.mPresenter.isSensorSupported();
    }

    private void initViews(View view) {
        initToolbar(view);
        initMainViewer(view);
        initAutoPlay(view);
        initFastOptionLayout(view);
    }

    /* access modifiers changed from: private */
    public boolean isFastOptionLayoutVisible() {
        if (this.mFastOptionLayout.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initAutoPlay$2(View view) {
        toggleAutoPlay();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (!this.mIsDestroyed) {
            this.mIsDestroyed = true;
            finishWithToast(R$string.unknown_error_description);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$new$1() {
        ThreadUtil.postOnUiThread(new e(20, this));
        return null;
    }

    /* access modifiers changed from: private */
    public WindowInsets onApplyWindowInsetsMainLayout(View view, WindowInsets windowInsets) {
        view.onApplyWindowInsets(windowInsets);
        if (SdkConfig.atLeast(SdkConfig.GED.B)) {
            this.mPresenter.initSystemBar(windowInsets);
            this.mSystemUi.setRootView(view);
            this.mSystemUi.onApplyInsets(this.mPresenter.getStatusBarInsets(), this.mPresenter.getNavigationBarInsets());
        }
        return windowInsets;
    }

    /* access modifiers changed from: private */
    public WindowInsets onApplyWindowInsetsToolParent(View view, WindowInsets windowInsets) {
        view.onApplyWindowInsets(windowInsets);
        if (SdkConfig.atLeast(SdkConfig.GED.P)) {
            updatePaddingForDisplayCutOut(view, windowInsets, false);
        }
        return windowInsets;
    }

    private void onPlaybackOptionsSelected() {
        if (this.mPresenter.isSensorSupported()) {
            stopAutoRotation();
        }
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer != null) {
            iGallery360Viewer.getBitmap(this.mBitmapReceivedListener);
        }
    }

    private void setAutoPlayVisibility(boolean z) {
        int i2;
        ImageView imageView = this.mAutoPlay;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageView.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public boolean showBars() {
        this.mSystemUi.showNavigationBar(getActivity());
        showDecorView();
        return true;
    }

    private void showDecorView() {
        boolean z;
        Toolbar toolbar = this.mToolbar;
        if (toolbar != null) {
            toolbar.setVisibility(0);
        }
        if (this.mAutoPlay != null) {
            Image360FastOptionView image360FastOptionView = this.mFastOptionLayout;
            if (image360FastOptionView == null || !image360FastOptionView.isViewIconContainerVisible()) {
                z = true;
            } else {
                z = false;
            }
            setAutoPlayVisibility(z);
        }
        Image360FastOptionView image360FastOptionView2 = this.mFastOptionLayout;
        if (image360FastOptionView2 != null) {
            image360FastOptionView2.setVisibility(0);
        }
    }

    private void toggleAutoPlay() {
        IGallery360Viewer iGallery360Viewer;
        int i2;
        int i7;
        if (getContext() != null && (iGallery360Viewer = this.m360Viewer) != null) {
            boolean z = !this.mAutoPlayEnabled;
            this.mAutoPlayEnabled = z;
            iGallery360Viewer.enableAutoPlay(z);
            if (this.mAutoPlayEnabled) {
                i2 = R$drawable.gallery_ic_photoviewer_360pause;
            } else {
                i2 = R$drawable.gallery_ic_photoviewer_360play;
            }
            this.mAutoPlay.setImageDrawable(getContext().getDrawable(i2));
            if (this.mAutoPlayEnabled) {
                i7 = R$string.gallery360viewer_pause;
            } else {
                i7 = R$string.gallery360viewer_play;
            }
            this.mAutoPlay.setContentDescription(getContext().getString(i7));
        }
    }

    private void updateMotionViewStatus() {
        if (this.mIsSensorEnabled) {
            this.mFastOptionLayout.setEnableMotionView(true);
            startAutoRotation();
            return;
        }
        this.mFastOptionLayout.setEnableMotionView(false);
        stopAutoRotation();
    }

    private void updatePaddingForDisplayCutOut(View view, WindowInsets windowInsets, boolean z) {
        DisplayCutout displayCutout;
        if (windowInsets != null) {
            displayCutout = windowInsets.getDisplayCutout();
        } else {
            displayCutout = null;
        }
        if (displayCutout != null) {
            if (displayCutout.getSafeInsetLeft() == view.getPaddingLeft()) {
                view.setPadding(0, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
            }
            if (displayCutout.getSafeInsetRight() == view.getPaddingRight()) {
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), 0, view.getPaddingBottom());
            }
        }
        Toolbar toolbar = this.mToolbar;
        if (toolbar != null) {
            applyDisplayCutOutPaddingForToolbar(toolbar, displayCutout, z);
        }
    }

    public void commitPlayBackFragment(int i2) {
        IActivityView activityView = getActivityView();
        if (activityView != null) {
            activityView.commitPlayBackFragment(i2);
        }
    }

    public void finishWithToast(int i2) {
        IActivityView activityView = getActivityView();
        if (activityView != null) {
            activityView.finishWithToast(i2);
        }
    }

    public IImage360FastOptionView getFastOptionView() {
        return this.mFastOptionLayout;
    }

    public IGallery360Viewer.SaveStatus getSaveStatus(String str) {
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer == null) {
            return null;
        }
        return iGallery360Viewer.getSaveStatus(str);
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_360_PHOTO_VIEWER_MAIN.toString();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mSystemUi.onViewAttach(getResources());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        handleConfigurationChange(configuration);
        showBars();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPresenter = new Image360Presenter(this);
        initValues(bundle);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menu.clear();
        menuInflater.inflate(R$menu.gallery360viewer_menu, menu);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R$layout.gallery360viewer_viewer, viewGroup, false);
        initViews(inflate);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer != null) {
            iGallery360Viewer.onDestroy();
            this.m360Viewer = null;
        }
        this.mPresenter.onDestroy();
        this.mPresenter = null;
    }

    public void onModeSelectorClicked(boolean z) {
        setAutoPlayVisibility(!z);
    }

    public void onMoreMenuItemClicked(MenuItem menuItem) {
        onOptionsItemSelected(menuItem);
    }

    public void onMotionViewClicked() {
        boolean z = !this.mIsSensorEnabled;
        this.mIsSensorEnabled = z;
        this.mPresenter.onMotionViewClicked(z);
        updateMotionViewStatus();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R$id.playback) {
            onPlaybackOptionsSelected();
            return true;
        } else if (itemId == R$id.gear_vr) {
            this.mPresenter.launchGearVRView();
            return true;
        } else if (itemId != R$id.capture_view) {
            return true;
        } else {
            this.mPresenter.captureView();
            return true;
        }
    }

    public void onPause() {
        super.onPause();
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer != null) {
            iGallery360Viewer.onPause();
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (Features.isEnabled(Features.SUPPORT_VR_GALLERY)) {
            int i2 = R$id.gear_vr;
            menu.findItem(i2).setShowAsAction(1);
            menu.findItem(i2).setVisible(true);
        }
        menu.findItem(R$id.capture_view).setShowAsAction(1);
        this.mPresenter.updateFastOptionViewMoreMenu(menu);
        super.onPrepareOptionsMenu(menu);
    }

    public void onResetClicked() {
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer != null) {
            iGallery360Viewer.resetView(this.mPresenter.getPlayBackDirection().ordinal());
        }
        this.mPresenter.onResetClicked();
    }

    public void onResume() {
        super.onResume();
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer != null) {
            iGallery360Viewer.onResume();
        }
        this.mSystemUi.showNavigationBar(getActivity());
        handleConfigurationChange(getResources().getConfiguration());
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("current_view_mode", getCurrentViewMode().ordinal());
        bundle.putInt("playback_direction_visibility", this.mPresenter.getPlayBackDirection().ordinal());
        bundle.putBoolean("sensor_state", this.mPresenter.isSensorSupported());
        bundle.putBoolean("auto_play_state", this.mAutoPlayEnabled);
        super.onSaveInstanceState(bundle);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() != R$id.image360_holder) {
            return false;
        }
        this.mGestureDetector.onTouchEvent(motionEvent);
        View view2 = this.mView;
        if (view2 != null) {
            return view2.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mPresenter.onViewCreated();
        updateMotionViewStatus();
        view.findViewById(R$id.main_layout).setOnApplyWindowInsetsListener(new w8.a(this, 0));
        view.findViewById(R$id.image360_toolparent).setOnApplyWindowInsetsListener(new w8.a(this, 1));
    }

    public void onViewIconClicked(int i2) {
        IGallery360Viewer.ViewMode viewMode = IGallery360Viewer.ViewMode.values()[i2];
        if (getCurrentViewMode() != viewMode) {
            this.mFastOptionLayout.updateModeSelector(viewMode.ordinal());
            try {
                this.m360Viewer.setViewMode(viewMode);
                this.mPresenter.onViewIconClicked(viewMode);
            } catch (IllegalArgumentException e) {
                Log.e("Image360Fragment", e.getMessage());
            }
        }
    }

    public void saveCaptureImage(String str, Callable<Void> callable) {
        if (this.m360Viewer != null) {
            this.mPresenter.setCaptureInProgress(true);
            this.m360Viewer.save(str, callable, this.mPresenter.getSourceFilePath());
        }
    }

    public void startAutoRotation() {
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer != null) {
            iGallery360Viewer.startAutoRotation();
        }
    }

    public void stopAutoRotation() {
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer != null) {
            iGallery360Viewer.stopAutoRotation();
        }
    }

    public void update360Viewer(Bitmap bitmap, IGallery360Viewer.ViewMode viewMode) {
        IGallery360Viewer iGallery360Viewer = this.m360Viewer;
        if (iGallery360Viewer != null) {
            iGallery360Viewer.setInputImage(bitmap);
            this.m360Viewer.setViewMode(viewMode);
        }
    }

    public void updatePlaybackDirection(int i2) {
        this.mPresenter.updatePlaybackDirection(i2);
    }
}
