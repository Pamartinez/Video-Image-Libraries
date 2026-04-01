package com.samsung.android.gallery.database.dal.cmh;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.impl.CameraAiImpl;
import com.samsung.android.gallery.database.dal.cmh.impl.StoriesImpl;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CmhMethodMap extends HashMap<String, CursorProvider> {
    final QueryParams mParams;

    public CmhMethodMap(QueryParams queryParams) {
        this.mParams = queryParams;
        init();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$0(QueryParams queryParams) {
        return getStories().getStoryAlbumCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$1(QueryParams queryParams) {
        return getStories().getStoryAlbumPickerCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$10(QueryParams queryParams) {
        return getStories().getRelatedMemories();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$11(QueryParams queryParams) {
        return getStories().getStoryAppBar();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$12(QueryParams queryParams) {
        return getStories().getStoryChapterCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$13(QueryParams queryParams) {
        return getStories().getDateHideRuleCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$14(QueryParams queryParams) {
        return getStories().getSceneHideRuleCursor(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$15(QueryParams queryParams) {
        return getStories().getSceneHideRuleCursor(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$16(QueryParams queryParams) {
        return getStories().getStoryShareFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$17(QueryParams queryParams) {
        return getCameraAi().getCameraAiCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$2(QueryParams queryParams) {
        return getStories().getStoryAlbumFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$3(QueryParams queryParams) {
        return getStories().getStoryAlbumFileDateCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$4(QueryParams queryParams) {
        return getStories().getStoryAlbumFileCurationCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$5(QueryParams queryParams) {
        return getStories().getStoryAlbumFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$6(QueryParams queryParams) {
        return getStories().getStoryNotificationFileCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$7(QueryParams queryParams) {
        return getStories().getSearchAutoCompleteStoryCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$8(QueryParams queryParams) {
        return getStories().getVisualArtCursor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Cursor lambda$init$9(QueryParams queryParams) {
        return getStories().getAutoCreatedVisualArtCursor();
    }

    public CameraAiImpl getCameraAi() {
        return new CameraAiImpl(this.mParams);
    }

    public StoriesImpl getStories() {
        return new StoriesImpl(this.mParams);
    }

    public void init() {
        put(DbKey.STORIES, new a(this, 0));
        put(DbKey.STORIES_PICKER, new a(this, 17));
        put(DbKey.STORY_FILES, new a(this, 1));
        put(DbKey.STORY_FILES_DAY, new a(this, 2));
        put(DbKey.STORY_FILES_CURATION, new a(this, 3));
        put(DbKey.STORY_FILES_HIGHLIGHT_LIST, new a(this, 4));
        put(DbKey.STORY_NOTIFICATION_FILES, new a(this, 5));
        put(DbKey.STORY_AUTO_COMPLETE, new a(this, 6));
        put("cmh://visualArt", new a(this, 7));
        put("cmh://visualArt/autocreate", new a(this, 8));
        put(DbKey.RELATED_MEMORIES, new a(this, 9));
        put(DbKey.STORY_APPBAR, new a(this, 10));
        put(DbKey.STORY_FILES_CHAPTER, new a(this, 11));
        put(DbKey.STORY_HIDE_RULE_DATE, new a(this, 12));
        put(DbKey.STORY_HIDE_RULE_SCENE, new a(this, 13));
        put(DbKey.STORY_HIDE_SCENE_SELECTION, new a(this, 14));
        put(DbKey.STORY_SHARE_FILES, new a(this, 15));
        put("cmh:///cameraAi", new a(this, 16));
    }
}
