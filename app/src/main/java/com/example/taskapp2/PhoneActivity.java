package com.example.taskapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    private EditText editPhone, editSMS;
    private Button buttonC, buttonSI;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    String codeSent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        editPhone = findViewById(R.id.editPhone);
        editSMS = findViewById(R.id.editSMS);
        buttonC = findViewById(R.id.buttonCont);
        buttonSI = findViewById(R.id.buttonSignIn);
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Log.e("TAG", "OnVerificationCompleted");
//                signIn(phoneAuthCredential);
//                phoneAuthCredential.getSmsCode();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("TAG", "onVerificationFailed"+e.getMessage());

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                codeSent = s;


            }
        };
    }
    private void signInCode(){
        String code = editSMS.getText().toString();
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(codeSent, code);
        phoneAuthCredential.getSmsCode();
        signIn(phoneAuthCredential);

    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance()
                .signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(PhoneActivity.this, "Успешно", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PhoneActivity.this, MainActivity.class));
                    finish();
                }else {
                    Log.e("TAG", "Error "+ task.getException().getMessage());
                    Toast.makeText(PhoneActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void onClick(View view) {
        editPhone.setVisibility(View.GONE);
        buttonC.setVisibility(View.GONE);
        editSMS.setVisibility(View.VISIBLE);
        buttonSI.setVisibility(View.VISIBLE);

        String phone = editPhone.getText().toString().trim();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone, 60, TimeUnit.SECONDS, this,callbacks
        );
    }

    public void signInBut(View view) {
        signInCode();

    }
}
