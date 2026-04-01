package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.tracing.Trace;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TypefaceCompat {
    private static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl = new TypefaceCompatApi29Impl();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ResourcesCallbackAdapter extends FontsContractCompat.FontRequestCallback {
        private ResourcesCompat.FontCallback mFontCallback;

        public ResourcesCallbackAdapter(ResourcesCompat.FontCallback fontCallback) {
            this.mFontCallback = fontCallback;
        }

        public void onTypefaceRequestFailed(int i2) {
            ResourcesCompat.FontCallback fontCallback = this.mFontCallback;
            if (fontCallback != null) {
                fontCallback.lambda$callbackFailAsync$1(i2);
            }
        }

        public void onTypefaceRetrieved(Typeface typeface) {
            ResourcesCompat.FontCallback fontCallback = this.mFontCallback;
            if (fontCallback != null) {
                fontCallback.lambda$callbackSuccessAsync$0(typeface);
            }
        }
    }

    static {
        Trace.beginSection("TypefaceCompat static init");
        Trace.endSection();
    }

    public static Typeface create(Context context, Typeface typeface, int i2) {
        if (context != null) {
            return Typeface.create(typeface, i2);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    public static Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        Trace.beginSection("TypefaceCompat.createFromFontInfo");
        try {
            return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fontInfoArr, i2);
        } finally {
            Trace.endSection();
        }
    }

    public static Typeface createFromFontInfoWithFallback(Context context, CancellationSignal cancellationSignal, List<FontsContractCompat.FontInfo[]> list, int i2) {
        Trace.beginSection("TypefaceCompat.createFromFontInfoWithFallback");
        try {
            return sTypefaceCompatImpl.createFromFontInfoWithFallback(context, cancellationSignal, list, i2);
        } finally {
            Trace.endSection();
        }
    }

    public static Typeface createFromResourcesFamilyXml(Context context, FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, Resources resources, int i2, String str, int i7, int i8, ResourcesCompat.FontCallback fontCallback, Handler handler, boolean z) {
        Typeface typeface;
        boolean z3;
        int i10;
        List of2;
        ResourcesCompat.FontCallback fontCallback2 = fontCallback;
        Handler handler2 = handler;
        if (familyResourceEntry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
            FontResourcesParserCompat.ProviderResourceEntry providerResourceEntry = (FontResourcesParserCompat.ProviderResourceEntry) familyResourceEntry;
            Typeface systemFontFamily = getSystemFontFamily(providerResourceEntry.getSystemFontFamilyName());
            if (systemFontFamily != null) {
                if (fontCallback2 != null) {
                    fontCallback2.callbackSuccessAsync(systemFontFamily, handler2);
                }
                return systemFontFamily;
            }
            if (!z ? fontCallback2 != null : providerResourceEntry.getFetchStrategy() != 0) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (z) {
                i10 = providerResourceEntry.getTimeout();
            } else {
                i10 = -1;
            }
            int i11 = i10;
            Handler handler3 = ResourcesCompat.FontCallback.getHandler(handler2);
            ResourcesCallbackAdapter resourcesCallbackAdapter = new ResourcesCallbackAdapter(fontCallback2);
            if (providerResourceEntry.getFallbackRequest() != null) {
                of2 = List.of(providerResourceEntry.getRequest(), providerResourceEntry.getFallbackRequest());
            } else {
                of2 = List.of(providerResourceEntry.getRequest());
            }
            typeface = FontsContractCompat.requestFont(context, of2, i8, z3, i11, handler3, resourcesCallbackAdapter);
            int i12 = i8;
        } else {
            typeface = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (FontResourcesParserCompat.FontFamilyFilesResourceEntry) familyResourceEntry, resources, i8);
            if (fontCallback2 != null) {
                if (typeface != null) {
                    fontCallback2.callbackSuccessAsync(typeface, handler2);
                } else {
                    fontCallback2.callbackFailAsync(-3, handler2);
                }
            }
        }
        if (typeface != null) {
            sTypefaceCache.put(createResourceUid(resources, i2, str, i7, i8), typeface);
        }
        return typeface;
    }

    public static Typeface createFromResourcesFontFile(Context context, Resources resources, int i2, String str, int i7, int i8) {
        Resources resources2 = resources;
        int i10 = i2;
        String str2 = str;
        int i11 = i8;
        Typeface createFromResourcesFontFile = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources2, i10, str2, i11);
        if (createFromResourcesFontFile != null) {
            sTypefaceCache.put(createResourceUid(resources2, i10, str2, i7, i11), createFromResourcesFontFile);
        }
        return createFromResourcesFontFile;
    }

    private static String createResourceUid(Resources resources, int i2, String str, int i7, int i8) {
        return resources.getResourcePackageName(i2) + '-' + str + '-' + i7 + '-' + i2 + '-' + i8;
    }

    public static Typeface findFromCache(Resources resources, int i2, String str, int i7, int i8) {
        return sTypefaceCache.get(createResourceUid(resources, i2, str, i7, i8));
    }

    private static Typeface getSystemFontFamily(String str) {
        if (str != null && !str.isEmpty()) {
            Typeface create = Typeface.create(str, 0);
            Typeface create2 = Typeface.create(Typeface.DEFAULT, 0);
            if (create == null || create.equals(create2)) {
                return null;
            }
            return create;
        }
        return null;
    }
}
