package com.samsung.android.gallery.module.story;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.smartrect.TitlePosition;
import com.samsung.android.gallery.support.utils.RectUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum TitleAlign {
    ;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.TitleAlign$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends TitleAlign {
        public /* synthetic */ AnonymousClass1() {
            this("TOP_START", 0);
        }

        public int getTextAlign() {
            return 5;
        }

        public int getTextGravity() {
            return 8388627;
        }

        public void setLayoutAlign(RelativeLayout.LayoutParams layoutParams) {
            TitleAlign.super.setLayoutAlign(layoutParams);
            layoutParams.addRule(10);
            layoutParams.addRule(20);
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.TitleAlign$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends TitleAlign {
        public /* synthetic */ AnonymousClass2() {
            this("TOP_MIDDLE", 1);
        }

        public int getTextAlign() {
            return 4;
        }

        public int getTextGravity() {
            return 17;
        }

        public void setLayoutAlign(RelativeLayout.LayoutParams layoutParams) {
            TitleAlign.super.setLayoutAlign(layoutParams);
            layoutParams.addRule(10);
            layoutParams.addRule(14);
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.TitleAlign$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends TitleAlign {
        public /* synthetic */ AnonymousClass3() {
            this("TOP_END", 2);
        }

        public int getTextAlign() {
            return 3;
        }

        public int getTextGravity() {
            return 8388629;
        }

        public void setLayoutAlign(RelativeLayout.LayoutParams layoutParams) {
            TitleAlign.super.setLayoutAlign(layoutParams);
            layoutParams.addRule(10);
            layoutParams.addRule(21);
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.TitleAlign$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends TitleAlign {
        public /* synthetic */ AnonymousClass4() {
            this("BOTTOM_START", 3);
        }

        public int getTextAlign() {
            return 2;
        }

        public int getTextGravity() {
            return 8388627;
        }

        public void setLayoutAlign(RelativeLayout.LayoutParams layoutParams) {
            TitleAlign.super.setLayoutAlign(layoutParams);
            layoutParams.addRule(12);
            layoutParams.addRule(20);
        }

        private AnonymousClass4(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.TitleAlign$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends TitleAlign {
        public /* synthetic */ AnonymousClass5() {
            this("BOTTOM_MIDDLE", 4);
        }

        public int getTextAlign() {
            return 4;
        }

        public int getTextGravity() {
            return 17;
        }

        public void setLayoutAlign(RelativeLayout.LayoutParams layoutParams) {
            TitleAlign.super.setLayoutAlign(layoutParams);
            layoutParams.addRule(12);
            layoutParams.addRule(14);
        }

        private AnonymousClass5(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.module.story.TitleAlign$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass6 extends TitleAlign {
        public /* synthetic */ AnonymousClass6() {
            this("BOTTOM_END", 5);
        }

        public int getTextAlign() {
            return 3;
        }

        public int getTextGravity() {
            return 8388629;
        }

        public void setLayoutAlign(RelativeLayout.LayoutParams layoutParams) {
            TitleAlign.super.setLayoutAlign(layoutParams);
            layoutParams.addRule(12);
            layoutParams.addRule(21);
        }

        private AnonymousClass6(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public static TitleAlign getTitleAlign(FileItemInterface fileItemInterface) {
        int titleAlignIndex;
        if (RectUtils.isValidRect(RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(fileItemInterface)))) {
            return BOTTOM_MIDDLE;
        }
        if (fileItemInterface == null || fileItemInterface.isBroken() || fileItemInterface.isVideo() || (titleAlignIndex = TitlePosition.getTitleAlignIndex(fileItemInterface)) < 0 || titleAlignIndex >= values().length) {
            return BOTTOM_MIDDLE;
        }
        return values()[titleAlignIndex];
    }

    private void resetLayoutRule(RelativeLayout.LayoutParams layoutParams) {
        layoutParams.removeRule(10);
        layoutParams.removeRule(12);
        layoutParams.removeRule(20);
        layoutParams.removeRule(14);
        layoutParams.removeRule(21);
    }

    public abstract int getTextAlign();

    public abstract int getTextGravity();

    public void setGradientDirection(View view) {
        int i2;
        if (view != null) {
            Context context = view.getContext();
            if (this == TOP_START || this == TOP_MIDDLE || this == TOP_END) {
                i2 = R$drawable.stories_pinch_item_upper_gradient;
            } else {
                i2 = R$drawable.stories_pinch_item_lower_gradient;
            }
            view.setBackground(context.getDrawable(i2));
        }
    }

    public void setLayoutAlign(RelativeLayout.LayoutParams layoutParams) {
        resetLayoutRule(layoutParams);
    }

    public void setTextAlign(TextView textView) {
        textView.setTextAlignment(getTextAlign());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.gravity = getTextGravity();
        textView.setLayoutParams(layoutParams);
    }

    public void setLayoutAlign(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            setLayoutAlign((RelativeLayout.LayoutParams) layoutParams);
            view.setLayoutParams(layoutParams);
            return;
        }
        throw new IllegalStateException("wrong parent layout");
    }
}
