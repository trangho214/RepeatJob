package paxcreation.com.checklibraryout;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Timer;

import custom.SecretTextView;


public class MainActivity extends AppCompatActivity {
    SecretTextView txtSecret;
    Button button;
    Handler handler;
    Thread thread;
    boolean isThreadStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        txtSecret = (SecretTextView)findViewById(R.id.txtSecret);
        handler = new Handler();
        thread = new Thread(updateTimeThread);
        txtSecret.setDuration(3000);
        txtSecret.setIsVisible(true);
        button = (Button)findViewById(R.id.btnToggle);

        txtSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSecret.toggle();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isThreadStart) {
                    thread.start();
                    isThreadStart = false;
                }
                else {
                    thread.start();
                    isThreadStart = true;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                        TODO: do stuff: txtSecret.toggle();
                        txtSecret.toggle();
                        handler.postDelayed(this, 4000);
                        Log.d("count", "count");
                }
            }, 4000);
        }
    };
}
