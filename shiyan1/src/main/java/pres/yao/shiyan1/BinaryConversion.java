package pres.yao.shiyan1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @ClassName BinaryConversion
 * @Description TOOD
 * Date 2020/10/7 10:48
 **/
public class BinaryConversion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_conversion);

        final TextView tv1 = findViewById(R.id.sys_textView_1);
        final TextView tv2 = findViewById(R.id.sys_textView_2);

        final Spinner p1 = findViewById(R.id.sys_spinner_first);
        final Spinner p2 = findViewById(R.id.sys_spinner_second);

        final StringBuffer s = new StringBuffer();

        Button bt0 = findViewById(R.id.sys_btn_0);
        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("0");
                tv1.setText(s);
            }
        });

        Button bt1 = findViewById(R.id.sys_btn_1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("1");
                tv1.setText(s);
            }
        });

        Button bt2 = findViewById(R.id.sys_btn_2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("2");
                tv1.setText(s);
            }
        });

        Button bt3 = findViewById(R.id.sys_btn_3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("3");
                tv1.setText(s);
            }
        });

        Button bt4 = findViewById(R.id.sys_btn_4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("4");
                tv1.setText(s);
            }
        });

        Button bt5 = findViewById(R.id.sys_btn_5);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("5");
                tv1.setText(s);
            }
        });

        Button bt6 = findViewById(R.id.sys_btn_6);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("6");
                tv1.setText(s);
            }
        });

        Button bt7 = findViewById(R.id.sys_btn_7);
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("7");
                tv1.setText(s);
            }
        });

        Button bt8 = findViewById(R.id.sys_btn_8);
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("8");
                tv1.setText(s);
            }
        });

        Button bt9 = findViewById(R.id.sys_btn_9);
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("9");
                tv1.setText(s);
            }
        });

        Button bta = findViewById(R.id.sys_btn_a);
        bta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("A");
                tv1.setText(s);
            }
        });

        Button btb = findViewById(R.id.sys_btn_b);
        btb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("B");
                tv1.setText(s);
            }
        });

        Button btc = findViewById(R.id.sys_btn_c);
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("C");
                tv1.setText(s);
            }
        });

        Button btd = findViewById(R.id.sys_btn_d);
        btd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("D");
                tv1.setText(s);
            }
        });

        Button bte = findViewById(R.id.sys_btn_e);
        bte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("E");
                tv1.setText(s);
            }
        });

        Button btf = findViewById(R.id.sys_btn_f);
        btf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.append("F");
                tv1.setText(s);
            }
        });

        Button bt_del = findViewById(R.id.sys_btn_del);
        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.delete(s.length()-1,s.length());
                tv1.setText(s);
            }
        });

        Button bt_ce = findViewById(R.id.sys_btn_ce);
        bt_ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s.delete(0,s.length());
                tv1.setText("");
                tv2.setText("");
            }
        });

        Button bt_equal = findViewById(R.id.sys_btn_point) ;
        bt_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = p1.getSelectedItemPosition();
                int j = p2.getSelectedItemPosition();

                String st = s.toString();
                int m ;
                String val = "";

                if(i==j) {
                    tv2.setText(s);
                }

                if(i==0 && j==1) {
                    m = Integer.parseInt(wantonlyConversion(st,2));
                    val = Integer.toOctalString(m);
                    tv2.setText(val);
                }

                if(i==0 && j==2) {
                    m = Integer.parseInt(wantonlyConversion(st,2));
                    val = String.valueOf(m);
                    tv2.setText(val);
                }

                if(i==0 && j==3) {
                    m = Integer.parseInt(wantonlyConversion(st,2));
                    val = Integer.toHexString(m);
                    tv2.setText(val);
                }

                if(i==1 && j==0) {
                    m = Integer.parseInt(wantonlyConversion(st,8));
                    val = Integer.toBinaryString(m);
                    tv2.setText(val);
                }

                if(i==1 && j==2) {
                    m = Integer.parseInt(wantonlyConversion(st,8));
                    val = String.valueOf(m);
                    tv2.setText(val);
                }

                if(i==1 && j==3) {
                    m = Integer.parseInt(wantonlyConversion(st,8));
                    val = Integer.toHexString(m);
                    tv2.setText(val);
                }

                if(i==2 && j==0) {
                    m = Integer.parseInt(st);
                    val = Integer.toBinaryString(m);
                    tv2.setText(val);
                }

                if(i==2 && j==1) {
                    m = Integer.parseInt(st);
                    val = Integer.toOctalString(m);
                    tv2.setText(val);
                }

                if(i==2 && j==3) {
                    m = Integer.parseInt(st);
                    val = Integer.toHexString(m);
                    tv2.setText(val);
                }

                if(i==3 && j==0) {
                    m = Integer.parseInt(wantonlyConversion(st,16));
                    val = Integer.toBinaryString(m);
                    tv2.setText(val);
                }

                if(i==3 && j==1) {
                    m = Integer.parseInt(wantonlyConversion(st,16));
                    val = Integer.toOctalString(m);
                    tv2.setText(val);
                }

                if(i==3 && j==2) {
                    m = Integer.parseInt(wantonlyConversion(st,16));
                    val =String.valueOf(m);
                    tv2.setText(val);
                }
            }
        });
    }
    //任意进制转换为十进制
    public static String wantonlyConversion(String a, int b) {
        int r = 0;
        for (int i = 0; i < a.length(); i++) {
            r = (int) (r + formatting(a.substring(i, i + 1))
                    * Math.pow(b, a.length() - i - 1));
        }
        return String.valueOf(r);
    }

    public static int formatting(String a) {
        int i = 0;
        for (int u = 0; u < 10; u++) {
            if (a.equals(String.valueOf(u))) {
                i = u;
            }
        }
        if (a.equals("A")) {
            i = 10;
        }
        if (a.equals("B")) {
            i = 11;
        }
        if (a.equals("C")) {
            i = 12;
        }
        if (a.equals("D")) {
            i = 13;
        }
        if (a.equals("E")) {
            i = 14;
        }
        if (a.equals("F")) {
            i = 15;
        }
        return i;
    }
}
