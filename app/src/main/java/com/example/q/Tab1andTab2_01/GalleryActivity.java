package com.example.q.Tab1andTab2_01;

import android.Manifest;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishakha on 18/10/16.
 */
public class GalleryActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private static final int URL_LOADER = 0;
    public GalleryPickerAdapter adapter;
    String permissions= new String (Manifest.permission.READ_EXTERNAL_STORAGE);
    private int PERMISSION_REQUEST_CODE = 200;
    AlbumView albumView = new AlbumView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_grid);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);  // 이거는 gallery를 보여주는 것과는 상관없음
        mRecyclerView.setLayoutManager(gridLayoutManager);

        if (adapter == null) {
            adapter = new GalleryPickerAdapter(getApplicationContext());
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkPermission()) {
            albumView.loadAlbum();
        }
    }

    private  boolean checkPermission() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        result = ContextCompat.checkSelfPermission(GalleryActivity.this,permissions);
        if (result != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(permissions);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(GalleryActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        if(permsRequestCode==200){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                albumView.loadAlbum();
            } else {
                Toast.makeText(GalleryActivity.this,  "Please give permission to use this feature", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class AlbumView implements LoaderManager.LoaderCallbacks<Cursor>{

        public void loadAlbum(){
            getLoaderManager().restartLoader(URL_LOADER, null, this);//restart the loader manager by invoking getSupportLoaderManager

        }

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            return new CursorLoader(getApplicationContext(),
                    GalleryPickerAdapter.uri,
                    GalleryPickerAdapter.projections,
                    null,
                    null,
                    GalleryPickerAdapter.sortOrder);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            adapter.setData(PhotosData.getData(true, cursor));
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }

}
