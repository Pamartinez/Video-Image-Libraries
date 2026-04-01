package com.samsung.android.sdk.mobileservice.profile;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Profile implements Parcelable {
    public static final int ACTION_TYPE_DELETE = 2;
    public static final int ACTION_TYPE_MODIFY = 1;
    public static final int ACTION_TYPE_NONE = 0;
    public static final int CALENDAR_TYPE_ADDED_VERSION = 4;
    public static final String CALENDAR_TYPE_LEAP = "2";
    public static final String CALENDAR_TYPE_LUNAR = "1";
    public static final String CALENDAR_TYPE_SOLAR = "0";
    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        public Profile[] newArray(int i2) {
            return new Profile[i2];
        }
    };
    public static final int MAKE_BIRTHDAY_LENGTH_VALID_VERSION = 6;
    public static final int PHOTO_FILE_URI_ADDED_VERSION = 5;
    private static final int PLACE_DELETED_VERSION = 3;
    public static final int PROFILE_VERSION = 6;
    /* access modifiers changed from: private */
    public static int sConnectedProfileVersion = 6;
    private AccountBirthday mAccountBirthday;
    private AccountName mAccountName;
    private Birthday mBirthday;
    private EmailAddress mEmailAddress;
    private Event mEvent;
    private Gender mGender;
    private Health mHealth;
    private MessengerAccount mMessengerAccount;
    private Name mName;
    private Nickname mNickname;
    private Note mNote;
    private Organization mOrganization;
    private PhoneNumber mPhoneNumber;
    private Photo mPhoto;
    private StatusMessage mStatusMessage;
    private WebAddress mWebAddress;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PlaceData implements Parcelable {
        public static final Parcelable.Creator<PlaceData> CREATOR = new Parcelable.Creator<PlaceData>() {
            public PlaceData createFromParcel(Parcel parcel) {
                return new PlaceData(parcel);
            }

            public PlaceData[] newArray(int i2) {
                return new PlaceData[i2];
            }
        };
        private String mAddress;
        private String mBluetoothMacAddress;
        private String mBluetoothName;
        private int mCategory;
        private Double mLatitude;
        private String mLocationType;
        private boolean mLocked;
        private Double mLongitude;
        private String mName;
        private String mPlaceKey;
        private Long mTimeStamp;
        private int mType;
        private String mWifiBssId;
        private String mWifiName;

        public PlaceData(Parcel parcel) {
            readFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void readFromParcel(Parcel parcel) {
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.mLocked = z;
            this.mPlaceKey = parcel.readString();
            this.mName = parcel.readString();
            this.mCategory = parcel.readInt();
            this.mType = parcel.readInt();
            this.mLocationType = parcel.readString();
            this.mAddress = parcel.readString();
            this.mLatitude = Double.valueOf(parcel.readDouble());
            this.mLongitude = Double.valueOf(parcel.readDouble());
            this.mWifiName = parcel.readString();
            this.mWifiBssId = parcel.readString();
            this.mBluetoothName = parcel.readString();
            this.mBluetoothMacAddress = parcel.readString();
            this.mTimeStamp = Long.valueOf(parcel.readLong());
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeString(this.mPlaceKey);
            parcel.writeString(this.mName);
            parcel.writeInt(this.mCategory);
            parcel.writeInt(this.mType);
            parcel.writeString(this.mLocationType);
            parcel.writeString(this.mAddress);
            parcel.writeDouble(this.mLatitude.doubleValue());
            parcel.writeDouble(this.mLongitude.doubleValue());
            parcel.writeString(this.mWifiName);
            parcel.writeString(this.mWifiBssId);
            parcel.writeString(this.mBluetoothName);
            parcel.writeString(this.mBluetoothMacAddress);
            parcel.writeLong(this.mTimeStamp.longValue());
        }
    }

    public Profile(Parcel parcel) {
        readFromParcel(parcel);
    }

    private void readDeprecatedPlaceData(Parcel parcel) {
        parcel.readInt();
        parcel.readInt();
        parcel.readInt();
        parcel.readTypedList(new ArrayList(), PlaceData.CREATOR);
    }

    private void writeDeprecatedPlaceData(Parcel parcel) {
        parcel.writeInt(0);
        parcel.writeInt(0);
        parcel.writeInt(0);
        parcel.writeTypedList(new ArrayList());
    }

    public int describeContents() {
        return 0;
    }

    public AccountBirthday getAccountBirthdayInstance() {
        return this.mAccountBirthday;
    }

    public AccountName getAccountNameInstance() {
        return this.mAccountName;
    }

    public Birthday getBirthdayInstance() {
        return this.mBirthday;
    }

    public int getConnectedProfileVersion() {
        return sConnectedProfileVersion;
    }

    public EmailAddress getEmailAddressInstance() {
        return this.mEmailAddress;
    }

    public Event getEventInstance() {
        return this.mEvent;
    }

    public Gender getGenderInstance() {
        return this.mGender;
    }

    public Health getHealthInstance() {
        return this.mHealth;
    }

    public MessengerAccount getMessengerAccountInstance() {
        return this.mMessengerAccount;
    }

    public Name getNameInstance() {
        return this.mName;
    }

    public Nickname getNicknameInstance() {
        return this.mNickname;
    }

    public Note getNoteInstance() {
        return this.mNote;
    }

    public Organization getOrganizationInstance() {
        return this.mOrganization;
    }

    public PhoneNumber getPhoneNumberInstance() {
        return this.mPhoneNumber;
    }

    public Photo getPhotoInstance() {
        return this.mPhoto;
    }

    public StatusMessage getStatusMessageInstance() {
        return this.mStatusMessage;
    }

    public WebAddress getWebAddressInstance() {
        return this.mWebAddress;
    }

    public void readFromParcel(Parcel parcel) {
        sConnectedProfileVersion = parcel.readInt();
        this.mName = new Name(parcel);
        this.mAccountName = new AccountName(parcel);
        this.mBirthday = new Birthday(parcel);
        this.mAccountBirthday = new AccountBirthday(parcel);
        this.mNickname = new Nickname(parcel);
        this.mPhoto = new Photo(parcel);
        this.mOrganization = new Organization(parcel);
        this.mGender = new Gender(parcel);
        this.mStatusMessage = new StatusMessage(parcel);
        this.mNote = new Note(parcel);
        this.mMessengerAccount = new MessengerAccount(parcel);
        this.mPhoneNumber = new PhoneNumber(parcel);
        this.mEmailAddress = new EmailAddress(parcel);
        this.mWebAddress = new WebAddress(parcel);
        this.mEvent = new Event(parcel);
        this.mHealth = new Health(parcel);
        if (sConnectedProfileVersion < 3) {
            readDeprecatedPlaceData(parcel);
        }
    }

    public void setConnectedProfileVersion(int i2) {
        sConnectedProfileVersion = i2;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(6);
        this.mName.putParcelData(parcel);
        this.mAccountName.putParcelData(parcel);
        this.mBirthday.putParcelData(parcel);
        this.mAccountBirthday.putParcelData(parcel);
        this.mNickname.putParcelData(parcel);
        this.mPhoto.putParcelData(parcel);
        this.mOrganization.putParcelData(parcel);
        this.mGender.putParcelData(parcel);
        this.mStatusMessage.putParcelData(parcel);
        this.mNote.putParcelData(parcel);
        this.mMessengerAccount.putParcelData(parcel);
        this.mPhoneNumber.putParcelData(parcel);
        this.mEmailAddress.putParcelData(parcel);
        this.mWebAddress.putParcelData(parcel);
        this.mEvent.putParcelData(parcel);
        this.mHealth.putParcelData(parcel);
        if (sConnectedProfileVersion < 3) {
            writeDeprecatedPlaceData(parcel);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Birthday {
        private int mAction;
        private String mCalendarType;
        private String mDay;
        private boolean mLocked;
        private String mMonth;
        private String mYear;

        public Birthday(Parcel parcel) {
            readFromParcel(parcel);
        }

        public int getAction() {
            return this.mAction;
        }

        public String getCalendarType() {
            return this.mCalendarType;
        }

        public String getDay() {
            return this.mDay;
        }

        public String getMonth() {
            return this.mMonth;
        }

        public String getYear() {
            return this.mYear;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mYear);
            parcel.writeString(this.mMonth);
            parcel.writeString(this.mDay);
            if (Profile.sConnectedProfileVersion >= 4) {
                parcel.writeString(this.mCalendarType);
            }
        }

        public void readFromParcel(Parcel parcel) {
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.mLocked = z;
            this.mAction = parcel.readInt();
            this.mYear = parcel.readString();
            this.mMonth = parcel.readString();
            this.mDay = parcel.readString();
            if (Profile.sConnectedProfileVersion >= 4) {
                this.mCalendarType = parcel.readString();
            }
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setBirthday(String str, String str2, String str3) {
            this.mYear = str;
            this.mMonth = str2;
            this.mDay = str3;
        }

        public void setCalendarType(String str) {
            this.mCalendarType = str;
        }

        public void setDay(String str) {
            this.mDay = str;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setMonth(String str) {
            this.mMonth = str;
        }

        public void setYear(String str) {
            this.mYear = str;
        }

        public Birthday() {
        }

        public void setBirthday(String str, String str2, String str3, String str4) {
            this.mYear = str;
            this.mMonth = str2;
            this.mDay = str3;
            this.mCalendarType = str4;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmailAddress {
        private int mAction;
        private ArrayList<EmailAddressData> mEmailAddressData;

        public EmailAddress() {
            this.mEmailAddressData = new ArrayList<>();
        }

        public int getAction() {
            return this.mAction;
        }

        public ArrayList<EmailAddressData> getEmailAddressData() {
            return this.mEmailAddressData;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeInt(this.mAction);
            parcel.writeTypedList(this.mEmailAddressData);
        }

        public void readFromParcel(Parcel parcel) {
            this.mAction = parcel.readInt();
            ArrayList<EmailAddressData> arrayList = new ArrayList<>();
            this.mEmailAddressData = arrayList;
            parcel.readTypedList(arrayList, EmailAddressData.CREATOR);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public EmailAddress(Parcel parcel) {
            readFromParcel(parcel);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmailAddressData implements Parcelable {
        public static final Parcelable.Creator<EmailAddressData> CREATOR = new Parcelable.Creator<EmailAddressData>() {
            public EmailAddressData createFromParcel(Parcel parcel) {
                return new EmailAddressData(parcel);
            }

            public EmailAddressData[] newArray(int i2) {
                return new EmailAddressData[i2];
            }
        };
        public static final String TYPE_CUSTOM = "custom";
        public static final String TYPE_HOME = "home";
        public static final String TYPE_OTHER = "other";
        public static final String TYPE_REPRESENTATIVE = "representative";
        public static final String TYPE_WORK = "work";
        private String mLabel;
        private boolean mLocked;
        private String mType;
        private String mValue;

        public EmailAddressData(Parcel parcel) {
            readFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public String getEmailAddress() {
            return this.mValue;
        }

        public String getLabel() {
            return this.mLabel;
        }

        public String getType() {
            return this.mType;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void readFromParcel(Parcel parcel) {
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.mLocked = z;
            this.mValue = parcel.readString();
            this.mType = parcel.readString();
            this.mLabel = parcel.readString();
        }

        public boolean setEmailAddress(boolean z, String str, String str2, String str3) {
            this.mLocked = z;
            this.mType = str2;
            this.mValue = str;
            this.mLabel = str3;
            return true;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeString(this.mValue);
            parcel.writeString(this.mType);
            parcel.writeString(this.mLabel);
        }

        public EmailAddressData() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EventData implements Parcelable {
        public static final Parcelable.Creator<EventData> CREATOR = new Parcelable.Creator<EventData>() {
            public EventData createFromParcel(Parcel parcel) {
                return new EventData(parcel);
            }

            public EventData[] newArray(int i2) {
                return new EventData[i2];
            }
        };
        public static final String TYPE_ANNIVERSARY = "anniversary";
        public static final String TYPE_CUSTOM = "custom";
        public static final String TYPE_OTHER = "other";
        private String mCalendarType;
        private String mLabel;
        private boolean mLocked;
        private String mType;
        private String mValue;

        public EventData(Parcel parcel) {
            readFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public String getCalendarType() {
            return this.mCalendarType;
        }

        public String getEvent() {
            return this.mValue;
        }

        public String getLabel() {
            return this.mLabel;
        }

        public String getType() {
            return this.mType;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void readFromParcel(Parcel parcel) {
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.mLocked = z;
            this.mValue = parcel.readString();
            this.mType = parcel.readString();
            this.mLabel = parcel.readString();
            if (Profile.sConnectedProfileVersion >= 4) {
                this.mCalendarType = parcel.readString();
            }
        }

        public boolean setEvent(String str, String str2, String str3) {
            this.mType = str2;
            this.mValue = str;
            this.mLabel = str3;
            return true;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeString(this.mValue);
            parcel.writeString(this.mType);
            parcel.writeString(this.mLabel);
            if (Profile.sConnectedProfileVersion >= 4) {
                parcel.writeString(this.mCalendarType);
            }
        }

        public EventData() {
        }

        public boolean setEvent(String str, String str2, String str3, String str4) {
            this.mType = str2;
            this.mValue = str;
            this.mLabel = str3;
            this.mCalendarType = str4;
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MessengerAccount {
        private int mAction;
        private ArrayList<MessengerAccountData> mMessengerAccountData;

        public MessengerAccount() {
            this.mMessengerAccountData = new ArrayList<>();
        }

        public int getAction() {
            return this.mAction;
        }

        public ArrayList<MessengerAccountData> getMessengerAccountData() {
            return this.mMessengerAccountData;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeInt(this.mAction);
            parcel.writeTypedList(this.mMessengerAccountData);
        }

        public void readFromParcel(Parcel parcel) {
            this.mAction = parcel.readInt();
            ArrayList<MessengerAccountData> arrayList = new ArrayList<>();
            this.mMessengerAccountData = arrayList;
            parcel.readTypedList(arrayList, MessengerAccountData.CREATOR);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public MessengerAccount(Parcel parcel) {
            readFromParcel(parcel);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MessengerAccountData implements Parcelable {
        public static final Parcelable.Creator<MessengerAccountData> CREATOR = new Parcelable.Creator<MessengerAccountData>() {
            public MessengerAccountData createFromParcel(Parcel parcel) {
                return new MessengerAccountData(parcel);
            }

            public MessengerAccountData[] newArray(int i2) {
                return new MessengerAccountData[i2];
            }
        };
        public static final String TYPE_AIM = "AIM";
        public static final String TYPE_CUSTOM = "custom";
        public static final String TYPE_FACEBOOK = "facebook";
        public static final String TYPE_HANGOUTS = "hangouts";
        public static final String TYPE_ICQ = "ICQ";
        public static final String TYPE_JABBER = "jabber";
        public static final String TYPE_QQ = "QQ";
        public static final String TYPE_SKYPE = "skype";
        public static final String TYPE_WHATSAPP = "whatsApp";
        public static final String TYPE_WINDOWS_LIVE = "windowsLive";
        public static final String TYPE_YAHOO = "yahoo";
        private String mLabel;
        private boolean mLocked;
        private String mType;
        private String mValue;

        public MessengerAccountData(Parcel parcel) {
            readFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public String getLabel() {
            return this.mLabel;
        }

        public String getMessengerAccount() {
            return this.mValue;
        }

        public String getType() {
            return this.mType;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void readFromParcel(Parcel parcel) {
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.mLocked = z;
            this.mValue = parcel.readString();
            this.mType = parcel.readString();
            this.mLabel = parcel.readString();
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public boolean setMessengerAccount(String str, String str2, String str3) {
            this.mType = str2;
            this.mValue = str;
            this.mLabel = str3;
            return true;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeString(this.mValue);
            parcel.writeString(this.mType);
            parcel.writeString(this.mLabel);
        }

        public MessengerAccountData() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PhoneNumber {
        private int mAction;
        private ArrayList<PhoneNumberData> mPhoneNumberData;

        public PhoneNumber() {
            this.mPhoneNumberData = new ArrayList<>();
        }

        public int getAction() {
            return this.mAction;
        }

        public ArrayList<PhoneNumberData> getPhoneNumberData() {
            return this.mPhoneNumberData;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeInt(this.mAction);
            parcel.writeTypedList(this.mPhoneNumberData);
        }

        public void readFromParcel(Parcel parcel) {
            this.mAction = parcel.readInt();
            ArrayList<PhoneNumberData> arrayList = new ArrayList<>();
            this.mPhoneNumberData = arrayList;
            parcel.readTypedList(arrayList, PhoneNumberData.CREATOR);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public PhoneNumber(Parcel parcel) {
            readFromParcel(parcel);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PhoneNumberData implements Parcelable {
        public static final Parcelable.Creator<PhoneNumberData> CREATOR = new Parcelable.Creator<PhoneNumberData>() {
            public PhoneNumberData createFromParcel(Parcel parcel) {
                return new PhoneNumberData(parcel);
            }

            public PhoneNumberData[] newArray(int i2) {
                return new PhoneNumberData[i2];
            }
        };
        public static final String TYPE_CALLBACK = "callback";
        public static final String TYPE_CUSTOM = "custom";
        public static final String TYPE_HOME = "home";
        public static final String TYPE_HOME_FAX = "homeFax";
        public static final String TYPE_MOBILE = "mobile";
        public static final String TYPE_OTHER = "other";
        public static final String TYPE_PAGER = "pager";
        public static final String TYPE_REPRESENTATIVE = "representative";
        public static final String TYPE_WORK = "work";
        public static final String TYPE_WORK_FAX = "workFax";
        private String mLabel;
        private boolean mLocked;
        private String mType;
        private String mValue;

        public PhoneNumberData(Parcel parcel) {
            readFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public String getLabel() {
            return this.mLabel;
        }

        public String getPhoneNumber() {
            return this.mValue;
        }

        public String getType() {
            return this.mType;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void readFromParcel(Parcel parcel) {
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.mLocked = z;
            this.mValue = parcel.readString();
            this.mType = parcel.readString();
            this.mLabel = parcel.readString();
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public boolean setPhoneNumber(boolean z, String str, String str2, String str3) {
            this.mLocked = z;
            this.mType = str2;
            this.mValue = str;
            this.mLabel = str3;
            return true;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeString(this.mValue);
            parcel.writeString(this.mType);
            parcel.writeString(this.mLabel);
        }

        public PhoneNumberData() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WebAddress {
        private int mAction;
        private ArrayList<WebAddressData> mWebAddressData;

        public WebAddress() {
            this.mWebAddressData = new ArrayList<>();
        }

        public int getAction() {
            return this.mAction;
        }

        public ArrayList<WebAddressData> getWebAddressData() {
            return this.mWebAddressData;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeInt(this.mAction);
            parcel.writeTypedList(this.mWebAddressData);
        }

        public void readFromParcel(Parcel parcel) {
            this.mAction = parcel.readInt();
            ArrayList<WebAddressData> arrayList = new ArrayList<>();
            this.mWebAddressData = arrayList;
            parcel.readTypedList(arrayList, WebAddressData.CREATOR);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public WebAddress(Parcel parcel) {
            readFromParcel(parcel);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WebAddressData implements Parcelable {
        public static final Parcelable.Creator<WebAddressData> CREATOR = new Parcelable.Creator<WebAddressData>() {
            public WebAddressData createFromParcel(Parcel parcel) {
                return new WebAddressData(parcel);
            }

            public WebAddressData[] newArray(int i2) {
                return new WebAddressData[i2];
            }
        };
        private boolean mLocked;
        private String mValue;

        public WebAddressData(Parcel parcel) {
            readFromParcel(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public String getWebAddress() {
            return this.mValue;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void readFromParcel(Parcel parcel) {
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.mLocked = z;
            this.mValue = parcel.readString();
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setWebAddress(String str) {
            this.mValue = str;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeString(this.mValue);
        }

        public WebAddressData() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AccountBirthday {
        private int mAction;
        private String mDay;
        private boolean mLocked;
        private String mMonth;
        private String mYear;

        public AccountBirthday(Parcel parcel) {
            readFromParcel(parcel);
            makeBirthdateLengthValidIfNeeded();
        }

        private void makeBirthdateLengthValidIfNeeded() {
            String str = this.mYear;
            if (str != null && str.length() > 4) {
                this.mYear = this.mYear.substring(0, 4);
            }
            String str2 = this.mMonth;
            if (str2 != null && str2.length() > 2) {
                this.mMonth = this.mMonth.substring(0, 2);
            }
            String str3 = this.mDay;
            if (str3 != null && str3.length() > 2) {
                this.mDay = this.mDay.substring(0, 2);
            }
        }

        public int getAction() {
            return this.mAction;
        }

        public String getDay() {
            return this.mDay;
        }

        public String getMonth() {
            return this.mMonth;
        }

        public String getYear() {
            return this.mYear;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mYear);
            parcel.writeString(this.mMonth);
            parcel.writeString(this.mDay);
        }

        public void readFromParcel(Parcel parcel) {
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.mLocked = z;
            this.mAction = parcel.readInt();
            this.mYear = parcel.readString();
            this.mMonth = parcel.readString();
            this.mDay = parcel.readString();
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setBirthday(String str, String str2, String str3) {
            this.mYear = str;
            this.mMonth = str2;
            this.mDay = str3;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public AccountBirthday() {
        }
    }

    public Profile() {
        this.mName = new Name();
        this.mAccountName = new AccountName();
        this.mBirthday = new Birthday();
        this.mAccountBirthday = new AccountBirthday();
        this.mNickname = new Nickname();
        this.mPhoto = new Photo();
        this.mOrganization = new Organization();
        this.mGender = new Gender();
        this.mStatusMessage = new StatusMessage();
        this.mNote = new Note();
        this.mMessengerAccount = new MessengerAccount();
        this.mPhoneNumber = new PhoneNumber();
        this.mEmailAddress = new EmailAddress();
        this.mWebAddress = new WebAddress();
        this.mEvent = new Event();
        this.mHealth = new Health();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Event {
        private int mAction;
        private final ArrayList<EventData> mEventData;

        public Event(Parcel parcel) {
            this.mAction = parcel.readInt();
            ArrayList<EventData> arrayList = new ArrayList<>();
            this.mEventData = arrayList;
            parcel.readTypedList(arrayList, EventData.CREATOR);
        }

        public int getAction() {
            return this.mAction;
        }

        public ArrayList<EventData> getEventData() {
            return this.mEventData;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeInt(this.mAction);
            parcel.writeTypedList(this.mEventData);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public Event() {
            this.mEventData = new ArrayList<>();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Gender {
        public static final String TYPE_FEMALE = "female";
        public static final String TYPE_MALE = "male";
        private int mAction;
        private boolean mLocked;
        private String mValue;

        public Gender(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            this.mValue = parcel.readString();
        }

        public int getAction() {
            return this.mAction;
        }

        public String getGender() {
            return this.mValue;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mValue);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public boolean setGender(String str) {
            this.mValue = str;
            return true;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public Gender() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Nickname {
        private int mAction;
        private boolean mLocked;
        private String mNickName;

        public Nickname(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            this.mNickName = parcel.readString();
        }

        public int getAction() {
            return this.mAction;
        }

        public String getNickname() {
            return this.mNickName;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mNickName);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setNickname(String str) {
            this.mNickName = str;
        }

        public Nickname() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Note {
        private int mAction;
        private boolean mLocked;
        private String mValue;

        public Note(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            this.mValue = parcel.readString();
        }

        public int getAction() {
            return this.mAction;
        }

        public String getNote() {
            return this.mValue;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mValue);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setNote(String str) {
            this.mValue = str;
        }

        public Note() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StatusMessage {
        private int mAction;
        private boolean mLocked;
        private String mValue;

        public StatusMessage(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            this.mValue = parcel.readString();
        }

        public int getAction() {
            return this.mAction;
        }

        public String getStatusMessage() {
            return this.mValue;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mValue);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setStatusMessage(String str) {
            this.mValue = str;
        }

        public StatusMessage() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AccountName {
        private int mAction;
        private String mFamilyName;
        private String mGivenName;
        private boolean mLocked;

        public AccountName(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            this.mGivenName = parcel.readString();
            this.mFamilyName = parcel.readString();
        }

        public int getAction() {
            return this.mAction;
        }

        public String getFamilyName() {
            return this.mFamilyName;
        }

        public String getGivenName() {
            return this.mGivenName;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mGivenName);
            parcel.writeString(this.mFamilyName);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setName(String str, String str2) {
            this.mGivenName = str;
            this.mFamilyName = str2;
        }

        public AccountName() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Health {
        private int mAction;
        private String mActivityLevel;
        private String mHeight;
        private boolean mLocked;
        private String mWeight;

        public Health(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            this.mHeight = parcel.readString();
            this.mWeight = parcel.readString();
            this.mActivityLevel = parcel.readString();
        }

        public int getAction() {
            return this.mAction;
        }

        public String getActivityLevel() {
            return this.mActivityLevel;
        }

        public String getHeight() {
            return this.mHeight;
        }

        public String getWeight() {
            return this.mWeight;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mHeight);
            parcel.writeString(this.mWeight);
            parcel.writeString(this.mActivityLevel);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setActivityLevel(String str) {
            this.mActivityLevel = str;
        }

        public void setHealth(String str, String str2, String str3) {
            this.mHeight = str;
            this.mWeight = str2;
            this.mActivityLevel = str3;
        }

        public void setHeight(String str) {
            this.mHeight = str;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setWeight(String str) {
            this.mWeight = str;
        }

        public Health() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Organization {
        private int mAction;
        private String mCompany;
        private String mDepartment;
        private boolean mLocked;
        private String mTitle;

        public Organization(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            this.mCompany = parcel.readString();
            this.mDepartment = parcel.readString();
            this.mTitle = parcel.readString();
        }

        public int getAction() {
            return this.mAction;
        }

        public String getCompany() {
            return this.mCompany;
        }

        public String getDepartment() {
            return this.mDepartment;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mCompany);
            parcel.writeString(this.mDepartment);
            parcel.writeString(this.mTitle);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setCompany(String str) {
            this.mCompany = str;
        }

        public void setDepartment(String str) {
            this.mDepartment = str;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setOrganization(String str, String str2, String str3) {
            this.mCompany = str;
            this.mDepartment = str2;
            this.mTitle = str3;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }

        public Organization() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Photo {
        private int mAction;
        private boolean mLocked;
        private byte[] mPhoto;
        private String mPhotoFileUri;
        private int mPhotoSize;
        private String mPhotoType;

        public Photo(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            int readInt = parcel.readInt();
            this.mPhotoSize = readInt;
            if (readInt > 0) {
                byte[] bArr = new byte[readInt];
                this.mPhoto = bArr;
                parcel.readByteArray(bArr);
            }
            this.mPhotoType = parcel.readString();
            if (Profile.sConnectedProfileVersion >= 5) {
                this.mPhotoFileUri = parcel.readString();
            }
        }

        public int getAction() {
            return this.mAction;
        }

        public byte[] getPhoto() {
            return this.mPhoto;
        }

        public String getPhotoFileUri() {
            return this.mPhotoFileUri;
        }

        public String getPhotoType() {
            return this.mPhotoType;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeInt(this.mPhotoSize);
            if (this.mPhotoSize > 0) {
                parcel.writeByteArray(this.mPhoto);
            }
            parcel.writeString(this.mPhotoType);
            if (Profile.sConnectedProfileVersion >= 5) {
                parcel.writeString(this.mPhotoFileUri);
            }
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setPhoto(byte[] bArr) {
            this.mPhoto = bArr;
            if (bArr == null) {
                this.mPhotoSize = 0;
            } else {
                this.mPhotoSize = bArr.length;
            }
        }

        public void setPhotoFileUri(String str) {
            this.mPhotoFileUri = str;
        }

        public void setPhotoType(String str) {
            this.mPhotoType = str;
        }

        public Photo() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Name {
        private int mAction;
        private String mFamilyName;
        private String mGivenName;
        private boolean mLocked;
        private String mMiddleName;
        private String mPhoneticFamilyName;
        private String mPhoneticGivenName;
        private String mPhoneticMiddleName;
        private String mPrefixName;
        private String mSuffixName;

        public Name(Parcel parcel) {
            this.mLocked = parcel.readByte() != 1 ? false : true;
            this.mAction = parcel.readInt();
            this.mPrefixName = parcel.readString();
            this.mGivenName = parcel.readString();
            this.mMiddleName = parcel.readString();
            this.mFamilyName = parcel.readString();
            this.mSuffixName = parcel.readString();
            this.mPhoneticGivenName = parcel.readString();
            this.mPhoneticMiddleName = parcel.readString();
            this.mPhoneticFamilyName = parcel.readString();
        }

        public int getAction() {
            return this.mAction;
        }

        public String getFamilyName() {
            return this.mFamilyName;
        }

        public String getGivenName() {
            return this.mGivenName;
        }

        public String getMiddleName() {
            return this.mMiddleName;
        }

        public String getPhoneticFamilyName() {
            return this.mPhoneticFamilyName;
        }

        public String getPhoneticGivenName() {
            return this.mPhoneticGivenName;
        }

        public String getPhoneticMiddleName() {
            return this.mPhoneticMiddleName;
        }

        public String getPrefixName() {
            return this.mPrefixName;
        }

        public String getSuffixName() {
            return this.mSuffixName;
        }

        public boolean isLocked() {
            return this.mLocked;
        }

        public void putParcelData(Parcel parcel) {
            parcel.writeByte(this.mLocked ? (byte) 1 : 0);
            parcel.writeInt(this.mAction);
            parcel.writeString(this.mPrefixName);
            parcel.writeString(this.mGivenName);
            parcel.writeString(this.mMiddleName);
            parcel.writeString(this.mFamilyName);
            parcel.writeString(this.mSuffixName);
            parcel.writeString(this.mPhoneticGivenName);
            parcel.writeString(this.mPhoneticMiddleName);
            parcel.writeString(this.mPhoneticFamilyName);
        }

        public void setAction(int i2) {
            this.mAction = i2;
        }

        public void setFamilyName(String str) {
            this.mFamilyName = str;
        }

        public void setGivenName(String str) {
            this.mGivenName = str;
        }

        public void setLock(boolean z) {
            this.mLocked = z;
        }

        public void setMiddleName(String str) {
            this.mMiddleName = str;
        }

        public void setName(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.mPrefixName = str;
            this.mGivenName = str2;
            this.mMiddleName = str3;
            this.mFamilyName = str4;
            this.mSuffixName = str5;
            this.mPhoneticGivenName = str6;
            this.mPhoneticMiddleName = str7;
            this.mPhoneticFamilyName = str8;
        }

        public void setPhoneticFamilyName(String str) {
            this.mPhoneticFamilyName = str;
        }

        public void setPhoneticGivenName(String str) {
            this.mPhoneticGivenName = str;
        }

        public void setPhoneticMiddleName(String str) {
            this.mPhoneticMiddleName = str;
        }

        public void setPrefixName(String str) {
            this.mPrefixName = str;
        }

        public void setSuffixName(String str) {
            this.mSuffixName = str;
        }

        public Name() {
        }
    }
}
