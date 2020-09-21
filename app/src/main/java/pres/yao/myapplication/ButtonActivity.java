package pres.yao.myapplication;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        Button btn1 = findViewById(R.id.btn1);
        MyClickListener mcl = new MyClickListener();
        btn1.setOnClickListener(mcl);//为按钮注册点击事件监听器
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //在控制台输出语句
            Log.e("TAG","点击按钮时注册了内部类监听器对象的按钮");
        }
    }
}