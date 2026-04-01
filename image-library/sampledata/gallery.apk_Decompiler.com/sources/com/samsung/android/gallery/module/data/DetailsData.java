package com.samsung.android.gallery.module.data;

import A.a;
import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.DualShotState;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.library.sef.SefParser;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsData implements Copyable<DetailsData> {
    private static final ArrayList<MediaItem> EMPTY = new ArrayList<>();
    private static final String TAG = "DetailsData";
    static final Function<String, Object> constructor = new j(2);
    private String address;
    private String addressFull;
    public String addressInDB;
    public String aiCache;
    public byte[] aiCacheStream;
    public String analyzedSolutions;
    public boolean autoSaveByColorCorrect;
    public boolean autoSaveByRemaster;
    public String capturedApp;
    public String capturedPath;
    public String capturedUrl;
    private Pair<String, Boolean> clippedData;
    public String countryInDB;
    private ArrayList<MediaItem> creatures;
    public DualShotState dualShotState;
    private ArrayList<MediaItem> dynamic;
    public boolean hasC2pa;
    public boolean hasOcr;
    private final HashMap<String, byte[]> mSefDataMap = new HashMap<>();
    MediaCache mediaCache;
    public ArrayList<MediaPlayback> motionPhotoPlaybacks;
    public MotionPhotoViewMode motionPhotoViewMode;
    public int parentAlbumId;
    private ArrayList<MediaItem> people;
    private ArrayList<MediaItem> pets;
    private String poi;
    public String pppOriginal;
    private ArrayList<MediaItem> related;
    private String relatedTitle;
    public String sceneTags;
    public SefParser sefParser;
    private ArrayList<MediaItem> story;
    private ArrayList<MediaItem> superSlow;
    private ArrayList<MediaItem> userTags;

    public static SefParser computeSefParserIfAbsent(FileItemInterface fileItemInterface) {
        DetailsData of2 = of(fileItemInterface);
        if (of2.sefParser == null) {
            long currentTimeMillis = System.currentTimeMillis();
            of2.sefParser = new SefParser(fileItemInterface.getPath()).unpack();
            String str = TAG;
            a.A(new Object[]{Long.valueOf(fileItemInterface.getFileId()), Long.valueOf(currentTimeMillis)}, new StringBuilder("computeSefParserIfAbsent"), str);
        }
        return of2.sefParser;
    }

    public static void copy(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        String str = TAG;
        fileItemInterface2.setTag(str, fileItemInterface.getTag(str));
    }

    public static MediaCache getMediaCache(FileItemInterface fileItemInterface) {
        DetailsData of2 = of(fileItemInterface);
        if (of2.mediaCache == null) {
            if (of2.aiCacheStream == null) {
                of2.aiCacheStream = StringCompat.parseHex(of2.aiCache);
            }
            of2.mediaCache = new MediaCache(fileItemInterface.getFileId()).unpack(of2.aiCacheStream).setHash(fileItemInterface.getFileHashCode());
        }
        return of2.mediaCache;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$static$0(String str) {
        return new DetailsData();
    }

    public static DetailsData of(FileItemInterface fileItemInterface) {
        return (DetailsData) fileItemInterface.tags().computeIfAbsent(TAG, constructor);
    }

    public static void parseSefDataIfAbsent(FileItemInterface fileItemInterface) {
        DetailsData of2 = of(fileItemInterface);
        if (of2.mSefDataMap.isEmpty()) {
            long currentTimeMillis = System.currentTimeMillis();
            SefParser unpack = new SefParser(fileItemInterface.getPath()).unpack();
            HashMap<String, byte[]> hashMap = of2.mSefDataMap;
            String str = SefInfo.SCREEN_CAPTURE_INFO.keyName;
            hashMap.put(str, unpack.getData(str));
            HashMap<String, byte[]> hashMap2 = of2.mSefDataMap;
            String str2 = SefInfo.CAMERA_CAPTURE_MODE_INFO.keyName;
            hashMap2.put(str2, unpack.getData(str2));
            HashMap<String, byte[]> hashMap3 = of2.mSefDataMap;
            String str3 = SefInfo.VIRTUAL_APERTURE_EXPOSURE_INFO.keyName;
            hashMap3.put(str3, unpack.getData(str3));
            HashMap<String, byte[]> hashMap4 = of2.mSefDataMap;
            String str4 = SefInfo.PRO_WHITE_BALANCE_INFO.keyName;
            hashMap4.put(str4, unpack.getData(str4));
            String str5 = TAG;
            a.A(new Object[]{Long.valueOf(fileItemInterface.getFileId()), Long.valueOf(currentTimeMillis)}, new StringBuilder("computeSefParserIfAbsent"), str5);
        }
    }

    public String getAddress() {
        return this.address;
    }

    public String getAddressFull() {
        return this.addressFull;
    }

    public Pair<String, Boolean> getClippedData() {
        return this.clippedData;
    }

    public ArrayList<MediaItem> getCreatures() {
        ArrayList<MediaItem> arrayList = this.creatures;
        if (arrayList == null) {
            return EMPTY;
        }
        return arrayList;
    }

    public ArrayList<MediaItem> getDynamic() {
        ArrayList<MediaItem> arrayList = this.dynamic;
        if (arrayList == null) {
            return EMPTY;
        }
        return arrayList;
    }

    public ArrayList<MediaItem> getPeople() {
        ArrayList<MediaItem> arrayList = this.people;
        if (arrayList == null) {
            return EMPTY;
        }
        return arrayList;
    }

    public ArrayList<MediaItem> getPets() {
        ArrayList<MediaItem> arrayList = this.pets;
        if (arrayList == null) {
            return EMPTY;
        }
        return arrayList;
    }

    public String getPoi() {
        return this.poi;
    }

    public ArrayList<MediaItem> getRelated() {
        ArrayList<MediaItem> arrayList = this.related;
        if (arrayList == null) {
            return EMPTY;
        }
        return arrayList;
    }

    public String getRelatedTitle() {
        return this.relatedTitle;
    }

    public byte[] getSefParserData(String str) {
        if (!this.mSefDataMap.isEmpty()) {
            return this.mSefDataMap.get(str);
        }
        return null;
    }

    public ArrayList<MediaItem> getStory() {
        ArrayList<MediaItem> arrayList = this.story;
        if (arrayList == null) {
            return EMPTY;
        }
        return arrayList;
    }

    public ArrayList<MediaItem> getSuperSlow() {
        ArrayList<MediaItem> arrayList = this.superSlow;
        if (arrayList == null) {
            return EMPTY;
        }
        return arrayList;
    }

    public ArrayList<MediaItem> getUserTags() {
        ArrayList<MediaItem> arrayList = this.userTags;
        if (arrayList == null) {
            return EMPTY;
        }
        return arrayList;
    }

    public void setAddress(String str, String str2) {
        this.address = str;
        this.addressFull = str2;
    }

    public void setClippedData(Pair<String, Boolean> pair) {
        this.clippedData = pair;
    }

    public void setCreatures(ArrayList<MediaItem> arrayList) {
        this.creatures = arrayList;
    }

    public void setDynamic(ArrayList<MediaItem> arrayList) {
        this.dynamic = arrayList;
    }

    public void setPeople(ArrayList<MediaItem> arrayList) {
        this.people = arrayList;
    }

    public void setPets(ArrayList<MediaItem> arrayList) {
        this.pets = arrayList;
    }

    public void setPoi(String str) {
        this.poi = str;
    }

    public void setRelated(ArrayList<MediaItem> arrayList) {
        this.related = arrayList;
    }

    public void setRelatedTitle(String str) {
        this.relatedTitle = str;
    }

    public void setStory(ArrayList<MediaItem> arrayList) {
        this.story = arrayList;
    }

    public void setSuperSlow(ArrayList<MediaItem> arrayList) {
        this.superSlow = arrayList;
    }

    public void setUserTags(ArrayList<MediaItem> arrayList) {
        this.userTags = arrayList;
    }

    public DetailsData copyOf() {
        try {
            return (DetailsData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) TAG, "clone failed", (Throwable) e);
            return this;
        }
    }
}
