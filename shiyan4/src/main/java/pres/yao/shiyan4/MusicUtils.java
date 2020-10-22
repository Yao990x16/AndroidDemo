package pres.yao.shiyan4;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MusicUtils
 * @Description TOOD
 * Date 2020/10/22 19:40
 **/
public class MusicUtils {
    /*
     * 用于获取本地Music目录下的所有音乐信息，并封装成List后返回
     * 需要一个Context对象
     * */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static List<Music> ResolveMusicToList(Context context){
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        String sortOrder = MediaStore.MediaColumns.DISPLAY_NAME+"";
        List<Music> musicList = new ArrayList<>();

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        ContentResolver contentResolver = context.getContentResolver();
        @SuppressLint("Recycle") Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,selection,null,sortOrder);

        if (cursor != null){
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                Music music = new Music();
                music.setMusic_title(cursor.getString(0));
                music.setMusic_artist(cursor.getString(1));
                music.setMusic_name(cursor.getString(2));
                music.setMusic_path(cursor.getString(3));
                music.setMusic_duration(Integer.parseInt(cursor.getString(4)));
                musicList.add(music);
            }
        }
        return musicList;
    }
}
