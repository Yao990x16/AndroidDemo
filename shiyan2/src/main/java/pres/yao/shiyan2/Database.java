package pres.yao.shiyan2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @ClassName Database
 * @Description TOOD
 * Date 2020/10/14 15:22
 **/
public class Database extends SQLiteOpenHelper {
    public Context mContext=null;
    public String tableName=null;
    public static int VERSION=2;
    //参数1:上下文环境
    //参数2:数据库路径
    //参数3:游标工厂,null
    //参数4:版本,会觉得是否调用升级方法 1
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
        mContext=context;
        tableName=name;
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory){
        this(context,name,factory,VERSION);
        mContext=context;
        tableName=name;
    }

    public Database(Context context, String name){
        this(context,name,null);
        mContext=context;
        tableName=name;
    };


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table dict(word text,pse text,prone text,psa text,prona text," +
                "interpret text, sentorig text, senttrans text)");
        Log.e("TAG","创建");
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
