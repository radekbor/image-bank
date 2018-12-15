package org.radekbor.domains.images;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageDetailsRepository extends JpaRepository<ImageDetails, Long> {
    Page<ImageDetails> findAllByUserId(long id, Pageable pageable);

    @Query("select details from ImageDetails details where details.userId = ?1 and details.name like %?2%")
    Page<ImageDetails> findAllByUserIdAndName(long id, String name, Pageable pageable);

}
