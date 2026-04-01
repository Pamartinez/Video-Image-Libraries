package com.samsung.android.sivs.ai.sdkcommon.tts;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.text.TextUtils;
import com.samsung.android.sivs.ai.sdkcommon.tts.ISynthesisCallback;
import com.samsung.android.sivs.ai.sdkcommon.tts.ISynthesizerInitCallback;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITextToSpeechService extends IInterface {
    public static final String DESCRIPTOR = "com.samsung.android.sivs.ai.sdkcommon.tts.ITextToSpeechService";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Default implements ITextToSpeechService {
        public IBinder asBinder() {
            return null;
        }

        public int createCustomVoiceSynthesizer(String str, int i2, ISynthesizerInitCallback iSynthesizerInitCallback) {
            return 0;
        }

        public int createSynthesizer(String str, int i2, ISynthesizerInitCallback iSynthesizerInitCallback) {
            return 0;
        }

        public List<String> getAvailableCustomVoiceLocales(String str, int i2) {
            return null;
        }

        public List<String> getAvailableLocales(String str, int i2) {
            return null;
        }

        public int getAvailableSynthesizerCount(String str, int i2) {
            return 0;
        }

        public String getCustomVoicePackageName(String str, int i2, String str2) {
            return null;
        }

        public List<String> getSupportCustomVoiceLocales(String str, int i2) {
            return null;
        }

        public List<String> getSupportLocales(String str, int i2) {
            return null;
        }

        public Voice getVoice(String str, int i2, String str2, String str3, String str4) {
            return null;
        }

        public List<Voice> getVoices(String str, int i2) {
            return null;
        }

        public List<Voice> getVoicesWithLocale(String str, int i2, String str2) {
            return null;
        }

        public int isLanguageAvailable(String str, int i2, String str2, String str3, String str4) {
            return 0;
        }

        public int releaseSynthesizer(String str, int i2) {
            return 0;
        }

        public int removeCustomVoice(String str, int i2, Voice voice) {
            return 0;
        }

        public int renameCustomVoice(String str, int i2, Voice voice, String str2) {
            return 0;
        }

        public int setVoice(String str, int i2, Voice voice) {
            return 0;
        }

        public int speak(String str, int i2, Voice voice, CharSequence charSequence, int i7, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback) {
            return 0;
        }

        public int stop(String str, int i2) {
            return 0;
        }

        public int synthesize(String str, int i2, Voice voice, CharSequence charSequence, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback) {
            return 0;
        }

        public int synthesizeToFile(String str, int i2, Voice voice, CharSequence charSequence, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback) {
            return 0;
        }
    }

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
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i2) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i7 = 0; i7 < size; i7++) {
                writeTypedObject(parcel, (Parcelable) list.get(i7), i2);
            }
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

    int createCustomVoiceSynthesizer(String str, int i2, ISynthesizerInitCallback iSynthesizerInitCallback);

    int createSynthesizer(String str, int i2, ISynthesizerInitCallback iSynthesizerInitCallback);

    List<String> getAvailableCustomVoiceLocales(String str, int i2);

    List<String> getAvailableLocales(String str, int i2);

    int getAvailableSynthesizerCount(String str, int i2);

    String getCustomVoicePackageName(String str, int i2, String str2);

    List<String> getSupportCustomVoiceLocales(String str, int i2);

    List<String> getSupportLocales(String str, int i2);

    Voice getVoice(String str, int i2, String str2, String str3, String str4);

    List<Voice> getVoices(String str, int i2);

    List<Voice> getVoicesWithLocale(String str, int i2, String str2);

    int isLanguageAvailable(String str, int i2, String str2, String str3, String str4);

    int releaseSynthesizer(String str, int i2);

    int removeCustomVoice(String str, int i2, Voice voice);

    int renameCustomVoice(String str, int i2, Voice voice, String str2);

    int setVoice(String str, int i2, Voice voice);

    int speak(String str, int i2, Voice voice, CharSequence charSequence, int i7, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback);

    int stop(String str, int i2);

    int synthesize(String str, int i2, Voice voice, CharSequence charSequence, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback);

    int synthesizeToFile(String str, int i2, Voice voice, CharSequence charSequence, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Stub extends Binder implements ITextToSpeechService {
        static final int TRANSACTION_createCustomVoiceSynthesizer = 15;
        static final int TRANSACTION_createSynthesizer = 7;
        static final int TRANSACTION_getAvailableCustomVoiceLocales = 16;
        static final int TRANSACTION_getAvailableLocales = 2;
        static final int TRANSACTION_getAvailableSynthesizerCount = 14;
        static final int TRANSACTION_getCustomVoicePackageName = 20;
        static final int TRANSACTION_getSupportCustomVoiceLocales = 17;
        static final int TRANSACTION_getSupportLocales = 1;
        static final int TRANSACTION_getVoice = 5;
        static final int TRANSACTION_getVoices = 3;
        static final int TRANSACTION_getVoicesWithLocale = 4;
        static final int TRANSACTION_isLanguageAvailable = 6;
        static final int TRANSACTION_releaseSynthesizer = 8;
        static final int TRANSACTION_removeCustomVoice = 18;
        static final int TRANSACTION_renameCustomVoice = 19;
        static final int TRANSACTION_setVoice = 9;
        static final int TRANSACTION_speak = 10;
        static final int TRANSACTION_stop = 13;
        static final int TRANSACTION_synthesize = 11;
        static final int TRANSACTION_synthesizeToFile = 12;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Proxy implements ITextToSpeechService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int createCustomVoiceSynthesizer(String str, int i2, ISynthesizerInitCallback iSynthesizerInitCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iSynthesizerInitCallback);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int createSynthesizer(String str, int i2, ISynthesizerInitCallback iSynthesizerInitCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iSynthesizerInitCallback);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getAvailableCustomVoiceLocales(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getAvailableLocales(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getAvailableSynthesizerCount(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getCustomVoicePackageName(String str, int i2, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeString(str2);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return ITextToSpeechService.DESCRIPTOR;
            }

            public List<String> getSupportCustomVoiceLocales(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getSupportLocales(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Voice getVoice(String str, int i2, String str2, String str3, String str4) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Voice) _Parcel.readTypedObject(obtain2, Voice.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Voice> getVoices(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Voice.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<Voice> getVoicesWithLocale(String str, int i2, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeString(str2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Voice.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int isLanguageAvailable(String str, int i2, String str2, String str3, String str4) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int releaseSynthesizer(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int removeCustomVoice(String str, int i2, Voice voice) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, voice, 0);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int renameCustomVoice(String str, int i2, Voice voice, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, voice, 0);
                    obtain.writeString(str2);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int setVoice(String str, int i2, Voice voice) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, voice, 0);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int speak(String str, int i2, Voice voice, CharSequence charSequence, int i7, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, voice, 0);
                    if (charSequence != null) {
                        obtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i7);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iSynthesisCallback);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int stop(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int synthesize(String str, int i2, Voice voice, CharSequence charSequence, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, voice, 0);
                    if (charSequence != null) {
                        obtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iSynthesisCallback);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public int synthesizeToFile(String str, int i2, Voice voice, CharSequence charSequence, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle, String str2, ISynthesisCallback iSynthesisCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITextToSpeechService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, voice, 0);
                    if (charSequence != null) {
                        obtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    _Parcel.writeTypedObject(obtain, parcelFileDescriptor, 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(iSynthesisCallback);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }
        }

        public Stub() {
            attachInterface(this, ITextToSpeechService.DESCRIPTOR);
        }

        public static ITextToSpeechService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITextToSpeechService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ITextToSpeechService)) {
                return new Proxy(iBinder);
            }
            return (ITextToSpeechService) queryLocalInterface;
        }

        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i7) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ITextToSpeechService.DESCRIPTOR);
            }
            if (i2 != 1598968902) {
                switch (i2) {
                    case 1:
                        List<String> supportLocales = getSupportLocales(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeStringList(supportLocales);
                        return true;
                    case 2:
                        List<String> availableLocales = getAvailableLocales(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeStringList(availableLocales);
                        return true;
                    case 3:
                        List<Voice> voices = getVoices(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        _Parcel.writeTypedList(parcel2, voices, 1);
                        return true;
                    case 4:
                        List<Voice> voicesWithLocale = getVoicesWithLocale(parcel.readString(), parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        _Parcel.writeTypedList(parcel2, voicesWithLocale, 1);
                        return true;
                    case 5:
                        Voice voice = getVoice(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        _Parcel.writeTypedObject(parcel2, voice, 1);
                        return true;
                    case 6:
                        int isLanguageAvailable = isLanguageAvailable(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isLanguageAvailable);
                        return true;
                    case 7:
                        int createSynthesizer = createSynthesizer(parcel.readString(), parcel.readInt(), ISynthesizerInitCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(createSynthesizer);
                        return true;
                    case 8:
                        int releaseSynthesizer = releaseSynthesizer(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(releaseSynthesizer);
                        return true;
                    case 9:
                        int voice2 = setVoice(parcel.readString(), parcel.readInt(), (Voice) _Parcel.readTypedObject(parcel, Voice.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(voice2);
                        return true;
                    case 10:
                        int speak = speak(parcel.readString(), parcel.readInt(), (Voice) _Parcel.readTypedObject(parcel, Voice.CREATOR), (CharSequence) _Parcel.readTypedObject(parcel, TextUtils.CHAR_SEQUENCE_CREATOR), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readString(), ISynthesisCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(speak);
                        return true;
                    case 11:
                        int synthesize = synthesize(parcel.readString(), parcel.readInt(), (Voice) _Parcel.readTypedObject(parcel, Voice.CREATOR), (CharSequence) _Parcel.readTypedObject(parcel, TextUtils.CHAR_SEQUENCE_CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readString(), ISynthesisCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(synthesize);
                        return true;
                    case 12:
                        int synthesizeToFile = synthesizeToFile(parcel.readString(), parcel.readInt(), (Voice) _Parcel.readTypedObject(parcel, Voice.CREATOR), (CharSequence) _Parcel.readTypedObject(parcel, TextUtils.CHAR_SEQUENCE_CREATOR), (ParcelFileDescriptor) _Parcel.readTypedObject(parcel, ParcelFileDescriptor.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readString(), ISynthesisCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(synthesizeToFile);
                        return true;
                    case 13:
                        int stop = stop(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(stop);
                        return true;
                    case 14:
                        int availableSynthesizerCount = getAvailableSynthesizerCount(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(availableSynthesizerCount);
                        return true;
                    case 15:
                        int createCustomVoiceSynthesizer = createCustomVoiceSynthesizer(parcel.readString(), parcel.readInt(), ISynthesizerInitCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(createCustomVoiceSynthesizer);
                        return true;
                    case 16:
                        List<String> availableCustomVoiceLocales = getAvailableCustomVoiceLocales(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeStringList(availableCustomVoiceLocales);
                        return true;
                    case 17:
                        List<String> supportCustomVoiceLocales = getSupportCustomVoiceLocales(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeStringList(supportCustomVoiceLocales);
                        return true;
                    case 18:
                        int removeCustomVoice = removeCustomVoice(parcel.readString(), parcel.readInt(), (Voice) _Parcel.readTypedObject(parcel, Voice.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(removeCustomVoice);
                        return true;
                    case 19:
                        int renameCustomVoice = renameCustomVoice(parcel.readString(), parcel.readInt(), (Voice) _Parcel.readTypedObject(parcel, Voice.CREATOR), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(renameCustomVoice);
                        return true;
                    case 20:
                        String customVoicePackageName = getCustomVoicePackageName(parcel.readString(), parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(customVoicePackageName);
                        return true;
                    default:
                        return super.onTransact(i2, parcel, parcel2, i7);
                }
            } else {
                parcel2.writeString(ITextToSpeechService.DESCRIPTOR);
                return true;
            }
        }

        public IBinder asBinder() {
            return this;
        }
    }
}
