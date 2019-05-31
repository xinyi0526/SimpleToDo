package sg.edu.rp.c346.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText task;
    Button addbtn;
    Button clearbtn;
    Button deletebtn;
    ArrayList taskList;
    ArrayAdapter aa;
    ListView lv;
    Spinner spn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = findViewById(R.id.editText);
        addbtn = findViewById(R.id.Addbutton);
        clearbtn = findViewById(R.id.clearButton);
        deletebtn = findViewById(R.id.deleteButton);

        lv = findViewById(R.id.lvTask);
        spn= findViewById(R.id.spinner);

        taskList = new ArrayList<String>();
        aa= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,taskList);
        lv.setAdapter(aa);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = task.getText().toString();
                task.setText("");
                taskList.add(t);
                aa.notifyDataSetChanged();
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                aa.notifyDataSetChanged();
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = task.getText().toString();
                boolean isInteger = true;
                for(int i = 0; i < text.length(); i ++){
                    if(!Character.isDigit(text.charAt(i))){
                        isInteger = false;
                    }
                }

                if(!isInteger){
                    Toast.makeText(MainActivity.this, "Please enter integer number", Toast.LENGTH_LONG).show();

                }
                else{
                    int pos = Integer.parseInt(text);
                    if(taskList.isEmpty()){
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(pos+1 > taskList.size()){
                        Toast.makeText(MainActivity.this,"Wrong index number", Toast.LENGTH_LONG).show();
                        return;
                    }
                    taskList.remove(pos);
                }
                aa.notifyDataSetChanged();
                lv.setAdapter(aa);
                task.setText("");
            }
        });
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        addbtn.setEnabled(true);
                        deletebtn.setEnabled(false);
                        task.setHint("Type a new task here");
                        task.setText("");
                        break;
                    case 1:
                        addbtn.setEnabled(false);
                        deletebtn.setEnabled(true);
                        task.setHint("Type in the index of the task to be removed");
                        task.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {



            }
        });
    }
}
