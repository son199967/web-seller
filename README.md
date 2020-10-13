# web-seller
set table sql

ALTER TABLE user MODIFY
    fist_name VARCHAR(255)
        CHARACTER SET utf8mb4
            COLLATE utf8mb4_unicode_ci;

# For each database:
ALTER DATABASE database_name CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
# For each table:
ALTER TABLE table_name CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
# For each column:
ALTER TABLE table_name CHANGE column_name column_name VARCHAR(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
# (Don’t blindly copy-paste this! The exact statement depends on the column type, maximum length, and other properties. The above line is just an example for a `VARCHAR` column.)