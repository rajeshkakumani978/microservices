package com.app.job.service.impl;


import com.app.job.entity.Company;
import com.app.job.entity.Job;
import com.app.job.repositories.CompanyRepository;
import com.app.job.service.impl.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company updateCompany = companyOptional.get();
            updateCompany.setName(company.getName());
            updateCompany.setDescription(company.getDescription());
            updateCompany.setJob(company.getJob());
            companyRepository.save(updateCompany);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Company getCompanyById(Long id) {
        return  companyRepository.findById(id).orElse(null);
    }
}
