package com.samsung.android.app.sdk.deepsky.textextraction.textclassification;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.D;
import Vf.z0;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.u;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Landroid/view/textclassifier/TextClassification;", "<anonymous>", "(LVf/A;)Landroid/view/textclassifier/TextClassification;"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper$classifyTextWithTimeout$1$1", f = "TextClassificationHelper.kt", l = {109}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextClassificationHelper$classifyTextWithTimeout$1$1 extends i implements c {
    final /* synthetic */ boolean $isDuplicatedEntityEnabled;
    final /* synthetic */ String $leftText;
    final /* synthetic */ String $rightText;
    final /* synthetic */ String $targetText;
    Object L$0;
    int label;
    final /* synthetic */ TextClassificationHelper this$0;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "", "<anonymous>", "(LVf/A;)Ljava/lang/Object;"}, k = 3, mv = {2, 1, 0})
    @C1273e(c = "com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper$classifyTextWithTimeout$1$1$1", f = "TextClassificationHelper.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper$classifyTextWithTimeout$1$1$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnonymousClass1 extends i implements c {
        int label;

        public final C1227c create(Object obj, C1227c cVar) {
            return new AnonymousClass1(obj3, textClassificationHelper, str, str2, str3, z, cVar);
        }

        public final Object invoke(A a7, C1227c cVar) {
            return ((AnonymousClass1) create(a7, cVar)).invokeSuspend(x.f4917a);
        }

        public final Object invokeSuspend(Object obj) {
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            if (this.label == 0) {
                a.A(obj);
                try {
                    obj3.d = textClassificationHelper.classifyTextInternal(str, str2, str3, z);
                    return x.f4917a;
                } catch (IllegalStateException e) {
                    return new Integer(LibLogger.e("TextClassificationHelper", "classifyText failed,", e));
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TextClassificationHelper$classifyTextWithTimeout$1$1(TextClassificationHelper textClassificationHelper, String str, String str2, String str3, boolean z, C1227c cVar) {
        super(2, cVar);
        this.this$0 = textClassificationHelper;
        this.$targetText = str;
        this.$leftText = str2;
        this.$rightText = str3;
        this.$isDuplicatedEntityEnabled = z;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new TextClassificationHelper$classifyTextWithTimeout$1$1(this.this$0, this.$targetText, this.$leftText, this.$rightText, this.$isDuplicatedEntityEnabled, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((TextClassificationHelper$classifyTextWithTimeout$1$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [kotlin.jvm.internal.u, java.lang.Object] */
    public final Object invokeSuspend(Object obj) {
        u uVar;
        z0 z0Var;
        Object obj2 = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            final ? obj3 = new Object();
            try {
                final TextClassificationHelper textClassificationHelper = this.this$0;
                final String str = this.$targetText;
                final String str2 = this.$leftText;
                final String str3 = this.$rightText;
                final boolean z = this.$isDuplicatedEntityEnabled;
                AnonymousClass1 r32 = new AnonymousClass1((C1227c) null);
                this.L$0 = obj3;
                this.label = 1;
                if (D.x(1200, r32, this) == obj2) {
                    return obj2;
                }
                uVar = obj3;
            } catch (z0 e) {
                z0Var = e;
                uVar = obj3;
                LibLogger.e("TextClassificationHelper", "classifyText timeout", z0Var);
                return uVar.d;
            }
        } else if (i2 == 1) {
            uVar = (u) this.L$0;
            try {
                a.A(obj);
            } catch (z0 e7) {
                z0Var = e7;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return uVar.d;
    }
}
