package com.example.karisir.tumblrphotographyapplication ;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.tumblr.jumblr.JumblrClient;

import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Photo;
import com.tumblr.jumblr.types.PhotoPost;
import com.tumblr.jumblr.types.QuotePost;
import com.tumblr.jumblr.types.TextPost;
import com.tumblr.jumblr.types.User;

import java.io.File;





public class GoToTumblr extends ActionBarActivity {

    ImageView iv;
    TextView textView;
    TextView Blogs;
    Bitmap perm;
    String Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_tumblr);
        iv = (ImageView) findViewById(R.id.Pic);

        Intent in = getIntent();

        Bundle extras = in.getExtras();
        Bitmap bmp = extras.getParcelable("imagebitmap");
        Bitmap perm = extras.getParcelable("imagebitmap");
        iv.setImageBitmap(bmp);

        TumblrPostPhotoAsyncTask task = new TumblrPostPhotoAsyncTask();
        task.execute(perm, url2, url3);

        //enableStrictMode();
        //BlogList();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_go_to_tumblr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void BlogList() {

        textView = (TextView) findViewById(R.id.UserName);
        textView.setTextSize(40);
        Blogs = (TextView) findViewById(R.id.BlogList);
        Blogs.setTextSize(40);


        // Authenticate via OAuth

        JumblrClient client = new JumblrClient(
                "LAZS0SIhKk2Wh3vwJzvhdV12qgAZ0yZOXYguPEcpjsl64ehLar",
                "ZNLaDFYVsGAzykjjOIyT7wurwtKW8wXSNSDwGquadFolP6YNOm"
        );
        client.setToken(
                "cZxdZzDA6W8UTxTtjCauQYyF9EaupIe3c1ktxKuiN2Z7vcB6hX",
                "EXl1oDxxGZOKVWJXvY4lPivXrczQ023ylFi6AxTl9JHcQ20A09"
        );

// Make the request
        User user = client.user();
        textView.setText(user.getName());


        // And list their blogs
        for (Blog blog : user.getBlogs()) {
            Blogs.setText("\t" + blog.getTitle());
            Name = blog.getTitle();
        }

        // TextPost post = client.newPost(Name,TextPost.class);
        // post.setBody("hello world");
        // post.save();

    }

    public void enableStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
}




