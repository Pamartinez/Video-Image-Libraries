package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FontRequestEmojiCompatConfig extends EmojiCompat.Config {
    private static final FontProviderHelper DEFAULT_FONTS_CONTRACT = new FontProviderHelper();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FontProviderHelper {
        public Typeface buildTypeface(Context context, FontsContractCompat.FontInfo fontInfo) {
            return FontsContractCompat.buildTypeface(context, (CancellationSignal) null, new FontsContractCompat.FontInfo[]{fontInfo});
        }

        public FontsContractCompat.FontFamilyResult fetchFonts(Context context, FontRequest fontRequest) {
            return FontsContractCompat.fetchFonts(context, (CancellationSignal) null, fontRequest);
        }

        public void unregisterObserver(Context context, ContentObserver contentObserver) {
            context.getContentResolver().unregisterContentObserver(contentObserver);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FontRequestMetadataLoader implements EmojiCompat.MetadataRepoLoader {
        EmojiCompat.MetadataRepoLoaderCallback mCallback;
        private final Context mContext;
        private Executor mExecutor;
        private final FontProviderHelper mFontProviderHelper;
        private final Object mLock = new Object();
        private Handler mMainHandler;
        private Runnable mMainHandlerLoadCallback;
        private ThreadPoolExecutor mMyThreadPoolExecutor;
        private ContentObserver mObserver;
        private final FontRequest mRequest;

        public FontRequestMetadataLoader(Context context, FontRequest fontRequest, FontProviderHelper fontProviderHelper) {
            Preconditions.checkNotNull(context, "Context cannot be null");
            Preconditions.checkNotNull(fontRequest, "FontRequest cannot be null");
            this.mContext = context.getApplicationContext();
            this.mRequest = fontRequest;
            this.mFontProviderHelper = fontProviderHelper;
        }

        private void cleanUp() {
            synchronized (this.mLock) {
                try {
                    this.mCallback = null;
                    ContentObserver contentObserver = this.mObserver;
                    if (contentObserver != null) {
                        this.mFontProviderHelper.unregisterObserver(this.mContext, contentObserver);
                        this.mObserver = null;
                    }
                    Handler handler = this.mMainHandler;
                    if (handler != null) {
                        handler.removeCallbacks(this.mMainHandlerLoadCallback);
                    }
                    this.mMainHandler = null;
                    ThreadPoolExecutor threadPoolExecutor = this.mMyThreadPoolExecutor;
                    if (threadPoolExecutor != null) {
                        threadPoolExecutor.shutdown();
                    }
                    this.mExecutor = null;
                    this.mMyThreadPoolExecutor = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        private FontsContractCompat.FontInfo retrieveFontInfo() {
            try {
                FontsContractCompat.FontFamilyResult fetchFonts = this.mFontProviderHelper.fetchFonts(this.mContext, this.mRequest);
                if (fetchFonts.getStatusCode() == 0) {
                    FontsContractCompat.FontInfo[] fonts = fetchFonts.getFonts();
                    if (fonts != null && fonts.length != 0) {
                        return fonts[0];
                    }
                    throw new RuntimeException("fetchFonts failed (empty result)");
                }
                throw new RuntimeException("fetchFonts failed (" + fetchFonts.getStatusCode() + ")");
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException("provider not found", e);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            r1 = retrieveFontInfo();
            r2 = r1.getResultCode();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
            if (r2 != 2) goto L_0x0024;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
            r3 = r4.mLock;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
            monitor-enter(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            monitor-exit(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0022, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0024, code lost:
            if (r2 != 0) goto L_0x006b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            androidx.core.os.TraceCompat.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
            r0 = r4.mFontProviderHelper.buildTypeface(r4.mContext, r1);
            r1 = androidx.core.graphics.TypefaceCompatUtil.mmap(r4.mContext, (android.os.CancellationSignal) null, r1.getUri());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x003e, code lost:
            if (r1 == null) goto L_0x005f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0040, code lost:
            if (r0 == null) goto L_0x005f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0042, code lost:
            r0 = androidx.emoji2.text.MetadataRepo.create(r0, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            androidx.core.os.TraceCompat.endSection();
            r1 = r4.mLock;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x004b, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            r2 = r4.mCallback;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x004e, code lost:
            if (r2 == null) goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0050, code lost:
            r2.onLoaded(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0054, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0056, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
            cleanUp();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x005a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x005d, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0066, code lost:
            throw new java.lang.RuntimeException("Unable to open file.");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
            androidx.core.os.TraceCompat.endSection();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x006a, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0081, code lost:
            throw new java.lang.RuntimeException("fetchFonts result is not OK. (" + r2 + ")");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0084, code lost:
            monitor-enter(r4.mLock);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
            r1 = r4.mCallback;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0087, code lost:
            if (r1 != null) goto L_0x0089;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x0089, code lost:
            r1.onFailed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x008d, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x0090, code lost:
            cleanUp();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x0093, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0095, code lost:
            throw r4;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void createMetadata() {
            /*
                r4 = this;
                java.lang.String r0 = "fetchFonts result is not OK. ("
                java.lang.Object r1 = r4.mLock
                monitor-enter(r1)
                androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback r2 = r4.mCallback     // Catch:{ all -> 0x000b }
                if (r2 != 0) goto L_0x000e
                monitor-exit(r1)     // Catch:{ all -> 0x000b }
                return
            L_0x000b:
                r4 = move-exception
                goto L_0x0096
            L_0x000e:
                monitor-exit(r1)     // Catch:{ all -> 0x000b }
                androidx.core.provider.FontsContractCompat$FontInfo r1 = r4.retrieveFontInfo()     // Catch:{ all -> 0x0022 }
                int r2 = r1.getResultCode()     // Catch:{ all -> 0x0022 }
                r3 = 2
                if (r2 != r3) goto L_0x0024
                java.lang.Object r3 = r4.mLock     // Catch:{ all -> 0x0022 }
                monitor-enter(r3)     // Catch:{ all -> 0x0022 }
                monitor-exit(r3)     // Catch:{ all -> 0x001f }
                goto L_0x0024
            L_0x001f:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x001f }
                throw r0     // Catch:{ all -> 0x0022 }
            L_0x0022:
                r0 = move-exception
                goto L_0x0082
            L_0x0024:
                if (r2 != 0) goto L_0x006b
                java.lang.String r0 = "EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface"
                androidx.core.os.TraceCompat.beginSection(r0)     // Catch:{ all -> 0x005d }
                androidx.emoji2.text.FontRequestEmojiCompatConfig$FontProviderHelper r0 = r4.mFontProviderHelper     // Catch:{ all -> 0x005d }
                android.content.Context r2 = r4.mContext     // Catch:{ all -> 0x005d }
                android.graphics.Typeface r0 = r0.buildTypeface(r2, r1)     // Catch:{ all -> 0x005d }
                android.content.Context r2 = r4.mContext     // Catch:{ all -> 0x005d }
                android.net.Uri r1 = r1.getUri()     // Catch:{ all -> 0x005d }
                r3 = 0
                java.nio.ByteBuffer r1 = androidx.core.graphics.TypefaceCompatUtil.mmap(r2, r3, r1)     // Catch:{ all -> 0x005d }
                if (r1 == 0) goto L_0x005f
                if (r0 == 0) goto L_0x005f
                androidx.emoji2.text.MetadataRepo r0 = androidx.emoji2.text.MetadataRepo.create(r0, r1)     // Catch:{ all -> 0x005d }
                androidx.core.os.TraceCompat.endSection()     // Catch:{ all -> 0x0022 }
                java.lang.Object r1 = r4.mLock     // Catch:{ all -> 0x0022 }
                monitor-enter(r1)     // Catch:{ all -> 0x0022 }
                androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback r2 = r4.mCallback     // Catch:{ all -> 0x0054 }
                if (r2 == 0) goto L_0x0056
                r2.onLoaded(r0)     // Catch:{ all -> 0x0054 }
                goto L_0x0056
            L_0x0054:
                r0 = move-exception
                goto L_0x005b
            L_0x0056:
                monitor-exit(r1)     // Catch:{ all -> 0x0054 }
                r4.cleanUp()     // Catch:{ all -> 0x0022 }
                return
            L_0x005b:
                monitor-exit(r1)     // Catch:{ all -> 0x0054 }
                throw r0     // Catch:{ all -> 0x0022 }
            L_0x005d:
                r0 = move-exception
                goto L_0x0067
            L_0x005f:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x005d }
                java.lang.String r1 = "Unable to open file."
                r0.<init>(r1)     // Catch:{ all -> 0x005d }
                throw r0     // Catch:{ all -> 0x005d }
            L_0x0067:
                androidx.core.os.TraceCompat.endSection()     // Catch:{ all -> 0x0022 }
                throw r0     // Catch:{ all -> 0x0022 }
            L_0x006b:
                java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x0022 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0022 }
                r3.<init>(r0)     // Catch:{ all -> 0x0022 }
                r3.append(r2)     // Catch:{ all -> 0x0022 }
                java.lang.String r0 = ")"
                r3.append(r0)     // Catch:{ all -> 0x0022 }
                java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0022 }
                r1.<init>(r0)     // Catch:{ all -> 0x0022 }
                throw r1     // Catch:{ all -> 0x0022 }
            L_0x0082:
                java.lang.Object r2 = r4.mLock
                monitor-enter(r2)
                androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback r1 = r4.mCallback     // Catch:{ all -> 0x008d }
                if (r1 == 0) goto L_0x008f
                r1.onFailed(r0)     // Catch:{ all -> 0x008d }
                goto L_0x008f
            L_0x008d:
                r4 = move-exception
                goto L_0x0094
            L_0x008f:
                monitor-exit(r2)     // Catch:{ all -> 0x008d }
                r4.cleanUp()
                return
            L_0x0094:
                monitor-exit(r2)     // Catch:{ all -> 0x008d }
                throw r4
            L_0x0096:
                monitor-exit(r1)     // Catch:{ all -> 0x000b }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.FontRequestEmojiCompatConfig.FontRequestMetadataLoader.createMetadata():void");
        }

        public void load(EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            Preconditions.checkNotNull(metadataRepoLoaderCallback, "LoaderCallback cannot be null");
            synchronized (this.mLock) {
                this.mCallback = metadataRepoLoaderCallback;
            }
            loadInternal();
        }

        public void loadInternal() {
            synchronized (this.mLock) {
                try {
                    if (this.mCallback != null) {
                        if (this.mExecutor == null) {
                            ThreadPoolExecutor createBackgroundPriorityExecutor = ConcurrencyHelpers.createBackgroundPriorityExecutor("emojiCompat");
                            this.mMyThreadPoolExecutor = createBackgroundPriorityExecutor;
                            this.mExecutor = createBackgroundPriorityExecutor;
                        }
                        this.mExecutor.execute(new c(this));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void setExecutor(Executor executor) {
            synchronized (this.mLock) {
                this.mExecutor = executor;
            }
        }
    }

    public FontRequestEmojiCompatConfig(Context context, FontRequest fontRequest) {
        super(new FontRequestMetadataLoader(context, fontRequest, DEFAULT_FONTS_CONTRACT));
    }

    public FontRequestEmojiCompatConfig setLoadingExecutor(Executor executor) {
        ((FontRequestMetadataLoader) getMetadataRepoLoader()).setExecutor(executor);
        return this;
    }
}
