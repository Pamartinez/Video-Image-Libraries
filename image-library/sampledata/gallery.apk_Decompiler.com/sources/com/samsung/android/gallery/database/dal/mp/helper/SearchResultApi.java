package com.samsung.android.gallery.database.dal.mp.helper;

import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.table.MpFacesView;
import com.samsung.android.gallery.support.providers.CmhUri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchResultApi extends BaseImpl {
    public SearchResultApi() {
        super(new QueryParams());
    }

    public String getColumnForExpression(String str) {
        return MpFacesView.getType(str).getExpressionColumn();
    }

    public String getExpressionSelection() {
        return "sec_media_id = ?";
    }

    public Uri getFacesUri() {
        return CmhUri.getFaces();
    }

    public String getMyTagSelection() {
        return "sec_media_id = ? AND tag = ?";
    }

    public Uri getMyTagUri() {
        return CmhUri.getUserTags();
    }

    public String getOthersSelection() {
        return "sec_media_id = ? AND scene_name = ?";
    }

    public Uri getOthersUri() {
        return CmhUri.getSceneries();
    }

    public Uri getPetFacesUri() {
        return CmhUri.getPetFaces();
    }

    public String tag() {
        return "SearchResultApi";
    }
}
