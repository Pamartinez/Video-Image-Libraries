package com.samsung.android.gallery.plugins.panorama;

import A4.A;
import Ab.a;
import Ba.h;
import Ca.b;
import Ca.c;
import Ca.d;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.plugins.R$color;
import com.samsung.android.gallery.plugins.R$drawable;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.dialog.ProgressAvdCompat;
import com.samsung.android.gallery.widget.photoview.PhotoPreView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import i.C0212a;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PanoramaActivity extends AppCompatActivity {
    final Blackboard mBlackboard = Blackboard.getInstance(toString());
    /* access modifiers changed from: private */
    public Runnable mDeferredUpdateToolbarLayout;
    /* access modifiers changed from: private */
    public final PanoramaDelegate mDelegate = new PanoramaDelegate();
    private int mDensityDpi;
    private final FoldStateManager.FoldChangedListener mFoldChangedListener = new FoldStateManager.FoldChangedListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onTableModeChanged$0(boolean z) {
            PanoramaActivity.this.updateToolbarLayout(z);
        }

        public void onTableModeChanged(boolean z, int i2) {
            C0212a.x("onTableModeChanged ", "PanoramaActivity", z);
            if (PanoramaActivity.this.mSurfaceLoaded) {
                PanoramaActivity.this.updateToolbarLayout(z);
            } else {
                PanoramaActivity.this.mDeferredUpdateToolbarLayout = new a(this, z);
            }
            PanoramaActivity.this.mDelegate.updateViewMargin(PanoramaActivity.this.mParentView, z);
            PanoramaActivity.this.mDelegate.applyFlexModeTransition(PanoramaActivity.this.findViewById(R$id.photo_3d_view), 0);
        }
    };
    private FoldStateManager mFoldStateManager;
    private ProgressAvdCompat mInitProgressDialog;
    private final View.OnApplyWindowInsetsListener mOnApplyWindowInsetsListener = new c(0, this);
    /* access modifiers changed from: private */
    public ViewGroup mParentView;
    /* access modifiers changed from: private */
    public PhotoPreView mPreview;
    private Runnable mProgressShowRunnable;
    /* access modifiers changed from: private */
    public boolean mSurfaceLoaded = false;
    private GalleryToolbar mToolbar;

    private void bindToolbar(ViewGroup viewGroup) {
        View findViewById = viewGroup.findViewById(R$id.viewer_toolbar);
        if (findViewById instanceof FloatingToolbarLayout) {
            FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) findViewById;
            floatingToolbarLayout.clearBlurInfo(findViewById.getContext());
            floatingToolbarLayout.setColorForFloatingBackground(findViewById.getResources().getColor(R$color.viewer_floating_menu_background_color, (Resources.Theme) null));
        }
        GalleryToolbar galleryToolbar = (GalleryToolbar) viewGroup.findViewById(R$id.toolbar);
        this.mToolbar = galleryToolbar;
        galleryToolbar.setNavigationIcon(R$drawable.tw_ic_ab_back_mtrl_detailview);
        this.mToolbar.setNavigationOnClickListener(new a(13, this));
        this.mToolbar.setNavigationContentDescription(R$string.navigate_up);
    }

    private void dismissInitProgress() {
        Runnable runnable = this.mProgressShowRunnable;
        if (runnable != null) {
            ThreadUtil.removeCallbackOnUiThread(runnable);
        }
        ProgressAvdCompat progressAvdCompat = this.mInitProgressDialog;
        if (progressAvdCompat != null) {
            progressAvdCompat.dismiss();
            this.mInitProgressDialog.setOnCancelListener((DialogInterface.OnCancelListener) null);
            this.mInitProgressDialog = null;
        }
    }

    private boolean isDensityChanged(int i2) {
        if (this.mDensityDpi != i2) {
            return true;
        }
        return false;
    }

    private boolean isTableMode() {
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager == null || !foldStateManager.isTableMode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindToolbar$5(View view) {
        this.mToolbar.postDelayed(new b(this, 3), 180);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadBitmap$4(MediaItem mediaItem, Consumer consumer) {
        Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.parse((FileItemInterface) mediaItem, true));
        Log.d("PanoramaActivity", "loadBitmap#decode", Logger.toString(decodedBitmap));
        consumer.accept(decodedBitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$8() {
        updateToolbarMargin(isTableMode());
        this.mDelegate.updateViewMargin(this.mParentView, isTableMode());
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.onApplyWindowInsets();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsets lambda$new$9(View view, WindowInsets windowInsets) {
        ViewUtils.postOnGlobalLayout(this.mParentView, new b(this, 1));
        return windowInsets;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBackPressed$7(Boolean bool) {
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(FoldStateManager foldStateManager) {
        foldStateManager.register(this.mFoldChangedListener);
        this.mFoldStateManager = foldStateManager;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1() {
        this.mDelegate.introTransition(this.mPreview);
        Runnable runnable = this.mDeferredUpdateToolbarLayout;
        if (runnable != null) {
            ThreadUtil.runOnUiThread(runnable);
            this.mDeferredUpdateToolbarLayout = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(View view, Boolean bool) {
        if (bool.booleanValue()) {
            ViewUtils.setVisibility(view, 4);
            dismissInitProgress();
            this.mSurfaceLoaded = true;
            ThreadUtil.postOnUiThreadDelayed(new b(this, 0), 100);
            return;
        }
        this.mDelegate.showToast((Context) this, R$string.unknown_error_description);
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(MediaItem mediaItem, Bitmap bitmap) {
        try {
            this.mDelegate.bindView(this.mParentView, mediaItem, bitmap, new h(3, this));
        } catch (Exception e) {
            Log.e((CharSequence) "PanoramaActivity", "execute failed", (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showInitProgress$6() {
        this.mInitProgressDialog.show();
    }

    private void loadBitmap(MediaItem mediaItem, Consumer<Bitmap> consumer) {
        SimpleThreadPool.getInstance().execute(new d(mediaItem, consumer));
    }

    private void replaceToolbar() {
        View findViewById = findViewById(R$id.viewer_toolbar);
        if (findViewById == null) {
            Log.e("PanoramaActivity", "replaceToolbar failed.");
            return;
        }
        View inflate = getLayoutInflater().inflate(R$layout.toolbar_viewer, (ViewGroup) null);
        ViewUtils.replaceView(findViewById, inflate);
        inflate.setId(findViewById.getId());
        bindToolbar(this.mParentView);
    }

    private void showInitProgress() {
        if (this.mProgressShowRunnable == null) {
            this.mProgressShowRunnable = new b(this, 2);
        }
        if (this.mInitProgressDialog == null) {
            this.mInitProgressDialog = new ProgressAvdCompat(this).setProgressMessage(R$string.please_wait).build();
        }
        ThreadUtil.postOnUiThreadDelayed(this.mProgressShowRunnable, 1000);
    }

    /* access modifiers changed from: private */
    public void updateToolbarLayout(boolean z) {
        updateToolbarMargin(z);
        this.mDelegate.applyFlexModeTransition(this.mToolbar, StatusCodes.INPUT_MISSING);
    }

    private void updateToolbarMargin(boolean z) {
        int i2;
        View findViewById = this.mParentView.findViewById(R$id.viewer_toolbar);
        if (findViewById != null) {
            WindowInsets rootWindowInsets = findViewById.getRootWindowInsets();
            if (z) {
                i2 = ViewUtils.getHeight(this.mParentView) / 2;
            } else {
                i2 = WindowUtils.getSystemInsetsTop(rootWindowInsets);
            }
            ViewMarginUtils.setMargin(findViewById, Integer.valueOf(WindowUtils.getSystemInsetsLeft(rootWindowInsets)), Integer.valueOf(i2), Integer.valueOf(WindowUtils.getSystemInsetsRight(rootWindowInsets)), Integer.valueOf(WindowUtils.getSystemInsetsBottom(rootWindowInsets)));
        }
    }

    public void applyScreenConfiguration() {
        boolean z;
        Log.d("PanoramaActivity", "applyScreenConfiguration");
        if (!ResourceCompat.isLandscape((Context) this) || !SystemUi.hasNoStatusBarInLandscape(this)) {
            z = false;
        } else {
            z = true;
        }
        SystemUi.setSystemUiVisibility(this, SystemUi.getSystemUiVisibilityFull(z));
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null && galleryToolbar.getVisibility() != 0) {
            this.mToolbar.setVisibility(0);
            this.mToolbar.setAlpha(0.0f);
            this.mToolbar.animate().alpha(1.0f).start();
        }
    }

    public void bindPreview(MediaItem mediaItem, Bitmap bitmap) {
        this.mPreview.setBasicInfo(bitmap, mediaItem, new MarginParams());
        this.mPreview.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                PanoramaActivity.this.mPreview.getViewTreeObserver().removeOnPreDrawListener(this);
                PanoramaActivity.this.mParentView.setVisibility(0);
                return true;
            }
        });
    }

    public void finish() {
        getWindow().setSharedElementReturnTransition((Transition) null);
        getWindow().setSharedElementReenterTransition((Transition) null);
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setTransitionName((String) null);
        }
        super.finish();
        overridePendingTransition(0, 0);
    }

    public void onBackPressed() {
        PhotoPreView photoPreView = this.mPreview;
        if (photoPreView != null) {
            photoPreView.updateLayoutSize();
            this.mDelegate.outroTransition(this.mPreview, new Ca.a(this, 0));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d("PanoramaActivity", "onConfigurationChanged", Integer.valueOf(configuration.orientation), Integer.valueOf(configuration.densityDpi), Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels));
        if (isDensityChanged(configuration.densityDpi)) {
            replaceToolbar();
        }
        this.mDensityDpi = configuration.densityDpi;
        applyScreenConfiguration();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("data-key");
        if (stringExtra == null) {
            Log.e("PanoramaActivity", "no data-key");
            return;
        }
        Object[] objArr = (Object[]) Blackboard.getApplicationInstance().pop(stringExtra);
        if (objArr == null || objArr[0] == null || objArr[1] == null) {
            if (objArr == null) {
                Log.e("PanoramaActivity", "no data");
            } else {
                Log.e((CharSequence) "PanoramaActivity", "no data ", objArr[0], objArr[1]);
            }
            finish();
            return;
        }
        this.mDensityDpi = getResources().getConfiguration().densityDpi;
        SystemUi.setLayoutInDisPlayCutoutMode(getWindow(), 1);
        setContentView(R$layout.panorama_activity_layout);
        Optional.ofNullable(FoldStateManager.getInstance(this.mBlackboard, this)).ifPresent(new Ca.a(this, 1));
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.container);
        this.mParentView = viewGroup;
        viewGroup.setOnApplyWindowInsetsListener(this.mOnApplyWindowInsetsListener);
        this.mPreview = (PhotoPreView) this.mParentView.findViewById(R$id.preview);
        bindToolbar(this.mParentView);
        applyScreenConfiguration();
        MediaItem mediaItem = (MediaItem) objArr[0];
        bindPreview(mediaItem, ((Bitmap) objArr[1]).copy(Bitmap.Config.ARGB_8888, false));
        loadBitmap(mediaItem, new A(8, (Object) this, (Object) mediaItem));
        showInitProgress();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.reset(toString());
        dismissInitProgress();
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.unregister(this.mFoldChangedListener);
            this.mFoldStateManager = null;
        }
    }

    public void onPause() {
        super.onPause();
        this.mDelegate.pauseAnim();
    }

    public void onResume() {
        super.onResume();
        FoldStateManager foldStateManager = this.mFoldStateManager;
        if (foldStateManager != null) {
            foldStateManager.resume();
        }
        this.mDelegate.resumeAnim();
    }

    public void onStart() {
        super.onStart();
        overridePendingTransition(0, 0);
    }
}
