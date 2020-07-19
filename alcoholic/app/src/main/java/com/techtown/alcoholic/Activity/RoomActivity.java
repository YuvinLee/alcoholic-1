package com.techtown.alcoholic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.techtown.alcoholic.R;

import java.util.Hashtable;

public class RoomActivity extends AppCompatActivity {
    private String TAG = "RoomLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        context=this;

        fragmentManager = getSupportFragmentManager();

        btnRoomGameListFragment= findViewById(R.id.btn_RoomGameListFragment);
        btnRoomInfoFragment = findViewById(R.id.btn_RoomInfoFragment);


        fragmentTransaction= fragmentManager.beginTransaction();

        btnRoomInfoFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFrag(0);
            }
        });
        btnRoomGameListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFrag(1);
            }
        });

        roomGameListFragment = new RoomGameListFragment();
        roomInfoFragment = new RoomInfoFragment();


        fragmentTransaction.replace(R.id.FrameLayout,roomInfoFragment).commitAllowingStateLoss();
        setFrag(0);

    }



    public void setFrag(int n) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (n){
            case 0:
                fragmentTransaction.replace(R.id.FrameLayout,roomInfoFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                    fragmentTransaction.replace(R.id.FrameLayout,roomGameListFragment);

                fragmentTransaction.commit();
                break;

        }


        imageViewQRCode = (ImageView)findViewById(R.id.qrCode);


        textForQRCode = "테스트";

        Log.d(TAG, "onCreate: ");
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            /* Encode to utf-8 */
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

            //width랑 height에서 QR코드 이미지 크기 조정
            BitMatrix bitMatrix = multiFormatWriter.encode(textForQRCode, BarcodeFormat.QR_CODE,200,200, hints);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageViewQRCode.setImageBitmap(bitmap);

        }catch (Exception e){}

    }

}
