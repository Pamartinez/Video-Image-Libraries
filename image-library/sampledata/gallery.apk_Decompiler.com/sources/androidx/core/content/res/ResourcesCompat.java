package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.util.WeakHashMap;
import n5.f;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ResourcesCompat {
    private static final Object sColorStateCacheLock = new Object();
    private static final WeakHashMap<ColorStateListCacheKey, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = new WeakHashMap<>(0);
    private static final ThreadLocal<TypedValue> sTempTypedValue = new ThreadLocal<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static Drawable getDrawable(Resources resources, int i2, Resources.Theme theme) {
            return resources.getDrawable(i2, theme);
        }

        public static Drawable getDrawableForDensity(Resources resources, int i2, int i7, Resources.Theme theme) {
            return resources.getDrawableForDensity(i2, i7, theme);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static int getColor(Resources resources, int i2, Resources.Theme theme) {
            return resources.getColor(i2, theme);
        }

        public static ColorStateList getColorStateList(Resources resources, int i2, Resources.Theme theme) {
            return resources.getColorStateList(i2, theme);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static float getFloat(Resources resources, int i2) {
            return resources.getFloat(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ColorStateListCacheEntry {
        final Configuration mConfiguration;
        final int mThemeHash;
        final ColorStateList mValue;

        public ColorStateListCacheEntry(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
            int i2;
            this.mValue = colorStateList;
            this.mConfiguration = new Configuration(configuration);
            if (theme == null) {
                i2 = 0;
            } else {
                i2 = theme.hashCode();
            }
            this.mThemeHash = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ColorStateListCacheKey {
        final Resources mResources;
        final Resources.Theme mTheme;

        public ColorStateListCacheKey(Resources resources, Resources.Theme theme) {
            this.mResources = resources;
            this.mTheme = theme;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && ColorStateListCacheKey.class == obj.getClass()) {
                ColorStateListCacheKey colorStateListCacheKey = (ColorStateListCacheKey) obj;
                if (!this.mResources.equals(colorStateListCacheKey.mResources) || !ObjectsCompat.equals(this.mTheme, colorStateListCacheKey.mTheme)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mResources, this.mTheme);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class FontCallback {
        public static Handler getHandler(Handler handler) {
            if (handler == null) {
                return new Handler(Looper.getMainLooper());
            }
            return handler;
        }

        public final void callbackFailAsync(int i2, Handler handler) {
            getHandler(handler).post(new f(this, i2, 4));
        }

        public final void callbackSuccessAsync(Typeface typeface, Handler handler) {
            getHandler(handler).post(new e(18, this, typeface));
        }

        /* renamed from: onFontRetrievalFailed */
        public abstract void lambda$callbackFailAsync$1(int i2);

        /* renamed from: onFontRetrieved */
        public abstract void lambda$callbackSuccessAsync$0(Typeface typeface);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ThemeCompat {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Api29Impl {
            public static void rebase(Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void rebase(Resources.Theme theme) {
            Api29Impl.rebase(theme);
        }
    }

    private static void addColorStateListToCache(ColorStateListCacheKey colorStateListCacheKey, int i2, ColorStateList colorStateList, Resources.Theme theme) {
        synchronized (sColorStateCacheLock) {
            try {
                WeakHashMap<ColorStateListCacheKey, SparseArray<ColorStateListCacheEntry>> weakHashMap = sColorStateCaches;
                SparseArray sparseArray = weakHashMap.get(colorStateListCacheKey);
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                    weakHashMap.put(colorStateListCacheKey, sparseArray);
                }
                sparseArray.append(i2, new ColorStateListCacheEntry(colorStateList, colorStateListCacheKey.mResources.getConfiguration(), theme));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        if (r2.mThemeHash == r5.hashCode()) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0046, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.res.ColorStateList getCachedColorStateList(androidx.core.content.res.ResourcesCompat.ColorStateListCacheKey r5, int r6) {
        /*
            java.lang.Object r0 = sColorStateCacheLock
            monitor-enter(r0)
            java.util.WeakHashMap<androidx.core.content.res.ResourcesCompat$ColorStateListCacheKey, android.util.SparseArray<androidx.core.content.res.ResourcesCompat$ColorStateListCacheEntry>> r1 = sColorStateCaches     // Catch:{ all -> 0x0032 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0032 }
            android.util.SparseArray r1 = (android.util.SparseArray) r1     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0045
            int r2 = r1.size()     // Catch:{ all -> 0x0032 }
            if (r2 <= 0) goto L_0x0045
            java.lang.Object r2 = r1.get(r6)     // Catch:{ all -> 0x0032 }
            androidx.core.content.res.ResourcesCompat$ColorStateListCacheEntry r2 = (androidx.core.content.res.ResourcesCompat.ColorStateListCacheEntry) r2     // Catch:{ all -> 0x0032 }
            if (r2 == 0) goto L_0x0045
            android.content.res.Configuration r3 = r2.mConfiguration     // Catch:{ all -> 0x0032 }
            android.content.res.Resources r4 = r5.mResources     // Catch:{ all -> 0x0032 }
            android.content.res.Configuration r4 = r4.getConfiguration()     // Catch:{ all -> 0x0032 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0032 }
            if (r3 == 0) goto L_0x0042
            android.content.res.Resources$Theme r5 = r5.mTheme     // Catch:{ all -> 0x0032 }
            if (r5 != 0) goto L_0x0034
            int r3 = r2.mThemeHash     // Catch:{ all -> 0x0032 }
            if (r3 == 0) goto L_0x003e
            goto L_0x0034
        L_0x0032:
            r5 = move-exception
            goto L_0x0048
        L_0x0034:
            if (r5 == 0) goto L_0x0042
            int r3 = r2.mThemeHash     // Catch:{ all -> 0x0032 }
            int r5 = r5.hashCode()     // Catch:{ all -> 0x0032 }
            if (r3 != r5) goto L_0x0042
        L_0x003e:
            android.content.res.ColorStateList r5 = r2.mValue     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            return r5
        L_0x0042:
            r1.remove(r6)     // Catch:{ all -> 0x0032 }
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            r5 = 0
            return r5
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x0032 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.getCachedColorStateList(androidx.core.content.res.ResourcesCompat$ColorStateListCacheKey, int):android.content.res.ColorStateList");
    }

    public static Typeface getCachedFont(Context context, int i2) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i2, new TypedValue(), 0, (FontCallback) null, (Handler) null, false, true);
    }

    public static int getColor(Resources resources, int i2, Resources.Theme theme) {
        return Api23Impl.getColor(resources, i2, theme);
    }

    public static ColorStateList getColorStateList(Resources resources, int i2, Resources.Theme theme) {
        ColorStateListCacheKey colorStateListCacheKey = new ColorStateListCacheKey(resources, theme);
        ColorStateList cachedColorStateList = getCachedColorStateList(colorStateListCacheKey, i2);
        if (cachedColorStateList != null) {
            return cachedColorStateList;
        }
        ColorStateList inflateColorStateList = inflateColorStateList(resources, i2, theme);
        if (inflateColorStateList == null) {
            return Api23Impl.getColorStateList(resources, i2, theme);
        }
        addColorStateListToCache(colorStateListCacheKey, i2, inflateColorStateList, theme);
        return inflateColorStateList;
    }

    public static Drawable getDrawable(Resources resources, int i2, Resources.Theme theme) {
        return Api21Impl.getDrawable(resources, i2, theme);
    }

    public static Drawable getDrawableForDensity(Resources resources, int i2, int i7, Resources.Theme theme) {
        return Api21Impl.getDrawableForDensity(resources, i2, i7, theme);
    }

    public static float getFloat(Resources resources, int i2) {
        return Api29Impl.getFloat(resources, i2);
    }

    public static Typeface getFont(Context context, int i2) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i2, new TypedValue(), 0, (FontCallback) null, (Handler) null, false, false);
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> threadLocal = sTempTypedValue;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    private static ColorStateList inflateColorStateList(Resources resources, int i2, Resources.Theme theme) {
        if (isColorInt(resources, i2)) {
            return null;
        }
        try {
            return ColorStateListInflaterCompat.createFromXml(resources, resources.getXml(i2), theme);
        } catch (Exception e) {
            Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    private static boolean isColorInt(Resources resources, int i2) {
        TypedValue typedValue = getTypedValue();
        resources.getValue(i2, typedValue, true);
        int i7 = typedValue.type;
        if (i7 < 28 || i7 > 31) {
            return false;
        }
        return true;
    }

    private static Typeface loadFont(Context context, int i2, TypedValue typedValue, int i7, FontCallback fontCallback, Handler handler, boolean z, boolean z3) {
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        Typeface loadFont = loadFont(context, resources, typedValue, i2, i7, fontCallback, handler, z, z3);
        if (loadFont != null || fontCallback != null || z3) {
            return loadFont;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i2) + " could not be retrieved.");
    }

    public static void getFont(Context context, int i2, FontCallback fontCallback, Handler handler) {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
            return;
        }
        loadFont(context, i2, new TypedValue(), 0, fontCallback, handler, false, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface loadFont(android.content.Context r13, android.content.res.Resources r14, android.util.TypedValue r15, int r16, int r17, androidx.core.content.res.ResourcesCompat.FontCallback r18, android.os.Handler r19, boolean r20, boolean r21) {
        /*
            r2 = r16
            r7 = r18
            r8 = r19
            java.lang.String r10 = "ResourcesCompat"
            java.lang.CharSequence r0 = r15.string
            if (r0 == 0) goto L_0x00a1
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "res/"
            boolean r0 = r3.startsWith(r0)
            r11 = 0
            r12 = -3
            if (r0 != 0) goto L_0x0020
            if (r7 == 0) goto L_0x001f
            r7.callbackFailAsync(r12, r8)
        L_0x001f:
            return r11
        L_0x0020:
            int r0 = r15.assetCookie
            r5 = r17
            android.graphics.Typeface r0 = androidx.core.graphics.TypefaceCompat.findFromCache(r14, r2, r3, r0, r5)
            if (r0 == 0) goto L_0x0030
            if (r7 == 0) goto L_0x002f
            r7.callbackSuccessAsync(r0, r8)
        L_0x002f:
            return r0
        L_0x0030:
            if (r21 == 0) goto L_0x0033
            return r11
        L_0x0033:
            java.lang.String r0 = r3.toLowerCase()     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            java.lang.String r1 = ".xml"
            boolean r0 = r0.endsWith(r1)     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            if (r0 == 0) goto L_0x0070
            android.content.res.XmlResourceParser r0 = r14.getXml(r2)     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            androidx.core.content.res.FontResourcesParserCompat$FamilyResourceEntry r1 = androidx.core.content.res.FontResourcesParserCompat.parse(r0, r14)     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            if (r1 != 0) goto L_0x005b
            java.lang.String r13 = "Failed to find font-family tag"
            android.util.Log.e(r10, r13)     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            if (r7 == 0) goto L_0x005a
            r7.callbackFailAsync(r12, r8)     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            goto L_0x005a
        L_0x0054:
            r0 = move-exception
        L_0x0055:
            r13 = r0
            goto L_0x0088
        L_0x0057:
            r0 = move-exception
        L_0x0058:
            r13 = r0
            goto L_0x0092
        L_0x005a:
            return r11
        L_0x005b:
            int r5 = r15.assetCookie     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            r0 = r13
            r6 = r17
            r9 = r20
            r4 = r3
            r3 = r2
            r2 = r14
            android.graphics.Typeface r13 = androidx.core.graphics.TypefaceCompat.createFromResourcesFamilyXml(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ XmlPullParserException -> 0x006d, IOException -> 0x006a }
            return r13
        L_0x006a:
            r0 = move-exception
            r3 = r4
            goto L_0x0055
        L_0x006d:
            r0 = move-exception
            r3 = r4
            goto L_0x0058
        L_0x0070:
            int r4 = r15.assetCookie     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            r0 = r13
            r1 = r14
            r2 = r16
            r5 = r17
            android.graphics.Typeface r13 = androidx.core.graphics.TypefaceCompat.createFromResourcesFontFile(r0, r1, r2, r3, r4, r5)     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            if (r7 == 0) goto L_0x0087
            if (r13 == 0) goto L_0x0084
            r7.callbackSuccessAsync(r13, r8)     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
            return r13
        L_0x0084:
            r7.callbackFailAsync(r12, r8)     // Catch:{ XmlPullParserException -> 0x0057, IOException -> 0x0054 }
        L_0x0087:
            return r13
        L_0x0088:
            java.lang.String r14 = "Failed to read xml resource "
            java.lang.String r14 = r14.concat(r3)
            android.util.Log.e(r10, r14, r13)
            goto L_0x009b
        L_0x0092:
            java.lang.String r14 = "Failed to parse xml resource "
            java.lang.String r14 = r14.concat(r3)
            android.util.Log.e(r10, r14, r13)
        L_0x009b:
            if (r7 == 0) goto L_0x00a0
            r7.callbackFailAsync(r12, r8)
        L_0x00a0:
            return r11
        L_0x00a1:
            android.content.res.Resources$NotFoundException r13 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "Resource \""
            r0.<init>(r3)
            java.lang.String r14 = r14.getResourceName(r2)
            r0.append(r14)
            java.lang.String r14 = "\" ("
            r0.append(r14)
            java.lang.String r14 = java.lang.Integer.toHexString(r2)
            r0.append(r14)
            java.lang.String r14 = ") is not a Font: "
            r0.append(r14)
            r0.append(r15)
            java.lang.String r14 = r0.toString()
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ResourcesCompat.loadFont(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, androidx.core.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, boolean):android.graphics.Typeface");
    }

    public static Typeface getFont(Context context, int i2, TypedValue typedValue, int i7, FontCallback fontCallback) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i2, typedValue, i7, fontCallback, (Handler) null, true, false);
    }
}
