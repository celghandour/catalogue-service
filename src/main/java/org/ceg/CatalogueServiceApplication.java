package org.ceg;

import org.ceg.dao.CategoryRepository;
import org.ceg.dao.ProductRepository;
import org.ceg.entities.Category;
import org.ceg.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class CatalogueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogueServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CategoryRepository categoryRepository, ProductRepository productRepository) {

        return args -> {

            categoryRepository.deleteAll();
            Stream.of("C1 Ordinateurs", "C2 Imprimantes").forEach(c -> {
                categoryRepository.save(new Category(c.split(" ")[0], c.split(" ")[1], new ArrayList<>()));
            });
            categoryRepository.findAll().forEach(System.out::println);


            productRepository.deleteAll();
            Category c1 = categoryRepository.findById("C1").get();
            Stream.of("P1", "P2", "P3", "P4").forEach(name -> {
                Product product = productRepository.save(new Product(null, name, Math.random() * 1000, c1));

                c1.getProducts().add(product);
                categoryRepository.save(c1);
            });

            Category c2 = categoryRepository.findById("C2").get();
            Stream.of("P5", "P6").forEach(name -> {
                Product product1 = productRepository.save(new Product(null, name, Math.random() * 1000, c2));
                c2.getProducts().add(product1);
                categoryRepository.save(c2);

            });

            productRepository.findAll().forEach(p -> {
                System.out.println(p.toString());
            });

        };

    }

}
