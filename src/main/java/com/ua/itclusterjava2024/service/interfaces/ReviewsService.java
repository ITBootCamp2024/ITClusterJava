package com.ua.itclusterjava2024.service.interfaces;

import com.ua.itclusterjava2024.entity.Reviews;

public interface ReviewsService extends Service<Reviews> {
    public long getAllReviewsCount(long specialistId);
    public long getAllReviewsCountAcceptedTrue(long specialistId);
}
