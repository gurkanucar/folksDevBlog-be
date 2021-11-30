package com.gucardev.folksdevblogbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "blogpost")
@Where(clause = "deleted = false")
public class Post implements Serializable {

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
        Post post = (Post) o;
        return id.equals(post.id) && deleted == post.deleted && Objects.equals(created, post.created) && Objects.equals(modified, post.modified) && Objects.equals(name, post.name) && Objects.equals(imageUrl, post.imageUrl) && Objects.equals(details, post.details) && Objects.equals(videoUrl, post.videoUrl);
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
        private Post post;

        public Builder() {
            post = new Post();
        }

        public static Builder aBlogPost() {
            return new Builder();
        }

        public Builder id(Long id) {
            post.setId(id);
            return this;
        }

        public Builder deleted(boolean deleted) {
            post.setDeleted(deleted);
            return this;
        }

        public Builder created(Timestamp created) {
            post.setCreated(created);
            return this;
        }

        public Builder modified(Timestamp modified) {
            post.setModified(modified);
            return this;
        }

        public Builder name(String name) {
            post.setName(name);
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            post.setImageUrl(imageUrl);
            return this;
        }

        public Builder details(String details) {
            post.setDetails(details);
            return this;
        }

        public Builder videoUrl(String videoUrl) {
            post.setVideoUrl(videoUrl);
            return this;
        }

        public Post build() {
            return post;
        }
    }
}