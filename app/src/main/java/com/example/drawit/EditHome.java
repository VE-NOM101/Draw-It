package com.example.drawit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;

import java.io.ByteArrayOutputStream;

public class EditHome extends AppCompatActivity {
    ImageView edit, camera;
    int IMAGE_REQUEST_CODE = 45;
    int CAMERA_REQUEST_CODE = 14;
    int RESULT_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_home);
        edit = findViewById(R.id.editBtn);
        camera = findViewById(R.id.cameraBtn);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(EditHome.this,
                        android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditHome.this,
                            new String[] {Manifest.permission.CAMERA}, 32);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(requestCode == IMAGE_REQUEST_CODE) {
                if(data.getData() != null) {
                    Uri filePath = data.getData();
                    Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
                    dsPhotoEditorIntent.setData(filePath);
                    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "DrawIT");
                    int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_FILTER,DsPhotoEditorActivity.TOOL_PIXELATE,
                            DsPhotoEditorActivity.TOOL_WARMTH,DsPhotoEditorActivity.TOOL_CONTRAST,DsPhotoEditorActivity.TOOL_EXPOSURE,DsPhotoEditorActivity.TOOL_FRAME,
                            DsPhotoEditorActivity.TOOL_ROUND,DsPhotoEditorActivity.TOOL_SATURATION,DsPhotoEditorActivity.TOOL_SHARPNESS,DsPhotoEditorActivity.TOOL_VIGNETTE};
                    dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
                    startActivityForResult(dsPhotoEditorIntent, RESULT_CODE);
                }
            }
        }catch (Exception e){

        }

        if(requestCode==RESULT_CODE){
            Toast.makeText(this,"Image has been saved",Toast.LENGTH_SHORT).show();
        }

        try {
            if(requestCode == CAMERA_REQUEST_CODE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Uri uri = getImageUri(photo);
                Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
                dsPhotoEditorIntent.setData(uri);
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "DrawIT");
                int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_FILTER,DsPhotoEditorActivity.TOOL_PIXELATE,
                        DsPhotoEditorActivity.TOOL_WARMTH,DsPhotoEditorActivity.TOOL_CONTRAST,DsPhotoEditorActivity.TOOL_EXPOSURE,DsPhotoEditorActivity.TOOL_FRAME,
                        DsPhotoEditorActivity.TOOL_ROUND,DsPhotoEditorActivity.TOOL_SATURATION,DsPhotoEditorActivity.TOOL_SHARPNESS,DsPhotoEditorActivity.TOOL_VIGNETTE};
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
                startActivityForResult(dsPhotoEditorIntent, RESULT_CODE);
            }
        }catch (Exception e){

        }

    }

    public Uri getImageUri(Bitmap bitmap) {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, arrayOutputStream);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }
}