package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.AddTagBaseCmd;
import com.samsung.android.gallery.app.controller.internals.AddTagViaDialogCmd;
import com.samsung.android.gallery.app.controller.internals.AddTagViaEditorCmd;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.tag.MyTagUpdater;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.details.DetailsLayoutManager;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemTag extends DetailsListItem<ListViewHolder, FlexboxLayoutManager> implements AddTagBaseCmd.OnMyTagListener {
    private final boolean SUPPORT_TAG_EDITOR = Features.isEnabled(Features.SUPPORT_TAG_EDITOR);
    private final int mTagPropertyWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TagAdapter extends DetailsListAdapter<ListViewHolder> {
        private int mTitleMaxWidth = -1;

        public TagAdapter(RecyclerView recyclerView) {
            super(recyclerView);
        }

        private String getTag(MediaItem mediaItem) {
            String title = mediaItem.getTitle();
            if (title != null) {
                return title.toLowerCase(Locale.getDefault());
            }
            return "";
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$contains$0(MediaItem mediaItem, MediaItem mediaItem2) {
            return StringCompat.equals(getTag(mediaItem2), getTag(mediaItem));
        }

        public boolean contains(MediaItem mediaItem) {
            return getData().stream().anyMatch(new B(this, mediaItem));
        }

        public int getItemCount() {
            int itemCount = super.getItemCount();
            if (itemCount == 0) {
                return 1;
            }
            return itemCount + 1;
        }

        public int getItemViewType(int i2) {
            if (getData().isEmpty() && i2 == 0) {
                return -1;
            }
            if (i2 >= getData().size()) {
                return -2;
            }
            return 0;
        }

        public int getMaxWidth() {
            return this.mTitleMaxWidth;
        }

        public void updateMaxWidth(int i2, ListViewHolder listViewHolder) {
            if (i2 > 0) {
                this.mTitleMaxWidth = i2;
                if (listViewHolder instanceof ViewHolderTag) {
                    ((ViewHolderTag) listViewHolder).setTitleMaxWidth(i2);
                }
            }
        }

        public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
            int i7;
            super.onBindViewHolder(listViewHolder, i2);
            if (listViewHolder.getItemViewType() == -2) {
                listViewHolder.bind(this.DUMMY_ITEM);
            } else {
                listViewHolder.bind(getMediaItem(i2));
            }
            if ((listViewHolder instanceof ViewHolderTag) && (i7 = this.mTitleMaxWidth) > 0) {
                ((ViewHolderTag) listViewHolder).setTitleMaxWidth(i7);
            }
        }

        public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            View inflate = getLayoutInflater(viewGroup.getContext()).inflate(R.layout.recycler_item_details_tag_layout, viewGroup, false);
            if (i2 == -2) {
                return new ViewHolderTagEdit(inflate, i2);
            }
            if (i2 == -1) {
                return new ViewHolderTagAdd(inflate, i2);
            }
            return new ViewHolderTag(inflate, i2);
        }
    }

    public DetailsItemTag(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
        this.mTagPropertyWidth = detailsView.getResources().getDimensionPixelOffset(R.dimen.moreinfo_item_tag_view_extra_size);
    }

    /* access modifiers changed from: private */
    public void appendTag(MediaItem mediaItem) {
        TagAdapter tagAdapter = (TagAdapter) getAdapter();
        if (tagAdapter != null && !tagAdapter.contains(mediaItem)) {
            tagAdapter.append(mediaItem);
        }
    }

    private void onAddItemClicked() {
        String str;
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.e(this.TAG, "onAddTag failed");
            return;
        }
        ArrayList<MediaItem> data = getAdapter().getData();
        if (this.SUPPORT_TAG_EDITOR) {
            AddTagViaEditorCmd addTagViaEditorCmd = new AddTagViaEditorCmd(currentItem, data, this);
            EventContext eventContext = this.mEventContext;
            ArrayList arrayList = new ArrayList(data);
            if (currentItem.isImage()) {
                str = ContentUri.getUriString(currentItem);
            } else {
                str = null;
            }
            addTagViaEditorCmd.execute(eventContext, arrayList, str);
            return;
        }
        new AddTagViaDialogCmd(currentItem, data, this) {
            public void addTag(EventContext eventContext, ArrayList<Object> arrayList) {
                super.addTag(eventContext, arrayList);
                if (arrayList != null && !arrayList.isEmpty() && !TextUtils.isEmpty((String) arrayList.get(0))) {
                    DetailsItemTag.this.appendTag(MediaItemBuilder.create((String) arrayList.get(0)));
                }
            }
        }.execute(this.mEventContext, new Object[0]);
    }

    private void onDeleteItemClicked(int i2, MediaItem mediaItem) {
        TagAdapter tagAdapter = (TagAdapter) getAdapter();
        boolean z = false;
        if (tagAdapter == null || TextUtils.isEmpty(mediaItem.getTitle()) || !TextUtils.equals(tagAdapter.getData().get(i2).getTitle(), mediaItem.getTitle())) {
            StringCompat stringCompat = this.TAG;
            String title = mediaItem.getTitle();
            Integer valueOf = Integer.valueOf(i2);
            if (tagAdapter != null) {
                z = true;
            }
            Log.e((CharSequence) stringCompat, "onDeleteTag failed", title, valueOf, Boolean.valueOf(z));
            return;
        }
        new MyTagUpdater(this.mEventContext.getContext(), this.mBlackboard, this.mEventContext.getCurrentItem(), new ArrayList(), new ArrayList(Collections.singletonList(mediaItem.getTitle()))).execute(new Void[0]);
        getAdapter().delete(i2);
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_SELECT_TAG);
    }

    private void onTagClicked(MediaItem mediaItem) {
        String title = mediaItem.getTitle();
        UriBuilder appendArg = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Category/MyTag", title)).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, "My tags").appendArg("sub", title);
        this.mBlackboard.post("command://MoveURL", appendArg.appendArg("title", "#" + title).appendArg("term", "usertag").appendArg("from_viewer", true).build());
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_REMOVE_TAG);
    }

    /* access modifiers changed from: private */
    public void updateTitleMaxWidth() {
        if (!this.SUPPORT_TAG_EDITOR && this.mListView != null) {
            int width = this.mItemView.getWidth() - this.mTagPropertyWidth;
            TagAdapter tagAdapter = (TagAdapter) getAdapter();
            if (width != tagAdapter.getMaxWidth()) {
                if (this.mListView.getChildCount() == 0) {
                    tagAdapter.updateMaxWidth(width, (ListViewHolder) null);
                    return;
                }
                for (int i2 = 0; i2 < this.mListView.getChildCount(); i2++) {
                    tagAdapter.updateMaxWidth(width, (ListViewHolder) this.mListView.findViewHolderForAdapterPosition(i2));
                }
            }
        }
    }

    public DetailsListAdapter<ListViewHolder> createAdapter(RecyclerView recyclerView) {
        TagAdapter tagAdapter = new TagAdapter(recyclerView);
        this.mItemView.post(new A(this, 1));
        return tagAdapter;
    }

    public int getLayoutId() {
        return R.id.moreinfo_my_tag_oneui_6x;
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onItemClick(listViewHolder, i2, mediaItem, i7);
        if (i7 == 1002) {
            postAnalyticsLog(AnalyticsEventId.EVENT_MENU_ADD_TAG);
            onAddItemClicked();
        } else if (i7 == 1003) {
            postAnalyticsLog(AnalyticsEventId.EVENT_MENU_EDIT_TAG);
            onAddItemClicked();
        } else if (i7 == 1001) {
            onDeleteItemClicked(i2, mediaItem);
        } else {
            onTagClicked(mediaItem);
        }
    }

    public void onSelectDone(ArrayList<String> arrayList) {
        Log.d(this.TAG, "onSelectDone ");
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.TAG, this);
    }

    public boolean supportItem(MediaItem mediaItem) {
        if ((!PocFeatures.SUPPORT_PRIVATE_ALBUM || !mediaItem.isPrivateItem()) && !mediaItem.isSharing() && mediaItem.getFileId() > 0) {
            return true;
        }
        return false;
    }

    public void updateLayout() {
        super.updateLayout();
        updateTitleMaxWidth();
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        getAdapter().setData(DetailsData.of(mediaItem).getUserTags());
    }

    public FlexboxLayoutManager createLayoutManager(RecyclerView recyclerView) {
        return DetailsLayoutManager.createFlexboxLayoutManager(recyclerView);
    }
}
