package com.example.android.newsapp.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by kevinsun on 11/13/17.
 */

public class News {

    private String status;



    private List<Articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public static class Articles implements Parcelable {

        public Articles(String author, String title, String description, String url, String urlToImage, String publishedAt) {
            this.author = author;
            this.title = title;
            this.description = description;
            this.url = url;
            this.urlToImage = urlToImage;
            this.publishedAt = publishedAt;
        }

        public Articles(Parcel source){
            author = source.readString();
            title = source.readString();
            description = source.readString();
            url = source.readString();
            urlToImage = source.readString();
            publishedAt = source.readString();

        }

        private String author;

        private String title;

        private String description;

        private String url;

        private String urlToImage;

        private String publishedAt;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(author);
            dest.writeString(title);
            dest.writeString(description);
            dest.writeString(url);
            dest.writeString(urlToImage);
            dest.writeString(publishedAt);
        }

        public static final Creator<Articles> CREATOR = new Creator<Articles>() {
            @Override
            public Articles createFromParcel(Parcel source) {
                return new Articles(source);
            }

            @Override
            public Articles[] newArray(int size) {
                return new Articles[size];
            }
        };
    }
}
