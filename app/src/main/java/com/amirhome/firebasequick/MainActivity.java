package com.amirhome.firebasequick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Firebase mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button mButtonFoggy = (Button) findViewById(R.id.buttonFoggy);
        Button mButtonSunny = (Button) findViewById(R.id.buttonSunny);
        final TextView mTextCondition = (TextView) findViewById(R.id.textViewCondition);

        mRef = new Firebase("https://eat2donate.firebaseio.com/");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String newCondition = (String) dataSnapshot.getValue();

                mTextCondition.setText(newCondition);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mButtonFoggy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mRef.setValue("Foggy..");
            }
        });

        mButtonSunny.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mRef.setValue("Sunny..");
            }
        });
    }
}
