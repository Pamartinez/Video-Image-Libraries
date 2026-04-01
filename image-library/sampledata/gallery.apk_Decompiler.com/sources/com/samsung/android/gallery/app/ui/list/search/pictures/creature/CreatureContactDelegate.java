package com.samsung.android.gallery.app.ui.list.search.pictures.creature;

import D9.a;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ICreatureContactDelegate;
import com.samsung.android.gallery.database.dal.contact.ContactApi;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.engine.PersonalLinkCore;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.function.Consumer;
import x6.f;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureContactDelegate implements ICreatureContactDelegate {
    private ActivityResultLauncher<Intent> mContactDetail;
    private ActivityResultLauncher<Intent> mContactPicker;
    private MediaItem mCreatureItem;
    private final IMvpBaseView mView;

    public CreatureContactDelegate(IMvpBaseView iMvpBaseView, Consumer<Object> consumer) {
        this.mView = iMvpBaseView;
        createContactDetail(consumer);
        createContactPicker(consumer);
    }

    private void createContactDetail(Consumer<Object> consumer) {
        this.mContactDetail = ((Fragment) this.mView).registerForActivityResult(new ActivityResultContracts$StartActivityForResult(), new a(9, consumer));
    }

    private void createContactPicker(Consumer<Object> consumer) {
        this.mContactPicker = ((Fragment) this.mView).registerForActivityResult(new ActivityResultContracts$StartActivityForResult(), new a(10, consumer));
    }

    private String getContactId() {
        Cursor contactId;
        MediaItem mediaItem = this.mCreatureItem;
        if (mediaItem != null && !TextUtils.isEmpty(CreatureData.of(mediaItem).creatureUuid)) {
            long contactId2 = PersonalLinkCore.getInstance().getContactId(this.mView.getContext(), CreatureData.of(this.mCreatureItem).creatureUuid);
            if (contactId2 > 0) {
                return String.valueOf(contactId2);
            }
        }
        String str = null;
        if (CreatureData.hasContactRawId(this.mCreatureItem)) {
            try {
                contactId = new ContactApi().getContactId(Long.parseLong(CreatureData.of(this.mCreatureItem).contactRawId));
                if (contactId != null) {
                    if (contactId.moveToFirst()) {
                        str = contactId.getString(contactId.getColumnIndex("_id"));
                    }
                }
                if (contactId != null) {
                    contactId.close();
                }
                return str;
            } catch (Exception e) {
                A.a.s(e, new StringBuilder("fail to get contact id : "), "CreatureContactDelegate");
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return null;
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createContactPicker$1(Consumer consumer, ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1 && activityResult.getData() != null) {
            consumer.accept(activityResult.getData().getData());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchContactDetail$2(String str) {
        this.mContactDetail.launch(new Intent("android.intent.action.VIEW", Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, str)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchContactDetail$3() {
        String contactId = getContactId();
        if (TextUtils.isEmpty(contactId)) {
            Log.w("CreatureContactDelegate", "contact id is invalid");
        } else {
            ThreadUtil.postOnUiThread(new f(3, this, contactId));
        }
    }

    public void launchContactDetail() {
        SimpleThreadPool.getInstance().execute(new l(3, this));
    }

    public void launchContactPicker() {
        if (this.mContactPicker != null) {
            Intent intent = new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI);
            intent.setType("vnd.android.cursor.dir/contact");
            this.mContactPicker.launch(intent);
        }
    }

    public void setItem(MediaItem mediaItem) {
        this.mCreatureItem = mediaItem;
    }
}
