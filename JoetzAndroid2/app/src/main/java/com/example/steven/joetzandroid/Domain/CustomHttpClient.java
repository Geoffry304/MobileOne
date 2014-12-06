package com.example.steven.joetzandroid.Domain;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;


import org.apache.http.HttpResponse;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;




public class CustomHttpClient {

    private static HttpClient customHttpClient;

    public static synchronized HttpClient getHttpClient() {
        if (customHttpClient != null) {
            return customHttpClient;
        }
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params,
                HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);

        ConnManagerParams.setTimeout(params, 1000);

        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, 10000);

        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        schReg.register(new Scheme("https",
        SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
                params, schReg);

        customHttpClient = new DefaultHttpClient(conMgr, params);
        return customHttpClient;

    }


    public static Bitmap getScaledBitmap(String res,int resId,
                                         int reqWidth, int reqHeight)
    {
        Bitmap bitmap = null;

        try {
            URL url = new URL(res);

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //BitmapFactory.decodeStream(url.openStream(),null,options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeStream(url.openStream(),null,options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeBitmap(String s) {

       Bitmap bitmap=null;
        try {
            URL url = new URL(s);
            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inSampleSize=5;
            options.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeStream(url.openStream(),null,options);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }



    private static final String TAG = "Berekeningen";
    public static Bitmap getBitmap(String path) {


        try {
            URL url = new URL(path);
            InputStream in = url.openStream();

            final int IMAGE_MAX_SIZE = 1200000; // 1.2MP


            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, o);
            in.close();
            int scale = 1;
            while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {
                scale++;
            }
            Log.d(TAG, "scale = " + scale + ", orig-width: " + o.outWidth + ", orig - height: " + o.outHeight);

            Bitmap b = null;

            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                o = new BitmapFactory.Options();
                o.inSampleSize = scale;
                b = BitmapFactory.decodeStream(in, null, o);

                // resize to desired dimensions
                int height = b.getHeight();
                int width = b.getWidth();
                Log.d(TAG, "1th scale operation dimenions - width: " + width + ",height: " + height);

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, (int) x,
                        (int) y, true);
                b.recycle();
                b = scaledBitmap;

                System.gc();
            } else {
                b = BitmapFactory.decodeStream(in);
            }
            in.close();

            Log.d(TAG, "bitmap size - width: " + b.getWidth() + ", height: " +
                    b.getHeight());
            return b;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

}
