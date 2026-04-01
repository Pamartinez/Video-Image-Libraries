package com.samsung.android.gallery.app.controller.album;

import A4.C0387w;
import A9.b;
import Fa.C0571z;
import H3.d;
import H3.e;
import H3.f;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateFolderCmd extends BaseCommand {
    private boolean mCreateFromMenu = false;
    private MediaItem mCurrentFolder;
    private int mHighlightIndex = -1;
    private boolean mIsMxEssentialView = false;
    private boolean mIsMxLegacyView = false;
    private boolean mIsSortByCustom = false;
    private MediaItem[] mSelectedItems;
    private Type mType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        CREATE_EMPTY,
        GROUPING,
        UPDATE
    }

    private int addNewEmptyFolder(String str) {
        int i2;
        updateLocalDbIsBusy(true);
        if (this.mCurrentFolder != null) {
            i2 = AlbumHelper.getInstance().addNewEmptyFolder(this.mCurrentFolder.getFolderID(), this.mCurrentFolder.getFolderName(), str, 1000000000000000007L);
        } else {
            i2 = AlbumHelper.getInstance().addNewEmptyFolder(str);
        }
        updateLocalDbIsBusy(false);
        return i2;
    }

    private int addNewFolder(EventContext eventContext, String str, List<Integer> list) {
        int i2;
        updateLocalDbIsBusy(true);
        if (this.mCurrentFolder != null) {
            List<Integer> list2 = list;
            i2 = AlbumHelper.getInstance().addNewFolder(list2, this.mCurrentFolder.getFolderID(), this.mCurrentFolder.getFolderName(), str, getAlbumOrder(eventContext));
        } else {
            String str2 = str;
            i2 = AlbumHelper.getInstance().addNewFolder(list, str2, getAlbumOrder(eventContext));
        }
        updateLocalDbIsBusy(false);
        return i2;
    }

    /* access modifiers changed from: private */
    public void checkOrderAndCreateFolder(EventContext eventContext, ArrayList<Object> arrayList) {
        if (!isValidData(arrayList)) {
            Log.e(this.TAG, "createFolder data is null");
            return;
        }
        String str = (String) ((Object[]) arrayList.get(0))[0];
        MediaItem[] allItems = getAllItems(eventContext);
        if (allItems == null || allItems.length <= 0) {
            Log.e(this.TAG, "createFolder, unable to update order info");
        } else if (!isNeedToUpdateOrder(allItems) || !this.mIsSortByCustom) {
            lambda$createFolder$4(eventContext, str);
        } else {
            new UpdateOrderCmd().execute(eventContext, new f(this, eventContext, str), -1, Integer.valueOf(this.mHighlightIndex));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: createEmptyFolder */
    public void lambda$createEmptyFolder$2(EventContext eventContext, ArrayList<Object> arrayList) {
        String str;
        if (arrayList == null) {
            Log.e(this.TAG, "createEmptyFolder data is null");
        } else if (ThreadUtil.isMainThread()) {
            SimpleThreadPool.getInstance().execute(new b(this, eventContext, new ArrayList(arrayList), 21));
        } else {
            String str2 = (String) ((Object[]) arrayList.get(0))[0];
            int i2 = -1;
            if (addNewEmptyFolder(str2) == -1) {
                ThreadUtil.postOnUiThread(new e(this, 0));
            } else {
                saveFolderLastIndex();
            }
            Blackboard blackboard = getBlackboard();
            MediaItem mediaItem = this.mCurrentFolder;
            if (mediaItem != null) {
                i2 = mediaItem.getFolderID();
            }
            Integer valueOf = Integer.valueOf(i2);
            MediaItem mediaItem2 = this.mCurrentFolder;
            if (mediaItem2 != null) {
                str = mediaItem2.getFolderName();
            } else {
                str = null;
            }
            blackboard.post("data://usernew_item_creation", new Object[]{str2, null, valueOf, str});
        }
    }

    private void createFolder(EventContext eventContext) {
        boolean equals = Type.GROUPING.equals(this.mType);
        if (equals) {
            getBlackboard().publish("data://selected_albums", this.mSelectedItems);
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/FolderName").appendArg("screenId", getScreenId()).build()).setOnDataCompleteListener(equals ? new d(this, 0) : new d(this, 1)).start();
    }

    private long getAlbumOrder(EventContext eventContext) {
        MediaItem[] allItems = getAllItems(eventContext);
        HashSet hashSet = new HashSet();
        for (MediaItem albumID : this.mSelectedItems) {
            hashSet.add(Integer.valueOf(albumID.getAlbumID()));
        }
        if (this.mCreateFromMenu) {
            return getAlbumOrderFromMenu(eventContext, allItems, hashSet);
        }
        return getAlbumOrderNotFromMenu(allItems, hashSet);
    }

    private long getAlbumOrderFromMenu(EventContext eventContext, MediaItem[] mediaItemArr, HashSet<Integer> hashSet) {
        int i2 = 0;
        long albumOrder = this.mSelectedItems[0].getAlbumOrder();
        if (!isNeedToUpdateOrder(mediaItemArr) || !this.mIsSortByCustom) {
            MediaItem[] allItems = getAllItems(eventContext);
            int length = allItems.length;
            while (i2 < length) {
                MediaItem mediaItem = allItems[i2];
                if (hashSet.contains(Integer.valueOf(mediaItem.getAlbumID()))) {
                    return mediaItem.getAlbumOrder();
                }
                i2++;
            }
        } else {
            int length2 = mediaItemArr.length;
            int i7 = 1;
            int i8 = 0;
            while (i2 < length2) {
                MediaItem mediaItem2 = mediaItemArr[i2];
                if (hashSet.contains(Integer.valueOf(mediaItem2.getAlbumID()))) {
                    return ((long) getFullAlbumIndex(hashSet, mediaItem2.getAlbumID(), i7, i8)) * 1000000000;
                }
                i7++;
                if (mediaItem2.isMergedAlbum()) {
                    i8 = mediaItem2.getChildList().size() + i8;
                }
                i2++;
            }
        }
        return albumOrder;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0098 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long getAlbumOrderNotFromMenu(com.samsung.android.gallery.module.data.MediaItem[] r20, java.util.HashSet<java.lang.Integer> r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            com.samsung.android.gallery.support.blackboard.Blackboard r3 = r0.getBlackboard()
            java.lang.String r4 = "dragged_item_album_order"
            java.lang.Object r3 = r3.pop(r4)
            r4 = 0
            if (r3 == 0) goto L_0x001a
            java.lang.Long r3 = (java.lang.Long) r3
            long r5 = r3.longValue()
            goto L_0x0022
        L_0x001a:
            com.samsung.android.gallery.module.data.MediaItem[] r3 = r0.mSelectedItems
            r3 = r3[r4]
            long r5 = r3.getAlbumOrder()
        L_0x0022:
            boolean r3 = r19.isNeedToUpdateOrder(r20)
            if (r3 == 0) goto L_0x009d
            boolean r3 = r0.mIsSortByCustom
            if (r3 == 0) goto L_0x009d
            int r3 = r1.length
            r7 = 1
            r8 = r4
            r9 = r8
        L_0x0030:
            if (r8 >= r3) goto L_0x009d
            r10 = r1[r8]
            boolean r11 = r10.isFolder()
            if (r11 == 0) goto L_0x006b
            com.samsung.android.gallery.module.data.MediaItem[] r11 = r10.getItemsInFolder()
            int r13 = r11.length
            r14 = r4
        L_0x0040:
            if (r14 >= r13) goto L_0x0068
            r15 = r11[r14]
            r16 = r4
            int r4 = r15.getAlbumID()
            r17 = 1000000000(0x3b9aca00, double:4.94065646E-315)
            com.samsung.android.gallery.module.data.MediaItem[] r12 = r0.mSelectedItems
            r12 = r12[r16]
            int r12 = r12.getAlbumID()
            if (r4 != r12) goto L_0x0063
            int r1 = r15.getAlbumID()
            int r0 = r0.getFullAlbumIndex(r2, r1, r7, r9)
        L_0x005f:
            long r0 = (long) r0
            long r0 = r0 * r17
            return r0
        L_0x0063:
            int r14 = r14 + 1
            r4 = r16
            goto L_0x0040
        L_0x0068:
            r16 = r4
            goto L_0x0087
        L_0x006b:
            r16 = r4
            r17 = 1000000000(0x3b9aca00, double:4.94065646E-315)
            int r4 = r10.getAlbumID()
            com.samsung.android.gallery.module.data.MediaItem[] r11 = r0.mSelectedItems
            r11 = r11[r16]
            int r11 = r11.getAlbumID()
            if (r4 != r11) goto L_0x0087
            int r1 = r10.getAlbumID()
            int r0 = r0.getFullAlbumIndex(r2, r1, r7, r9)
            goto L_0x005f
        L_0x0087:
            int r7 = r7 + 1
            boolean r4 = r10.isMergedAlbum()
            if (r4 == 0) goto L_0x0098
            java.util.List r4 = r10.getChildList()
            int r4 = r4.size()
            int r9 = r9 + r4
        L_0x0098:
            int r8 = r8 + 1
            r4 = r16
            goto L_0x0030
        L_0x009d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.album.CreateFolderCmd.getAlbumOrderNotFromMenu(com.samsung.android.gallery.module.data.MediaItem[], java.util.HashSet):long");
    }

    private MediaItem[] getAllItems(EventContext eventContext) {
        MediaItem[] allItems = eventContext.getAllItems();
        if (this.mIsMxEssentialView || this.mIsMxLegacyView) {
            return (MediaItem[]) Arrays.stream(allItems).filter(new C0571z(9)).toArray(new C0387w(9));
        }
        return allItems;
    }

    private int getFullAlbumIndex(HashSet<Integer> hashSet, int i2, int i7, int i8) {
        if (!this.mIsMxEssentialView) {
            return i7 + i8;
        }
        MediaData open = MediaDataFactory.getInstance(getBlackboard()).open("location://albums", true);
        if (open != null) {
            try {
                Iterator<MediaItem> it = open.getFullData().iterator();
                int i10 = 0;
                int i11 = 0;
                while (it.hasNext()) {
                    MediaItem next = it.next();
                    if (next.getAlbumID() == i2) {
                        int i12 = i10 + 1 + i11;
                        open.close();
                        return i12;
                    }
                    if (next.isMergedAlbum() && !hashSet.contains(Integer.valueOf(next.getAlbumID()))) {
                        i11 += next.getChildList().size();
                    }
                    if (!hashSet.contains(Integer.valueOf(next.getAlbumID()))) {
                        i10++;
                    }
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (open != null) {
            open.close();
        }
        return i7 + i8;
        throw th;
    }

    private void initialize(Object[] objArr) {
        MediaItem[] mediaItemArr;
        MediaItem[] mediaItemArr2 = objArr[0];
        if (mediaItemArr2 == null) {
            mediaItemArr = null;
        } else {
            mediaItemArr = mediaItemArr2;
        }
        this.mSelectedItems = mediaItemArr;
        this.mType = objArr[1];
        this.mCreateFromMenu = objArr[2].booleanValue();
        this.mCurrentFolder = objArr[3];
        if (objArr.length > 4) {
            this.mHighlightIndex = objArr[4].intValue();
        }
        this.mIsSortByCustom = SortByType.isSortByCustom();
    }

    private boolean isPossibleCreateFolder(MediaItem[] mediaItemArr) {
        return !Arrays.stream(mediaItemArr).anyMatch(new C0571z(7));
    }

    public static boolean isValidData(ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.isEmpty() || !(arrayList.get(0) instanceof Object[])) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$containsDefaultOrder$1(MediaItem mediaItem) {
        if (mediaItem.getAlbumOrder() == 1000000000000000007L) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createEmptyFolder$3() {
        Toast.makeText(getContext(), R.string.unable_to_create_album, 0).show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createFolder$5() {
        Toast.makeText(getContext(), R.string.unable_to_create_album, 0).show();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getAllItems$6(MediaItem mediaItem) {
        return !mediaItem.isSharing();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getAllItems$7(int i2) {
        return new MediaItem[i2];
    }

    private void saveFolderLastIndex() {
        PreferenceCache.FolderNameIndex.incrementAndGet();
    }

    private void sendFolderErrorEvent() {
        getBlackboard().postBroadcastEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_NOT_USER_OWNER, new Object[]{Boolean.FALSE}));
    }

    private void updateFolder() {
        MediaItem[] mediaItemArr = this.mSelectedItems;
        if (mediaItemArr != null) {
            MediaItem mediaItem = mediaItemArr[0];
            MediaItem mediaItem2 = mediaItemArr[1];
            if (!mediaItem.isFolder()) {
                Log.e(this.TAG, "updateFolder error");
                sendFolderErrorEvent();
                return;
            }
            if (!updateFolder(mediaItem, mediaItem2)) {
                Toast.makeText(getContext(), R.string.unable_to_add_item, 0).show();
                sendFolderErrorEvent();
            } else {
                getBlackboard().postBroadcastEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_NOT_USER_OWNER, new Object[]{Boolean.TRUE, Boolean.FALSE, Integer.valueOf(mediaItem.getFolderID()), mediaItem.getFolderName()}));
            }
            getBlackboard().erase("data://selected_albums");
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    private void updateLocalDbIsBusy(boolean z) {
        getBlackboard().publish("local_db_updating", Boolean.valueOf(z));
    }

    public boolean containsDefaultOrder(MediaItem[] mediaItemArr) {
        return Arrays.stream(mediaItemArr).anyMatch(new C0571z(8));
    }

    public String getEventId() {
        if (Type.GROUPING.equals(this.mType)) {
            return AnalyticsEventId.EVENT_MENU_ALBUM_GROUP.toString();
        }
        if (Type.CREATE_EMPTY.equals(this.mType)) {
            return AnalyticsEventId.EVENT_MENU_ALBUM_CREATE_EMPTY_GROUP.toString();
        }
        return AnalyticsEventId.EVENT_MENU_ADD_ALBUMS.toString();
    }

    public boolean isNeedToUpdateOrder(MediaItem[] mediaItemArr) {
        if (mediaItemArr[0].getAlbumOrder() == 0 || mediaItemArr[mediaItemArr.length - 1].getAlbumOrder() == 0 || containsDefaultOrder(mediaItemArr)) {
            return true;
        }
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z;
        initialize(objArr);
        MediaItem[] mediaItemArr = this.mSelectedItems;
        boolean z3 = false;
        if (mediaItemArr == null || !this.mCreateFromMenu || isPossibleCreateFolder(mediaItemArr)) {
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                PreferenceFeatures preferenceFeatures = PreferenceFeatures.EssentialAlbums;
                if (!preferenceFeatures.isEnabled() || !"location://albums".equals(eventContext.getLocationKey())) {
                    z = false;
                } else {
                    z = true;
                }
                this.mIsMxEssentialView = z;
                if (!preferenceFeatures.isEnabled() && "location://albums".equals(eventContext.getLocationKey())) {
                    z3 = true;
                }
                this.mIsMxLegacyView = z3;
            }
            Log.d(this.TAG, "mIsMxEssentialView#" + this.mIsMxEssentialView + " mIsMxLegacyView#" + this.mIsMxLegacyView);
            if (Type.GROUPING.equals(this.mType) || Type.CREATE_EMPTY.equals(this.mType)) {
                createFolder(eventContext);
            } else if (Type.UPDATE.equals(this.mType)) {
                updateFolder();
            }
        } else {
            Toast.makeText(getContext(), R.string.unable_to_grouping, 0).show();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: createFolder */
    public void lambda$createFolder$4(EventContext eventContext, String str) {
        if (ThreadUtil.isMainThread()) {
            SimpleThreadPool.getInstance().execute(new b(this, eventContext, str, 22));
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (MediaItem albumID : this.mSelectedItems) {
            arrayList.add(Integer.valueOf(albumID.getAlbumID()));
        }
        int addNewFolder = addNewFolder(eventContext, str, arrayList);
        if (addNewFolder == -1) {
            ThreadUtil.postOnUiThread(new e(this, 1));
        } else {
            saveFolderLastIndex();
            if (this.mCreateFromMenu) {
                getBlackboard().postBroadcastEvent(EventMessage.obtain(1003));
            }
            getBlackboard().postBroadcastEvent(EventMessage.obtain(ErrorCodeConvertor.TEMP_AGENT_NOT_USER_OWNER, new Object[]{Boolean.TRUE, Boolean.valueOf(this.mCreateFromMenu), Integer.valueOf(addNewFolder), str, arrayList}));
        }
        getBlackboard().erase("data://selected_albums");
    }

    private boolean updateFolder(MediaItem mediaItem, MediaItem mediaItem2) {
        updateLocalDbIsBusy(true);
        boolean updateFolder = AlbumHelper.getInstance().updateFolder(new int[]{mediaItem2.getAlbumID()}, mediaItem.getFolderID(), mediaItem.getFolderName());
        updateLocalDbIsBusy(false);
        return updateFolder;
    }
}
