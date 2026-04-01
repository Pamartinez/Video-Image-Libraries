package com.samsung.android.gallery.support.utils;

import android.app.Service;
import android.content.Context;
import androidx.collection.CircularArray;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ServiceManager {
    private static final ServiceManager instance = new ServiceManager();
    private final ConcurrentHashMap<Integer, ServiceInfoCompat> activeMap = new ConcurrentHashMap<>();
    private final CircularArray<ServiceInfoCompat> historyList = new CircularArray<>(30);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ServiceInfoCompat {
        long endTime;
        /* access modifiers changed from: package-private */
        public String serviceName;
        long startTime = System.currentTimeMillis();
        long timeoutTime;

        public ServiceInfoCompat(String str) {
            this.serviceName = str;
        }

        public String toString() {
            String str;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.serviceName);
            sb2.append("{");
            sb2.append(TimeUtil.getIsoLocalDateTime(this.startTime));
            sb2.append(",+");
            long j2 = this.endTime;
            if (j2 <= 0) {
                j2 = System.currentTimeMillis();
            }
            sb2.append(TimeUtil.duration(j2 - this.startTime));
            if (this.timeoutTime > 0) {
                str = ",timeout=" + TimeUtil.getIsoLocalDateTime(this.timeoutTime);
            } else {
                str = "";
            }
            return C0212a.p(sb2, str, "}");
        }
    }

    public static ServiceManager getInstance() {
        return instance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$timeout$0(Map.Entry entry) {
        return entry.getKey() + "=" + entry.getValue();
    }

    public void add(Service service) {
        this.activeMap.put(Integer.valueOf(service.hashCode()), new ServiceInfoCompat(service.getClass().getName()));
        Log.d("ServiceManager", "add", Integer.valueOf(this.activeMap.size()), service.getClass().getSimpleName(), Integer.valueOf(this.historyList.size()));
    }

    public List<String> getHistory() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.historyList) {
            int i2 = 0;
            while (i2 < this.historyList.size()) {
                try {
                    arrayList.add(this.historyList.get(i2));
                    i2++;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }
        return (List) arrayList.stream().map(new C0670h(6)).collect(Collectors.toList());
    }

    public Set<String> getRunningServices() {
        return (Set) this.activeMap.values().stream().map(new C0670h(5)).collect(Collectors.toSet());
    }

    public boolean hasRunningService(Context context, String... strArr) {
        Set<String> runningServices = getRunningServices();
        for (String str : strArr) {
            if (runningServices.contains(str)) {
                Log.i("ServiceManager", "hasRunningService", str);
                return true;
            }
        }
        return false;
    }

    public boolean hasRunningServiceForTrash(Context context) {
        return hasRunningService(context, "com.samsung.android.gallery.app.service.DeleteService", "com.samsung.android.gallery.app.service.QuickDeleteService", "com.samsung.android.gallery.app.service.RestoreService", "com.samsung.android.gallery.app.service.EmptyService");
    }

    public void remove(Service service) {
        ServiceInfoCompat remove = this.activeMap.remove(Integer.valueOf(service.hashCode()));
        if (remove != null) {
            synchronized (this.historyList) {
                remove.endTime = System.currentTimeMillis();
                this.historyList.addLast(remove);
            }
            Log.d("ServiceManager", "remove", Integer.valueOf(this.activeMap.size()), service.getClass().getSimpleName(), Integer.valueOf(this.historyList.size()));
            return;
        }
        Log.e("ServiceManager", "remove failed. no active service ".concat(service.getClass().getSimpleName()));
    }

    public void timeout(Service service) {
        String str;
        ServiceInfoCompat serviceInfoCompat = this.activeMap.get(Integer.valueOf(service.hashCode()));
        if (serviceInfoCompat != null) {
            serviceInfoCompat.timeoutTime = System.currentTimeMillis();
            Log.w((CharSequence) "ServiceManager", "timeout", Integer.valueOf(this.activeMap.size()), service.getClass().getSimpleName(), Integer.valueOf(this.historyList.size()));
            return;
        }
        String str2 = (String) this.activeMap.entrySet().stream().map(new C0670h(25)).collect(Collectors.joining("\n"));
        StringBuilder sb2 = new StringBuilder("timeout. no active service ");
        sb2.append(service.getClass().getSimpleName());
        if (str2.isEmpty()) {
            str = "";
        } else {
            str = "\n".concat(str2);
        }
        sb2.append(str);
        Log.e("ServiceManager", sb2.toString());
    }

    public String toDebugString() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("== services(" + this.activeMap.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.historyList.size() + ") ==");
        if (!this.activeMap.isEmpty()) {
            this.activeMap.values().forEach(new C0683v(arrayList, 3));
        }
        if (!this.historyList.isEmpty()) {
            getHistory().forEach(new C0683v(arrayList, 7));
        }
        return String.join("\n", arrayList);
    }

    public String toString() {
        return "ServiceInfo{" + this.activeMap.size() + ",active=[" + ((String) this.activeMap.values().stream().map(new C0670h(4)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX))) + "],history=" + this.historyList.size() + "}";
    }
}
