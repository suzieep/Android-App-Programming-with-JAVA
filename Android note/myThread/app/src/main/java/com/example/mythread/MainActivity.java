package com.example.mythread;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog myProgressDlg;
    private Bitmap myBitMap = null;
    Button myButton;
    ImageView myImageView;
    EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = (Button) findViewById(R.id.button);
        myImageView = (ImageView) findViewById(R.id.imageView);
        myEditText = (EditText) findViewById(R.id.editText);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInternetConenction()) {
                    downloadImage(myEditText.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Internet is not connected.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myImageView.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));
            myProgressDlg.dismiss();
        }
    };

    // 인터넷 연결을 connectivityManager를 사용하여 점검한다.
    // 인터넷이 연결된 상태라면 true를 아니면 false를 리턴한다.
    private boolean checkInternetConenction() { // get Connectivity Manager object to check connection
        ConnectivityManager connect = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        // Check for network connections
        if (connect.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED || connect.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING || connect.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING || connect.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (connect.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED || connect.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }

    private void downloadImage(String urlStr) {
        myProgressDlg = ProgressDialog.show(this, "", "Downloading Image from " + urlStr);
        final String url = urlStr;
// 뜨레드 처리로 화면 멈춤을 막아줌.
        new Thread() {
            public void run() {
                InputStream in = null;
                Message msg = Message.obtain();
                msg.what = 1;
                try { // http 연결
                    in = openHttpConnection(url);// 다운받은 파일을 비트맵으로 변환시켜 화면에 그려줄 준비를 해줌.
                    myBitMap = BitmapFactory.decodeStream(in);
// 비트맵 파일은 메시지 핸들러를 통해 처리되어야 한다. 이를 위해 번들 클래스를
// 활용하여 비트맵 파일을 메시지 핸들러에 보낼 수 있도록 한다.
                    if (in != null) {
                        Bundle b = new Bundle();
                        b.putParcelable("bitmap", myBitMap);
                        msg.setData(b);
                    } else {
                        Toast.makeText(getApplicationContext(), "The image is not available at the address you gave.", Toast.LENGTH_LONG).show();
                    }
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
// 메시지 핸들러에 이미지 비트맵을 전송
                messageHandler.sendMessage(msg);
            }
        }.start();
    } // http 연결과 데이터 송수신을 담당한다.

    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;
        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();
            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}