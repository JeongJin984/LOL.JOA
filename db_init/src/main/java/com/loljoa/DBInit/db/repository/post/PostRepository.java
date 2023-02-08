package com.loljoa.DBInit.db.repository.post;

import com.loljoa.DBInit.db.entity.BettingChoice;
import com.loljoa.DBInit.db.entity.Post;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CommonRepository<Post, Long>, PostRepositoryCustom {
}
