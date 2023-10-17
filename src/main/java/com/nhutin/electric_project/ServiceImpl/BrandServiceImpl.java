package com.nhutin.electric_project.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhutin.electric_project.repository.brandsRepository;
import com.nhutin.electric_project.service.BrandsService;

@Service
public class BrandServiceImpl implements BrandsService {
    @Autowired
    brandsRepository brandsRepository;

    public List<Object[]> getRevenueByMonth(int brandId) {
        return brandsRepository.getRevenueByMonth(brandId);
    }

}
