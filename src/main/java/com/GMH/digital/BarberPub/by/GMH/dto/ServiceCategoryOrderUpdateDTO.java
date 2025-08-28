package com.GMH.digital.BarberPub.by.GMH.dto;

import java.util.List;

public class ServiceCategoryOrderUpdateDTO {
    private List<CategoryOrderDTO> categories;

    public ServiceCategoryOrderUpdateDTO() {
    }

    public List<CategoryOrderDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryOrderDTO> categories) {
        this.categories = categories;
    }

    public static class CategoryOrderDTO {
        private Long id;
        private int sortOrder;

        public CategoryOrderDTO() {
        }

        public CategoryOrderDTO(Long id, int sortOrder) {
            this.id = id;
            this.sortOrder = sortOrder;
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
