package rd.samurmuan.somjai.rdrun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by user on 8/30/2016.
 */
//ตัวอักษรตัวแรกตัวใหญ่ Class Name
    //method 2 type
    //void
    //return
public class MyAlert {
    //      การต่อท่อบน programming เรียกว่า context
  public  void myDialog(Context context,
                        int intIcon,
                        String strTitle,
                        String strMessage) {
      //ใช้ AlertDialog.app
      // new ctrl+space ใส่อัตโนมัติ
      AlertDialog.Builder builder = new AlertDialog.Builder(context);
      builder.setCancelable(false);//
      builder.setIcon(intIcon);
      builder.setTitle(strTitle);
      builder.setMessage(strMessage);
      builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();//ให้ dialog หายไป

          }
      });//ในวงเล็บ () ctrl+enter , new เว้นวรรค ctrl+ space
      builder.show();


  }
}//main class
