package com.thtf.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.domain.Attachment;



/**
 * Spring Data JPA repository for the Attachment entity.
 */
@Repository
public interface AttachmentRepository extends BaseRepository<Attachment>  {
	
	List<Attachment> findByIdIn(List<Long> id);
	
	void deleteByIdAndUploader(Long id, String uploader);
	/**
	 * 更新通告附件
	 * 
	 * @param _ids0
	 * @param _fileType0
	 * @param _relatedId0
	 * @return
	 */
	@Modifying
	@Query(value = "update t_attachment set file_type = (:_fileType0),related_id = (:_relatedId0) where id in (:_ids0)", nativeQuery = true)
	int updateByIdIn(@Param("_ids0") List<Long> ids0, @Param("_fileType0") String fileType0, @Param("_relatedId0") Long relatedId0);
	
	/**
	 * 更新通告附件size
	 * 
	 * @param _ids0
	 * @param _fileSize0
	 * @param _fileType0
	 * @param _relatedId0
	 * @return
	 */
	@Modifying
	@Query(value = "update t_attachment set file_size = (:_fileSize0) where file_type = (:_fileType0) and related_id = (:_relatedId0) and id not in (:_ids0)", nativeQuery = true)
	int updateByIdNotIn(@Param("_ids0") List<Long> ids0, @Param("_fileType0") String fileType0, @Param("_relatedId0") Long relatedId0, @Param("_fileSize0") int fileSize0);
	
	/**
	 * 查询给定通告id的附件记录(fileSize!=0)
	 * 
	 * @param _fileSize0
	 * @param _fileType0
	 * @param _relatedId0
	 * @return
	 */
	@Modifying
	@Query(value = "select * from t_attachment where related_id = (:_relatedId0) and file_type = (:_fileType0) and file_size <> (:_fileSize0)", nativeQuery = true)
	List<Attachment> findByRelatedIdAndFileTypeAndFileSize(@Param("_relatedId0") Long relatedId0, @Param("_fileType0") String fileType0, @Param("_fileSize0") int fileSize0);
	
	/**
	 * 通告移除所有附件时，更新附件size=0
	 * 
	 * @param _fileSize0
	 * @param _fileType0
	 * @param _relatedId0
	 * @return
	 */
	@Modifying
	@Query(value = "update t_attachment set file_size = (:_fileSize0) where related_id = (:_relatedId0) and file_type = (:_fileType0)", nativeQuery = true)
	int updateByRelatedId(@Param("_fileType0") String fileType0, @Param("_relatedId0") Long relatedId0, @Param("_fileSize0") int fileSize0);
}
