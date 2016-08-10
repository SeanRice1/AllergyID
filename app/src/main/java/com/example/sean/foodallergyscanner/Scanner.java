package com.example.sean.foodallergyscanner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class Scanner extends Activity {

    SurfaceView surfaceView;
    final static int CAMERA_REQUEST=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        int perm = ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

        if(perm == PackageManager.PERMISSION_GRANTED) {
            createScanner();

        }
        else
            requestCameraPermission();



    }

    public void requestCameraPermission(){
        if (ActivityCompat.checkSelfPermission(Scanner.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Scanner.this,new String[]{Manifest.permission.CAMERA},CAMERA_REQUEST);

        }
    }



    private void createScanner() {

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.UPC_E)
                .setBarcodeFormats(Barcode.UPC_A)
                .build();

        CameraSource.Builder builder = new CameraSource.Builder(this, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(15f)
                .setRequestedPreviewSize(1600, 1024);

        // make sure that auto focus is an available option
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
           builder.setAutoFocusEnabled(true);
        }

        final CameraSource cameraSource = builder.build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {


                try {
                    if (ActivityCompat.checkSelfPermission(Scanner.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestCameraPermission();

                    }
                    cameraSource.start(surfaceView.getHolder());

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                SparseArray<Barcode> resultingBarcodes = detections.getDetectedItems();

                    if (resultingBarcodes.size() > 0) {
                       Intent intent = new Intent(getApplicationContext(), Result.class);
                        intent.putExtra("UPC", resultingBarcodes.valueAt(0).displayValue);
                        startActivity(intent);
                        finish();
                    }

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[]
            ,int[] grantResults){


        switch (requestCode){
            case CAMERA_REQUEST: {
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    createScanner();

                }
            }


        }

    }

}