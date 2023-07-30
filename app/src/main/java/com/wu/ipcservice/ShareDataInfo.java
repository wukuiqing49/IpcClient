package com.wu.ipcservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:7/29/23
 * <p>
 * 用途:
 */


public class ShareDataInfo implements Parcelable {
    int type;
    String content;

    public ShareDataInfo(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public ShareDataInfo(Parcel in) {
        type = in.readInt();
        content = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShareDataInfo> CREATOR = new Creator<ShareDataInfo>() {
        @Override
        public ShareDataInfo createFromParcel(Parcel in) {
            return new ShareDataInfo(in);
        }

        @Override
        public ShareDataInfo[] newArray(int size) {
            return new ShareDataInfo[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "ShareDataInfo{" +
                "type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
