package com.bujikun.fsd.capstone.eHealthcare.config.data;

import com.bujikun.fsd.capstone.eHealthcare.entity.Permission;
import com.bujikun.fsd.capstone.eHealthcare.entity.Product;
import com.bujikun.fsd.capstone.eHealthcare.entity.User;
import com.bujikun.fsd.capstone.eHealthcare.repository.PermissionRepository;
import com.bujikun.fsd.capstone.eHealthcare.repository.ProductRepository;
import com.bujikun.fsd.capstone.eHealthcare.repository.UserRepository;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration(proxyBeanMethods = false)
public class InitialDataConfig {
    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository foodRepository,
                                               UserRepository userRepository,
                                               PermissionRepository permissionRepository,
                                               DateUtil dateUtil,
                                               PasswordEncoder passwordEncoder
    ) {
        return args -> {
            foodRepository.deleteAll();
            try {
                var f1 = Product.builder()
                        .price(new BigDecimal(20.55d))
                        .name("Spaghetti")
                        .createdOn(dateUtil.now())
                        .description("Some nice spaghetti")
                        .imageUrl("https://www.inspiredtaste.net/wp-content/uploads/2019/03/" +
                                "Spaghetti-with-Meat-Sauce-Recipe-1-1200.jpg")
                        .build();
                var f2 = Product.builder()
                        .price(new BigDecimal(10d))
                        .name("Burger")
                        .createdOn(dateUtil.now())
                        .description("Some nice burger")
                        .imageUrl("https://www.allrecipes.com/thmb/5JVfA7MxfTUPfRerQMdF-nGKsLY=/1500x0/filters:" +
                                "no_upscale():max_bytes(150000):strip_icc()/25473-the-perfect-basic-" +
                                "burger-DDMFS-4x3-56eaba3833fd4a26a82755bcd0be0c54.jpg")
                        .build();

                var f3 = Product.builder()
                        .price(new BigDecimal(5.99d))
                        .name("French Fries")
                        .createdOn(dateUtil.now())
                        .description("Some nice fries")
                        .imageUrl("https://www.seriouseats.com/thmb/Il7mv9ZSDh7n0cZz3t3V-28ImkQ=/1500x0/filters:no_upscale()" +
                                ":max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration_" +
                                "_serious_eats__seriouseats.com__2018__04__20180309-french-fries-vicky-wasik-" +
                                "15-5a9844742c2446c7a7be9fbd41b6e27d.jpg")
                        .build();
                var f4 = Product.builder()
                        .price(new BigDecimal(15.99d))
                        .name("Tacos")
                        .createdOn(dateUtil.now())
                        .description("Some nice tacos")
                        .imageUrl("https://static.onecms.io/wp-content/uploads/sites/43/2023/01/30/70935-taqueria-style-" +
                                "tacos-mfs-3x2-35.jpg")
                        .build();
                var f5 = Product.builder()
                        .price(new BigDecimal(2.77d))
                        .name("Biscuits")
                        .imageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Biscuits_in_Ghana.jpg/220px" +
                                "-Biscuits_in_Ghana.jpg")
                        .createdOn(dateUtil.now())
                        .description("Some nice biscuits")
                        .build();

                var f6 = Product.builder()
                        .price(new BigDecimal(7.50d))
                        .name("French Pasta")
                        .imageUrl("https://www.sidechef.com/recipe/81372375-5170-4e31-884d-b5f7070fb924.jpg?d=1408x1120")
                        .createdOn(dateUtil.now())
                        .description("Some nice pasta")
                        .build();
                foodRepository.saveAll(List.of(f1, f2, f3, f4, f5, f6));

                var perm8 = Permission.builder()
                        .name("user:read:all")
                        .createdOn(dateUtil.now())
                        .build();
                var perm6 = Permission.builder()
                        .name("product:create:one")
                        .createdOn(dateUtil.now())
                        .build();

                var perm5 = Permission.builder()
                        .name("product:read:one")
                        .createdOn(dateUtil.now())
                        .build();

                var perm7 = Permission.builder()
                        .name("product:delete:one")
                        .createdOn(dateUtil.now())
                        .build();

                var perm4 = Permission.builder()
                        .name("order:read:all")
                        .createdOn(dateUtil.now())
                        .build();

                var perm3 = Permission.builder()
                        .name("user:change-password")
                        .createdOn(dateUtil.now())
                        .build();

                permissionRepository.saveAll(Set.of(perm3,perm4,perm5,perm6,perm7,perm8));

                var admin = User.builder()
                        .username("admin")
                        .firstname("admin")
                        .lastname("admin")
                        .email("admin@admin.com")
                        .accountNumber(UUID.randomUUID().toString())
                        .password(passwordEncoder.encode("password"))
                        .isEnabled(true)
                        .isAccountExpired(false)
                        .isAccountLocked(false)
                        .isCredentialsExpired(false)
                        .createdOn(dateUtil.now())
                        .userPermissions(new HashSet<>())
                        .build();

                admin.linkPermissions(Set.of(perm3,perm4,perm5,perm6,perm7,perm8));

                var manager = User.builder()
                        .username("user")
                        .firstname("user")
                        .lastname("user")
                        .email("user@user.com")
                        .accountNumber(UUID.randomUUID().toString())
                        .password(passwordEncoder.encode("password"))
                        .isEnabled(true)
                        .isAccountExpired(false)
                        .isAccountLocked(false)
                        .isCredentialsExpired(false)
                        .createdOn(dateUtil.now())
                        .userPermissions(new HashSet<>())
                        .build();
                manager.linkPermissions(Set.of(perm3,perm4));
                userRepository.save(admin);
                userRepository.save(manager);

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
