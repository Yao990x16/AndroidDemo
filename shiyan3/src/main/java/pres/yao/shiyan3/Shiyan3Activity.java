package pres.yao.shiyan3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.TransactionTooLargeException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import pres.yao.shiyan3.gson.Trash;
import pres.yao.shiyan3.net.retrofit.IApi;
import pres.yao.shiyan3.net.retrofit.RetrofitImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class Shiyan3Activity extends AppCompatActivity {
    //按钮
    Button search_button;
    Button collect_button;
    //输入框
    EditText editText;
    //文本显示
    TextView resultTextView;

    //api申请的key
    final String url_key =  "90b813a39bcf48683e7871c988b143af";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan3);

        initViews();
        initEvents();


    }

    private void initViews() {
        search_button = (Button)findViewById(R.id.search_button);
        editText = (EditText)findViewById(R.id.edit_trash_name);
        resultTextView = (TextView)findViewById(R.id.search_result);
        collect_button = (Button)findViewById(R.id.button_add);
    }

    private void initEvents() {
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IApi api = RetrofitImpl.getRetrofit().create(IApi.class);
                String trash_name = editText.getText().toString();
                api.get(url_key,trash_name).enqueue(new Callback<Trash>() {
                    @Override
                    public void onResponse(Call<Trash> call, Response<Trash> response) {
                        try {
                            Trash.NewslistBean result_trash = response.body().getNewslist().get(0);
                            //String respStr = response.body().string();
                            Log.e("tianxing",result_trash.toString());
                            resultTextView.setText(
                                    "垃圾名称:"+result_trash.getName()+"\n"
                                            +"垃圾类型:"+result_trash.getExplain()+"\n"
                                            +"包括:"+result_trash.getContain()+"\n"
                                            +"提示:"+result_trash.getTip());
                        }catch (NullPointerException ignored) {
                            Toast.makeText(Shiyan3Activity.this,"没有相关数据,请重新输入",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Trash> call, Throwable t) {
                        Toast.makeText(Shiyan3Activity.this,"网络错误",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        collect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("查询结果",resultTextView.toString());
                if(!resultTextView.getText().toString().equals("")){
                    SharedPreferences.Editor editor = getSharedPreferences("trash_collect",MODE_PRIVATE).edit();
                    String str = resultTextView.getText().toString();
                    Log.e("收藏条目",str);
                    editor.putString("trash", str);
                    editor.apply();
                }else {
                    Toast.makeText(Shiyan3Activity.this,"没有显示结果,请查询后再收藏",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return retValue;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent intent=new Intent();
        switch (id){
            case R.id.search_item:
                intent.setClass(this,Shiyan3Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.record_item:
                intent.setClass(this, RecordActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.exit_item:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
