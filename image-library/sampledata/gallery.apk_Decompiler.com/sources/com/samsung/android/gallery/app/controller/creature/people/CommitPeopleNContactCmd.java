package com.samsung.android.gallery.app.controller.creature.people;

import K3.C0404a;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.PersonLinkCmd;
import com.samsung.android.gallery.app.controller.creature.abstraction.CommitCreatureCmd;
import com.samsung.android.gallery.database.dal.contact.ContactApi;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CommitPeopleNContactCmd extends CommitCreatureCmd {
    private boolean isNeedToUpdatePhoto(CreatureNameData creatureNameData) {
        boolean z;
        if (creatureNameData.isAccountProfile() || !new ContactApi().isSimAccount(creatureNameData.getContactRawId())) {
            z = false;
        } else {
            z = true;
        }
        if ((creatureNameData.isEmptyContactImage() || creatureNameData.isEmptyAccountImage()) && !z) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$mergeCreature$0(long j2, MediaItem mediaItem) {
        PeopleDataManager.merge(new String[]{mediaItem.getSubCategory()}, createIdentityInfo(j2), MyQueryUtil.getInterface());
    }

    private void updateContactPhoto(EventContext eventContext, CreatureNameData creatureNameData, Bitmap bitmap) {
        if (isNeedToUpdatePhoto(creatureNameData)) {
            new UpdateContactPhotoCmd().execute(eventContext, creatureNameData, bitmap);
        } else {
            postEventToDismiss();
        }
    }

    public long addName(String str, CreatureNameData creatureNameData) {
        return PeopleDataManager.addName(str, creatureNameData);
    }

    public void addTag(EventContext eventContext, CreatureNameData creatureNameData, String str, Bitmap bitmap) {
        super.addTag(eventContext, creatureNameData, str, bitmap);
        if (creatureNameData != null && creatureNameData.isContact()) {
            new PersonLinkCmd().execute(getHandler(), creatureNameData);
        }
    }

    public String createIdentityInfo(long j2) {
        return IdentityCreatureUtil.createWithCreatureId(j2, IdentityCreatureUtil.Category.PEOPLE);
    }

    public String getCreatureCategoryType() {
        return "People";
    }

    public void mergeCreature(MediaItem mediaItem, long j2) {
        Optional.ofNullable(mediaItem).ifPresent(new C0404a(this, j2, 0));
    }

    public void postAddTag(EventContext eventContext, CreatureNameData creatureNameData, Bitmap bitmap) {
        updateContactPhoto(eventContext, creatureNameData, bitmap);
        this.mIsMe = TextUtils.equals("me", creatureNameData.getRelationship());
    }
}
