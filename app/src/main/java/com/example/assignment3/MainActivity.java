package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    private LinearLayout linearInput,linearOutput;

    private EditText etname,etstudentid,etpassword,etconfirmpassword,etemail;

    private Button btnsubmit;

    private Spinner spinneritem;


    private TextView tvresult;
    private  String item,name,studentid,pass,confirmpassword,email;

    //pattern declaring
    private Pattern namepattern= Pattern.compile("[A-Z]{1}[a-zA-Z .-]+");
    private Pattern passpattern=Pattern.compile("(\\w|\\W){8,}");
    private Pattern emailpattern=Pattern.compile("[a-z](?:[a-z\\d]*_?[a-z\\d]+)*(@gmail|@yahoo)\\.com");
private  Pattern studentidpattern=Pattern.compile("^0182210012101[0-9]{3}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        linearInput=findViewById(R.id.linear_input);
        linearOutput=findViewById(R.id.linear_output);
        etname=findViewById(R.id.et_name);
        etpassword=findViewById(R.id.et_password);
        etemail=findViewById(R.id.et_email);
        spinneritem=findViewById(R.id.spinner_item);
        btnsubmit=findViewById(R.id.btn_submit);
        tvresult=findViewById(R.id.tv_output);
        etconfirmpassword=findViewById(R.id.et_confirmpassword);
        etstudentid=findViewById(R.id.et_studentid);


        String[] str={"Select","Cricket","Football","Baseball","Tennis","Swimming","Basketball"};
        spinneritem.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,str));
        spinneritem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item=spinneritem.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=etname.getText().toString();
                studentid=etstudentid.getText().toString();
                email=etemail.getText().toString();
                pass=etpassword.getText().toString();
               confirmpassword=etconfirmpassword.getText().toString();
                if(name.isEmpty()){
                    etname.setError("Empty name");
                    etname.requestFocus();
                }


                else if(!namepattern.matcher(name).matches()){
                    etname.setError("create a valid name");
                    etname.requestFocus();

                }


                else if(studentid.isEmpty()){
                    etstudentid.setError("Empty student id");
                    etstudentid.requestFocus();
                }
                else if(!studentidpattern.matcher(studentid).matches()){
                etstudentid.setError("create a valid student id");
                    etstudentid.requestFocus();

                }

                else if(pass.isEmpty()){
                    etpassword.setError("Empty password");
                    etpassword.requestFocus();
                }
                else if(!passpattern.matcher(pass).matches()){
                    etpassword.setError("create a valid password");
                    etpassword.requestFocus();

                }

                else if (!pass.equals(confirmpassword)) {
                    etconfirmpassword.setError("Passwords do not match");
                    etconfirmpassword.requestFocus();}


                else if(email.isEmpty()){
                    etemail.setError("Empty email");
                    etemail.requestFocus();
                }else if(!emailpattern.matcher(email).matches()){

                    etemail.setError("create a valid email");
                    etemail.requestFocus();
                }
                else if(item.equals("Select")){
                    Toast.makeText(getApplication(),"Please select an item",Toast.LENGTH_SHORT).show();
                }
                else{
                    linearInput.setVisibility(View.GONE);
                    linearOutput.setVisibility(View.VISIBLE);
                    tvresult.setText("Name:"+name + "\nStudent Id:"+ studentid+ "\nPassword:"+pass + "\nEmail:"+email +"\nItem:"+item);
                }
            }

        });







    }
}