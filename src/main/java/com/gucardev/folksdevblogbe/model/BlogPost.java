package com.gucardev.folksdevblogbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Where(clause = "deleted = false")
public class BlogPost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private boolean deleted;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp modified;

    @NotNull
    @NotBlank
    private String name;


    private String imageUrl;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "LONGTEXT")
    private String details;

    @NotNull
    @NotBlank
    private String videoUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return id.equals(blogPost.id) && deleted == blogPost.deleted && Objects.equals(created, blogPost.created) && Objects.equals(modified, blogPost.modified) && Objects.equals(name, blogPost.name) && Objects.equals(imageUrl, blogPost.imageUrl) && Objects.equals(details, blogPost.details) && Objects.equals(videoUrl, blogPost.videoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, created, modified, name, imageUrl, details, videoUrl);
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", created=" + created +
                ", modified=" + modified +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", details='" + details + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    public static final class Builder {
        private BlogPost blogPost;

        public Builder() {
            blogPost = new BlogPost();
        }

        public static Builder aBlogPost() {
            return new Builder();
        }

        public Builder id(Long id) {
            blogPost.setId(id);
            return this;
        }

        public Builder deleted(boolean deleted) {
            blogPost.setDeleted(deleted);
            return this;
        }

        public Builder created(Timestamp created) {
            blogPost.setCreated(created);
            return this;
        }

        public Builder modified(Timestamp modified) {
            blogPost.setModified(modified);
            return this;
        }

        public Builder name(String name) {
            blogPost.setName(name);
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            blogPost.setImageUrl(imageUrl);
            return this;
        }

        public Builder details(String details) {
            blogPost.setDetails(details);
            return this;
        }

        public Builder videoUrl(String videoUrl) {
            blogPost.setVideoUrl(videoUrl);
            return this;
        }

        public BlogPost build() {
            return blogPost;
        }
    }
}