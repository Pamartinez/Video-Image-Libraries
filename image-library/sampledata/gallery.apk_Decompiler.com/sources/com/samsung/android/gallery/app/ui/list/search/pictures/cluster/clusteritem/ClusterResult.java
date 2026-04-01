package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterHeaderView;
import com.samsung.android.gallery.database.dal.abstraction.FilterResultsKeySet;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.album.AlbumInfo;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.scsp.media.file.FileApiContract;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import o5.C0496a;
import o6.p;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClusterResult implements IClusterResult {
    private final boolean SEARCH_CLUSTER_CAROUSEL = PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
    protected final String TAG = getClass().getSimpleName();
    protected ClusterResultViewAdapter mClusterViewAdapter;
    protected final EventContext mHandler;
    protected boolean mIsActive = true;
    protected boolean mIsEnabled = true;
    protected boolean mItemClicked;
    protected final View mMainLayout;
    private OnUiUpdateListener mOnUiUpdateListener;
    protected final View mParent;
    protected ClusterResultPresenter mPresenter;
    protected GalleryListView mRecyclerView;
    protected final TextView mTextView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnUiUpdateListener {
    }

    public ClusterResult(View view, EventContext eventContext) {
        this.mParent = view;
        View inflateViewStub = ViewUtils.inflateViewStub(view.findViewById(getViewStubId()));
        this.mMainLayout = inflateViewStub;
        TextView textView = (TextView) inflateViewStub.findViewById(R.id.search_cluster_divider_count);
        this.mTextView = textView;
        ViewUtils.setVisibleOrGone(textView, false);
        this.mPresenter = createPresenter(eventContext, this);
        this.mHandler = eventContext;
    }

    private UriBuilder getBuilder() {
        return new UriBuilder("location://search/fileList/KeywordClusterPictures").appendArg("collect_type", SearchWordCollector.Type.KEYWORD_INPUT.toString()).appendArg("searchToolbar", false).appendArg("search_keyword_pictures_only", true).appendArg("search_cluster_result_type", ClusterResultType.MY_TAGS.name());
    }

    private String getClusterPicturesTarget(String str, String str2) {
        return getBuilder().appendArg("sub", str).appendArg("title", str).appendArg("term", str2).appendArg("collect_keyword", str).build();
    }

    private String getQueryValue() {
        if (this.SEARCH_CLUSTER_CAROUSEL) {
            return this.mPresenter.getCarouselClusterMap().get(ClusterResultType.OCRS).replace("'", "");
        }
        return this.mPresenter.getClusterKeyNames().replace("'", "");
    }

    private String getRequestKeyWithType(MediaItem mediaItem, String str) {
        HashSet<Integer> subAlbumIds;
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !mediaItem.isMergedAlbum()) {
            return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(str, "id", String.valueOf(mediaItem.getAlbumID())), "type", String.valueOf(mediaItem.getAlbumType().toInt()));
        }
        MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
        ArrayList arrayList = new ArrayList();
        for (MediaItem albumID : albumsInFolder) {
            arrayList.add(Integer.valueOf(albumID.getAlbumID()));
        }
        if (arrayList.size() == 0 && (subAlbumIds = AlbumInfo.getSubAlbumIds(mediaItem.getAlbumID())) != null) {
            arrayList.addAll(subAlbumIds);
        }
        return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(str, "mergedAlbumId", String.valueOf(mediaItem.getAlbumID())), "ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
    }

    private UriBuilder getTargetBuilder(MediaItem mediaItem, String str) {
        int tagType = mediaItem.getTagType();
        String category = mediaItem.getCategory();
        String subCategory = mediaItem.getSubCategory();
        String searchLocationKey = LocationKey.getSearchLocationKey(str, subCategory);
        String title = mediaItem.getTitle();
        this.mPresenter.getBlackboard().erase(searchLocationKey);
        UriBuilder appendArg = new UriBuilder(searchLocationKey).appendArg(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, category).appendArg("sub", subCategory).appendArg("title", title).appendArg("term", FilterResultsKeySet.getField(category, subCategory, tagType));
        appendArg.appendArg("collect_keyword", SearchWordCollector.getCollectedKeyword(title, subCategory, category)).appendArg("collect_type", SearchWordCollector.getVisualSearchType(category));
        VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString(), category, subCategory, false);
        return appendArg;
    }

    private boolean isHorizontalLayout() {
        if (getType() == ClusterResultType.CAROUSELS) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onExpansionArrowClicked(View view) {
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter != null) {
            clusterResultPresenter.getBlackboard().post("command://MoveURL", getCategoryLocationKey());
        }
    }

    private void setViewMoreGui() {
        int i2;
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            boolean z = true;
            if (getType() == ClusterResultType.CAROUSELS) {
                i2 = 1;
            } else {
                i2 = 10;
            }
            if (getMediaData().getCount() <= i2) {
                z = false;
            }
            TextView textView = (TextView) this.mMainLayout.findViewById(R.id.view_all);
            ImageView imageView = (ImageView) this.mMainLayout.findViewById(R.id.search_cluster_divider_arrow);
            ViewUtils.setVisibleOrGone(imageView, z);
            ViewUtils.setVisibleOrGone(textView, false);
            if (z) {
                textView.setOnClickListener(new C0496a(28, this));
                imageView.setOnClickListener(new C0496a(28, this));
            }
        }
    }

    private void updateTitleInfo() {
        setTitle();
        setViewMoreGui();
    }

    public ClusterResultViewAdapter createAdapter() {
        return new ClusterResultViewAdapter(this, this.mHandler);
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this.mRecyclerView.getContext(), isHorizontalLayout() ^ true ? 1 : 0, false);
    }

    public ClusterResultPresenter createPresenter(EventContext eventContext, IClusterResult iClusterResult) {
        return new ClusterResultPresenter(eventContext, iClusterResult);
    }

    public ClusterResultViewAdapter getAdapter() {
        return this.mClusterViewAdapter;
    }

    public abstract String getCategoryLocationKey();

    public int getClusterItemListViewPosition(int i2) {
        return this.mClusterViewAdapter.getViewPosition(i2);
    }

    public abstract String getClusterItemLocationKey(ListViewHolder listViewHolder, MediaItem mediaItem);

    public int getItemType() {
        return getType().ordinal();
    }

    public GalleryListView getListView() {
        if (this.mRecyclerView == null) {
            GalleryListView galleryListView = (GalleryListView) this.mMainLayout.findViewById(R.id.search_result_cluster_recycler_view);
            this.mRecyclerView = galleryListView;
            galleryListView.setLayoutManager(createLayoutManager());
        }
        return this.mRecyclerView;
    }

    public int getMaxItemCount() {
        if (this.SEARCH_CLUSTER_CAROUSEL) {
            return 15;
        }
        return 10;
    }

    public MediaData getMediaData() {
        return this.mPresenter.getMediaData();
    }

    public String getTargetAlbum(MediaItem mediaItem) {
        UriBuilder uriBuilder = new UriBuilder(getRequestKeyWithType(mediaItem, "location://albums/fileList/fromSearchCluster"));
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            if (BucketUtils.isFavourite(mediaItem.getAlbumID())) {
                uriBuilder.appendArg("with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
            }
            uriBuilder.appendArg("type", String.valueOf(mediaItem.getAlbumType().toInt()));
        }
        uriBuilder.appendArg("count", String.valueOf(mediaItem.getCount())).appendArg("cluster_album_item", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).appendArg("supportSearchIcon", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).appendArg("album_split_blocked", true);
        return uriBuilder.build();
    }

    public String getTargetLocation(MediaItem mediaItem) {
        String build = getTargetBuilder(mediaItem, "location://search/fileList/Category/Location").build();
        publishMapInitialLocation(mediaItem);
        return build;
    }

    public String getTargetOcr() {
        return getClusterPicturesTarget(getQueryValue(), "ocrtext");
    }

    public String getTargetPdc(MediaItem mediaItem) {
        return getClusterPicturesTarget(mediaItem.getTitle(), "pdc_cluster");
    }

    public String getTargetPeople(MediaItem mediaItem) {
        UriBuilder targetBuilder = getTargetBuilder(mediaItem, "location://search/fileList/Category/People");
        if (mediaItem.isPeople()) {
            if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_PEOPLE_FACE_SCORE) {
                this.mPresenter.getBlackboard().publish("data:///CreatureHeaderItem", mediaItem);
            }
            targetBuilder.appendArg("isNamed", String.valueOf(CreatureData.hasName(mediaItem)));
            targetBuilder.appendArg("people_from_visual_search", true);
        }
        return targetBuilder.build();
    }

    public String getTargetPet(MediaItem mediaItem) {
        UriBuilder targetBuilder = getTargetBuilder(mediaItem, "location://search/fileList/Category/Pet");
        if (mediaItem.isPet()) {
            this.mPresenter.getBlackboard().publish("data:///CreatureHeaderItem", mediaItem);
            targetBuilder.appendArg("isNamed", String.valueOf(CreatureData.hasName(mediaItem)));
            targetBuilder.appendArg("people_from_visual_search", true);
        }
        return targetBuilder.build();
    }

    public String getTargetUserTag(MediaItem mediaItem) {
        return getClusterPicturesTarget(mediaItem.getTitle(), "key_word");
    }

    public abstract ClusterResultType getType();

    public abstract int getViewStubId();

    public boolean handleEvent(EventMessage eventMessage) {
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter == null || !clusterResultPresenter.handleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    public void initListView() {
        GalleryListView listView = getListView();
        this.mRecyclerView = listView;
        listView.setNestedScrollingEnabled(false);
        ClusterResultViewAdapter clusterResultViewAdapter = this.mClusterViewAdapter;
        if (clusterResultViewAdapter == null) {
            ClusterResultViewAdapter createAdapter = createAdapter();
            this.mClusterViewAdapter = createAdapter;
            this.mRecyclerView.setAdapter(createAdapter);
            return;
        }
        clusterResultViewAdapter.notifyDataChanged((Runnable) null);
    }

    public boolean isItemClicked() {
        return this.mItemClicked;
    }

    public boolean isViewActive() {
        if (!ViewUtils.isVisible(this.mMainLayout) || !ViewUtils.isVisible(this.mParent) || !this.mIsActive) {
            return false;
        }
        return true;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.ViewClusterResultMenuOption) || ViewUtils.isTouchedOnView(this.mRecyclerView, motionEvent) || ViewUtils.isTouchedOnView(this.mMainLayout.findViewById(R.id.view_all), motionEvent)) {
            return false;
        }
        return true;
    }

    public void loadCluster(String str, ArrayList<String> arrayList) {
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter != null) {
            clusterResultPresenter.loadCluster(str, arrayList);
        }
    }

    public void loadClusterIncludeCarousel(ArrayList<HashMap<ClusterResultType, ArrayList<String>>> arrayList) {
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter != null) {
            clusterResultPresenter.loadClusterIncludeCarousel(arrayList);
        }
    }

    public void onClusterItemClicked(ListViewHolder listViewHolder, MediaItem mediaItem, int i2) {
        boolean z;
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter == null || !this.mIsEnabled) {
            String str = this.TAG;
            if (clusterResultPresenter == null) {
                z = true;
            } else {
                z = false;
            }
            Log.w((CharSequence) str, "onClusterItemClicked failed : ", Boolean.valueOf(z), Boolean.valueOf(this.mIsEnabled));
            return;
        }
        clusterResultPresenter.getBlackboard().post("command://MoveURL", getClusterItemLocationKey(listViewHolder, mediaItem));
    }

    public void onCreate() {
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter != null) {
            clusterResultPresenter.onCreate();
        }
    }

    public void onDataChangedOnUi(MediaData mediaData) {
        boolean z;
        if (this.mPresenter != null) {
            initListView();
            updateTitleInfo();
            if (mediaData.getCount() > 0) {
                z = true;
            } else {
                z = false;
            }
            setVisibility(z);
            OnUiUpdateListener onUiUpdateListener = this.mOnUiUpdateListener;
            if (onUiUpdateListener != null) {
                ((SearchClusterHeaderView) ((p) onUiUpdateListener).e).lambda$loadClusterData$0(getType());
            }
        }
    }

    public void onDestroy() {
        this.mItemClicked = false;
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView != null) {
            galleryListView.destroy();
            this.mRecyclerView = null;
        }
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter != null) {
            clusterResultPresenter.destroy();
            this.mPresenter = null;
        }
    }

    public void onPause() {
        this.mIsActive = false;
    }

    public void onResume() {
        this.mIsActive = true;
    }

    public void publishMapInitialLocation(MediaItem mediaItem) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("latitude", mediaItem.getLatitude());
            jSONObject.put("longitude", mediaItem.getLongitude());
            jSONObject.put("entryItem", mediaItem.getFileId());
            jSONObject.put(FileApiContract.Parameter.PATH, mediaItem.getPath());
            this.mPresenter.getBlackboard().publish("data://user/map/InitialLocation", jSONObject);
        } catch (Exception e) {
            Log.se(this.TAG, e.getMessage());
        }
    }

    public void resetItemClicked() {
        this.mItemClicked = false;
    }

    public void setEnabled(boolean z) {
        float f;
        this.mIsEnabled = z;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.ViewClusterResultMenuOption)) {
            View view = this.mMainLayout;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.4f;
            }
            ViewUtils.setAlpha(view, f);
        }
    }

    public void setEntity(ClusterResultsEntity clusterResultsEntity) {
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        if (clusterResultPresenter != null) {
            clusterResultPresenter.setEntity(clusterResultsEntity);
        }
    }

    public void setOnUiUpdateListener(OnUiUpdateListener onUiUpdateListener) {
        this.mOnUiUpdateListener = onUiUpdateListener;
    }

    public void setTitle() {
        ViewUtils.setText((TextView) this.mMainLayout.findViewById(R.id.search_cluster_divider_title), getType().titleResId);
    }

    public void setVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mMainLayout, z);
    }

    public boolean supportItemSelection() {
        return false;
    }

    public void updateBottomMargin(int i2) {
        ViewMarginUtils.setBottomMargin(this.mMainLayout, i2);
    }

    public void handleOrientationChange(int i2) {
    }
}
