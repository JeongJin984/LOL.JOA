package com.loljoa.DBInit.db.repository.postComment;

import com.loljoa.DBInit.db.entity.BettingChoice;
import com.loljoa.DBInit.db.entity.PostComment;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends CommonRepository<PostComment, Long>, PostCommentRepositoryCustom {
}
