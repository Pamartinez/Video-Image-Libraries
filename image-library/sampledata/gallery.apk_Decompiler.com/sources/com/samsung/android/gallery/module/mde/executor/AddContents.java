package com.samsung.android.gallery.module.mde.executor;

import A4.C0375j;
import E9.a;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.mobileservice.social.share.SharedItem;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddContents extends ServiceExecutor {
    public AddContents(Handler handler, Context context, Blackboard blackboard) {
        super(handler, context, blackboard);
    }

    private void addCoverData(SharedItemListResult sharedItemListResult) {
        String str = (String) this.mBlackboard.pop("data://user/coverInfo", null);
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("title");
                String string2 = jSONObject.getString("bucketId");
                for (SharedItem next : sharedItemListResult.getSuccessList()) {
                    if (isSharedCoverItem(string, string2, next)) {
                        updateSpaceCover(next, jSONObject.getString("coverRectRatio"));
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private MediaItem[] convertUrisToMediaItems(Context context, ArrayList<Uri> arrayList) {
        if (arrayList.isEmpty()) {
            Log.e(this.TAG, "convertUrisToMediaItems failed. uris is null");
            return new MediaItem[0];
        }
        ArrayList<MediaItem> mediaItems = getMediaItems(context, (Uri[]) arrayList.toArray(new Uri[0]));
        if (mediaItems.size() != 0) {
            return (MediaItem[]) mediaItems.toArray(new MediaItem[0]);
        }
        Log.e(this.TAG, "convertUrisToMediaItems failed. No data available");
        return new MediaItem[0];
    }

    private Consumer<SharedItemListResult> getAddContentsResultCallback() {
        return new a(0, this);
    }

    private List<FileItemInterface> getFilteredNullItemList(Bundle bundle) {
        MediaItem[] convertUrisToMediaItems = convertUrisToMediaItems((Context) this.mBlackboard.read("data://app_context"), bundle.getParcelableArrayList("contentsData"));
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, convertUrisToMediaItems);
        arrayList.removeIf(new C0375j(21));
        return arrayList;
    }

    private ArrayList<MediaItem> getMediaItems(Context context, Uri[] uriArr) {
        if (Arrays.stream(uriArr).filter(new com.samsung.android.gallery.module.dynamicview.a(17)).allMatch(new C0375j(22))) {
            return UriItemLoader.getItemsFromMediaUris(uriArr);
        }
        if (Arrays.stream(uriArr).filter(new com.samsung.android.gallery.module.dynamicview.a(17)).allMatch(new C0375j(23))) {
            return UriItemLoader.getSharingItemsFromFileUris(context, uriArr);
        }
        return UriItemLoader.getMediaItemFromUris(context, uriArr);
    }

    private boolean isSharedCoverItem(String str, String str2, SharedItem sharedItem) {
        if (!TextUtils.equals(str, sharedItem.getTitle()) || !TextUtils.equals(str2, (String) sharedItem.getMetaData().get("bucketId"))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getAddContentsResultCallback$0(SharedItemListResult sharedItemListResult) {
        addCoverData(sharedItemListResult);
        int code = sharedItemListResult.getStatus().getCode();
        if (MdeResultCode.isSuccess(code)) {
            onSuccess((String[]) null, (Bundle) null);
        } else {
            onFailure(Integer.valueOf(code), (String[]) null);
        }
    }

    private void updateSpaceCover(SharedItem sharedItem, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("coverMediaId", sharedItem.getItemId());
        hashMap.put("coverRectRatio", str);
        MdeSharingHelper.requestSpaceUpdate(sharedItem.getSpaceId(), hashMap, (Consumer<Integer>) null);
    }

    public void execute(Bundle bundle) {
        List<FileItemInterface> filteredNullItemList = getFilteredNullItemList(bundle);
        if (((Boolean) this.mBlackboard.pop("data://add_to_shared_album_from_external", Boolean.FALSE)).booleanValue()) {
            this.mBlackboard.publish("command://request_suicide", (Object) null);
        }
        if (filteredNullItemList.isEmpty()) {
            Log.e(this.TAG, "There are no media items to add to shared album.");
            return;
        }
        Context context = (Context) this.mBlackboard.read("data://app_context");
        if (context == null) {
            Log.e(this.TAG, "context is null.");
        } else {
            MdeSharingHelper.requestAddContents(context, bundle.getString("spaceId"), bundle.getString("groupId"), filteredNullItemList, getAddContentsResultCallback());
        }
    }

    public void onFailure(Integer num, String[] strArr) {
        Log.d(this.TAG, "Failed to add contents but did not delete space");
        super.onFailure(num, strArr);
    }

    public void onSuccess(String[] strArr, Bundle bundle) {
        sendDestroyServiceMessage();
    }
}
