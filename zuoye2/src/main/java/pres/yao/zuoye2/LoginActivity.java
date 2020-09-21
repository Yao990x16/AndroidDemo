package pres.yao.zuoye2;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edt1;
    EditText edt2;
    Button btn;
    Toast mtoast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt1=(EditText)findViewById(R.id.edit1);
        edt2=(EditText)findViewById(R.id.edit2);
        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (edt1.getText().toString().equals("")||edt2.getText().toString().equals("")) {
            showToast("用户名或密码不能为空！");
        }else if(edt1.getText().toString().equals("abc")&&edt2.getText().toString().equals("123")){
            showToast("登录成功！！！");
            Intent intent = new Intent(LoginActivity.this,Zuoye2Activity.class);
            startActivity(intent);
        }else{
            showToast("登录失败！");
        }
    }
    private void showToast(String msg){
        mtoast=Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        mtoast.show();
    }
}