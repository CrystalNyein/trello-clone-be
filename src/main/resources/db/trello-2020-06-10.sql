USE trello;

ALTER TABLE `account` DROP COLUMN `id`;

-- add a new column named `checked` to `checklist` table
ALTER TABLE `checklist` ADD COLUMN `checked` TINYINT(1) NOT NULL DEFAULT 0 AFTER `item`;