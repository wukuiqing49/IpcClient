package com.wu.ipcclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.wu.ipcservice.IShareDataInterface;
import com.wu.ipcservice.ShareDataInfo;

public class MainActivity extends AppCompatActivity {
    private IShareDataInterface shareDataInterface;
    boolean isConnected;
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnected = true;
            shareDataInterface = IShareDataInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.bt_connected).setOnClickListener(v ->{
            startService();
        });

        findViewById(R.id.bt_send).setOnClickListener(v ->{
            try {
                shareDataInterface.sendShareData(10086,"发送了消息");
                Log.e("IPCClient:","发送了消息:"+10086);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        findViewById(R.id.bt_get).setOnClickListener(v ->{
            try {
                String content=shareDataInterface.getShareData(10086);
                Log.e("IPCClient:","获取消息:"+content);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        findViewById(R.id.bt_get_info).setOnClickListener(v ->{
            try {
                ShareDataInfo info= new ShareDataInfo(10010,"对象信息");
                shareDataInterface.sendShareDataInfo(info);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private void startService() {

        ComponentName name = new ComponentName("com.wu.ipcservice", "com.wu.ipcservice.ShareDataService");
        Intent intent = new Intent();
        intent.setAction("com.wu.ipc.service");
        intent.setComponent(name);
        this.bindService(intent, this.mServiceConnection, this.BIND_AUTO_CREATE);

    }
}
