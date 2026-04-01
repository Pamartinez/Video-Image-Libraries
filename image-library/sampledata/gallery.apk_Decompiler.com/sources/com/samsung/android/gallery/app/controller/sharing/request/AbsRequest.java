package com.samsung.android.gallery.app.controller.sharing.request;

import O3.o;
import Ob.a;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsRequest {
    protected final String TAG = getClass().getSimpleName();
    protected Context mAppContext;
    protected Blackboard mBlackboard;
    private final WeakReference<EventContext> mHandlerRef;
    protected Object[] mParams;
    protected RequestCmdType mRequestCmdType;

    public AbsRequest(EventContext eventContext, Object[] objArr) {
        this.mHandlerRef = new WeakReference<>(eventContext);
        Blackboard blackboard = eventContext.getBlackboard();
        this.mBlackboard = blackboard;
        this.mAppContext = BlackboardUtils.readAppContext(blackboard);
        this.mParams = objArr;
        this.mRequestCmdType = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showToast$0(String str) {
        Toast.makeText(this.mAppContext, str, 0).show();
    }

    public final Context getContext() {
        Context context;
        if (getEventContext() != null) {
            context = getEventContext().getContext();
        } else {
            context = null;
        }
        if (context == null) {
            context = BlackboardUtils.readActivity(this.mBlackboard);
        }
        if (context != null) {
            return context;
        }
        return this.mAppContext;
    }

    public EventContext getEventContext() {
        return this.mHandlerRef.get();
    }

    public MediaType getItemTypeWithMediaList() {
        List<FileItemInterface> mdeItemList = getMdeItemList();
        if (mdeItemList == null || mdeItemList.isEmpty()) {
            return MediaType.Unsupported;
        }
        return MdeAlbumHelper.getMediaTypeOfItems(mdeItemList);
    }

    public List<FileItemInterface> getMdeItemList() {
        return null;
    }

    public int getNetworkErrorStringId() {
        return -1;
    }

    public RequestCmdType getRequestCmdType() {
        return this.mRequestCmdType;
    }

    public int getRunningStringId() {
        return -1;
    }

    public boolean isSameItemId(FileItemInterface fileItemInterface, String str) {
        if (TextUtils.isEmpty(str) || !MediaItemMde.getItemId(fileItemInterface).equals(str)) {
            return false;
        }
        return true;
    }

    public void processBySpace(List<FileItemInterface> list, BiConsumer<String, List<FileItemInterface>> biConsumer) {
        ((Map) list.stream().collect(Collectors.groupingBy(new o(15)))).forEach(biConsumer);
    }

    public abstract void request();

    public void showNetworkErrorToast() {
        int networkErrorStringId = getNetworkErrorStringId();
        if (networkErrorStringId != -1) {
            showToast(this.mAppContext.getResources().getString(networkErrorStringId));
        }
    }

    public void showRunningToast() {
        int runningStringId = getRunningStringId();
        if (runningStringId != -1) {
            showToast(this.mAppContext.getResources().getString(runningStringId));
        }
    }

    public void showToast(String str) {
        ThreadUtil.postOnUiThread(new a(13, this, str));
    }

    public MediaType getItemTypeWithMediaList(List<FileItemInterface> list) {
        if (list == null || list.isEmpty()) {
            return MediaType.Unsupported;
        }
        return MdeAlbumHelper.getMediaTypeOfItems(list);
    }

    public void onPostExecute(boolean z) {
    }
}
