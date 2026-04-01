package com.samsung.android.sdk.mobileservice.auth;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMobileServiceAuth extends IInterface {
    boolean getAccountValidation();

    Bundle getAuthInfoCached();

    Bundle getDeviceAuthInfoCached();

    boolean getDisclaimerAgreementForService(int i2);

    boolean getDisclaimerAgreementForSocial();

    Intent getIntentForAccountAccessTokenRequest(String str, String str2);

    Intent getIntentForAccountDisclaimerAgreement(String str, boolean z);

    Intent getIntentForAccountPasswordConfirmation(String str);

    Intent getIntentForAccountPasswordConfirmationPopup(String str);

    Intent getIntentForAccountProfileModification(String str);

    Intent getIntentForAccountSetupWizard(boolean z);

    Intent getIntentForAccountSignIn();

    Intent getIntentForAccountSignInPopup();

    Intent getIntentForAccountValidationRequest(String str, boolean z, boolean z3);

    Intent getIntentForSocialDisclaimerAgreement(boolean z, boolean z3);

    Intent getIntentForSocialDisclaimerDisplay();

    Intent getIntentForSocialRegistrationInformation();

    Intent getIntentForSocialSignUp();

    Intent getIntentForSocialTnC();

    boolean getNeedToShowSocialTncPopup();

    boolean isServiceRegistered(int i2);

    void requestAccessTokenForAccount(String str, String str2, Bundle bundle, IAccessTokenResultCallback iAccessTokenResultCallback);

    void requestAuthCode(String str, String str2, Bundle bundle, IAuthCodeResultCallback iAuthCodeResultCallback);

    void requestAuthInfo(String str, String str2, Bundle bundle, IAuthResultCallback iAuthResultCallback);

    void requestDisclaimerAgreementForThirdParty(String str, String str2, Bundle bundle, IDisclaimerAgreementForThirdPartyResultCallback iDisclaimerAgreementForThirdPartyResultCallback);

    void requestRenewAccessTokenForAccount(String str, String str2, Bundle bundle, String str3, IAccessTokenResultCallback iAccessTokenResultCallback);

    void requestValidation(String str, String str2, Bundle bundle, IValidationResultCallback iValidationResultCallback);

    boolean setDisclaimerAgreementForSocial(boolean z);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements IMobileServiceAuth {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth";
        static final int TRANSACTION_getAccountValidation = 23;
        static final int TRANSACTION_getAuthInfoCached = 18;
        static final int TRANSACTION_getDeviceAuthInfoCached = 19;
        static final int TRANSACTION_getDisclaimerAgreementForService = 25;
        static final int TRANSACTION_getDisclaimerAgreementForSocial = 24;
        static final int TRANSACTION_getIntentForAccountAccessTokenRequest = 1;
        static final int TRANSACTION_getIntentForAccountDisclaimerAgreement = 2;
        static final int TRANSACTION_getIntentForAccountPasswordConfirmation = 3;
        static final int TRANSACTION_getIntentForAccountPasswordConfirmationPopup = 4;
        static final int TRANSACTION_getIntentForAccountProfileModification = 5;
        static final int TRANSACTION_getIntentForAccountSetupWizard = 6;
        static final int TRANSACTION_getIntentForAccountSignIn = 7;
        static final int TRANSACTION_getIntentForAccountSignInPopup = 8;
        static final int TRANSACTION_getIntentForAccountValidationRequest = 9;
        static final int TRANSACTION_getIntentForSocialDisclaimerAgreement = 10;
        static final int TRANSACTION_getIntentForSocialDisclaimerDisplay = 11;
        static final int TRANSACTION_getIntentForSocialRegistrationInformation = 12;
        static final int TRANSACTION_getIntentForSocialSignUp = 13;
        static final int TRANSACTION_getIntentForSocialTnC = 27;
        static final int TRANSACTION_getNeedToShowSocialTncPopup = 26;
        static final int TRANSACTION_isServiceRegistered = 14;
        static final int TRANSACTION_requestAccessTokenForAccount = 15;
        static final int TRANSACTION_requestAuthCode = 16;
        static final int TRANSACTION_requestAuthInfo = 17;
        static final int TRANSACTION_requestDisclaimerAgreementForThirdParty = 22;
        static final int TRANSACTION_requestRenewAccessTokenForAccount = 20;
        static final int TRANSACTION_requestValidation = 21;
        static final int TRANSACTION_setDisclaimerAgreementForSocial = 28;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements IMobileServiceAuth {
            public static IMobileServiceAuth sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public boolean getAccountValidation() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAccountValidation();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getAuthInfoCached() {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(18, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = Stub.getDefaultImpl().getAuthInfoCached();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getDeviceAuthInfoCached() {
                Bundle bundle;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(19, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = Stub.getDefaultImpl().getDeviceAuthInfoCached();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getDisclaimerAgreementForService(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    boolean z = false;
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisclaimerAgreementForService(i2);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getDisclaimerAgreementForSocial() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisclaimerAgreementForSocial();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountAccessTokenRequest(String str, String str2) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountAccessTokenRequest(str, str2);
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountDisclaimerAgreement(String str, boolean z) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountDisclaimerAgreement(str, z);
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountPasswordConfirmation(String str) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountPasswordConfirmation(str);
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountPasswordConfirmationPopup(String str) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountPasswordConfirmationPopup(str);
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountProfileModification(String str) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountProfileModification(str);
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountSetupWizard(boolean z) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountSetupWizard(z);
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountSignIn() {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountSignIn();
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountSignInPopup() {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountSignInPopup();
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForAccountValidationRequest(String str, boolean z, boolean z3) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z3 ? 1 : 0);
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForAccountValidationRequest(str, z, z3);
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForSocialDisclaimerAgreement(boolean z, boolean z3) {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z3 ? 1 : 0);
                    if (this.mRemote.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForSocialDisclaimerAgreement(z, z3);
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForSocialDisclaimerDisplay() {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForSocialDisclaimerDisplay();
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForSocialRegistrationInformation() {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(12, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForSocialRegistrationInformation();
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForSocialSignUp() {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForSocialSignUp();
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent getIntentForSocialTnC() {
                Intent intent;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(27, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(obtain2);
                        } else {
                            intent = null;
                        }
                    } else {
                        intent = Stub.getDefaultImpl().getIntentForSocialTnC();
                    }
                    return intent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public boolean getNeedToShowSocialTncPopup() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNeedToShowSocialTncPopup();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isServiceRegistered(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    boolean z = false;
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isServiceRegistered(i2);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void requestAccessTokenForAccount(String str, String str2, Bundle bundle, IAccessTokenResultCallback iAccessTokenResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iAccessTokenResultCallback != null) {
                        iBinder = iAccessTokenResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(15, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestAccessTokenForAccount(str, str2, bundle, iAccessTokenResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestAuthCode(String str, String str2, Bundle bundle, IAuthCodeResultCallback iAuthCodeResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iAuthCodeResultCallback != null) {
                        iBinder = iAuthCodeResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(16, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestAuthCode(str, str2, bundle, iAuthCodeResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestAuthInfo(String str, String str2, Bundle bundle, IAuthResultCallback iAuthResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iAuthResultCallback != null) {
                        iBinder = iAuthResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(17, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestAuthInfo(str, str2, bundle, iAuthResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestDisclaimerAgreementForThirdParty(String str, String str2, Bundle bundle, IDisclaimerAgreementForThirdPartyResultCallback iDisclaimerAgreementForThirdPartyResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iDisclaimerAgreementForThirdPartyResultCallback != null) {
                        iBinder = iDisclaimerAgreementForThirdPartyResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(22, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestDisclaimerAgreementForThirdParty(str, str2, bundle, iDisclaimerAgreementForThirdPartyResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void requestRenewAccessTokenForAccount(String str, String str2, Bundle bundle, String str3, IAccessTokenResultCallback iAccessTokenResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str3);
                    if (iAccessTokenResultCallback != null) {
                        iBinder = iAccessTokenResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(20, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestRenewAccessTokenForAccount(str, str2, bundle, str3, iAccessTokenResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th2;
                }
            }

            public void requestValidation(String str, String str2, Bundle bundle, IValidationResultCallback iValidationResultCallback) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (iValidationResultCallback != null) {
                        iBinder = iValidationResultCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(21, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().requestValidation(str, str2, bundle, iValidationResultCallback);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public boolean setDisclaimerAgreementForSocial(boolean z) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    boolean z3 = false;
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDisclaimerAgreementForSocial(z);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z3 = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z3;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMobileServiceAuth asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMobileServiceAuth)) {
                return new Proxy(iBinder);
            }
            return (IMobileServiceAuth) queryLocalInterface;
        }

        public static IMobileServiceAuth getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IMobileServiceAuth iMobileServiceAuth) {
            if (Proxy.sDefaultImpl != null || iMobileServiceAuth == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMobileServiceAuth;
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v22, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) {
            /*
                r8 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth"
                if (r9 == r0) goto L_0x032e
                r0 = 0
                r3 = 0
                switch(r9) {
                    case 1: goto L_0x030f;
                    case 2: goto L_0x02eb;
                    case 3: goto L_0x02d0;
                    case 4: goto L_0x02b5;
                    case 5: goto L_0x029a;
                    case 6: goto L_0x027a;
                    case 7: goto L_0x0263;
                    case 8: goto L_0x024c;
                    case 9: goto L_0x021f;
                    case 10: goto L_0x01f6;
                    case 11: goto L_0x01df;
                    case 12: goto L_0x01c8;
                    case 13: goto L_0x01b1;
                    case 14: goto L_0x019f;
                    case 15: goto L_0x0177;
                    case 16: goto L_0x014f;
                    case 17: goto L_0x0127;
                    case 18: goto L_0x0110;
                    case 19: goto L_0x00f9;
                    case 20: goto L_0x00ca;
                    case 21: goto L_0x00a2;
                    case 22: goto L_0x007a;
                    case 23: goto L_0x006c;
                    case 24: goto L_0x005e;
                    case 25: goto L_0x004c;
                    case 26: goto L_0x003e;
                    case 27: goto L_0x0027;
                    case 28: goto L_0x0012;
                    default: goto L_0x000d;
                }
            L_0x000d:
                boolean r8 = super.onTransact(r9, r10, r11, r12)
                return r8
            L_0x0012:
                r10.enforceInterface(r2)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x001c
                r3 = r1
            L_0x001c:
                boolean r8 = r8.setDisclaimerAgreementForSocial(r3)
                r11.writeNoException()
                r11.writeInt(r8)
                return r1
            L_0x0027:
                r10.enforceInterface(r2)
                android.content.Intent r8 = r8.getIntentForSocialTnC()
                r11.writeNoException()
                if (r8 == 0) goto L_0x003a
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x003d
            L_0x003a:
                r11.writeInt(r3)
            L_0x003d:
                return r1
            L_0x003e:
                r10.enforceInterface(r2)
                boolean r8 = r8.getNeedToShowSocialTncPopup()
                r11.writeNoException()
                r11.writeInt(r8)
                return r1
            L_0x004c:
                r10.enforceInterface(r2)
                int r9 = r10.readInt()
                boolean r8 = r8.getDisclaimerAgreementForService(r9)
                r11.writeNoException()
                r11.writeInt(r8)
                return r1
            L_0x005e:
                r10.enforceInterface(r2)
                boolean r8 = r8.getDisclaimerAgreementForSocial()
                r11.writeNoException()
                r11.writeInt(r8)
                return r1
            L_0x006c:
                r10.enforceInterface(r2)
                boolean r8 = r8.getAccountValidation()
                r11.writeNoException()
                r11.writeInt(r8)
                return r1
            L_0x007a:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0093
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0093:
                android.os.IBinder r10 = r10.readStrongBinder()
                com.samsung.android.sdk.mobileservice.auth.IDisclaimerAgreementForThirdPartyResultCallback r10 = com.samsung.android.sdk.mobileservice.auth.IDisclaimerAgreementForThirdPartyResultCallback.Stub.asInterface(r10)
                r8.requestDisclaimerAgreementForThirdParty(r9, r12, r0, r10)
                r11.writeNoException()
                return r1
            L_0x00a2:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x00bb
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00bb:
                android.os.IBinder r10 = r10.readStrongBinder()
                com.samsung.android.sdk.mobileservice.auth.IValidationResultCallback r10 = com.samsung.android.sdk.mobileservice.auth.IValidationResultCallback.Stub.asInterface(r10)
                r8.requestValidation(r9, r12, r0, r10)
                r11.writeNoException()
                return r1
            L_0x00ca:
                r10.enforceInterface(r2)
                java.lang.String r3 = r10.readString()
                java.lang.String r4 = r10.readString()
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x00e4
                android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00e4:
                r5 = r0
                java.lang.String r6 = r10.readString()
                android.os.IBinder r9 = r10.readStrongBinder()
                com.samsung.android.sdk.mobileservice.auth.IAccessTokenResultCallback r7 = com.samsung.android.sdk.mobileservice.auth.IAccessTokenResultCallback.Stub.asInterface(r9)
                r2 = r8
                r2.requestRenewAccessTokenForAccount(r3, r4, r5, r6, r7)
                r11.writeNoException()
                return r1
            L_0x00f9:
                r10.enforceInterface(r2)
                android.os.Bundle r8 = r8.getDeviceAuthInfoCached()
                r11.writeNoException()
                if (r8 == 0) goto L_0x010c
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x010f
            L_0x010c:
                r11.writeInt(r3)
            L_0x010f:
                return r1
            L_0x0110:
                r10.enforceInterface(r2)
                android.os.Bundle r8 = r8.getAuthInfoCached()
                r11.writeNoException()
                if (r8 == 0) goto L_0x0123
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x0126
            L_0x0123:
                r11.writeInt(r3)
            L_0x0126:
                return r1
            L_0x0127:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0140
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0140:
                android.os.IBinder r10 = r10.readStrongBinder()
                com.samsung.android.sdk.mobileservice.auth.IAuthResultCallback r10 = com.samsung.android.sdk.mobileservice.auth.IAuthResultCallback.Stub.asInterface(r10)
                r8.requestAuthInfo(r9, r12, r0, r10)
                r11.writeNoException()
                return r1
            L_0x014f:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0168
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0168:
                android.os.IBinder r10 = r10.readStrongBinder()
                com.samsung.android.sdk.mobileservice.auth.IAuthCodeResultCallback r10 = com.samsung.android.sdk.mobileservice.auth.IAuthCodeResultCallback.Stub.asInterface(r10)
                r8.requestAuthCode(r9, r12, r0, r10)
                r11.writeNoException()
                return r1
            L_0x0177:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0190
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0190:
                android.os.IBinder r10 = r10.readStrongBinder()
                com.samsung.android.sdk.mobileservice.auth.IAccessTokenResultCallback r10 = com.samsung.android.sdk.mobileservice.auth.IAccessTokenResultCallback.Stub.asInterface(r10)
                r8.requestAccessTokenForAccount(r9, r12, r0, r10)
                r11.writeNoException()
                return r1
            L_0x019f:
                r10.enforceInterface(r2)
                int r9 = r10.readInt()
                boolean r8 = r8.isServiceRegistered(r9)
                r11.writeNoException()
                r11.writeInt(r8)
                return r1
            L_0x01b1:
                r10.enforceInterface(r2)
                android.content.Intent r8 = r8.getIntentForSocialSignUp()
                r11.writeNoException()
                if (r8 == 0) goto L_0x01c4
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x01c7
            L_0x01c4:
                r11.writeInt(r3)
            L_0x01c7:
                return r1
            L_0x01c8:
                r10.enforceInterface(r2)
                android.content.Intent r8 = r8.getIntentForSocialRegistrationInformation()
                r11.writeNoException()
                if (r8 == 0) goto L_0x01db
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x01de
            L_0x01db:
                r11.writeInt(r3)
            L_0x01de:
                return r1
            L_0x01df:
                r10.enforceInterface(r2)
                android.content.Intent r8 = r8.getIntentForSocialDisclaimerDisplay()
                r11.writeNoException()
                if (r8 == 0) goto L_0x01f2
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x01f5
            L_0x01f2:
                r11.writeInt(r3)
            L_0x01f5:
                return r1
            L_0x01f6:
                r10.enforceInterface(r2)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x0201
                r9 = r1
                goto L_0x0202
            L_0x0201:
                r9 = r3
            L_0x0202:
                int r10 = r10.readInt()
                if (r10 == 0) goto L_0x020a
                r10 = r1
                goto L_0x020b
            L_0x020a:
                r10 = r3
            L_0x020b:
                android.content.Intent r8 = r8.getIntentForSocialDisclaimerAgreement(r9, r10)
                r11.writeNoException()
                if (r8 == 0) goto L_0x021b
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x021e
            L_0x021b:
                r11.writeInt(r3)
            L_0x021e:
                return r1
            L_0x021f:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x022e
                r12 = r1
                goto L_0x022f
            L_0x022e:
                r12 = r3
            L_0x022f:
                int r10 = r10.readInt()
                if (r10 == 0) goto L_0x0237
                r10 = r1
                goto L_0x0238
            L_0x0237:
                r10 = r3
            L_0x0238:
                android.content.Intent r8 = r8.getIntentForAccountValidationRequest(r9, r12, r10)
                r11.writeNoException()
                if (r8 == 0) goto L_0x0248
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x024b
            L_0x0248:
                r11.writeInt(r3)
            L_0x024b:
                return r1
            L_0x024c:
                r10.enforceInterface(r2)
                android.content.Intent r8 = r8.getIntentForAccountSignInPopup()
                r11.writeNoException()
                if (r8 == 0) goto L_0x025f
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x0262
            L_0x025f:
                r11.writeInt(r3)
            L_0x0262:
                return r1
            L_0x0263:
                r10.enforceInterface(r2)
                android.content.Intent r8 = r8.getIntentForAccountSignIn()
                r11.writeNoException()
                if (r8 == 0) goto L_0x0276
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x0279
            L_0x0276:
                r11.writeInt(r3)
            L_0x0279:
                return r1
            L_0x027a:
                r10.enforceInterface(r2)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x0285
                r9 = r1
                goto L_0x0286
            L_0x0285:
                r9 = r3
            L_0x0286:
                android.content.Intent r8 = r8.getIntentForAccountSetupWizard(r9)
                r11.writeNoException()
                if (r8 == 0) goto L_0x0296
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x0299
            L_0x0296:
                r11.writeInt(r3)
            L_0x0299:
                return r1
            L_0x029a:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                android.content.Intent r8 = r8.getIntentForAccountProfileModification(r9)
                r11.writeNoException()
                if (r8 == 0) goto L_0x02b1
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x02b4
            L_0x02b1:
                r11.writeInt(r3)
            L_0x02b4:
                return r1
            L_0x02b5:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                android.content.Intent r8 = r8.getIntentForAccountPasswordConfirmationPopup(r9)
                r11.writeNoException()
                if (r8 == 0) goto L_0x02cc
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x02cf
            L_0x02cc:
                r11.writeInt(r3)
            L_0x02cf:
                return r1
            L_0x02d0:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                android.content.Intent r8 = r8.getIntentForAccountPasswordConfirmation(r9)
                r11.writeNoException()
                if (r8 == 0) goto L_0x02e7
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x02ea
            L_0x02e7:
                r11.writeInt(r3)
            L_0x02ea:
                return r1
            L_0x02eb:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                int r10 = r10.readInt()
                if (r10 == 0) goto L_0x02fa
                r10 = r1
                goto L_0x02fb
            L_0x02fa:
                r10 = r3
            L_0x02fb:
                android.content.Intent r8 = r8.getIntentForAccountDisclaimerAgreement(r9, r10)
                r11.writeNoException()
                if (r8 == 0) goto L_0x030b
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x030e
            L_0x030b:
                r11.writeInt(r3)
            L_0x030e:
                return r1
            L_0x030f:
                r10.enforceInterface(r2)
                java.lang.String r9 = r10.readString()
                java.lang.String r10 = r10.readString()
                android.content.Intent r8 = r8.getIntentForAccountAccessTokenRequest(r9, r10)
                r11.writeNoException()
                if (r8 == 0) goto L_0x032a
                r11.writeInt(r1)
                r8.writeToParcel(r11, r1)
                goto L_0x032d
            L_0x032a:
                r11.writeInt(r3)
            L_0x032d:
                return r1
            L_0x032e:
                r11.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.mobileservice.auth.IMobileServiceAuth.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements IMobileServiceAuth {
        public IBinder asBinder() {
            return null;
        }

        public boolean getAccountValidation() {
            return false;
        }

        public Bundle getAuthInfoCached() {
            return null;
        }

        public Bundle getDeviceAuthInfoCached() {
            return null;
        }

        public boolean getDisclaimerAgreementForService(int i2) {
            return false;
        }

        public boolean getDisclaimerAgreementForSocial() {
            return false;
        }

        public Intent getIntentForAccountAccessTokenRequest(String str, String str2) {
            return null;
        }

        public Intent getIntentForAccountDisclaimerAgreement(String str, boolean z) {
            return null;
        }

        public Intent getIntentForAccountPasswordConfirmation(String str) {
            return null;
        }

        public Intent getIntentForAccountPasswordConfirmationPopup(String str) {
            return null;
        }

        public Intent getIntentForAccountProfileModification(String str) {
            return null;
        }

        public Intent getIntentForAccountSetupWizard(boolean z) {
            return null;
        }

        public Intent getIntentForAccountSignIn() {
            return null;
        }

        public Intent getIntentForAccountSignInPopup() {
            return null;
        }

        public Intent getIntentForAccountValidationRequest(String str, boolean z, boolean z3) {
            return null;
        }

        public Intent getIntentForSocialDisclaimerAgreement(boolean z, boolean z3) {
            return null;
        }

        public Intent getIntentForSocialDisclaimerDisplay() {
            return null;
        }

        public Intent getIntentForSocialRegistrationInformation() {
            return null;
        }

        public Intent getIntentForSocialSignUp() {
            return null;
        }

        public Intent getIntentForSocialTnC() {
            return null;
        }

        public boolean getNeedToShowSocialTncPopup() {
            return false;
        }

        public boolean isServiceRegistered(int i2) {
            return false;
        }

        public boolean setDisclaimerAgreementForSocial(boolean z) {
            return false;
        }

        public void requestAccessTokenForAccount(String str, String str2, Bundle bundle, IAccessTokenResultCallback iAccessTokenResultCallback) {
        }

        public void requestAuthCode(String str, String str2, Bundle bundle, IAuthCodeResultCallback iAuthCodeResultCallback) {
        }

        public void requestAuthInfo(String str, String str2, Bundle bundle, IAuthResultCallback iAuthResultCallback) {
        }

        public void requestDisclaimerAgreementForThirdParty(String str, String str2, Bundle bundle, IDisclaimerAgreementForThirdPartyResultCallback iDisclaimerAgreementForThirdPartyResultCallback) {
        }

        public void requestValidation(String str, String str2, Bundle bundle, IValidationResultCallback iValidationResultCallback) {
        }

        public void requestRenewAccessTokenForAccount(String str, String str2, Bundle bundle, String str3, IAccessTokenResultCallback iAccessTokenResultCallback) {
        }
    }
}
