package com.samsung.android.sivs.ai.sdkcommon.language;

import android.os.Parcel;
import com.sec.android.app.ve.vebgm.IBgmUriProviderService;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f0 implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1703a;
    public final /* synthetic */ Parcel b;

    public /* synthetic */ f0(Parcel parcel, int i2) {
        this.f1703a = i2;
        this.b = parcel;
    }

    public final void accept(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = (String) obj2;
        switch (this.f1703a) {
            case 0:
                Parcel parcel = this.b;
                parcel.writeString(str);
                parcel.writeString(str2);
                return;
            case 1:
                Parcel parcel2 = this.b;
                parcel2.writeString(str);
                parcel2.writeString(str2);
                return;
            case 2:
                Parcel parcel3 = this.b;
                parcel3.writeString(str);
                parcel3.writeString(str2);
                return;
            case 3:
                Parcel parcel4 = this.b;
                parcel4.writeString(str);
                parcel4.writeString(str2);
                return;
            case 4:
                Parcel parcel5 = this.b;
                parcel5.writeString(str);
                parcel5.writeString(str2);
                return;
            case 5:
                Parcel parcel6 = this.b;
                parcel6.writeString(str);
                parcel6.writeString(str2);
                return;
            case 6:
                Parcel parcel7 = this.b;
                parcel7.writeString(str);
                parcel7.writeString(str2);
                return;
            case 7:
                Parcel parcel8 = this.b;
                parcel8.writeString(str);
                parcel8.writeString(str2);
                return;
            case 8:
                Parcel parcel9 = this.b;
                parcel9.writeString(str);
                parcel9.writeString(str2);
                return;
            case 9:
                Parcel parcel10 = this.b;
                parcel10.writeString(str);
                parcel10.writeString(str2);
                return;
            default:
                IBgmUriProviderService.Stub.lambda$onTransact$0(this.b, str, str2);
                return;
        }
    }
}
