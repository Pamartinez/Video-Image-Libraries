package com.samsung.android.gallery.module.story;

import android.graphics.Typeface;
import com.samsung.android.gallery.database.dbtype.StoryLevel2Cat;
import com.samsung.android.gallery.support.config.SdkConfig;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FontTypefaceUtils {
    /* access modifiers changed from: private */
    public static final boolean IS_U_OS = SdkConfig.atLeast(SdkConfig.SEM.U);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TextFont {
        ;
        
        static final HashMap<String, Typeface> fontCache = null;

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont$1  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass1 extends TextFont {
            public /* synthetic */ AnonymousClass1() {
                this("NOTO_SERIF_REGULAR", 0);
            }

            public Typeface getTypeface() {
                return TextFont.fontCache.computeIfAbsent(name(), new a(0));
            }

            private AnonymousClass1(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont$2  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass2 extends TextFont {
            public /* synthetic */ AnonymousClass2() {
                this("ROBOTO_BOLD", 1);
            }

            public Typeface getTypeface() {
                if (FontTypefaceUtils.IS_U_OS) {
                    return TextFont.fontCache.computeIfAbsent(name(), new a(1));
                }
                return TextFont.fontCache.computeIfAbsent(name(), new a(2));
            }

            private AnonymousClass2(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont$3  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass3 extends TextFont {
            public /* synthetic */ AnonymousClass3() {
                this("ROBOTO_SEMI_BOLD", 2);
            }

            public Typeface getTypeface() {
                if (FontTypefaceUtils.IS_U_OS) {
                    return TextFont.fontCache.computeIfAbsent(name(), new a(3));
                }
                return TextFont.fontCache.computeIfAbsent(name(), new a(4));
            }

            private AnonymousClass3(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont$4  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass4 extends TextFont {
            public /* synthetic */ AnonymousClass4() {
                this("ROBOTO_REGULAR", 3);
            }

            public Typeface getTypeface() {
                if (FontTypefaceUtils.IS_U_OS) {
                    return TextFont.fontCache.computeIfAbsent(name(), new a(5));
                }
                return TextFont.fontCache.computeIfAbsent(name(), new a(6));
            }

            private AnonymousClass4(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont$5  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass5 extends TextFont {
            public /* synthetic */ AnonymousClass5() {
                this("COMING_SOON", 4);
            }

            public Typeface getTypeface() {
                return TextFont.fontCache.computeIfAbsent(name(), new a(7));
            }

            private AnonymousClass5(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont$6  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass6 extends TextFont {
            public /* synthetic */ AnonymousClass6() {
                this("SAMSUNG_KOREAN_LIGHT", 5);
            }

            public Typeface getTypeface() {
                return TextFont.fontCache.computeIfAbsent(name(), new a(8));
            }

            private AnonymousClass6(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont$7  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass7 extends TextFont {
            public /* synthetic */ AnonymousClass7() {
                this("SAMSUNG_KOREAN_REGULAR", 6);
            }

            public Typeface getTypeface() {
                return TextFont.fontCache.computeIfAbsent(name(), new a(9));
            }

            private AnonymousClass7(String str, int i2) {
                super(str, i2, 0);
            }
        }

        /* 'enum' modifier removed */
        /* renamed from: com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont$8  reason: invalid class name */
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public final class AnonymousClass8 extends TextFont {
            public /* synthetic */ AnonymousClass8() {
                this("SAMSUNG_KOREAN_BOLD", 7);
            }

            public Typeface getTypeface() {
                return TextFont.fontCache.computeIfAbsent(name(), new a(10));
            }

            private AnonymousClass8(String str, int i2) {
                super(str, i2, 0);
            }
        }

        static {
            fontCache = new HashMap<>();
        }

        public abstract Typeface getTypeface();
    }

    public static Typeface getTextFont(int i2, int i7, boolean z) {
        int abs = Math.abs(i7) % 10;
        if (!z) {
            if (StoryLevel2Cat.isOverseas(i2) || StoryLevel2Cat.isScenery(i2)) {
                if (abs < 3) {
                    return getTextFontTypeface(TextFont.NOTO_SERIF_REGULAR);
                }
            } else if (StoryLevel2Cat.isOuting(i2) || StoryLevel2Cat.isDaily(i2)) {
                if (abs < 5) {
                    return getTextFontTypeface(TextFont.NOTO_SERIF_REGULAR);
                }
            } else if (StoryLevel2Cat.isPet(i2)) {
                if (abs < 3) {
                    return getTextFontTypeface(TextFont.NOTO_SERIF_REGULAR);
                }
                if (abs < 7) {
                    return getTextFontTypeface(TextFont.COMING_SOON);
                }
            }
            return getTextFontTypeface(TextFont.ROBOTO_BOLD);
        } else if (StoryLevel2Cat.isCollection(i2)) {
            return getTextFontTypeface(TextFont.SAMSUNG_KOREAN_LIGHT);
        } else {
            return getTextFontTypeface(TextFont.SAMSUNG_KOREAN_REGULAR);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r1 = getTextFontTypeface(com.samsung.android.gallery.module.story.FontTypefaceUtils.TextFont.ROBOTO_BOLD);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized android.graphics.Typeface getTextFontTypeface(com.samsung.android.gallery.module.story.FontTypefaceUtils.TextFont r1) {
        /*
            java.lang.Class<com.samsung.android.gallery.module.story.FontTypefaceUtils> r0 = com.samsung.android.gallery.module.story.FontTypefaceUtils.class
            monitor-enter(r0)
            android.graphics.Typeface r1 = r1.getTypeface()     // Catch:{ Exception -> 0x000b }
            monitor-exit(r0)
            return r1
        L_0x0009:
            r1 = move-exception
            goto L_0x0013
        L_0x000b:
            com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont r1 = com.samsung.android.gallery.module.story.FontTypefaceUtils.TextFont.ROBOTO_BOLD     // Catch:{ all -> 0x0009 }
            android.graphics.Typeface r1 = getTextFontTypeface(r1)     // Catch:{ all -> 0x0009 }
            monitor-exit(r0)
            return r1
        L_0x0013:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.FontTypefaceUtils.getTextFontTypeface(com.samsung.android.gallery.module.story.FontTypefaceUtils$TextFont):android.graphics.Typeface");
    }
}
