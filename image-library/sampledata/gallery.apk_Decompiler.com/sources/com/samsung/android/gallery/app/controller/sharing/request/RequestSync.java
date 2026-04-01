package com.samsung.android.gallery.app.controller.sharing.request;

import Ba.h;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestSync extends AbsRequest {

    /* renamed from: com.samsung.android.gallery.app.controller.sharing.request.RequestSync$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$controller$sharing$request$RequestSync$Types;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.app.controller.sharing.request.RequestSync$Types[] r0 = com.samsung.android.gallery.app.controller.sharing.request.RequestSync.Types.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$controller$sharing$request$RequestSync$Types = r0
                com.samsung.android.gallery.app.controller.sharing.request.RequestSync$Types r1 = com.samsung.android.gallery.app.controller.sharing.request.RequestSync.Types.Space     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$sharing$request$RequestSync$Types     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.controller.sharing.request.RequestSync$Types r1 = com.samsung.android.gallery.app.controller.sharing.request.RequestSync.Types.SpaceWithQuota     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$sharing$request$RequestSync$Types     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.controller.sharing.request.RequestSync$Types r1 = com.samsung.android.gallery.app.controller.sharing.request.RequestSync.Types.SpaceItem     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$controller$sharing$request$RequestSync$Types     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.app.controller.sharing.request.RequestSync$Types r1 = com.samsung.android.gallery.app.controller.sharing.request.RequestSync.Types.GroupMember     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.sharing.request.RequestSync.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Types {
        Space,
        SpaceWithQuota,
        SpaceItem,
        GroupMember
    }

    public RequestSync(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public void handleRequestCallback(int i2, String str) {
        String str2 = this.TAG;
        Log.sh(str2, "handleRequestCallback" + Logger.v(Integer.valueOf(i2), str));
        if (!MdeResultCode.isSuccess(i2)) {
            if (MdeResultCode.isPermissionDenied(i2, str)) {
                MdeAlbumHelper.sSemsPermissionDenied = true;
            } else if (!MdeResultCode.isGdprError(i2)) {
                MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, i2);
            }
        }
    }

    private void requestGroupMemberSync() {
        MdeGroupHelper.requestGroupMemberSync((String) this.mParams[2]);
    }

    private void requestShareItemSync() {
        MdeSharingHelper.requestShareItemSync((String) this.mParams[2], new h(16, this));
    }

    private void requestSpaceListSync(boolean z) {
        MdeSharingHelper.requestSpaceListSync(z, new h(16, this));
    }

    public void request() {
        Object obj;
        long currentTimeMillis = System.currentTimeMillis();
        Types types = (Types) this.mParams[1];
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$controller$sharing$request$RequestSync$Types[types.ordinal()];
        if (i2 == 1) {
            requestSpaceListSync(false);
        } else if (i2 == 2) {
            requestSpaceListSync(true);
        } else if (i2 == 3) {
            requestShareItemSync();
        } else if (i2 == 4) {
            requestGroupMemberSync();
        }
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("request {");
        sb2.append(this.mParams[0]);
        sb2.append(',');
        sb2.append(types);
        sb2.append(',');
        Object[] objArr = this.mParams;
        if (objArr.length > 2) {
            obj = objArr[2];
        } else {
            obj = "null";
        }
        sb2.append(obj);
        sb2.append("} +");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.sh(str, sb2.toString());
    }
}
