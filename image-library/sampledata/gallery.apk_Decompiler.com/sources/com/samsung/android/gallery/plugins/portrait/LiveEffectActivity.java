package com.samsung.android.gallery.plugins.portrait;

import A4.C0367b;
import A4.L;
import Ca.c;
import Da.b;
import Da.d;
import Da.e;
import Da.f;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.plugins.R$color;
import com.samsung.android.gallery.plugins.R$dimen;
import com.samsung.android.gallery.plugins.R$drawable;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.plugins.R$menu;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.abstraction.ScreenMode;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemParams;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import i.C0212a;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveEffectActivity extends AppCompatActivity implements ILiveEffect {
    private LiveEffectBixbyDelegate mBixbyDelegate;
    final Blackboard mBlackboard = Blackboard.getInstance(toString());
    private ViewGroup mContainer;
    /* access modifiers changed from: private */
    public Runnable mDeferredUpdateToolbarLayout;
    private final LiveEffectDelegate mDelegate = new LiveEffectDelegate(this);
    private int mDensityDpi;
    private FastOptionView mFastOption;
    private boolean mFastOptionsDisabled = false;
    private final FoldStateManager.FoldChangedListener mFoldChangedListener = new FoldStateManager.FoldChangedListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onTableModeChanged$0() {
            LiveEffectActivity liveEffectActivity = LiveEffectActivity.this;
            liveEffectActivity.updateTableModeLayout(liveEffectActivity.mParentView.getRootWindowInsets());
        }

        public void onTableModeChanged(boolean z, int i2) {
            C0212a.x("onTableModeChanged ", "LiveEffectActivity", z);
            if (LiveEffectActivity.this.mSurfaceLoaded) {
                LiveEffectActivity liveEffectActivity = LiveEffectActivity.this;
                liveEffectActivity.updateTableModeLayout(liveEffectActivity.mParentView.getRootWindowInsets());
                return;
            }
            LiveEffectActivity.this.mDeferredUpdateToolbarLayout = new d(7, this);
        }
    };
    private FoldStateManager mFoldStateManager;
    private View mGyroBtn;
    private InitProgressDelegate mInitProgressDelegate;
    private boolean mIsAutoSave = false;
    private boolean mIsCanceled = false;
    private boolean mIsFullScreen = false;
    private MediaItem mItem;
    private final View.OnApplyWindowInsetsListener mOnApplyWindowInsetsListener = new c(1, this);
    /* access modifiers changed from: private */
    public ViewGroup mParentView;
    /* access modifiers changed from: private */
    public PhotoPreView mPreview;
    /* access modifiers changed from: private */
    public boolean mSurfaceLoaded = false;
    private View mToolBarViewer;
    private GalleryToolbar mToolbar;
    private final ArrayList<MenuItem> mToolbarMenus = new ArrayList<>();

    private void bindFastOptionView(ViewGroup viewGroup) {
        FastOptionView fastOptionView = (FastOptionView) viewGroup.findViewById(R$id.fast_option_view);
        this.mFastOption = fastOptionView;
        if (this.mFastOptionsDisabled) {
            fastOptionView.setVisibility(8);
            return;
        }
        fastOptionView.setElevation((float) fastOptionView.getResources().getDimensionPixelSize(R$dimen.viewer_floating_fast_option_elevation));
        if (!this.mItem.isLiveEffect()) {
            this.mFastOption.addItem(getFastOptionItemParams(R$string.share_short, R$id.action_share), R$drawable.gallery_ic_detail_share);
            if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) {
                this.mFastOption.addItem(getFastOptionItemParams(R$string.change_cover_apply, R$id.action_apply), R$drawable.gallery_ic_liveeffect_apply);
            } else {
                this.mFastOption.addItem(getFastOptionItemParams(R$string.save_as_copy, R$id.action_apply), R$drawable.gallery_ic_detail_save);
            }
            if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_WALLPAPER) && !isDualDexMode()) {
                this.mFastOption.addItem(getFastOptionItemParams(R$string.set_as_wallpaper, R$id.action_set_as_wallpaper), R$drawable.suggestions_set_as_wallpaper);
            }
        }
        this.mFastOption.setItemSelectedListener(new b(this));
    }

    private void bindGyroButton(ViewGroup viewGroup) {
        if (Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) {
            View findViewById = viewGroup.findViewById(R$id.liveeffect_gyro_button);
            this.mGyroBtn = findViewById;
            findViewById.setVisibility(0);
            this.mGyroBtn.setOnClickListener(new Da.c(this, 0));
        }
    }

    private void bindToolbar(ViewGroup viewGroup) {
        View findViewById = viewGroup.findViewById(R$id.viewer_toolbar);
        this.mToolBarViewer = findViewById;
        if (findViewById instanceof FloatingToolbarLayout) {
            ((FloatingToolbarLayout) findViewById).clearBlurInfo(findViewById.getContext());
            View view = this.mToolBarViewer;
            ((FloatingToolbarLayout) view).setColorForFloatingBackground(view.getResources().getColor(R$color.viewer_floating_menu_background_color, (Resources.Theme) null));
        }
        GalleryToolbar galleryToolbar = (GalleryToolbar) viewGroup.findViewById(R$id.toolbar);
        this.mToolbar = galleryToolbar;
        galleryToolbar.setNavigationIcon(R$drawable.tw_ic_ab_back_mtrl_detailview);
        this.mToolbar.setNavigationOnClickListener(new Da.c(this, 3));
        this.mToolbar.setNavigationContentDescription(R$string.navigate_up);
        this.mToolbar.setOnMenuItemClickListener(new b(this));
        this.mToolbar.inflateMenu(R$menu.menu_3d_photo);
        for (int i2 = 0; i2 < this.mToolbar.getMenu().size(); i2++) {
            MenuItem item = this.mToolbar.getMenu().getItem(i2);
            if (!isSupportedToolbarItem(item)) {
                item.setVisible(false);
            } else {
                item.setShowAsAction(2);
                this.mToolbarMenus.add(item);
                item.setVisible(true);
            }
        }
    }

    private void cancelGenerating() {
        this.mIsCanceled = true;
        InitProgressDelegate initProgressDelegate = this.mInitProgressDelegate;
        if (initProgressDelegate != null) {
            initProgressDelegate.onCancel();
        }
    }

    private FastOptionItemParams.Builder getFastOptionItemParams(int i2, int i7) {
        return FastOptionItemParams.builder().setShowText(true).setTitleRes(i2).setMenuId(i7).setFastOptionMenu(true).setWidthRes(R$dimen.live_effect_fast_option_item_width);
    }

    private boolean hasFastOptionMenuItems() {
        FastOptionView fastOptionView = this.mFastOption;
        if (fastOptionView == null || fastOptionView.getItemCount() == 0) {
            return false;
        }
        return true;
    }

    private boolean isDensityChanged(int i2) {
        if (this.mDensityDpi != i2) {
            return true;
        }
        return false;
    }

    private boolean isSupportedToolbarItem(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (this.mItem.isLiveEffect()) {
            return false;
        }
        if (itemId == R$id.action_apply && !Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_GYRO)) {
            menuItem.setIcon(R$drawable.gallery_ic_detail_save).setTitle(R$string.save_as_copy);
        }
        if (itemId != R$id.action_set_as_wallpaper) {
            return true;
        }
        if (!Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_WALLPAPER) || isDualDexMode()) {
            return false;
        }
        return true;
    }

    private boolean isTableMode() {
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager == null || !foldStateManager.isTableMode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindFastOptionView$9(int i2, View view) {
        Log.d("LiveEffectActivity", "FastOptionView#onClick", Integer.valueOf(i2));
        onMenuClicked(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindGyroButton$10(View view) {
        onClickGyro();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindToolbar$7(View view) {
        this.mToolbar.postDelayed(new e(this, 0), 180);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$bindToolbar$8(MenuItem menuItem) {
        return onMenuClicked(menuItem.getItemId());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$12(WindowInsets windowInsets) {
        if (this.mDeferredUpdateToolbarLayout == null) {
            this.mDelegate.updateContainerMargin(this.mParentView, isTableMode());
        }
        updateChromeMargin(windowInsets);
        InitProgressDelegate initProgressDelegate = this.mInitProgressDelegate;
        if (initProgressDelegate != null) {
            initProgressDelegate.onLayoutUpdated();
        }
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.onApplyWindowInsets();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsets lambda$new$13(View view, WindowInsets windowInsets) {
        ViewUtils.postOnGlobalLayout(this.mParentView, new f(0, this, windowInsets));
        return windowInsets;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(FoldStateManager foldStateManager) {
        foldStateManager.register(this.mFoldChangedListener);
        this.mFoldStateManager = foldStateManager;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(View view) {
        toggleFullScreen();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renderLiveEffectByItemType$2() {
        onMenuClicked(R$id.action_apply);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renderLiveEffectByItemType$3(View view) {
        toggleFullScreen();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renderLiveEffectByItemType$4(View view) {
        Log.i("LiveEffectActivity", "onBind the live effect view");
        ViewUtils.setVisibility(view, 0);
        Runnable runnable = this.mDeferredUpdateToolbarLayout;
        if (runnable != null) {
            ThreadUtil.runOnUiThread(runnable);
            this.mDeferredUpdateToolbarLayout = null;
            ViewUtils.setVisibility(this.mPreview, 4);
        } else {
            this.mDelegate.applyTransition(this.mPreview, view);
        }
        updateMenuVisibility(getResources().getConfiguration());
        if (this.mIsAutoSave) {
            Log.d("LiveEffectActivity", "execute auto save");
            ThreadUtil.runOnUiThread(new e(this, 2));
        }
        view.setOnClickListener(new Da.c(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renderLiveEffectByItemType$5(View view, Boolean bool) {
        if (bool.booleanValue()) {
            ViewUtils.setVisibility(view, 4);
            InitProgressDelegate initProgressDelegate = this.mInitProgressDelegate;
            if (initProgressDelegate != null) {
                initProgressDelegate.dismissInitProgress();
                this.mInitProgressDelegate = null;
            } else {
                Log.e("LiveEffectActivity", "fail to dismiss initProgress(null)");
            }
            if (this.mIsCanceled) {
                finish();
                return;
            }
            this.mSurfaceLoaded = true;
            ThreadUtil.postOnUiThreadDelayed(new f(1, this, view), 100);
            return;
        }
        this.mDelegate.showToast((Context) this, R$string.unknown_error_description);
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateTableModeLayout$6() {
        this.mToolBarViewer.requestLayout();
    }

    private void onClickGyro() {
        int i2;
        this.mDelegate.toggleGyroMode();
        View view = this.mGyroBtn;
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R$id.gyro_icon);
            if (this.mDelegate.isGyroMode()) {
                i2 = R$drawable.ic_motion_phone_rotate_on;
            } else {
                i2 = R$drawable.ic_motion_phone_rotate_off;
            }
            imageView.setImageResource(i2);
        }
    }

    /* access modifiers changed from: private */
    public void onSaveDone(String str, Uri uri) {
        if (this.mIsAutoSave) {
            Blackboard.postGlobal("data://bixby_command_done", new Object[]{"no_error", null, str, uri});
        }
        if (str != null) {
            finish();
        }
    }

    private void renderLiveEffectByItemType(MediaItem mediaItem, Object[] objArr) {
        d dVar = new d(this, 1);
        Bitmap copy = objArr[1].copy(Bitmap.Config.ARGB_8888, false);
        bindPreview(mediaItem, copy);
        this.mDelegate.bindView(this.mParentView, mediaItem, copy, dVar);
    }

    private void replaceFastOption() {
        View findViewById = findViewById(R$id.viewer_fast_option);
        if (findViewById == null) {
            Log.e("LiveEffectActivity", "replaceFastOption failed.");
            return;
        }
        View inflate = getLayoutInflater().inflate(R$layout.live_effect_fastoption, (ViewGroup) null);
        ViewUtils.replaceView(findViewById, inflate);
        inflate.setId(findViewById.getId());
        bindFastOptionView(this.mParentView);
    }

    private void replaceToolbar() {
        View findViewById = findViewById(R$id.viewer_toolbar);
        if (findViewById == null) {
            Log.e("LiveEffectActivity", "replaceToolbar failed.");
            return;
        }
        View inflate = getLayoutInflater().inflate(R$layout.floating_toolbar_layout, (ViewGroup) null);
        ViewUtils.replaceView(findViewById, inflate);
        inflate.setId(findViewById.getId());
        bindToolbar(this.mParentView);
    }

    private void toggleColor() {
        if (this.mIsFullScreen) {
            this.mParentView.setBackgroundColor(-16777216);
        } else {
            this.mParentView.setBackgroundResource(R$color.daynight_default_background);
        }
    }

    private void updateChromeMargin(WindowInsets windowInsets) {
        int systemInsetsTop = WindowUtils.getSystemInsetsTop(windowInsets);
        int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(windowInsets);
        int systemInsetsStart = WindowUtils.getSystemInsetsStart(windowInsets);
        int systemInsetsEnd = WindowUtils.getSystemInsetsEnd(windowInsets);
        updateToolbarMargin(systemInsetsTop, systemInsetsStart, systemInsetsEnd);
        updateFastOptionMargin(systemInsetsBottom, systemInsetsStart, systemInsetsEnd);
        updateGyroMargin(systemInsetsBottom, systemInsetsStart, systemInsetsEnd);
    }

    private void updateFastOptionMargin(int i2, int i7, int i8) {
        FastOptionView fastOptionView = this.mFastOption;
        ViewMarginUtils.setBottomMargin(fastOptionView, fastOptionView.getResources().getDimensionPixelOffset(R$dimen.viewer_floating_fast_option_bottom_margin) + i2);
        ViewMarginUtils.setHorizontalRelativeMargin(this.mFastOption, i7, i8);
    }

    private void updateGyroMargin(int i2, int i7, int i8) {
        ViewMarginUtils.setBottomMargin(this.mGyroBtn, this.mFastOption.getResources().getDimensionPixelOffset(R$dimen.gyro_bottom_margin) + i2);
        ViewMarginUtils.setRightMargin(this.mGyroBtn, this.mFastOption.getResources().getDimensionPixelOffset(R$dimen.gyro_right_margin) + i8);
    }

    private void updateInsetUi() {
        boolean z;
        if (this.mIsFullScreen) {
            SystemUi.setSystemUiVisibility(this, 3846);
            return;
        }
        if (!ResourceCompat.isLandscape((Context) this) || !SystemUi.hasNoStatusBarInLandscape(this)) {
            z = false;
        } else {
            z = true;
        }
        SystemUi.setSystemUiVisibility(this, SystemUi.getSystemUiVisibilityFull(z));
        SystemUi.setViewerSystemBar(this);
    }

    private void updateMenuVisibility(Configuration configuration) {
        boolean z;
        boolean z3;
        int i2;
        int i7;
        int i8;
        View view = this.mGyroBtn;
        int i10 = 8;
        if (view != null) {
            if (!this.mSurfaceLoaded || this.mIsFullScreen) {
                i8 = 8;
            } else {
                i8 = 0;
            }
            view.setVisibility(i8);
        }
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            boolean z7 = this.mSurfaceLoaded;
            boolean z9 = true;
            if (!z7 || this.mIsFullScreen) {
                z = false;
            } else {
                z = true;
            }
            if (!z7 || configuration.orientation != 2 || this.mIsFullScreen) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            galleryToolbar.setVisibility(i2);
            View view2 = this.mToolBarViewer;
            if (z) {
                i7 = 0;
            } else {
                i7 = 8;
            }
            view2.setVisibility(i7);
            this.mToolbarMenus.forEach(new L(z3, 3));
            if (this.mFastOption != null && !this.mFastOptionsDisabled) {
                if (!this.mSurfaceLoaded || z3 || this.mIsFullScreen || !hasFastOptionMenuItems()) {
                    z9 = false;
                }
                FastOptionView fastOptionView = this.mFastOption;
                if (z9) {
                    i10 = 0;
                }
                fastOptionView.setVisibility(i10);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTableModeLayout(WindowInsets windowInsets) {
        boolean isTableMode = isTableMode();
        if (!this.mIsFullScreen) {
            this.mDelegate.applyFlexModeTransition(this.mToolBarViewer, 0, new e(this, 1));
        }
        this.mDelegate.updateContainerMargin(this.mParentView, isTableMode);
        this.mDelegate.applyFlexModeTransition(findViewById(R$id.photo_3d_view), StatusCodes.INPUT_MISSING);
        updateChromeMargin(windowInsets);
    }

    private void updateToolbarMargin(int i2, int i7, int i8) {
        boolean isTableMode = isTableMode();
        View findViewById = this.mParentView.findViewById(R$id.viewer_toolbar);
        if (findViewById != null) {
            if (isTableMode) {
                i2 = ViewUtils.getHeight(this.mParentView) / 2;
            }
            ViewMarginUtils.setTopMargin(findViewById, i2);
            ViewMarginUtils.setHorizontalRelativeMargin(findViewById, i7, i8);
        }
    }

    public void bindDecorView(ViewGroup viewGroup) {
        bindToolbar(viewGroup);
        bindFastOptionView(viewGroup);
        bindGyroButton(viewGroup);
        updateMenuVisibility(getResources().getConfiguration());
    }

    public void bindPreview(MediaItem mediaItem, Bitmap bitmap) {
        this.mPreview.setBasicInfo(bitmap, mediaItem, getPhotoPreViewMargin());
        this.mPreview.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                LiveEffectActivity.this.mPreview.getViewTreeObserver().removeOnPreDrawListener(this);
                LiveEffectActivity.this.mParentView.setVisibility(0);
                return true;
            }
        });
    }

    public void enterFullScreen(boolean z) {
        String str;
        ScreenMode full = ScreenMode.getFull(z);
        if (SystemUi.getScreenMode(this.mBlackboard) != full) {
            StringBuilder sb2 = new StringBuilder("enterScreen {full");
            if (z) {
                str = "-nostat";
            } else {
                str = "-stat";
            }
            sb2.append(str);
            sb2.append("-");
            sb2.append(full);
            sb2.append("}");
            Log.d("LiveEffectActivity", sb2.toString());
            SystemUi.setSystemUiVisibility(this, SystemUi.getSystemUiVisibilityFull(z));
            SystemUi.setScreenMode(this.mBlackboard, full);
            SystemUi.setViewerSystemBar(this);
            return;
        }
        SystemUi.requestApplyInset(this);
    }

    public void finish() {
        getWindow().setSharedElementReturnTransition((Transition) null);
        getWindow().setSharedElementReenterTransition((Transition) null);
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setTransitionName((String) null);
        }
        super.finish();
    }

    public MarginParams getPhotoPreViewMargin() {
        MarginParams marginParams = new MarginParams();
        if (!isInMultiWindowMode() && isTableMode()) {
            marginParams.bottomMargin = DeviceInfo.getRealDisplaySize(this).getHeight() / 2;
        }
        return marginParams;
    }

    public ShareComponent getShareComponent() {
        return (ShareComponent) Optional.ofNullable(this.mBixbyDelegate).map(new a(0)).orElse((Object) null);
    }

    public boolean isDualDexMode() {
        if (!DeviceInfo.isDexMode(this) || SeApiCompat.isDexStandAloneMode(this)) {
            return false;
        }
        return true;
    }

    public void onBackPressed() {
        InitProgressDelegate initProgressDelegate = this.mInitProgressDelegate;
        if (initProgressDelegate == null || !initProgressDelegate.isProcessing()) {
            finish();
        } else if (!this.mIsCanceled) {
            cancelGenerating();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d("LiveEffectActivity", "onConfigurationChanged", Integer.valueOf(configuration.orientation), Integer.valueOf(configuration.densityDpi), Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels));
        if (isDensityChanged(configuration.densityDpi)) {
            replaceToolbar();
            replaceFastOption();
        }
        updateMenuVisibility(configuration);
        updateInsetUi();
        InitProgressDelegate initProgressDelegate = this.mInitProgressDelegate;
        if (initProgressDelegate != null) {
            initProgressDelegate.onLayoutUpdated();
        }
        this.mDensityDpi = configuration.densityDpi;
        ViewGroup viewGroup = this.mParentView;
        if (viewGroup != null) {
            viewGroup.requestApplyInsets();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("data-key");
        if (stringExtra == null) {
            Log.e("LiveEffectActivity", "no data-key");
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("system-no-status-bar", false);
        intent.getIntExtra("system-status-bar-color", 0);
        this.mIsAutoSave = intent.getBooleanExtra("auto-save", false);
        Object[] objArr = (Object[]) Blackboard.getApplicationInstance().pop(stringExtra);
        if (objArr == null || objArr[0] == null || objArr[1] == null) {
            if (objArr == null) {
                Log.e("LiveEffectActivity", "no data");
            } else {
                Log.e((CharSequence) "LiveEffectActivity", "no data ", objArr[0], objArr[1]);
            }
            finish();
            return;
        }
        this.mDensityDpi = getResources().getConfiguration().densityDpi;
        enterFullScreen(booleanExtra);
        SystemUi.setLayoutInDisPlayCutoutMode(getWindow(), 1);
        setContentView(R$layout.photography_3d_activity_layout);
        MediaItem mediaItem = (MediaItem) objArr[0];
        this.mItem = mediaItem;
        this.mDelegate.setIsFromDetailView(mediaItem.isLiveEffect());
        this.mParentView = (ViewGroup) findViewById(R$id.live_effect_root);
        this.mContainer = (ViewGroup) findViewById(R$id.container);
        this.mParentView.setOnApplyWindowInsetsListener(this.mOnApplyWindowInsetsListener);
        this.mPreview = (PhotoPreView) this.mParentView.findViewById(R$id.preview);
        Optional.ofNullable(FoldStateManager.getInstance(this.mBlackboard, this)).ifPresent(new C0367b(27, this));
        bindDecorView(this.mParentView);
        this.mDelegate.updateContainerMargin(this.mParentView, isTableMode());
        renderLiveEffectByItemType(this.mItem, objArr);
        if (intent.getBooleanExtra("show-init-progress", true)) {
            InitProgressDelegate initProgressDelegate = new InitProgressDelegate(this, this.mParentView);
            this.mInitProgressDelegate = initProgressDelegate;
            initProgressDelegate.showInitProgress();
        }
        this.mBixbyDelegate = new LiveEffectBixbyDelegate(this, this.mBlackboard, this.mItem);
        this.mContainer.setOnClickListener(new Da.c(this, 2));
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.reset(toString());
        InitProgressDelegate initProgressDelegate = this.mInitProgressDelegate;
        if (initProgressDelegate != null) {
            initProgressDelegate.dismissInitProgress();
        }
        this.mDelegate.dismissDialog();
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.unregister(this.mFoldChangedListener);
            this.mFoldStateManager = null;
        }
        Optional.ofNullable(this.mBixbyDelegate).ifPresent(new b(0));
    }

    public boolean onMenuClicked(int i2) {
        if (i2 == R$id.action_share) {
            this.mDelegate.share();
            return true;
        } else if (i2 == R$id.action_apply) {
            this.mDelegate.save(new d(this, 0));
            return true;
        } else if (i2 != R$id.action_set_as_wallpaper) {
            return true;
        } else {
            this.mDelegate.setAsWallpaper();
            return true;
        }
    }

    public void onPause() {
        super.onPause();
        InitProgressDelegate initProgressDelegate = this.mInitProgressDelegate;
        if (initProgressDelegate != null) {
            initProgressDelegate.pauseProcessing();
        }
        this.mDelegate.pauseAnim();
    }

    public void onResume() {
        super.onResume();
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.resume();
        }
        InitProgressDelegate initProgressDelegate = this.mInitProgressDelegate;
        if (initProgressDelegate != null) {
            initProgressDelegate.resumeProcessing();
        }
        this.mDelegate.resumeAnim();
        Optional.ofNullable(this.mBixbyDelegate).ifPresent(new b(1));
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            Optional.ofNullable(this.mBixbyDelegate).ifPresent(new b(2));
        }
    }

    public void toggleFullScreen() {
        this.mIsFullScreen = !this.mIsFullScreen;
        updateInsetUi();
        updateMenuVisibility(getResources().getConfiguration());
        toggleColor();
    }

    public Activity getActivity() {
        return this;
    }
}
