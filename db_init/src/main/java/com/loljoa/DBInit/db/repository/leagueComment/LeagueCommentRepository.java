package com.loljoa.DBInit.db.repository.leagueComment;

import com.loljoa.DBInit.db.entity.LeagueComment;
import com.loljoa.DBInit.db.repository.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueCommentRepository extends CommonRepository<LeagueComment, Long>, LeagueCommentRepositoryCustom {
}
