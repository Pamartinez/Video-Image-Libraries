package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item;

import O3.o;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedDataHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelatedPageItem extends PageItem {
    private RelatedDataHolder mData;
    private List<Integer> mIds = new ArrayList();

    public boolean equalItems(PageItem pageItem) {
        if (pageItem instanceof RelatedPageItem) {
            RelatedPageItem relatedPageItem = (RelatedPageItem) pageItem;
            if (!this.mIds.isEmpty() && this.mIds.size() == relatedPageItem.mIds.size()) {
                return new HashSet(this.mIds).containsAll(relatedPageItem.mIds);
            }
        }
        return super.equalItems(pageItem);
    }

    public RelatedDataHolder getRelatedData() {
        return this.mData;
    }

    public int getType() {
        return 2;
    }

    public RelatedPageItem setRelatedData(RelatedDataHolder relatedDataHolder) {
        this.mData = relatedDataHolder;
        if (relatedDataHolder == null || !relatedDataHolder.isValid()) {
            this.mIds = new ArrayList();
            return this;
        }
        this.mIds = (List) relatedDataHolder.pickedStories.stream().map(new o(25)).collect(Collectors.toList());
        return this;
    }

    public String toString() {
        Boolean bool;
        StringBuilder sb2 = new StringBuilder("RelatedPageItem@");
        sb2.append(Integer.toHexString(hashCode()));
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getType());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        RelatedDataHolder relatedDataHolder = this.mData;
        if (relatedDataHolder != null) {
            bool = Boolean.valueOf(relatedDataHolder.isValid());
        } else {
            bool = null;
        }
        sb2.append(bool);
        return sb2.toString();
    }
}
