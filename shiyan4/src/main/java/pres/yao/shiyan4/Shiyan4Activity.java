package pres.yao.shiyan4;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.*;

public class Shiyan4Activity extends AppCompatActivity{
    private List<Music> musicList = new ArrayList<Music>();
    private MusicAdapter mListAdapter;
    private ListView mListView;
    //记录长按的列表项坐标
    private int currentSel;
    //按钮
    private ImageView btnPrevious;
    private ImageView btnNext;
    private ImageView btnPlay;
    //文本
    private TextView listTitle;
    private TextView playingName;
    //进度条
    private SeekBar musicSeekBar;
    //自定义Binder对象 用于调用服务中的方法
    private MyBinderInterface myBinder;
    //自定义服务连接对象
    private MyServiceConnection conn;
    //是否正在播放
    private boolean isPlaying = false;
    private Handler handler = new Handler();
    //更新线程用于更新进度条
    private final Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            if (myBinder != null){
                try {
                    if (myBinder.isPlaying()){
                        int duration = myBinder.getDuration();
                        int currentPos = myBinder.getCurrentPosition();
                        musicSeekBar.setMax(duration);
                        musicSeekBar.setProgress(currentPos);

                        int prg_sec = currentPos/1000;
                        int max_sec = duration/1000;
                        if (prg_sec == max_sec){
                            myBinder.PlayNext();
                            updateState();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            handler.post(updateThread);
        }
    };

    //定义服务连接
    private class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyBinderInterface)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    //更新播放状态
    private void updateState() {
        int index = myBinder.getCurrentIndex();
        mListAdapter.setFocusItemPos(index);
        String currentMusicName = musicList.get(index).getMusic_title();
        playingName.setText(currentMusicName);
        btnPlay.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.pause));
        isPlaying = true;
    }
    //重置进度条
    private void resetMusicSeekBar(){
        musicSeekBar.setProgress(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan4);
        //通过工具类MusicUtils获取音乐信息列表
        musicList = MusicUtils.ResolveMusicToList(getApplicationContext());
        //获取视图
        initView();
        //设置列表标题
        String title = "本地音乐数量";
        title += "（总数："+ musicList.size() + "）";
        listTitle.setText(title);
        //为mListView注册上下文菜单
        registerForContextMenu(mListView);
        conn = new MyServiceConnection();
        //绑定服务
        bindService(new Intent(this,MusicService.class),conn, Context.BIND_AUTO_CREATE);

        handler.post(updateThread);
    }
    //初始化视图
    private void initView(){
        listTitle = (TextView)findViewById(R.id.music_list_title);
        playingName = (TextView)findViewById(R.id.music_name);
        musicSeekBar = (SeekBar)findViewById(R.id.music_seek_bar);

        btnPrevious = (ImageView)findViewById(R.id.btn_previous);
        btnNext = (ImageView)findViewById(R.id.btn_next);
        btnPlay = (ImageView)findViewById(R.id.btn_play);

        mListView = (ListView)findViewById(R.id.music_list);
        //需要传入的参数:环境,数据源
        mListAdapter = new MusicAdapter(Shiyan4Activity.this,musicList);
        //设置适配器
        mListView.setAdapter(mListAdapter);

        setListener();
    }
    //设置监听事件
    private void setListener(){
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mListView.showContextMenu();
                currentSel = position;
                return true;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myBinder.setCurrentIndex(position);
                myBinder.Play();

                mListAdapter.setFocusItemPos(position);
                updateState();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    myBinder.PlayPrev();
                    updateState();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    myBinder.PlayNext();
                    updateState();
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    btnPlay.setImageBitmap(BitmapFactory.decodeResource(
                            getResources(),R.drawable.play));
                    isPlaying = false;
                    myBinder.Pause();
                    return;
                }
                if (!isPlaying){
                    if (myBinder.getCurrentIndex() == -1){
                        myBinder.setCurrentIndex(0);
                        mListAdapter.setFocusItemPos(0);
                        myBinder.Play();
                        updateState();
                    }
                    btnPlay.setImageBitmap(BitmapFactory.decodeResource(
                            getResources(),R.drawable.pause));
                    isPlaying = true;
                    myBinder.Resume();

                }
            }
        });

        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (myBinder != null){
                    try {
                        myBinder.seekTo(seekBar.getProgress());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(0,0,0,R.string.menu_detail);
        menu.add(0,1,1,R.string.menu_play);
        menu.add(0,2,2,"删除");
        super.onCreateContextMenu(menu,view,menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                StringBuilder msgBuilder = new StringBuilder();
                msgBuilder.append("文件名：" + musicList.get(currentSel).getMusic_name() + "\n");
                msgBuilder.append("路  径：" + musicList.get(currentSel).getMusic_path() + "\n");
                msgBuilder.append("时  长：" + musicList.get(currentSel).getMusic_duration()/1000 + " s\n");
                String title = "文件详情";
                new AlertDialog.Builder(Shiyan4Activity.this)
                        .setIcon(R.drawable.note)
                        .setTitle(title)
                        .setMessage(msgBuilder.toString())
                        .setPositiveButton(R.string.btn_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }}).create().show();
                break;
            case 1:
                //不处于播放状态 或者 选择的歌曲和正在播放的歌曲不是同一首 则更新状态且播放
                if (isPlaying == false || currentSel != myBinder.getCurrentIndex()){
                    myBinder.setCurrentIndex(currentSel);
                    updateState();
                    myBinder.Play();
                }
                //提示选择的歌曲已经在播放了
                else{
                    Toast.makeText(Shiyan4Activity.this,R.string.str_playing,Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                //删除当前选中的音乐
                musicList.remove(currentSel);
                myBinder.Stop();
                updateState();
                resetMusicSeekBar();

                Log.e("TAG", String.valueOf(musicList.size()));
                //通知适配器更新数据
                mListAdapter.notifyDataSetChanged();
                //设置ListView自动显示到最新数据
                mListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                //设置适配
                mListView.setAdapter(mListAdapter);
                //将更新后的list传回service
                myBinder.setList(musicList);
                //删除后自动播放下一首,
                myBinder.Play();
                updateState();


            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.music_menu,menu);
        return retValue;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.help_item){
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append("音乐播放器  V1.0.0\n");
            msgBuilder.append("作者：姚泱茹\n");
            //msgBuilder.append("(C) 2019 ......");
            String title = "帮助文档";
            new AlertDialog.Builder(Shiyan4Activity.this)
                    .setIcon(R.drawable.note)
                    .setTitle(title)
                    .setMessage(msgBuilder.toString())
                    .setPositiveButton(R.string.btn_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }}).create().show();
        }
        if (item.getItemId() == R.id.exit_item){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        String title = "提示";
        new AlertDialog.Builder(Shiyan4Activity.this)
                .setIcon(R.drawable.note)
                .setTitle(title)
                .setMessage("确定要退出吗?")
                .setPositiveButton(R.string.btn_confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myBinder.Release();
                        finish();
                    }
                })
                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
    }
}