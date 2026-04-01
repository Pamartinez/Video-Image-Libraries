package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import Y2.a;
import Y2.c;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/IotBarcodeAdapterFactory;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "LY2/a;", "iotParsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "create", "(LY2/a;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "appContext", "Landroid/content/Context;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class IotBarcodeAdapterFactory {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static volatile IotBarcodeAdapterFactory instance;
    private final Context appContext;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/IotBarcodeAdapterFactory$Companion;", "", "<init>", "()V", "instance", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/IotBarcodeAdapterFactory;", "getInstance", "context", "Landroid/content/Context;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final IotBarcodeAdapterFactory getInstance(Context context) {
            IotBarcodeAdapterFactory access$getInstance$cp;
            j.e(context, "context");
            IotBarcodeAdapterFactory access$getInstance$cp2 = IotBarcodeAdapterFactory.instance;
            if (access$getInstance$cp2 != null) {
                return access$getInstance$cp2;
            }
            synchronized (this) {
                access$getInstance$cp = IotBarcodeAdapterFactory.instance;
                if (access$getInstance$cp == null) {
                    Context applicationContext = context.getApplicationContext();
                    j.d(applicationContext, "getApplicationContext(...)");
                    access$getInstance$cp = new IotBarcodeAdapterFactory(applicationContext, (e) null);
                    IotBarcodeAdapterFactory.instance = access$getInstance$cp;
                }
            }
            return access$getInstance$cp;
        }

        private Companion() {
        }
    }

    public /* synthetic */ IotBarcodeAdapterFactory(Context context, e eVar) {
        this(context);
    }

    public final BarcodeParsedResult create(a aVar) {
        j.e(aVar, "iotParsedResult");
        c cVar = aVar.f954c;
        if (cVar == c.APP_LINK_QR) {
            return new SmartThingsLinkBarcodeAdapter(this.appContext, aVar);
        }
        if (cVar == c.MATTER_QR) {
            return new MatterBarcodeAdapter(this.appContext, aVar);
        }
        return new SmartThingsDeviceBarcodeAdapter(this.appContext, aVar);
    }

    private IotBarcodeAdapterFactory(Context context) {
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }
}
