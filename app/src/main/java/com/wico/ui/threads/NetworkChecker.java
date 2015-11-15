package com.wico.ui.threads;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChecker extends Thread{

    private NetworkCheckerListener listener;
    private Context caller;

    public interface NetworkCheckerListener {
        void onConnected();
    }

    public NetworkChecker(Context context){
        caller = context;
        this.listener = null;
    }

    public void setNetworkCheckerListener( NetworkCheckerListener listener){
        this.listener = listener;
    }

    @Override
    public void run() {
        while (!isNetworkAvailable()) {
            waitALittle();
        }
        if(listener!=null){
            listener.onConnected();
        }
    }

    private void waitALittle(){
        try{
            synchronized (this){
                this.wait(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Connection checker Thread Interrupted");
        }
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetwork = ((ConnectivityManager)
                caller.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
