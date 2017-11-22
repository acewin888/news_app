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

        public Articles(Parcel sources){
            author = sources.readString();
            title = sources.readString();
            description = sources.readString();
            url = sources.readString();
            urlToImage = sources.readString();
            publishedAt = sources.readString();
            source =sources.readParcelable(Source.class.getClassLoader());


        }

        private Source source;

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
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
            dest.writeParcelable(source, flags);
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

        public static class Source implements Parcelable{
            private String id;
            private String name;

            public Source(Parcel source){
                id = source.readString();
                name = source.readString();
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(name);
            }
            public static final Creator<Source> CREATOR = new Creator<Source>() {
                @Override
                public Source createFromParcel(Parcel source) {
                    return new Source(source);
                }

                @Override
                public Source[] newArray(int size) {
                    return new Source[size];
                }
            };
        }
    }

}
