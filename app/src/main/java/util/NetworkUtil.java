package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

import java.util.ArrayList;
import java.util.List;

public class NetworkUtil  {

    public static boolean isConnected(Context context){
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public static class ConnectedCallBack extends ConnectivityManager.NetworkCallback{
        private List<Runnable> tasks = new ArrayList<>();
        final NetworkRequest networkRequest;

        public  ConnectedCallBack(Runnable task){
           networkRequest = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .build();
            tasks.add(task);
        }

        public void listen(Context context) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.registerNetworkCallback(networkRequest , this);
        }

        @Override
        public void onAvailable(Network network){
            runAllTask();
        }

        public void runAllTask(){
            for (Runnable r : this.tasks)
                r.run();
        }
    }

}
