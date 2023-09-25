package com.nhutin.electric_project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.repository.brandsRepository;
import com.nhutin.electric_project.service.BrandsService;

@Service
public class BrandServiceImpl implements BrandsService {
    @Autowired
    brandsRepository brandsRepository;

}
