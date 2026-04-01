package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectMeView extends TagMeView {
    private MediaItem[] mMeItems;

    public SelectMeView(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    public int getContentStringResID() {
        return R.string.multiple_faces_are_tagged_as_you;
    }

    public void onPositiveButtonClicked() {
        this.mEventContext.getBlackboard().publish("data://user/selected", this.mMeItems);
        this.mEventContext.getBlackboard().post("command://MoveURL", "location://search/fileList/SelectMe");
    }

    public void setData(Object obj) {
        this.mMeItems = (MediaItem[]) obj;
    }
}
