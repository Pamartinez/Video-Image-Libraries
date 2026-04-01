package com.samsung.android.gallery.module.data;

import A.a;
import A4.C0384t;
import android.database.Cursor;
import android.graphics.RectF;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.CustomRelationshipKeySet;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.data.CursorHolder;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewParser;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewType;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaItemBuilderDelegate {
    private static final String COPY_AVAILABLE_EDIT_INFO_KEY_STR = String.valueOf(SefInfo.COPY_AVAILABLE_EDIT_INFO.keyCode);
    private static final String PORTRAIT_EFFECT_INFO_KEY_STR = String.valueOf(3105);

    public static void addCleanOutData(MediaItem mediaItem, CursorHolder cursorHolder) {
        SuggestData.of(mediaItem).parseCleanOut(cursorHolder);
    }

    public static void addFilterAndToneData(MediaItem mediaItem) {
        if (!TextUtils.isEmpty(mediaItem.mSefFileTypes)) {
            mediaItem.mHasFilterAndTone = mediaItem.mSefFileTypes.contains(COPY_AVAILABLE_EDIT_INFO_KEY_STR);
        }
    }

    public static void addHighlightData(MediaItem mediaItem, CursorHolder cursorHolder) {
        byte[] blob;
        if (PreferenceFeatures.OneUi41.HIGHLIGHT_SUGGESTIONS && !mediaItem.isStories() && (blob = cursorHolder.getBlob(cursorHolder.indexDynamicViewInfo)) != null && blob.length > 0) {
            DynamicViewInfo parse = DynamicViewParser.parse(blob, mediaItem.getFileDuration(), false);
            DynamicViewData of2 = DynamicViewData.of(mediaItem);
            of2.dynamicViewInfo = parse;
            of2.dynamicViewBlob = blob;
            if (parse == null) {
                return;
            }
            if (PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL) {
                List list = (List) parse.getClipInfoData().stream().sorted(Comparator.comparing(new C0384t(26)).reversed()).collect(Collectors.toList());
                if (!list.isEmpty()) {
                    ClipInfo clipInfo = (ClipInfo) list.get(0);
                    of2.dynamicViewPlayInfo = null;
                    of2.dynamicViewInfo = parse;
                    mediaItem.setVideoHighlightTime(Long.valueOf(((long) clipInfo.clipStartMs) * 1000), Long.valueOf(((long) clipInfo.clipEndMs) * 1000));
                    return;
                }
                return;
            }
            DynamicViewPlayInfo build = new DynamicViewInfo.PlayInfoBuilder(parse).setFileDuration(mediaItem.getFileDuration()).setPlayType(DynamicViewType.getSuggestionsType(mediaItem.getFileDuration())).build();
            of2.dynamicViewPlayInfo = build;
            mediaItem.setTitle(build.getTypeString());
            mediaItem.setVideoHighlightTime(Long.valueOf(build.getVideoThumbStartTime()), (Long) null);
        }
    }

    public static void addPortraitData(MediaItem mediaItem, CursorHolder cursorHolder) {
        String string;
        if (PreferenceFeatures.OneUi41.PORTRAIT_SUGGESTIONS && (string = cursorHolder.getString(cursorHolder.indexOriginPath, (String) null)) != null) {
            mediaItem.setExtra(ExtrasID.ORIGIN_PATH, string);
        }
        if (!TextUtils.isEmpty(mediaItem.mSefFileTypes)) {
            mediaItem.mHasPortraitsEffect = mediaItem.mSefFileTypes.contains(PORTRAIT_EFFECT_INFO_KEY_STR);
        }
    }

    public static void addRevitalizedData(MediaItem mediaItem, CursorHolder cursorHolder) {
        SuggestData parseRemaster = SuggestData.of(mediaItem).parseRemaster(cursorHolder);
        if (parseRemaster.remasterType > 0) {
            mediaItem.mIsRevitalization = true;
        }
        String str = parseRemaster.originalPath;
        if (str != null) {
            mediaItem.setExtra(ExtrasID.ORIGIN_PATH, str);
        }
    }

    public static void addStoryData(MediaItem mediaItem, CursorHolder cursorHolder) {
        byte[] blob;
        if (cursorHolder.indexStoryType >= 0) {
            mediaItem.mCount = cursorHolder.getInt(cursorHolder.indexCount, 0);
            mediaItem.mIsStoriesItem = true;
            StoryData parse = StoryData.of(mediaItem).parse(cursorHolder);
            if (parse.storyType >= 0 && TextUtils.isEmpty(mediaItem.mMimeType)) {
                mediaItem.mMimeType = parse.getMimeType();
            }
            if (Features.isEnabled(Features.SUPPORT_DYNAMIC_VIEW) && (blob = cursorHolder.getBlob(cursorHolder.indexDynamicViewInfo)) != null && blob.length > 0) {
                DynamicViewInfo parse2 = DynamicViewParser.parse(blob, mediaItem.getFileDuration(), false);
                DynamicViewData.of(mediaItem).dynamicViewInfo = parse2;
                if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave) && parse2 != null) {
                    mediaItem.setVideoHighlightTime(Long.valueOf(((long) parse2.getStartMs()) * 1000), (Long) null);
                }
            }
        }
    }

    public static void addTrashData(MediaItem mediaItem, CursorHolder cursorHolder) {
        String string = cursorHolder.getString(cursorHolder.indexRestoreExtra, (String) null);
        TrashData of2 = TrashData.of(mediaItem);
        of2.restoreData = string;
        mediaItem.setExtra(ExtrasID.ORIGIN_PATH, cursorHolder.getString(cursorHolder.indexOriginPath, (String) null));
        of2.originalTitle = cursorHolder.getString(cursorHolder.indexOriginTitle, (String) null);
        long j2 = cursorHolder.getLong(cursorHolder.indexDeleteTime, -1);
        if (j2 > 0) {
            int i2 = cursorHolder.getInt(cursorHolder.indexExpiredPeriod, 0);
            if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
                long j3 = cursorHolder.getLong(cursorHolder.indexExpiredTime, 0);
                if (j3 > 0) {
                    i2 = (int) ((j3 - j2) / MediaApiContract.DAY_IN_MILLI);
                }
                int i7 = cursorHolder.indexTrashMediaId;
                if (i7 != -1) {
                    of2.mediaId = cursorHolder.getLong(i7, 0);
                }
            }
            of2.deleteTime = j2;
            of2.expiredPeriod = i2;
        }
        if (!TextUtils.isEmpty(string)) {
            parseRestoreData(mediaItem, string);
        }
    }

    public static MediaItem buildSuggestions(Cursor cursor) {
        CursorHolder cursorHolder = CursorHolder.getCursorHolder(cursor, CursorHolder.CursorType.SUGGESTS_CURSOR);
        if (cursorHolder.indexSuggestType < 0) {
            return null;
        }
        long j2 = cursorHolder.getLong(cursorHolder.indexId, -1);
        long j3 = cursorHolder.getLong(cursorHolder.indexMediaId, -1);
        MediaType valueOf = MediaType.valueOf(cursorHolder.getInt(cursorHolder.indexMediaType, MediaType.Unsupported.toInt()));
        MediaItem mediaItem = new MediaItem(cursorHolder.getString(cursorHolder.indexPath, (String) null), j2, j3, valueOf, 0, cursorHolder.getInt(cursorHolder.indexOrientation, 0), cursorHolder.getInt(cursorHolder.indexOrientationTag, 0), 0, cursorHolder.getInt(cursorHolder.indexWidth, 0), cursorHolder.getInt(cursorHolder.indexHeight, 0));
        mediaItem.mStorageType = StorageType.valueOf(cursorHolder.getInt(cursorHolder.indexStorageType, StorageType.Local.ordinal()));
        String string = cursorHolder.getString(cursorHolder.indexTitle, (String) null);
        mediaItem.mTitle = string;
        mediaItem.mDisplayName = string;
        mediaItem.mAlbumID = (int) j3;
        String string2 = cursorHolder.getString(cursorHolder.indexRect, (String) null);
        if (string2 != null) {
            ArrayList<RectF> listOf = RectUtils.listOf(string2);
            mediaItem.mCropRectRatioList = listOf;
            if (listOf != null) {
                mediaItem.mSmartCropRectRatio = listOf.get(Math.min(listOf.size() - 1, 1));
            }
        }
        int i2 = SuggestData.of(mediaItem).parse(cursorHolder).extra;
        StoryData of2 = StoryData.of(mediaItem);
        if (MediaItemSuggest.isCleanOutGroup(mediaItem) || MediaItemSuggest.isIntelligentGroup(mediaItem)) {
            mediaItem.mCount = i2;
        } else {
            if (i2 != -1) {
                of2.notifyStatus = i2;
            }
            of2.storyType = cursorHolder.getInt(cursorHolder.indexSuggestExtraData, -1);
            of2.startTime = cursorHolder.getLong(cursorHolder.indexSuggestExtraValue, -1);
            if (of2.storyType >= 0 && TextUtils.isEmpty(mediaItem.mMimeType)) {
                mediaItem.mMimeType = of2.getMimeType();
            }
        }
        String string3 = cursorHolder.getString(cursorHolder.indexRect, (String) null);
        if (string3 != null) {
            of2.coverRectRatio = string3;
        }
        mediaItem.setVideoThumbStartTime((long) cursorHolder.getInt(cursorHolder.indexVideoThumbStartTime, 0));
        return mediaItem;
    }

    public static void parseRestoreData(MediaItem mediaItem, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("__is360Video")) {
                mediaItem.m360Video = jSONObject.optBoolean("__is360Video");
            }
            if (jSONObject.has("__fileDuration")) {
                mediaItem.mFileDuration = jSONObject.optInt("__fileDuration");
            }
            if (jSONObject.has("__recordingMode")) {
                mediaItem.mRecordingMode = jSONObject.optInt("__recordingMode");
            }
            if (jSONObject.has("__sefFileSubType")) {
                mediaItem.mSefFileSubType = jSONObject.optInt("__sefFileSubType");
            }
            if (jSONObject.has("__sefFileType")) {
                mediaItem.mSefFileType = jSONObject.optInt("__sefFileType");
            }
            if (jSONObject.has("__group_type")) {
                mediaItem.mGroupType = jSONObject.optInt("__group_type");
            }
            if (jSONObject.has("__dateTaken")) {
                mediaItem.mDateTaken = jSONObject.optLong("__dateTaken");
            }
            if (jSONObject.has("__mimeType")) {
                mediaItem.mMimeType = jSONObject.optString("__mimeType");
            }
            if (jSONObject.has("__resolution")) {
                mediaItem.mResolution = jSONObject.optString("__resolution");
            }
            if (jSONObject.has("__recordingType")) {
                mediaItem.mRecordingType = jSONObject.optInt("__recordingType");
            }
            if (jSONObject.has("__isDrm")) {
                TrashData.of(mediaItem).drm = jSONObject.optBoolean("__isDrm");
            }
            if (jSONObject.has("__isHdr10Video") && jSONObject.optBoolean("__isHdr10Video")) {
                mediaItem.setVideoColorFormat(1001);
            }
        } catch (Error | JSONException e) {
            a.z(e, new StringBuilder("addRestoreData failed. e="), "MediaItemBuilder");
        }
    }

    public static void setSuggestExtraData(MediaItem mediaItem, String str) {
        try {
            String[] split = str.split(NumericEnum.SEP);
            mediaItem.mMediaID = UnsafeCast.toLong(split[0], 0);
            mediaItem.setAlbumID((int) mediaItem.getMediaId());
            String str2 = split[1];
            if (str2 != null && !str2.isEmpty()) {
                mediaItem.setPath(split[1]);
            }
            mediaItem.setSize(UnsafeCast.toInt(split[2], 0), UnsafeCast.toInt(split[3], 0));
            mediaItem.setOrientation(UnsafeCast.toInt(split[4], 0));
            String str3 = split[5];
            if (str3 != null && str3.length() > 0) {
                ArrayList<RectF> listOf = RectUtils.listOf(split[5]);
                mediaItem.mCropRectRatioList = listOf;
                if (listOf != null) {
                    mediaItem.mSmartCropRectRatio = listOf.get(Math.min(listOf.size() - 1, 1));
                }
            }
            mediaItem.mMediaType = MediaType.valueOf(UnsafeCast.toInt(split[6], MediaType.Image.toInt()));
            mediaItem.mStorageType = StorageType.valueOf(UnsafeCast.toInt(split[7], StorageType.Local.toInt()));
            mediaItem.setVideoThumbStartTime((long) UnsafeCast.toInt(split[8], 0));
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.w((CharSequence) "MediaItemBuilder", "setMediaItemExtraData failed", (Throwable) e);
        }
    }

    public static String updateCreatureExtra(MediaItem mediaItem, CursorHolder cursorHolder) {
        IdentityCreatureUtil.Category category;
        String str;
        CreatureData of2 = CreatureData.of(mediaItem);
        long j2 = cursorHolder.getLong(cursorHolder.indexCreatureFaceGroupId, -1);
        if (j2 > -1 && TextUtils.isEmpty(mediaItem.mSubCategory)) {
            int i2 = cursorHolder.getInt(cursorHolder.indexCreatureType, 0);
            long j3 = cursorHolder.getLong(cursorHolder.indexCreatureFaceRecommendedId, -1);
            long j8 = cursorHolder.getLong(cursorHolder.indexCreatureTagId, -1);
            if (i2 == 0) {
                category = IdentityCreatureUtil.Category.PEOPLE;
            } else {
                category = IdentityCreatureUtil.Category.PET;
            }
            mediaItem.mSubCategory = IdentityCreatureUtil.create(j2, j8, j3, category);
            mediaItem.setExtra(ExtrasID.FACE_GROUP_ID, Long.valueOf(j2));
            of2.faceGroupId = j2;
            if (i2 == 0) {
                String string = cursorHolder.getString(cursorHolder.indexCreatureRelationship, (String) null);
                if (PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP && !TextUtils.isEmpty(string) && !RelationshipKeySet.hasPredefinedRelationshipType(string)) {
                    String type = CustomRelationshipKeySet.getInstance().getType(string);
                    string = type != null ? type : CustomRelationshipKeySet.getInstance().addDataFrom(string, false);
                }
                of2.relationshipType = string;
                if (Features.isEnabled(Features.SUPPORT_PDC_CONTACT_LINK)) {
                    String string2 = cursorHolder.getString(cursorHolder.indexContactRawId, (String) null);
                    if (!TextUtils.isEmpty(string2)) {
                        try {
                            of2.contactRawId = String.valueOf(Long.parseLong(string2));
                        } catch (NumberFormatException unused) {
                            if (string2.contains(".")) {
                                str = String.valueOf((long) Float.parseFloat(string2));
                            } else {
                                str = "0";
                            }
                            of2.contactRawId = str;
                        }
                    }
                    of2.creatureUuid = cursorHolder.getString(cursorHolder.indexCreatureUuid, (String) null);
                }
            }
        }
        String string3 = cursorHolder.getString(cursorHolder.indexCreatureTagName, (String) null);
        if ("null".equalsIgnoreCase(string3)) {
            string3 = null;
        }
        if (string3 != null) {
            of2.creatureName = string3;
        }
        String string4 = cursorHolder.getString(cursorHolder.indexCreatureFacePosRatio, (String) null);
        if (string4 != null) {
            of2.facePositionRatio = string4;
            mediaItem.mFaceRectRatio = RectUtils.stringToRectF(string4);
        }
        if (PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE) {
            boolean z = true;
            if (cursorHolder.getInt(cursorHolder.indexCreatureFaceHide, 0) != 1) {
                z = false;
            }
            of2.isCreatureHide = z;
        }
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
            of2.faceScore = cursorHolder.getInt(cursorHolder.indexCreatureFaceScore, 0);
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            of2.essentialGroup = cursorHolder.getInt(cursorHolder.indexCreatureEssentialGroup, -1);
            of2.uniqueDays = cursorHolder.getInt(cursorHolder.indexCreatureUniqueDays, -1);
            of2.faceGroupUuid = cursorHolder.getString(cursorHolder.indexCreatureGroupUuid, (String) null);
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_CREATURE_THUMB_FROM_VIDEO_FRAME) {
            of2.frameId = cursorHolder.getInt(cursorHolder.indexVideoFrameId, -1);
            int i7 = cursorHolder.getInt(cursorHolder.indexVideoFramePos, -1);
            of2.framePos = i7;
            mediaItem.setTag("creature_frame_pos", Integer.valueOf(i7));
        }
        return string3;
    }
}
