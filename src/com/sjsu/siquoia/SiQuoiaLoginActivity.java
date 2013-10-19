/**
 * 
 */
package com.sjsu.siquoia;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * @author parnit
 *
 */
public class SiQuoiaLoginActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
