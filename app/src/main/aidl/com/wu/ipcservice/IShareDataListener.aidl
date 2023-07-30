// IShareDataListener.aidl
package com.wu.ipcservice;

interface IShareDataListener {
    void notifyShareData(int key, String values);
}
