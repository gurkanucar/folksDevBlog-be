package com.gucardev.folksdevblogbe.controller.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

public class PostDto {

    private Long id;

    private Timestamp created;

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

    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto that = (PostDto) o;
        return deleted == that.deleted && Objects.equals(id, that.id) && Objects.equals(created, that.created) && Objects.equals(modified, that.modified) && Objects.equals(name, that.name) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(details, that.details) && Objects.equals(videoUrl, that.videoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, modified, name, imageUrl, details, videoUrl, deleted);
    }

    @Override
    public String toString() {
        return "BlogPostDto{" +
                "id=" + id +
                ", created=" + created +
                ", modified=" + modified +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", details='" + details + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", deleted=" + deleted +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public static final class Builder {
        private PostDto postDto;

        public Builder() {
            postDto = new PostDto();
        }

        public static Builder aBlogPostDto() {
            return new Builder();
        }

        public Builder id(Long id) {
            postDto.setId(id);
            return this;
        }

        public Builder created(Timestamp created) {
            postDto.setCreated(created);
            return this;
        }

        public Builder modified(Timestamp modified) {
            postDto.setModified(modified);
            return this;
        }

        public Builder name(String name) {
            postDto.setName(name);
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            postDto.setImageUrl(imageUrl);
            return this;
        }

        public Builder details(String details) {
            postDto.setDetails(details);
            return this;
        }

        public Builder videoUrl(String videoUrl) {
            postDto.setVideoUrl(videoUrl);
            return this;
        }

        public Builder deleted(boolean deleted) {
            postDto.setDeleted(deleted);
            return this;
        }

        public PostDto build() {
            return postDto;
        }
    }
}


