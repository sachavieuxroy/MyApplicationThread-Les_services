package google.android.com.myapplicationthread_les_services;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {
    private MyFirstService myService;
    private boolean mbound;
    private Intent bindIntent;
    private ServiceConnection myServiceConnection = new
            ServiceConnection() {

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    myService = null;
                }

                @Override
                public void onServiceConnected(ComponentName name, IBinder
                        service) {
                    MyFirstService.MyActivityBinder binder = (MyFirstService.MyActivityBinder) service;
                    myService = binder.getMyService();
                    mbound = true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    @Override
    protected void onStart() {
        super.onStart();
        //Demarrage du service
        bindIntent = new Intent(this, MyFirstService.class);
        bindService(bindIntent, myServiceConnection,  Context.BIND_AUTO_CREATE);
        startService(bindIntent);


    }



    public void soumettre(View v){
        if (mbound){
            Toast.makeText(this, String.valueOf(myService.getValeur()), Toast.LENGTH_LONG).show();
        }
    }

    public MyFirstService getMyService() {
        return myService;
    }

    public void setMyService(MyFirstService myService) {
        this.myService = myService;
    }
    @Override
    protected void onStop() {
        super.onStop();
        // Deconnection du service
        if (mbound) {
            unbindService(myServiceConnection);
            mbound = false;
        }
        stopService(bindIntent);
    }

}
