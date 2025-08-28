package com.GMH.digital.BarberPub.by.GMH.dto;

import java.util.List;

public class UpdateOrderDTO {

    private List<ServiceOrderDTO> services;
    private List<CategoryOrderDTO> categories;

    public UpdateOrderDTO() {
    }

    public List<ServiceOrderDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceOrderDTO> services) {
        this.services = services;
    }

    public List<CategoryOrderDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryOrderDTO> categories) {
        this.categories = categories;
    }

    public static class ServiceOrderDTO {
        private Long id;
        private int sortOrder;
        private Long categoryId;

        public ServiceOrderDTO() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }
    }

    public static class CategoryOrderDTO {
        private Long id;
        private int sortOrder;

        public CategoryOrderDTO() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }
    }
}
