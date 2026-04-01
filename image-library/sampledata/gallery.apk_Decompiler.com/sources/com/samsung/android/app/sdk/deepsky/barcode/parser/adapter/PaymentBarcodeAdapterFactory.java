package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.r;
import X2.u;
import X2.y;
import Z2.a;
import Z2.c;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\f\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\nJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/PaymentBarcodeAdapterFactory;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "LX2/r;", "parsedResult", "", "isSamsungPayIndiaType", "(LX2/r;)Z", "isSamsungPayIndonesiaType", "isPixType", "isUriType", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "create", "(LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "appContext", "Landroid/content/Context;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PaymentBarcodeAdapterFactory {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static volatile PaymentBarcodeAdapterFactory instance;
    private final Context appContext;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/PaymentBarcodeAdapterFactory$Companion;", "", "<init>", "()V", "instance", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/PaymentBarcodeAdapterFactory;", "getInstance", "context", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final PaymentBarcodeAdapterFactory getInstance(Context context) {
            PaymentBarcodeAdapterFactory access$getInstance$cp;
            j.e(context, "context");
            PaymentBarcodeAdapterFactory access$getInstance$cp2 = PaymentBarcodeAdapterFactory.instance;
            if (access$getInstance$cp2 != null) {
                return access$getInstance$cp2;
            }
            synchronized (this) {
                access$getInstance$cp = PaymentBarcodeAdapterFactory.instance;
                if (access$getInstance$cp == null) {
                    Context applicationContext = context.getApplicationContext();
                    j.d(applicationContext, "getApplicationContext(...)");
                    access$getInstance$cp = new PaymentBarcodeAdapterFactory(applicationContext, (e) null);
                    PaymentBarcodeAdapterFactory.instance = access$getInstance$cp;
                }
            }
            return access$getInstance$cp;
        }

        private Companion() {
        }
    }

    public /* synthetic */ PaymentBarcodeAdapterFactory(Context context, e eVar) {
        this(context);
    }

    private final boolean isPixType(r rVar) {
        j.c(rVar, "null cannot be cast to non-null type com.google.zxing.client.result.pay.PayParsedResult");
        if (((a) rVar).d() == c.PIX) {
            return true;
        }
        return false;
    }

    private final boolean isSamsungPayIndiaType(r rVar) {
        j.c(rVar, "null cannot be cast to non-null type com.google.zxing.client.result.pay.PayParsedResult");
        if (((a) rVar).d() == c.SAMSUNG_PAY_INDIA) {
            return true;
        }
        return false;
    }

    private final boolean isSamsungPayIndonesiaType(r rVar) {
        j.c(rVar, "null cannot be cast to non-null type com.google.zxing.client.result.pay.PayParsedResult");
        if (((a) rVar).d() == c.SAMSUNG_PAY_INDONESIA) {
            return true;
        }
        return false;
    }

    private final boolean isUriType(r rVar) {
        j.c(rVar, "null cannot be cast to non-null type com.google.zxing.client.result.pay.PayParsedResult");
        a aVar = (a) rVar;
        for (u e : a.e) {
            if (e.e(aVar.d) != null) {
                return true;
            }
        }
        return false;
    }

    public final BarcodeParsedResult create(r rVar) {
        j.e(rVar, "parsedResult");
        if (isSamsungPayIndiaType(rVar) && ActionUtil.INSTANCE.isSamsungPayIndiaSupported(this.appContext)) {
            return new SamsungPayBarcodeAdapter(this.appContext, (a) rVar);
        }
        if (isSamsungPayIndonesiaType(rVar) && ActionUtil.INSTANCE.isSamsungPayIndonesiaSupported(this.appContext)) {
            return new SamsungPayBarcodeAdapter(this.appContext, (a) rVar);
        }
        if (isPixType(rVar)) {
            return new PixBarcodeAdapter(this.appContext, rVar);
        }
        if (isUriType(rVar)) {
            return new UrlBarcodeAdapter(this.appContext, (y) rVar);
        }
        return new TextBarcodeAdapter(this.appContext, rVar);
    }

    private PaymentBarcodeAdapterFactory(Context context) {
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }
}
