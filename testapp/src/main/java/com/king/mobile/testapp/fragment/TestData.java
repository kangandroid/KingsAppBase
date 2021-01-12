package com.king.mobile.testapp.fragment;

import android.os.Parcel;
import android.os.Parcelable;

public class TestData implements DiffI, Parcelable {
    public String Id;
    public String name;
    public String hideInfo;

    protected TestData(Parcel in) {
        Id = in.readString();
        name = in.readString();
        hideInfo = in.readString();
    }

    public static final Creator<TestData> CREATOR = new Creator<TestData>() {
        @Override
        public TestData createFromParcel(Parcel in) {
            return new TestData(in);
        }

        @Override
        public TestData[] newArray(int size) {
            return new TestData[size];
        }
    };

    @Override
    public boolean isSameOne(DiffI old) {
        if (old instanceof TestData) {
            return this.Id.equals(((TestData) old).Id);
        }
        return false;
    }

    @Override
    public boolean isContentSame(DiffI old) {
        if (old instanceof TestData) {
            return this.name.equals(((TestData) old).name);
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(this.Id.getBytes());
        dest.writeByteArray(this.name.getBytes());
        dest.writeByteArray(this.hideInfo.getBytes());
    }
}
