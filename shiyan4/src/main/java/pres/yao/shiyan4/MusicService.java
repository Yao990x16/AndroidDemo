package pres.yao.shiyan4;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.List;

public class MusicService extends Service {
    private MediaPlayer mPlayer;
    private int seekLength = 0;
    private int currentIndex = -1;

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    private List<Music> musicList;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onCreate() {
        super.onCreate();
        InitPlayer();
        //通过工具类MusicUtils获取音乐信息列表
        musicList = MusicUtils.ResolveMusicToList(getApplicationContext());
    }

    private void InitPlayer() {
        mPlayer = new MediaPlayer();
    }

    private class MyBinder extends Binder implements MyBinderInterface{

        @Override
        public void Pause() {
            if (mPlayer.isPlaying()){
                mPlayer.pause();
                seekLength = mPlayer.getCurrentPosition();
            }
        }

        @Override
        public void Resume() {
            mPlayer.seekTo(seekLength);
            mPlayer.start();
        }

        @Override
        public void Play() {
            mPlayer.reset();
            Uri path = Uri.parse(musicList.get(currentIndex).getMusic_path());
            Log.e("PATH", String.valueOf(path));
            Log.e("PATH", String.valueOf(musicList.size()));
            try {
                mPlayer.setDataSource(String.valueOf(path));
                mPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPlayer.seekTo(seekLength);
            mPlayer.start();
        }

        @Override
        public void Stop() {
            mPlayer.stop();
        }

        @Override
        public void PlayNext() {
            currentIndex += 1;
            if (currentIndex >= musicList.size()){
                currentIndex = 0;
            }
            seekLength = 0;
            Play();
        }

        @Override
        public void PlayPrev() {
            currentIndex -= 1;
            if (currentIndex <= 0){
                currentIndex = musicList.size() - 1;
            }
            seekLength = 0;
            Play();
        }

        @Override
        public void Release() {
            mPlayer.reset();
            mPlayer.stop();
            mPlayer.release();
        }

        @Override
        public boolean isPlaying() {
            return mPlayer.isPlaying();
        }

        @Override
        public int getDuration() {
            return mPlayer.getDuration();
        }

        @Override
        public int getCurrentPosition() {
            return mPlayer.getCurrentPosition();
        }

        @Override
        public void seekTo(int length) {
            seekLength = length;
            mPlayer.seekTo(length);
        }

        @Override
        public int getCurrentIndex() {
            return currentIndex;
        }

        @Override
        public void setCurrentIndex(int currentIdx) {
            currentIndex = currentIdx;
        }

        @Override
        public void setList(List<Music> musicList) {
            setMusicList(musicList);
        }
    }
}
