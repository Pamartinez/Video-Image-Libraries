package com.samsung.android.gallery.module.creature.base;

import A3.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureNameData {
    /* access modifiers changed from: private */
    public String mCategoryType;
    /* access modifiers changed from: private */
    public String mContactPhotoUri;
    private Drawable mContactPresetDrawable;
    /* access modifiers changed from: private */
    public long mContactRawId;
    /* access modifiers changed from: private */
    public ContactType mContactType;
    private Bitmap mFaceBitmap;
    /* access modifiers changed from: private */
    public long mId;
    /* access modifiers changed from: private */
    public String mInitialLetter = "";
    /* access modifiers changed from: private */
    public boolean mIsAccountProfile;
    /* access modifiers changed from: private */
    public MediaItem mMediaItem;
    /* access modifiers changed from: private */
    public MediaType mMediaType = MediaType.Image;
    /* access modifiers changed from: private */
    public String mName;
    private String mNameLower;
    /* access modifiers changed from: private */
    public int mOrientation;
    /* access modifiers changed from: private */
    public byte[] mPhotoData;
    /* access modifiers changed from: private */
    public RectF mPosRatio;
    /* access modifiers changed from: private */
    public String mRelationship;
    private long mSimilarContactGroupId;
    private String mUuid;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        CreatureNameData data;

        public Builder(String str, ContactType contactType) {
            CreatureNameData creatureNameData = new CreatureNameData();
            this.data = creatureNameData;
            creatureNameData.mCategoryType = str;
            this.data.mContactType = contactType;
            this.data.mId = 1;
        }

        public Builder assignAccountProfile() {
            this.data.mIsAccountProfile = true;
            this.data.mContactRawId = -100;
            return this;
        }

        public CreatureNameData build() {
            return this.data;
        }

        public Builder setContactRawId(long j2) {
            this.data.mContactRawId = j2;
            return this;
        }

        public Builder setId(long j2) {
            this.data.mId = j2;
            return this;
        }

        public Builder setInitialLetter(String str) {
            this.data.mInitialLetter = str;
            return this;
        }

        public Builder setMediaItem(MediaItem mediaItem) {
            this.data.mMediaItem = mediaItem;
            return this;
        }

        public Builder setMediaType(MediaType mediaType) {
            this.data.mMediaType = mediaType;
            return this;
        }

        public Builder setName(String str) {
            this.data.mName = str;
            return this;
        }

        public Builder setOrientation(int i2) {
            this.data.mOrientation = i2;
            return this;
        }

        public Builder setPhotoData(byte[] bArr) {
            this.data.mPhotoData = bArr;
            return this;
        }

        public Builder setPhotoUri(String str) {
            this.data.mContactPhotoUri = str;
            return this;
        }

        public Builder setPosRatio(RectF rectF) {
            this.data.mPosRatio = rectF;
            return this;
        }

        public Builder setRelationship(String str) {
            this.data.mRelationship = str;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ContactType {
        MY_PROFILE,
        TAGGED,
        HEADER,
        FREQUENTLY_CONTACT,
        CONTACT,
        RELATED_MY_PROFILE_CONTACT,
        RECOMMEND_CONTACT
    }

    private long getId() {
        long j2 = this.mContactRawId;
        if (j2 > 0) {
            return j2;
        }
        return this.mId;
    }

    public boolean contains(String str) {
        String str2;
        if (this.mNameLower == null) {
            String str3 = this.mName;
            if (str3 == null) {
                str2 = "";
            } else {
                str2 = str3.toLowerCase(Locale.getDefault());
            }
            this.mNameLower = str2;
        }
        if (this.mNameLower.isEmpty() || this.mNameLower.contains(str)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CreatureNameData) {
            CreatureNameData creatureNameData = (CreatureNameData) obj;
            if (this.mId == creatureNameData.mId && this.mContactRawId == creatureNameData.mContactRawId && this.mContactType == creatureNameData.getContactType() && TextUtils.equals(this.mContactPhotoUri, creatureNameData.getContactPhotoUri()) && this.mPhotoData == creatureNameData.getPhotoData()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public String getContactPhotoUri() {
        return this.mContactPhotoUri;
    }

    public Drawable getContactPresetDrawable(Context context) {
        int i2;
        int abs;
        if (this.mContactPresetDrawable == null) {
            long j2 = this.mContactRawId;
            if (j2 > 0) {
                String str = this.mName;
                int i7 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
                int[] iArr = a.f33a;
                int i8 = 0;
                if (i7 > 0) {
                    if (i7 >= 0) {
                        i8 = (int) (j2 % ((long) 35));
                    }
                    i2 = iArr[i8];
                } else {
                    if (str != null && (abs = Math.abs(str.hashCode())) >= 0) {
                        i8 = (int) ((long) (abs % 35));
                    }
                    i2 = iArr[i8];
                }
                this.mContactPresetDrawable = context.getDrawable(i2);
            }
        }
        return this.mContactPresetDrawable;
    }

    public long getContactRawId() {
        return this.mContactRawId;
    }

    public ContactType getContactType() {
        return this.mContactType;
    }

    public long getCreatureId() {
        return this.mId;
    }

    public String getCreatureUuid() {
        return this.mUuid;
    }

    public Bitmap getFaceBitmap() {
        return this.mFaceBitmap;
    }

    public String getInitialLetter() {
        return this.mInitialLetter;
    }

    public ThumbnailInterface getMediaItem() {
        if (this.mMediaItem == null) {
            MediaItem mediaItem = new MediaItem();
            this.mMediaItem = mediaItem;
            mediaItem.setFileId(getId());
            this.mMediaItem.setDisplayName(this.mName);
            this.mMediaItem.setStorageType(StorageType.ContactItem);
            this.mMediaItem.setMediaType(this.mMediaType);
            MediaItem mediaItem2 = this.mMediaItem;
            String str = this.mContactPhotoUri;
            if (str == null) {
                str = "null";
            }
            mediaItem2.setPath(str);
            this.mMediaItem.setOrientation(this.mOrientation);
            this.mMediaItem.setCropRect(this.mPosRatio);
            this.mMediaItem.setTag("contact-photo-stream", this.mPhotoData);
            this.mMediaItem.setTag("contact-tagged", Boolean.valueOf(isTagged()));
        }
        return this.mMediaItem;
    }

    public String getName() {
        return this.mName;
    }

    public byte[] getPhotoData() {
        return this.mPhotoData;
    }

    public String getPrivateUriLog(String str) {
        if (str == null || !str.startsWith("file://")) {
            return str;
        }
        return "file";
    }

    public String getRelationship() {
        return this.mRelationship;
    }

    public String getRelationshipForDB() {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP || !CustomRelationshipKeySet.isCustomRelationshipType(this.mRelationship)) {
            return this.mRelationship;
        }
        return this.mRelationship.replace("<custom>", "");
    }

    public long getSimilarContactGroupId() {
        return this.mSimilarContactGroupId;
    }

    public long getUid() {
        int i2;
        long id = getId();
        if (this.mFaceBitmap != null) {
            i2 = 66;
        } else if (hasValidPhoto()) {
            i2 = 86;
        } else {
            i2 = 78;
        }
        return id + (((long) i2) << 52);
    }

    public boolean hasSameName(String str) {
        return TextUtils.equals(this.mName, str);
    }

    public boolean hasValidPhoto() {
        if (this.mContactPhotoUri == null && this.mPhotoData == null) {
            return false;
        }
        return true;
    }

    public boolean isAccountProfile() {
        return this.mIsAccountProfile;
    }

    public boolean isContact() {
        if (this.mContactType == ContactType.CONTACT) {
            return true;
        }
        return false;
    }

    public boolean isEmptyAccountImage() {
        if (!this.mIsAccountProfile) {
            return false;
        }
        byte[] bArr = this.mPhotoData;
        if (bArr == null || bArr.length == 0) {
            return true;
        }
        return false;
    }

    public boolean isEmptyContactImage() {
        if (this.mContactRawId <= 0 || this.mContactPhotoUri != null) {
            return false;
        }
        return true;
    }

    public boolean isEmptyName() {
        return TextUtils.isEmpty(this.mName);
    }

    public boolean isFrequentlyUsed() {
        ContactType contactType = this.mContactType;
        if (contactType == ContactType.FREQUENTLY_CONTACT || contactType == ContactType.HEADER) {
            return true;
        }
        return false;
    }

    public boolean isHeader() {
        if (this.mContactType == ContactType.HEADER) {
            return true;
        }
        return false;
    }

    public boolean isMyProfile() {
        if (this.mContactType == ContactType.MY_PROFILE) {
            return true;
        }
        return false;
    }

    public boolean isPeople() {
        return TextUtils.equals(this.mCategoryType, "People");
    }

    public boolean isSameContactPerson(MediaItem mediaItem) {
        if (!isContact() || !CreatureData.hasContactRawId(mediaItem) || this.mContactRawId != Long.parseLong(CreatureData.of(mediaItem).contactRawId)) {
            return false;
        }
        return true;
    }

    public boolean isTagged() {
        if (this.mContactType == ContactType.TAGGED) {
            return true;
        }
        return false;
    }

    public boolean isTaggedData() {
        if (this.mContactType == ContactType.TAGGED) {
            return true;
        }
        return false;
    }

    public void setCreatureUuid(String str) {
        this.mUuid = str;
    }

    public void setFaceBitmap(Bitmap bitmap) {
        this.mFaceBitmap = bitmap;
    }

    public void setSimilarContactGroupId(long j2) {
        this.mSimilarContactGroupId = j2;
    }

    public String toString() {
        Object obj;
        char c5;
        StringBuilder sb2 = new StringBuilder("CreatureName{");
        if (this.mFaceBitmap != null) {
            c5 = 'B';
        } else if (hasValidPhoto()) {
            c5 = 'V';
        } else {
            obj = "N";
            sb2.append(obj);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mId);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mContactRawId);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mContactType);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mInitialLetter);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(getPrivateUriLog(this.mContactPhotoUri));
            sb2.append("}");
            return sb2.toString();
        }
        obj = Character.valueOf(c5);
        sb2.append(obj);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mContactRawId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mContactType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mInitialLetter);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getPrivateUriLog(this.mContactPhotoUri));
        sb2.append("}");
        return sb2.toString();
    }
}
