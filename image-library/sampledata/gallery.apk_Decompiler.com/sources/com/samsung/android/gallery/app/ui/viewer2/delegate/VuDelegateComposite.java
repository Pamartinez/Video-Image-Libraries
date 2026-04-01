package com.samsung.android.gallery.app.ui.viewer2.delegate;

import K7.a;
import K7.b;
import K7.c;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateComposite;
import com.samsung.android.gallery.app.ui.abstraction.delegate.DelegateFactory;
import com.samsung.android.gallery.app.ui.abstraction.delegate.NotReadyException;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.IVuDelegateView;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VuDelegateComposite<V extends IVuDelegateView> extends DelegateComposite<AbsVuDelegate<V>, V, ContainerModel> {
    public VuDelegateComposite(DelegateFactory<AbsVuDelegate<V>, V> delegateFactory, V v) {
        super(delegateFactory, v);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onMediaDataChangedInternal$3(int i2, int i7, ArrayList arrayList, AbsVuDelegate absVuDelegate) {
        try {
            absVuDelegate.onMediaDataChanged(i2, i7);
        } catch (NotReadyException unused) {
            arrayList.add(absVuDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onPageInvalidateInternal$1(int i2, ViewerObjectComposite viewerObjectComposite, ArrayList arrayList, AbsVuDelegate absVuDelegate) {
        try {
            absVuDelegate.onPageInvalidate(i2, viewerObjectComposite);
        } catch (NotReadyException unused) {
            arrayList.add(absVuDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onPageSelectedInternal$0(int i2, ViewerObjectComposite viewerObjectComposite, ArrayList arrayList, AbsVuDelegate absVuDelegate) {
        try {
            absVuDelegate.onPageSelected(i2, viewerObjectComposite);
        } catch (NotReadyException unused) {
            arrayList.add(absVuDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onTableModeChangedInternal$2(boolean z, int i2, ArrayList arrayList, AbsVuDelegate absVuDelegate) {
        try {
            absVuDelegate.onTableModeChanged(z, i2);
        } catch (NotReadyException unused) {
            arrayList.add(absVuDelegate);
        }
    }

    private ArrayList<AbsVuDelegate<V>> onMediaDataChangedInternal(ArrayList<AbsVuDelegate<V>> arrayList, int i2, int i7) {
        ArrayList<AbsVuDelegate<V>> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(i2, i7, (ArrayList) arrayList2));
        return arrayList2;
    }

    private ArrayList<AbsVuDelegate<V>> onPageInvalidateInternal(ArrayList<AbsVuDelegate<V>> arrayList, int i2, ViewerObjectComposite viewerObjectComposite) {
        ArrayList<AbsVuDelegate<V>> arrayList2 = new ArrayList<>();
        arrayList.forEach(new b(i2, viewerObjectComposite, arrayList2, 0));
        return arrayList2;
    }

    private ArrayList<AbsVuDelegate<V>> onPageSelectedInternal(ArrayList<AbsVuDelegate<V>> arrayList, int i2, ViewerObjectComposite viewerObjectComposite) {
        ArrayList<AbsVuDelegate<V>> arrayList2 = new ArrayList<>();
        arrayList.forEach(new b(i2, viewerObjectComposite, arrayList2, 1));
        return arrayList2;
    }

    private ArrayList<AbsVuDelegate<V>> onTableModeChangedInternal(ArrayList<AbsVuDelegate<V>> arrayList, boolean z, int i2) {
        ArrayList<AbsVuDelegate<V>> arrayList2 = new ArrayList<>();
        arrayList.forEach(new c((ArrayList) arrayList2, z, i2));
        return arrayList2;
    }

    public void onMediaDataChanged(int i2, int i7) {
        ArrayList arrayList = this.mDelegateList;
        int i8 = 0;
        while (arrayList.size() > 0) {
            if (i8 < 3) {
                arrayList = onMediaDataChangedInternal(arrayList, i2, i7);
            } else {
                throwException(arrayList, "onMediaDataChanged");
            }
            i8++;
        }
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        ArrayList arrayList = this.mDelegateList;
        int i7 = 0;
        while (arrayList.size() > 0) {
            if (i7 < 3) {
                arrayList = onPageInvalidateInternal(arrayList, i2, viewerObjectComposite);
            } else {
                throwException(arrayList, "onPageInvalidate");
            }
            i7++;
        }
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        ArrayList arrayList = this.mDelegateList;
        int i7 = 0;
        while (arrayList.size() > 0) {
            if (i7 < 3) {
                arrayList = onPageSelectedInternal(arrayList, i2, viewerObjectComposite);
            } else {
                throwException(arrayList, "onPageSelected");
            }
            i7++;
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        ArrayList arrayList = this.mDelegateList;
        int i7 = 0;
        while (arrayList.size() > 0) {
            if (i7 < 3) {
                arrayList = onTableModeChangedInternal(arrayList, z, i2);
            } else {
                throwException(arrayList, "onTableModeChanged");
            }
            i7++;
        }
    }
}
