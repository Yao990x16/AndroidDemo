/*
package pres.yao.shiyan3.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

*/
/**
 * @ClassName TrashConverter
 * @Description TOOD
 * Date 2020/10/26 19:53
 **//*

public class TrashConverter implements PropertyConverter<List<TrashEntity.NewslistBean>,String> {

    @Override
    public List<TrashEntity.NewslistBean> convertToEntityProperty(String databaseValue) {
        if(databaseValue == null) {
            return null;
        }else{
            // 先得获得这个，然后再typeToken.getType()，否则会异常
            TypeToken<List<TrashEntity.NewslistBean>> typeToken = new TypeToken<List<TrashEntity.NewslistBean>>(){};
            return new Gson().fromJson(databaseValue, typeToken.getType());
        }
    }

    @Override
    public String convertToDatabaseValue(List<TrashEntity.NewslistBean> entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        return new Gson().toJson(entityProperty);
    }
}
*/
