package org.radekbor.domains.images;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDetailsRepository extends JpaRepository<ImageDetails, Long> {
    Page<ImageDetails> findAllByUserId(long id, Pageable pageable);
}
