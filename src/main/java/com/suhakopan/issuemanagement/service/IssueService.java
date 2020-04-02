package com.suhakopan.issuemanagement.service;

import com.suhakopan.issuemanagement.dto.IssueDto;
import com.suhakopan.issuemanagement.entity.Issue;
import com.suhakopan.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueDto issue);
}
