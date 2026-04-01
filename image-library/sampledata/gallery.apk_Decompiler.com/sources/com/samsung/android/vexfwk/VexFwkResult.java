package com.samsung.android.vexfwk;

import Ae.b;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003:\u0002\u000f\u0010B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ-\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\u000b\u0010\nJ3\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00010\u0000\"\u0004\b\u0002\u0010\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0006¢\u0006\u0004\b\u000e\u0010\n\u0001\u0002\u0011\u0012¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/vexfwk/VexFwkResult;", "T", "E", "", "<init>", "()V", "Lkotlin/Function1;", "Lme/x;", "action", "onSuccess", "(LAe/b;)Lcom/samsung/android/vexfwk/VexFwkResult;", "onFailure", "R", "transform", "map", "Success", "Failure", "Lcom/samsung/android/vexfwk/VexFwkResult$Failure;", "Lcom/samsung/android/vexfwk/VexFwkResult$Success;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkResult<T, E> {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0002\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\bJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0002HÆ\u0001¢\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/vexfwk/VexFwkResult$Failure;", "E", "Lcom/samsung/android/vexfwk/VexFwkResult;", "", "error", "<init>", "(Ljava/lang/Object;)V", "getError", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/samsung/android/vexfwk/VexFwkResult$Failure;", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Failure<E> extends VexFwkResult {
        private final E error;

        public Failure(E e) {
            super((e) null);
            this.error = e;
        }

        public static /* synthetic */ Failure copy$default(Failure failure, E e, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                e = failure.error;
            }
            return failure.copy(e);
        }

        public final E component1() {
            return this.error;
        }

        public final Failure<E> copy(E e) {
            return new Failure<>(e);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof Failure) && j.a(this.error, ((Failure) obj).error)) {
                return true;
            }
            return false;
        }

        public final E getError() {
            return this.error;
        }

        public int hashCode() {
            E e = this.error;
            if (e == null) {
                return 0;
            }
            return e.hashCode();
        }

        public String toString() {
            E e = this.error;
            return "Failure(error=" + e + ")";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0002\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\bJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0002HÆ\u0001¢\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/vexfwk/VexFwkResult$Success;", "T", "Lcom/samsung/android/vexfwk/VexFwkResult;", "", "value", "<init>", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/samsung/android/vexfwk/VexFwkResult$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Success<T> extends VexFwkResult {
        private final T value;

        public Success(T t) {
            super((e) null);
            this.value = t;
        }

        public static /* synthetic */ Success copy$default(Success success, T t, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                t = success.value;
            }
            return success.copy(t);
        }

        public final T component1() {
            return this.value;
        }

        public final Success<T> copy(T t) {
            return new Success<>(t);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof Success) && j.a(this.value, ((Success) obj).value)) {
                return true;
            }
            return false;
        }

        public final T getValue() {
            return this.value;
        }

        public int hashCode() {
            T t = this.value;
            if (t == null) {
                return 0;
            }
            return t.hashCode();
        }

        public String toString() {
            T t = this.value;
            return "Success(value=" + t + ")";
        }
    }

    public /* synthetic */ VexFwkResult(e eVar) {
        this();
    }

    public final <R> VexFwkResult<R, E> map(b bVar) {
        j.e(bVar, "transform");
        if (this instanceof Success) {
            return new Success(bVar.invoke(((Success) this).getValue()));
        }
        if (this instanceof Failure) {
            return new Failure(((Failure) this).getError());
        }
        throw new RuntimeException();
    }

    public final VexFwkResult<T, E> onFailure(b bVar) {
        j.e(bVar, "action");
        if (this instanceof Failure) {
            bVar.invoke(((Failure) this).getError());
        }
        return this;
    }

    public final VexFwkResult<T, E> onSuccess(b bVar) {
        j.e(bVar, "action");
        if (this instanceof Success) {
            bVar.invoke(((Success) this).getValue());
        }
        return this;
    }

    private VexFwkResult() {
    }
}
