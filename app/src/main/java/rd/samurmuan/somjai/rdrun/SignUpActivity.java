package rd.samurmuan.somjai.rdrun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {
//Explicit การประกาศตัวแปร
    //ประกอบด้วย 3 อย่าง
    //-access การเข้าถึง public private /type ประเภทข้อมูล /name
    private EditText nameEditText,surnameEditText,userEditText, passwordEditText;
    private RadioGroup radioGroup;
    private RadioButton avata1RadioButton,avata2RadioButton,avata3RadioButton,avata4RadioButton, avata5RadioButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //bind or Initial Widget คือ การผูกความสัมพันธ์ระหว่างตัวแปร และ Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        radioGroup = (RadioGroup) findViewById(R.id.ragAvata);
        avata1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        avata2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avata3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avata4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avata5RadioButton = (RadioButton) findViewById(R.id.radioButton5);

    }//main method
    public void clickSignUpSign(View view) {

    }//clickSign
}//main class

//alt+11 hind tab
//shift+ctrl+enter close complement
//ctrl+space เติมคำ
//alt+enter ใส่ แคทส
