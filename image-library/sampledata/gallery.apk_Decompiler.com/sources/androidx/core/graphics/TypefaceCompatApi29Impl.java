package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    private Font findBaseFont(FontFamily fontFamily, int i2) {
        int i7;
        int i8;
        if ((i2 & 1) != 0) {
            i7 = 700;
        } else {
            i7 = 400;
        }
        if ((i2 & 2) != 0) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        FontStyle fontStyle = new FontStyle(i7, i8);
        Font font = fontFamily.getFont(0);
        int matchScore = getMatchScore(fontStyle, font.getStyle());
        for (int i10 = 1; i10 < fontFamily.getSize(); i10++) {
            Font font2 = fontFamily.getFont(i10);
            int matchScore2 = getMatchScore(fontStyle, font2.getStyle());
            if (matchScore2 < matchScore) {
                font = font2;
                matchScore = matchScore2;
            }
        }
        return font;
    }

    private static FontFamily getFontFamily(CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, ContentResolver contentResolver) {
        ParcelFileDescriptor openFileDescriptor;
        FontFamily.Builder builder = null;
        for (FontsContractCompat.FontInfo fontInfo : fontInfoArr) {
            try {
                openFileDescriptor = contentResolver.openFileDescriptor(fontInfo.getUri(), "r", cancellationSignal);
                if (openFileDescriptor != null) {
                    Font build = new Font.Builder(openFileDescriptor).setWeight(fontInfo.getWeight()).setSlant(fontInfo.isItalic() ? 1 : 0).setTtcIndex(fontInfo.getTtcIndex()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(build);
                    } else {
                        builder.addFont(build);
                    }
                } else if (openFileDescriptor == null) {
                }
                openFileDescriptor.close();
            } catch (IOException e) {
                Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (builder == null) {
            return null;
        }
        return builder.build();
        throw th;
    }

    private static int getMatchScore(FontStyle fontStyle, FontStyle fontStyle2) {
        int i2;
        int abs = Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100;
        if (fontStyle.getSlant() == fontStyle2.getSlant()) {
            i2 = 0;
        } else {
            i2 = 2;
        }
        return abs + i2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d A[SYNTHETIC, Splitter:B:16:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x000a A[Catch:{ Exception -> 0x0042 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontFamilyFilesResourceEntry(android.content.Context r7, androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry r8, android.content.res.Resources r9, int r10) {
        /*
            r6 = this;
            r7 = 0
            androidx.core.content.res.FontResourcesParserCompat$FontFileResourceEntry[] r8 = r8.getEntries()     // Catch:{ Exception -> 0x0042 }
            int r0 = r8.length     // Catch:{ Exception -> 0x0042 }
            r1 = 0
            r2 = r7
        L_0x0008:
            if (r1 >= r0) goto L_0x004a
            r3 = r8[r1]     // Catch:{ Exception -> 0x0042 }
            android.graphics.fonts.Font$Builder r4 = new android.graphics.fonts.Font$Builder     // Catch:{ IOException -> 0x0047 }
            int r5 = r3.getResourceId()     // Catch:{ IOException -> 0x0047 }
            r4.<init>(r9, r5)     // Catch:{ IOException -> 0x0047 }
            int r5 = r3.getWeight()     // Catch:{ IOException -> 0x0047 }
            android.graphics.fonts.Font$Builder r4 = r4.setWeight(r5)     // Catch:{ IOException -> 0x0047 }
            boolean r5 = r3.isItalic()     // Catch:{ IOException -> 0x0047 }
            android.graphics.fonts.Font$Builder r4 = r4.setSlant(r5)     // Catch:{ IOException -> 0x0047 }
            int r5 = r3.getTtcIndex()     // Catch:{ IOException -> 0x0047 }
            android.graphics.fonts.Font$Builder r4 = r4.setTtcIndex(r5)     // Catch:{ IOException -> 0x0047 }
            java.lang.String r3 = r3.getVariationSettings()     // Catch:{ IOException -> 0x0047 }
            android.graphics.fonts.Font$Builder r3 = r4.setFontVariationSettings(r3)     // Catch:{ IOException -> 0x0047 }
            android.graphics.fonts.Font r3 = r3.build()     // Catch:{ IOException -> 0x0047 }
            if (r2 != 0) goto L_0x0044
            android.graphics.fonts.FontFamily$Builder r4 = new android.graphics.fonts.FontFamily$Builder     // Catch:{ IOException -> 0x0047 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0047 }
            r2 = r4
            goto L_0x0047
        L_0x0042:
            r6 = move-exception
            goto L_0x0067
        L_0x0044:
            r2.addFont(r3)     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            int r1 = r1 + 1
            goto L_0x0008
        L_0x004a:
            if (r2 != 0) goto L_0x004d
            return r7
        L_0x004d:
            android.graphics.fonts.FontFamily r8 = r2.build()     // Catch:{ Exception -> 0x0042 }
            android.graphics.Typeface$CustomFallbackBuilder r9 = new android.graphics.Typeface$CustomFallbackBuilder     // Catch:{ Exception -> 0x0042 }
            r9.<init>(r8)     // Catch:{ Exception -> 0x0042 }
            android.graphics.fonts.Font r6 = r6.findBaseFont(r8, r10)     // Catch:{ Exception -> 0x0042 }
            android.graphics.fonts.FontStyle r6 = r6.getStyle()     // Catch:{ Exception -> 0x0042 }
            android.graphics.Typeface$CustomFallbackBuilder r6 = r9.setStyle(r6)     // Catch:{ Exception -> 0x0042 }
            android.graphics.Typeface r6 = r6.build()     // Catch:{ Exception -> 0x0042 }
            return r6
        L_0x0067:
            java.lang.String r8 = "TypefaceCompatApi29Impl"
            java.lang.String r9 = "Font load failed"
            android.util.Log.w(r8, r9, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi29Impl.createFromFontFamilyFilesResourceEntry(android.content.Context, androidx.core.content.res.FontResourcesParserCompat$FontFamilyFilesResourceEntry, android.content.res.Resources, int):android.graphics.Typeface");
    }

    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        try {
            FontFamily fontFamily = getFontFamily(cancellationSignal, fontInfoArr, context.getContentResolver());
            if (fontFamily == null) {
                return null;
            }
            return new Typeface.CustomFallbackBuilder(fontFamily).setStyle(findBaseFont(fontFamily, i2).getStyle()).build();
        } catch (Exception e) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
            return null;
        }
    }

    public Typeface createFromFontInfoWithFallback(Context context, CancellationSignal cancellationSignal, List<FontsContractCompat.FontInfo[]> list, int i2) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily fontFamily = getFontFamily(cancellationSignal, list.get(0), contentResolver);
            if (fontFamily == null) {
                return null;
            }
            Typeface.CustomFallbackBuilder customFallbackBuilder = new Typeface.CustomFallbackBuilder(fontFamily);
            for (int i7 = 1; i7 < list.size(); i7++) {
                FontFamily fontFamily2 = getFontFamily(cancellationSignal, list.get(i7), contentResolver);
                if (fontFamily2 != null) {
                    customFallbackBuilder.addCustomFallback(fontFamily2);
                }
            }
            return customFallbackBuilder.setStyle(findBaseFont(fontFamily, i2).getStyle()).build();
        } catch (Exception e) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
            return null;
        }
    }

    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i2, String str, int i7) {
        try {
            Font build = new Font.Builder(resources, i2).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(build).build()).setStyle(build.getStyle()).build();
        } catch (Exception e) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", e);
            return null;
        }
    }
}
