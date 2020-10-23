package pres.yao.shiyan4;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @ClassName MusicAdapter
 * @Description TOOD
 * Date 2020/10/22 18:19
 **/
public class MusicAdapter extends BaseAdapter {
    private final List<Music> musicList;
    private final LayoutInflater layoutInflater;
    private final Context context;
    private int currentPos = -1;
    private ViewHolder holder = null;

    public MusicAdapter(Context context,List<Music> musicList) {
        this.musicList = musicList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setFocusItemPos(int pos){
        currentPos = pos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int position) {
        return musicList.get(position).getMusic_title();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void remove(int index){
        if(index<0){
            currentPos = 0;
            return;
        }else {
            musicList.remove(index);
        }
        notifyDataSetChanged();
    }

    public void refreshDataSet(){
        notifyDataSetChanged();
    }
    //获取试图,设置list每一项显示效果
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            //将布局资源转为view
            //参数1:引用的布局资源
            //参数2:父容器,一般为null
            convertView = layoutInflater.inflate(R.layout.item_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        //如果是正在播放的音乐 就改变图片、字体颜色
        if (position == currentPos){
            holder.itemIcon.setImageBitmap(BitmapFactory.decodeResource(
                    context.getResources(),R.drawable.arrow));
            holder.itemMusicName.setTextColor(Color.RED);
            holder.itemMusicSinger.setTextColor(Color.RED);
        }
        //否则使用默认图片、字体颜色
        else{
            holder.itemIcon.setImageBitmap(BitmapFactory.decodeResource(
                    context.getResources(),R.drawable.music));
            holder.itemMusicName.setTextColor(holder.defaultTextColor);
            holder.itemMusicSinger.setTextColor(holder.defaultTextColor);
        }
        holder.itemMusicName.setText(musicList.get(position).getMusic_title());
        holder.itemMusicSinger.setText(musicList.get(position).getMusic_artist());
        return convertView;
    }
}
class ViewHolder{
    public ImageView itemIcon;
    public TextView itemMusicName;
    public TextView itemMusicSinger;
    public int defaultTextColor;

    View itemView;

    public ViewHolder(View itemView) {
        if (itemView == null){
            throw new IllegalArgumentException("item View can not be null!");
        }
        this.itemView = itemView;
        itemIcon = itemView.findViewById(R.id.rand_icon);
        itemMusicName = itemView.findViewById(R.id.item_music_name);
        itemMusicSinger = itemView.findViewById(R.id.item_music_singer);

        defaultTextColor = itemMusicName.getCurrentTextColor();
    }
}