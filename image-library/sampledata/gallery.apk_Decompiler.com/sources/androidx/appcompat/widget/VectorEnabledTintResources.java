package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VectorEnabledTintResources extends ResourcesWrapper {
    private static boolean sCompatVectorFromResourcesEnabled = false;
    private final WeakReference<Context> mContextRef;

    public VectorEnabledTintResources(Context context, Resources resources) {
        super(resources);
        this.mContextRef = new WeakReference<>(context);
    }

    public static boolean isCompatVectorFromResourcesEnabled() {
        return sCompatVectorFromResourcesEnabled;
    }

    public static boolean shouldBeUsed() {
        isCompatVectorFromResourcesEnabled();
        return false;
    }

    public /* bridge */ /* synthetic */ XmlResourceParser getAnimation(int i2) {
        return super.getAnimation(i2);
    }

    public /* bridge */ /* synthetic */ boolean getBoolean(int i2) {
        return super.getBoolean(i2);
    }

    public /* bridge */ /* synthetic */ int getColor(int i2) {
        return super.getColor(i2);
    }

    public /* bridge */ /* synthetic */ ColorStateList getColorStateList(int i2) {
        return super.getColorStateList(i2);
    }

    public /* bridge */ /* synthetic */ Configuration getConfiguration() {
        return super.getConfiguration();
    }

    public /* bridge */ /* synthetic */ float getDimension(int i2) {
        return super.getDimension(i2);
    }

    public /* bridge */ /* synthetic */ int getDimensionPixelOffset(int i2) {
        return super.getDimensionPixelOffset(i2);
    }

    public /* bridge */ /* synthetic */ int getDimensionPixelSize(int i2) {
        return super.getDimensionPixelSize(i2);
    }

    public /* bridge */ /* synthetic */ DisplayMetrics getDisplayMetrics() {
        return super.getDisplayMetrics();
    }

    public /* bridge */ /* synthetic */ Drawable getDrawable(int i2, Resources.Theme theme) {
        return super.getDrawable(i2, theme);
    }

    public /* bridge */ /* synthetic */ Drawable getDrawableForDensity(int i2, int i7) {
        return super.getDrawableForDensity(i2, i7);
    }

    public /* bridge */ /* synthetic */ float getFraction(int i2, int i7, int i8) {
        return super.getFraction(i2, i7, i8);
    }

    public /* bridge */ /* synthetic */ int getIdentifier(String str, String str2, String str3) {
        return super.getIdentifier(str, str2, str3);
    }

    public /* bridge */ /* synthetic */ int[] getIntArray(int i2) {
        return super.getIntArray(i2);
    }

    public /* bridge */ /* synthetic */ int getInteger(int i2) {
        return super.getInteger(i2);
    }

    public /* bridge */ /* synthetic */ XmlResourceParser getLayout(int i2) {
        return super.getLayout(i2);
    }

    public /* bridge */ /* synthetic */ Movie getMovie(int i2) {
        return super.getMovie(i2);
    }

    public /* bridge */ /* synthetic */ String getQuantityString(int i2, int i7) {
        return super.getQuantityString(i2, i7);
    }

    public /* bridge */ /* synthetic */ CharSequence getQuantityText(int i2, int i7) {
        return super.getQuantityText(i2, i7);
    }

    public /* bridge */ /* synthetic */ String getResourceEntryName(int i2) {
        return super.getResourceEntryName(i2);
    }

    public /* bridge */ /* synthetic */ String getResourceName(int i2) {
        return super.getResourceName(i2);
    }

    public /* bridge */ /* synthetic */ String getResourcePackageName(int i2) {
        return super.getResourcePackageName(i2);
    }

    public /* bridge */ /* synthetic */ String getResourceTypeName(int i2) {
        return super.getResourceTypeName(i2);
    }

    public /* bridge */ /* synthetic */ String getString(int i2) {
        return super.getString(i2);
    }

    public /* bridge */ /* synthetic */ String[] getStringArray(int i2) {
        return super.getStringArray(i2);
    }

    public /* bridge */ /* synthetic */ CharSequence getText(int i2) {
        return super.getText(i2);
    }

    public /* bridge */ /* synthetic */ CharSequence[] getTextArray(int i2) {
        return super.getTextArray(i2);
    }

    public /* bridge */ /* synthetic */ void getValue(int i2, TypedValue typedValue, boolean z) {
        super.getValue(i2, typedValue, z);
    }

    public /* bridge */ /* synthetic */ void getValueForDensity(int i2, int i7, TypedValue typedValue, boolean z) {
        super.getValueForDensity(i2, i7, typedValue, z);
    }

    public /* bridge */ /* synthetic */ XmlResourceParser getXml(int i2) {
        return super.getXml(i2);
    }

    public /* bridge */ /* synthetic */ TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return super.obtainAttributes(attributeSet, iArr);
    }

    public /* bridge */ /* synthetic */ TypedArray obtainTypedArray(int i2) {
        return super.obtainTypedArray(i2);
    }

    public /* bridge */ /* synthetic */ InputStream openRawResource(int i2) {
        return super.openRawResource(i2);
    }

    public /* bridge */ /* synthetic */ AssetFileDescriptor openRawResourceFd(int i2) {
        return super.openRawResourceFd(i2);
    }

    public /* bridge */ /* synthetic */ void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) {
        super.parseBundleExtra(str, attributeSet, bundle);
    }

    public /* bridge */ /* synthetic */ void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) {
        super.parseBundleExtras(xmlResourceParser, bundle);
    }

    public /* bridge */ /* synthetic */ void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
    }

    public Drawable getDrawable(int i2) {
        Context context = this.mContextRef.get();
        if (context != null) {
            return ResourceManagerInternal.get().onDrawableLoadedFromResources(context, this, i2);
        }
        return getDrawableCanonical(i2);
    }

    public /* bridge */ /* synthetic */ Drawable getDrawableForDensity(int i2, int i7, Resources.Theme theme) {
        return super.getDrawableForDensity(i2, i7, theme);
    }

    public /* bridge */ /* synthetic */ String getQuantityString(int i2, int i7, Object[] objArr) {
        return super.getQuantityString(i2, i7, objArr);
    }

    public /* bridge */ /* synthetic */ String getString(int i2, Object[] objArr) {
        return super.getString(i2, objArr);
    }

    public /* bridge */ /* synthetic */ CharSequence getText(int i2, CharSequence charSequence) {
        return super.getText(i2, charSequence);
    }

    public /* bridge */ /* synthetic */ void getValue(String str, TypedValue typedValue, boolean z) {
        super.getValue(str, typedValue, z);
    }

    public /* bridge */ /* synthetic */ InputStream openRawResource(int i2, TypedValue typedValue) {
        return super.openRawResource(i2, typedValue);
    }
}
