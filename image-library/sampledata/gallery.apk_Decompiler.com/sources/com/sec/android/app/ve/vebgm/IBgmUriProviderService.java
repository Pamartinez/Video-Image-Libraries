package com.sec.android.app.ve.vebgm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.samsung.android.sivs.ai.sdkcommon.language.f0;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBgmUriProviderService extends IInterface {
    public static final String DESCRIPTOR = "com.sec.android.app.ve.vebgm.IBgmUriProviderService";

    List<String> arrangeBgmUri(String str, String str2);

    void downloadAllBgms();

    void downloadSingleBgm(String str, String str2);

    List<String> getBgmDownloadedNames();

    List<String> getCategories();

    Map<String, String> getDownloadedBgmNameWithCategory();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IBgmUriProviderService {
        public List<String> arrangeBgmUri(String str, String str2) {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }

        public List<String> getBgmDownloadedNames() {
            return null;
        }

        public List<String> getCategories() {
            return null;
        }

        public Map<String, String> getDownloadedBgmNameWithCategory() {
            return null;
        }

        public void downloadAllBgms() {
        }

        public void downloadSingleBgm(String str, String str2) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IBgmUriProviderService {
        static final int TRANSACTION_arrangeBgmUri = 1;
        static final int TRANSACTION_downloadAllBgms = 4;
        static final int TRANSACTION_downloadSingleBgm = 6;
        static final int TRANSACTION_getBgmDownloadedNames = 2;
        static final int TRANSACTION_getCategories = 3;
        static final int TRANSACTION_getDownloadedBgmNameWithCategory = 5;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IBgmUriProviderService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public List<String> arrangeBgmUri(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBgmUriProviderService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void downloadAllBgms() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBgmUriProviderService.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void downloadSingleBgm(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBgmUriProviderService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getBgmDownloadedNames() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBgmUriProviderService.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getCategories() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBgmUriProviderService.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Map<String, String> getDownloadedBgmNameWithCategory() {
                HashMap hashMap;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBgmUriProviderService.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (readInt < 0) {
                        hashMap = null;
                    } else {
                        hashMap = new HashMap();
                    }
                    IntStream.range(0, readInt).forEach(new a(obtain2, hashMap));
                    return hashMap;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IBgmUriProviderService.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, IBgmUriProviderService.DESCRIPTOR);
        }

        public static IBgmUriProviderService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBgmUriProviderService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBgmUriProviderService)) {
                return new Proxy(iBinder);
            }
            return (IBgmUriProviderService) queryLocalInterface;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$onTransact$0(Parcel parcel, String str, String str2) {
            parcel.writeString(str);
            parcel.writeString(str2);
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IBgmUriProviderService.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                switch (i2) {
                    case 1:
                        List<String> arrangeBgmUri = arrangeBgmUri(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeStringList(arrangeBgmUri);
                        return true;
                    case 2:
                        List<String> bgmDownloadedNames = getBgmDownloadedNames();
                        parcel2.writeNoException();
                        parcel2.writeStringList(bgmDownloadedNames);
                        return true;
                    case 3:
                        List<String> categories = getCategories();
                        parcel2.writeNoException();
                        parcel2.writeStringList(categories);
                        return true;
                    case 4:
                        downloadAllBgms();
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        Map<String, String> downloadedBgmNameWithCategory = getDownloadedBgmNameWithCategory();
                        parcel2.writeNoException();
                        if (downloadedBgmNameWithCategory == null) {
                            parcel2.writeInt(-1);
                            return true;
                        }
                        parcel2.writeInt(downloadedBgmNameWithCategory.size());
                        downloadedBgmNameWithCategory.forEach(new f0(parcel2, 10));
                        return true;
                    case 6:
                        downloadSingleBgm(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i2, parcel, parcel2, i7);
                }
            } else {
                parcel2.writeString(IBgmUriProviderService.DESCRIPTOR);
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
