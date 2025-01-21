package com.zgqinc.loginregister;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, emailEditText;
    private RadioGroup genderRadioGroup;
    private CheckBox checkboxSports, checkboxMusic, checkboxTravel, checkboxReading;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // 初始化控件
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);

        // 兴趣爱好复选框
        checkboxSports = findViewById(R.id.checkbox_sports);
        checkboxMusic = findViewById(R.id.checkbox_music);
        checkboxTravel = findViewById(R.id.checkbox_travel);
        checkboxReading = findViewById(R.id.checkbox_reading);

        registerButton = findViewById(R.id.registerButton);

        // 注册按钮点击事件
        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String email = emailEditText.getText().toString();

        // 获取选中的性别
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId != -1) {
            RadioButton selectedGenderButton = findViewById(selectedGenderId);
            gender = selectedGenderButton.getText().toString();
        }

        // 获取兴趣爱好
        StringBuilder hobbies = new StringBuilder();
        if (checkboxSports.isChecked()) hobbies.append("Sports ");
        if (checkboxMusic.isChecked()) hobbies.append("Music ");
        if (checkboxTravel.isChecked()) hobbies.append("Travel ");
        if (checkboxReading.isChecked()) hobbies.append("Reading ");

        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !gender.isEmpty()) {
            // 保存注册信息到 SharedPreferences
            SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("email", email);
            editor.putString("gender", gender);
            editor.putString("hobbies", hobbies.toString().trim());
            editor.apply();

            // 注册成功提示
            Toast.makeText(this, "Registration Successful!\nUsername: " + username, Toast.LENGTH_LONG).show();

            // 结束当前 Activity
            finish();
        } else {
            // 提示用户填写所有字段
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
