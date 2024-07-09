package org.java.spring_crud2.db.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recensione")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private int rating; // 0-5

    @Column(columnDefinition = "TEXT", nullable = true)
    private String comment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public Review() {
    }

    public Review(int rating, String comment, Product product)
            throws Exception {

        setRating(rating);
        setComment(comment);
        setProduct(product);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws Exception {

        if (rating < 0 || rating > 5)
            throw new Exception("Rating must be between 0 and 5!");

        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) throws Exception {

        if (comment == null || comment.isEmpty())
            throw new Exception("Comment cannot be null or empty!");

        this.comment = comment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {

        return "Review{" +
                "\n\tid=" + id +
                ",\n\trating=" + rating +
                ",\n\tcomment='" + comment + '\'' +
                "\n}";
    }
}
