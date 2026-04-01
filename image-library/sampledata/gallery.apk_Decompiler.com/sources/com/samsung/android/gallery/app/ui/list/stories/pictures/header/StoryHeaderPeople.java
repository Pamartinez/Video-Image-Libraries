package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import A.a;
import A2.d;
import A4.C0372g;
import N2.j;
import android.content.Context;
import android.database.Cursor;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryCategoryType;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHeaderPeople extends StoryHeaderBasic {
    private PeopleAdapter mAdapter;
    private RecyclerView mListView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PeopleAdapter extends RecyclerView.Adapter<StoryHeaderPeopleViewHolder> {
        private int mCount;
        private boolean mIsEnabled;
        private final ArrayList<MediaItem> mPeopleData;
        private SearchRelatedPeopleListener mSearchRelatedPeopleListener;

        public /* synthetic */ PeopleAdapter(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public void setPeopleData(ArrayList<MediaItem> arrayList) {
            this.mPeopleData.clear();
            this.mPeopleData.addAll(arrayList);
            setCount(arrayList.size());
        }

        /* access modifiers changed from: private */
        public void setSearchRelatedPeopleListener(SearchRelatedPeopleListener searchRelatedPeopleListener) {
            this.mSearchRelatedPeopleListener = searchRelatedPeopleListener;
        }

        public final MediaItem getItem(int i2) {
            if (i2 < 0 || i2 >= this.mPeopleData.size()) {
                return null;
            }
            return this.mPeopleData.get(i2);
        }

        public int getItemCount() {
            return this.mCount;
        }

        public StoryHeaderPeopleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new StoryHeaderPeopleViewHolder(C0086a.d(viewGroup, R.layout.story_pictures_face_item_layout, viewGroup, false));
        }

        public void setCount(int i2) {
            this.mCount = i2;
        }

        public void setEnabled(boolean z) {
            this.mIsEnabled = z;
            notifyDataSetChanged();
        }

        private PeopleAdapter() {
            this.mPeopleData = new ArrayList<>();
            this.mIsEnabled = true;
            this.mCount = 0;
        }

        public void onBindViewHolder(StoryHeaderPeopleViewHolder storyHeaderPeopleViewHolder, int i2) {
            storyHeaderPeopleViewHolder.bind(getItem(i2), i2, this.mSearchRelatedPeopleListener, this.mIsEnabled);
        }

        public void onViewRecycled(StoryHeaderPeopleViewHolder storyHeaderPeopleViewHolder) {
            storyHeaderPeopleViewHolder.recycle();
            super.onViewRecycled(storyHeaderPeopleViewHolder);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SearchRelatedPeopleListener {
    }

    private String createPeopleLocationKey(MediaItem mediaItem) {
        String subCategory = mediaItem.getSubCategory();
        String build = new UriBuilder(LocationKey.getSearchLocationKey("location://search/fileList/Category/People", subCategory)).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, "People").appendArg("sub", subCategory).appendArg("isNamed", String.valueOf(CreatureData.hasName(mediaItem))).appendArg("title", mediaItem.getTitle()).build();
        this.mBlackBoard.erase(build);
        return build;
    }

    private PeopleAdapter getAdapter() {
        if (this.mAdapter == null) {
            PeopleAdapter peopleAdapter = new PeopleAdapter(0);
            this.mAdapter = peopleAdapter;
            peopleAdapter.setSearchRelatedPeopleListener(new b(this));
        }
        return this.mAdapter;
    }

    public static Cursor getPeopleCursor(FileItemInterface fileItemInterface) {
        long j2;
        if (Features.isEnabled(Features.SUPPORT_STORIES_DATA_SEP11)) {
            j2 = DbCompat.storyApi().getStoryPeopleHeaderFileId(fileItemInterface.getAlbumID());
        } else {
            j2 = fileItemInterface.getFileId();
        }
        if (j2 > 0) {
            Log.d("StoryHeaderPeople", "load faceData=" + j2);
            return DbCompat.query(new QueryParams("mp://People").setFileId(j2));
        }
        StringBuilder j3 = j.j(j2, "invalid faceFileId=", " ,storyId=");
        j3.append(fileItemInterface.getAlbumID());
        Log.d("StoryHeaderPeople", j3.toString());
        return null;
    }

    private ViewGroup getPeopleRootView(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.story_pictures_category_people);
        if (viewGroup2 != null) {
            return viewGroup2;
        }
        ViewGroup viewGroup3 = (ViewGroup) C0086a.d(viewGroup, R.layout.story_pictures_header_related_people_layout, viewGroup, false);
        viewGroup.addView(viewGroup3);
        return viewGroup3;
    }

    private void initRecyclerView(Context context) {
        RecyclerView recyclerView = (RecyclerView) this.mRootView.findViewById(R.id.story_pictures_category_people_item_container);
        this.mListView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        this.mListView.setAdapter(getAdapter());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPeopleData$1() {
        Cursor peopleCursor;
        ArrayList arrayList = new ArrayList();
        try {
            peopleCursor = getPeopleCursor(this.mMediaItem);
            if (peopleCursor != null) {
                if (peopleCursor.getCount() > 0) {
                    while (peopleCursor.moveToNext()) {
                        arrayList.add(MediaItemBuilder.cloneCreatureItem(MediaItemBuilder.create(peopleCursor)));
                    }
                }
            }
            if (peopleCursor != null) {
                peopleCursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        ThreadUtil.postOnUiThread(new d(12, this, arrayList));
        return;
        throw th;
    }

    private void loadPeopleData(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        SimpleThreadPool.getInstance().execute(new C0372g(9, this));
    }

    private void setViewVisibility(boolean z) {
        int i2;
        ViewGroup viewGroup = this.mRootView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(viewGroup, i2);
    }

    /* access modifiers changed from: private */
    public void showSearchPeopleResult(MediaItem mediaItem) {
        this.mBlackBoard.post("command://MoveURL", createPeopleLocationKey(mediaItem));
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SHOW_PEOPLE_VIEW_STORY);
    }

    /* access modifiers changed from: private */
    /* renamed from: updatePeopleData */
    public void lambda$loadPeopleData$0(ArrayList<MediaItem> arrayList) {
        ThreadUtil.assertUiThread("updatePeopleData");
        if (!isDestroyed()) {
            this.mAdapter.setPeopleData(arrayList);
            if (this.mAdapter.getItemCount() != 0) {
                setViewVisibility(true);
                this.mAdapter.notifyDataSetChanged();
            } else {
                setViewVisibility(false);
            }
            a.w(new StringBuilder("updatePeopleData size="), this.mAdapter.getItemCount(), this.TAG);
            return;
        }
        Log.d(this.TAG, "view destroyed");
    }

    public void bindView(ViewGroup viewGroup) {
        this.mRootView = getPeopleRootView(viewGroup);
        initRecyclerView(viewGroup.getContext());
    }

    public void clear() {
        this.mAdapter = null;
        ViewUtils.removeAllViews(this.mListView);
        this.mListView.setAdapter((RecyclerView.Adapter) null);
        this.mListView.getRecycledViewPool().clear();
        setViewVisibility(false);
    }

    public int getContainerResId() {
        return R.id.stories_pictures_related_people_container;
    }

    public boolean isDestroyed() {
        if (this.mAdapter == null) {
            return true;
        }
        return false;
    }

    public void loadData(MediaItem mediaItem) {
        loadPeopleData(mediaItem);
    }

    public void setEnabled(boolean z) {
        this.mAdapter.setEnabled(z);
    }

    public boolean supported(MediaItem mediaItem) {
        if (MediaItemStory.getStoryCategoryType(mediaItem) == StoryCategoryType.PERSON.ordinal() || MediaItemStory.getStoryCategoryType(mediaItem) == StoryCategoryType.GROWTH.ordinal()) {
            return true;
        }
        return false;
    }
}
