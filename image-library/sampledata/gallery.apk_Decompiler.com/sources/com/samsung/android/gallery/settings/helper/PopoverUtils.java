package com.samsung.android.gallery.settings.helper;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import com.samsung.android.gallery.settings.R$bool;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PopoverUtils {

    /* renamed from: com.samsung.android.gallery.settings.helper.PopoverUtils$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$settings$helper$PopoverUtils$Anchor;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.settings.helper.PopoverUtils$Anchor[] r0 = com.samsung.android.gallery.settings.helper.PopoverUtils.Anchor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$settings$helper$PopoverUtils$Anchor = r0
                com.samsung.android.gallery.settings.helper.PopoverUtils$Anchor r1 = com.samsung.android.gallery.settings.helper.PopoverUtils.Anchor.TOP_START     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$settings$helper$PopoverUtils$Anchor     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.settings.helper.PopoverUtils$Anchor r1 = com.samsung.android.gallery.settings.helper.PopoverUtils.Anchor.TOP_END     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$settings$helper$PopoverUtils$Anchor     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.settings.helper.PopoverUtils$Anchor r1 = com.samsung.android.gallery.settings.helper.PopoverUtils.Anchor.BOTTOM_START     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$settings$helper$PopoverUtils$Anchor     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.settings.helper.PopoverUtils$Anchor r1 = com.samsung.android.gallery.settings.helper.PopoverUtils.Anchor.BOTTOM_END     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.settings.helper.PopoverUtils.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ActivityOptionsBuilder {
        private final Anchor anchor;
        private final Context context;
        private final int[] height = new int[2];
        private final Point[] margin = new Point[2];
        private final int[] position = new int[2];
        private final int[] width = new int[2];

        public ActivityOptionsBuilder(Context context2, Anchor anchor2) {
            this.context = context2;
            this.anchor = anchor2;
            init();
        }

        /* access modifiers changed from: private */
        public Bundle build() {
            try {
                ActivityOptions makeBasic = ActivityOptions.makeBasic();
                makeBasic.semSetPopOverOptions(this.width, this.height, this.margin, this.position);
                return makeBasic.toBundle();
            } catch (Exception e) {
                Log.e((CharSequence) "PopoverUtils", "fail to build activity options", (Throwable) e);
                return null;
            }
        }

        private void init() {
            int[] iArr = this.width;
            iArr[1] = 360;
            iArr[0] = 360;
            int[] iArr2 = this.height;
            iArr2[1] = 731;
            iArr2[0] = 731;
            Point[] pointArr = this.margin;
            Point point = new Point(0, 0);
            pointArr[1] = point;
            pointArr[0] = point;
            if (this.anchor != null) {
                boolean z = this.context.getResources().getBoolean(R$bool.is_right_to_left);
                int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$settings$helper$PopoverUtils$Anchor[this.anchor.ordinal()];
                int i7 = 32;
                if (i2 == 1) {
                    int[] iArr3 = this.position;
                    if (!z) {
                        i7 = 16;
                    }
                    iArr3[0] = i7 | 1;
                } else if (i2 == 2) {
                    int[] iArr4 = this.position;
                    if (z) {
                        i7 = 16;
                    }
                    iArr4[0] = i7 | 1;
                } else if (i2 == 3) {
                    int[] iArr5 = this.position;
                    if (!z) {
                        i7 = 16;
                    }
                    iArr5[0] = i7 | 2;
                } else if (i2 != 4) {
                    Log.w("PopoverUtils", "invalid anchor");
                } else {
                    int[] iArr6 = this.position;
                    if (z) {
                        i7 = 16;
                    }
                    iArr6[0] = i7 | 2;
                }
                int[] iArr7 = this.position;
                iArr7[1] = iArr7[0];
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Anchor {
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END
    }

    public static boolean isPopover(Context context) {
        if (context == null || !context.getResources().getConfiguration().semIsPopOver()) {
            return false;
        }
        return true;
    }

    public static void startActivity(Context context, Intent intent, Anchor anchor) {
        context.startActivity(intent, new ActivityOptionsBuilder(context, anchor).build());
    }
}
