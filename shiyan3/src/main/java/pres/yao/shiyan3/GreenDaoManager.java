package pres.yao.shiyan3;

import android.content.Context;
import org.greenrobot.greendao.query.QueryBuilder;
import pres.yao.shiyan3.entity.TrashEntity;
import pres.yao.shiyan3.entity.TrashEntityDao;

import java.util.List;

/**
 * @ClassName GreenDaoManager
 * @Description TOOD
 * Date 2020/10/26 20:44
 **/
public class GreenDaoManager {
    Context mContext;
    private final TrashEntityDao mTrashEntityDao;

    public GreenDaoManager(Context context) {
        this.mContext = context;
        mTrashEntityDao = MyApplication.mDaoSession.getTrashEntityDao();
    }

    //增加数据
    public void insertTrash(TrashEntity trashEntity){
        mTrashEntityDao.insertOrReplace(trashEntity);
    }

    //删除数据
    public void deleteTrash(TrashEntity trashEntity){
        mTrashEntityDao.delete(trashEntity);
    }

    //更新数据
    public void updateTrash(TrashEntity trashEntity){
        mTrashEntityDao.update(trashEntity);
    }

    //查询数据
    public TrashEntity findTrash(TrashEntity trashEntity){
        QueryBuilder<TrashEntity> queryBuilder = mTrashEntityDao.queryBuilder()
                //根据id排序
                .orderAsc(TrashEntityDao.Properties.Id);
        return queryBuilder.unique();
    }

    public List<TrashEntity> selectAll(){
        return mTrashEntityDao.loadAll();
    }
}
