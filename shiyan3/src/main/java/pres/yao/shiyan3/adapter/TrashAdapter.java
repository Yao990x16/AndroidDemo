package pres.yao.shiyan3.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pres.yao.shiyan3.GreenDaoManager;
import pres.yao.shiyan3.R;
import pres.yao.shiyan3.entity.TrashEntity;

import java.util.*;

/**
 * @ClassName TrashAdapter
 * @Description TOOD
 * Date 2020/10/26 8:36
 **/
public class TrashAdapter extends RecyclerView.Adapter<TrashAdapter.TrashViewHolder>{
    private final Context context;
    private List<TrashEntity> dataSource;

    public TrashAdapter(Context context){
        this.context = context;
        this.dataSource = new ArrayList<>();

    }

    public void setDataSource(List<TrashEntity> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }
    
    /*
     * @Description: 创建并且返回ViewHolder
     * @Param [parent, viewType]
     * @return pres.yao.shiyan3.adapter.TrashAdapter.TrashViewHolder
     **/
    @NonNull
    @Override
    public TrashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrashViewHolder(LayoutInflater.from(context).inflate(R.layout.record_item_layout,parent,false)) ;
    }
    
    /*
     * @Description: 通过ViewHolder绑定数据
     * @Param [holder, position]
     * @return void
     **/
    @Override
    public void onBindViewHolder(@NonNull TrashViewHolder holder, final int position) {
        final TrashEntity trashEntity = dataSource.get(position);
        holder.trash_nameView.setText(trashEntity.getName());
        holder.trash_descriptionView.setText(
                "垃圾类型:"+trashEntity.getExplain()+"\n"
                +"包括:"+trashEntity.getContain()+"\n"
                +"提示:"+trashEntity.getTip());
        holder.trash_deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.remove(position);
                notifyItemRemoved(position);
                new GreenDaoManager(context).deleteTrash(trashEntity);
            }
        });
    }
    
    /*
     * @Description: 返回数据数量
     * @Param []
     * @return int
     **/
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    static class TrashViewHolder extends RecyclerView.ViewHolder {
        TextView trash_nameView,trash_descriptionView;
        Button trash_deleteButton;
        public TrashViewHolder(@NonNull View itemView) {
            super(itemView);
            trash_nameView = itemView.findViewById(R.id.item_trash_name);
            trash_descriptionView = itemView.findViewById(R.id.item_trash_description);
            trash_deleteButton = itemView.findViewById(R.id.button_record_delete);
        }
    }

}
