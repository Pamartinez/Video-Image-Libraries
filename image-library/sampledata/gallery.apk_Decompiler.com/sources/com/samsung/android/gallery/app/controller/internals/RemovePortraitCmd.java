package com.samsung.android.gallery.app.controller.internals;

import Ad.C0720a;
import Fa.C0571z;
import M5.a;
import O3.o;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemovePortraitCmd extends BaseCommand {
    private ArrayList<Long> getSelectedContentsIds(MediaItem[] mediaItemArr) {
        return (ArrayList) Arrays.stream(mediaItemArr).filter(new C0571z(4)).map(new o(2)).collect(Collectors.toCollection(new C0720a(1)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem[] mediaItemArr) {
        showResultToast(SuggestedHelper.getInstance().updatePortraitData(getContext(), getSelectedContentsIds(mediaItemArr), false));
        BlackboardUtils.forceRefreshPicturesData(getBlackboard(), true);
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    private void showResultToast(int i2) {
        showToast(getContext().getResources().getQuantityString(R.plurals.remove_from_suggestions_plural, i2, new Object[]{Integer.valueOf(i2)}));
    }

    public String getEventId() {
        return super.getEventId();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ThreadUtil.postOnBgThread(new a(17, this, objArr[0]));
    }
}
