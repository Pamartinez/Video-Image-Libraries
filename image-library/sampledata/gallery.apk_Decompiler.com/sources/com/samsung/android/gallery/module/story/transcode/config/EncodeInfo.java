package com.samsung.android.gallery.module.story.transcode.config;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.Pair;
import android.widget.ImageView;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.RectUtils;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EncodeInfo {
    public Bitmap backgroundBitmap;
    public String bgmName;
    public RectF cornerRadius;
    public RectF displayPositionRect;
    public int duration;
    public String filterName;
    public Bitmap foregroundBitmap;
    public HashMap<ThumbnailInterface, KenBurnsInfo> kenBurnsMap;
    public Mode mode = Mode.STORY;
    public String outFilePath;
    public int outHeight;
    public int outWidth;
    public ImageView.ScaleType scaleType;
    public HashMap<ThumbnailInterface, Pair<Long, Long>> sectionInfoMap;
    public int storyId;
    public String subTitle;
    public String title;
    public int uniqueKey;

    public static EncodeInfo from(Intent intent, Blackboard blackboard) {
        ImageView.ScaleType scaleType2;
        int[] iArr = {intent.getIntExtra("key-export-width", 0), intent.getIntExtra("key-export-height", 0)};
        String stringExtra = intent.getStringExtra("key-export-scale-type");
        EncodeInfo outSize = new EncodeInfo().setStoryId(intent.getIntExtra("story_id", -1)).setTitle(intent.getStringExtra("key-title")).setSubtitle(intent.getStringExtra("key-subtitle")).setDuration(intent.getIntExtra("key-duration", 0)).setBgmName(intent.getStringExtra("key-bgm")).setFilterName(intent.getStringExtra("key-effect-filter-name")).setKenBurnMap(blackboard.pop("data://user/highlight_encode_ken_burns")).setUniqueKey(intent.getIntExtra("key-unique-key", 0)).setOutFilePath(intent.getStringExtra("output_file_path")).setOutSize(iArr);
        if (stringExtra != null) {
            scaleType2 = ImageView.ScaleType.valueOf(stringExtra);
        } else {
            scaleType2 = null;
        }
        return outSize.setScaleType(scaleType2);
    }

    public int getItemDuration(ThumbnailInterface thumbnailInterface) {
        HashMap<ThumbnailInterface, Pair<Long, Long>> hashMap;
        Pair pair;
        if (!Mode.MOTION_PHOTO.equals(this.mode) || (hashMap = this.sectionInfoMap) == null || (pair = hashMap.get(thumbnailInterface)) == null) {
            return this.mode.getDuration(thumbnailInterface);
        }
        return (int) (((Long) pair.second).longValue() - ((Long) pair.first).longValue());
    }

    public int getItemStartTimeMs(ThumbnailInterface thumbnailInterface) {
        HashMap<ThumbnailInterface, Pair<Long, Long>> hashMap;
        Pair pair;
        if (!Mode.MOTION_PHOTO.equals(this.mode) || (hashMap = this.sectionInfoMap) == null || (pair = hashMap.get(thumbnailInterface)) == null) {
            return this.mode.getStartTime(thumbnailInterface);
        }
        return Math.toIntExact(((Long) pair.first).longValue());
    }

    public EncodeInfo setBackgroundBitmap(Bitmap bitmap) {
        this.backgroundBitmap = bitmap;
        return this;
    }

    public EncodeInfo setBgmName(String str) {
        this.bgmName = str;
        return this;
    }

    public EncodeInfo setCornerRadius(String str) {
        this.cornerRadius = RectUtils.stringToRectF(str);
        return this;
    }

    public EncodeInfo setDisplayPositionRect(String str) {
        this.displayPositionRect = RectUtils.stringToRectF(str);
        return this;
    }

    public EncodeInfo setDuration(int i2) {
        this.duration = i2;
        return this;
    }

    public EncodeInfo setFilterName(String str) {
        this.filterName = str;
        return this;
    }

    public EncodeInfo setForegroundBitmap(Bitmap bitmap) {
        this.foregroundBitmap = bitmap;
        return this;
    }

    public EncodeInfo setKenBurnMap(Object obj) {
        if (obj instanceof HashMap) {
            this.kenBurnsMap = (HashMap) obj;
        }
        return this;
    }

    public EncodeInfo setMode(Mode mode2) {
        this.mode = mode2;
        return this;
    }

    public EncodeInfo setOutFilePath(String str) {
        this.outFilePath = str;
        return this;
    }

    public EncodeInfo setOutSize(int[] iArr) {
        this.outWidth = iArr[0];
        this.outHeight = iArr[1];
        return this;
    }

    public EncodeInfo setScaleType(ImageView.ScaleType scaleType2) {
        this.scaleType = scaleType2;
        return this;
    }

    public EncodeInfo setSectionInfoMap(HashMap<ThumbnailInterface, Pair<Long, Long>> hashMap) {
        this.sectionInfoMap = hashMap;
        return this;
    }

    public EncodeInfo setStoryId(int i2) {
        this.storyId = i2;
        return this;
    }

    public EncodeInfo setSubtitle(String str) {
        this.subTitle = str;
        return this;
    }

    public EncodeInfo setTitle(String str) {
        this.title = str;
        return this;
    }

    public EncodeInfo setUniqueKey(int i2) {
        this.uniqueKey = i2;
        return this;
    }
}
