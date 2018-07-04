package com.example.q.Tab1andTab2_01;

import android.util.Log;

/**
 * Created by vaishakha on 18/10/16.
 */
public class PhotosModel {
    String bucketId, imageBucket, imagePath, imageName;

    public PhotosModel(String bucketId, String imageBucket, String imagePath, String imageName) {
        this.bucketId = bucketId;
        this.imageBucket = imageBucket;
        this.imageName = imageName;
        this.imagePath = imagePath;
        Log.d("MyTag", "PhotoModel");
    }

    public String getBucketId() {
        return bucketId;
    }

    public String getImageBucket() {
        return imageBucket;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

}
