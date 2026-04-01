package com.samsung.android.gallery.module.creature.people;

import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ PeopleDataManager.PeopleDataHolder d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Consumer f;

    public /* synthetic */ f(PeopleDataManager.PeopleDataHolder peopleDataHolder, long j2, Consumer consumer) {
        this.d = peopleDataHolder;
        this.e = j2;
        this.f = consumer;
    }

    public final void run() {
        this.d.lambda$load$0(this.e, this.f);
    }
}
