package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import v7.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentsTypeIconUi extends ViewerObject {
    private View mContentsTypeIcon;

    private String appendTypeString(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return C0212a.B(str, " ", str2);
    }

    private void hideTypeIcon() {
        ViewUtils.setVisibility(this.mContentsTypeIcon, 8);
    }

    private boolean isLogVideo(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isLogVideo() || LocationKey.isColorCorrectView(this.mModel.getContainerModel().getLocationKey())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        resetTypeIcon(this.mModel.getMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        if (this.mContentsTypeIcon == null) {
            this.mContentsTypeIcon = objArr[0].findViewById(R.id.contents_type_icon);
        }
    }

    private void resetTypeIcon(MediaItem mediaItem) {
        Context context = this.mModel.getContext();
        String str = "";
        if (!(context == null || mediaItem == null)) {
            if (mediaItem.isRawImage()) {
                str = appendTypeString(str, context.getString(R.string.raw));
            }
            if (MediaItemUtil.isApv(mediaItem)) {
                str = appendTypeString(str, context.getString(R.string.apv));
            }
            if (isLogVideo(mediaItem)) {
                str = appendTypeString(str, context.getString(R.string.camera_capture_mode_log_video));
            }
        }
        if (TextUtils.isEmpty(str)) {
            hideTypeIcon();
        } else {
            showTypeIcon(str);
        }
    }

    private void showTypeIcon(String str) {
        View view = this.mContentsTypeIcon;
        if (view != null) {
            if (ViewUtils.isViewStub(view)) {
                this.mContentsTypeIcon = ((ViewStub) this.mContentsTypeIcon).inflate();
            }
            ViewUtils.setText((TextView) this.mContentsTypeIcon.findViewById(R.id.image_type_icon_text), str);
            ViewUtils.setVisibility(this.mContentsTypeIcon, 0);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.GROUP_CURRENT_ITEM_CHANGED, new f(this, 0));
        this.mActionInvoker.add(ViewerAction.DECOR_LAYOUT, new f(this, 1));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        resetTypeIcon(mediaItem);
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        resetTypeIcon(this.mModel.getMediaItem());
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        hideTypeIcon();
    }
}
