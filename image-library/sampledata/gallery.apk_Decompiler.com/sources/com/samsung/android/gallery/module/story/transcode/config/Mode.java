package com.samsung.android.gallery.module.story.transcode.config;

import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.story.DurationHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Mode {
    ;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.transcode.config.Mode$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends Mode {
        public /* synthetic */ AnonymousClass1() {
            this("STORY", 0);
        }

        public int getDuration(ThumbnailInterface thumbnailInterface) {
            return DurationHelper.getItemDuration(thumbnailInterface);
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.transcode.config.Mode$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends Mode {
        public /* synthetic */ AnonymousClass2() {
            this("SLIDESHOW", 1);
        }

        public int getDuration(ThumbnailInterface thumbnailInterface) {
            return DurationHelper.getFixedDuration(thumbnailInterface);
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.transcode.config.Mode$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends Mode {
        public /* synthetic */ AnonymousClass3() {
            this("COLLAGE", 2);
        }

        public int getDuration(ThumbnailInterface thumbnailInterface) {
            return Math.min(DurationHelper.getItemDuration(thumbnailInterface), TextToSpeechConst.MAX_SPEECH_INPUT);
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.transcode.config.Mode$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends Mode {
        public /* synthetic */ AnonymousClass4() {
            this("MOTION_PHOTO", 3);
        }

        public int getDuration(ThumbnailInterface thumbnailInterface) {
            return MetadataManager.getInstance().lambda$preload$0(thumbnailInterface).duration;
        }

        public int getStartTime(ThumbnailInterface thumbnailInterface) {
            return 0;
        }

        private AnonymousClass4(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public abstract int getDuration(ThumbnailInterface thumbnailInterface);

    public int getStartTime(ThumbnailInterface thumbnailInterface) {
        return (int) (thumbnailInterface.getVideoThumbStartTime() / 1000);
    }
}
