package com.samsung.android.gallery.app.ui.list.albums.pictures.setting;

import C3.C0391a;
import O3.l;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.IScreenshotFilterCustomView;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterCustomDataManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenshotFilterCustomPresenter<V extends IScreenshotFilterCustomView> extends BaseListPresenter<V> {
    public ScreenshotFilterCustomPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    /* access modifiers changed from: private */
    public void saveData(Object obj, Bundle bundle) {
        if (((Boolean) ((Object[]) obj)[1]).booleanValue()) {
            ScreenShotFilterCustomDataManager.saveData(getMediaData().getAllData());
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command:///ScreenshotFilterOrderChanged", new C0391a(16, this)));
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) "Manage categories");
        toolbar.setSubtitle((CharSequence) null);
        setNavigationUpButton(toolbar);
        Optional.ofNullable(((IScreenshotFilterCustomView) this.mView).getAppbarLayout()).ifPresent(new l(9));
    }
}
