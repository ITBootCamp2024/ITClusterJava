package com.ua.itclusterjava2024.repository;

import com.ua.itclusterjava2024.entity.DisciplineBlocks;
import com.ua.itclusterjava2024.entity.DisciplineGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineGroupsRepository extends JpaRepository<DisciplineGroups, Long>{
    @Query("SELECT dg FROM DisciplineGroups dg WHERE dg.block_id = :blockId")
    List<DisciplineGroups> findByBlockId(@Param("blockId") DisciplineBlocks block);

}
