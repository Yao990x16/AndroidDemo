package pres.yao.shiyan3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pres.yao.shiyan3.adapter.TrashAdapter;
import pres.yao.shiyan3.entity.TrashEntity;

import java.util.List;
import java.util.Map;

public class RecordActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TrashAdapter trashAdapter;
    GreenDaoManager daoManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        daoManager = new GreenDaoManager(this);
        initViews();
        initEvents();
    }
    private void initViews() {
        mRecyclerView = findViewById(R.id.record_recyclerview);

        //mRecyclerView.setAdapter();
    }
    private void initEvents() {
        List<TrashEntity> dataSource = daoManager.selectAll();
        //线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        trashAdapter = new TrashAdapter(this);
        trashAdapter.setDataSource(dataSource);
        mRecyclerView.setAdapter(trashAdapter);
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