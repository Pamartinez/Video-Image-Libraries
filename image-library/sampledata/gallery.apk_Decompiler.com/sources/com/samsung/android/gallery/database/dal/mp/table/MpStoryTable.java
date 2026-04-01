package com.samsung.android.gallery.database.dal.mp.table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpStoryTable extends DbTable {
    public MpStoryTable(QueryParams queryParams) {
        super(queryParams);
    }

    public String getTableNameRaw() {
        return "story";
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("S._id", "__albumID");
        this.mQueryBuilder.addProjection("S.start_time", "__startTime");
        this.mQueryBuilder.addProjection("S.end_time", "__endTime");
        this.mQueryBuilder.addProjection("S.cover_id", "__storyCover");
        this.mQueryBuilder.addProjection("S.notify_status", "__storyNotifyStatus");
        this.mQueryBuilder.addProjection("S.story_type", "__storyType");
        this.mQueryBuilder.addProjection("S.cover_rect_ratio", "__storyCoverRectRatio");
        if (this.IS_GTE_T) {
            this.mQueryBuilder.addProjection("S.cover_face_ratio", "__cover_face_ratio");
            this.mQueryBuilder.addProjection("S.user_enter", "__story_user_enter");
        }
        if (Features.isEnabled(Features.SUPPORT_CMH_STORY_SA_TYPE)) {
            this.mQueryBuilder.addProjection("S.sa_type", "__storySaType");
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme)) {
            this.mQueryBuilder.addProjection("S.story_theme", "__storyTheme");
            this.mQueryBuilder.addProjection("S.story_filter", "__storyFilter");
            this.mQueryBuilder.addProjection("S.story_bgm_info", "__storyBgmInfo");
            this.mQueryBuilder.addProjection("S.story_updated_by_user", "__storyUpdatedByUser");
        }
        this.mQueryBuilder.addProjection("S.creation_time", "creation_time");
        if (PreferenceFeatures.OneUi8x.SUPPORT_RECAP) {
            this.mQueryBuilder.addProjection("S.story_enhanced_cover_applied", "__recap_story_path");
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "S");
    }

    public String tag() {
        return "MpStoryTable";
    }

    public void onConstruct() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
