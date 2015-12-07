package zhangyf.vir56k.flowframelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SimpleFlowLayout simpleFlowLayout;
    ViewGroup.MarginLayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleFlowLayout = (SimpleFlowLayout) findViewById(R.id.simpleFlowLayout);

        lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int margin = 5;

        lp.leftMargin = margin;
        lp.rightMargin = margin;
        lp.topMargin = margin;
        lp.bottomMargin = margin;

        Random random = new Random();
        TextView view;

        view = createView("一");
        simpleFlowLayout.addView(view);

        view = createView("二二");
        simpleFlowLayout.addView(view, lp);

        view = createView("三三三");
        simpleFlowLayout.addView(view, lp);

        view = createView("四四四四");
        simpleFlowLayout.addView(view, lp);

        view = createView("五五五五五");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九n");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九零一二三");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九零一二三四五六七八九");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九零一二三");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九零一二三四五六七八九");
        simpleFlowLayout.addView(view, lp);
        for (int i = 0; i < 15; i++) {
            String word = "";
            int count = random.nextInt(10);
            for (int j = 0; j < count; j++) {
                word += "字";
            }
            view = createView(word);
            simpleFlowLayout.addView(view, lp);
        }
    }

    TextView createView(String str) {
        TextView view = new TextView(MainActivity.this);
        view.setText(str.trim());
        view.setTextSize(16);
        return view;
    }


    void demo1(){
        TextView view;


        view = createView("一");
        simpleFlowLayout.addView(view, lp);

        view = createView("二二");
        simpleFlowLayout.addView(view, lp);

        view = createView("三三三");
        simpleFlowLayout.addView(view, lp);

        view = createView("四四四四");
        simpleFlowLayout.addView(view, lp);

        view = createView("五五五五五");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九零一二三");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九零一二三四五六七八九");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九零一二三");
        simpleFlowLayout.addView(view, lp);

        view = createView("一二三四五六七八九零一二三四五六七八九");
        simpleFlowLayout.addView(view, lp);
    }

}
