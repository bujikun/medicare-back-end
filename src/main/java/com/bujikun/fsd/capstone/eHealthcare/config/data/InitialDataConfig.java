package com.bujikun.fsd.capstone.eHealthcare.config.data;

import com.bujikun.fsd.capstone.eHealthcare.entity.*;
import com.bujikun.fsd.capstone.eHealthcare.repository.*;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration(proxyBeanMethods = false)
public class InitialDataConfig {
    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository,
                                               UserRepository userRepository,
                                               PermissionRepository permissionRepository,
                                               CategoryRepository categoryRepository,
                                               SellerRepository sellerRepository,
                                               DateUtil dateUtil,
                                               PasswordEncoder passwordEncoder
    ) {
        return args -> {
            try {

                userRepository.deleteAll();
                permissionRepository.deleteAll();
                productRepository.deleteAll();
                categoryRepository.deleteAll();
                sellerRepository.deleteAll();

                var c1 = Category.builder()
                        .name("Syringes")
                        .createdOn(dateUtil.now())
                        .deleted(false)
                        .build();
                var c2 = Category.builder()
                        .name("Gloves")
                        .createdOn(dateUtil.now())
                        .deleted(false)
                        .build();
                var c3 = Category.builder()
                        .name("Toothbrushes")
                        .deleted(false)
                        .createdOn(dateUtil.now())
                        .build();
                var c4 = Category.builder()
                        .name("Medicines")
                        .deleted(false)
                        .createdOn(dateUtil.now())
                        .build();
                categoryRepository.saveAll(Set.of(c1,c2,c3,c4));

                //for(int i=0;i<40;i++) {

                var s1 = Seller.builder().name("100mg").createdOn(dateUtil.now()).build();
                var s2 = Seller.builder().name("Johnson& Johnson").createdOn(dateUtil.now()).build();

                sellerRepository.saveAll(Set.of(s1,s2));

                var f1 = Product.builder()
                            .price(new BigDecimal(20.55d))
                            .name("SYRINGE INTRALIGAMENTAL 1.8ML EURO THREAD")
                            .deleted(false)
                            .createdOn(dateUtil.now())
                            .description("SYRINGES FOR INTRALIGAMENTAL ANAESTHESIA\n" +
                                    "(4962 - 4963 - 4964)\n" +
                                    "The intraligament anaesthesia technique is used for the treatment of " +
                                    "individual teeth by means of an injection between the alveolar crest and the " +
                                    "tooth root. With this technique the needle is inserted through the gingival sulcus " +
                                    "into the periodontal space along the mesial or distal surface of the tooth: the " +
                                    "anaesthetic solution must be injected slowly keeping a constant and moderate pressure " +
                                    "to facilitate the distribution of the liquid. Contraindicated in presence of deep " +
                                    "periodontal pockets and gingival acute infection. ")
                            .imageUrl("https://www.inspiredtaste.net/wp-content/uploads/2019/03/" +
                                    "Spaghetti-with-Meat-Sauce-Recipe-1-1200.jpg")
                        .productSellers(new HashSet<>())

                            .build();
                    f1.setProductCategories(new HashSet<>());
                    f1.linkCategory(c1);
                    f1.linkSeller(s1);
                    var f2 = Product.builder()
                            .price(new BigDecimal(10d))
                            .deleted(false)
                            .name("PROTEC LATEX POWDER FREE SMALL GLOVES")
                            .createdOn(dateUtil.now())
                            .description("Powder Free Latex Gloves - Latex Exam Gloves\n" +
                                    "\n" +
                                    "Price is for a Carton of 10 boxes of 100\n" +
                                    "\n" +
                                    "    designed for use in medical areas where protection from blood borne infection is essential.\n" +
                                    "    Ambidextrous fitting.\n" +
                                    "    Up to five sizes available.\n" +
                                    "    High production standards to TGA and FDA requirements from an ISO audited supplier.\n" +
                                    "    No Powdered Gloves for people with allergies to conventional glove powders.\n" +
                                    "    Each of the three types are coloured coded for easy identification.\n" +
                                    "    Perspex Gloves box dispensers available\n")
                            .imageUrl("https://www.allrecipes.com/thmb/5JVfA7MxfTUPfRerQMdF-nGKsLY=/1500x0/filters:" +
                                    "no_upscale():max_bytes(150000):strip_icc()/25473-the-perfect-basic-" +
                                    "burger-DDMFS-4x3-56eaba3833fd4a26a82755bcd0be0c54.jpg")
                            .productSellers(new HashSet<>())
                            .build();
                    f2.setProductCategories(new HashSet<>());
                    f2.linkCategory(c2);
                    f2.linkSeller(s2);
                    var f3 = Product.builder()
                            .price(new BigDecimal(15.99d))
                            .deleted(false)
                            .name("TEPE NOVA SOFT TOOTHBRUSH 80 PACK")
                            .createdOn(dateUtil.now())
                            .description("Nova has a tapered brush head with an active tip for increased access. It is " +
                                    "specially efficient around the posterior teeth and other difficult to reach areas. " +
                                    "The end-rounded filaments ensure gentle cleaning. Non-slip handle with thumb pad. " +
                                    "Neck can be angled without heating. Blue tip: medium, yellow tip: soft, pink tip: x-soft. ")
                            .imageUrl("https://static.onecms.io/wp-content/uploads/sites/43/2023/01/30/70935-taqueria-style-" +
                                    "tacos-mfs-3x2-35.jpg")
                            .productSellers(new HashSet<>())

                            .build();

                    f3.setProductCategories(new HashSet<>());
                    f3.linkCategory(c3);
                    f3.linkSeller(s1);

                    var f5 = Product.builder()
                            .price(new BigDecimal(2.77d))
                            .name("Cura-Heat Back and Shoulder Pain 3 Heat Packs")
                            .imageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Biscuits_in_Ghana.jpg/220px" +
                                    "-Biscuits_in_Ghana.jpg")
                            .createdOn(dateUtil.now())
                            .description("Cura-Heat Back & Shoulder Pain 3 Heat Packs is a heat patch which gives 12 hour " +
                                    "relief from muscles and joint aches and pains. It works by increasing the blood " +
                                    "circulation in the area which in turn reduces the stiffness and relaxes the sore " +
                                    "muscles. Cura-Heat Back & Shoulder Pain can be bought from OxfordPharmacyOnline.")
                                                   .productSellers(new HashSet<>())
                            .productSellers(new HashSet<>())
                        .deleted(false)
                            .build();

                    f5.setProductCategories(new HashSet<>());
                    f5.linkCategory(c4);
                    f5.linkSeller(s2);
                    var f6 = Product.builder()
                            .price(new BigDecimal(7.50d))
                            .name("Dermovate (Clobetasol Propionate) 0.05% Cream")
                            .imageUrl("https://www.sidechef.com/recipe/81372375-5170-4e31-884d-b5f7070fb924.jpg?d=1408x1120")
                            .createdOn(dateUtil.now())
                            .description("If you suffer from chronic skin flare ups and other corticosteroid creams have not " +
                                    "worked, Dermovate could help. \n" +
                                    "\n" +
                                    "With OxfordOnlinePharmacy it is easier than ever to get your Dermovate prescription " +
                                    "online: no waiting times, no hassle.\n" +
                                    "\n" +
                                    "Dermovate is a strong topical steroid which is effective in the treatment of severe " +
                                    "skin conditions such as eczema and psoriasis.\n" +
                                    "\n" +
                                    "Dermovate is a cream that contains the active ingredient Clobetasol Propionate, which " +
                                    "is a topical corticosteroid (topical means that it is applied direct to affected area," +
                                    " and a corticosteroid reduces inflammation). Dermovate is stronger than other " +
                                    "corticosteroids which is only available on prescription. Dermovate is only to be used " +
                                    "when other steroid creams have not been effective.")
                            .deleted(false)
                            .productSellers(new HashSet<>())

                            .build();

                    f6.setProductCategories(new HashSet<>());
                    f6.linkCategory(c4);
                    f6.linkSeller(s1);

                    productRepository.saveAll(List.of(f1, f2, f3, f5, f6));
                //}

                var perm1 = Permission.builder()
                        .name("USER")
                        .createdOn(dateUtil.now())
                        .deleted(false)
                        .build();
                var perm2 = Permission.builder()
                        .name("ADMIN")
                        .deleted(false)
                        .createdOn(dateUtil.now())
                        .build();

                permissionRepository.saveAll(Set.of(perm1,perm2));

                var admin = User.builder()
                        .username("admin")
                        .firstname("admin")
                        .lastname("admin")
                        .email("admin@admin.com")
                        .accountNumber(UUID.randomUUID().toString().toUpperCase())
                        .password(passwordEncoder.encode("password"))
                        .isEnabled(true)
                        .isAccountExpired(false)
                        .isAccountLocked(false)
                        .isCredentialsExpired(false)
                        .createdOn(dateUtil.now())
                        .userPermissions(new HashSet<>())
                        .deleted(false)
                        .build();

                admin.linkPermissions(Set.of(perm2));

                var manager = User.builder()
                        .username("user")
                        .firstname("user")
                        .lastname("user")
                        .email("user@user.com")
                        .accountNumber(UUID.randomUUID().toString().toUpperCase())
                        .password(passwordEncoder.encode("password"))
                        .isEnabled(true)
                        .isAccountExpired(false)
                        .isAccountLocked(false)
                        .isCredentialsExpired(false)
                        .createdOn(dateUtil.now())
                        .userPermissions(new HashSet<>())
                        .deleted(false)
                        .build();
                manager.linkPermissions(Set.of(perm1));
                userRepository.save(admin);
                userRepository.save(manager);

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
