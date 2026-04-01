package com.samsung.android.gallery.module.story.transcode.renderer.constants;

import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.sdk.spage.card.CardContent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum BlendOption {
    ;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.transcode.renderer.constants.BlendOption$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends BlendOption {
        public /* synthetic */ AnonymousClass1() {
            this("NONE", 0);
        }

        public int get() {
            return -1;
        }

        public boolean supportBlend() {
            return false;
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.transcode.renderer.constants.BlendOption$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends BlendOption {
        public /* synthetic */ AnonymousClass2() {
            this(CardContent.NORMAL, 1);
        }

        public int get() {
            return ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8;
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.transcode.renderer.constants.BlendOption$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends BlendOption {
        public /* synthetic */ AnonymousClass3() {
            this("ADDITIVE", 2);
        }

        public int get() {
            return 1;
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public abstract int get();

    public boolean supportBlend() {
        return true;
    }
}
