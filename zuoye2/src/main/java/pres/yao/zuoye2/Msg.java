package pres.yao.zuoye2;

/**
 * @ClassName Msg
 * @Description TOOD
 * Date 2020/9/20 16:48
 **/
public class Msg {

    public static final int TYPE_RECEIVED = 0;

    public static final int TYPE_SENT = 1;

    private String content;

    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

}
