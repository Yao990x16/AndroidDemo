package pres.yao.zuoye4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Zuoye4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuoye4);
        Button button = (Button)findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(Zuoye4Activity.this, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(Zuoye4Activity.this,"缺少打电话权限",Toast.LENGTH_SHORT).show();
                    return;
                }
                String phonenumber = "13230100682";
                String encodePhonenumber = null;
                try{
                    encodePhonenumber = URLEncoder.encode(phonenumber,"UTF-8");
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel"+encodePhonenumber)));
            }
        });
    }
}
