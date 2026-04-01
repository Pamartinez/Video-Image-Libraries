package com.samsung.android.gallery.app.ui.viewer2.slideshow;

import C3.C0391a;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.remote.StartSlideshowV2Cmd;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegatePresenter;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.SlideDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowPresenter extends AbsVuDelegatePresenter<ISlideshowView> {
    private SlideDelegate mSlideDelegate;

    public SlideshowPresenter(Blackboard blackboard, ISlideshowView iSlideshowView) {
        super(blackboard, iSlideshowView);
    }

    /* access modifiers changed from: private */
    public void onAppCastChanged(Object obj, Bundle bundle) {
        MediaItem[] mediaItemArr;
        if (getMediaData() == null || getMediaData().getAllData() == null) {
            mediaItemArr = null;
        } else {
            mediaItemArr = (MediaItem[]) getMediaData().getAllData().toArray(new MediaItem[0]);
        }
        new StartSlideshowV2Cmd().execute(((ISlideshowView) this.mView).getEventContext(), mediaItemArr);
        ((ISlideshowView) this.mView).finish();
    }

    private void pauseContentViewHolder() {
        ViewerObjectComposite currentContentViewer = getCurrentContentViewer();
        if (currentContentViewer != null) {
            currentContentViewer.onPause();
        }
    }

    private void resumeContentViewHolder() {
        ViewerObjectComposite currentContentViewer = getCurrentContentViewer();
        if (currentContentViewer != null) {
            currentContentViewer.onResume();
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://remote/onAppCastChanged", new C0391a(18, this)));
    }

    public MenuDataBinding createMenuDataBinding() {
        return new MenuDataBinding(R.menu.menu_slideshow);
    }

    public ViewerObjectComposite getCurrentContentViewer() {
        return ((ISlideshowView) this.mView).getCurrentContentViewer();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3014) {
            return false;
        }
        onNavigationPressed((View) null);
        return true;
    }

    public boolean hasOptionsMenu() {
        return true;
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        ViewerObjectComposite currentContentViewer = getCurrentContentViewer();
        if (currentContentViewer != null) {
            currentContentViewer.onApplyWindowInsets();
        }
    }

    public void onDump(PrintWriter printWriter, String str) {
        StringBuilder t = C0212a.t(str, " mPosition : ");
        t.append(((ContainerModel) this.mModel).getPosition());
        Logger.dumpLog(printWriter, t.toString());
        if (getCurrentContentViewer() != null) {
            getCurrentContentViewer().onDump(printWriter, str);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (R.id.action_resume_slideshow != menuItem.getItemId()) {
            return false;
        }
        Log.d(this.TAG, "resume button clicked");
        this.mSlideDelegate.resumeSlideshow();
        return true;
    }

    public void onViewCreate() {
        this.mSlideDelegate = (SlideDelegate) ((ISlideshowView) this.mView).getDelegate(SlideDelegate.class);
        Log.d(this.TAG, "onViewCreate");
        super.onViewCreate();
    }

    public void onViewCreated(View view) {
        String str = this.TAG;
        Log.d(str, "onViewCreated :" + ((ContainerModel) this.mModel).getPosition());
        super.onViewCreated(view);
    }

    public void onViewDestroy() {
        String str = this.TAG;
        Log.d(str, "onViewDestroy :" + ((ContainerModel) this.mModel).getPosition());
        super.onViewDestroy();
    }

    public void onViewPause() {
        String str = this.TAG;
        Log.d(str, "onViewPause :" + ((ContainerModel) this.mModel).getPosition());
        super.onViewPause();
        pauseContentViewHolder();
    }

    public void onViewResume() {
        String str = this.TAG;
        Log.d(str, "onViewResume :" + ((ContainerModel) this.mModel).getPosition());
        super.onViewResume();
        resumeContentViewHolder();
    }

    public void updateToolbar(Toolbar toolbar) {
        setNavigationUpButton(toolbar);
    }
}
