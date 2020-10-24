package pres.yao.shiyan3.gson;

import java.util.List;

/**
 * @ClassName Trash
 * @Description TOOD
 * Date 2020/10/24 13:03
 **/
public class Trash {

    /**
     * code : 200
     * msg : success
     * newslist : [{"name":"西瓜皮","type":2,"aipre":0,"explain":"厨余垃圾是指居民日常生活及食品加工、饮食服务、单位供餐等活动中产生的垃圾。","contain":"常见包括菜叶、剩菜、剩饭、果皮、蛋壳、茶渣、骨头等","tip":"纯流质的食物垃圾、如牛奶等，应直接倒进下水口。有包装物的湿垃圾应将包装物去除后分类投放、包装物请投放到对应的可回收物或干垃圾容器"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    @Override
    public String toString() {
        return "Trash{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", newslist=" + newslist +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * name : 西瓜皮
         * type : 2
         * aipre : 0
         * explain : 厨余垃圾是指居民日常生活及食品加工、饮食服务、单位供餐等活动中产生的垃圾。
         * contain : 常见包括菜叶、剩菜、剩饭、果皮、蛋壳、茶渣、骨头等
         * tip : 纯流质的食物垃圾、如牛奶等，应直接倒进下水口。有包装物的湿垃圾应将包装物去除后分类投放、包装物请投放到对应的可回收物或干垃圾容器
         */

        private String name;
        private int type;
        private int aipre;
        private String explain;
        private String contain;
        private String tip;

        @Override
        public String toString() {
            return "NewslistBean{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    ", aipre=" + aipre +
                    ", explain='" + explain + '\'' +
                    ", contain='" + contain + '\'' +
                    ", tip='" + tip + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAipre() {
            return aipre;
        }

        public void setAipre(int aipre) {
            this.aipre = aipre;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getContain() {
            return contain;
        }

        public void setContain(String contain) {
            this.contain = contain;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }
}
