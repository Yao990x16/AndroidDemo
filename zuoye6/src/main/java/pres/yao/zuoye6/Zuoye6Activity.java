package pres.yao.zuoye6;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Zuoye6Activity extends AppCompatActivity {
    CalculatorService calculatorService = null;
    private static final String TAG = "activityTAG";
    static final String UPPER_NUM = "upper";
    EditText input;
    CalThread calThread;
    class CalThread extends Thread{
        public Handler mHandler;
        @SuppressLint("HandlerLeak")
        public void run(){
            //创建Looper对象，同时创建MessageQueue
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123){
                        @SuppressLint("HandlerLeak") List<Integer> nums = new ArrayList<>();
                        //从UI线程（EditText）中或者数值
                        int upper = msg.getData().getInt(UPPER_NUM);
                        //从2开始，到upper的所有质数
                        //outer就是一个标签，java语言中根本没有此关键字，因此outer也可以用其它的词来代替
                        // 当程序从外层循环进入内层循环后，当(i != 2 && i % j ==0)时，程序遇到一个continue outer;语句，
                        // 这行代码将会导致继续outer标签指定的外层循环，而不是继续continue所在的循环
                        outer:
                        for (int i = 2 ; i <= upper ; i++){
                            //用i除以2开始，到i的平方根的所有数
                            for (int j = 2 ; j <= Math.sqrt(i) ; j++){
                                //如果可以整除，则表明这个数不是质数
                                if (i != 2 && i % j ==0){
                                    continue outer;
                                }
                            }
                            nums.add(i);
                        }
                        if(nums.contains(upper)){
                            Toast.makeText(Zuoye6Activity.this,+upper+"是素数",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(Zuoye6Activity.this,+upper+"不是素数",Toast.LENGTH_LONG).show();
                        }

                    }
                }
            };
            //启动Looper
            Looper.loop();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuoye6);

        input = findViewById(R.id.input);
        calThread = new CalThread();
        //启动线程
        calThread.start();
        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.v(TAG,"onServiceConnected");
                calculatorService = ((CalculatorService.CalBinder)service).getCalulatorService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.v(TAG,"onServiceDisconnected");
            }
        };
        Button buttonStart = (Button) findViewById(R.id.startService);
        Button buttonStop = (Button) findViewById(R.id.stopService);
        Button buttonUse = (Button) findViewById(R.id.useService);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zuoye6Activity.this,CalculatorService.class);
                bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });

        buttonUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calculatorService!=null){
                    Log.v(TAG,"\n使用计算服务:加: "+calculatorService.add(1,2)+"减: "+calculatorService.sub(4,2)
                    +"乘: "+calculatorService.mul(6,6)+"除: "+calculatorService.div(25,5));
                }
            }
        });
        Button buttonCal = (Button)findViewById(R.id.calSushu);
        buttonCal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 0x123;
                Bundle bundle = new Bundle();
                bundle.putInt(UPPER_NUM , Integer.parseInt(input.getText().toString()));
                msg.setData(bundle);
                calThread.mHandler.sendMessage(msg);
            }
        });
    }
}
