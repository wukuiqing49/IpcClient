// IShareDataInterface.aidl
package com.wu.ipcservice;

import com.wu.ipcservice.IShareDataListener;
import com.wu.ipcservice.ShareDataInfo;

interface IShareDataInterface {


    void sendShareData(int key, String values);

    String getShareData(int key) ;

    void registerCallback(int id, IShareDataListener callback) ;

    void unregisterCallback(IShareDataListener callback) ;

    void sendShareDataInfo(in ShareDataInfo info);

}
