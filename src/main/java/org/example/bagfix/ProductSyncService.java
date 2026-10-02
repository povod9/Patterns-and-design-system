package org.example.bagfix;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductSyncService {

    private ExternalService externalService;
    private CategoryRepository categoryRepository;

    public ProductSyncService(final ExternalService externalService,
                              final CategoryRepository categoryRepository) {
        this.externalService = externalService;
        this.categoryRepository = categoryRepository;
    }

    public void synchronize(List<Product> products) {
        final var categories = categoryRepository.findAll().stream()
                .collect(Collectors.toMap(Category::id, c -> c));
        for (final var product : products) {
            System.out.println("-".repeat(10));
            System.out.println("Syncing product " + product);
            var category = Optional.ofNullable(categories.get(product.categoryId()))
                    .orElseGet(() -> externalService.getCategory(product.categoryId()));
            try {
                externalService.syncProduct(product, category);
                product.markAsSynced();
            }catch (ProductAlreadySyncedException exception) {
                System.out.println(exception.getMessage());
                product.markAsSynced();
            }catch (RuntimeException exception){
                System.out.println(exception.getMessage());
            }

            System.out.printf("Product sync status %s = %b\n", product.name(), product.isSynced());
        }
    }
}
