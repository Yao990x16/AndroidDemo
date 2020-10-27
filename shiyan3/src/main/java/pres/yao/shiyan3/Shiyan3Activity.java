package pres.yao.shiyan3;

import android.content.Intent;
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
import pres.yao.shiyan3.entity.TrashBean;
import pres.yao.shiyan3.entity.TrashEntity;
import pres.yao.shiyan3.net.retrofit.IApi;
import pres.yao.shiyan3.net.retrofit.RetrofitImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shiyan3Activity extends AppCompatActivity {
    GreenDaoManager greenDaoManager;
    TrashBean.NewslistBean trashEntityList;
    //按钮
    Button search_button;
    Button collect_button;
    //输入框
    EditText editText;
    //文本显示
    TextView resultTextView;

    String intentTrash;
    private String trashName;
    private String trashDes;

    //api申请的key
    final String url_key =  "90b813a39bcf48683e7871c988b143af";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan3);
        //初始化
        greenDaoManager = new GreenDaoManager(this);

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
                final String trash_name = editText.getText().toString();
                api.get(url_key,trash_name).enqueue(new Callback<TrashBean>() {
                    @Override
                    public void onResponse(Call<TrashBean> call, Response<TrashBean> response) {
                       try {
                           Log.e("tianxing",response.body().toString());
                           //TrashBean trashBean = response.body();
                           trashEntityList = response.body().getNewslist().get(0);
                           //String respStr = response.body().string();
                           Log.e("tianxing",trashEntityList.getName());

                           trashName = trashEntityList.getName();
                           trashDes = "垃圾类型:"+trashEntityList.getExplain()+"\n"
                                   +"包括:"+trashEntityList.getContain()+"\n"
                                   +"提示:"+trashEntityList.getTip();
                           resultTextView.setText("垃圾名称:"+trashName+"\n"+trashDes);

                        }catch (NullPointerException ignored) {
                            Toast.makeText(Shiyan3Activity.this,"没有相关数据,请重新输入",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<TrashBean> call, Throwable t) {
                        Toast.makeText(Shiyan3Activity.this,"网络错误",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        collect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("查询结果",resultTextView.getText().toString());
                //Log.e("收藏条目",trashBean.toString());
                if(!resultTextView.getText().toString().equals("")){
                    //添加到数据库中,id,name,explain,contain,tip
                    TrashEntity trashEntity = new TrashEntity(
                            null,
                            trashEntityList.getName(),
                            trashEntityList.getExplain(),
                            trashEntityList.getContain(),
                            trashEntityList.getTip());
                    intentTrash = trashEntity.toString();
                    Log.e("查询结果",trashEntity.toString());
                    greenDaoManager.insertTrash(trashEntity);

                    Toast.makeText(Shiyan3Activity.this,"收藏成功!",Toast.LENGTH_LONG).show();
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
