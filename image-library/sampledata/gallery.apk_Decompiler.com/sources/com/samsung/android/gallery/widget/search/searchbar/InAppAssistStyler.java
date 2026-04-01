package com.samsung.android.gallery.widget.search.searchbar;

import V8.a;
import android.content.res.Resources;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.effects.GalleryGuidingLightEffect;
import com.samsung.android.gallery.widget.effects.blur.AGSLBlurEffect;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InAppAssistStyler {
    private GalleryGuidingLightEffect mLightEffect;
    private SearchToolbar mSearchView;

    private void applyGradientHint(EditText editText) {
        String str;
        Resources resources = editText.getResources();
        if (editText.getHint() != null) {
            str = editText.getHint().toString();
        } else {
            str = resources.getString(R$string.search_hint_what_are_you_looking_for);
        }
        editText.getPaint().setShader(new LinearGradient(0.0f, 0.0f, editText.getPaint().measureText(str), (float) editText.getHeight(), new int[]{resources.getColor(R$color.sec_searchbar_gradient_color_1, (Resources.Theme) null), resources.getColor(R$color.sec_searchbar_gradient_color_2, (Resources.Theme) null), resources.getColor(R$color.sec_searchbar_gradient_color_3, (Resources.Theme) null), resources.getColor(R$color.sec_searchbar_gradient_color_4, (Resources.Theme) null)}, new float[]{0.0f, 0.6f, 0.8f, 1.0f}, Shader.TileMode.CLAMP));
    }

    private void initBlurBackground(SearchToolbarOptions searchToolbarOptions) {
        View view;
        if (searchToolbarOptions.supportBlurBackground() && searchToolbarOptions.getContainer() != null) {
            if (searchToolbarOptions.getCustomBlurTargetView() != null) {
                view = searchToolbarOptions.getCustomBlurTargetView();
            } else {
                view = (View) Optional.ofNullable(searchToolbarOptions.getContainer().findViewById(R$id.my_recycler_view)).map(new a(2)).orElse((Object) null);
            }
            View findViewById = searchToolbarOptions.getContainer().findViewById(R$id.bottom_search_toolbar_inner_container);
            if (view != null && findViewById != null && Build.VERSION.SDK_INT >= 33) {
                new AGSLBlurEffect.Builder(view, findViewById).build();
            }
        }
    }

    private void releaseLightEffect() {
        GalleryGuidingLightEffect galleryGuidingLightEffect = this.mLightEffect;
        if (galleryGuidingLightEffect != null) {
            galleryGuidingLightEffect.release();
        }
    }

    private void setActivateLightEffectWhenTyping(EditText editText) {
        GalleryGuidingLightEffect galleryGuidingLightEffect = this.mLightEffect;
        if (galleryGuidingLightEffect != null) {
            galleryGuidingLightEffect.setActivate(!TextUtils.isEmpty(editText.getText()));
        }
    }

    private boolean supportGuidingLightEffect() {
        if (Build.VERSION.SDK_INT >= 35) {
            return true;
        }
        return false;
    }

    public void applyTextViewStyle(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            applyGradientHint(editText);
            return;
        }
        editText.getPaint().setShader((Shader) null);
        editText.setTextColor(AppResources.getColor(R$color.new_search_bar_text_color));
    }

    public void destroy() {
        if (supportGuidingLightEffect()) {
            releaseLightEffect();
            this.mSearchView = null;
        }
    }

    public void initGuidingLight() {
        SearchToolbar searchToolbar;
        View view;
        if (supportGuidingLightEffect() && (searchToolbar = this.mSearchView) != null && this.mLightEffect == null) {
            ViewGroup viewGroup = (ViewGroup) searchToolbar.getParent();
            if (viewGroup != null) {
                view = viewGroup.findViewById(R$id.search_toolbar_visual_effect_view);
            } else {
                view = null;
            }
            if (view != null) {
                this.mLightEffect = new GalleryGuidingLightEffect(view, !this.mSearchView.isHintVisible());
            }
        }
    }

    public void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
        if (supportGuidingLightEffect()) {
            this.mSearchView = searchToolbar;
            initGuidingLight();
        }
        initBlurBackground(searchToolbarOptions);
    }

    public void onConfigurationChanged() {
        GalleryGuidingLightEffect galleryGuidingLightEffect = this.mLightEffect;
        if (galleryGuidingLightEffect != null) {
            galleryGuidingLightEffect.onConfigurationChanged();
        }
    }

    public void onQueryTextChanged(EditText editText) {
        applyTextViewStyle(editText);
        setActivateLightEffectWhenTyping(editText);
    }

    public void onResume() {
        GalleryGuidingLightEffect galleryGuidingLightEffect = this.mLightEffect;
        if (galleryGuidingLightEffect != null) {
            galleryGuidingLightEffect.invalidateEffect();
        }
    }
}
