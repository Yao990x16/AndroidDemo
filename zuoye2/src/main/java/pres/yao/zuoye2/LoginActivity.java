package pres.yao.zuoye2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
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

    //自定义对话框
    Dialog cus_dialog ;
    public void showCustomDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 布局填充器
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_login, null);
        // 设置自定义的对话框界面
        builder.setView(view);
        // 获取用户名密码
        final EditText name = (EditText) view.findViewById(R.id.et_name);
        final EditText pwd = (EditText) view.findViewById(R.id.et_pwd);
        Button btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                if(name.getText().toString().trim().equals("abc")||pwd.getText().toString().trim().equals("123")){
                    showToast("用户名正确");
                    // 对话框消失
                    cus_dialog.dismiss();
                }
                else{
                    showToast("用户名错误");
                }
            }
        });
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 对话框消失
                cus_dialog.dismiss();
            }
        });
        cus_dialog = builder.create();
        cus_dialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showCustomDialog(getCurrentFocus());
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