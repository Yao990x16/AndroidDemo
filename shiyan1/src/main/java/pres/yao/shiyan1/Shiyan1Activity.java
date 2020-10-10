package pres.yao.shiyan1;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Stack;

public class Shiyan1Activity extends AppCompatActivity implements View.OnClickListener{
    GridLayout only;
    TextView show;
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_back;
    Button btn_clean;
    Button btn_sin;
    Button btn_cos;
    Button btn_tan;
    Button btn_plus;
    Button btn_minus;
    Button btn_multiply;
    Button btn_divided;
    Button btn_point;
    Button btn_e;
    Button btn_equal;
    Button btn_left;
    Button btn_right;
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiyan1);
        only = findViewById(R.id.only);
        show = findViewById(R.id.show);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_back = findViewById(R.id.btn_back);
        btn_clean = findViewById(R.id.btn_clean);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_tan = findViewById(R.id.btn_tan);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divided = findViewById(R.id.btn_divided);
        btn_point = findViewById(R.id.btn_point);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        btn_e = findViewById(R.id.btn_e);
        btn_equal = findViewById(R.id.btn_equal);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divided.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_clean.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_e.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_tan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String str = show.getText().toString();
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:

                if (flag) {
                    flag = false;
                    str = "";
                    show.setText("");
                }
                show.setText(str + ((Button) view).getText());
                break;
            case R.id.btn_plus:
                if (flag) {
                    flag = false;
                    str = "";
                    show.setText("");
                }

                show.setText(show.getText().toString()+"+");
                break;
            case R.id.btn_minus:
                if (flag) {
                    flag = false;
                    str = "";
                    show.setText("");
                }

                show.setText(show.getText().toString()+"-");
                break;
            case R.id.btn_multiply:
                if (flag) {
                    flag = false;
                    str = "";
                    show.setText("");
                }

                show.setText(show.getText().toString()+"*");
                break;
            case R.id.btn_divided:
                if (flag) {
                    flag = false;
                    str = "";
                    show.setText("");
                }

                show.setText(show.getText().toString()+"/");
                break;
            case R.id.btn_clean:
                if (flag)
                    flag = false;
                str = "";
                show.setText("");
                break;
            case R.id.btn_back: //判断是否为空，然后在进行删除
                if (show.getText().toString().length() > 1) {
                    if (show.getText().toString().length() >= 3) {
                        int i = show.getText().toString().length();
                        String s = show.getText().toString().substring(i - 3, i);
                        Log.i("judge", s);
                        if (s.equals("sin") || s.equals("cos")||s.equals("tan")) {
                            show.setText(show.getText().toString().substring(0, show.getText().toString().length() - 3));
                        } else {
                            show.setText(show.getText().toString().substring(0, show.getText().toString().length() - 1));
                        }
                    } else {
                        show.setText(show.getText().toString().substring(0, show.getText().toString().length() - 1));
                    }

                } else {
                    show.setText("");
                }


                break;
            case R.id.btn_equal:
                getResult();
                break;

            case R.id.btn_sin:
                String stringNum1 = show.getText().toString();
                Double str_sin = Double.valueOf(stringNum1);
                double a = 0;
                a = Math.round(Math.sin(str_sin * Math.PI / 180) * 100000);
                show.setText(a / 100000 + "");
                break;

            case R.id.btn_cos:
                String stringNum2 = show.getText().toString();
                Double str_cos = Double.valueOf(stringNum2);
                double b = 0;
                b = Math.round(Math.cos(str_cos * Math.PI / 180) * 100000);
                show.setText(b / 100000 + "");
                break;
            case R.id.btn_tan:
                String stringNum3 = show.getText().toString();
                Double str_tan = Double.valueOf(stringNum3);
                double c = 0;
                c = Math.round(Math.tan(str_tan * Math.PI / 180) * 100000);
                show.setText(c / 100000 + "");
                break;

            case R.id.btn_left:


                show.setText(show.getText().toString() + "(");
                break;
            case R.id.btn_right:

                show.setText(show.getText().toString() + ")");
                break;
            case R.id.btn_e:
                String stringNume = show.getText().toString();
                Double str_e = Double.valueOf(stringNume);
                double d= 0;
                d = Math.round(Math.exp(str_e)*100000);
                show.setText(d/100000+ "");
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                //创建intent对象用于activity转换
                Intent intent_help = new Intent(Shiyan1Activity.this, Help.class);
                startActivity(intent_help);
                break;
            case R.id.binary:
                //创建intent对象用于activity转换
                Intent intent_binary = new Intent(Shiyan1Activity.this, BinaryConversion.class);
                startActivity(intent_binary);
                break;
            case R.id.unit:
                Intent intent_unit = new Intent(Shiyan1Activity.this, UnitConversion.class);
                startActivity(intent_unit);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //实例化MenuInflater对象，用于解析菜单资源文件，传递参数为上下文对象this
        MenuInflater menuInflater = new MenuInflater(this);
        //解析菜单资源文件，
        menuInflater.inflate(R.layout.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private static StringBuffer toPostfix(String infix) {
        Stack<String> stack = new Stack<String>();
        StringBuffer Postfix = new StringBuffer(infix.length() * 2);
        int i = 0;
        while (i < infix.length()) {
            char ch = infix.charAt(i);
            switch (ch) {
                case '+':
                case '-':
                    while (!stack.isEmpty() && !stack.peek().equals("("))
                        Postfix.append(stack.pop());
                    stack.push(ch + "");
                    i++;
                    break;
                case '*':
                case '/':
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
                        Postfix.append(stack.pop());
                    stack.push(ch + "");
                    i++;
                    break;
                case '(':
                    stack.push(ch + "");
                    i++;
                    break;
                case ')':
                    String out = stack.pop();
                    while (out != null && !out.equals("(")) {
                        Postfix.append(out);
                        out = stack.pop();
                    }
                    i++;
                    break;
                default:
                    while (i < infix.length() && ch >= '0' && ch <= '9' || ch == '.' || ch == 's' || ch == 'c' || ch == 't') {
                        Postfix.append(ch);
                        i++;
                        Log.i("where", String.valueOf(ch));
                        if (i < infix.length())
                            ch = infix.charAt(i);
                    }
                    Postfix.append(" ");
            }
        }
        while (!stack.isEmpty())
            Postfix.append(stack.pop());
        return Postfix;
    }

    private static double toValue(StringBuffer postfix) {
        Stack<Double> stack = new Stack<Double>();
        int value = 0;

        double a = 0;
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if (ch >= '0' && ch <= '9' || ch == '.' || ch == 's' || ch == 'c' || ch == 't') {
                value = 0;
                String sum = "";
                while (ch != ' ') {
                    sum += ch;
                    a = Double.valueOf(sum);
                    ch = postfix.charAt(++i);
                }
//               i Log.i("ii",String.valueOf(s))
                stack.push(a);
            } else if (ch != ' ') {
                double y = stack.pop(), x = stack.pop();
                switch (ch) {
                    case '+':
                        a = x + y;
                        break;
                    case '-':
                        a = x - y;
                        break;
                    case '*':
                        a = x * y;
                        break;
                    case '/':
                        a = x / y;
                        break;
                    case 's':
                        a = Math.sin(x);
                        break;
                    case 'c':
                        a = Math.cos(x);
                        break;
                    case 't':
                        a = Math.tan(x);


                }
                System.out.print(x + (ch + "") + y + "=" + value + ",");
                stack.push(a);
            }
        }
        return stack.pop();
    }

    private void getResult() {
//        try {
        double res = toValue(toPostfix(show.getText().toString()));
        show.setText(String.valueOf(res));
//        }catch (Exception e)
//        {
//            show.setText("error");
//        }

        /** String exp=show.getText().toString();
         if(exp==null||exp.equals("")) return ;
         //因为没有运算符所以不用运算
         if(!exp.contains(" ")){
         return ;
         }
         if(flag){
         flag=false;
         return;
         }
         flag=true;
         //截取运算符前面的字符串
         String s1=exp.substring(0,exp.indexOf(" "));
         //截取的运算符
         String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
         //截取运算符后面的字符串
         String s2=exp.substring(exp.indexOf(" ")+3);
         double cnt=0;
         if(!s1.equals("")&&!s2.equals("")){
         double d1=Double.parseDouble(s1);
         double d2=Double.parseDouble(s2);
         if(op.equals("+")){
         cnt=d1+d2;
         }
         if(op.equals("-")){
         cnt=d1-d2;
         }
         if(op.equals("*")){
         cnt=d1*d2;
         }
         if(op.equals("/")){
         if(d2==0) cnt=0;
         else cnt=d1/d2;
         }
         if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
         int res = (int) cnt;
         show.setText(res+"");
         }else {
         show.setText(cnt+"");}
         }
         //如果s1是空    s2不是空  就执行下一步
         else if(!s1.equals("")&&s2.equals("")){
         double d1=Double.parseDouble(s1);
         if(op.equals("+")){
         cnt=d1;
         }
         if(op.equals("-")){
         cnt=d1;
         }
         if(op.equals("*")){
         cnt=0;
         }
         if(op.equals("/")){
         cnt=0;
         }
         if(!s1.contains(".")) {
         int res = (int) cnt;
         show.setText(res+"");
         }else {
         show.setText(cnt+"");}
         }
         //如果s1是空    s2不是空  就执行下一步
         else if(s1.equals("")&&!s2.equals("")){
         double d2=Double.parseDouble(s2);
         if(op.equals("+")){
         cnt=d2;
         }
         if(op.equals("-")){
         cnt=0-d2;
         }
         if(op.equals("*")){
         cnt=0;
         }
         if(op.equals("/")){
         cnt=0;
         }
         if(!s2.contains(".")) {
         int res = (int) cnt;
         show.setText(res+"");
         }else {
         show.setText(cnt+"");}
         }
         else {
         show.setText("");
         }
         }**/

    }
}
