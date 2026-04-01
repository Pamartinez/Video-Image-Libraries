package com.samsung.android.gallery.app.ui.list.sharings.invitations;

import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class InvitationsAdapter<V extends IBaseListView> extends BaseListViewAdapter<V> {
    private final MediaData.OnDataChangeListener mInvitationDataChanged = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new a(InvitationsAdapter.this));
        }
    };
    private MediaData mInvitationMediaData;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ViewHolder extends ListViewHolder implements View.OnClickListener {
        public View mCardViewDivider;
        TextView mExpireDate;
        String mGroupId;
        TextView mRequestedTimeTextView;
        TextView mRequesterNameTextView;
        String mTitle;
        TextView mTitleTextView;

        public ViewHolder(View view, int i2) {
            super(view, i2);
        }

        private String getRequestedTimeText(long j2) {
            return getTimeText(DateFormat.is24HourFormat(InvitationsAdapter.this.getContext()), j2);
        }

        private String getTimeText(boolean z, long j2) {
            if (z) {
                return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(Long.valueOf(j2));
            }
            return DateUtils.formatDateTime(InvitationsAdapter.this.getContext(), j2, 1);
        }

        public void bind(MediaItem mediaItem) {
            super.bind(mediaItem);
            this.mGroupId = MediaItemMde.getInvitationGroupId(mediaItem);
            String invitationSpaceName = MediaItemMde.getInvitationSpaceName(mediaItem);
            this.mTitle = invitationSpaceName;
            this.mTitleTextView.setText(invitationSpaceName);
            this.mRequesterNameTextView.setText(MdeAlbumHelper.getRequesterNameText(MediaItemMde.getInvitationRequesterName(mediaItem)));
            this.mRequestedTimeTextView.setText(getRequestedTimeText(MediaItemMde.getInvitationRequestedTime(mediaItem)));
            this.mExpireDate.setText(MdeAlbumHelper.getExpiredTimeText(MediaItemMde.getInvitationExpiredTime(mediaItem)));
            this.itemView.setOnClickListener(this);
        }

        public final void bindView(View view) {
            this.mTitleTextView = (TextView) view.findViewById(R.id.title);
            this.mRequesterNameTextView = (TextView) view.findViewById(R.id.requester_name);
            this.mRequestedTimeTextView = (TextView) view.findViewById(R.id.requested_time);
            this.mExpireDate = (TextView) view.findViewById(R.id.expire_date);
            this.mCardViewDivider = view.findViewById(R.id.cardview_divider);
        }

        public View getListHorizontalDividerView() {
            return this.mCardViewDivider;
        }

        public void onClick(View view) {
            InvitationsAdapter.this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_VIEW_INVITATIONS_TOUCH_ITEM);
            InvitationsAdapter.this.mBlackBoard.publish("data://user/dialog/SharingInvitationsJoinDialog", new Object[]{this.mGroupId, this.mTitle});
            InvitationsAdapter.this.mBlackBoard.erase("data://user/dialog/SharingInvitationsJoinDialog");
        }

        public void recycle() {
            super.recycle();
            this.mTitleTextView.setText((CharSequence) null);
            this.mRequesterNameTextView.setText((CharSequence) null);
            this.mRequestedTimeTextView.setText((CharSequence) null);
            this.mExpireDate.setText((CharSequence) null);
            this.mGroupId = null;
        }
    }

    public InvitationsAdapter(V v, String str) {
        super(v, str);
        openInvitationMediaData();
    }

    private void closeInvitationMediaData() {
        MediaData mediaData = this.mInvitationMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mInvitationDataChanged);
            this.mInvitationMediaData.close();
            this.mInvitationMediaData = null;
        }
    }

    private int getItemViewLayoutId() {
        return R.layout.recycler_sharing_invitations_layout;
    }

    private void openInvitationMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackBoard).open("location://sharing/albums/invitations");
        this.mInvitationMediaData = open;
        open.register(this.mInvitationDataChanged);
    }

    private void setListHorizontalDividerVisibility(ListViewHolder listViewHolder, boolean z) {
        int i2;
        View listHorizontalDividerView = listViewHolder.getListHorizontalDividerView();
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        listHorizontalDividerView.setVisibility(i2);
    }

    public void destroy() {
        closeInvitationMediaData();
        super.destroy();
    }

    public int getItemViewType(int i2) {
        return 0;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        boolean z = false;
        if (i2 == 0 && getItemCount() == 1) {
            listViewHolder.setRoundMode(15);
        } else {
            if (i2 == 0) {
                listViewHolder.setRoundMode(3);
            } else if (i2 == getItemCount() - 1) {
                listViewHolder.setRoundMode(12);
            } else {
                listViewHolder.setRoundMode(0);
            }
            z = true;
        }
        setListHorizontalDividerVisibility(listViewHolder, z);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemViewLayoutId(), viewGroup, false), i2);
        }
        return super.onCreateViewHolder(viewGroup, i2);
    }
}
