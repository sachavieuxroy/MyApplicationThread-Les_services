package google.android.com.myapplicationthread_les_services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyFirstService extends Service {
    private IBinder myBinder = new MyActivityBinder();
    private int valeur=10;
    private int i=0;
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    valeur=0;

                    while (valeur<=10){
                        valeur++;
                        Log.d("valeur=",String.valueOf(valeur));
                    }
                }

            }
        }).start();
        return START_STICKY;
    }

    public int getValeur(){
        return valeur;
    }
    public class MyActivityBinder extends Binder {
        MyFirstService getMyService() {
            return MyFirstService.this;
        }
    }

}
