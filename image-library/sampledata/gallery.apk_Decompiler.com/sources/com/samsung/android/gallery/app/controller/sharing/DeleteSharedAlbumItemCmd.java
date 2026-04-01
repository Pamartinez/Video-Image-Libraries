package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import S3.d;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteSharedAlbumItemCmd extends BaseCommand {
    private boolean mFamilySharedAlbum;
    private String mGroupId;
    private MediaItem[] mItems;

    private String getDialogEventId(String str, boolean z) {
        if (AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_PICTURE.toString().equals(str)) {
            if (z) {
                return AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_IMAGE_OK.toString();
            }
            return AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_IMAGE_CANCEL.toString();
        } else if (AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_VIDEO.toString().equals(str)) {
            if (z) {
                return AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_VIDEO_OK.toString();
            }
            return AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_VIDEO_CANCEL.toString();
        } else if (z) {
            return AnalyticsEventId.EVENT_SHARED_DETAIL_REMOVE_FROM_ALBUM_DIALOG_OK.toString();
        } else {
            return AnalyticsEventId.EVENT_SHARED_DETAIL_REMOVE_FROM_ALBUM_DIALOG_CANCEL.toString();
        }
    }

    private String getOption1(Context context) {
        if (this.mFamilySharedAlbum) {
            return context.getString(R.string.move_to_trash);
        }
        return context.getString(R.string.remove);
    }

    private RequestCmdType getRequestCmdType() {
        if (this.mFamilySharedAlbum) {
            return RequestCmdType.REQUEST_MOVE_TO_TRASH;
        }
        return RequestCmdType.REQUEST_DELETE_CONTENTS;
    }

    private String getTitle(Context context) {
        int i2;
        int i7;
        int i8;
        MediaItem[] mediaItemArr = this.mItems;
        int length = mediaItemArr.length;
        int count = (int) Stream.of(mediaItemArr).filter(new d(1)).count();
        int i10 = length - count;
        if (length == 1 && this.mFamilySharedAlbum) {
            if (i10 > 0) {
                i8 = R.string.move_this_image_to_the_trash;
            } else {
                i8 = R.string.move_this_video_to_the_trash;
            }
            return context.getString(i8);
        } else if (length == i10) {
            Resources resources = context.getResources();
            if (this.mFamilySharedAlbum) {
                i7 = R.plurals.move_images_to_the_trash_plural;
            } else {
                i7 = R.plurals.remove_images_from_shared_album_plural;
            }
            return resources.getQuantityString(i7, length, new Object[]{Integer.valueOf(length)});
        } else if (length == count) {
            Resources resources2 = context.getResources();
            if (this.mFamilySharedAlbum) {
                i2 = R.plurals.move_videos_to_the_trash_plural;
            } else {
                i2 = R.plurals.remove_videos_from_shared_album_plural;
            }
            return resources2.getQuantityString(i2, length, new Object[]{Integer.valueOf(length)});
        } else if (this.mFamilySharedAlbum) {
            return context.getResources().getQuantityString(R.plurals.move_items_to_the_trash_plural, length, new Object[]{Integer.valueOf(length)});
        } else {
            return context.getString(R.string.remove_items_from_this_shared_album, new Object[]{Integer.valueOf(this.mItems.length)});
        }
    }

    /* access modifiers changed from: private */
    public void requestDeleteSharedAlbumItem(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2 = 0;
        if (arrayList != null) {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        if (i2 == 1) {
            getBlackboard().postEvent(EventMessage.obtain(1057));
            getBlackboard().postEvent(EventMessage.obtain(3015));
            ArrayList arrayList2 = new ArrayList();
            Collections.addAll(arrayList2, this.mItems);
            new RequestSharedAlbumCmd().execute(eventContext, getRequestCmdType(), arrayList2);
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_SHARING_REMOVE_FROM_ALBUM.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z;
        Context context = getContext();
        MediaItem[] mediaItemArr = objArr[1];
        this.mItems = mediaItemArr;
        if (objArr.length > 2) {
            this.mGroupId = objArr[2];
        } else if (mediaItemArr.length == 1) {
            this.mGroupId = MediaItemMde.getGroupId(mediaItemArr[0]);
        }
        if (TextUtils.isEmpty(this.mGroupId)) {
            this.mGroupId = ArgumentsUtil.getArgValue(eventContext.getLocationKey(), "groupId");
        }
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) || !MdeGroupHelper.isSAFamilyGroup(this.mGroupId)) {
            z = false;
        } else {
            z = true;
        }
        this.mFamilySharedAlbum = z;
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", getTitle(context)).appendArg("option1", getOption1(context)).appendArg("screenId", getScreenId()).appendArg("option1EventId", getDialogEventId(getScreenId(), true)).appendArg("cancelEventId", getDialogEventId(getScreenId(), false)).build()).setOnDataCompleteListener(new y(16, this)).start();
    }
}
