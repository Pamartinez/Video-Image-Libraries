package com.samsung.android.gallery.app.controller.creature.pet;

import K3.C0404a;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.abstraction.CommitCreatureCmd;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CommitPetCmd extends CommitCreatureCmd {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$mergeCreature$0(long j2, MediaItem mediaItem) {
        PetDataManager.merge(new String[]{mediaItem.getSubCategory()}, createIdentityInfo(j2), MyQueryUtil.getInterface());
    }

    public long addName(String str, CreatureNameData creatureNameData) {
        return PetDataManager.addName(str, creatureNameData);
    }

    public String createIdentityInfo(long j2) {
        return IdentityCreatureUtil.createWithCreatureId(j2, IdentityCreatureUtil.Category.PET);
    }

    public String getCreatureCategoryType() {
        return "Pet";
    }

    public void mergeCreature(MediaItem mediaItem, long j2) {
        Optional.ofNullable(mediaItem).ifPresent(new C0404a(this, j2, 1));
    }

    public void postAddTag(EventContext eventContext, CreatureNameData creatureNameData, Bitmap bitmap) {
        postEventToDismiss();
    }
}
