package pres.yao.shiyan3;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import pres.yao.shiyan3.entity.DaoMaster;
import pres.yao.shiyan3.entity.DaoSession;

/**
 * @ClassName MyApplication
 * @Description TOOD
 * Date 2020/10/26 20:38
 **/
public class MyApplication extends Application {
    public static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }
    //连接数据库并创建会话
    public void initDB(){
        //获取需要链接的数据库
        DaoMaster.DevOpenHelper devOpenHelper =
                new DaoMaster.DevOpenHelper(this,"trash_collection.db");
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        //创建数据库链接
        DaoMaster daoMaster = new DaoMaster(db);
        //创建数据库会话
        mDaoSession = daoMaster.newSession();
    }
}
