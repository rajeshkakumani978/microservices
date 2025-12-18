package com.app.companyms.service.impl;


import com.app.companyms.entity.Company;
import com.app.companyms.repository.CompanyRepository;
import com.app.companyms.service.CompanyService;
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
