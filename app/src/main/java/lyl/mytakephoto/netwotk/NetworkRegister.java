package lyl.mytakephoto.netwotk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * @author lyl
 * @date 2018/3/16.
 */

public class NetworkRegister extends BroadcastReceiver {

    private  NetworkCallback networkCallback;

    public void setNetworkCallback(NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {

            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    //Toast.makeText(context, "wifi不能用", Toast.LENGTH_SHORT).show();
                    if (networkCallback!=null){
                        networkCallback.netDisable();
                    }

                    break;
                case WifiManager.WIFI_STATE_DISABLING:

                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    if (networkCallback!=null){
                        networkCallback.netEnable();
                    }

                   // Toast.makeText(context, "wifi可用", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    break;
                default:
                    break;
            }
        }
    }


    public interface NetworkCallback {
        void netDisable();

        void netEnable();
    }
}
