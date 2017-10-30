package com.example.ravin.marvel.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ravin.marvel.R;
import com.example.ravin.marvel.utils.Constants;
import com.example.ravin.marvel.utils.SharedUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_signin)
    Button btnSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_signin)
    public void onViewClicked() {
        if(!etEmail.getText().toString().isEmpty()&&!etPassword.getText().toString().isEmpty()){
            validatecredentials(etEmail.getText().toString(),etPassword.getText().toString());
        }else if(etEmail.getText().toString().isEmpty()){
            etEmail.setError("Field is mandatory");
        }
        else if(etPassword.getText().toString().isEmpty()){
            etPassword.setError("Field is mandatory");
        }
    }

    public void validatecredentials(String email,String password){
        if(email.equalsIgnoreCase("marvel")&&password.equalsIgnoreCase("12345")){
            Intent home = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(home);
            SharedUtils.set(this, Constants.LOGINSTATUS,"Active");
            finish();
        }else{
            Toast.makeText(this, "Authenticaion failed", Toast.LENGTH_SHORT).show();
        }

    }
}
