package com.samsung.android.gallery.app.controller.creature.pet;

import com.samsung.android.gallery.app.controller.creature.abstraction.CreatureHideCmd;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PetHideCmd extends CreatureHideCmd {
    public void hideCreature(ArrayList<Long> arrayList) {
        PetDataManager.hidePet(arrayList, MyQueryUtil.getInterface());
    }
}
