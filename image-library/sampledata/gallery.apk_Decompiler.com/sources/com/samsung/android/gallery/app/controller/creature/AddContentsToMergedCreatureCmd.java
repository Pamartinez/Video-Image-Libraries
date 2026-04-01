package com.samsung.android.gallery.app.controller.creature;

import A4.C0387w;
import B8.g;
import Gb.a;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddContentsToMergedCreatureCmd extends BaseCommand {
    /* access modifiers changed from: private */
    /* renamed from: addContents */
    public void lambda$onExecute$1(String[] strArr, boolean z) {
        String str;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String unifiedIdentityIds = IdentityCreatureUtil.getUnifiedIdentityIds(Arrays.asList(strArr), z);
            List<Long> autoAlbumIds = AutoAlbumHelper.getInstance().getAutoAlbumIds(unifiedIdentityIds, z);
            if (autoAlbumIds != null && !autoAlbumIds.isEmpty()) {
                List list = (List) Stream.of(unifiedIdentityIds.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new a(9)).collect(Collectors.toList());
                if (z) {
                    str = PeopleDataManager.getIncludedMediaIds(list);
                } else {
                    str = PetDataManager.getIncludedMediaIds(list);
                }
                if (!TextUtils.isEmpty(str)) {
                    AutoAlbumHelper.getInstance().addAutoAlbumContents(str, autoAlbumIds);
                    getBlackboard().postBroadcastEvent(EventMessage.obtain(101));
                }
            }
            String str2 = this.TAG;
            Log.v(str2, "addContents rIds[" + unifiedIdentityIds + "], aIds" + autoAlbumIds + " elapsed", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$onExecute$0(int i2) {
        return new String[i2];
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (objArr != null && objArr.length >= 3) {
            String str = objArr[0];
            String[] strArr = objArr[1];
            if (str != null && strArr != null && strArr.length != 0) {
                SimpleThreadPool.getInstance().execute(new g((Object) this, (Object) (String[]) Stream.concat(Stream.of(str), Stream.of(strArr)).toArray(new C0387w(10)), objArr[2].booleanValue(), 3));
            }
        }
    }
}
