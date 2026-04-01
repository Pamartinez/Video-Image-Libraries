package com.samsung.android.gallery.module.creature.people;

import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2927a;
    public final /* synthetic */ long[] b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2928c;
    public final /* synthetic */ long d;

    public /* synthetic */ e(long[] jArr, String str, long j2, int i2) {
        this.f2927a = i2;
        this.b = jArr;
        this.f2928c = str;
        this.d = j2;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2927a) {
            case 0:
                PeopleDataManager.PeopleDataHolder.lambda$updateByUnifiedId$8(this.b, this.f2928c, this.d, (Long) obj, (PeopleDataManager.PeopleDataHolder.Data) obj2);
                return;
            case 1:
                PeopleDataManager.PeopleDataHolder.lambda$updateByPerson$6(this.b, this.f2928c, this.d, (Long) obj, (PeopleDataManager.PeopleDataHolder.Data) obj2);
                return;
            default:
                PeopleDataManager.PeopleDataHolder.lambda$updateByGroup$3(this.b, this.f2928c, this.d, (Long) obj, (PeopleDataManager.PeopleDataHolder.Data) obj2);
                return;
        }
    }
}
