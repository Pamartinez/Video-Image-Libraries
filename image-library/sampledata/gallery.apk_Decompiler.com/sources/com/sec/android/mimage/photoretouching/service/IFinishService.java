package com.sec.android.mimage.photoretouching.service;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IFinishService extends IInterface {
    public static final String DESCRIPTOR = "com.sec.android.mimage.photoretouching.service.IFinishService";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class _Parcel {
        /* access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i2) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i2);
                return;
            }
            parcel.writeInt(0);
        }
    }

    void addQuickView();

    void basicTypes(int i2, long j2, boolean z, float f, double d, String str);

    void hideBlurImage();

    void hideImage(int i2);

    void setBitmap(Bitmap bitmap);

    void setBoundary(Rect rect);

    void showBlurImage();

    void showBlurImageWithAnimation(int i2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IFinishService {
        public IBinder asBinder() {
            return null;
        }

        public void addQuickView() {
        }

        public void hideBlurImage() {
        }

        public void showBlurImage() {
        }

        public void hideImage(int i2) {
        }

        public void setBitmap(Bitmap bitmap) {
        }

        public void setBoundary(Rect rect) {
        }

        public void showBlurImageWithAnimation(int i2) {
        }

        public void basicTypes(int i2, long j2, boolean z, float f, double d, String str) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IFinishService {
        static final int TRANSACTION_addQuickView = 8;
        static final int TRANSACTION_basicTypes = 1;
        static final int TRANSACTION_hideBlurImage = 7;
        static final int TRANSACTION_hideImage = 6;
        static final int TRANSACTION_setBitmap = 3;
        static final int TRANSACTION_setBoundary = 2;
        static final int TRANSACTION_showBlurImage = 4;
        static final int TRANSACTION_showBlurImageWithAnimation = 5;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IFinishService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public void addQuickView() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFinishService.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void basicTypes(int i2, long j2, boolean z, float f, double d, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFinishService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    obtain.writeLong(j2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeFloat(f);
                    obtain.writeDouble(d);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IFinishService.DESCRIPTOR;
            }

            public void hideBlurImage() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFinishService.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void hideImage(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFinishService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setBitmap(Bitmap bitmap) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFinishService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bitmap, 0);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setBoundary(Rect rect) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFinishService.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, rect, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showBlurImage() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFinishService.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showBlurImageWithAnimation(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFinishService.DESCRIPTOR);
                    obtain.writeInt(i2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IFinishService.DESCRIPTOR);
        }

        public static IFinishService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IFinishService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IFinishService)) {
                return new Proxy(iBinder);
            }
            return (IFinishService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            boolean z;
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(IFinishService.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                switch (i2) {
                    case 1:
                        int readInt = parcel.readInt();
                        long readLong = parcel.readLong();
                        if (parcel.readInt() != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        basicTypes(readInt, readLong, z, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                        parcel2.writeNoException();
                        break;
                    case 2:
                        setBoundary((Rect) _Parcel.readTypedObject(parcel, Rect.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 3:
                        setBitmap((Bitmap) _Parcel.readTypedObject(parcel, Bitmap.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 4:
                        showBlurImage();
                        parcel2.writeNoException();
                        break;
                    case 5:
                        showBlurImageWithAnimation(parcel.readInt());
                        parcel2.writeNoException();
                        break;
                    case 6:
                        hideImage(parcel.readInt());
                        parcel2.writeNoException();
                        break;
                    case 7:
                        hideBlurImage();
                        parcel2.writeNoException();
                        break;
                    case 8:
                        addQuickView();
                        parcel2.writeNoException();
                        break;
                    default:
                        return super.onTransact(i2, parcel, parcel2, i7);
                }
                return true;
            }
            parcel2.writeString(IFinishService.DESCRIPTOR);
            return true;
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
