package com.app.jobms.clients;

import com.app.jobms.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE", url="${company-service.url}")
public interface CompanyClient {

    @GetMapping("/companies/{companyId}")
    Company getCompany(@PathVariable("companyId") Long companyId);

}
