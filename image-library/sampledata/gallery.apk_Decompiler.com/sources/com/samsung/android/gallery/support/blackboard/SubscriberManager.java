package com.samsung.android.gallery.support.blackboard;

import com.samsung.android.gallery.support.trace.Trace;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SubscriberManager {
    private ArrayList<Subscriber> mSubscriberList;

    public SubscriberManager(ArrayList<Subscriber> arrayList) {
        this.mSubscriberList = arrayList;
    }

    public void add(Subscriber subscriber) {
        this.mSubscriberList.add(subscriber);
    }

    public void dump(PrintWriter printWriter) {
        Iterator<Subscriber> it = this.mSubscriberList.iterator();
        while (it.hasNext()) {
            it.next().dump(printWriter);
        }
    }

    public void onCreate() {
        Trace.beginSection("SubscriberManager onCreate");
        Iterator<Subscriber> it = this.mSubscriberList.iterator();
        while (it.hasNext()) {
            it.next().onCreate();
        }
        Trace.endSection();
    }

    public void onDestroy() {
        Iterator<Subscriber> it = this.mSubscriberList.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
        this.mSubscriberList.clear();
    }
}
