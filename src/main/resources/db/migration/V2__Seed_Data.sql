INSERT INTO `categories` (`id`, `name`, `version`, `deleted`, `created_on`, `updated_on`, `created_by`,
                          `last_modified_by`)
VALUES (UNHEX('7E98551090CF4DD8B4620549B1A11A0D'), 'Gloves', NULL, CONV('0', 2, 10) + 0, '2023-08-01 00:16:08.000000',
        NULL, NULL, NULL),
       (UNHEX('8DF057C3D91949BD92ABC06F4268EE5F'), 'Toothbrushes', NULL, CONV('0', 2, 10) + 0,
        '2023-08-01 00:16:08.000000', NULL, NULL, NULL),
       (UNHEX('C6226BC750B341A7A2C1D0BB3FB8E3B3'), 'Medicines', NULL, CONV('0', 2, 10) + 0,
        '2023-08-01 00:16:08.000000', NULL, NULL, NULL),
       (UNHEX('E950EEB86746488CA8F3CF519D08FB6D'), 'Syringes', NULL, CONV('0', 2, 10) + 0, '2023-08-01 00:16:08.000000',

       NULL, NULL, NULL);


INSERT INTO `permissions` (`id`, `name`, `deleted`, `version`, `created_on`, `updated_on`, `created_by`,
                           `last_modified_by`)
VALUES (UNHEX('0A1105A198FB4428894278D33AB2F634'), 'USER', CONV('0', 2, 10) + 0, NULL, '2023-08-01 00:16:08.000000',
        NULL, NULL, NULL),
       (UNHEX('5967FBC7766D43C0A3FAD1C9FA544381'), 'ADMIN', CONV('0', 2, 10) + 0, NULL, '2023-08-01 00:16:08.000000',
        NULL, NULL, NULL);


INSERT INTO `products` (`id`, `name`, `price`, `img_url`, `description`, `deleted`, `created_on`, `updated_on`,
                        `version`, `created_by`, `last_modified_by`)
VALUES (UNHEX('0D1651A24BEE41528FB8C9E57D0C7878'), 'SYRINGE INTRALIGAMENTAL 1.8ML EURO THREAD', 20.55,
        'https://www.inspiredtaste.net/wp-content/uploads/2019/03/Spaghetti-with-Meat-Sauce-Recipe-1-1200.jpg',
        'SYRINGES FOR INTRALIGAMENTAL ANAESTHESIA\n(4962 - 4963 - 4964)\nThe intraligament anaesthesia technique is used for the treatment of individual teeth by means of an injection between the alveolar crest and the tooth root. With this technique the needle is inserted through the gingival sulcus into the periodontal space along the mesial or distal surface of the tooth: the anaesthetic solution must be injected slowly keeping a constant and moderate pressure to facilitate the distribution of the liquid. Contraindicated in presence of deep periodontal pockets and gingival acute infection. ',
        CONV('0', 2, 10) + 0, '2023-08-01 00:16:08.000000', NULL, NULL, NULL, NULL),
       (UNHEX('1829FF024BE34870A4155349E36399F1'), 'PROTEC LATEX POWDER FREE SMALL GLOVES', 10.00,
        'https://www.allrecipes.com/thmb/5JVfA7MxfTUPfRerQMdF-nGKsLY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/25473-the-perfect-basic-burger-DDMFS-4x3-56eaba3833fd4a26a82755bcd0be0c54.jpg',
        'Powder Free Latex Gloves - Latex Exam Gloves\n\nPrice is for a Carton of 10 boxes of 100\n\n    designed for use in medical areas where protection from blood borne infection is essential.\n    Ambidextrous fitting.\n    Up to five sizes available.\n    High production standards to TGA and FDA requirements from an ISO audited supplier.\n    No Powdered Gloves for people with allergies to conventional glove powders.\n    Each of the three types are coloured coded for easy identification.\n    Perspex Gloves box dispensers available\n',
        CONV('0', 2, 10) + 0, '2023-08-01 00:16:08.000000', NULL, NULL, NULL, NULL),
       (UNHEX('6183F94A300841E68553E006883CC471'), 'Dermovate (Clobetasol Propionate) 0.05% Cream', 7.50,
        'https://www.sidechef.com/recipe/81372375-5170-4e31-884d-b5f7070fb924.jpg?d=1408x1120',
        'If you suffer from chronic skin flare ups and other corticosteroid creams have not worked, Dermovate could help. \n\nWith OxfordOnlinePharmacy it is easier than ever to get your Dermovate prescription online: no waiting times, no hassle.\n\nDermovate is a strong topical steroid which is effective in the treatment of severe skin conditions such as eczema and psoriasis.\n\nDermovate is a cream that contains the active ingredient Clobetasol Propionate, which is a topical corticosteroid (topical means that it is applied direct to affected area, and a corticosteroid reduces inflammation). Dermovate is stronger than other corticosteroids which is only available on prescription. Dermovate is only to be used when other steroid creams have not been effective.',
        CONV('0', 2, 10) + 0, '2023-08-01 00:16:08.000000', NULL, NULL, NULL, NULL),
       (UNHEX('845CEA8132FE4909917EF31CFF421199'), 'Cura-Heat Back and Shoulder Pain 3 Heat Packs', 2.77,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Biscuits_in_Ghana.jpg/220px-Biscuits_in_Ghana.jpg',
        'Cura-Heat Back & Shoulder Pain 3 Heat Packs is a heat patch which gives 12 hour relief from muscles and joint aches and pains. It works by increasing the blood circulation in the area which in turn reduces the stiffness and relaxes the sore muscles. Cura-Heat Back & Shoulder Pain can be bought from OxfordPharmacyOnline.',
        CONV('0', 2, 10) + 0, '2023-08-01 00:16:08.000000', NULL, NULL, NULL, NULL),
       (UNHEX('EC9A68C1F73A4E6197074EAC44D77C65'), 'TEPE NOVA SOFT TOOTHBRUSH 80 PACK', 15.99,
        'https://static.onecms.io/wp-content/uploads/sites/43/2023/01/30/70935-taqueria-style-tacos-mfs-3x2-35.jpg',
        'Nova has a tapered brush head with an active tip for increased access. It is specially efficient around the posterior teeth and other difficult to reach areas. The end-rounded filaments ensure gentle cleaning. Non-slip handle with thumb pad. Neck can be angled without heating. Blue tip: medium, yellow tip: soft, pink tip: x-soft. ',
        CONV('0', 2, 10) + 0, '2023-08-01 00:16:08.000000', NULL, NULL, NULL, NULL);


INSERT INTO `sellers` (`id`, `name`, `version`, `deleted`, `created_on`, `updated_on`, `created_by`, `last_modified_by`)
VALUES (UNHEX('589D60FD03B54B4AA0147EAD0DDD02D1'), '100mg', NULL, NULL, '2023-08-01 00:16:08.000000', NULL, NULL, NULL),
       (UNHEX('629269FF521843AABB7ADB8858CE6CFE'), 'Johnson& Johnson', NULL, NULL, '2023-08-01 00:16:08.000000', NULL,
        NULL, NULL);

INSERT INTO `products_categories` (`fk_product_id`, `fk_category_id`, `deleted`, `created_on`, `updated_on`)
VALUES (UNHEX('0D1651A24BEE41528FB8C9E57D0C7878'), UNHEX('E950EEB86746488CA8F3CF519D08FB6D'), NULL,
        '2023-08-01 00:16:08.000000', NULL),
       (UNHEX('1829FF024BE34870A4155349E36399F1'), UNHEX('7E98551090CF4DD8B4620549B1A11A0D'), NULL,
        '2023-08-01 00:16:08.000000', NULL),
       (UNHEX('6183F94A300841E68553E006883CC471'), UNHEX('C6226BC750B341A7A2C1D0BB3FB8E3B3'), NULL,
        '2023-08-01 00:16:08.000000', NULL),
       (UNHEX('845CEA8132FE4909917EF31CFF421199'), UNHEX('C6226BC750B341A7A2C1D0BB3FB8E3B3'), NULL,
        '2023-08-01 00:16:08.000000', NULL),
       (UNHEX('EC9A68C1F73A4E6197074EAC44D77C65'), UNHEX('8DF057C3D91949BD92ABC06F4268EE5F'), NULL,
        '2023-08-01 00:16:08.000000', NULL);


INSERT INTO `products_sellers` (`fk_product_id`, `fk_seller_id`, `deleted`, `created_on`, `updated_on`)
VALUES (UNHEX('0D1651A24BEE41528FB8C9E57D0C7878'), UNHEX('589D60FD03B54B4AA0147EAD0DDD02D1'), NULL,
        '2023-08-01 00:16:08.000000', NULL),
       (UNHEX('1829FF024BE34870A4155349E36399F1'), UNHEX('629269FF521843AABB7ADB8858CE6CFE'), NULL,
        '2023-08-01 00:16:08.000000', NULL),
       (UNHEX('6183F94A300841E68553E006883CC471'), UNHEX('589D60FD03B54B4AA0147EAD0DDD02D1'), NULL,
        '2023-08-01 00:16:08.000000', NULL),
       (UNHEX('845CEA8132FE4909917EF31CFF421199'), UNHEX('629269FF521843AABB7ADB8858CE6CFE'), NULL,
        '2023-08-01 00:16:08.000000', NULL),
       (UNHEX('EC9A68C1F73A4E6197074EAC44D77C65'), UNHEX('589D60FD03B54B4AA0147EAD0DDD02D1'), NULL,
        '2023-08-01 00:16:08.000000', NULL);



INSERT INTO `users` (`id`, `username`, `password`, `firstname`, `lastname`, `account_number`, `is_account_locked`,
                     `is_account_expired`, `is_credentials_expired`, `is_enabled`, `deleted`, `balance`, `version`,
                     `updated_on`, `created_on`, `created_by`, `last_modified_by`)
VALUES (UNHEX('F84136E1A60F4890BC8D0C82469CF37A'), 'user',
        '{bcrypt}$2a$10$8ND7peb4Dpg.1s0AsmXqne/HMkXbV9zkpAu76Bb9v0MqUmBWC/FNm', 'user', 'user',
        '615305E4-9882-4AD7-BACD-6384D71820B6', CONV('0', 2, 10) + 0, CONV('0', 2, 10) + 0, CONV('0', 2, 10) + 0,
        CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, 1000.00, NULL, NULL, '2023-08-01 00:16:08.000000', NULL, NULL),
       (UNHEX('FC19FD13D8A94DF992915221C40E9898'), 'admin',
        '{bcrypt}$2a$10$NHuwmKM1AxCDo4Tp9At./OqO7nbmKdpmgsdabtMIQu4B.hkaXqb7.', 'admin', 'admin',
        '9B4F4326-74B9-499C-9B6D-D94794476872', CONV('0', 2, 10) + 0, CONV('0', 2, 10) + 0, CONV('0', 2, 10) + 0,
        CONV('1', 2, 10) + 0, CONV('0', 2, 10) + 0, 684.44, NULL, NULL, '2023-08-01 00:16:08.000000', NULL, NULL);

INSERT INTO `users_permissions` (`fk_user_id`, `fk_permission_id`, `created_on`, `updated_on`, `deleted`)
VALUES (UNHEX('F84136E1A60F4890BC8D0C82469CF37A'), UNHEX('0A1105A198FB4428894278D33AB2F634'),
        '2023-08-01 00:16:08.000000', NULL, NULL),
       (UNHEX('FC19FD13D8A94DF992915221C40E9898'), UNHEX('0A1105A198FB4428894278D33AB2F634'),
        '2023-08-01 00:16:08.000000', NULL, NULL),
       (UNHEX('FC19FD13D8A94DF992915221C40E9898'), UNHEX('5967FBC7766D43C0A3FAD1C9FA544381'),
        '2023-08-01 00:16:08.000000', NULL, NULL);

INSERT INTO `orders` (`id`, `fk_user_id`, `customer_name`, `total_price`, `deleted`, `created_on`, `updated_on`,
                      `created_by`, `last_modified_by`, `version`)
VALUES (UNHEX('031C6D09B62A4881A8549D45EF650C98'), UNHEX('FC19FD13D8A94DF992915221C40E9898'), 'admin admin', 75.04,
        CONV('0', 2, 10) + 0, '2023-08-01 00:17:48.000000', NULL, NULL, NULL, NULL),
       (UNHEX('37A8C805C61A4D83A7E34DF8BA843097'), UNHEX('FC19FD13D8A94DF992915221C40E9898'), 'admin admin', 137.77,
        CONV('0', 2, 10) + 0, '2023-08-01 10:54:09.000000', NULL, NULL, NULL, NULL),
       (UNHEX('A1A62C2839B24812A41DA489CA61A3E2'), UNHEX('FC19FD13D8A94DF992915221C40E9898'), 'admin admin', 102.75,
        CONV('0', 2, 10) + 0, '2023-08-01 00:17:21.000000', NULL, NULL, NULL, NULL);


INSERT INTO `order_items` (`product_name`, `unit_price`, `quantity`, `fk_order_id`, `deleted`)
VALUES ('SYRINGE INTRALIGAMENTAL 1.8ML EURO THREAD', 20.55, 5, UNHEX('A1A62C2839B24812A41DA489CA61A3E2'),
        CONV('0', 2, 10) + 0),
       ('Cura-Heat Back and Shoulder Pain 3 Heat Packs', 2.77, 4, UNHEX('031C6D09B62A4881A8549D45EF650C98'),
        CONV('0', 2, 10) + 0),
       ('TEPE NOVA SOFT TOOTHBRUSH 80 PACK', 15.99, 4, UNHEX('031C6D09B62A4881A8549D45EF650C98'), CONV('0', 2, 10) + 0),
       ('Cura-Heat Back and Shoulder Pain 3 Heat Packs', 2.77, 1, UNHEX('37A8C805C61A4D83A7E34DF8BA843097'),
        CONV('0', 2, 10) + 0),
       ('Dermovate (Clobetasol Propionate) 0.05% Cream', 7.50, 18, UNHEX('37A8C805C61A4D83A7E34DF8BA843097'),
        CONV('0', 2, 10) + 0);
