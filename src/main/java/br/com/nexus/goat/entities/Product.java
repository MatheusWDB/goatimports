package br.com.nexus.goat.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private Integer stock;

    private String imgUrl;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToMany
    @JoinTable(name = "tb_product_categories", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_category"))
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_features", referencedColumnName = "id")
    private Feature features;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderProduct> products = new HashSet<>();

    @ManyToMany(mappedBy = "wishes", cascade = CascadeType.REMOVE)
    private Set<User> wishes = new HashSet<>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String size, Integer stock, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.stock = stock;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @JsonIgnore
    public Set<Category> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Long> getIdCategories() {
        Set<Long> idCategories = new HashSet<>();
        for (Category category : categories) {
            idCategories.add(category.getId());
        }
        return idCategories;
    }

    @JsonIgnore
    public Feature getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(Feature features) {
        this.features = features;
    }

    public Long getIdFeature() {
        return features.getId();
    }

    @JsonIgnore
    public Set<User> getWishes() {
        return wishes;
    }

    @JsonIgnore
    public Set<Order> getProducts() {
        Set<Order> set = new HashSet<>();
        for (OrderProduct x : products) {
            set.add(x.getOrder());
        }
        return set;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", size="
                + size + ", stock=" + stock + ", imgUrl=" + imgUrl + ", createdAt=" + createdAt + ", categories="
                + categories + ", features=" + features + "]";
    }
}
