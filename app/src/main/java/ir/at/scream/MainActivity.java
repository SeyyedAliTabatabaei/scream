package ir.at.scream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.pushpole.sdk.PushPole;

import ir.at.scream.main.FragmentMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PushPole.initialize(this,true);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FragmentMain.REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this , "لطفا به میکروفون دسترسی دهید", Toast.LENGTH_SHORT).show();
        }
    }
}