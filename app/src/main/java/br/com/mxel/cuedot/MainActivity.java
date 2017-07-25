package br.com.mxel.cuedot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyClass my = new MyClass();
    }

    // SF_SWITCH_NO_DEFAULT
    // SF_SWITCH_FALLTHROUGH
    /*private void findBugsFailTest(int var) {
        switch (var) {
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
        }
    }*/

    /*private void pmdFailTest(int a, int b, int c, int d) {
        if (a > b) {
            if (b > c) {
                if (c > d) {
                    if (d > a)
                        System.out.println("PMD");
                        // some logic

                }
            }
        }
    }*/
}
