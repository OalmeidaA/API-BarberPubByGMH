package com.GMH.digital.BarberPub.by.GMH.dto;

public class BusinessSocialsDTO {
    private String facebookUrl;
    private String instagramUrl;
    private String twitterUrl;
    private String youtubeUrl;
    private String websiteUrl;

    public BusinessSocialsDTO() {
    }

    public BusinessSocialsDTO(String facebookUrl, String instagramUrl, String twitterUrl, String youtubeUrl, String websiteUrl) {
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
        this.twitterUrl = twitterUrl;
        this.youtubeUrl = youtubeUrl;
        this.websiteUrl = websiteUrl;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
