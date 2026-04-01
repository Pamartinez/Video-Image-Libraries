package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.abstraction.RemasterSuggestGroup;
import com.samsung.android.gallery.module.abstraction.StoryCategory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import java.util.function.ToIntFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class T implements ToIntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2960a;

    public /* synthetic */ T(int i2) {
        this.f2960a = i2;
    }

    public final int applyAsInt(Object obj) {
        switch (this.f2960a) {
            case 0:
                return MediaItemUtil.getContentCount((MediaItem) obj);
            case 1:
                return ((RemasterSuggestGroup) obj).ordinal();
            default:
                return StoryCategory.getOrder((String) obj);
        }
    }
}
