package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Base64;
import android.util.Xml;
import androidx.core.R$styleable;
import androidx.core.provider.FontRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FontResourcesParserCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static int getType(TypedArray typedArray, int i2) {
            return typedArray.getType(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FamilyResourceEntry {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(FontFileResourceEntry[] fontFileResourceEntryArr) {
            this.mEntries = fontFileResourceEntryArr;
        }

        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FontFileResourceEntry {
        private final String mFileName;
        private final boolean mItalic;
        private final int mResourceId;
        private final int mTtcIndex;
        private final String mVariationSettings;
        private final int mWeight;

        public FontFileResourceEntry(String str, int i2, boolean z, String str2, int i7, int i8) {
            this.mFileName = str;
            this.mWeight = i2;
            this.mItalic = z;
            this.mVariationSettings = str2;
            this.mTtcIndex = i7;
            this.mResourceId = i8;
        }

        public int getResourceId() {
            return this.mResourceId;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public String getVariationSettings() {
            return this.mVariationSettings;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ProviderResourceEntry implements FamilyResourceEntry {
        private final FontRequest mFallbackRequest;
        private final FontRequest mRequest;
        private final int mStrategy;
        private final String mSystemFontFamilyName;
        private final int mTimeoutMs;

        public ProviderResourceEntry(FontRequest fontRequest, FontRequest fontRequest2, int i2, int i7, String str) {
            this.mRequest = fontRequest;
            this.mFallbackRequest = fontRequest2;
            this.mStrategy = i2;
            this.mTimeoutMs = i7;
            this.mSystemFontFamilyName = str;
        }

        public FontRequest getFallbackRequest() {
            return this.mFallbackRequest;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        public FontRequest getRequest() {
            return this.mRequest;
        }

        public String getSystemFontFamilyName() {
            return this.mSystemFontFamilyName;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }
    }

    private static int getType(TypedArray typedArray, int i2) {
        return Api21Impl.getType(typedArray, i2);
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public static androidx.core.content.res.FontResourcesParserCompat.FamilyResourceEntry parse(org.xmlpull.v1.XmlPullParser r3, android.content.res.Resources r4) {
        /*
        L_0x0000:
            int r0 = r3.next()
            r1 = 2
            if (r0 == r1) goto L_0x000b
            r2 = 1
            if (r0 == r2) goto L_0x000b
            goto L_0x0000
        L_0x000b:
            if (r0 != r1) goto L_0x0012
            androidx.core.content.res.FontResourcesParserCompat$FamilyResourceEntry r3 = readFamilies(r3, r4)
            return r3
        L_0x0012:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r4 = "No start tag found"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.FontResourcesParserCompat.parse(org.xmlpull.v1.XmlPullParser, android.content.res.Resources):androidx.core.content.res.FontResourcesParserCompat$FamilyResourceEntry");
    }

    public static List<List<byte[]>> readCerts(Resources resources, int i2) {
        if (i2 == 0) {
            return Collections.EMPTY_LIST;
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i2);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.EMPTY_LIST;
            }
            ArrayList arrayList = new ArrayList();
            if (getType(obtainTypedArray, 0) == 1) {
                for (int i7 = 0; i7 < obtainTypedArray.length(); i7++) {
                    int resourceId = obtainTypedArray.getResourceId(i7, 0);
                    if (resourceId != 0) {
                        arrayList.add(toByteArrayList(resources.getStringArray(resourceId)));
                    }
                }
            } else {
                arrayList.add(toByteArrayList(resources.getStringArray(i2)));
            }
            obtainTypedArray.recycle();
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    private static FamilyResourceEntry readFamilies(XmlPullParser xmlPullParser, Resources resources) {
        xmlPullParser.require(2, (String) null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return readFamily(xmlPullParser, resources);
        }
        skip(xmlPullParser);
        return null;
    }

    private static FamilyResourceEntry readFamily(XmlPullParser xmlPullParser, Resources resources) {
        Resources resources2 = resources;
        TypedArray obtainAttributes = resources2.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.FontFamily);
        String string = obtainAttributes.getString(R$styleable.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(R$styleable.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(R$styleable.FontFamily_fontProviderQuery);
        String string4 = obtainAttributes.getString(R$styleable.FontFamily_fontProviderFallbackQuery);
        int resourceId = obtainAttributes.getResourceId(R$styleable.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(R$styleable.FontFamily_fontProviderFetchTimeout, 500);
        String string5 = obtainAttributes.getString(R$styleable.FontFamily_fontProviderSystemFontFamily);
        obtainAttributes.recycle();
        FontRequest fontRequest = null;
        if (string == null || string2 == null || string3 == null) {
            ArrayList arrayList = new ArrayList();
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("font")) {
                        arrayList.add(readFont(xmlPullParser, resources));
                    } else {
                        skip(xmlPullParser);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            return new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) arrayList.toArray(new FontFileResourceEntry[0]));
        }
        while (xmlPullParser.next() != 3) {
            skip(xmlPullParser);
        }
        List<List<byte[]>> readCerts = readCerts(resources2, resourceId);
        if (string4 != null) {
            fontRequest = new FontRequest(string, string2, string4, readCerts);
        }
        return new ProviderResourceEntry(new FontRequest(string, string2, string3, readCerts), fontRequest, integer, integer2, string5);
    }

    private static FontFileResourceEntry readFont(XmlPullParser xmlPullParser, Resources resources) {
        boolean z;
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.FontFamilyFont);
        int i2 = R$styleable.FontFamilyFont_fontWeight;
        if (!obtainAttributes.hasValue(i2)) {
            i2 = R$styleable.FontFamilyFont_android_fontWeight;
        }
        int i7 = obtainAttributes.getInt(i2, 400);
        int i8 = R$styleable.FontFamilyFont_fontStyle;
        if (!obtainAttributes.hasValue(i8)) {
            i8 = R$styleable.FontFamilyFont_android_fontStyle;
        }
        if (1 == obtainAttributes.getInt(i8, 0)) {
            z = true;
        } else {
            z = false;
        }
        int i10 = R$styleable.FontFamilyFont_ttcIndex;
        if (!obtainAttributes.hasValue(i10)) {
            i10 = R$styleable.FontFamilyFont_android_ttcIndex;
        }
        int i11 = R$styleable.FontFamilyFont_fontVariationSettings;
        if (!obtainAttributes.hasValue(i11)) {
            i11 = R$styleable.FontFamilyFont_android_fontVariationSettings;
        }
        String string = obtainAttributes.getString(i11);
        int i12 = obtainAttributes.getInt(i10, 0);
        int i13 = R$styleable.FontFamilyFont_font;
        if (!obtainAttributes.hasValue(i13)) {
            i13 = R$styleable.FontFamilyFont_android_font;
        }
        int resourceId = obtainAttributes.getResourceId(i13, 0);
        String string2 = obtainAttributes.getString(i13);
        obtainAttributes.recycle();
        while (xmlPullParser.next() != 3) {
            skip(xmlPullParser);
        }
        return new FontFileResourceEntry(string2, i7, z, string, i12, resourceId);
    }

    private static void skip(XmlPullParser xmlPullParser) {
        int i2 = 1;
        while (i2 > 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }

    private static List<byte[]> toByteArrayList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String decode : strArr) {
            arrayList.add(Base64.decode(decode, 0));
        }
        return arrayList;
    }
}
