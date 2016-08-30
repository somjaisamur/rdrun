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
    private String nameString,surnameString,userString,passwordString,avataString;//วิธีพิมพ์ surnameกดctrl+space
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
        //get value from edit text
        nameString = nameEditText.getText().toString().trim();//รับ จาก text แปลงเป็น String ตัดช่องว่าง
        surnameString = surnameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString=passwordEditText.getText().toString().trim();
        //check space ตรวจสอบหาข่องว่าง
        if (checkSpace()) {//เอา cursor วางใน () alt+enter สร้าง method อัตโนมัริ
            //true
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,R.drawable.doremon48,"มีช่องว่าง",
                    "กรุณากรอกช่องว่าง คะ");
        }

    }//clickSign

    private boolean checkSpace() {
        boolean result =false;

        if (nameString.equals("") ||
                surnameString.equals("")||
                userString.equals("") ||
                passwordString.equals("")) {//if กด shift+ctrl+enter มันจะใส่ (){} อัตโนมัติ
            result = true;
        }
        return result;
    }
}//main class

//alt+11 hind tab
//shift+ctrl+enter close complement
//ctrl+space เติมคำ
//alt+enter ใส่ แคทส
