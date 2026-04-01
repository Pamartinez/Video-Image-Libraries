package com.samsung.android.gallery.module.publisher;

import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;
import java.util.function.BiFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e0 implements BiFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3066a;

    public /* synthetic */ e0(int i2) {
        this.f3066a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        String str = (String) obj;
        Integer num = (Integer) obj2;
        switch (this.f3066a) {
            case 0:
                return StoriesDataPublisher.CacheCursorWrapper.lambda$loadPosition$0(str, num);
            default:
                return StoriesDataPublisher.CacheCursorWrapper.lambda$loadPosition$1(str, num);
        }
    }
}
