package com.samsung.android.gallery.module.creature.people;

import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import java.util.function.LongPredicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements LongPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2923a;
    public final /* synthetic */ PeopleData b;

    public /* synthetic */ b(PeopleData peopleData, int i2) {
        this.f2923a = i2;
        this.b = peopleData;
    }

    public final boolean test(long j2) {
        int i2 = this.f2923a;
        PeopleData peopleData = this.b;
        switch (i2) {
            case 0:
                return PeopleDataManager.PeopleDataHolder.lambda$updateByUnifiedId$7(peopleData, j2);
            case 1:
                return PeopleDataManager.PeopleDataHolder.lambda$updateByGroup$2(peopleData, j2);
            default:
                return PeopleDataManager.PeopleDataHolder.lambda$updateByPerson$5(peopleData, j2);
        }
    }
}
