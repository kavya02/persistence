package helloworld.example.com.helloworld;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edittext_message;
    RelativeLayout lay_send;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init SharedPreference and Editor
        sharedPreferences = getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();

        edittext_message = (EditText) findViewById(R.id.edittext_message);
        lay_send = (RelativeLayout) findViewById(R.id.lay_send);

        // Show Saved Message
        if(!TextUtils.isEmpty(sharedPreferences.getString(getResources().getString(R.string.key_name), ""))){
            edittext_message.setText(sharedPreferences.getString(getResources().getString(R.string.key_name), ""));
        }

        // EditText ChangeListener
        edittext_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString(getResources().getString(R.string.key_name), edittext_message.getText().toString());
                editor.commit();
            }
        });

        // Send Button ClickListener
        lay_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edittext_message.getText().toString())){

                    //Clear Saved Message
                    edittext_message.setText("");
                    Toast.makeText(MainActivity.this, "Send Message Successfully!", Toast.LENGTH_LONG).show();
                    editor.clear();
                    editor.commit();
                }else{
                    Toast.makeText(MainActivity.this, "Please Enter Message!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
