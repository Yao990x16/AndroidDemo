package pres.yao.shiyan4;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.*;

public class Shiyan4Activity extends AppCompatActivity implements View.OnClickListener{
    MediaPlayer player = new MediaPlayer();
    List<Music> musicList;
    ListView list_view;
    File file;
    TextView text_now,text_all,text_name;
    Button btn_last,btn_begin,btn_next,btn_model;
    private SeekBar plan;
    // 是否正在播放
    private boolean mPlaying;
    // 目前播放的音乐位置
    private int play_index;
    //播放模式（true为循环，false为随机）
    private boolean model;
    // 播放时间
    private int play_time;
    //拖动后的进度
    private int play_change;
    //当前音乐文件的总时长
    private int all_time;
    // 计时器
    private Timer timer;

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            int progress = player.getCurrentPosition();
            plan.setProgress(progress);
            setTime(progress/1000,false);
            if (play_time == all_time||!player.isPlaying())
                play_next();
            return true;
        }
    });

    public void play(){
        if(play_change!=play_time) {
            stopTime();
            play_time = play_change;
            player.seekTo(play_time);
            startTime();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void play(int i) {
        if(mPlaying) {
            if (play_index != i) {//选中了未在正在播放的曲子
                try {
                    stopTime();
                    plan.setProgress(0);
                    btn_begin.setEnabled(true);
                    btn_begin.setBackground(getResources().getDrawable(R.drawable.pause,getTheme()));
                    text_now.setText("0:00");
                    player.reset();
                    player.setDataSource(musicList.get(i).getPath() + musicList.get(i).getName());
                    player.prepare();
                    player.start();
                    play_index = i;
                    text_name.setText(musicList.get(play_index).getName());
                    all_time = player.getDuration();
                    setTime(all_time/1000,true);
                    play_time = 0;
                    plan.setMax(player.getDuration());
                    startTime();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else{
            if (play_index != i) {//未选中暂停的曲子或者未开始播放
                try {
                    player.reset();
                    stopTime();
                    btn_begin.setEnabled(true);
                    btn_begin.setBackground(getResources().getDrawable(R.drawable.pause,getTheme()));
                    plan.setProgress(0);
                    text_now.setText("0:00");
                    player.setDataSource(musicList.get(i).getPath() + musicList.get(i).getName());
                    player.prepare();
                    player.start();
                    play_index = i;
                    text_name.setText(musicList.get(play_index).getName());
                    all_time = player.getDuration();
                    setTime(all_time/1000,true);
                    play_time = 0;
                    plan.setMax(player.getDuration());
                    startTime();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{//选中了暂停的曲子
                player.start();
                btn_begin.setBackground(getResources().getDrawable(R.drawable.pause,getTheme()));
                startTime();
            }
            mPlaying = true;
        }
    }

    public void pause() {
        if(mPlaying) {
            stopTime();
            btn_begin.setBackground(getResources().getDrawable(R.drawable.play_small,getTheme()));
            player.pause();
            mPlaying = false;
        }
    }

    public void play_next(){
        if(model) {
            if(play_index==musicList.size()-1)
                play(0);
            else
                play(play_index + 1);
        }
        else {
            int ran;
            do {
                ran = random();
            } while (ran == play_index);
            play(ran);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.music_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                if(ContextCompat.checkSelfPermission(Shiyan4Activity.this, Manifest.permission.
                        WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Shiyan4Activity.this,new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("audio/*");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.help_item:
                Toast.makeText(this,"此为帮助文档界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_item:
                finish();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.
                    PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(this, "拒绝访问", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan4);
        initMediaPlayer();
        text_all = findViewById(R.id.text_all);
        text_name = findViewById(R.id.text_name);
        text_now = findViewById(R.id.text_now);
        btn_last = findViewById(R.id.btn_last);
        btn_begin = findViewById(R.id.btn_begin);
        btn_next = findViewById(R.id.btn_next);
        btn_model = findViewById(R.id.btn_model);
        btn_last.setOnClickListener(this);
        btn_begin.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_model.setOnClickListener(this);
        list_view = findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter(this);
        list_view.setAdapter(adapter);
        plan=findViewById(R.id.plan);
        plan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                play_change = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                play();
            }
        });
    }

    private void initMediaPlayer(){
        try{
            musicList = new ArrayList<>();
            file = getFileStreamPath("music");
            File[] subFile = file.listFiles();
            Toast.makeText(this, "已添加"+file.getPath()+"中的文件", Toast.LENGTH_SHORT).show();
            int num = 0;
            for (int i = 0; i < Objects.requireNonNull(subFile).length; i++) {
                if (!subFile[i].isDirectory()) {
                    String filename = subFile[i].getName();
                    if (filename.trim().toLowerCase().endsWith(".mp3")) {
                        Music music = new Music(file.getPath()+"/",filename, num++);
                        musicList.add(music);
                    }
                }
            }
            mPlaying = false;
            play_index = -1;
            model = true;
        }
        catch(Exception ignored){ }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_last:
                if(model) {
                    if(play_index==0)
                        play(musicList.size()-1);
                    else
                        play(play_index - 1);
                }
                else {
                    int ran;
                    do {
                        ran = random();
                    } while (ran == play_index);
                    play(ran);
                }
                break;
            case R.id.btn_begin:

                if(Objects.equals(btn_begin.getBackground().getConstantState(), Objects.requireNonNull(ContextCompat.getDrawable(this,
                        R.drawable.play_small)).getConstantState())){

                    btn_begin.setBackground(getResources().getDrawable(R.drawable.pause,getTheme()));
                    play(play_index);

                }
                else{
                    btn_begin.setBackground(getResources().getDrawable(R.drawable.play_small,getTheme()));
                    pause();
                }
                break;
            case R.id.btn_next:
                play_next();
                break;
            case R.id.btn_model:
                if(Objects.equals(btn_model.getBackground().getConstantState(), getResources().getDrawable(R.drawable.ordered, getTheme()).getConstantState())){
                    btn_model.setBackground(getResources().getDrawable(R.drawable.unordered,getTheme()));
                    Toast.makeText(Shiyan4Activity.this, "随机播放模式",
                            Toast.LENGTH_SHORT).show();
                    model = false;
                }
                else{
                    btn_model.setBackground(getResources().getDrawable(R.drawable.ordered,getTheme()));
                    Toast.makeText(Shiyan4Activity.this, "顺序播放模式",
                            Toast.LENGTH_SHORT).show();
                    model = true;
                }
                break;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(player != null){
            player.stop();
            player.release();
        }
    }


    public class MyAdapter extends BaseAdapter {

        private final LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return musicList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder= new ViewHolder();
                convertView = mInflater.inflate(R.layout.music_item, null);
                holder.name = (Button)convertView.findViewById(R.id.name);
                holder.delete = (Button)convertView.findViewById(R.id.delete);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.name.setTag(position);
            holder.name.setText((String)musicList.get(position).getName());
            holder.delete.setTag(position);
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    play(position);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_delete(position);
                }
            });
            return convertView;
        }
    }

    private static final class ViewHolder {
        private Button delete;
        private Button name;
    }

    public void list_delete(int i){
        musicList.remove(i);
        List<Music> mid = new ArrayList<>();
        for(int j =0;j<musicList.size();j++){
            Music music = new Music(musicList.get(j).getPath(),musicList.get(j).getName(),j);
            mid.add(music);
        }
        musicList = mid;
        MyAdapter adapter = new MyAdapter(this);
        list_view.setAdapter(adapter);
        if(play_index==i){
            if(model) {
                play_index = -1;
                play(i);
            }
            else {
                play_index = -1;
                play(random());
            }
        }
    }

    public int random(){
        return (int)(1+Math.random()*(musicList.size()));
    }

    private void setTime(int i,boolean mod){
        String str = "";
        int a = i / 60;
        int b = i % 60;
        if( b < 10 )
            str = a + ":0" + b;
        else
            str = a + ":" + b;
        if(mod)
            text_all.setText("/"+str);
        else
            text_now.setText(str);
    }

    private void startTime() {
        if(timer==null){
            timer = new Timer();
        }
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(mPlaying) {
                    play_time = player.getCurrentPosition();
                    Message msg = Message.obtain();
                    mHandler.sendMessageDelayed(msg, 1);
                }
            }
        };
        timer.schedule(timerTask, 100,100);
    }

    private void stopTime() {
        if(timer!=null) {
            timer.cancel();
            timer = null;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                Uri uri = data.getData();
                StringBuilder add_path = new StringBuilder("/sdcard/");
                assert uri != null;
                String[] s = Objects.requireNonNull(uri.getPath()).split(":");
                s = s[1].split("/");
                for(int i = 0; i < s.length-1;i++){
                    add_path.append(s[i]).append("/");
                }
                Music music = new Music(add_path.toString(),s[s.length-1],musicList.size());
                musicList.add(music);
                MyAdapter adapter = new MyAdapter(this);
                list_view.setAdapter(adapter);
                Toast.makeText(this, add_path+s[s.length-1]+"添加成功", Toast.LENGTH_SHORT).show();
            }
        }
    }
}