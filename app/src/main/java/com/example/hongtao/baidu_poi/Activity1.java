package com.example.hongtao.baidu_poi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.search.geocode.GeoCoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Activity1 extends AppCompatActivity {
    private final int requestCode = 1;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Button button = (Button)findViewById(R.id.fileToStringArray);
        textView = (TextView)findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,requestCode);
            }
        });
    }
    String path = "";
    String result = "";
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
          if(resultCode == Activity.RESULT_OK) {
              Uri uri = data.getData();
              path = uri.getPath();
              //textView.setText(path);
              String Locations[] = new String[20];
              try{
                  FileInputStream fileInputStream = new FileInputStream(path);
                  int length = fileInputStream.available();
                  byte []buffer = new byte[length];
                  fileInputStream.read(buffer);
                  String string = new String(buffer,length);
                  textView.setText(string);
              }catch (FileNotFoundException e){
                  e.printStackTrace();
              }catch(IOException e){
                  e.printStackTrace();
              }
//haha
          }
    }
}
