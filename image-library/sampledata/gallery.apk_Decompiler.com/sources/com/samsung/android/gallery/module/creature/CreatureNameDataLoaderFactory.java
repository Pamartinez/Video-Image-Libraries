package com.samsung.android.gallery.module.creature;

import com.samsung.android.gallery.module.creature.base.CreatureNameDataLoader;
import com.samsung.android.gallery.module.creature.people.PersonNameDataLoader;
import com.samsung.android.gallery.module.creature.pet.PetNameDataLoader;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CreatureNameDataLoaderFactory {
    public static CreatureNameDataLoader get(boolean z) {
        return get(z, (List<Long>) null);
    }

    public static CreatureNameDataLoader get(boolean z, List<Long> list) {
        return z ? new PetNameDataLoader(list) : new PersonNameDataLoader(list);
    }
}
