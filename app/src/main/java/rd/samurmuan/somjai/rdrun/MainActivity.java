package rd.samurmuan.somjai.rdrun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    //Explicit
    private ImageView imageView;
    private EditText userEditText, passwordEditText;//type auto user=ctrl+space
    private String userString, passwordString;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind widget
        imageView = (ImageView) findViewById(R.id.imageView6);
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);// type auto Alt+Enter ===cast auto // shift+ctrl+Enter === close complement

        //load Image rom Server
        Picasso.with(this).load("http://swiftcodingthai.com/rd/Image/rd_logo.png")
                .resize(150, 150).into(imageView);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }// Main Method นี่คือ เมธอด

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://rd.samurmuan.somjai.rdrun/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://rd.samurmuan.somjai.rdrun/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    //create Inner Class
    //AsyncTask ถ้าทำงานไม่สำเร็จมันจะทำไปเรื่อย มี 3 สถานะ ก่อน กำลัง หลัง load
    // alt+Insert  import implement
    private class SynUser extends AsyncTask<Void, Void, String> {
        // Explicit
        private Context context;
        private String myUserString, myPasswordString;
        private static final String urlJSON = "http://swiftcodingthai.com/rd/get_user_master.php";// file ที่มี ๋ฆฯ์

        public SynUser(Context context, String myUserString, String myPasswordString) {
            this.context = context;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
        }

        @Override

        protected String doInBackground(Void... params) {//ต่อ intetnet
            //
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {//error ที่ยอมรับได้
                Log.d("31AugV2", "e doInBack ===> " + e.toString());
                return null;
            }
           // return null;
        }//doInBack
        //override คือ ดึงคุณสมบัติมาใช้ กด alt+Enter

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("31AugV2", "JSoN ==>" + s);
        }//on post
    }// sysnUser class

    public void clickSingInMain(View view) {
        //
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //check space
        if (userString.equals("") || passwordString.equals("")) {
            //have space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, R.drawable.rat48, "Have space",
                    "Please Fill All Every Blank");
        } else {
            SynUser synUser = new SynUser(this, userString, passwordString);
            synUser.execute();
            //No space
        }
    }//clickSingnIn

    // Get Event from Click Button
    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));

    }

}// Main Class
