package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.LocaleList;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.collection.LruCache;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Pair;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AppCompatTextHelper {
    private boolean mAsyncFontPending;
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private String mFontVariationSettings = null;
    private int mFontWeight = -1;
    private int mStyle = 0;
    private final TextView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static LocaleList forLanguageTags(String str) {
            return LocaleList.forLanguageTags(str);
        }

        public static void setTextLocales(TextView textView, LocaleList localeList) {
            textView.setTextLocales(localeList);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        private static Paint sPaint;
        private static final LruCache<Pair<Typeface, String>, Typeface> sVariationsCache = new LruCache<>(30);

        public static Typeface createVariationInstance(Typeface typeface, String str) {
            Pair pair = new Pair(typeface, str);
            LruCache<Pair<Typeface, String>, Typeface> lruCache = sVariationsCache;
            Typeface typeface2 = lruCache.get(pair);
            if (typeface2 != null) {
                return typeface2;
            }
            Paint paint = sPaint;
            if (paint == null) {
                paint = new Paint();
                sPaint = paint;
            }
            if (Objects.equals(paint.getFontVariationSettings(), str)) {
                paint.setFontVariationSettings((String) null);
            }
            paint.setTypeface(typeface);
            if (!paint.setFontVariationSettings(str)) {
                return null;
            }
            Typeface typeface3 = paint.getTypeface();
            lruCache.put(pair, typeface3);
            return typeface3;
        }

        public static int getAutoSizeStepGranularity(TextView textView) {
            return textView.getAutoSizeStepGranularity();
        }

        public static String getFontVariationSettings(TextView textView) {
            return textView.getFontVariationSettings();
        }

        public static void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int i2, int i7, int i8, int i10) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i2, i7, i8, i10);
        }

        public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, int[] iArr, int i2) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }

        public static boolean setFontVariationSettings(TextView textView, String str) {
            if (Objects.equals(textView.getFontVariationSettings(), str)) {
                textView.setFontVariationSettings("");
            }
            return textView.setFontVariationSettings(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static Typeface create(Typeface typeface, int i2, boolean z) {
            return Typeface.create(typeface, i2, z);
        }
    }

    public AppCompatTextHelper(TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView);
    }

    private void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable != null && tintInfo != null) {
            AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        }
    }

    private void applyFontAndVariationSettings(boolean z) {
        Typeface typeface = this.mFontTypeface;
        if (typeface != null) {
            if (this.mFontWeight == -1) {
                this.mView.setTypeface(typeface, this.mStyle);
            } else {
                this.mView.setTypeface(typeface);
            }
        } else if (z) {
            this.mView.setTypeface((Typeface) null);
        }
        String str = this.mFontVariationSettings;
        if (str != null) {
            Api26Impl.setFontVariationSettings(this.mView, str);
        }
    }

    /* access modifiers changed from: private */
    public static void applyNewTypefacePreservingVariationSettings(TextView textView, Typeface typeface, int i2) {
        String fontVariationSettings = Api26Impl.getFontVariationSettings(textView);
        if (!TextUtils.isEmpty(fontVariationSettings)) {
            Api26Impl.setFontVariationSettings(textView, (String) null);
        }
        textView.setTypeface(typeface, i2);
        if (!TextUtils.isEmpty(fontVariationSettings)) {
            Api26Impl.setFontVariationSettings(textView, fontVariationSettings);
        }
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i2) {
        ColorStateList tintList = appCompatDrawableManager.getTintList(context, i2);
        if (tintList == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = tintList;
        return tintInfo;
    }

    private ResourcesCompat.FontCallback makeFontCallback(final int i2, final int i7) {
        final WeakReference weakReference = new WeakReference(this.mView);
        return new ResourcesCompat.FontCallback() {
            public void onFontRetrieved(Typeface typeface) {
                boolean z;
                int i2 = i2;
                if (i2 != -1) {
                    if ((i7 & 2) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    typeface = Api28Impl.create(typeface, i2, z);
                }
                AppCompatTextHelper.this.onAsyncTypefaceReceived(weakReference, typeface);
            }

            public void onFontRetrievalFailed(int i2) {
            }
        };
    }

    private void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 != null || drawable6 != null) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            TextView textView = this.mView;
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
        } else if (drawable != null || drawable2 != null || drawable3 != null || drawable4 != null) {
            Drawable[] compoundDrawablesRelative2 = this.mView.getCompoundDrawablesRelative();
            Drawable drawable7 = compoundDrawablesRelative2[0];
            if (drawable7 == null && compoundDrawablesRelative2[2] == null) {
                Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
                TextView textView2 = this.mView;
                if (drawable == null) {
                    drawable = compoundDrawables[0];
                }
                if (drawable2 == null) {
                    drawable2 = compoundDrawables[1];
                }
                if (drawable3 == null) {
                    drawable3 = compoundDrawables[2];
                }
                if (drawable4 == null) {
                    drawable4 = compoundDrawables[3];
                }
                textView2.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                return;
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative2[1];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative2[3];
            }
            this.mView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, compoundDrawablesRelative2[2], drawable4);
        }
    }

    private void setCompoundTints() {
        TintInfo tintInfo = this.mDrawableTint;
        this.mDrawableLeftTint = tintInfo;
        this.mDrawableTopTint = tintInfo;
        this.mDrawableRightTint = tintInfo;
        this.mDrawableBottomTint = tintInfo;
        this.mDrawableStartTint = tintInfo;
        this.mDrawableEndTint = tintInfo;
    }

    private void setTextSizeInternal(int i2, float f) {
        this.mAutoSizeTextHelper.setTextSizeInternal(i2, f);
    }

    private boolean updateTypefaceAndStyle(Context context, TintTypedArray tintTypedArray) {
        String string;
        boolean z;
        boolean z3;
        Typeface typeface;
        this.mStyle = tintTypedArray.getInt(R$styleable.TextAppearance_android_textStyle, this.mStyle);
        int i2 = tintTypedArray.getInt(R$styleable.TextAppearance_android_textFontWeight, -1);
        this.mFontWeight = i2;
        if (i2 != -1) {
            this.mStyle &= 2;
        }
        int i7 = R$styleable.TextAppearance_fontVariationSettings;
        if (tintTypedArray.hasValue(i7)) {
            this.mFontVariationSettings = tintTypedArray.getString(i7);
        }
        int i8 = R$styleable.TextAppearance_android_fontFamily;
        boolean z7 = false;
        if (tintTypedArray.hasValue(i8) || tintTypedArray.hasValue(R$styleable.TextAppearance_fontFamily)) {
            this.mFontTypeface = null;
            int i10 = R$styleable.TextAppearance_fontFamily;
            if (tintTypedArray.hasValue(i10)) {
                i8 = i10;
            }
            int i11 = this.mFontWeight;
            int i12 = this.mStyle;
            if (!context.isRestricted()) {
                try {
                    Typeface font = tintTypedArray.getFont(i8, this.mStyle, makeFontCallback(i11, i12));
                    if (font != null) {
                        if (this.mFontWeight != -1) {
                            Typeface create = Typeface.create(font, 0);
                            int i13 = this.mFontWeight;
                            if ((this.mStyle & 2) != 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            this.mFontTypeface = Api28Impl.create(create, i13, z3);
                        } else {
                            this.mFontTypeface = font;
                        }
                    }
                    if (this.mFontTypeface == null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.mAsyncFontPending = z;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.mFontTypeface == null && (string = tintTypedArray.getString(i8)) != null) {
                if (this.mFontWeight != -1) {
                    Typeface create2 = Typeface.create(string, 0);
                    int i14 = this.mFontWeight;
                    if ((this.mStyle & 2) != 0) {
                        z7 = true;
                    }
                    this.mFontTypeface = Api28Impl.create(create2, i14, z7);
                } else {
                    this.mFontTypeface = Typeface.create(string, this.mStyle);
                }
            }
            return true;
        }
        int i15 = R$styleable.TextAppearance_android_typeface;
        if (tintTypedArray.hasValue(i15)) {
            this.mAsyncFontPending = false;
            int i16 = tintTypedArray.getInt(i15, 1);
            if (i16 == 1) {
                this.mFontTypeface = Typeface.SANS_SERIF;
            } else if (i16 == 2) {
                this.mFontTypeface = Typeface.SERIF;
            } else if (i16 == 3) {
                this.mFontTypeface = Typeface.MONOSPACE;
            }
            return true;
        }
        int i17 = this.mFontWeight;
        if (i17 == -1 || (typeface = this.mFontTypeface) == null) {
            return false;
        }
        if ((this.mStyle & 2) != 0) {
            z7 = true;
        }
        this.mFontTypeface = Api28Impl.create(typeface, i17, z7);
        return true;
    }

    public void applyCompoundDrawablesTints() {
        if (!(this.mDrawableLeftTint == null && this.mDrawableTopTint == null && this.mDrawableRightTint == null && this.mDrawableBottomTint == null)) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
            applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
        }
    }

    public void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    public int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    public int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    public int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    public int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    public ColorStateList getCompoundDrawableTintList() {
        TintInfo tintInfo = this.mDrawableTint;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    public PorterDuff.Mode getCompoundDrawableTintMode() {
        TintInfo tintInfo = this.mDrawableTint;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    public boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x0226  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x022d  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x023d  */
    /* JADX WARNING: Removed duplicated region for block: B:115:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x017c  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0207  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.util.AttributeSet r14, int r15) {
        /*
            r13 = this;
            android.widget.TextView r0 = r13.mView
            android.content.Context r0 = r0.getContext()
            androidx.appcompat.widget.AppCompatDrawableManager r1 = androidx.appcompat.widget.AppCompatDrawableManager.get()
            int[] r4 = androidx.appcompat.R$styleable.AppCompatTextHelper
            r9 = 0
            androidx.appcompat.widget.TintTypedArray r10 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r14, r4, r15, r9)
            android.widget.TextView r2 = r13.mView
            android.content.Context r3 = r2.getContext()
            android.content.res.TypedArray r6 = r10.getWrappedTypeArray()
            r8 = 0
            r5 = r14
            r7 = r15
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r2, r3, r4, r5, r6, r7, r8)
            int r14 = androidx.appcompat.R$styleable.AppCompatTextHelper_android_textAppearance
            r15 = -1
            int r14 = r10.getResourceId(r14, r15)
            int r2 = androidx.appcompat.R$styleable.AppCompatTextHelper_android_drawableLeft
            boolean r3 = r10.hasValue(r2)
            if (r3 == 0) goto L_0x003a
            int r2 = r10.getResourceId(r2, r9)
            androidx.appcompat.widget.TintInfo r2 = createTintInfo(r0, r1, r2)
            r13.mDrawableLeftTint = r2
        L_0x003a:
            int r2 = androidx.appcompat.R$styleable.AppCompatTextHelper_android_drawableTop
            boolean r3 = r10.hasValue(r2)
            if (r3 == 0) goto L_0x004c
            int r2 = r10.getResourceId(r2, r9)
            androidx.appcompat.widget.TintInfo r2 = createTintInfo(r0, r1, r2)
            r13.mDrawableTopTint = r2
        L_0x004c:
            int r2 = androidx.appcompat.R$styleable.AppCompatTextHelper_android_drawableRight
            boolean r3 = r10.hasValue(r2)
            if (r3 == 0) goto L_0x005e
            int r2 = r10.getResourceId(r2, r9)
            androidx.appcompat.widget.TintInfo r2 = createTintInfo(r0, r1, r2)
            r13.mDrawableRightTint = r2
        L_0x005e:
            int r2 = androidx.appcompat.R$styleable.AppCompatTextHelper_android_drawableBottom
            boolean r3 = r10.hasValue(r2)
            if (r3 == 0) goto L_0x0070
            int r2 = r10.getResourceId(r2, r9)
            androidx.appcompat.widget.TintInfo r2 = createTintInfo(r0, r1, r2)
            r13.mDrawableBottomTint = r2
        L_0x0070:
            int r2 = androidx.appcompat.R$styleable.AppCompatTextHelper_android_drawableStart
            boolean r3 = r10.hasValue(r2)
            if (r3 == 0) goto L_0x0082
            int r2 = r10.getResourceId(r2, r9)
            androidx.appcompat.widget.TintInfo r2 = createTintInfo(r0, r1, r2)
            r13.mDrawableStartTint = r2
        L_0x0082:
            int r2 = androidx.appcompat.R$styleable.AppCompatTextHelper_android_drawableEnd
            boolean r3 = r10.hasValue(r2)
            if (r3 == 0) goto L_0x0094
            int r2 = r10.getResourceId(r2, r9)
            androidx.appcompat.widget.TintInfo r2 = createTintInfo(r0, r1, r2)
            r13.mDrawableEndTint = r2
        L_0x0094:
            r10.recycle()
            android.widget.TextView r2 = r13.mView
            android.text.method.TransformationMethod r2 = r2.getTransformationMethod()
            boolean r2 = r2 instanceof android.text.method.PasswordTransformationMethod
            r3 = 1
            r4 = 0
            if (r14 == r15) goto L_0x00d0
            int[] r6 = androidx.appcompat.R$styleable.TextAppearance
            androidx.appcompat.widget.TintTypedArray r14 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r0, (int) r14, (int[]) r6)
            if (r2 != 0) goto L_0x00b9
            int r6 = androidx.appcompat.R$styleable.TextAppearance_textAllCaps
            boolean r8 = r14.hasValue(r6)
            if (r8 == 0) goto L_0x00b9
            boolean r6 = r14.getBoolean(r6, r9)
            r8 = r3
            goto L_0x00bb
        L_0x00b9:
            r6 = r9
            r8 = r6
        L_0x00bb:
            r13.updateTypefaceAndStyle(r0, r14)
            int r10 = androidx.appcompat.R$styleable.TextAppearance_textLocale
            boolean r11 = r14.hasValue(r10)
            if (r11 == 0) goto L_0x00cb
            java.lang.String r10 = r14.getString(r10)
            goto L_0x00cc
        L_0x00cb:
            r10 = r4
        L_0x00cc:
            r14.recycle()
            goto L_0x00d3
        L_0x00d0:
            r10 = r4
            r6 = r9
            r8 = r6
        L_0x00d3:
            int[] r14 = androidx.appcompat.R$styleable.TextAppearance
            androidx.appcompat.widget.TintTypedArray r14 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r5, r14, r7, r9)
            if (r2 != 0) goto L_0x00e8
            int r11 = androidx.appcompat.R$styleable.TextAppearance_textAllCaps
            boolean r12 = r14.hasValue(r11)
            if (r12 == 0) goto L_0x00e8
            boolean r6 = r14.getBoolean(r11, r9)
            goto L_0x00e9
        L_0x00e8:
            r3 = r8
        L_0x00e9:
            int r8 = androidx.appcompat.R$styleable.TextAppearance_textLocale
            boolean r11 = r14.hasValue(r8)
            if (r11 == 0) goto L_0x00f5
            java.lang.String r10 = r14.getString(r8)
        L_0x00f5:
            int r8 = androidx.appcompat.R$styleable.TextAppearance_android_textSize
            boolean r11 = r14.hasValue(r8)
            if (r11 == 0) goto L_0x0109
            int r8 = r14.getDimensionPixelSize(r8, r15)
            if (r8 != 0) goto L_0x0109
            android.widget.TextView r8 = r13.mView
            r11 = 0
            r8.setTextSize(r9, r11)
        L_0x0109:
            r13.updateTypefaceAndStyle(r0, r14)
            r14.recycle()
            if (r2 != 0) goto L_0x0116
            if (r3 == 0) goto L_0x0116
            r13.setAllCaps(r6)
        L_0x0116:
            r13.applyFontAndVariationSettings(r9)
            if (r10 == 0) goto L_0x0124
            android.widget.TextView r14 = r13.mView
            android.os.LocaleList r2 = androidx.appcompat.widget.AppCompatTextHelper.Api24Impl.forLanguageTags(r10)
            androidx.appcompat.widget.AppCompatTextHelper.Api24Impl.setTextLocales(r14, r2)
        L_0x0124:
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r14 = r13.mAutoSizeTextHelper
            r14.loadFromAttributes(r5, r7)
            boolean r14 = androidx.appcompat.widget.ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r14 == 0) goto L_0x0168
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r14 = r13.mAutoSizeTextHelper
            int r14 = r14.getAutoSizeTextType()
            if (r14 == 0) goto L_0x0168
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r14 = r13.mAutoSizeTextHelper
            int[] r14 = r14.getAutoSizeTextAvailableSizes()
            int r3 = r14.length
            if (r3 <= 0) goto L_0x0168
            android.widget.TextView r3 = r13.mView
            int r3 = androidx.appcompat.widget.AppCompatTextHelper.Api26Impl.getAutoSizeStepGranularity(r3)
            float r3 = (float) r3
            int r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r3 == 0) goto L_0x0163
            android.widget.TextView r14 = r13.mView
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r3 = r13.mAutoSizeTextHelper
            int r3 = r3.getAutoSizeMinTextSize()
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r6 = r13.mAutoSizeTextHelper
            int r6 = r6.getAutoSizeMaxTextSize()
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r7 = r13.mAutoSizeTextHelper
            int r7 = r7.getAutoSizeStepGranularity()
            androidx.appcompat.widget.AppCompatTextHelper.Api26Impl.setAutoSizeTextTypeUniformWithConfiguration(r14, r3, r6, r7, r9)
            goto L_0x0168
        L_0x0163:
            android.widget.TextView r3 = r13.mView
            androidx.appcompat.widget.AppCompatTextHelper.Api26Impl.setAutoSizeTextTypeUniformWithPresetSizes(r3, r14, r9)
        L_0x0168:
            int[] r14 = androidx.appcompat.R$styleable.AppCompatTextView
            androidx.appcompat.widget.TintTypedArray r14 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r0, (android.util.AttributeSet) r5, (int[]) r14)
            int r3 = androidx.appcompat.R$styleable.AppCompatTextView_drawableLeftCompat
            int r3 = r14.getResourceId(r3, r15)
            if (r3 == r15) goto L_0x017c
            android.graphics.drawable.Drawable r3 = r1.getDrawable(r0, r3)
            r6 = r3
            goto L_0x017d
        L_0x017c:
            r6 = r4
        L_0x017d:
            int r3 = androidx.appcompat.R$styleable.AppCompatTextView_drawableTopCompat
            int r3 = r14.getResourceId(r3, r15)
            if (r3 == r15) goto L_0x018b
            android.graphics.drawable.Drawable r3 = r1.getDrawable(r0, r3)
            r7 = r3
            goto L_0x018c
        L_0x018b:
            r7 = r4
        L_0x018c:
            int r3 = androidx.appcompat.R$styleable.AppCompatTextView_drawableRightCompat
            int r3 = r14.getResourceId(r3, r15)
            if (r3 == r15) goto L_0x019a
            android.graphics.drawable.Drawable r3 = r1.getDrawable(r0, r3)
            r8 = r3
            goto L_0x019b
        L_0x019a:
            r8 = r4
        L_0x019b:
            int r3 = androidx.appcompat.R$styleable.AppCompatTextView_drawableBottomCompat
            int r3 = r14.getResourceId(r3, r15)
            if (r3 == r15) goto L_0x01a9
            android.graphics.drawable.Drawable r3 = r1.getDrawable(r0, r3)
            r9 = r3
            goto L_0x01aa
        L_0x01a9:
            r9 = r4
        L_0x01aa:
            int r3 = androidx.appcompat.R$styleable.AppCompatTextView_drawableStartCompat
            int r3 = r14.getResourceId(r3, r15)
            if (r3 == r15) goto L_0x01b8
            android.graphics.drawable.Drawable r3 = r1.getDrawable(r0, r3)
            r10 = r3
            goto L_0x01b9
        L_0x01b8:
            r10 = r4
        L_0x01b9:
            int r3 = androidx.appcompat.R$styleable.AppCompatTextView_drawableEndCompat
            int r3 = r14.getResourceId(r3, r15)
            if (r3 == r15) goto L_0x01c8
            android.graphics.drawable.Drawable r0 = r1.getDrawable(r0, r3)
            r11 = r0
        L_0x01c6:
            r5 = r13
            goto L_0x01ca
        L_0x01c8:
            r11 = r4
            goto L_0x01c6
        L_0x01ca:
            r5.setCompoundDrawables(r6, r7, r8, r9, r10, r11)
            int r13 = androidx.appcompat.R$styleable.AppCompatTextView_drawableTint
            boolean r0 = r14.hasValue(r13)
            if (r0 == 0) goto L_0x01de
            android.content.res.ColorStateList r13 = r14.getColorStateList(r13)
            android.widget.TextView r0 = r5.mView
            androidx.core.widget.TextViewCompat.setCompoundDrawableTintList(r0, r13)
        L_0x01de:
            int r13 = androidx.appcompat.R$styleable.AppCompatTextView_drawableTintMode
            boolean r0 = r14.hasValue(r13)
            if (r0 == 0) goto L_0x01f3
            int r13 = r14.getInt(r13, r15)
            android.graphics.PorterDuff$Mode r13 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r13, r4)
            android.widget.TextView r0 = r5.mView
            androidx.core.widget.TextViewCompat.setCompoundDrawableTintMode(r0, r13)
        L_0x01f3:
            int r13 = androidx.appcompat.R$styleable.AppCompatTextView_firstBaselineToTopHeight
            int r13 = r14.getDimensionPixelSize(r13, r15)
            int r0 = androidx.appcompat.R$styleable.AppCompatTextView_lastBaselineToBottomHeight
            int r0 = r14.getDimensionPixelSize(r0, r15)
            int r1 = androidx.appcompat.R$styleable.AppCompatTextView_lineHeight
            boolean r3 = r14.hasValue(r1)
            if (r3 == 0) goto L_0x0226
            android.util.TypedValue r3 = r14.peekValue(r1)
            if (r3 == 0) goto L_0x021f
            int r4 = r3.type
            r6 = 5
            if (r4 != r6) goto L_0x021f
            int r1 = r3.data
            int r1 = androidx.core.util.TypedValueCompat.getUnitFromComplexDimension(r1)
            int r3 = r3.data
            float r3 = android.util.TypedValue.complexToFloat(r3)
            goto L_0x0228
        L_0x021f:
            int r1 = r14.getDimensionPixelSize(r1, r15)
            float r3 = (float) r1
            r1 = r15
            goto L_0x0228
        L_0x0226:
            r1 = r15
            r3 = r2
        L_0x0228:
            r14.recycle()
            if (r13 == r15) goto L_0x0232
            android.widget.TextView r14 = r5.mView
            androidx.core.widget.TextViewCompat.setFirstBaselineToTopHeight(r14, r13)
        L_0x0232:
            if (r0 == r15) goto L_0x0239
            android.widget.TextView r13 = r5.mView
            androidx.core.widget.TextViewCompat.setLastBaselineToBottomHeight(r13, r0)
        L_0x0239:
            int r13 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r13 == 0) goto L_0x024b
            if (r1 != r15) goto L_0x0246
            android.widget.TextView r13 = r5.mView
            int r14 = (int) r3
            androidx.core.widget.TextViewCompat.setLineHeight(r13, r14)
            return
        L_0x0246:
            android.widget.TextView r13 = r5.mView
            androidx.core.widget.TextViewCompat.setLineHeight(r13, r1, r3)
        L_0x024b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    public void onAsyncTypefaceReceived(WeakReference<TextView> weakReference, final Typeface typeface) {
        if (this.mAsyncFontPending) {
            this.mFontTypeface = typeface;
            final TextView textView = weakReference.get();
            if (textView == null) {
                return;
            }
            if (textView.isAttachedToWindow()) {
                final int i2 = this.mStyle;
                textView.post(new Runnable() {
                    public void run() {
                        AppCompatTextHelper.applyNewTypefacePreservingVariationSettings(textView, typeface, i2);
                    }
                });
                return;
            }
            applyNewTypefacePreservingVariationSettings(textView, typeface, this.mStyle);
        }
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        if (!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            autoSizeText();
        }
    }

    public void onSetCompoundDrawables() {
        applyCompoundDrawablesTints();
    }

    public void onSetTextAppearance(Context context, int i2) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i2, R$styleable.TextAppearance);
        int i7 = R$styleable.TextAppearance_textAllCaps;
        if (obtainStyledAttributes.hasValue(i7)) {
            setAllCaps(obtainStyledAttributes.getBoolean(i7, false));
        }
        int i8 = R$styleable.TextAppearance_android_textSize;
        if (obtainStyledAttributes.hasValue(i8) && obtainStyledAttributes.getDimensionPixelSize(i8, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        boolean updateTypefaceAndStyle = updateTypefaceAndStyle(context, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        applyFontAndVariationSettings(updateTypefaceAndStyle);
    }

    public void setAllCaps(boolean z) {
        this.mView.setAllCaps(z);
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i7, int i8, int i10) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i2, i7, i8, i10);
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
    }

    public void setAutoSizeTextTypeWithDefaults(int i2) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(i2);
    }

    public void setCompoundDrawableTintList(ColorStateList colorStateList) {
        boolean z;
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        TintInfo tintInfo = this.mDrawableTint;
        tintInfo.mTintList = colorStateList;
        if (colorStateList != null) {
            z = true;
        } else {
            z = false;
        }
        tintInfo.mHasTintList = z;
        setCompoundTints();
    }

    public void setCompoundDrawableTintMode(PorterDuff.Mode mode) {
        boolean z;
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        TintInfo tintInfo = this.mDrawableTint;
        tintInfo.mTintMode = mode;
        if (mode != null) {
            z = true;
        } else {
            z = false;
        }
        tintInfo.mHasTintMode = z;
        setCompoundTints();
    }

    public void setTextSize(int i2, float f) {
        if (!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE && !isAutoSizeEnabled()) {
            setTextSizeInternal(i2, f);
        }
    }

    public void populateSurroundingTextIfNeeded(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
    }
}
