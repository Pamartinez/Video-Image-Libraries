package com.samsung.android.gallery.app.controller.sharing;

import C3.C0392b;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.ChangeCoverCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.abstraction.CoverPickType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeAuthHelper;
import com.samsung.android.gallery.module.mde.MdeSocialHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeSharedAlbumCoverCmd extends ChangeCoverCmd {
    private String mMdeSpaceId;

    public static String getAlbumCoverId(Blackboard blackboard, String str, String str2) {
        MediaData open;
        int i2;
        try {
            open = MediaDataFactory.getInstance(blackboard).open("location://sharing/albums/fileList", true);
            if (!TextUtils.isEmpty(str)) {
                i2 = open.findPositionBy((Predicate<MediaItem>) new C0392b(str, 12));
            } else if (str2 != null) {
                i2 = open.findPositionBy((Predicate<MediaItem>) new C0392b(str2, 11));
            } else {
                i2 = -1;
            }
            String itemId = MediaItemMde.getItemId(open.read(Math.max(i2, 0)));
            open.close();
            return itemId;
        } catch (Exception e) {
            Log.e("ChangeSharedAlbumCoverCmd", e.getMessage());
            return "";
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void changeCover(MediaItem mediaItem, String str) {
        getBlackboard().postEvent(EventMessage.obtain(6019, mediaItem));
        new RequestSharedAlbumCmd().execute(getHandler(), RequestCmdType.REQUEST_CHANGE_SPACE_COVER, mediaItem, str);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_CHANGE_COVER_IMAGE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem;
        Context context = getContext();
        if (!MdeSocialHelper.isSocialServiceEnabled() || MdeAuthHelper.isReadyToUseShareService()) {
            boolean isNetworkConnected = NetworkUtils.isNetworkConnected(context);
            boolean isWifiConnected = NetworkUtils.isWifiConnected(context);
            if (!isNetworkConnected && !isWifiConnected) {
                Toast.makeText(context, R.string.couldnt_connect_check_network_connection_description, 0).show();
            } else if (objArr == null || objArr.length == 0) {
                if (eventContext.getHeaderItem() != null) {
                    MediaItem headerItem = eventContext.getHeaderItem();
                    this.mMediaItem = headerItem;
                    this.mMdeSpaceId = MediaItemMde.getSpaceId(headerItem);
                    setCoverByPickAndCrop();
                }
            } else if (objArr.length > 0 && (mediaItem = objArr[0]) != null) {
                MediaItem mediaItem2 = mediaItem;
                this.mMediaItem = mediaItem2;
                this.mMdeSpaceId = MediaItemMde.getSpaceId(mediaItem2);
                setCoverByCrop();
            }
        } else {
            new StartSharingServiceSetupWizardCmd().execute(eventContext, RequestSetupWizardType.SETUP_SHARING_SERVICE);
        }
    }

    public void setCoverByCrop() {
        launchCropper(makeCommonDataByCrop(360, 360).appendArg("FromSharedAlbumCover", true).appendArg("KEY_COVER_PICKER_TYPE", CoverPickType.FROM_SHARED_ALBUM.toInt()).appendArg("key-shared-album-space-id", this.mMdeSpaceId).appendArg("key-shared-item-id", MediaItemMde.getItemId(this.mMediaItem)).build());
    }

    public void setCoverByPickAndCrop() {
        String argValue = ArgumentsUtil.getArgValue((String) getBlackboard().read("location://variable/currentv1"), Message.KEY_POSITION);
        String albumCoverId = getAlbumCoverId(getBlackboard(), MediaItemMde.getSpaceCoverId(this.mMediaItem), this.mMediaItem.getPath());
        UriBuilder makeCommonDataByPickAndCrop = makeCommonDataByPickAndCrop();
        makeCommonDataByPickAndCrop.appendArg("FromSharedAlbumCover", true).appendArg("KEY_COVER_PICKER_TYPE", CoverPickType.FROM_SHARED_ALBUM.toInt()).appendArg("key-shared-album-space-id", this.mMdeSpaceId).appendArg("key-shared-album-group-id", MediaItemMde.getGroupId(this.mMediaItem)).appendArg("key-shared-album-list-position", argValue).appendArg("key-current-cover-item", albumCoverId);
        launchPickerForCrop(makeCommonDataByPickAndCrop.build());
    }
}
