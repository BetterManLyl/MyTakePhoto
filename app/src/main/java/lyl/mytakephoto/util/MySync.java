package lyl.mytakephoto.util;

import android.os.AsyncTask;

/**
 * @author lyl
 * @date 2018/3/13.
 */

public class MySync extends AsyncTask<Integer,Integer,String> {

    @Override
    protected String doInBackground(Integer... params) {
        publishProgress(params);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

}
