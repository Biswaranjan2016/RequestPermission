package com.example.happy.requestpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final private int REQUEST_CODE_ASK_PERMISSION = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissionStatus();
    }
    private void checkPermissionStatus(){
        int hasWriteContactPermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR);
        if (hasWriteContactPermission != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_CALENDAR},REQUEST_CODE_ASK_PERMISSION);
            return;
        }
        printStatus("In checkPermission status & Permission was granted");
    }
    public void onRequestPermissionsResult(int code, String permissions[],
                                           int grantResults[]){
        switch (code){
            case REQUEST_CODE_ASK_PERMISSION:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    printStatus("Permission Granted");
                }else{
                    printStatus("Permission Denied");
                }
                break;

            default:
                super.onRequestPermissionsResult(code,permissions,grantResults);
        }

    }
    private void printStatus(String status){
        TextView textView = (TextView) findViewById(R.id.showStatus);
        textView.setText(status);
    }

}
