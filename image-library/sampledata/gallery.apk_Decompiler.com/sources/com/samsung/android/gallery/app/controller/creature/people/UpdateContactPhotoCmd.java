package com.samsung.android.gallery.app.controller.creature.people;

import Fb.h;
import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.contact.ContactApi;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UpdateContactPhotoCmd extends BaseCommand {
    private Bitmap mBitmap = null;
    private CreatureNameData mCreatureNameData;

    private void saveBitmapToContact() {
        if (new ContactApi().updatePhoto(BitmapUtils.compressToBytes(this.mBitmap), this.mCreatureNameData.getContactRawId(), this.mCreatureNameData.isAccountProfile())) {
            showToast((int) R.string.contact_updated_with_most_recent_picture, 1);
        }
        this.mBitmap = null;
    }

    /* access modifiers changed from: private */
    public void updatePhoto(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            if (this.mBitmap == null) {
                Log.d(this.TAG, "Bitmap is null");
            } else {
                saveBitmapToContact();
            }
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mCreatureNameData = objArr[0];
        this.mBitmap = objArr[1];
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/UpdateContactPhoto").appendArg("name", this.mCreatureNameData.getName()).appendArg("screenId", getScreenId()).build()).setOnDataCompleteListener(new h(29, this)).start();
    }
}
