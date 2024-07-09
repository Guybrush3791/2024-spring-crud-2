package org.java.spring_crud2.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.spring_crud2.db.pojo.Review;
import org.java.spring_crud2.db.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServ {

    @Autowired
    private ReviewRepo reviewRepo;

    public List<Review> getAllReviews() {

        return reviewRepo.findAll();
    }

    public Optional<Review> getReviewById(int id) {

        return reviewRepo.findById(id);
    }

    public void save(Review review) {

        reviewRepo.save(review);
    }

    public void delete(Review review) {

        reviewRepo.delete(review);
    }
}
