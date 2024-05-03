package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Reviews;

public interface ReviewsService extends Service<Reviews> {
    long getAllReviewsCount(long specialistId);

    long getAllReviewsCountAcceptedTrue(long specialistId);

    long getAllReviewsCountAcceptedFalse(long specialistId);

}
