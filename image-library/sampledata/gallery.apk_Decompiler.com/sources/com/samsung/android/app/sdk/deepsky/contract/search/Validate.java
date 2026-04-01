package com.samsung.android.app.sdk.deepsky.contract.search;

import Ae.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/Validate;", "", "<init>", "()V", "Companion", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Validate {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\b\u001a\u00028\u0000\"\b\b\u0000\u0010\u0004*\u00020\u00012\b\u0010\u0005\u001a\u0004\u0018\u00018\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ)\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\bø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0002\u0007\n\u0005\b20\u0001¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/Validate$Companion;", "", "<init>", "()V", "T", "value", "Lkotlin/Function0;", "lazyMessage", "notNull", "(Ljava/lang/Object;LAe/a;)Ljava/lang/Object;", "", "Lme/x;", "checkTrue", "(ZLAe/a;)V", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final void checkTrue(boolean z, a aVar) {
            j.e(aVar, "lazyMessage");
            if (!z) {
                throw new SearchException(aVar.invoke().toString());
            }
        }

        public final <T> T notNull(T t, a aVar) {
            j.e(aVar, "lazyMessage");
            if (t != null) {
                return t;
            }
            throw new SearchException(aVar.invoke().toString());
        }

        private Companion() {
        }
    }

    public static final void checkTrue(boolean z, a aVar) {
        Companion.checkTrue(z, aVar);
    }

    public static final <T> T notNull(T t, a aVar) {
        return Companion.notNull(t, aVar);
    }
}
