package com.samsung.android.gallery.support.blackboard;

import Fa.C0571z;
import Gb.a;
import Ka.b;
import Ka.c;
import N2.j;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Blackboard {
    private static final ConcurrentHashMap<String, Blackboard> sBlackboardMap = new ConcurrentHashMap<>();
    private final String HASH_TAG;
    private final Object LOCK_SUBSCRIBER = new Object();
    private final ConcurrentHashMap<String, Object> mBlackboardData = new ConcurrentHashMap<>();
    private volatile boolean mIsActive = true;
    private final String mName;
    private final HashMap<String, ArrayList<SubscriberListener>> mSubscriberList = new HashMap<>();
    protected final HashMap<Integer, Integer> mThreadTypeMap = new HashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BlackboardTest extends Blackboard {
        public BlackboardTest(String str) {
            super(str);
        }

        public void notify(String str, Object obj, SubscriberListener subscriberListener) {
            if (this.mThreadTypeMap.get(Integer.valueOf(subscriberListener.hashCode())) != null) {
                subscriberListener.onNotify(obj, ArgumentsUtil.getArgs(str));
                if (subscriberListener instanceof InstantSubscriberListener) {
                    unsubscribe(str, subscriberListener);
                }
            }
        }
    }

    public Blackboard(String str) {
        this.mName = str;
        this.HASH_TAG = Logger.getSimpleName(str);
    }

    private void addSubscriberList(String str, SubscriberListener subscriberListener) {
        ArrayList computeIfAbsent = this.mSubscriberList.computeIfAbsent(str, new a(29));
        if (!computeIfAbsent.contains(subscriberListener)) {
            computeIfAbsent.add(subscriberListener);
        }
    }

    private void addThreadType(SubscriberListener subscriberListener, int i2) {
        this.mThreadTypeMap.putIfAbsent(Integer.valueOf(subscriberListener.hashCode()), Integer.valueOf(i2));
    }

    private boolean containsListener(SubscriberListener subscriberListener) {
        for (ArrayList<SubscriberListener> contains : this.mSubscriberList.values()) {
            if (contains.contains(subscriberListener)) {
                return true;
            }
        }
        return false;
    }

    public static void dumpAll(String str, PrintWriter printWriter) {
        ConcurrentHashMap<String, Blackboard> concurrentHashMap = sBlackboardMap;
        synchronized (concurrentHashMap) {
            try {
                printWriter.println("BB DUMP ALL " + concurrentHashMap.size());
                int i2 = 0;
                for (Blackboard dump : concurrentHashMap.values()) {
                    printWriter.println("BB DUMP #" + i2);
                    dump.dump(str, printWriter);
                    i2++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static Activity getActivity() {
        for (Blackboard read : sBlackboardMap.values()) {
            Activity activity = (Activity) read.read("data://activity");
            if (activity != null) {
                return activity;
            }
        }
        return null;
    }

    public static ArrayList<Blackboard> getActivityBlackboardList() {
        ArrayList<Blackboard> arrayList = new ArrayList<>();
        ConcurrentHashMap<String, Blackboard> concurrentHashMap = sBlackboardMap;
        synchronized (concurrentHashMap) {
            concurrentHashMap.forEach(new c(arrayList, 0));
        }
        return arrayList;
    }

    public static Context getAppContext() {
        return (Context) getApplicationInstance().read("data://app_context", (Object) null);
    }

    public static Blackboard getApplicationInstance() {
        return getInstance("GalleryApplication");
    }

    public static Map<String, Blackboard> getBlackboardMap() {
        return sBlackboardMap;
    }

    public static Context getContext() {
        List list = (List) sBlackboardMap.values().stream().map(new a(27)).filter(new C0571z(26)).collect(Collectors.toList());
        if (!list.isEmpty()) {
            return (Context) list.stream().filter(new C0571z(27)).filter(new C0571z(28)).findFirst().orElseGet(new J5.c(1, list));
        }
        return null;
    }

    public static Blackboard getInstance(String str) {
        return sBlackboardMap.computeIfAbsent(str, new a(28));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$addSubscriberList$7(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$dump$6(PrintWriter printWriter, String str, String str2, ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            printWriter.println(str + " [" + str2 + "] " + ((SubscriberListener) it.next()));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getActivityBlackboardList$1(ArrayList arrayList, String str, Blackboard blackboard) {
        if (str.contains("Activity")) {
            arrayList.add(blackboard);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Context lambda$getContext$2(Blackboard blackboard) {
        return (Context) blackboard.read("data://activity");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getContext$3(Context context) {
        return context instanceof AppCompatActivity;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Context lambda$getContext$5(List list) {
        return (Context) list.get(0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Blackboard lambda$getInstance$0(String str) {
        if ("Test".equals(str)) {
            return new BlackboardTest(str);
        }
        return new Blackboard(str);
    }

    private void notify(String str, Object obj, String str2) {
        ArrayList arrayList;
        synchronized (this.LOCK_SUBSCRIBER) {
            try {
                ArrayList arrayList2 = this.mSubscriberList.get(str2);
                arrayList = arrayList2 != null ? new ArrayList(arrayList2) : null;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                notify(str, obj, (SubscriberListener) it.next());
            }
        }
    }

    public static void postBroadcastEventGlobal(EventMessage eventMessage) {
        for (Blackboard postBroadcastEvent : sBlackboardMap.values()) {
            postBroadcastEvent.postBroadcastEvent(eventMessage);
        }
    }

    public static void postEventGlobal(EventMessage eventMessage) {
        for (Blackboard postEvent : sBlackboardMap.values()) {
            postEvent.postEvent(eventMessage);
        }
    }

    public static void postGlobal(String str, Object obj) {
        for (Blackboard post : sBlackboardMap.values()) {
            post.post(str, obj);
        }
    }

    public static void publishGlobal(String str, Object obj) {
        for (Blackboard publish : sBlackboardMap.values()) {
            publish.publish(str, obj);
        }
    }

    private void publishInternal(String str, Object obj, boolean z) {
        Object obj2;
        String removeArgs = ArgumentsUtil.removeArgs(str);
        int lastIndexOfWildRegex = ArgumentsUtil.lastIndexOfWildRegex(removeArgs);
        if (z) {
            ConcurrentHashMap<String, Object> concurrentHashMap = this.mBlackboardData;
            if (obj == null) {
                obj2 = "[NULL]";
            } else {
                obj2 = obj;
            }
            concurrentHashMap.put(removeArgs, obj2);
        }
        notify(str, obj, removeArgs);
        if (lastIndexOfWildRegex >= 0) {
            notify(ArgumentsUtil.appendArgs(str, "#", removeArgs.substring(lastIndexOfWildRegex + 1)), obj, removeArgs.substring(0, lastIndexOfWildRegex) + "/#");
        }
    }

    public void clear() {
        this.mBlackboardData.clear();
        synchronized (this.LOCK_SUBSCRIBER) {
            this.mSubscriberList.clear();
            this.mThreadTypeMap.clear();
            this.mIsActive = false;
        }
    }

    public <T> T computeIfAbsent(String str, Function<String, T> function) {
        return this.mBlackboardData.computeIfAbsent(str, function);
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.println("BB DUMP : " + this.mName + " / " + this);
        String p6 = C0212a.p(new StringBuilder(), str, "  ");
        Set<Map.Entry<String, Object>> entrySet = this.mBlackboardData.entrySet();
        TreeSet treeSet = new TreeSet(Map.Entry.comparingByKey());
        treeSet.addAll(entrySet);
        printWriter.println("DATA : ");
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object value = entry.getValue();
            printWriter.println(p6 + " [" + ((String) entry.getKey()) + "] " + value);
        }
        TreeMap treeMap = new TreeMap(Comparator.naturalOrder());
        synchronized (this.LOCK_SUBSCRIBER) {
            treeMap.putAll(this.mSubscriberList);
            printWriter.println("Subscribers : ");
            treeMap.forEach(new A9.a(5, printWriter, p6));
        }
    }

    public Object erase(String str) {
        return this.mBlackboardData.remove(ArgumentsUtil.removeArgs(str));
    }

    public void eraseWildNum(String str) {
        Set<String> keySet = this.mBlackboardData.keySet();
        ArrayList arrayList = new ArrayList();
        for (String next : keySet) {
            if (next.contains(str)) {
                arrayList.add(next);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.mBlackboardData.remove((String) it.next());
        }
    }

    public String getName() {
        return this.mName;
    }

    public boolean hasSubscriber(String str) {
        boolean containsKey;
        synchronized (this.LOCK_SUBSCRIBER) {
            containsKey = this.mSubscriberList.containsKey(ArgumentsUtil.removeArgs(str));
        }
        return containsKey;
    }

    public String hashTag() {
        return this.HASH_TAG;
    }

    public boolean isDebug() {
        return false;
    }

    public boolean isEmpty(String str) {
        return !this.mBlackboardData.containsKey(ArgumentsUtil.removeArgs(str));
    }

    public Object pop(String str) {
        if (!this.mBlackboardData.containsKey(str)) {
            return null;
        }
        Object read = read(str);
        erase(str);
        return read;
    }

    public void post(String str, Object obj) {
        publishInternal(str, obj, false);
    }

    public void postBroadcastEvent(EventMessage eventMessage) {
        publishInternal("command://UiBroadcastEvent", eventMessage, false);
    }

    public void postEvent(EventMessage eventMessage) {
        publishInternal("command://UiEvent", eventMessage, false);
    }

    public void postOnBgHighThread(Runnable runnable) {
        SimpleThreadPool.getInstance().executeOnPriorThread(runnable);
    }

    public void postOnBgThread(Runnable runnable) {
        ThreadPool.getInstance().submit(new b(runnable, 0));
    }

    public void postOnUiThread(Runnable runnable) {
        ThreadUtil.postOnUiThread(runnable);
    }

    public void publish(String str, Object obj) {
        publishInternal(str, obj, true);
    }

    public boolean publishIfEmpty(String str, Object obj) {
        if (!isEmpty(str)) {
            return false;
        }
        publishInternal(str, obj, true);
        return true;
    }

    public Object read(String str) {
        Object obj = this.mBlackboardData.get(ArgumentsUtil.removeArgs(str));
        if ("[NULL]".equals(obj)) {
            return null;
        }
        return obj;
    }

    public void replace(String str, Object obj) {
        erase(str);
        publishInternal(str, obj, true);
    }

    public void reset(String str) {
        String str2;
        StringBuilder sb2 = new StringBuilder("reset ");
        sb2.append(this);
        if (!this.mName.equals(str)) {
            str2 = C0212a.l(" ", str);
        } else {
            str2 = "";
        }
        j.y(sb2, str2, "Blackboard");
        sBlackboardMap.remove(str);
        clear();
    }

    public void subscribe(String str, SubscriberListener subscriberListener) {
        subscribe(str, subscriberListener, 0);
    }

    public void subscribeOnCurrent(String str, SubscriberListener subscriberListener) {
        subscribe(str, subscriberListener, 2);
    }

    public void subscribeOnUi(String str, SubscriberListener subscriberListener) {
        subscribe(str, subscriberListener, 1);
    }

    public String toString() {
        return "Blackboard{" + hashTag() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mSubscriberList.size() + "}";
    }

    public void unsubscribe(String str, SubscriberListener subscriberListener) {
        synchronized (this.LOCK_SUBSCRIBER) {
            try {
                ArrayList arrayList = this.mSubscriberList.get(str);
                if (arrayList != null) {
                    if (arrayList.remove(subscriberListener)) {
                        if (arrayList.isEmpty()) {
                            this.mSubscriberList.remove(str);
                        }
                    } else if (!(subscriberListener instanceof InstantSubscriberListener)) {
                        if (isDebug()) {
                            Log.e("Blackboard", "if you want to unsubscribe, do not use lambda expression ");
                            Log.e("Blackboard", "if you use  subscriberListener with singleton member field, it can overwrite until notify");
                            throw new IllegalArgumentException("wrong listener. [key]" + str + " [listener]" + subscriberListener);
                        }
                    }
                }
                if (!containsListener(subscriberListener)) {
                    this.mThreadTypeMap.remove(Integer.valueOf(subscriberListener.hashCode()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void subscribe(String str, SubscriberListener subscriberListener, int i2) {
        synchronized (this.LOCK_SUBSCRIBER) {
            try {
                if (this.mIsActive) {
                    addSubscriberList(str, subscriberListener);
                    addThreadType(subscriberListener, i2);
                } else {
                    Log.w((CharSequence) "Blackboard", "subscribe fail : " + str, subscriberListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public <T> T pop(String str, T t) {
        T pop = pop(str);
        return pop != null ? pop : t;
    }

    public <T> T read(String str, T t) {
        T read = read(str);
        return read != null ? read : t;
    }

    public void notify(String str, Object obj, SubscriberListener subscriberListener) {
        Integer num = this.mThreadTypeMap.get(Integer.valueOf(subscriberListener.hashCode()));
        if (num != null) {
            Bundle args = ArgumentsUtil.getArgs(str);
            if (BundleWrapper.getBoolean(args, "bbArg_NotifyDirect", false)) {
                num = 2;
            }
            if (isDebug()) {
                args.putString("_PUBLISHER", ThreadUtil.getCallerMethodName(6, 5));
            }
            if (num.intValue() == 1) {
                postOnUiThread(new Ka.a(subscriberListener, obj, args, 2));
            } else if (num.intValue() == 2) {
                subscriberListener.onNotify(obj, args);
            } else if (num.intValue() == 3) {
                postOnBgHighThread(new Ka.a(subscriberListener, obj, args, 0));
            } else {
                postOnBgThread(new Ka.a(subscriberListener, obj, args, 1));
            }
            if (subscriberListener instanceof InstantSubscriberListener) {
                unsubscribe(str, subscriberListener);
            }
        }
    }
}
