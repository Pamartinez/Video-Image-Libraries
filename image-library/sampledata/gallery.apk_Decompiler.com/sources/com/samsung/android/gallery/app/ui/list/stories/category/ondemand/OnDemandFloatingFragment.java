package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import F6.f;
import V3.b;
import W.a;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.IOnDemandFloatingView;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandFloatingPresenter;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.listview.TouchParentLayout;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandFloatingFragment<V extends IOnDemandFloatingView, P extends OnDemandFloatingPresenter<?>> extends MvpBaseFragment<V, P> implements IOnDemandFloatingView {
    private View mBlurTargetView;
    private ViewGroup mResultViewContainer;
    private TouchParentLayout mRootView;

    private boolean isLocationKeyValid() {
        String locationKey = getLocationKey();
        if (locationKey == null || !locationKey.equals(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard))) {
            return false;
        }
        return true;
    }

    private boolean isOnDemandRunning() {
        P p6 = this.mPresenter;
        if (p6 == null || !((OnDemandFloatingPresenter) p6).isOnDemandRunning()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hideResultView$2() {
        ViewUtils.setVisibleOrGone(this.mResultViewContainer, false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onCreate$0() {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setClearViewMode$1(MotionEvent motionEvent) {
        return true;
    }

    public int getLayoutId() {
        return R.layout.fragment_ondemand_floating_layout;
    }

    public void hideResultView() {
        if (ViewUtils.isVisible(this.mResultViewContainer)) {
            this.mResultViewContainer.animate().setDuration(200).alpha(0.0f).withEndAction(new b(23, this)).start();
        }
    }

    public void initView(View view) {
        boolean z;
        this.mRootView = (TouchParentLayout) view.findViewById(R.id.root_view);
        this.mResultViewContainer = (ViewGroup) view.findViewById(R.id.result_text_container);
        Context context = getContext();
        if (context != null) {
            if (!Features.isEnabled(Features.SUPPORT_REALTIME_BLUR) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY) || Build.VERSION.SDK_INT < 31) {
                z = false;
            } else {
                z = true;
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.floating_recommendation_background_image);
            if (z) {
                Bitmap bitmap = (Bitmap) this.mBlackboard.pop("data:///CapturedBitmap", null);
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
                View findViewById = view.findViewById(R.id.blur_effect_target_view);
                this.mBlurTargetView = findViewById;
                if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                    SeApiCompat.setCanvasBlurPreset(findViewById, 135);
                    return;
                }
                this.mBlurTargetView.setBackgroundColor(AppResources.getColor(R.color.search_blur_dim_color));
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                ((View) findViewById.getParent()).setRenderEffect(RenderEffect.createBlurEffect(150.0f, 150.0f, Shader.TileMode.CLAMP));
                return;
            }
            imageView.setBackgroundColor(context.getColor(R.color.accessibility_reduce_transparency_and_blur_background_color));
        }
    }

    public boolean isActive() {
        if (getUserVisibleHint() || isLocationKeyValid()) {
            return true;
        }
        return false;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (SdkConfig.atLeast(SdkConfig.GED.P) && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            View findViewById = view.findViewById(R.id.search_toolbar_container);
            ViewMarginUtils.setLeftMargin(findViewById, WindowUtils.getSystemInsetsLeft(rootWindowInsets));
            ViewMarginUtils.setRightMargin(findViewById, WindowUtils.getSystemInsetsRight(rootWindowInsets));
            ViewMarginUtils.setLeftMargin(this.mResultViewContainer, WindowUtils.getSystemInsetsLeft(rootWindowInsets));
            ViewMarginUtils.setRightMargin(this.mResultViewContainer, WindowUtils.getSystemInsetsRight(rootWindowInsets));
        }
        return windowInsets;
    }

    public boolean onBackPressed() {
        P p6 = this.mPresenter;
        if ((p6 == null || !((OnDemandFloatingPresenter) p6).onBackPressed()) && !super.onBackPressed()) {
            return false;
        }
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((OnDemandFloatingPresenter) p6).updateSearchToolbarHorizontalPadding();
            ((OnDemandFloatingPresenter) this.mPresenter).onConfigurationChanged();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSystemUi.setCustomHasNoStatusBar(new f(10));
    }

    public void onDestroy() {
        super.onDestroy();
        SeApiCompat.removeBlur(this.mBlurTargetView);
    }

    public void onDestroyView() {
        super.onDestroyView();
        ((OnDemandFloatingPresenter) this.mPresenter).onDestroyView();
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (isViewActive() && eventMessage.what == 1138 && isOnDemandRunning()) {
            hideNavigationBar();
        }
        return super.onHandleEvent(eventMessage);
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(getView());
    }

    public void setClearViewMode(boolean z) {
        a aVar;
        if (isViewActive()) {
            ((OnDemandFloatingPresenter) this.mPresenter).setSearchViewClearMode(z);
            TouchParentLayout touchParentLayout = this.mRootView;
            if (z) {
                aVar = new a(4);
            } else {
                aVar = null;
            }
            touchParentLayout.setListener(aVar);
        }
    }

    public void showResultView(String str) {
        if (TextUtils.isEmpty(str)) {
            str = AppResources.getString(R.string.fail_generate_story_result_try_again);
        }
        if (!ViewUtils.isVisible(this.mResultViewContainer)) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.story_on_demand_result_text_translate_value);
            ViewUtils.setAlpha(this.mResultViewContainer, 0.0f);
            ViewGroup viewGroup = this.mResultViewContainer;
            viewGroup.setTranslationY(viewGroup.getTranslationY() + ((float) dimensionPixelSize));
            ViewUtils.setVisibleOrGone(this.mResultViewContainer, true);
            ViewPropertyAnimator alpha = this.mResultViewContainer.animate().setDuration(300).alpha(1.0f);
            alpha.translationYBy((float) (-dimensionPixelSize));
            alpha.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_60));
            alpha.start();
            ((TextView) this.mResultViewContainer.getChildAt(0)).setText(str);
        }
    }

    public OnDemandFloatingPresenter<?> createPresenter(IOnDemandFloatingView iOnDemandFloatingView) {
        return new OnDemandFloatingPresenter<>(this.mBlackboard, iOnDemandFloatingView);
    }
}
