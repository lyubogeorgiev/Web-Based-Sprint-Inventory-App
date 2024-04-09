package com.example.demo.initilizer;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initData() {
        // Check if the database is empty
        if (partRepository.count() == 0 && productRepository.count() == 0) {

            String[] names = {"Sapphire Nitro+ Radeon RX 6900 XT",
                    "Intel Core i9-12900K",
                    "ASUS ROG Strix Z690-E Gaming WiFi",
                    "Corsair Vengeance RGB Pro 32GB (2 x 16GB) DDR4 3600MHz",
                    "Samsung 980 PRO 1TB NVMe SSD"};

            double[] prices = {1199.99, 699.99, 499.99, 199.99, 179.99};
            int[] inventories = {1, 2, 2, 4, 1};
            long[] ids = {1, 2, 3, 4, 5};

            Set<Part> inhousePartsSet = new HashSet<>();

            for (int i = 0; i < 5; i++) {
                Part part = new InhousePart();
                part.setName(names[i]);
                part.setPrice(prices[i]);
                part.setInv(inventories[i]);
                part.setId(ids[i]);

                inhousePartsSet.add(part);

            }

            partRepository.saveAll(inhousePartsSet);

            String[] productNames = {"Gaming Powerhouse PC",
                    "Content Creator Workstation",
                    "Enthusiast Gaming Rig",
                    "Professional Productivity Workstation",
                    "Budget-Friendly Gaming Setup"};
            double[] productPrice = {2499.99, 2199.99, 1879.89, 3000.99, 1199.99};
            int[] productInventory = {1, 2, 1, 3, 2};
            long[] productIds = {1, 2, 3, 4, 5};

            Set<Product> productSet = new HashSet<>();

            for (int i = 0; i < 5; i++) {
                Product product = new Product(productNames[i], productPrice[i], productInventory[i]);
                product.setId(productIds[i]);

                productSet.add(product);

                System.out.println(productSet.size());
            }

            productRepository.saveAll(productSet);
        }
    }
}

