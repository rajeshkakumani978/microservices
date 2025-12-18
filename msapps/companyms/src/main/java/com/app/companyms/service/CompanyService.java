package com.app.companyms.service;



import com.app.companyms.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    boolean updateCompany(Company company, Long id);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
}
