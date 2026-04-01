package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureSlideshow;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureSlideshow2;
import java.util.Objects;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2545a;

    public /* synthetic */ g(int i2) {
        this.f2545a = i2;
    }

    public final boolean test(Object obj) {
        switch (this.f2545a) {
            case 0:
                return Objects.nonNull((SearchCreatureSlideshow.SlidePage) obj);
            case 1:
                return SearchCreatureSlideshow.lambda$new$0((SearchCreatureSlideshow.SlidePage) obj);
            case 2:
                return ((SearchCreatureSlideshow.SlidePage) obj).isViewChanged();
            case 3:
                return Objects.nonNull((SearchCreatureSlideshow2.SlidePage) obj);
            case 4:
                return SearchCreatureSlideshow2.lambda$new$0((SearchCreatureSlideshow2.SlidePage) obj);
            default:
                return ((SearchCreatureSlideshow2.SlidePage) obj).isViewChanged();
        }
    }
}
