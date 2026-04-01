package com.samsung.android.gallery.database.dal.abstraction;

import com.samsung.android.gallery.support.utils.Features;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DbKey {
    public static final String ALBUMS;
    public static final String ALBUMS_AUTO_COMPLETE;
    public static final String ALBUMS_COUNT;
    public static final String ALBUMS_NEW;
    public static final String ALBUM_FILES;
    public static final String ALBUM_FILE_COUNT;
    public static final String ALBUM_FILE_DAY;
    public static final String ALBUM_FILE_IDS;
    public static final String ALBUM_FILE_IDS_ORDERED;
    public static final String ALBUM_FILE_REALRATIO;
    public static final String ALL_PICTURES;
    public static final String ALL_PICTURES_DAY;
    public static final String ALL_PICTURES_ID;
    public static final String ALL_PICTURES_NO_GROUP;
    public static final String ALL_PICTURES_REAL_RATIO;
    public static final String DUMMY = mp("__DUMMY__");
    @Deprecated
    public static final String FILES = mp("location://file");
    public static final String FILES_BURSTSHOT;
    public static final String FILES_DATA_STAMP;
    private static final String FILES_HEADER;
    public static final String FILES_SIMILAR;
    public static final String FILES_SIMILAR_ALL;
    public static final String FILES_SIMILAR_COUNT;
    public static final String FILES_SINGLETAKE;
    public static final String FILES_SINGLETAKE_ALL;
    public static final String RELATED_MEMORIES;
    public static final String STORIES;
    public static final String STORIES_PICKER;
    public static final String STORY_APPBAR;
    public static final String STORY_AUTO_COMPLETE;
    public static final String STORY_FILES;
    public static final String STORY_FILES_CHAPTER;
    public static final String STORY_FILES_CURATION;
    public static final String STORY_FILES_DAY;
    public static final String STORY_FILES_HIGHLIGHT_LIST;
    public static final String STORY_HIDE_RULE_DATE;
    public static final String STORY_HIDE_RULE_SCENE;
    public static final String STORY_HIDE_SCENE_SELECTION;
    public static final String STORY_NOTIFICATION_FILES;
    public static final String STORY_SHARE_FILES;
    public static final String TIMELINE;
    public static final String TIMELINE_DAY;
    public static final String TIMELINE_FILE_IDS;
    public static final String TIMELINE_FILE_IDS_ORDERED;
    public static final String TIMELINE_REALRATIO;
    public static final String TIMELINE_SPAN;
    public static final String TIMELINE_YEAR;
    private static final boolean USE_MP_STORY;
    public static final String VIRTUAL_ALBUM_FAVORITE;
    public static final String VIRTUAL_ALBUM_FAVORITE_ALBUM = mp("location://virtual/album/favorite");
    public static final String VIRTUAL_ALBUM_FAVORITE_DAY;
    public static final String VIRTUAL_ALBUM_FAVORITE_FILE_IDS_ORDERED;
    public static final String VIRTUAL_ALBUM_FAVORITE_REAL_RATIO;
    public static final String VIRTUAL_ALBUM_FILES;
    public static final String VIRTUAL_ALBUM_FILES_DAY;
    public static final String VIRTUAL_ALBUM_FILES_REAL_RATIO;
    public static final String VIRTUAL_ALBUM_RECENT;
    public static final String VIRTUAL_ALBUM_RECENT_ALBUM = mp("location://virtual/album/recently");
    public static final String VIRTUAL_ALBUM_RECENT_SHARE_DATA;
    public static final String VIRTUAL_ALBUM_REPAIR;
    public static final String VIRTUAL_ALBUM_REPAIR_DAY;
    public static final String VIRTUAL_ALBUM_REPAIR_REAL_RATIO;
    public static final String VIRTUAL_ALBUM_VIDEO;
    public static final String VIRTUAL_ALBUM_VIDEO_DAY;
    public static final String VIRTUAL_ALBUM_VIDEO_FILE_IDS_ORDERED;
    public static final String VIRTUAL_ALBUM_VIDEO_REAL_RATIO;

    static {
        String str;
        boolean isEnabled = Features.isEnabled(Features.CMH_TO_MP_MIGRATION);
        USE_MP_STORY = isEnabled;
        String mp = mp("location://timeline");
        TIMELINE = mp;
        TIMELINE_DAY = C0212a.A(mp, "/day");
        TIMELINE_REALRATIO = C0212a.A(mp, "/realratio");
        TIMELINE_SPAN = C0212a.A(mp, "/span");
        TIMELINE_FILE_IDS = C0212a.A(mp, "/fileIds");
        TIMELINE_FILE_IDS_ORDERED = C0212a.A(mp, "/orderedFileIds");
        TIMELINE_YEAR = C0212a.A(mp, "/year");
        String mp2 = mp("location://albums");
        ALBUMS = mp2;
        ALBUMS_NEW = C0212a.A(mp2, "/new");
        ALBUMS_COUNT = C0212a.A(mp2, "/count");
        ALBUMS_AUTO_COMPLETE = C0212a.A(mp2, "/autocomplete");
        ALBUM_FILES = C0212a.A(mp2, "/files");
        ALBUM_FILE_COUNT = C0212a.A(mp2, "/files/count");
        ALBUM_FILE_IDS = C0212a.A(mp2, "/files/fileIds");
        ALBUM_FILE_DAY = C0212a.A(mp2, "/files/day");
        ALBUM_FILE_REALRATIO = C0212a.A(mp2, "/files/realratio");
        ALBUM_FILE_IDS_ORDERED = C0212a.A(mp2, "/files/fileIds_ordered");
        String mp3 = mp("location://file");
        FILES_HEADER = mp3;
        FILES_DATA_STAMP = C0212a.A(mp3, "/dataStamp");
        ALL_PICTURES = C0212a.A(mp3, "/all");
        ALL_PICTURES_ID = C0212a.A(mp3, "/allId");
        ALL_PICTURES_NO_GROUP = C0212a.A(mp3, "/allNoGroup");
        ALL_PICTURES_DAY = C0212a.A(mp3, "/day");
        ALL_PICTURES_REAL_RATIO = C0212a.A(mp3, "/realRatio");
        FILES_BURSTSHOT = C0212a.A(mp3, "/burst");
        FILES_SIMILAR = C0212a.A(mp3, "/similar");
        FILES_SIMILAR_ALL = C0212a.A(mp3, "/similarall");
        FILES_SIMILAR_COUNT = C0212a.A(mp3, "/similar/count");
        FILES_SINGLETAKE = C0212a.A(mp3, "/singletake");
        FILES_SINGLETAKE_ALL = C0212a.A(mp3, "/singletakeall");
        String mp4 = mp("location://virtual/album/video/fileList");
        VIRTUAL_ALBUM_VIDEO = mp4;
        VIRTUAL_ALBUM_VIDEO_DAY = C0212a.A(mp4, "/day");
        VIRTUAL_ALBUM_VIDEO_FILE_IDS_ORDERED = C0212a.A(mp4, "/fileIds_ordered");
        VIRTUAL_ALBUM_VIDEO_REAL_RATIO = C0212a.A(mp4, "/realratio");
        String mp5 = mp("location://virtual/album/favorite/fileList");
        VIRTUAL_ALBUM_FAVORITE = mp5;
        VIRTUAL_ALBUM_FAVORITE_DAY = C0212a.A(mp5, "/day");
        VIRTUAL_ALBUM_FAVORITE_FILE_IDS_ORDERED = C0212a.A(mp5, "/fileIds_ordered");
        VIRTUAL_ALBUM_FAVORITE_REAL_RATIO = C0212a.A(mp5, "/realratio");
        String mp6 = mp("location://virtual/album/recently/fileList");
        VIRTUAL_ALBUM_RECENT = mp6;
        VIRTUAL_ALBUM_RECENT_SHARE_DATA = C0212a.A(mp6, "/share/data");
        String mp7 = mp("location://virtual/album/repair/fileList");
        VIRTUAL_ALBUM_REPAIR = mp7;
        VIRTUAL_ALBUM_REPAIR_DAY = C0212a.A(mp7, "/day");
        VIRTUAL_ALBUM_REPAIR_REAL_RATIO = C0212a.A(mp7, "/realratio");
        String mp8 = mp("location://virtual/album/fileList");
        VIRTUAL_ALBUM_FILES = mp8;
        VIRTUAL_ALBUM_FILES_DAY = C0212a.A(mp8, "/day");
        VIRTUAL_ALBUM_FILES_REAL_RATIO = C0212a.A(mp8, "/realratio");
        if (isEnabled) {
            str = mp("location://story/albums");
        } else {
            str = cmh("location://story/albums");
        }
        STORIES = str;
        STORIES_PICKER = C0212a.A(str, "/picker");
        String A10 = C0212a.A(str, "/files");
        STORY_FILES = A10;
        STORY_FILES_DAY = C0212a.A(str, "/files/day");
        STORY_FILES_CURATION = C0212a.A(str, "/files/curation");
        STORY_FILES_HIGHLIGHT_LIST = C0212a.A(str, "/files/highlightList");
        STORY_NOTIFICATION_FILES = C0212a.A(str, "/notification/files");
        STORY_AUTO_COMPLETE = C0212a.A(str, "/autocomplete");
        RELATED_MEMORIES = C0212a.A(str, "/relatedmemories");
        STORY_APPBAR = C0212a.A(str, "/appbar");
        STORY_FILES_CHAPTER = C0212a.A(A10, "/chapter");
        STORY_HIDE_RULE_DATE = C0212a.A(str, "/hideRuleDate");
        STORY_HIDE_RULE_SCENE = C0212a.A(str, "/hideRuleScene");
        STORY_HIDE_SCENE_SELECTION = C0212a.A(str, "/hideSceneSelection");
        STORY_SHARE_FILES = C0212a.A(str, "album/share/files");
    }

    public static String cmh(String str) {
        return str.replace("location://", "cmh://");
    }

    public static String mp(String str) {
        return str.replace("location://", "mp://");
    }
}
