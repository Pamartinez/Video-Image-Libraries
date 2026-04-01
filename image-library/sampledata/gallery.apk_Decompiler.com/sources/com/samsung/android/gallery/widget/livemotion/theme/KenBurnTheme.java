package com.samsung.android.gallery.widget.livemotion.theme;

import android.view.animation.Interpolator;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.livemotion.abstraction.IDuration;
import com.samsung.android.gallery.widget.livemotion.theme.Transition;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransformScale;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum KenBurnTheme {
    ;
    
    private static final float I_FRAME_SYNC_DELAY = 0.0175f;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass1() {
            this("Relaxing", 0);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN);
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$10  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass10 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass10() {
            this("Intense", 9);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                int i2 = (mod > 5 ? 1 : (mod == 5 ? 0 : -1));
                if (i2 < 0) {
                    return Transition.TYPE.FAST_FADE_IN;
                }
                if (i2 == 0) {
                    return Transition.TYPE.FAST_FADE_IN_ZOOM_OUT;
                }
                return Transition.TYPE.CUT;
            } else if (mod < 4) {
                return Transition.TYPE.FAST_FADE_IN;
            } else {
                return Transition.TYPE.CUT;
            }
        }

        private AnonymousClass10(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass2() {
            this("Lounge", 1);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                if (mod < 2) {
                    return Transition.TYPE.FAST_FADE_IN_ZOOM_OUT;
                }
                return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN);
            } else if (mod < 2) {
                return Transition.TYPE.CUT;
            } else {
                return Transition.TYPE.SLOW_FADE_IN;
            }
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass3() {
            this("Happy", 2);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                if (mod < 3) {
                    return Transition.TYPE.FAST_FADE_IN;
                }
                if (mod < 6) {
                    return Transition.TYPE.CUT;
                }
                if (mod < 7) {
                    return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN_ZOOM_OUT);
                }
                return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN);
            } else if (mod < 5) {
                return Transition.TYPE.CUT;
            } else {
                return Transition.TYPE.SLOW_FADE_IN;
            }
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass4() {
            this("Upbeat", 3);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                if (mod < 2) {
                    return Transition.TYPE.FAST_FADE_IN;
                }
                if (mod < 5) {
                    return Transition.TYPE.FAST_FADE_IN_ZOOM_OUT;
                }
                return Transition.TYPE.CUT;
            } else if (mod < 2) {
                return Transition.TYPE.FAST_FADE_IN;
            } else {
                return Transition.TYPE.CUT;
            }
        }

        private AnonymousClass4(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass5() {
            this("Lovely", 4);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                if (mod < 3) {
                    return Transition.TYPE.FAST_FADE_IN;
                }
                if (mod < 4) {
                    return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN_ZOOM_OUT);
                }
                return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN);
            } else if (mod < 4) {
                return Transition.TYPE.CUT;
            } else {
                return Transition.TYPE.SLOW_FADE_IN;
            }
        }

        private AnonymousClass5(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass6 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass6() {
            this("Dynamic", 5);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                if (mod < 5) {
                    return Transition.TYPE.FAST_FADE_IN;
                }
                return Transition.TYPE.CUT;
            } else if (mod < 3) {
                return Transition.TYPE.FAST_FADE_IN;
            } else {
                return Transition.TYPE.CUT;
            }
        }

        private AnonymousClass6(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$7  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass7 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass7() {
            this("Comic", 6);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                if (mod < 5) {
                    return Transition.TYPE.FAST_FADE_IN;
                }
                if (mod % 2 == 1) {
                    return Transition.TYPE.CUT;
                }
                return Transition.TYPE.FAST_FADE_IN_ZOOM_OUT;
            } else if (mod < 4) {
                return Transition.TYPE.FAST_FADE_IN;
            } else {
                return Transition.TYPE.CUT;
            }
        }

        private AnonymousClass7(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$8  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass8 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass8() {
            this("Sentimental", 7);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                if (mod < 2) {
                    return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN_ZOOM_OUT);
                }
                return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN);
            } else if (mod < 1) {
                return Transition.TYPE.CUT;
            } else {
                return Transition.TYPE.SLOW_FADE_IN;
            }
        }

        private AnonymousClass8(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.widget.livemotion.theme.KenBurnTheme$9  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass9 extends KenBurnTheme {
        public /* synthetic */ AnonymousClass9() {
            this("Mystery", 8);
        }

        public Transition.TYPE getTransitionType(MediaItem mediaItem) {
            long mod = getMod(mediaItem);
            if (isImageTransition(mediaItem)) {
                int i2 = (mod > 5 ? 1 : (mod == 5 ? 0 : -1));
                if (i2 < 0) {
                    return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN);
                }
                if (i2 == 0) {
                    return Transition.TYPE.FAST_FADE_IN;
                }
                if (mod % 2 == 0) {
                    return KenBurnTheme.fastTypeIfNeeded(mediaItem, Transition.TYPE.SLOW_FADE_IN_ZOOM_OUT);
                }
                return Transition.TYPE.CUT;
            } else if (mod < 3) {
                return Transition.TYPE.SLOW_FADE_IN;
            } else {
                return Transition.TYPE.CUT;
            }
        }

        private AnonymousClass9(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* access modifiers changed from: private */
    public static Transition.TYPE fastTypeIfNeeded(MediaItem mediaItem, Transition.TYPE type) {
        if (mediaItem != null && mediaItem.isGif()) {
            if (Transition.TYPE.SLOW_FADE_IN.equals(type)) {
                return Transition.TYPE.FAST_FADE_IN;
            }
            if (Transition.TYPE.SLOW_FADE_IN_ZOOM_OUT.equals(type)) {
                return Transition.TYPE.FAST_FADE_IN_ZOOM_OUT;
            }
        }
        return type;
    }

    private int getDuration(MediaItem mediaItem, IDuration iDuration) {
        return iDuration.getDuration(mediaItem);
    }

    private static KenBurnsInfo getKenBurnInfo(Map<ThumbnailInterface, KenBurnsInfo> map, MediaItem mediaItem) {
        KenBurnsInfo kenBurnsInfo = map.get(mediaItem);
        if (kenBurnsInfo != null) {
            return kenBurnsInfo;
        }
        KenBurnsInfo kenBurnsInfo2 = new KenBurnsInfo();
        map.put(mediaItem, kenBurnsInfo2);
        return kenBurnsInfo2;
    }

    public void buildKenBurnsInfo(Map<ThumbnailInterface, KenBurnsInfo> map, MediaItem mediaItem, MediaItem mediaItem2, IDuration iDuration) {
        KenBurnsInfo kenBurnInfo = getKenBurnInfo(map, mediaItem);
        Iterator<PageTransform> it = TransformCurrent.buildNext(mediaItem).iterator();
        while (it.hasNext()) {
            it.next().copyProperty(kenBurnInfo.getTransformProperty());
        }
        if (mediaItem2 != null) {
            KenBurnsInfo kenBurnInfo2 = getKenBurnInfo(map, mediaItem2);
            ArrayList<PageTransform> buildNext = TransformNext.buildNext(mediaItem2);
            KenBurnsInfo.Property transformProperty = kenBurnInfo2.getTransformProperty();
            Iterator<PageTransform> it2 = buildNext.iterator();
            while (it2.hasNext()) {
                it2.next().copyProperty(transformProperty);
            }
            KenBurnsInfo.Property transitionInProperty = kenBurnInfo.getTransitionInProperty();
            Iterator<PageTransform> it3 = Transition.build(getTransitionType(mediaItem2), getDuration(mediaItem, iDuration)).iterator();
            while (it3.hasNext()) {
                PageTransform next = it3.next();
                if (next.isVisiblePage()) {
                    next.copyProperty(kenBurnInfo.getTransitionOutProperty());
                } else {
                    transitionInProperty.setInitScaleX(transformProperty.getScaleX()).setTargetScaleX(transformProperty.getScaleX());
                    transitionInProperty.setInitScaleY(transformProperty.getScaleY()).setTargetScaleY(transformProperty.getScaleY());
                    transitionInProperty.setInitTranslateX(-transformProperty.getTranslateX()).setTargetTranslateX(-transformProperty.getTranslateX());
                    transitionInProperty.setInitTranslateY(-transformProperty.getTranslateY()).setTargetTranslateY(-transformProperty.getTranslateY());
                    if (!next.hasTargetView() || !(next instanceof PageTransformScale)) {
                        next.copyProperty(transitionInProperty);
                    } else {
                        transitionInProperty.setInitScaleX(transitionInProperty.getScaleX() + 0.100000024f).setInitScaleY(transitionInProperty.getScaleY() + 0.100000024f);
                    }
                    transitionInProperty.setDelay(Math.max(transitionInProperty.getDelay() - I_FRAME_SYNC_DELAY, 0.0f));
                }
            }
        }
    }

    public ArrayList<PageTransform> buildNext(MediaItem mediaItem, MediaItem mediaItem2, IDuration iDuration) {
        ArrayList<PageTransform> arrayList = new ArrayList<>(TransformCurrent.buildNext(mediaItem));
        if (mediaItem2 != null) {
            arrayList.addAll(Transition.build(getTransitionType(mediaItem2), getDuration(mediaItem, iDuration)));
            arrayList.addAll(TransformNext.buildNext(mediaItem2));
        }
        Log.d("KenBurnTheme", "build " + this);
        return arrayList;
    }

    public Interpolator getInterpolator(float f) {
        return new ThemeInterpolator(f);
    }

    public long getMod(MediaItem mediaItem) {
        return ThemeKey.getKey(mediaItem) % 10;
    }

    public abstract Transition.TYPE getTransitionType(MediaItem mediaItem);

    public boolean isImageTransition(MediaItem mediaItem) {
        return !StoryHelper.isVideoItem(mediaItem);
    }

    public ArrayList<PageTransform> prepare(MediaItem mediaItem) {
        return TransformNext.buildNext(mediaItem);
    }
}
