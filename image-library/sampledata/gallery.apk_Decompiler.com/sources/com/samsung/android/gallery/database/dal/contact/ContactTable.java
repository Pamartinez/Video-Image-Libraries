package com.samsung.android.gallery.database.dal.contact;

import A.a;
import android.net.Uri;
import android.provider.ContactsContract;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.moneta.basicdomain.common.ContentProviderConstants;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ContactTable {
    public String[] getAccountTypeProjection() {
        return new String[]{"account_type"};
    }

    public Uri getContactUri() {
        return ContactsContract.Contacts.CONTENT_URI;
    }

    public Uri getDataContactUri() {
        return ContactsContract.Data.CONTENT_URI;
    }

    public String[] getDataProjection() {
        return new String[]{ContentProviderConstants.PersonLink.ParameterKey.RAW_CONTACT_ID, "display_name", "data2", BuddyContract.ProfileCard.QUERY_PARAM_MIMETYPE, "account_type", "photo_uri"};
    }

    public String getDefaultOrderBy() {
        return "display_name ASC";
    }

    public String[] getDefaultProjection() {
        return new String[]{"_id", "display_name", "name_raw_contact_id", "photo_uri"};
    }

    public Uri getFrequentlyContactedUri() {
        return ContactsContract.Contacts.CONTENT_FREQUENT_URI;
    }

    public Uri getMyProfileUri() {
        return ContactsContract.Profile.CONTENT_URI;
    }

    public String getOrderByLimit() {
        return "times_used DESC limit 4";
    }

    public Uri getRawContactUri() {
        return ContactsContract.RawContacts.CONTENT_URI;
    }

    public String getSelectionForRelation() {
        return "mimetype = 'vnd.android.cursor.item/profile_relation' AND account_type = 'com.osp.app.signin'";
    }

    public String getSelectionNullCheckForName() {
        return "display_name is not null";
    }

    public String getSelectionToCheckSimAccount(long j2) {
        return a.e(j2, "_id = '", "'");
    }

    public String getSelectionNullCheckForName(long j2) {
        return a.e(j2, "display_name is not null AND name_raw_contact_id = ", "");
    }
}
