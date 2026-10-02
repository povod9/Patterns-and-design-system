package org.example;

import java.util.List;

public class ProductSyncService {

    private ExternalService externalService;
    private CategoryRepository categoryRepository;

    public ProductSyncService(final ExternalService externalService,
                              final CategoryRepository categoryRepository) {
        this.externalService = externalService;
        this.categoryRepository = categoryRepository;
    }

    public void synchronize(List<Product> products) {
        for (final var product : products) {
            System.out.println("-".repeat(10));
            System.out.println("Syncing product " + product);
            final var category = categoryRepository.findById(product.categoryId())
                    .orElse(externalService.getCategory(product.categoryId()));
            try {
                externalService.syncProduct(product, category);
                product.markAsSynced();
            } catch (Exception exception) {
                System.out.println("Something went wrong");
            }
            System.out.printf("Product sync status %s = %b\n", product.name(), product.isSynced());
        }
    }
}
