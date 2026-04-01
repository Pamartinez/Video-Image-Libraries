package com.samsung.android.app.sdk.deepsky.barcode.parser.adapter;

import X2.C0064d;
import X2.C0067g;
import X2.F;
import X2.h;
import X2.r;
import X2.v;
import X2.x;
import Y2.a;
import android.content.Context;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "", "<init>", "(Ljava/lang/String;I)V", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "ADDRESSBOOK", "CALENDAR", "EMAIL_ADDRESS", "URI", "TEL", "SMS", "WIFI", "TEXT", "PAYMENT", "IOT", "GEO", "PRODUCT", "ISBN", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ParsedResultType {
    ;

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.ADDRESSBOOK", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ADDRESSBOOK extends ParsedResultType {
        public ADDRESSBOOK(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new AddressBookAdapter(context, (C0064d) rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.CALENDAR", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class CALENDAR extends ParsedResultType {
        public CALENDAR(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new CalendarBarcodeAdapter(context, (C0067g) rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.EMAIL_ADDRESS", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EMAIL_ADDRESS extends ParsedResultType {
        public EMAIL_ADDRESS(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new EmailBarcodeAdapter(context, (h) rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.GEO", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class GEO extends ParsedResultType {
        public GEO(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new MapBarcodeAdapter(context, rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.IOT", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IOT extends ParsedResultType {
        public IOT(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return IotBarcodeAdapterFactory.Companion.getInstance(context).create((a) rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.ISBN", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ISBN extends ParsedResultType {
        public ISBN(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new IsbnBarcodeAdapter(context, rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.PAYMENT", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PAYMENT extends ParsedResultType {
        public PAYMENT(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return PaymentBarcodeAdapterFactory.Companion.getInstance(context).create(rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.PRODUCT", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PRODUCT extends ParsedResultType {
        public PRODUCT(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new ProductBarcodeAdapter(context, rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.SMS", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SMS extends ParsedResultType {
        public SMS(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new SmsBarcodeAdapter(context, (v) rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.TEL", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TEL extends ParsedResultType {
        public TEL(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new PhoneNumberBarcodeAdapter(context, (x) rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.TEXT", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TEXT extends ParsedResultType {
        public TEXT(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new TextBarcodeAdapter(context, rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.URI", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class URI extends ParsedResultType {
        public URI(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return UriBarcodeAdapterFactory.Companion.getInstance(context).create(rVar);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\bÊ\u0001\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType.WIFI", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/ParsedResultType;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "getBarcodeResult", "(Landroid/content/Context;LX2/r;)Lcom/samsung/android/app/sdk/deepsky/barcode/parser/adapter/BarcodeParsedResult;", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WIFI extends ParsedResultType {
        public WIFI(String str, int i2) {
            super(str, i2, (e) null);
        }

        public BarcodeParsedResult getBarcodeResult(Context context, r rVar) {
            j.e(context, "context");
            j.e(rVar, "parsedResult");
            return new WifiBarcodeAdapter(context, (F) rVar);
        }
    }

    static {
        ParsedResultType[] $values;
        $ENTRIES = c.t($values);
    }

    public abstract BarcodeParsedResult getBarcodeResult(Context context, r rVar);
}
