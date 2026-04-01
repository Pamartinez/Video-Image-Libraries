package com.samsung.android.gallery.app.ui.abstraction.delegate;

import A4.S;
import E7.c;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.abstraction.delegate.AbsDelegate;
import com.samsung.android.gallery.app.ui.abstraction.delegate.IDelegateView;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.functional.g;
import f4.a;
import i.C0212a;
import java.io.Closeable;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DelegateComposite<T extends AbsDelegate<V, M>, V extends IDelegateView<M>, M extends Closeable> extends AbsDelegate<V, M> {
    private final DelegateFactory<T, V> mDelegateFactory;
    protected ArrayList<T> mDelegateList;
    protected final ConcurrentHashMap<Class, T> mDelegateMap = new ConcurrentHashMap<>();

    public DelegateComposite(DelegateFactory<T, V> delegateFactory, V v) {
        super(v);
        this.mDelegateFactory = delegateFactory;
    }

    private void createDelegateList() {
        if (this.mDelegateList == null) {
            ArrayList<T> compositeList = getCompositeList(this.mDelegateFactory);
            this.mDelegateList = compositeList;
            compositeList.forEach(new b(23, this));
        }
    }

    private ArrayList<T> getCompositeList(DelegateFactory<T, V> delegateFactory) {
        ArrayList<T> arrayList;
        if (delegateFactory != null) {
            arrayList = delegateFactory.createDelegateList(this.mView);
        } else {
            arrayList = null;
        }
        if (arrayList == null) {
            return new ArrayList<>();
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDelegateList$0(AbsDelegate absDelegate) {
        this.mDelegateMap.put(absDelegate.getClass(), absDelegate);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onApplyWindowInsetsInternal$7(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onApplyWindowInsets();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onAttachInternal$13(Context context, ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onAttach(context);
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onBindViewInternal$4(View view, ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onBindView(view);
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onConfigurationChangedInternal$5(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9, ArrayList arrayList, AbsDelegate absDelegate) {
        AbsDelegate absDelegate2 = absDelegate;
        try {
            absDelegate2.onConfigurationChanged(configuration, z, z3, z7, z9);
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate2);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreateInternal$1(Bundle bundle, ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onCreate(bundle);
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreateViewInternal$2(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onCreateView(layoutInflater, viewGroup, bundle);
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDestroyInternal$17(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onDestroy();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDetachInternal$14(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onDetach();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onEnterTransitionEndInternal$10(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onEnterTransitionEnd();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onEnterTransitionStartInternal$9(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onEnterTransitionStart();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onMultiWindowModeChangedInternal$6(boolean z, ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onMultiWindowModeChanged(z);
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onPauseInternal$12(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onPause();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onResumeInternal$8(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onResume();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onReturnTransitionEndInternal$11(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onReturnTransitionEnd();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onStartInternal$15(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onStart();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onStopInternal$16(ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onStop();
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onViewCreatedInternal$3(View view, Bundle bundle, ArrayList arrayList, AbsDelegate absDelegate) {
        try {
            absDelegate.onViewCreated(view, bundle);
        } catch (NotReadyException unused) {
            arrayList.add(absDelegate);
        }
    }

    private ArrayList<T> onApplyWindowInsetsInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 4));
        return arrayList2;
    }

    private ArrayList<T> onAttachInternal(ArrayList<T> arrayList, Context context) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new e(16, context, arrayList2));
        return arrayList2;
    }

    private ArrayList<T> onBindViewInternal(ArrayList<T> arrayList, View view) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new e(17, view, arrayList2));
        return arrayList2;
    }

    private ArrayList<T> onConfigurationChangedInternal(ArrayList<T> arrayList, Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new f4.b(arrayList2, configuration, z, z3, z7, z9));
        return arrayList2;
    }

    private ArrayList<T> onCreateInternal(ArrayList<T> arrayList, Bundle bundle) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new e(15, bundle, arrayList2));
        return arrayList2;
    }

    private ArrayList<T> onCreateViewInternal(ArrayList<T> arrayList, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new S(layoutInflater, viewGroup, bundle, arrayList2, 14));
        return arrayList2;
    }

    private ArrayList<T> onDestroyInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 9));
        return arrayList2;
    }

    private ArrayList<T> onDetachInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 0));
        return arrayList2;
    }

    private ArrayList<T> onEnterTransitionEndInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 3));
        return arrayList2;
    }

    private ArrayList<T> onEnterTransitionStartInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 6));
        return arrayList2;
    }

    private ArrayList<T> onMultiWindowModeChangedInternal(ArrayList<T> arrayList, boolean z) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new c(arrayList2, z));
        return arrayList2;
    }

    private ArrayList<T> onPauseInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 1));
        return arrayList2;
    }

    private ArrayList<T> onResumeInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 8));
        return arrayList2;
    }

    private ArrayList<T> onReturnTransitionEndInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 5));
        return arrayList2;
    }

    private ArrayList<T> onStartInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 7));
        return arrayList2;
    }

    private ArrayList<T> onStopInternal(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new a(arrayList2, 2));
        return arrayList2;
    }

    private ArrayList<T> onViewCreatedInternal(ArrayList<T> arrayList, View view, Bundle bundle) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        arrayList.forEach(new g(view, bundle, arrayList2, 3));
        return arrayList2;
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        Iterator<T> it = this.mDelegateList.iterator();
        while (it.hasNext()) {
            ((AbsDelegate) it.next()).createGlobalSubscriberList(arrayList);
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        Iterator<T> it = this.mDelegateList.iterator();
        while (it.hasNext()) {
            ((AbsDelegate) it.next()).createSubscriberList(arrayList);
        }
    }

    public final <T extends AbsDelegate> T getDelegate(Class<T> cls) {
        return (AbsDelegate) cls.cast(this.mDelegateMap.get(cls));
    }

    public void onApplyWindowInsets() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onApplyWindowInsetsInternal(arrayList);
            } else {
                throwException(arrayList, "onApplyWindowInsets");
            }
            i2++;
        }
    }

    public void onAttach(Context context) {
        if (this.mDelegateList == null) {
            createDelegateList();
        }
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onAttachInternal(arrayList, context);
            } else {
                throwException(arrayList, "onAttach");
            }
            i2++;
        }
    }

    public void onBindView(View view) {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onBindViewInternal(arrayList, view);
            } else {
                throwException(arrayList, "onBindView");
            }
            i2++;
        }
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        Configuration configuration2;
        DelegateComposite delegateComposite;
        int i2 = 0;
        ArrayList<T> arrayList = this.mDelegateList;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                delegateComposite = this;
                configuration2 = configuration;
                z13 = z;
                z12 = z3;
                z11 = z7;
                z10 = z9;
                arrayList = delegateComposite.onConfigurationChangedInternal(arrayList, configuration2, z13, z12, z11, z10);
            } else {
                delegateComposite = this;
                configuration2 = configuration;
                z13 = z;
                z12 = z3;
                z11 = z7;
                z10 = z9;
                delegateComposite.throwException(arrayList, "onConfigurationChanged");
            }
            i2++;
            this = delegateComposite;
            configuration = configuration2;
            z = z13;
            z3 = z12;
            z7 = z11;
            z9 = z10;
        }
    }

    public void onCreate(Bundle bundle) {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onCreateInternal(arrayList, bundle);
            } else {
                throwException(arrayList, "onCreate");
            }
            i2++;
        }
    }

    public void onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onCreateViewInternal(arrayList, layoutInflater, viewGroup, bundle);
            } else {
                throwException(arrayList, "onCreateView");
            }
            i2++;
        }
    }

    public void onDestroy() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onDestroyInternal(arrayList);
            } else {
                throwException(arrayList, "onDestroy");
            }
            i2++;
        }
    }

    public void onDetach() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onDetachInternal(arrayList);
            } else {
                throwException(arrayList, "onDetach");
            }
            i2++;
        }
        this.mDelegateList.clear();
        this.mDelegateMap.clear();
    }

    public void onDump(PrintWriter printWriter, String str) {
        StringBuilder t = C0212a.t(str, " ============= dump of Delegate : size = ");
        t.append(this.mDelegateList.size());
        t.append(" =============");
        Logger.dumpLog(printWriter, t.toString());
        Iterator<T> it = this.mDelegateList.iterator();
        while (it.hasNext()) {
            AbsDelegate absDelegate = (AbsDelegate) it.next();
            StringBuilder t3 = C0212a.t(str, " [ ");
            t3.append(absDelegate.getClass().getSimpleName());
            t3.append(" ]");
            Logger.dumpLog(printWriter, t3.toString());
            absDelegate.onDump(printWriter, str + " + ");
        }
        Logger.dumpLog(printWriter, str + " ================ dump of Delegate : end ================");
    }

    public void onEnterTransitionEnd() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onEnterTransitionEndInternal(arrayList);
            } else {
                throwException(arrayList, "onEnterTransitionEnd");
            }
            i2++;
        }
    }

    public void onEnterTransitionStart() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onEnterTransitionStartInternal(arrayList);
            } else {
                throwException(arrayList, "onEnterTransitionEnd");
            }
            i2++;
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        Iterator<T> it = this.mDelegateList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= ((AbsDelegate) it.next()).onHandleEvent(eventMessage);
        }
        return z;
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        Iterator<T> it = this.mDelegateList.iterator();
        while (it.hasNext()) {
            if (((AbsDelegate) it.next()).onKeyDown(i2, keyEvent)) {
                return true;
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        Iterator<T> it = this.mDelegateList.iterator();
        while (it.hasNext()) {
            if (((AbsDelegate) it.next()).onKeyUp(i2, keyEvent)) {
                return true;
            }
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public void onMultiWindowModeChanged(boolean z) {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onMultiWindowModeChangedInternal(arrayList, z);
            } else {
                throwException(arrayList, "onMultiWindowModeChanged");
            }
            i2++;
        }
    }

    public void onPause() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onPauseInternal(arrayList);
            } else {
                throwException(arrayList, "onPause");
            }
            i2++;
        }
    }

    public void onResume() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onResumeInternal(arrayList);
            } else {
                throwException(arrayList, "onResume");
            }
            i2++;
        }
    }

    public void onReturnTransitionEnd() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onReturnTransitionEndInternal(arrayList);
            } else {
                throwException(arrayList, "onReturnTransitionEnd");
            }
            i2++;
        }
    }

    public void onStart() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onStartInternal(arrayList);
            } else {
                throwException(arrayList, "onStart");
            }
            i2++;
        }
    }

    public void onStop() {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onStopInternal(arrayList);
            } else {
                throwException(arrayList, "onStop");
            }
            i2++;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        ArrayList<T> arrayList = this.mDelegateList;
        int i2 = 0;
        while (arrayList.size() > 0) {
            if (i2 < 3) {
                arrayList = onViewCreatedInternal(arrayList, view, bundle);
            } else {
                throwException(arrayList, "onViewCreated");
            }
            i2++;
        }
    }

    public void throwException(ArrayList<T> arrayList, String str) {
        Iterator<T> it = arrayList.iterator();
        String str2 = "";
        while (it.hasNext()) {
            StringBuilder s = C0212a.s(str2);
            s.append(((AbsDelegate) it.next()).getClass().getSimpleName());
            s.append(" ");
            str2 = s.toString();
        }
        throw new NotReadyException(str + " [ " + str2 + "] is not ready");
    }
}
