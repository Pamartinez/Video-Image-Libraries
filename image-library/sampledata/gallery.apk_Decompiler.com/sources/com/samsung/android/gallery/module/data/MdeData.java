package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder;
import com.samsung.android.gallery.support.utils.Copyable;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import f4.a;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MdeData implements Copyable<MdeData> {
    private static final String TAG = "MdeData";
    static final Function<String, Object> constructor = new j(5);
    public long albumExpiry;
    public int cachedGroupMemberCount;
    public long createdTime;
    public String creator;
    public String creatorName = "";
    public String downloadUrl;
    public String downloadVideoPath;
    public Uri downloadVideoUri;
    public long expiredTime;
    public long expiryDate;
    public String featureId;
    public String groupId;
    public int groupMemberCount;
    public String groupName;
    public String groupType;
    public String hash;
    public boolean isUserCoverItem = true;
    public boolean isWideImage;
    public String itemHash;
    public String itemId;
    public String itemStatus;
    public String lastModifier;
    public String lastModifierName;
    MdeMetadata mdeMetadata;
    public String memo;
    public long message;
    public long modifiedTime;
    public long myUsage;
    public String originalContentPath;
    public int originalHeight;
    public int originalWidth;
    public boolean ownedByMe;
    public String owner;
    public String pathInHiddenFolder;
    public long requestedTime;
    public String requesterId;
    public String requesterName;
    public long requesterUpdatedTime;
    public Boolean shareVideoDownloadVerified;
    public String spaceCoverMediaId;
    public String spaceCoverRectRatio;
    public String spaceId;
    public String spaceName;
    public String storyBgmName;
    public String storyFilterName;
    public String storyThemeName;
    public String streamingUrl;
    public String thumbnailGroupPath;
    public String thumbnailPath;
    public String thumbnailRequesterPath;
    public int unreadCount;
    public Uri uriInHiddenFolder;
    public String webLinkUrl;
    public long webLinkUrlExpiry;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmptyHolder {
        static final MdeData EMPTY = new MdeData();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FeatureHolder {
        static final boolean SUPPORT_CREATOR = Features.isEnabled(Features.SUPPORT_CREATOR);
        static final boolean SUPPORT_FAMILY_SHARED_ITEM_STATUS = Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ITEM_STATUS);
        static final boolean SUPPORT_FAMILY_SHARED_TRASH = Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH);
        static final boolean SUPPORT_HIDDEN_PATH = Features.isEnabled(Features.SUPPORT_HIDDEN_PATH_IN_SEMS_SHARE_DB);
        static final boolean SUPPORT_SHARED_DOWNLOAD_FILE = Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MdeMetadata {
        ArrayList<RectF> cropRectRatioList;
        Long dateTaken;
        Integer fileDuration;
        Long fileSize;
        Integer groupType;
        Integer height;
        Boolean is360Video;
        Boolean isWideImage;
        Double latitude;
        Double longitude;
        Integer orientation;
        Integer orientationTag;
        String path;
        Integer recordingMode;
        Integer sefFileSubType;
        Integer sefFileType;
        RectF smartCropRectRatio;
        String spaceCoverMediaId;
        String spaceCoverRectRatio;
        String storyBgmName;
        String storyFilterName;
        String storyThemeName;
        String totalSmartCropDeviceRatio;
        String totalSmartCropRatio;
        Integer videoColorFormat;
        Integer width;

        public MdeMetadata parse(String str) {
            String[] split = str.split(";");
            for (int i2 = 1; i2 < split.length; i2 += 2) {
                setValue(split[i2 - 1], split[i2]);
            }
            return this;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setValue(java.lang.String r5, java.lang.String r6) {
            /*
                r4 = this;
                r5.getClass()
                int r0 = r5.hashCode()
                r1 = -1
                r2 = 1
                r3 = 0
                switch(r0) {
                    case -1992012396: goto L_0x0150;
                    case -1881891184: goto L_0x0144;
                    case -1553252829: goto L_0x0138;
                    case -1439978388: goto L_0x012c;
                    case -1439500848: goto L_0x0120;
                    case -1221029593: goto L_0x0114;
                    case -1192017132: goto L_0x0108;
                    case -997358447: goto L_0x00fb;
                    case -917472085: goto L_0x00ed;
                    case -801678346: goto L_0x00de;
                    case -639779342: goto L_0x00cf;
                    case -267344506: goto L_0x00c1;
                    case -230028839: goto L_0x00b3;
                    case -167065741: goto L_0x00a5;
                    case -163871951: goto L_0x0097;
                    case 17192948: goto L_0x0088;
                    case 113126854: goto L_0x007a;
                    case 137365935: goto L_0x006d;
                    case 669554750: goto L_0x0060;
                    case 752481687: goto L_0x0052;
                    case 1118893824: goto L_0x0044;
                    case 1150104272: goto L_0x0037;
                    case 1282509050: goto L_0x002a;
                    case 1863354799: goto L_0x001d;
                    case 2135427176: goto L_0x0010;
                    default: goto L_0x000d;
                }
            L_0x000d:
                r5 = r1
                goto L_0x015b
            L_0x0010:
                java.lang.String r0 = "coverMediaId"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0019
                goto L_0x000d
            L_0x0019:
                r5 = 24
                goto L_0x015b
            L_0x001d:
                java.lang.String r0 = "original_size"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0026
                goto L_0x000d
            L_0x0026:
                r5 = 23
                goto L_0x015b
            L_0x002a:
                java.lang.String r0 = "group_type"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0033
                goto L_0x000d
            L_0x0033:
                r5 = 22
                goto L_0x015b
            L_0x0037:
                java.lang.String r0 = "instantThumbnailPath"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0040
                goto L_0x000d
            L_0x0040:
                r5 = 21
                goto L_0x015b
            L_0x0044:
                java.lang.String r0 = "totalSmartCropDeviceRatio"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x004e
                goto L_0x000d
            L_0x004e:
                r5 = 20
                goto L_0x015b
            L_0x0052:
                java.lang.String r0 = "video_color_format"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x005c
                goto L_0x000d
            L_0x005c:
                r5 = 19
                goto L_0x015b
            L_0x0060:
                java.lang.String r0 = "isWideImage"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0069
                goto L_0x000d
            L_0x0069:
                r5 = 18
                goto L_0x015b
            L_0x006d:
                java.lang.String r0 = "longitude"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0076
                goto L_0x000d
            L_0x0076:
                r5 = 17
                goto L_0x015b
            L_0x007a:
                java.lang.String r0 = "width"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0084
                goto L_0x000d
            L_0x0084:
                r5 = 16
                goto L_0x015b
            L_0x0088:
                java.lang.String r0 = "themeName"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0093
                goto L_0x000d
            L_0x0093:
                r5 = 15
                goto L_0x015b
            L_0x0097:
                java.lang.String r0 = "recording_mode"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x00a1
                goto L_0x000d
            L_0x00a1:
                r5 = 14
                goto L_0x015b
            L_0x00a5:
                java.lang.String r0 = "bgmName"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x00af
                goto L_0x000d
            L_0x00af:
                r5 = 13
                goto L_0x015b
            L_0x00b3:
                java.lang.String r0 = "datetaken"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x00bd
                goto L_0x000d
            L_0x00bd:
                r5 = 12
                goto L_0x015b
            L_0x00c1:
                java.lang.String r0 = "cropRectRatioListString"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x00cb
                goto L_0x000d
            L_0x00cb:
                r5 = 11
                goto L_0x015b
            L_0x00cf:
                java.lang.String r0 = "sef_file_type"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x00da
                goto L_0x000d
            L_0x00da:
                r5 = 10
                goto L_0x015b
            L_0x00de:
                java.lang.String r0 = "totalSmartCropRatio"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x00e9
                goto L_0x000d
            L_0x00e9:
                r5 = 9
                goto L_0x015b
            L_0x00ed:
                java.lang.String r0 = "orientation_tag"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x00f7
                goto L_0x000d
            L_0x00f7:
                r5 = 8
                goto L_0x015b
            L_0x00fb:
                java.lang.String r0 = "sef_file_sub_type"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0106
                goto L_0x000d
            L_0x0106:
                r5 = 7
                goto L_0x015b
            L_0x0108:
                java.lang.String r0 = "is_360_video"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0112
                goto L_0x000d
            L_0x0112:
                r5 = 6
                goto L_0x015b
            L_0x0114:
                java.lang.String r0 = "height"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x011e
                goto L_0x000d
            L_0x011e:
                r5 = 5
                goto L_0x015b
            L_0x0120:
                java.lang.String r0 = "orientation"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x012a
                goto L_0x000d
            L_0x012a:
                r5 = 4
                goto L_0x015b
            L_0x012c:
                java.lang.String r0 = "latitude"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0136
                goto L_0x000d
            L_0x0136:
                r5 = 3
                goto L_0x015b
            L_0x0138:
                java.lang.String r0 = "filterName"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x0142
                goto L_0x000d
            L_0x0142:
                r5 = 2
                goto L_0x015b
            L_0x0144:
                java.lang.String r0 = "coverRectRatio"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x014e
                goto L_0x000d
            L_0x014e:
                r5 = r2
                goto L_0x015b
            L_0x0150:
                java.lang.String r0 = "duration"
                boolean r5 = r5.equals(r0)
                if (r5 != 0) goto L_0x015a
                goto L_0x000d
            L_0x015a:
                r5 = r3
            L_0x015b:
                switch(r5) {
                    case 0: goto L_0x0255;
                    case 1: goto L_0x0252;
                    case 2: goto L_0x024f;
                    case 3: goto L_0x023e;
                    case 4: goto L_0x0233;
                    case 5: goto L_0x0228;
                    case 6: goto L_0x021d;
                    case 7: goto L_0x0212;
                    case 8: goto L_0x0207;
                    case 9: goto L_0x0204;
                    case 10: goto L_0x01f9;
                    case 11: goto L_0x01cf;
                    case 12: goto L_0x01c4;
                    case 13: goto L_0x01c1;
                    case 14: goto L_0x01b6;
                    case 15: goto L_0x01b3;
                    case 16: goto L_0x01a8;
                    case 17: goto L_0x0197;
                    case 18: goto L_0x018c;
                    case 19: goto L_0x0181;
                    case 20: goto L_0x017e;
                    case 21: goto L_0x017b;
                    case 22: goto L_0x0170;
                    case 23: goto L_0x0163;
                    case 24: goto L_0x0160;
                    default: goto L_0x015e;
                }
            L_0x015e:
                goto L_0x024e
            L_0x0160:
                r4.spaceCoverMediaId = r6
                return
            L_0x0163:
                r0 = 0
                long r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toLong(r6, r0)
                java.lang.Long r5 = java.lang.Long.valueOf(r5)
                r4.fileSize = r5
                return
            L_0x0170:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6, r3)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.groupType = r5
                return
            L_0x017b:
                r4.path = r6
                return
            L_0x017e:
                r4.totalSmartCropDeviceRatio = r6
                return
            L_0x0181:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6, r3)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.videoColorFormat = r5
                return
            L_0x018c:
                boolean r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toBoolean(r6)
                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                r4.isWideImage = r5
                return
            L_0x0197:
                double r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toDouble(r6)
                boolean r0 = com.samsung.android.gallery.support.utils.UnsafeCast.isInvalid((double) r5)
                if (r0 != 0) goto L_0x024e
                java.lang.Double r5 = java.lang.Double.valueOf(r5)
                r4.longitude = r5
                return
            L_0x01a8:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6, r3)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.width = r5
                return
            L_0x01b3:
                r4.storyThemeName = r6
                return
            L_0x01b6:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.recordingMode = r5
                return
            L_0x01c1:
                r4.storyBgmName = r6
                return
            L_0x01c4:
                long r5 = java.lang.Long.parseLong(r6)
                java.lang.Long r5 = java.lang.Long.valueOf(r5)
                r4.dateTaken = r5
                return
            L_0x01cf:
                boolean r5 = r6.isEmpty()
                if (r5 != 0) goto L_0x024e
                java.util.ArrayList r5 = com.samsung.android.gallery.support.utils.RectUtils.listOf(r6)
                r4.cropRectRatioList = r5
                if (r5 == 0) goto L_0x024e
                boolean r5 = r5.isEmpty()
                if (r5 != 0) goto L_0x024e
                java.util.ArrayList<android.graphics.RectF> r5 = r4.cropRectRatioList
                int r5 = r5.size()
                int r5 = r5 - r2
                int r5 = java.lang.Math.min(r5, r2)
                java.util.ArrayList<android.graphics.RectF> r6 = r4.cropRectRatioList
                java.lang.Object r5 = r6.get(r5)
                android.graphics.RectF r5 = (android.graphics.RectF) r5
                r4.smartCropRectRatio = r5
                return
            L_0x01f9:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6, r1)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.sefFileType = r5
                return
            L_0x0204:
                r4.totalSmartCropRatio = r6
                return
            L_0x0207:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.orientationTag = r5
                return
            L_0x0212:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6, r1)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.sefFileSubType = r5
                return
            L_0x021d:
                boolean r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toBoolean(r6)
                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                r4.is360Video = r5
                return
            L_0x0228:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6, r3)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.height = r5
                return
            L_0x0233:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6, r3)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.orientation = r5
                return
            L_0x023e:
                double r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toDouble(r6)
                boolean r0 = com.samsung.android.gallery.support.utils.UnsafeCast.isInvalid((double) r5)
                if (r0 != 0) goto L_0x024e
                java.lang.Double r5 = java.lang.Double.valueOf(r5)
                r4.latitude = r5
            L_0x024e:
                return
            L_0x024f:
                r4.storyFilterName = r6
                return
            L_0x0252:
                r4.spaceCoverRectRatio = r6
                return
            L_0x0255:
                int r5 = com.samsung.android.gallery.support.utils.UnsafeCast.toInt(r6, r3)
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r4.fileDuration = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.data.MdeData.MdeMetadata.setValue(java.lang.String, java.lang.String):void");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$parse$0(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$parse$1(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$2(Integer num) {
        this.originalWidth = num.intValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$3(Integer num) {
        this.originalHeight = num.intValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$4(Boolean bool) {
        this.isWideImage = bool.booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$5(String str) {
        this.spaceCoverMediaId = str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$6(String str) {
        this.spaceCoverRectRatio = str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$7(String str) {
        this.storyThemeName = str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$8(String str) {
        this.storyFilterName = str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parse$9(String str) {
        this.storyBgmName = str;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$parseSpace$10(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parseSpace$11(String str) {
        if (this.mdeMetadata == null) {
            this.mdeMetadata = new MdeMetadata();
        }
        this.mdeMetadata.parse(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$static$12(String str) {
        return new MdeData();
    }

    public static MdeData of(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return EmptyHolder.EMPTY;
        }
        return (MdeData) fileItemInterface.tags().computeIfAbsent(TAG, constructor);
    }

    public MdeData parse(SharingCursorHolder sharingCursorHolder) {
        int i2;
        Uri uri;
        Uri uri2;
        this.spaceId = sharingCursorHolder.getString(sharingCursorHolder.indexSpaceId, (String) null);
        this.itemId = sharingCursorHolder.getString(sharingCursorHolder.indexItemId, (String) null);
        this.memo = sharingCursorHolder.getString(sharingCursorHolder.indexMemo, (String) null);
        this.owner = sharingCursorHolder.getString(sharingCursorHolder.indexOwner, (String) null);
        this.downloadUrl = sharingCursorHolder.getString(sharingCursorHolder.indexDownloadUrl, (String) null);
        this.streamingUrl = sharingCursorHolder.getString(sharingCursorHolder.indexStreamingUrl, (String) null);
        this.thumbnailPath = sharingCursorHolder.getString(sharingCursorHolder.indexThumbnailLocalPath, (String) null);
        this.originalContentPath = sharingCursorHolder.getString(sharingCursorHolder.indexOriginalContentPath, (String) null);
        boolean z = false;
        if (sharingCursorHolder.getInt(sharingCursorHolder.indexIsOwnedByMe, 0) > 0) {
            z = true;
        }
        this.ownedByMe = z;
        if (FeatureHolder.SUPPORT_SHARED_DOWNLOAD_FILE) {
            this.itemHash = sharingCursorHolder.getString(sharingCursorHolder.indexItemHash, (String) null);
            String string = sharingCursorHolder.getString(sharingCursorHolder.indexOriginalContentUri, (String) null);
            if (string != null) {
                uri2 = Uri.parse(string);
            } else {
                uri2 = null;
            }
            this.downloadVideoUri = uri2;
            this.downloadVideoPath = this.originalContentPath;
        }
        if (FeatureHolder.SUPPORT_HIDDEN_PATH) {
            String string2 = sharingCursorHolder.getString(sharingCursorHolder.indexOriginalInHiddenFolderPath, (String) null);
            String string3 = sharingCursorHolder.getString(sharingCursorHolder.indexOriginalInHiddenFolderContentUri, (String) null);
            if (TextUtils.isEmpty(string2)) {
                string2 = null;
            }
            this.pathInHiddenFolder = string2;
            if (TextUtils.isEmpty(string3)) {
                uri = null;
            } else {
                uri = Uri.parse(string3);
            }
            this.uriInHiddenFolder = uri;
        }
        if (FeatureHolder.SUPPORT_FAMILY_SHARED_TRASH && (i2 = sharingCursorHolder.indexExpiryDate) >= 0) {
            this.expiryDate = sharingCursorHolder.getLong(i2, 0);
        }
        if (FeatureHolder.SUPPORT_FAMILY_SHARED_ITEM_STATUS) {
            this.lastModifier = sharingCursorHolder.getString(sharingCursorHolder.indexLastModifier, (String) null);
            this.itemStatus = sharingCursorHolder.getString(sharingCursorHolder.indexItemStatus, (String) null);
            this.createdTime = sharingCursorHolder.getLong(sharingCursorHolder.indexCreateTime, 0);
            this.modifiedTime = sharingCursorHolder.getLong(sharingCursorHolder.indexModifiedTime, 0);
        }
        if (FeatureHolder.SUPPORT_CREATOR) {
            this.creator = sharingCursorHolder.getString(sharingCursorHolder.indexCreator, (String) null);
        }
        ArrayList arrayList = new ArrayList();
        Optional.ofNullable(sharingCursorHolder.getString(sharingCursorHolder.indexMetaData, (String) null)).filter(new e(1)).ifPresent(new a(arrayList, 17));
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
            Optional.ofNullable(sharingCursorHolder.getString(sharingCursorHolder.indexInstantMetaData, (String) null)).filter(new e(2)).ifPresent(new a(arrayList, 17));
        }
        if (!arrayList.isEmpty()) {
            MdeMetadata mdeMetadata2 = new MdeMetadata();
            this.mdeMetadata = mdeMetadata2;
            arrayList.forEach(new f(0, mdeMetadata2));
        }
        MdeMetadata mdeMetadata3 = this.mdeMetadata;
        if (mdeMetadata3 != null) {
            Optional.ofNullable(mdeMetadata3.width).ifPresent(new d(this, 6));
            Optional.ofNullable(this.mdeMetadata.height).ifPresent(new d(this, 7));
            Optional.ofNullable(this.mdeMetadata.isWideImage).ifPresent(new d(this, 8));
            Optional.ofNullable(this.mdeMetadata.spaceCoverMediaId).ifPresent(new d(this, 0));
            Optional.ofNullable(this.mdeMetadata.spaceCoverRectRatio).ifPresent(new d(this, 1));
            Optional.ofNullable(this.mdeMetadata.storyThemeName).ifPresent(new d(this, 3));
            Optional.ofNullable(this.mdeMetadata.storyFilterName).ifPresent(new d(this, 4));
            Optional.ofNullable(this.mdeMetadata.storyBgmName).ifPresent(new d(this, 5));
        }
        return this;
    }

    public MdeData parseGroup(SharingCursorHolder sharingCursorHolder) {
        this.groupId = sharingCursorHolder.getString(sharingCursorHolder.indexGroupId, (String) null);
        this.groupType = sharingCursorHolder.getString(sharingCursorHolder.indexType, (String) null);
        this.createdTime = sharingCursorHolder.getLong(sharingCursorHolder.indexCreatedTime, 0);
        if (PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
            this.albumExpiry = sharingCursorHolder.getLong(sharingCursorHolder.indexAlbumExpiry, 0);
        }
        return this;
    }

    public MdeData parseGroupMember(SharingCursorHolder sharingCursorHolder) {
        this.owner = sharingCursorHolder.getString(sharingCursorHolder.indexOwnerId, (String) null);
        if (Features.isEnabled(Features.SUPPORT_CREATOR)) {
            this.creator = sharingCursorHolder.getString(sharingCursorHolder.indexCreator, (String) null);
        }
        return this;
    }

    public MdeData parseInvitation(SharingCursorHolder sharingCursorHolder) {
        this.groupId = sharingCursorHolder.getString(sharingCursorHolder.indexGroupId, (String) null);
        this.featureId = sharingCursorHolder.getString(sharingCursorHolder.indexFeatureId, (String) null);
        this.spaceName = sharingCursorHolder.getString(sharingCursorHolder.indexSpaceName, (String) null);
        this.thumbnailGroupPath = sharingCursorHolder.getString(sharingCursorHolder.indexGroupThumbnailLocalPath, (String) null);
        this.hash = sharingCursorHolder.getString(sharingCursorHolder.indexHash, (String) null);
        this.requesterId = sharingCursorHolder.getString(sharingCursorHolder.indexRequesterId, (String) null);
        this.requesterName = sharingCursorHolder.getString(sharingCursorHolder.indexRequesterName, (String) null);
        this.thumbnailRequesterPath = sharingCursorHolder.getString(sharingCursorHolder.indexRequesterThumbnailLocalPath, (String) null);
        this.requesterUpdatedTime = sharingCursorHolder.getLong(sharingCursorHolder.indexRequesterUpdatedTime, 0);
        this.message = sharingCursorHolder.getLong(sharingCursorHolder.indexMessage, 0);
        this.requestedTime = sharingCursorHolder.getLong(sharingCursorHolder.indexRequestedTime, 0);
        this.expiredTime = sharingCursorHolder.getLong(sharingCursorHolder.indexExpiredTime, 0);
        return this;
    }

    public MdeData parseMetadata(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (this.mdeMetadata == null) {
                this.mdeMetadata = new MdeMetadata();
            }
            this.mdeMetadata.parse(str);
        }
        return this;
    }

    public MdeData parseSpace(SharingCursorHolder sharingCursorHolder) {
        boolean z;
        int i2;
        String string;
        this.spaceId = sharingCursorHolder.getString(sharingCursorHolder.indexSpaceId, (String) null);
        this.groupId = sharingCursorHolder.getString(sharingCursorHolder.indexGroupId, (String) null);
        this.unreadCount = sharingCursorHolder.getInt(sharingCursorHolder.indexUnreadCount, 0);
        this.owner = sharingCursorHolder.getString(sharingCursorHolder.indexOwner, (String) null);
        if (sharingCursorHolder.getInt(sharingCursorHolder.indexIsOwnedByMe, 0) > 0) {
            z = true;
        } else {
            z = false;
        }
        this.ownedByMe = z;
        this.thumbnailPath = sharingCursorHolder.getString(sharingCursorHolder.indexThumbnailLocalPath, (String) null);
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            this.myUsage = sharingCursorHolder.getLong(sharingCursorHolder.indexMyUsage, -1);
        }
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
            this.webLinkUrl = sharingCursorHolder.getString(sharingCursorHolder.indexWebLinkUrl, (String) null);
            this.webLinkUrlExpiry = sharingCursorHolder.getLong(sharingCursorHolder.indexWebLinkExpiry, 0);
        }
        int i7 = sharingCursorHolder.indexGroupName;
        if (i7 != -1) {
            this.groupName = sharingCursorHolder.getString(i7, (String) null);
            this.cachedGroupMemberCount = sharingCursorHolder.getInt(sharingCursorHolder.indexMembersCount, 0);
            this.groupType = sharingCursorHolder.getString(sharingCursorHolder.indexType, (String) null);
            this.createdTime = sharingCursorHolder.getLong(sharingCursorHolder.indexCreatedTime, 0);
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY && (i2 = sharingCursorHolder.indexMemo) != -1 && (string = sharingCursorHolder.getString(i2, (String) null)) != null && string.startsWith("story")) {
            try {
                JSONObject jSONObject = new JSONObject(string.substring(5));
                this.storyThemeName = jSONObject.getString("themeName");
                this.storyFilterName = jSONObject.getString("filterName");
                this.storyBgmName = jSONObject.getString("bgmName");
            } catch (Exception unused) {
            }
        }
        Optional.ofNullable(sharingCursorHolder.getString(sharingCursorHolder.indexMetaData, (String) null)).filter(new e(0)).ifPresent(new d(this, 2));
        return this;
    }

    public MdeData copyOf() {
        try {
            return (MdeData) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e((CharSequence) TAG, "clone failed", (Throwable) e);
            return this;
        }
    }
}
