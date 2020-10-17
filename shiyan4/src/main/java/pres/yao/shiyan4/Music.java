package pres.yao.shiyan4;

/**
 * @ClassName Music
 * @Description TOOD
 * Date 2020/10/17 8:36
 **/
public class Music {
    private String path;
    private String name;
    private int num;

    public Music(String path, String name, int num) {
        this.path = path;
        this.name = name;
        this.num = num;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
