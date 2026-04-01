package com.samsung.android.gallery.app.ui.list.sharings;

import Ab.b;
import H3.l;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.sharing.request.RequestSync;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.app.ui.list.hover.HoverParams;
import com.samsung.android.gallery.app.ui.list.sharings.ISharingsView;
import com.samsung.android.gallery.module.data.MdeData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingsViewAdapter<V extends ISharingsView> extends BaseListViewAdapter<V> {
    private final boolean SUPPORT_GROUP_RAW_QUERY = Features.isEnabled(Features.SUPPORT_SHARED_GROUP_RAW_QUERY);
    private final MediaData.OnDataChangeListener mGroupDataChanged = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new l(21, SharingsViewAdapter.this));
        }
    };
    private MediaData mGroupMediaData;
    private int mInvitationHeight = -1;
    private int mItemHeight = -1;
    private ViewGroup.MarginLayoutParams mLP;
    private int mLastInvitationHeight = -1;

    public SharingsViewAdapter(V v, String str) {
        super(v, str);
        openGroupMediaData();
    }

    private boolean bindGroupInfo(ListViewHolder listViewHolder, MediaItem mediaItem) {
        MediaData mediaData;
        if (!this.SUPPORT_GROUP_RAW_QUERY && (mediaData = this.mGroupMediaData) != null && mediaData.isDataAvailable() && mediaItem != null) {
            mediaItem = this.mGroupMediaData.readByKey(MediaItemMde.getGroupId(mediaItem));
        }
        setGroupInfoView(listViewHolder, mediaItem);
        return true;
    }

    private void closeGroupMediaData() {
        MediaData mediaData = this.mGroupMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mGroupDataChanged);
            this.mGroupMediaData.close();
            this.mGroupMediaData = null;
        }
    }

    private int getGroupCount(MediaItem mediaItem) {
        int i2;
        if (!Features.isEnabled(Features.SUPPORT_SHARED_GROUP_RAW_QUERY) || (i2 = MdeData.of(mediaItem).groupMemberCount) <= 0) {
            return mediaItem.getCount();
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ListViewHolder listViewHolder, int i2) {
        this.mLP = (ViewGroup.MarginLayoutParams) listViewHolder.itemView.getLayoutParams();
        if (!isInvitationViewType(listViewHolder.getViewType())) {
            this.mItemHeight = listViewHolder.itemView.getHeight() + this.mLP.bottomMargin;
        } else if (isLastInvitation(i2)) {
            this.mLastInvitationHeight = listViewHolder.itemView.getHeight() + this.mLP.bottomMargin;
        } else {
            this.mInvitationHeight = listViewHolder.itemView.getHeight() + this.mLP.bottomMargin;
        }
    }

    private void openGroupMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackBoard).open("location://sharing/groups");
        this.mGroupMediaData = open;
        open.register(this.mGroupDataChanged);
    }

    private void setGroupInfoView(ListViewHolder listViewHolder, MediaItem mediaItem) {
        boolean z;
        if (mediaItem != null) {
            setGroupCount(listViewHolder, getGroupCount(mediaItem));
            if (PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
                View decoView = listViewHolder.getDecoView(45);
                if (MediaItemMde.getAlbumExpiry(mediaItem) != 0) {
                    z = true;
                } else {
                    z = false;
                }
                ViewUtils.setVisibleOrGone(decoView, z);
            }
        }
    }

    public void bindThumbnail(ListViewHolder listViewHolder, int i2, int i7, MediaItem mediaItem) {
        super.bindThumbnail(listViewHolder, i2, i7, mediaItem);
        bindGroupInfo(listViewHolder, mediaItem);
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2, int i7) {
        if (!super.bindViewHolderOnScrollIdle(listViewHolder, i2, i7) || !bindGroupInfo(listViewHolder, getMediaItemFromCache(i2))) {
            return false;
        }
        return true;
    }

    public void destroy() {
        closeGroupMediaData();
        super.destroy();
    }

    public Bitmap getBrokenMediaItem(MediaItem mediaItem) {
        if (!TextUtils.isEmpty(mediaItem.getPath())) {
            return null;
        }
        if (mediaItem.getCount() != 0 && Features.isEnabled(Features.SUPPORT_DOWNLOAD_BROKEN_SHARED_COVER)) {
            this.mBlackBoard.post("data://download_shared_cover", mediaItem);
        }
        return ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    public int getInvitationDataCount() {
        int i2 = 0;
        if (!PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS || this.mMediaData == null) {
            return 0;
        }
        int i7 = 0;
        while (i2 < this.mMediaData.getCount() && isInvitation(i2)) {
            i7++;
            i2++;
        }
        return i7;
    }

    public int getItemHeight(int i2) {
        if (i2 < getInvitationDataCount() - 1) {
            return this.mInvitationHeight;
        }
        if (isLastInvitation(i2)) {
            return this.mLastInvitationHeight;
        }
        return this.mItemHeight;
    }

    public int getItemViewType(int i2) {
        return isInvitation(i2) ? 1 : 0;
    }

    public int getMaxScroll() {
        int invitationDataCount = getInvitationDataCount();
        return ((getItemCount() - invitationDataCount) * this.mItemHeight) + ((invitationDataCount - 1) * this.mInvitationHeight) + this.mLastInvitationHeight;
    }

    public int getNextRowIndex(int i2, int i7) {
        return Math.min(getGridSize() + i2, i7 - 1);
    }

    public int getPrevRowIndex(int i2) {
        return Math.max(0, i2 - getGridSize());
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public boolean isInvitation(int i2) {
        return MediaItemMde.isInvitation(this.mMediaData.read(i2));
    }

    public boolean isInvitationViewType(int i2) {
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    public boolean isLastInvitation(int i2) {
        if (getInvitationDataCount() - 1 == i2) {
            return true;
        }
        return false;
    }

    public void onHoverInternal(ListViewHolder listViewHolder, MotionEvent motionEvent) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (MediaItemMde.getUnreadCount(mediaItem) > 0) {
            String spaceId = MediaItemMde.getSpaceId(mediaItem);
            if (!TextUtils.isEmpty(spaceId)) {
                new RequestSharedAlbumCmd().execute(((ISharingsView) this.mView).getPresenter(), RequestCmdType.REQUEST_SYNC, RequestSync.Types.SpaceItem, spaceId);
            }
        }
        ((HoverHandler) this.mHoverHandler.get()).onHover(listViewHolder, motionEvent, new HoverParams.HoverParamsBuilder(getLocationKey()).setContainer((ViewGroup) ((ISharingsView) this.mView).getView()).setItem(listViewHolder.getMediaItem()).setDataPosition(getMediaItemPosition(listViewHolder.getViewPosition())).setAlbumType(true).setHideOption(false).build());
    }

    public void setGroupCount(ListViewHolder listViewHolder, int i2) {
        ViewUtils.setText(listViewHolder.getCountView(), StringCompat.toReadableCount(i2));
    }

    public boolean startSelect(int i2) {
        return false;
    }

    public boolean supportHover() {
        return true;
    }

    public boolean supportVideoPreview() {
        return false;
    }

    public void updateInvitationBottomMargin(ListViewHolder listViewHolder, int i2) {
        if (isInvitationViewType(listViewHolder.getViewType())) {
            ((SharingsInvitationViewHolder) listViewHolder).updateMargin(isLastInvitation(i2));
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        updateInvitationBottomMargin(listViewHolder, i2);
        listViewHolder.itemView.post(new b((Object) this, (Object) listViewHolder, i2, 21));
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (!PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS || i2 != 1) {
            return SharingsItemViewHolder.createViewHolder(viewGroup, i2);
        }
        return SharingsInvitationViewHolder.createViewHolder(viewGroup, i2);
    }
}
