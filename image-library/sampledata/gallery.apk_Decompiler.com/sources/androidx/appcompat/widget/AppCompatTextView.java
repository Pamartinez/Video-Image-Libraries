package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import androidx.appcompat.widget.AppCompatTextHelper;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.reflect.widget.SeslTextViewReflector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppCompatTextView extends TextView {
    private boolean isNeedToSkipRefreshDrawable;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private AppCompatEmojiTextHelper mEmojiTextViewHelper;
    private String mFontVariationSettings;
    private boolean mIsSetTypefaceProcessing;
    private Typeface mLastKnownTypefaceSetOnPaint;
    private Typeface mOriginalTypeface;
    private Future<PrecomputedTextCompat> mPrecomputedTextFuture;
    private SuperCaller mSuperCaller;
    private final AppCompatTextClassifierHelper mTextClassifierHelper;
    private final AppCompatTextHelper mTextHelper;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SuperCaller {
        int getAutoSizeMaxTextSize();

        int getAutoSizeMinTextSize();

        int getAutoSizeStepGranularity();

        int[] getAutoSizeTextAvailableSizes();

        int getAutoSizeTextType();

        TextClassifier getTextClassifier();

        void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i7, int i8, int i10);

        void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2);

        void setAutoSizeTextTypeWithDefaults(int i2);

        void setFirstBaselineToTopHeight(int i2);

        void setLastBaselineToBottomHeight(int i2);

        void setLineHeight(int i2, float f);

        void setTextClassifier(TextClassifier textClassifier);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SuperCallerApi28 extends SuperCallerApi26 {
        public SuperCallerApi28() {
            super();
        }

        public void setFirstBaselineToTopHeight(int i2) {
            AppCompatTextView.super.setFirstBaselineToTopHeight(i2);
        }

        public void setLastBaselineToBottomHeight(int i2) {
            AppCompatTextView.super.setLastBaselineToBottomHeight(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SuperCallerApi34 extends SuperCallerApi28 {
        public SuperCallerApi34() {
            super();
        }

        public void setLineHeight(int i2, float f) {
            AppCompatTextView.super.setLineHeight(i2, f);
        }
    }

    public AppCompatTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    private void consumeTextFutureAndSetBlocking() {
        Future<PrecomputedTextCompat> future = this.mPrecomputedTextFuture;
        if (future != null) {
            try {
                this.mPrecomputedTextFuture = null;
                if (future.get() == null) {
                    TextViewCompat.setPrecomputedText(this, (PrecomputedTextCompat) null);
                    return;
                }
                throw new ClassCastException();
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.mEmojiTextViewHelper == null) {
            this.mEmojiTextViewHelper = new AppCompatEmojiTextHelper(this);
        }
        return this.mEmojiTextViewHelper;
    }

    private void setTypefaceInternal(Typeface typeface) {
        this.mLastKnownTypefaceSetOnPaint = typeface;
        super.setTypeface(typeface);
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
    }

    public int getAutoSizeMaxTextSize() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return getSuperCaller().getAutoSizeMaxTextSize();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            return appCompatTextHelper.getAutoSizeMaxTextSize();
        }
        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return getSuperCaller().getAutoSizeMinTextSize();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            return appCompatTextHelper.getAutoSizeMinTextSize();
        }
        return -1;
    }

    public int getAutoSizeStepGranularity() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return getSuperCaller().getAutoSizeStepGranularity();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            return appCompatTextHelper.getAutoSizeStepGranularity();
        }
        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            return getSuperCaller().getAutoSizeTextAvailableSizes();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            return appCompatTextHelper.getAutoSizeTextAvailableSizes();
        }
        return new int[0];
    }

    public int getAutoSizeTextType() {
        if (!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
            if (appCompatTextHelper != null) {
                return appCompatTextHelper.getAutoSizeTextType();
            }
            return 0;
        } else if (getSuperCaller().getAutoSizeTextType() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    public int getFirstBaselineToTopHeight() {
        return TextViewCompat.getFirstBaselineToTopHeight(this);
    }

    public String getFontVariationSettings() {
        return this.mFontVariationSettings;
    }

    public int getLastBaselineToBottomHeight() {
        return TextViewCompat.getLastBaselineToBottomHeight(this);
    }

    public SuperCaller getSuperCaller() {
        if (this.mSuperCaller == null) {
            if (Build.VERSION.SDK_INT >= 34) {
                this.mSuperCaller = new SuperCallerApi34();
            } else {
                this.mSuperCaller = new SuperCallerApi28();
            }
        }
        return this.mSuperCaller;
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintList();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintMode();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.getCompoundDrawableTintList();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.getCompoundDrawableTintMode();
    }

    public CharSequence getText() {
        consumeTextFutureAndSetBlocking();
        return super.getText();
    }

    public TextClassifier getTextClassifier() {
        return getSuperCaller().getTextClassifier();
    }

    public PrecomputedTextCompat.Params getTextMetricsParamsCompat() {
        return TextViewCompat.getTextMetricsParams(this);
    }

    public Typeface getTypeface() {
        return this.mOriginalTypeface;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getBackground() instanceof SeslRecoilDrawable) {
            this.isNeedToSkipRefreshDrawable = true;
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.mTextHelper.populateSurroundingTextIfNeeded(this, onCreateInputConnection, editorInfo);
        return AppCompatHintHelper.onCreateInputConnection(onCreateInputConnection, editorInfo, this);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT < 33 && onCheckIsTextEditor()) {
            ((InputMethodManager) getContext().getSystemService("input_method")).isActive(this);
        }
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onLayout(z, i2, i7, i8, i10);
        }
    }

    public void onMeasure(int i2, int i7) {
        consumeTextFutureAndSetBlocking();
        super.onMeasure(i2, i7);
    }

    public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
        super.onTextChanged(charSequence, i2, i7, i8);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null && !ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE && appCompatTextHelper.isAutoSizeEnabled()) {
            this.mTextHelper.autoSizeText();
        }
    }

    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.isNeedToSkipRefreshDrawable && getStateListAnimator() != null) {
            getStateListAnimator().jumpToCurrentState();
            this.isNeedToSkipRefreshDrawable = false;
        }
    }

    public void seslSetButtonShapeEnabled(boolean z) {
        SeslTextViewReflector.semSetButtonShapeEnabled(this, z);
    }

    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().setAllCaps(z);
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i7, int i8, int i10) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            getSuperCaller().setAutoSizeTextTypeUniformWithConfiguration(i2, i7, i8, i10);
            return;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i2, i7, i8, i10);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            getSuperCaller().setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            return;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i2) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            getSuperCaller().setAutoSizeTextTypeWithDefaults(i2);
            return;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.setAutoSizeTextTypeWithDefaults(i2);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundResource(i2);
        }
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetCompoundDrawables();
        }
    }

    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetCompoundDrawables();
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetCompoundDrawables();
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetCompoundDrawables();
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().setEnabled(z);
    }

    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().getFilters(inputFilterArr));
    }

    public void setFirstBaselineToTopHeight(int i2) {
        getSuperCaller().setFirstBaselineToTopHeight(i2);
    }

    public boolean setFontVariationSettings(String str) {
        Typeface typeface = this.mOriginalTypeface;
        if (this.mLastKnownTypefaceSetOnPaint != getPaint().getTypeface()) {
            Log.w("AppCompatTextView", "getPaint().getTypeface() changed unexpectedly. App code should not modify the result of getPaint().");
            typeface = getPaint().getTypeface();
        }
        Typeface createVariationInstance = AppCompatTextHelper.Api26Impl.createVariationInstance(typeface, str);
        if (createVariationInstance == null) {
            return false;
        }
        setTypefaceInternal(createVariationInstance);
        this.mFontVariationSettings = str;
        return true;
    }

    public void setLastBaselineToBottomHeight(int i2) {
        getSuperCaller().setLastBaselineToBottomHeight(i2);
    }

    public void setLineHeight(int i2) {
        TextViewCompat.setLineHeight(this, i2);
    }

    public void setPrecomputedText(PrecomputedTextCompat precomputedTextCompat) {
        TextViewCompat.setPrecomputedText(this, precomputedTextCompat);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintList(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintMode(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.mTextHelper.setCompoundDrawableTintList(colorStateList);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.mTextHelper.setCompoundDrawableTintMode(mode);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetTextAppearance(context, i2);
        }
    }

    public void setTextClassifier(TextClassifier textClassifier) {
        getSuperCaller().setTextClassifier(textClassifier);
    }

    public void setTextFuture(Future<PrecomputedTextCompat> future) {
        this.mPrecomputedTextFuture = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(PrecomputedTextCompat.Params params) {
        TextViewCompat.setTextMetricsParams(this, params);
    }

    public void setTextSize(int i2, float f) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            super.setTextSize(i2, f);
            return;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.setTextSize(i2, f);
        }
    }

    public void setTypeface(Typeface typeface) {
        this.mOriginalTypeface = typeface;
        setTypefaceInternal(typeface);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public void setLineHeight(int i2, float f) {
        if (Build.VERSION.SDK_INT >= 34) {
            getSuperCaller().setLineHeight(i2, f);
        } else {
            TextViewCompat.setLineHeight(this, i2, f);
        }
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i2) {
        super(TintContextWrapper.wrap(context), attributeSet, i2);
        this.mIsSetTypefaceProcessing = false;
        this.mSuperCaller = null;
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper;
        appCompatBackgroundHelper.loadFromAttributes(attributeSet, i2);
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper;
        appCompatTextHelper.loadFromAttributes(attributeSet, i2);
        appCompatTextHelper.applyCompoundDrawablesTints();
        this.mTextClassifierHelper = new AppCompatTextClassifierHelper(this);
        getEmojiTextViewHelper().loadFromAttributes(attributeSet, i2);
    }

    public void setTypeface(Typeface typeface, int i2) {
        if (!this.mIsSetTypefaceProcessing) {
            if (typeface != null && i2 > 0) {
                typeface = TypefaceCompat.create(getContext(), typeface, i2);
            }
            this.mIsSetTypefaceProcessing = true;
            try {
                super.setTypeface(typeface, i2);
            } finally {
                this.mIsSetTypefaceProcessing = false;
            }
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i7, int i8, int i10) {
        Context context = getContext();
        Drawable drawable = null;
        Drawable drawable2 = i2 != 0 ? AppCompatResources.getDrawable(context, i2) : null;
        Drawable drawable3 = i7 != 0 ? AppCompatResources.getDrawable(context, i7) : null;
        Drawable drawable4 = i8 != 0 ? AppCompatResources.getDrawable(context, i8) : null;
        if (i10 != 0) {
            drawable = AppCompatResources.getDrawable(context, i10);
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawable2, drawable3, drawable4, drawable);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetCompoundDrawables();
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(int i2, int i7, int i8, int i10) {
        Context context = getContext();
        Drawable drawable = null;
        Drawable drawable2 = i2 != 0 ? AppCompatResources.getDrawable(context, i2) : null;
        Drawable drawable3 = i7 != 0 ? AppCompatResources.getDrawable(context, i7) : null;
        Drawable drawable4 = i8 != 0 ? AppCompatResources.getDrawable(context, i8) : null;
        if (i10 != 0) {
            drawable = AppCompatResources.getDrawable(context, i10);
        }
        setCompoundDrawablesWithIntrinsicBounds(drawable2, drawable3, drawable4, drawable);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetCompoundDrawables();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SuperCallerApi26 implements SuperCaller {
        public SuperCallerApi26() {
        }

        public int getAutoSizeMaxTextSize() {
            return AppCompatTextView.super.getAutoSizeMaxTextSize();
        }

        public int getAutoSizeMinTextSize() {
            return AppCompatTextView.super.getAutoSizeMinTextSize();
        }

        public int getAutoSizeStepGranularity() {
            return AppCompatTextView.super.getAutoSizeStepGranularity();
        }

        public int[] getAutoSizeTextAvailableSizes() {
            return AppCompatTextView.super.getAutoSizeTextAvailableSizes();
        }

        public int getAutoSizeTextType() {
            return AppCompatTextView.super.getAutoSizeTextType();
        }

        public TextClassifier getTextClassifier() {
            return AppCompatTextView.super.getTextClassifier();
        }

        public void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i7, int i8, int i10) {
            AppCompatTextView.super.setAutoSizeTextTypeUniformWithConfiguration(i2, i7, i8, i10);
        }

        public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) {
            AppCompatTextView.super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }

        public void setAutoSizeTextTypeWithDefaults(int i2) {
            AppCompatTextView.super.setAutoSizeTextTypeWithDefaults(i2);
        }

        public void setTextClassifier(TextClassifier textClassifier) {
            AppCompatTextView.super.setTextClassifier(textClassifier);
        }

        public void setLineHeight(int i2, float f) {
        }
    }
}
