package pres.yao.shiyan3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

public class Shiyan3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan3);
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
