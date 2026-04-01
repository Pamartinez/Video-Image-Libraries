package com.samsung.scsp.common;

import android.accounts.Account;
import android.content.ContentResolver;
import android.os.Bundle;
import com.samsung.android.sum.core.functional.g;
import com.samsung.scsp.error.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PushConsumerManager {
    private final Map<String, Consumer<PushVo>> PUSH_CONSUMER_MAP = new HashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder {
        /* access modifiers changed from: private */
        public static final PushConsumerManager INSTANCE = new PushConsumerManager();

        private Holder() {
        }
    }

    public static PushConsumerManager getInstance() {
        return Holder.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$add$0(Supplier supplier, String[] strArr, Bundle bundle) {
        Account account = (Account) supplier.get();
        if (account != null) {
            for (String str : strArr) {
                Logger.get("PushConsumerManager").i("requestSync: " + str);
                ContentResolver.requestSync(account, str, bundle);
            }
        }
    }

    public void add(String str, Consumer<PushVo> consumer) {
        this.PUSH_CONSUMER_MAP.put(str, consumer);
    }

    public Consumer<PushVo> get(String str) {
        return this.PUSH_CONSUMER_MAP.get(str);
    }

    public void add(String str, String[] strArr, Supplier<Account> supplier, Bundle bundle) {
        this.PUSH_CONSUMER_MAP.put(str, new g(supplier, strArr, bundle, 1));
    }
}
