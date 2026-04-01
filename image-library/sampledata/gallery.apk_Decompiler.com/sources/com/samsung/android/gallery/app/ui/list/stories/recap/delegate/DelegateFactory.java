package com.samsung.android.gallery.app.ui.list.stories.recap.delegate;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ControlDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.OsdUiDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.WindowDecoDelegate;
import com.samsung.android.gallery.app.ui.list.stories.recap.IRecapView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DelegateFactory {
    public static Delegate create(IRecapView iRecapView) {
        return new ControlDelegate<IRecapView>(iRecapView) {
            public List<Delegate> create(IRecapView iRecapView) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new WindowDecoDelegate(iRecapView));
                arrayList.add(new OsdUiDelegate(iRecapView));
                arrayList.add(new DecorLayoutDelegate(iRecapView));
                arrayList.add(new MediaPlayerViewDelegate(iRecapView));
                arrayList.add(new MediaControllerDelegate(iRecapView));
                arrayList.add(new FilmStripDelegate(iRecapView));
                arrayList.add(new RecapLastPageDelegate(iRecapView));
                return arrayList;
            }
        };
    }
}
