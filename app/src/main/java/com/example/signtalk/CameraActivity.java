package com.example.signtalk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.*;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import com.google.mediapipe.tasks.vision.handlandmarker.*;
import com.google.mediapipe.tasks.core.BaseOptions;
import com.google.mediapipe.tasks.vision.core.RunningMode;
import com.google.mediapipe.framework.image.MPImage;
import com.google.mediapipe.framework.image.BitmapImageBuilder;

public class CameraActivity extends AppCompatActivity {

    PreviewView previewView;
    TextView gestureText;

    Button spaceBtn, clearBtn;

    HandLandmarker handLandmarker;

    String word = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);

        previewView = findViewById(R.id.previewView);
        gestureText = findViewById(R.id.gestureText);

        spaceBtn = findViewById(R.id.spaceBtn);
        clearBtn = findViewById(R.id.clearBtn);

        setupHandLandmarker();

        spaceBtn.setOnClickListener(v -> {

            word += " ";
            gestureText.setText(word);

        });

        clearBtn.setOnClickListener(v -> {

            word = "";
            gestureText.setText("");

        });

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    101);

        } else {

            startCamera();

        }

    }

    // ---------------- HAND LANDMARK SETUP ----------------

    private void setupHandLandmarker(){

        BaseOptions baseOptions =
                BaseOptions.builder()
                        .setModelAssetPath("hand_landmarker.task")
                        .build();

        HandLandmarker.HandLandmarkerOptions options =
                HandLandmarker.HandLandmarkerOptions.builder()
                        .setBaseOptions(baseOptions)
                        .setRunningMode(RunningMode.IMAGE)
                        .setNumHands(1)
                        .build();

        handLandmarker =
                HandLandmarker.createFromOptions(
                        this,
                        options);

    }

    // ---------------- CAMERA START ----------------

    private void startCamera(){

        ListenableFuture<ProcessCameraProvider> future =
                ProcessCameraProvider.getInstance(this);

        future.addListener(() -> {

            try {

                ProcessCameraProvider provider = future.get();

                Preview preview =
                        new Preview.Builder().build();

                preview.setSurfaceProvider(
                        previewView.getSurfaceProvider());

                ImageAnalysis analysis =
                        new ImageAnalysis.Builder()
                                .setBackpressureStrategy(
                                        ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build();

                analysis.setAnalyzer(
                        ContextCompat.getMainExecutor(this),
                        image -> {

                            Bitmap bitmap = dummyBitmap(image);

                            detectSign(bitmap);

                            image.close();

                        });

                provider.unbindAll();

                provider.bindToLifecycle(
                        this,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        analysis);

            }

            catch (Exception e){

                e.printStackTrace();

            }

        }, ContextCompat.getMainExecutor(this));

    }

    // ---------------- SIGN DETECTION ----------------

    private void detectSign(Bitmap bitmap){

        MPImage mpImage =
                new BitmapImageBuilder(bitmap).build();

        HandLandmarkerResult result =
                handLandmarker.detect(mpImage);

        if(result.landmarks().size()>0){

            String letter =
                    classifyLetter(result);

            if(!letter.equals("")){

                word += letter;

                runOnUiThread(() ->
                        gestureText.setText(word));

            }

        }

    }

    // ---------------- A-Z CLASSIFIER ----------------

    private String classifyLetter(HandLandmarkerResult result){

        float thumb = result.landmarks().get(0).get(4).y();
        float index = result.landmarks().get(0).get(8).y();
        float middle = result.landmarks().get(0).get(12).y();
        float ring = result.landmarks().get(0).get(16).y();
        float pinky = result.landmarks().get(0).get(20).y();

        if(index>thumb && middle>thumb && ring>thumb && pinky>thumb)
            return "A";

        if(index<thumb && middle<thumb && ring<thumb && pinky<thumb)
            return "B";

        if(index<thumb && pinky>thumb)
            return "C";

        if(index<thumb && middle>thumb)
            return "D";

        if(index>middle && middle>ring)
            return "E";

        if(thumb<index && pinky<middle)
            return "F";

        if(index<thumb && middle>thumb && ring>thumb)
            return "G";

        if(index<thumb && middle<thumb && ring>thumb)
            return "H";

        if(pinky<thumb && index>thumb)
            return "I";

        if(pinky<thumb && middle>thumb)
            return "J";

        return "";

    }

    // ---------------- BITMAP FIX ----------------

    private Bitmap dummyBitmap(ImageProxy image){

        return Bitmap.createBitmap(
                image.getWidth(),
                image.getHeight(),
                Bitmap.Config.ARGB_8888);

    }

}
