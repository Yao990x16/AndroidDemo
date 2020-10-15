package pres.yao.shiyan2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * @ClassName DBContentProvider
 * @Description TOOD
 * Date 2020/10/14 18:57
 **/
public class DBContentProvider extends ContentProvider {
    //dict表相对应的数字
    public static final int DICT_NUM = 0;

    //包路径authority
    public static final String AUTHORITY = "pres.yao.shiyan2";
    //匹配Uri,进行文本过滤
    private static UriMatcher uriMatcher;
    private Database database;

    //静态代码块做初始化
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"dict",DICT_NUM);

    }
    public DBContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = database.getWritableDatabase();
        int delectRows = 0;
        switch (uriMatcher.match(uri)){
            case DICT_NUM:
                delectRows = db.delete("dict", selection, selectionArgs);
                break;
        }

        return delectRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case DICT_NUM:
                return "vnd.android.cursor.dir/vnd.pres.yao.shiyan2.dict";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase db = database.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case DICT_NUM:
                db.insert("dict",null,values);
                break;
        }
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        database = new Database(getContext(),"dict",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //查询数据
        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = null;
        //判断是查询哪个表
        switch (uriMatcher.match(uri)){
            case DICT_NUM:
                cursor = db.query("dict",projection,selection,selectionArgs,null,null,sortOrder);
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase db = database.getWritableDatabase();
        int updateraws = 0;
        switch (uriMatcher.match(uri)){
            case DICT_NUM:
                updateraws = db.update("dict", values, selection, selectionArgs);
                break;
        }
        return updateraws;
    }
}
