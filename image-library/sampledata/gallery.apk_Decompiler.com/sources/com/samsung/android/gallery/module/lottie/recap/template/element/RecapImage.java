package com.samsung.android.gallery.module.lottie.recap.template.element;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapImage extends RecapDynamicElement<RecapImage> {
    public float bright;
    public int delayForLastSceneImageFree = -1;
    public int fileHeight;
    public int fileWidth;
    public float hue = -1.0f;
    public boolean isVertical;
    public String locationName;
    public String mAvoidColor;
    public Bitmap mTargetBitmap;
    public byte[] mTargetJpeg;
    public TargetCandidate mTargetType = TargetCandidate.COMMON;
    public MediaItem mUsedItem;
    int month;
    public String peopleName;
    public boolean placeClosing;
    int priorityWeight = 0;
    public boolean properNoPeople;
    public boolean properPeople;
    public boolean properScene;
    public float saturation;
    public int sceneIndex;
    public String sceneName;
    public boolean useViewSizeHeadGuard;
    public int viewHeight;
    public int viewWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TargetCandidate {
        PEOPLE,
        SINGLE_PEOPLE,
        TOP_3_FACE,
        WITH_TOP_3_FACE,
        NEW_PLACE,
        BEST_PLACE,
        LOCATION,
        MONTHLY_BEST,
        KEY_MOMENTS,
        YEAR_TOP_ACTIVE_DAYS,
        YEAR_MOST_ACTIVE_DAY,
        COMMON,
        REUSE_IMAGE
    }

    public RecapImage(String str) {
        super(str);
    }

    private void setMonth(int i2) {
        this.month = i2;
    }

    public void calculateHsv() {
        if (this.hue == -1.0f) {
            float[] hsvColor = BitmapUtils.getHsvColor(this.mTargetBitmap);
            this.hue = hsvColor[0];
            this.saturation = hsvColor[1];
            this.bright = hsvColor[2];
        }
    }

    public String colorIntToHexString(int i2) {
        return C0212a.n("#", String.format("%02X", new Object[]{Integer.valueOf(Color.red(i2))}), String.format("%02X", new Object[]{Integer.valueOf(Color.green(i2))}), String.format("%02X", new Object[]{Integer.valueOf(Color.blue(i2))}));
    }

    public String createDynamicColor(float f, float f5) {
        return colorIntToHexString(Color.HSVToColor(new float[]{this.hue, f, f5}));
    }

    public RecapImage dummy() {
        this.priorityWeight = 999999;
        return this;
    }

    public int getMonth() {
        return this.month;
    }

    public int getPriority() {
        int i2;
        if (this.properPeople) {
            i2 = 1;
        } else {
            i2 = 10000;
        }
        if (this.properNoPeople) {
            i2 = 2;
        }
        if (this.properScene) {
            i2 = 3;
        }
        return i2 + this.priorityWeight;
    }

    public RecapImage hidden() {
        this.priorityWeight = 1000;
        return this;
    }

    public RecapImage highPriority() {
        this.priorityWeight = -100;
        return this;
    }

    public boolean isKeyMoments() {
        if (this.mTargetType == TargetCandidate.KEY_MOMENTS) {
            return true;
        }
        return false;
    }

    public boolean isMonthlyImage() {
        if (this.mTargetType == TargetCandidate.MONTHLY_BEST) {
            return true;
        }
        return false;
    }

    public RecapImage keyMoments() {
        this.mTargetType = TargetCandidate.KEY_MOMENTS;
        return this;
    }

    public void loadBitmap() {
        byte[] bArr;
        if (this.mTargetBitmap != null || (bArr = this.mTargetJpeg) == null) {
            throw new IllegalStateException("fail loadBitmap");
        }
        this.mTargetBitmap = ImageDecoder.decodeByteArray(bArr, 0, bArr.length, new BitmapOptions());
        this.mTargetJpeg = null;
    }

    public RecapImage location() {
        this.mTargetType = TargetCandidate.LOCATION;
        return this;
    }

    public RecapImage lowPriority() {
        this.priorityWeight = 100;
        return this;
    }

    public RecapImage monthly(int i2) {
        this.mTargetType = TargetCandidate.MONTHLY_BEST;
        setMonth(i2);
        return this;
    }

    public boolean needNonPeopleImage() {
        return this.properNoPeople;
    }

    public boolean needPeopleImage() {
        return this.properPeople;
    }

    public boolean needToUseLocationImages() {
        if (this.mTargetType == TargetCandidate.LOCATION || this.properScene || this.properNoPeople) {
            return true;
        }
        return false;
    }

    public boolean needToUsePeoplesImage() {
        TargetCandidate targetCandidate = this.mTargetType;
        if (targetCandidate == TargetCandidate.PEOPLE || targetCandidate == TargetCandidate.SINGLE_PEOPLE || this.properPeople) {
            return true;
        }
        return false;
    }

    public RecapImage notBlackRight() {
        this.mAvoidColor = "blackRight";
        this.priorityWeight -= 200;
        return this;
    }

    public RecapImage notWhiteBottom() {
        this.mAvoidColor = "whiteBottom";
        this.priorityWeight -= 200;
        return this;
    }

    public RecapImage notWhiteTop() {
        this.mAvoidColor = "whiteTop";
        this.priorityWeight -= 200;
        return this;
    }

    public RecapImage notWhiteVerticalMiddle() {
        this.mAvoidColor = "whiteVerticalMiddle";
        this.priorityWeight -= 200;
        return this;
    }

    public RecapImage people() {
        this.mTargetType = TargetCandidate.PEOPLE;
        return this;
    }

    public RecapImage placeClosing() {
        this.placeClosing = true;
        this.useViewSizeHeadGuard = true;
        return this;
    }

    public RecapImage properPeople() {
        this.properPeople = true;
        return this;
    }

    public RecapImage properScene() {
        this.properScene = true;
        return this;
    }

    public RecapImage properZeroPeople() {
        this.properNoPeople = true;
        return this;
    }

    public RecapImage recycleBitmaps() {
        this.delayForLastSceneImageFree = 0;
        return this;
    }

    public RecapImage reuse() {
        this.mTargetType = TargetCandidate.REUSE_IMAGE;
        this.priorityWeight = 0;
        return this;
    }

    public void setResolution(int i2, int i7) {
        boolean z;
        this.fileWidth = i2;
        this.fileHeight = i7;
        if (i7 >= i2) {
            z = true;
        } else {
            z = false;
        }
        this.isVertical = z;
    }

    public RecapImage singlePeople() {
        this.mTargetType = TargetCandidate.SINGLE_PEOPLE;
        return this;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("RecapImage{");
        sb2.append(this.dataBindingKey);
        sb2.append("',");
        sb2.append(this.mTargetType);
        sb2.append(",file ='");
        MediaItem mediaItem = this.mUsedItem;
        if (mediaItem != null) {
            str = mediaItem.getDisplayName();
        } else {
            str = "null";
        }
        sb2.append(str);
        sb2.append("', ");
        sb2.append(this.fileWidth);
        sb2.append("x");
        sb2.append(this.fileHeight);
        sb2.append(", vert=");
        sb2.append(this.isVertical);
        sb2.append(", hue=");
        sb2.append(this.hue);
        sb2.append(", sat=");
        sb2.append(this.saturation);
        sb2.append(", bri=");
        sb2.append(this.bright);
        sb2.append('}');
        return sb2.toString();
    }

    public RecapImage type(TargetCandidate targetCandidate) {
        this.mTargetType = targetCandidate;
        return this;
    }

    public RecapImage useHeadGuard(int i2, int i7) {
        this.viewWidth = i2 * 3;
        this.viewHeight = i7 * 3;
        return this;
    }

    public RecapImage recycleBitmaps(int i2) {
        this.delayForLastSceneImageFree = i2;
        return this;
    }

    public RecapImage(String str, int i2, int i7) {
        super(str);
        this.viewWidth = i2;
        this.viewHeight = i7;
    }
}
