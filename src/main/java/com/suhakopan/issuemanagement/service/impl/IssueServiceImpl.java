package com.suhakopan.issuemanagement.service.impl;

import com.suhakopan.issuemanagement.dto.IssueDto;
import com.suhakopan.issuemanagement.entity.Issue;
import com.suhakopan.issuemanagement.repository.IssueRepository;
import com.suhakopan.issuemanagement.service.IssueService;
import com.suhakopan.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper){
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public IssueDto save(IssueDto issue) {
        if(issue.getDate()==null)
            throw new IllegalArgumentException("Issue date should be fill");

        Issue issueDb = modelMapper.map(issue, Issue.class);

        issueDb =  issueRepository.save(issueDb);
        return modelMapper.map(issueDb,IssueDto.class);
    }

    @Override
    public IssueDto getById(Long id) {
        Issue i = issueRepository.getOne(id);
        return modelMapper.map(i,IssueDto.class);
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage page = new TPage<IssueDto>();
        IssueDto[]  dtos = modelMapper.map(data.getContent(),IssueDto[].class);
        page.setStat(data, Arrays.asList(dtos));
        return page;
    }

    @Override
    public Boolean delete(Long id) {
        issueRepository.deleteById(id);
        return true;
    }

    @Override
    public IssueDto update(Long id, IssueDto issue){
        Issue issueDB = issueRepository.getOne(id);
        if(issueDB==null)
            throw new IllegalArgumentException("Issue Does Not Exist ID: " + id);

        issueDB.setDescription(issue.getDescription());
        issueDB.setDate(issue.getDate());
        issueDB.setDetails(issue.getDetails());
        issueDB.setIssueStatus(issue.getIssueStatus());

        issueRepository.save(issueDB);
        return modelMapper.map(issueDB,IssueDto.class);
    }
}
