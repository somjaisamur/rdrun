package rd.samurmuan.somjai.rdrun;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ServiceActivity extends FragmentActivity implements OnMapReadyCallback {
    //Explicit
    private GoogleMap mMap;
    private String idString, avataString, nameString, surnameString;
    private ImageView imageView;
    private TextView nameTextView, surnameTextView;
    private int[] avataInts;
    private double userLatADouble=13.674900, userLngADouble = 100.721001;//Connection ,  //13.806576,100.579742
    private LocationManager locationManager;//location ในแผนที่
    private Criteria criteria;//เงื่อนไขในการค้นหา
    private static final String urlPHP = "http://swiftcodingthai.com/rd/edit_location_somjai.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_service);
        //bind Widget
        imageView = (ImageView) findViewById(R.id.imageView7);
        nameTextView = (TextView) findViewById(R.id.textView8);
        surnameTextView = (TextView) findViewById(R.id.textView9);



        //Get Value From Intent
        idString = getIntent().getStringExtra("id");
        avataString = getIntent().getStringExtra("Avata");
        nameString = getIntent().getStringExtra("Name");
        surnameString = getIntent().getStringExtra("Surname");

        // setup location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);//open location service
        //หาระดับของ location service
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//ค้นหาโดยละเอียด 300 เมตร
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);




        //show text
        nameTextView.setText(nameString);
        surnameTextView.setText(surnameString);

        //show avata
        MyConstant myConstant = new MyConstant();
        avataInts = myConstant.getAvataInts();
        imageView.setImageResource(avataInts[Integer.parseInt(avataString)]);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }// Main method

    private class SynAllUser extends AsyncTask<Void, Void, String> {// alt+ enter create auto doInBackground
        // Explicit
        private Context context;
        private GoogleMap googleMap;
        private static final String urlJSON = "http://swiftcodingthai.com/rd/get_user_master.php";


        //Alt+ insert
        public SynAllUser(Context context, GoogleMap googleMap) {
            this.context = context;
            this.googleMap = googleMap;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("2SupV2 ", "e doIn ==> " + e.toString());
                return null;
            }

        }// doInBack
         //alt+insert create method onPostExecute
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("2SepV2", "JSON ==> " + s);



        }//onPost

    }// SynallUser Class




//change mode ==> run to pouse or pouse to run

//alt+ insert shoutkey

    @Override
    protected void onResume() {//when on app. start find location
        super.onResume();
        locationManager.removeUpdates(locationListener);

        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER); //ค่า location ที่ได้จากการต่อ internet
        if (networkLocation!=null) {
            //true
            userLngADouble = networkLocation.getLongitude();
            userLngADouble = networkLocation.getLongitude();

        }
        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER);// ค้นหา location จาก card GPS
        if (gpsLocation != null) {
            userLatADouble = gpsLocation.getLatitude();
            userLngADouble = gpsLocation.getLongitude();
        }
    }// onResume

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);//when close app. stop find location
    }

    //สร้าง method ใหม่ ใต้ main เสมอ
    public Location myFindLocation(String strProvider) {//จะใช้ card หรือ isp หา location
        Location location = null;
        if (locationManager.isProviderEnabled(strProvider)) {
            //true
            locationManager.requestLocationUpdates(strProvider,1000,10,locationListener);//1นาทีค่้นหาา
            location = locationManager.getLastKnownLocation(strProvider);

        } else {

            Log.d("1SepV1","Cannot find Location");
        }

        return location;
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {//ทำเมื่อ location เปลี่ยน
            userLatADouble = location.getLatitude();
            userLngADouble = location.getLongitude();


        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {//ทำเมื่อเปลี่ยนสถานะ

        }

        @Override
        public void onProviderEnabled(String s) {//ออก Internet ได้ให้ทำอะไระ

        }

        @Override
        public void onProviderDisabled(String s) {//ออก Internet ไม่ได้ให้ทำอะไร

        }
    };
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       // setup center of map
        LatLng latLng = new LatLng(userLatADouble, userLngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));// level การ Zoom map ตอนเปิด map มีทั้งหมด 20 level
        //Loop
        myLoop();//alt + enter create method
    }//onMapReady

    private void myLoop() {
        //To Do
        Log.d("1SepV2", "Lat ==> " + userLatADouble);
        Log.d("1SepV2", "Lng ==> " + userLngADouble);
        // โยนค่าขึ้้น  server
        editLatLngOnServer();//alt+enter auto method

        //create marker
        createMarker();//alt+enter

        //Post Delay
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myLoop();
            }
        },1000);//หน่วง 1 วินาที

    }// my loop

    private void createMarker() {
        SynAllUser synAllUser = new SynAllUser(this,mMap);// ctrl+P ใน () เพื่อให้แสดงค่า parametor ที่ต้องการ
        synAllUser.execute();


    }//create marker

    private void editLatLngOnServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        //request body
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("id", idString)
                .add("Lat", Double.toString(userLatADouble))
                .add("Lng", Double.toString(userLngADouble))
                .build();
        //จ่าหน้าซอง
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("2SepV1"," ====>" + e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("2SepV1", "Result ===> " + response.body().toString());

            }
        });

    }
}//Main Class
//alt + 7 === show structure