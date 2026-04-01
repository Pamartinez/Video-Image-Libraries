package com.samsung.android.gallery.app.ui.list.sharings;

import H.a;
import J5.e;
import L5.c;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewholders.CheckboxListViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SharingsInvitationViewHolder extends CheckboxListViewHolder {
    protected TextView mAccept;
    protected TextView mDecline;
    protected TextView mExpireDate;
    protected TextView mHostName;
    private ImageView mInvitationHostIcon;
    protected TextView mTitleText;

    public SharingsInvitationViewHolder(View view, int i2) {
        super(view, i2);
        this.mDecline.setOnClickListener(new c(this, 0));
        this.mAccept.setOnClickListener(new c(this, 1));
    }

    public static ListViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        return new SharingsInvitationViewHolder(C0086a.d(viewGroup, R.layout.sharing_invitation_layout, viewGroup, false), i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        onItemClickInternal(100);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(View view) {
        onItemClickInternal(101);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setThumbnailOnHostIcon$3(Bitmap bitmap, ImageView imageView) {
        imageView.setBackgroundResource(R.color.sharingv2_invitation_icon_bg_color);
        imageView.setImageBitmap(bitmap);
    }

    private void resetInvitationHostIcon() {
        this.mInvitationHostIcon.setImageBitmap((Bitmap) null);
        this.mInvitationHostIcon.setBackgroundResource(R.drawable.sharingv2_invitation_icon_bg);
    }

    /* access modifiers changed from: private */
    /* renamed from: setThumbnailOnHostIcon */
    public void lambda$bindImage$2(Bitmap bitmap) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && !TextUtils.isEmpty(mediaItem.getPath()) && bitmap != null) {
            Optional.ofNullable(this.mInvitationHostIcon).ifPresent(new e(bitmap, 3));
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mTitleText.setText(MediaItemMde.getInvitationSpaceName(mediaItem));
        this.mHostName.setText(MdeAlbumHelper.getRequesterNameText(MediaItemMde.getInvitationRequesterName(mediaItem)));
        this.mExpireDate.setText(MdeAlbumHelper.getExpiredTimeText(MediaItemMde.getInvitationExpiredTime(mediaItem)));
    }

    public void bindImage(Bitmap bitmap) {
        ThreadUtil.runOnUiThread(new a(24, this, bitmap));
    }

    public void bindView(View view) {
        super.bindView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.invitation_icon_view);
        this.mInvitationHostIcon = imageView;
        ViewUtils.setViewShape(imageView, 0, 0.0f);
        this.mHostName = (TextView) view.findViewById(R.id.host);
        this.mExpireDate = (TextView) view.findViewById(R.id.expire_date);
        this.mAccept = (TextView) view.findViewById(R.id.accept);
        this.mDecline = (TextView) view.findViewById(R.id.decline);
        this.mTitleText = (TextView) view.findViewById(R.id.title);
    }

    public ImageView getImage() {
        return this.mInvitationHostIcon;
    }

    public ThumbKind getThumbKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public void recycle() {
        super.recycle();
        this.mTitleText.setText((CharSequence) null);
        this.mHostName.setText((CharSequence) null);
        this.mExpireDate.setText((CharSequence) null);
        resetInvitationHostIcon();
    }

    public void setImageUid(Object obj) {
        this.mInvitationHostIcon.setTag(obj);
    }

    public void updateMargin(boolean z) {
        int i2;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
        Resources resources = this.itemView.getContext().getResources();
        if (z) {
            i2 = R.dimen.sharingv2_last_invitation_bottom_margin;
        } else {
            i2 = R.dimen.shared_view_item_vertical_bottom_space;
        }
        layoutParams.bottomMargin = resources.getDimensionPixelOffset(i2);
        this.itemView.setLayoutParams(layoutParams);
    }
}
