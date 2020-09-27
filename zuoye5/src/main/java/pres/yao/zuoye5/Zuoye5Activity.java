package pres.yao.zuoye5;

import android.Manifest;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class Zuoye5Activity extends AppCompatActivity {
    //文件名
    private final static String key_Username = "Yao";
    private final static String key_StudentNum = "2017013010";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private final static String SharedPreferencesFileName = "config";

    private static final String TAG = "标签";
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuoye5);
        preferences = getSharedPreferences(SharedPreferencesFileName,MODE_PRIVATE);
        editor = preferences.edit();
        resolver = this.getContentResolver();

        Button buttonWrite = (Button)findViewById(R.id.writeButton);
        Button buttonRead = (Button)findViewById(R.id.readButton);
        Button buttonFileWrite = (Button)findViewById(R.id.writeFileButton);
        Button buttonFileRead = (Button)findViewById(R.id.readFileButton);
        Button buttonReadContact = (Button)findViewById(R.id.readContacts);
        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //写入键值对
                editor.putString(key_Username,"姚泱茹");
                editor.putString(key_StudentNum,"2017013010");
                editor.apply();
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = preferences.getString(key_Username,null);
                String strStuNum = preferences.getString(key_StudentNum,null);
                if(strUserName != null && strStuNum != null){
                    Toast.makeText(Zuoye5Activity.this,"姓名:"+strUserName+"学号:"+strStuNum,Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Zuoye5Activity.this,"无数据",Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonFileWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out = null;
                try{
                    FileOutputStream fileOut = openFileOutput("Zuoye5",MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOut);
                    String content = "yaoyangru,2017013010";
                    try{
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }finally {
                        if(out!=null){
                            out.close();
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        buttonFileRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream in = null;
                try{
                    FileInputStream fis = openFileInput("Zuoye5");
                    in = new BufferedInputStream(fis);
                    int c;
                    StringBuilder stringBuilder = new StringBuilder();
                    try{
                        while((c=in.read())!=-1){
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(Zuoye5Activity.this,stringBuilder.toString(),
                                Toast.LENGTH_LONG).show();
                    }finally {
                        if(in!=null){
                            in.close();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        buttonReadContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
                while (cursor.moveToNext()){
                    String msg;
                    //id
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    msg = "id:"+id;
                    //name
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    msg = msg + " name:"+name;
                    //phone
                    Cursor phoneNum = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+id,null,null);
                    while (phoneNum.moveToNext()){
                        String phoneNumber =
                                phoneNum.getString(phoneNum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        msg = msg + " phone:"+phoneNumber;
                    }
                    //email
                    Cursor emails = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID+"="+id,null,null);
                    while (emails.moveToNext()){
                        String email =
                                emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        msg = msg + " email:"+email;
                    }
                    Log.v(TAG,msg);
                }
            }
        });
    }
    public void checkPermission()
    {
        int targetSdkVersion = 0;
        String[] PermissionString={Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};
        try {
            final PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;//获取应用的Target版本
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
//            Log.e("err", "检查权限_err0");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Build.VERSION.SDK_INT是获取当前手机版本 Build.VERSION_CODES.M为6.0系统
            //如果系统>=6.0
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                //第 1 步: 检查是否有相应的权限
                boolean isAllGranted = checkPermissionAllGranted(PermissionString);
                if (isAllGranted) {
                    //Log.e("err","所有权限已经授权！");
                    return;
                }
                // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
                ActivityCompat.requestPermissions(this,
                        PermissionString, 1);
            }
        }
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                //Log.e("err","权限"+permission+"没有授权");
                return false;
            }
        }
        return true;
    }

    //申请权限结果返回处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            boolean isAllGranted = true;
            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                // 所有的权限都授予了
                Log.e("err","权限都授权了");
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                //容易判断错
                //MyDialog("提示", "某些权限未开启,请手动开启", 1) ;
            }
        }
    }
}
