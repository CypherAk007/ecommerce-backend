package com.ecommerce.ecommercebackend.repositories;

import com.ecommerce.ecommercebackend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Optional<Notification> findByProductId(Long productId);
    List<Notification> findAllByProductId(Long productId);

    @Query("SELECT n FROM Notification n JOIN n.subscribedUsers su WHERE su.id = :customerId")
    List<Notification> findBySubscribedUserId(@Param("customerId") Long customerId);

    @Query("SELECT CASE WHEN COUNT(n) > 0 THEN true ELSE false END " +
            "FROM Notification n " +
            "JOIN n.subscribedUsers su " +
            "WHERE su.id = :customerId AND n.product.id = :productId")
    boolean existsByCustomerIdAndProductId(@Param("customerId") Long customerId,
                                           @Param("productId") Long productId);
}
