package com.samsung.android.gallery.module.mdebase.db.Table;

import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.mobileservice.social.share.provider.SharedItemContract;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MdeSharedItemTable extends DbTable {
    static final String[] COVER_PROJECTION = {"meta_data", "thumbnail_local_path", "mime_type", SharedItemContract.Item.ITEM_ID};

    public MdeSharedItemTable(QueryParams queryParams) {
        super(queryParams);
    }

    public String[] getSpaceCoverProjectionArray() {
        return COVER_PROJECTION;
    }

    public String getTableNameRaw() {
        return "item";
    }

    public void setDefaultProjection() {
        this.mQueryBuilder.addProjection("_id", "__absID");
        this.mQueryBuilder.addProjection("spaceId", "spaceId");
        this.mQueryBuilder.addProjection(SharedItemContract.Item.ITEM_ID, SharedItemContract.Item.ITEM_ID);
        this.mQueryBuilder.addProjection("title", "title");
        this.mQueryBuilder.addProjection("memo", "memo");
        this.mQueryBuilder.addProjection("createTime", "createTime");
        this.mQueryBuilder.addProjection("modifiedTime", "modifiedTime");
        this.mQueryBuilder.addProjection("owner", "owner");
        this.mQueryBuilder.addProjection("mime_type", "mime_type");
        this.mQueryBuilder.addProjection(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
        this.mQueryBuilder.addProjection("is_owned_by_me", "is_owned_by_me");
        this.mQueryBuilder.addProjection(SharedItemContract.Item.ORIGINAL_URL, SharedItemContract.Item.ORIGINAL_URL);
        this.mQueryBuilder.addProjection("download_url", "download_url");
        this.mQueryBuilder.addProjection("thumbnail_url", "thumbnail_url");
        this.mQueryBuilder.addProjection("streaming_url", "streaming_url");
        this.mQueryBuilder.addProjection("meta_data", "meta_data");
        this.mQueryBuilder.addProjection("size", "size");
        this.mQueryBuilder.addProjection("thumbnail_local_path", "thumbnail_local_path");
        this.mQueryBuilder.addProjection("original_content_path", "original_content_path");
        this.mQueryBuilder.addProjection("last_synced_time", "last_synced_time");
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY)) {
            this.mQueryBuilder.addProjection("hash", "hash");
            this.mQueryBuilder.addProjection(BundleKey.ORIGINAL_CONTENT_URI, BundleKey.ORIGINAL_CONTENT_URI);
        }
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
            this.mQueryBuilder.addProjection("instant_meta_data", "instant_meta_data");
        }
        if (Features.isEnabled(Features.SUPPORT_HIDDEN_PATH_IN_SEMS_SHARE_DB)) {
            this.mQueryBuilder.addProjection("original_in_hidden_folder_path", "original_in_hidden_folder_path");
            this.mQueryBuilder.addProjection("original_in_hidden_folder_content_uri", "original_in_hidden_folder_content_uri");
        }
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ITEM_STATUS)) {
            this.mQueryBuilder.addProjection(SharedItemContract.Item.LAST_MODIFIER, "__lastModifier");
            this.mQueryBuilder.addProjection("item_status", "__itemStatus");
        }
        if (Features.isEnabled(Features.SUPPORT_CREATOR)) {
            this.mQueryBuilder.addProjection("creator", "__creator");
        }
    }

    public void setDefaultTable() {
        this.mQueryBuilder.addTable(getTableNameRaw(), "");
    }

    public String tag() {
        return "MdeSharedItemTable";
    }

    public void onConstruct() {
    }

    public void setDefaultCondition() {
    }

    public void setDefaultOrder() {
    }
}
