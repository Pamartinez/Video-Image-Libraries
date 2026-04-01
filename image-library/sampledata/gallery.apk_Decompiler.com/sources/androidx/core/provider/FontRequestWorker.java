package androidx.core.provider;

import android.content.Context;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FontRequestWorker {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
    static final Object LOCK = new Object();
    static final SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> PENDING_REPLIES = new SimpleArrayMap<>();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);

    private static String createCacheId(List<FontRequest> list, int i2) {
        StringBuilder sb2 = new StringBuilder();
        for (int i7 = 0; i7 < list.size(); i7++) {
            sb2.append(list.get(i7).getId());
            sb2.append("-");
            sb2.append(i2);
            if (i7 < list.size() - 1) {
                sb2.append(";");
            }
        }
        return sb2.toString();
    }

    private static int getFontFamilyResultStatus(FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i2 = 1;
        if (fontFamilyResult.getStatusCode() == 0) {
            FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
            if (!(fonts == null || fonts.length == 0)) {
                int length = fonts.length;
                i2 = 0;
                int i7 = 0;
                while (i7 < length) {
                    int resultCode = fonts[i7].getResultCode();
                    if (resultCode == 0) {
                        i7++;
                    } else if (resultCode < 0) {
                        return -3;
                    } else {
                        return resultCode;
                    }
                }
            }
            return i2;
        } else if (fontFamilyResult.getStatusCode() != 1) {
            return -3;
        } else {
            return -2;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:29|30|31|32) */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r3 = new androidx.core.provider.FontRequestWorker.TypefaceResult(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0061, code lost:
        androidx.tracing.Trace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0064, code lost:
        return r3;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x005b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.core.provider.FontRequestWorker.TypefaceResult getFontSync(java.lang.String r3, android.content.Context r4, java.util.List<androidx.core.provider.FontRequest> r5, int r6) {
        /*
            java.lang.String r0 = "getFontSync"
            androidx.tracing.Trace.beginSection(r0)
            androidx.collection.LruCache<java.lang.String, android.graphics.Typeface> r0 = sTypefaceCache     // Catch:{ all -> 0x0065 }
            java.lang.Object r1 = r0.get(r3)     // Catch:{ all -> 0x0065 }
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1     // Catch:{ all -> 0x0065 }
            if (r1 == 0) goto L_0x0018
            androidx.core.provider.FontRequestWorker$TypefaceResult r3 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x0065 }
            r3.<init>((android.graphics.Typeface) r1)     // Catch:{ all -> 0x0065 }
            androidx.tracing.Trace.endSection()
            return r3
        L_0x0018:
            r1 = 0
            androidx.core.provider.FontsContractCompat$FontFamilyResult r5 = androidx.core.provider.FontProvider.getFontFamilyResult(r4, r5, r1)     // Catch:{ NameNotFoundException -> 0x005b }
            int r2 = getFontFamilyResultStatus(r5)     // Catch:{ all -> 0x0065 }
            if (r2 == 0) goto L_0x002c
            androidx.core.provider.FontRequestWorker$TypefaceResult r3 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x0065 }
            r3.<init>((int) r2)     // Catch:{ all -> 0x0065 }
            androidx.tracing.Trace.endSection()
            return r3
        L_0x002c:
            boolean r2 = r5.hasFallback()     // Catch:{ all -> 0x0065 }
            if (r2 == 0) goto L_0x003b
            java.util.List r5 = r5.getFontsWithFallbacks()     // Catch:{ all -> 0x0065 }
            android.graphics.Typeface r4 = androidx.core.graphics.TypefaceCompat.createFromFontInfoWithFallback(r4, r1, r5, r6)     // Catch:{ all -> 0x0065 }
            goto L_0x0043
        L_0x003b:
            androidx.core.provider.FontsContractCompat$FontInfo[] r5 = r5.getFonts()     // Catch:{ all -> 0x0065 }
            android.graphics.Typeface r4 = androidx.core.graphics.TypefaceCompat.createFromFontInfo(r4, r1, r5, r6)     // Catch:{ all -> 0x0065 }
        L_0x0043:
            if (r4 == 0) goto L_0x0051
            r0.put(r3, r4)     // Catch:{ all -> 0x0065 }
            androidx.core.provider.FontRequestWorker$TypefaceResult r3 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x0065 }
            r3.<init>((android.graphics.Typeface) r4)     // Catch:{ all -> 0x0065 }
            androidx.tracing.Trace.endSection()
            return r3
        L_0x0051:
            androidx.core.provider.FontRequestWorker$TypefaceResult r3 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x0065 }
            r4 = -3
            r3.<init>((int) r4)     // Catch:{ all -> 0x0065 }
            androidx.tracing.Trace.endSection()
            return r3
        L_0x005b:
            androidx.core.provider.FontRequestWorker$TypefaceResult r3 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x0065 }
            r4 = -1
            r3.<init>((int) r4)     // Catch:{ all -> 0x0065 }
            androidx.tracing.Trace.endSection()
            return r3
        L_0x0065:
            r3 = move-exception
            androidx.tracing.Trace.endSection()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontRequestWorker.getFontSync(java.lang.String, android.content.Context, java.util.List, int):androidx.core.provider.FontRequestWorker$TypefaceResult");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        r9 = new androidx.core.provider.FontRequestWorker.AnonymousClass3();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        if (r8 != null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r8 = DEFAULT_EXECUTOR_SERVICE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        androidx.core.provider.RequestExecutor.execute(r8, r9, new androidx.core.provider.FontRequestWorker.AnonymousClass4());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Typeface requestFontAsync(final android.content.Context r5, final java.util.List<androidx.core.provider.FontRequest> r6, final int r7, java.util.concurrent.Executor r8, final androidx.core.provider.CallbackWrapper r9) {
        /*
            java.lang.String r0 = createCacheId(r6, r7)
            androidx.collection.LruCache<java.lang.String, android.graphics.Typeface> r1 = sTypefaceCache
            java.lang.Object r1 = r1.get(r0)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L_0x0017
            androidx.core.provider.FontRequestWorker$TypefaceResult r5 = new androidx.core.provider.FontRequestWorker$TypefaceResult
            r5.<init>((android.graphics.Typeface) r1)
            r9.onTypefaceResult(r5)
            return r1
        L_0x0017:
            androidx.core.provider.FontRequestWorker$2 r1 = new androidx.core.provider.FontRequestWorker$2
            r1.<init>()
            java.lang.Object r9 = LOCK
            monitor-enter(r9)
            androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.util.Consumer<androidx.core.provider.FontRequestWorker$TypefaceResult>>> r2 = PENDING_REPLIES     // Catch:{ all -> 0x002f }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ all -> 0x002f }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x002f }
            r4 = 0
            if (r3 == 0) goto L_0x0031
            r3.add(r1)     // Catch:{ all -> 0x002f }
            monitor-exit(r9)     // Catch:{ all -> 0x002f }
            return r4
        L_0x002f:
            r5 = move-exception
            goto L_0x004f
        L_0x0031:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x002f }
            r3.<init>()     // Catch:{ all -> 0x002f }
            r3.add(r1)     // Catch:{ all -> 0x002f }
            r2.put(r0, r3)     // Catch:{ all -> 0x002f }
            monitor-exit(r9)     // Catch:{ all -> 0x002f }
            androidx.core.provider.FontRequestWorker$3 r9 = new androidx.core.provider.FontRequestWorker$3
            r9.<init>(r0, r5, r6, r7)
            if (r8 != 0) goto L_0x0046
            java.util.concurrent.ExecutorService r8 = DEFAULT_EXECUTOR_SERVICE
        L_0x0046:
            androidx.core.provider.FontRequestWorker$4 r5 = new androidx.core.provider.FontRequestWorker$4
            r5.<init>(r0)
            androidx.core.provider.RequestExecutor.execute(r8, r9, r5)
            return r4
        L_0x004f:
            monitor-exit(r9)     // Catch:{ all -> 0x002f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontRequestWorker.requestFontAsync(android.content.Context, java.util.List, int, java.util.concurrent.Executor, androidx.core.provider.CallbackWrapper):android.graphics.Typeface");
    }

    public static Typeface requestFontSync(final Context context, final FontRequest fontRequest, CallbackWrapper callbackWrapper, final int i2, int i7) {
        final String createCacheId = createCacheId(List.of(fontRequest), i2);
        Typeface typeface = sTypefaceCache.get(createCacheId);
        if (typeface != null) {
            callbackWrapper.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        } else if (i7 == -1) {
            TypefaceResult fontSync = getFontSync(createCacheId, context, List.of(fontRequest), i2);
            callbackWrapper.onTypefaceResult(fontSync);
            return fontSync.mTypeface;
        } else {
            try {
                TypefaceResult typefaceResult = (TypefaceResult) RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, new Callable<TypefaceResult>() {
                    public TypefaceResult call() {
                        return FontRequestWorker.getFontSync(createCacheId, context, List.of(fontRequest), i2);
                    }
                }, i7);
                callbackWrapper.onTypefaceResult(typefaceResult);
                return typefaceResult.mTypeface;
            } catch (InterruptedException unused) {
                callbackWrapper.onTypefaceResult(new TypefaceResult(-3));
                return null;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        public TypefaceResult(int i2) {
            this.mTypeface = null;
            this.mResult = i2;
        }

        public boolean isSuccess() {
            if (this.mResult == 0) {
                return true;
            }
            return false;
        }

        public TypefaceResult(Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }
    }
}
