package com.samsung.android.gallery.module.story.smartrect;

import com.samsung.android.gallery.module.story.smartrect.TitlePosition;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3090a;

    public /* synthetic */ a(int i2) {
        this.f3090a = i2;
    }

    public final boolean test(Object obj) {
        TitlePosition.TitleInfo titleInfo = (TitlePosition.TitleInfo) obj;
        switch (this.f3090a) {
            case 0:
                return TitlePosition.lambda$getTitleAlignIndex$0(titleInfo);
            case 1:
                return titleInfo.intersectObject;
            case 2:
                return titleInfo.intersectFace;
            case 3:
                return TitlePosition.lambda$preferredIndexBaseOnFace$5(3, titleInfo);
            default:
                return TitlePosition.lambda$preferredIndexBaseOnFace$6(3, titleInfo);
        }
    }
}
