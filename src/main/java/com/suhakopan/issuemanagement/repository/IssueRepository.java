package com.suhakopan.issuemanagement.repository;

import com.suhakopan.issuemanagement.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,Long> {
    
}
