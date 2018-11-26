package com.thtf.app.repository;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.domain.Notification;
/**
 * Spring Data JPA repository for the Notification entity.
 */
@Repository
public interface NotificationRepository extends BaseRepository<Notification> {

	/**
	 * 读取通知
	 * 
	 * @param loginName
	 * @return
	 */

	@Query("select n from Notification n inner join n.notificationRecords r where n.notifyType = 'notify' and n.notifyStatus = '发布' and n.endDate > ?1 and r.readFlag = '0' and r.userLogin = ?2 order by n.startDate")
	List<Notification> readMyNotification(ZonedDateTime now0, String loginName);

	/**
	 * 不判断时间
	 * 
	 * @param loginName
	 * @return
	 */
	@Query("select n from Notification n inner join n.notificationRecords r where n.notifyType = 'notify' and n.notifyStatus = '发布' and r.readFlag = '0' and r.userLogin = ?1 order by n.startDate")
	List<Notification> readMyNotification(String loginName);

	/**
	 * 已经读过的通知或公告
	 * 
	 * @param notifyType
	 * @param loginName
	 * @return
	 */
	@Query("select distinct n from Notification n inner join n.notificationRecords r where n.notifyStatus = '发布' and r.readFlag = '1' and n.notifyType = ?1 and r.userLogin = ?2")
	List<Notification> readProclamation(String notifyType, String loginName);

	/**
	 * 获取未读过的公告
	 * 
	 * @param now0
	 * @param nIds0
	 * @param userOrgId0
	 * @return
	 */
	@Query("select n from Notification n where n.notifyStatus = '发布' and n.endDate > :now0 and n.notifyType = 'proclamation' and n.id not in :nIds0 and (n.notifyScope is null or n.notifyScope like :userOrgId0) order by n.startDate")
	List<Notification> findProclamation(@Param("now0") ZonedDateTime now0, @Param("nIds0") List<Long> nIds0,
			@Param("userOrgId0") String userOrgId0);

	/**
	 * 不判断时间
	 * 
	 * @param now0
	 * @param nIds0
	 * @param userOrgId0
	 * @return
	 */
	@Query("select n from Notification n where n.notifyStatus = '发布' and n.notifyType = 'proclamation' and n.id not in :nIds0 and (n.notifyScope is null or n.notifyScope like :userOrgId0) order by n.startDate")
	List<Notification> findProclamation(@Param("nIds0") List<Long> nIds0, @Param("userOrgId0") String userOrgId0);

	Page<Notification> findByIdIn(Pageable pageable, List<Long> rids);

	/**
	 * 获取通告id
	 * 
	 * @param loginName
	 * @return
	 */
	@Query(value = "SELECT r.notification_id FROM t_notification_record r, t_notification t WHERE r.notification_id = t.id AND t.notify_status = '发布' AND t.notify_type = ?1 AND r.read_flag = ?2 AND r.user_login = ?3", nativeQuery = true)
	List<BigInteger> getNotificationId(String notifyType, String notifyStatus, String loginName);
	
	/**
	 * 获取已读通告id
	 * 
	 * @param loginName
	 * @return
	 */
	@Query(value = "SELECT r.notification_id FROM t_notification_record r, t_notification t WHERE r.notification_id = t.id AND t.notify_status = '发布' AND r.read_flag = ?1 AND r.user_login = ?2", nativeQuery = true)
	List<BigInteger> getNotificationId(String notifyStatus, String loginName);
	
//	@Modifying
//	int deleteByIdAndNotifyStatus(Long id, String status);
	
	@Modifying
	@Query(value = "DELETE FROM t_notification WHERE id = ?1 AND notify_status = ?2", nativeQuery = true)
	int deleteNotification(Long id, String status);
}
