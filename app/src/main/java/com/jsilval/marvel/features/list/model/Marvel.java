package com.jsilval.marvel.features.list.model;

import javax.annotation.Nullable;

public final class Marvel {

    private final String title;

    private final String detailUrl;

    private final String format;

    private final String thumbnail;

    private final String description;

    public Marvel(@Nullable String title, @Nullable String detailUrl, @Nullable
            String format, @Nullable String thumbnail, @Nullable String description) {
        this.title = title;
        this.detailUrl = detailUrl;
        this.format = format;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public String getFormat() {
        return format;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }
}
