package pres.yao.zuoye6;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CalculatorService extends Service {
    private static final String TAG = "serviceTAG";
    //onBind返回IBinder接口对象
    private CalBinder myBinder = new CalBinder();
    public CalculatorService() {
    }
    //计算器binder
    public class CalBinder extends Binder{
        CalculatorService getCalulatorService(){
            return CalculatorService.this;
        }
    }
    public int add(int x,int y){
        return x+y;
    }

    public int sub(int x,int y){
        return x-y;
    }

    public int mul(int x,int y){
        return x*y;
    }

    public int div(int x,int y){
        return x/y;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.v(TAG,"onBind()");
        return myBinder;
    }
    @Override
    public boolean onUnbind(Intent intent){
        Log.v(TAG,"onUnbind()");
        return super.onUnbind(intent);
    }
}
