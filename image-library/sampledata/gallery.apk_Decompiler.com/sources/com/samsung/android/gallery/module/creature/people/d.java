package com.samsung.android.gallery.module.creature.people;

import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2925a;
    public final /* synthetic */ long b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2926c;
    public final /* synthetic */ long d;

    public /* synthetic */ d(long j2, String str, long j3, int i2) {
        this.f2925a = i2;
        this.b = j2;
        this.f2926c = str;
        this.d = j3;
    }

    public final void accept(Object obj, Object obj2) {
        switch (this.f2925a) {
            case 0:
                String str = this.f2926c;
                long j2 = this.b;
                long j3 = this.d;
                PeopleDataManager.PeopleDataHolder.lambda$updateByPerson$4(j2, str, j3, (Long) obj, (PeopleDataManager.PeopleDataHolder.Data) obj2);
                return;
            case 1:
                PeopleDataManager.PeopleDataHolder.lambda$updateByGroup$1(this.b, this.f2926c, this.d, (Long) obj, (PeopleDataManager.PeopleDataHolder.Data) obj2);
                return;
            default:
                PeopleDataManager.PeopleDataHolder.lambda$updateByUnifiedId$9(this.b, this.f2926c, this.d, (Long) obj, (PeopleDataManager.PeopleDataHolder.Data) obj2);
                return;
        }
    }
}
