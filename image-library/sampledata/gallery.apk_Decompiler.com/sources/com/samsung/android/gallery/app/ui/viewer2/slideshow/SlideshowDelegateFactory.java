package com.samsung.android.gallery.app.ui.viewer2.slideshow;

import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateFactory;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewHolderStateDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.MediaDataDelegate;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.PeopleDataDelegate;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.SlideDelegate;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.ViewPagerDelegate;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowDelegateFactory implements DelegateFactory<AbsVuDelegate, ISlideshowView> {
    public ArrayList<AbsVuDelegate> createDelegateList(ISlideshowView iSlideshowView) {
        ArrayList<AbsVuDelegate> arrayList = new ArrayList<>();
        arrayList.add(new MediaDataDelegate(iSlideshowView));
        arrayList.add(new ViewPagerDelegate(iSlideshowView));
        arrayList.add(new ViewHolderStateDelegate(iSlideshowView));
        arrayList.add(new SlideDelegate(iSlideshowView));
        arrayList.add(new PeopleDataDelegate(iSlideshowView));
        return arrayList;
    }
}
