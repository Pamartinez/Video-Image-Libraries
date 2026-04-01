package com.samsung.android.gallery.module.details;

import android.text.SpannableString;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDetailsModel {
    private String mAddress;
    private String mCapturedUrl;
    private long mDateTime;
    private boolean mIsCapturedUrlDeleted = false;
    private double mLatitude;
    private long mLocalDateTime;
    private double mLongitude;
    private final String mOriginExtension;
    private final String mOriginTitle;
    private String mPoi;
    private long mPrevLocalDateTime;

    public EditDetailsModel(MediaItem mediaItem) {
        this.mDateTime = mediaItem.getDateTaken();
        long localDateTime = DetailsDataRefiner.getLocalDateTime(mediaItem);
        this.mLocalDateTime = localDateTime;
        this.mPrevLocalDateTime = localDateTime;
        this.mAddress = DetailsData.of(mediaItem).getAddress();
        this.mPoi = DetailsData.of(mediaItem).getPoi();
        this.mLatitude = mediaItem.getLatitude();
        this.mLongitude = mediaItem.getLongitude();
        String[] displayNameAndExtensionFromPath = getDisplayNameAndExtensionFromPath(mediaItem);
        this.mOriginTitle = displayNameAndExtensionFromPath[0];
        this.mOriginExtension = displayNameAndExtensionFromPath[1];
        Optional.ofNullable(DetailsDataRefiner.getCapturedUrlText(mediaItem)).ifPresent(new d(2, this));
    }

    private String[] getDisplayNameAndExtensionFromPath(MediaItem mediaItem) {
        String nameFromPath = FileUtils.getNameFromPath(mediaItem.getDataPath());
        if (TextUtils.isEmpty(nameFromPath)) {
            return new String[]{"", ""};
        }
        return FileUtils.splitNameAndExtension(nameFromPath);
    }

    private boolean isHeifMp4Editable(MediaItem mediaItem) {
        if (mediaItem.isHeif() || mediaItem.isVideo()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(SpannableString spannableString) {
        this.mCapturedUrl = spannableString.toString();
    }

    public void deleteCapturedUrl() {
        if (!TextUtils.isEmpty(this.mCapturedUrl)) {
            this.mCapturedUrl = null;
            this.mIsCapturedUrlDeleted = true;
        }
    }

    public void deleteLocation() {
        this.mLatitude = MapUtil.INVALID_LOCATION;
        this.mLongitude = MapUtil.INVALID_LOCATION;
        this.mAddress = null;
        this.mPoi = null;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public String getCapturedUrl() {
        return this.mCapturedUrl;
    }

    public long getDateTime() {
        return this.mDateTime;
    }

    public long getLocalDateTime() {
        return this.mLocalDateTime;
    }

    public double[] getLocation() {
        return new double[]{this.mLatitude, this.mLongitude};
    }

    public String getPoi() {
        return this.mPoi;
    }

    public String getTitle() {
        return this.mOriginTitle;
    }

    public String getTitleExtension() {
        return this.mOriginExtension;
    }

    public boolean hasCapturedUrlDeleted() {
        return this.mIsCapturedUrlDeleted;
    }

    public boolean hasDateChanged(MediaItem mediaItem) {
        if (this.mLocalDateTime != -1) {
            if (mediaItem.getDateLocal() <= 0) {
                if (this.mLocalDateTime != this.mPrevLocalDateTime) {
                    return true;
                }
                return false;
            } else if (this.mLocalDateTime != mediaItem.getDateLocal()) {
                return true;
            } else {
                return false;
            }
        } else if (this.mDateTime != mediaItem.getDateTaken()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasLocationChanged(MediaItem mediaItem) {
        if (this.mLatitude == mediaItem.getLatitude() && this.mLongitude == mediaItem.getLongitude()) {
            return false;
        }
        return true;
    }

    public boolean hasTitleChanged(String str) {
        return !TextUtils.equals(str, this.mOriginTitle);
    }

    public boolean hasValidLocalDateTime() {
        return TimeUtil.isValidLocalTime(this.mLocalDateTime);
    }

    public boolean isDateTimeEditable(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isLocal() || mediaItem.isDrm() || mediaItem.isStories()) {
            return false;
        }
        if (mediaItem.isJpeg() || mediaItem.isPng() || isHeifMp4Editable(mediaItem)) {
            return true;
        }
        return false;
    }

    public boolean isLocationEditable(MediaItem mediaItem) {
        boolean z;
        boolean z3;
        if (mediaItem == null || mediaItem.isDrm() || ((!mediaItem.isJpeg() && !mediaItem.isPng() && !isHeifMp4Editable(mediaItem)) || mediaItem.isCloudOnly())) {
            z = false;
        } else {
            z = true;
        }
        if (!Features.isEnabled(Features.IS_CHINESE_DEVICE) || PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z || !z3) {
            return false;
        }
        return true;
    }

    public boolean isTitleEditable(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isCloudOnly() || mediaItem.isBurstShot() || mediaItem.isStories()) {
            return false;
        }
        return true;
    }

    public void setDateTime(long j2) {
        this.mDateTime = j2;
    }

    public void setLocalDateTime(long j2) {
        this.mLocalDateTime = j2;
    }

    public void setLocation(double d, double d2, String str) {
        this.mLatitude = d;
        this.mLongitude = d2;
        this.mAddress = str;
        this.mPoi = null;
    }
}
