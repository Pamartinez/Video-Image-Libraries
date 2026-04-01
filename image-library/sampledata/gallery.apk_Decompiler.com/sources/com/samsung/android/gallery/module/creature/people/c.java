package com.samsung.android.gallery.module.creature.people;

import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PeopleDataManager.PeopleDataHolder f2924a;

    public /* synthetic */ c(PeopleDataManager.PeopleDataHolder peopleDataHolder) {
        this.f2924a = peopleDataHolder;
    }

    public final Object apply(Object obj) {
        return this.f2924a.loadPeopleData(((Long) obj).longValue());
    }
}
