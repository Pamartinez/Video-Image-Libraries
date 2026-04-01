package com.samsung.android.gallery.app.ui.list.suggestions;

import android.content.Context;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.suggestions.ISuggestionsView;
import com.samsung.android.gallery.database.dbtype.SuggestedType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.module.suggested.SuggestedLocalUpdater;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SuggestionsPresenter<V extends ISuggestionsView> extends BaseListPresenter<V> {
    private static final int TITLE_RES;
    private SuggestedLocalUpdater mLocalUpdater;
    private final Map<Integer, String> mTargetLocationKeyMap = new HashMap<Integer, String>() {
        {
            put(Integer.valueOf(SuggestedType.CLEANOUT.toInt()), "location://cleanOut/fileList");
            put(Integer.valueOf(SuggestedType.CLEANOUT_DUPLICATED_IMAGE.toInt()), "location://cleanOut/duplicated/fileList");
            put(Integer.valueOf(SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.toInt()), "location://cleanOut/motionPhotoClip/fileList");
            put(Integer.valueOf(SuggestedType.CLEANOUT_BURST_SIMILAR.toInt()), "location://cleanOut/burstSimilar/fileList");
            put(Integer.valueOf(SuggestedType.PORTRAIT.toInt()), "location://portrait/fileList");
            put(Integer.valueOf(SuggestedType.HIGHLIGHT.toInt()), "location://highlight/fileList");
            put(Integer.valueOf(SuggestedType.REMASTER.toInt()), "location://revitalized/fileList");
        }
    };

    static {
        int i2;
        if (PreferenceFeatures.OneUi7x.IS_ONE_UI_70) {
            i2 = R.string.bottom_tab_drawer_clean_out;
        } else {
            i2 = R.string.bottom_tab_drawer_suggestions;
        }
        TITLE_RES = i2;
    }

    public SuggestionsPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$updateAutoItemStatus$1(MediaItem mediaItem, ThreadPool.JobContext jobContext) {
        int i2;
        int type = MediaItemSuggest.getType(mediaItem);
        if (type == SuggestedType.REMASTER.toInt()) {
            i2 = 13;
        } else {
            i2 = MediaItemStory.getStoryType(mediaItem);
        }
        SuggestedHelper.getInstance().updateAutoItem(i2, 1, type);
        return Boolean.TRUE;
    }

    private void updateNewBadge(int i2, MediaItem mediaItem) {
        SuggestedType type = SuggestedType.getType(MediaItemSuggest.getType(mediaItem));
        if (type != null) {
            if (SuggestedType.CLEANOUT_MOTION_PHOTO_CLIP.equals(type)) {
                PreferenceCache.CleanOutMotionPhotoClipCount.setInt(mediaItem.getCount());
            } else if (!SuggestedHelper.updatePreferenceWithTab(type, MediaItemSuggest.getCleanOutDeleteType(mediaItem))) {
                return;
            }
            Optional.ofNullable((SuggestionsViewAdapter) ((ISuggestionsView) this.mView).getAdapter()).ifPresent(new a(i2));
        }
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(TITLE_RES);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1032) {
            forceReloadData();
            return true;
        } else if (i2 != 3017) {
            return super.handleEvent(eventMessage);
        } else {
            BaseListViewAdapter adapter = ((ISuggestionsView) this.mView).getAdapter();
            if (adapter == null) {
                return true;
            }
            adapter.sizeDownThumbnail(-1);
            return true;
        }
    }

    public void launchSuggestionPictures(int i2, MediaItem mediaItem) {
        String str = this.mTargetLocationKeyMap.get(Integer.valueOf(MediaItemSuggest.getType(mediaItem)));
        if (str != null) {
            UriBuilder appendArg = new UriBuilder(str).appendArg("id", mediaItem.getAlbumID()).appendArg(Message.KEY_POSITION, i2);
            if (MediaItemSuggest.isCleanOut(mediaItem)) {
                appendArg.appendArg("delete_type", MediaItemSuggest.getCleanOutDeleteType(mediaItem));
            }
            String build = appendArg.build();
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "launchSuggestionPictures " + build);
            this.mBlackboard.post("command://MoveURL", build);
        }
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        updateNewBadge(i2, mediaItem);
        launchSuggestionPictures(i2, mediaItem);
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        SuggestedLocalUpdater suggestedLocalUpdater = new SuggestedLocalUpdater(getContext(), this.mBlackboard);
        this.mLocalUpdater = suggestedLocalUpdater;
        suggestedLocalUpdater.onCreate();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        this.mLocalUpdater.onDestroy();
    }

    public void updateAutoItemStatus(MediaItem mediaItem) {
        ThreadPool.getInstance().submit(new b(mediaItem));
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle(TITLE_RES);
        toolbar.setSubtitle((CharSequence) null);
        setNavigationUpButton(toolbar);
    }
}
