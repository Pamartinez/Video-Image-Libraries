package androidx.appcompat.widget;

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
import androidx.core.content.res.ResourcesCompat;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ResourcesWrapper extends Resources {
    private final Resources mResources;

    public ResourcesWrapper(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mResources = resources;
    }

    public XmlResourceParser getAnimation(int i2) {
        return this.mResources.getAnimation(i2);
    }

    public boolean getBoolean(int i2) {
        return this.mResources.getBoolean(i2);
    }

    public int getColor(int i2) {
        return this.mResources.getColor(i2);
    }

    public ColorStateList getColorStateList(int i2) {
        return this.mResources.getColorStateList(i2);
    }

    public Configuration getConfiguration() {
        return this.mResources.getConfiguration();
    }

    public float getDimension(int i2) {
        return this.mResources.getDimension(i2);
    }

    public int getDimensionPixelOffset(int i2) {
        return this.mResources.getDimensionPixelOffset(i2);
    }

    public int getDimensionPixelSize(int i2) {
        return this.mResources.getDimensionPixelSize(i2);
    }

    public DisplayMetrics getDisplayMetrics() {
        return this.mResources.getDisplayMetrics();
    }

    public Drawable getDrawable(int i2, Resources.Theme theme) {
        return ResourcesCompat.getDrawable(this.mResources, i2, theme);
    }

    public final Drawable getDrawableCanonical(int i2) {
        return super.getDrawable(i2);
    }

    public Drawable getDrawableForDensity(int i2, int i7) {
        return ResourcesCompat.getDrawableForDensity(this.mResources, i2, i7, (Resources.Theme) null);
    }

    public float getFraction(int i2, int i7, int i8) {
        return this.mResources.getFraction(i2, i7, i8);
    }

    public int getIdentifier(String str, String str2, String str3) {
        return this.mResources.getIdentifier(str, str2, str3);
    }

    public int[] getIntArray(int i2) {
        return this.mResources.getIntArray(i2);
    }

    public int getInteger(int i2) {
        return this.mResources.getInteger(i2);
    }

    public XmlResourceParser getLayout(int i2) {
        return this.mResources.getLayout(i2);
    }

    public Movie getMovie(int i2) {
        return this.mResources.getMovie(i2);
    }

    public String getQuantityString(int i2, int i7, Object... objArr) {
        return this.mResources.getQuantityString(i2, i7, objArr);
    }

    public CharSequence getQuantityText(int i2, int i7) {
        return this.mResources.getQuantityText(i2, i7);
    }

    public String getResourceEntryName(int i2) {
        return this.mResources.getResourceEntryName(i2);
    }

    public String getResourceName(int i2) {
        return this.mResources.getResourceName(i2);
    }

    public String getResourcePackageName(int i2) {
        return this.mResources.getResourcePackageName(i2);
    }

    public String getResourceTypeName(int i2) {
        return this.mResources.getResourceTypeName(i2);
    }

    public String getString(int i2) {
        return this.mResources.getString(i2);
    }

    public String[] getStringArray(int i2) {
        return this.mResources.getStringArray(i2);
    }

    public CharSequence getText(int i2) {
        return this.mResources.getText(i2);
    }

    public CharSequence[] getTextArray(int i2) {
        return this.mResources.getTextArray(i2);
    }

    public void getValue(int i2, TypedValue typedValue, boolean z) {
        this.mResources.getValue(i2, typedValue, z);
    }

    public void getValueForDensity(int i2, int i7, TypedValue typedValue, boolean z) {
        this.mResources.getValueForDensity(i2, i7, typedValue, z);
    }

    public XmlResourceParser getXml(int i2) {
        return this.mResources.getXml(i2);
    }

    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.mResources.obtainAttributes(attributeSet, iArr);
    }

    public TypedArray obtainTypedArray(int i2) {
        return this.mResources.obtainTypedArray(i2);
    }

    public InputStream openRawResource(int i2) {
        return this.mResources.openRawResource(i2);
    }

    public AssetFileDescriptor openRawResourceFd(int i2) {
        return this.mResources.openRawResourceFd(i2);
    }

    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) {
        this.mResources.parseBundleExtra(str, attributeSet, bundle);
    }

    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) {
        this.mResources.parseBundleExtras(xmlResourceParser, bundle);
    }

    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.mResources;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    public Drawable getDrawableForDensity(int i2, int i7, Resources.Theme theme) {
        return ResourcesCompat.getDrawableForDensity(this.mResources, i2, i7, theme);
    }

    public String getQuantityString(int i2, int i7) {
        return this.mResources.getQuantityString(i2, i7);
    }

    public String getString(int i2, Object... objArr) {
        return this.mResources.getString(i2, objArr);
    }

    public CharSequence getText(int i2, CharSequence charSequence) {
        return this.mResources.getText(i2, charSequence);
    }

    public void getValue(String str, TypedValue typedValue, boolean z) {
        this.mResources.getValue(str, typedValue, z);
    }

    public InputStream openRawResource(int i2, TypedValue typedValue) {
        return this.mResources.openRawResource(i2, typedValue);
    }
}
