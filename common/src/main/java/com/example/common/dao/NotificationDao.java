package com.example.common.dao;

import com.example.common.entity.Notification;
import com.example.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao extends JpaRepository<Notification,Integer> {
    @Query(value ="SELECT  count (id) FROM  cule_notification n WHERE  n.touser_id= ?1 AND n.is_read =0",nativeQuery = true)
    long getNotificationCount(Integer id);
    List<Notification> getByTouserOrOrderByInitTimeDesc(User user);
    @Modifying
    @Query("UPDATE  Notification  n SET  n.isRead = true WHERE  n.touser =?1")
    void updateBuIsRead(User user);
}
