use trello_clone;

-- account
update `account` set `verified`=0 where `verified` is null;
alter table `account` modify column `verified` tinyint(1) not null default 0;

update `account` set `date_created`=current_timestamp where `date_created` is null;
alter table `account` modify column `date_created` timestamp not null default current_timestamp;

update `account` set `last_updated`=current_timestamp where `last_updated` is null;
alter table `account` modify column `last_updated` timestamp not null default current_timestamp on update current_timestamp;

-- card
update `card` set `status`=1 where `status` is null;
alter table `card` modify column `status` tinyint unsigned not null default 1;

update `card` set `date_created`=current_timestamp where `date_created` is null;
alter table `card` modify column `date_created` timestamp not null default current_timestamp;

update `card` set `last_updated`=current_timestamp where `last_updated` is null;
alter table `card` modify column `last_updated` timestamp not null default current_timestamp on update current_timestamp;

-- list
update `list` set `status`=1 where `status` is null;
alter table `list` modify column `status` tinyint unsigned not null default 1;

update `list` set `date_created`=current_timestamp where `date_created` is null;
alter table `list` modify column `date_created` timestamp not null default current_timestamp;

update `list` set `last_updated`=current_timestamp where `last_updated` is null;
alter table `list` modify column `last_updated` timestamp not null default current_timestamp on update current_timestamp;

-- card_member
update `card_member` set `date_created`=current_timestamp where `date_created` is null;
alter table `card_member` modify column `date_created` timestamp not null default current_timestamp;

update `card_member` set `last_updated`=current_timestamp where `last_updated` is null;
alter table `card_member` modify column `last_updated` timestamp not null default current_timestamp on update current_timestamp;

CREATE TABLE `label` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `color` varchar(30) NOT NULL,
  `status` tinyint unsigned NOT NULL DEFAULT '1',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `card_label` (
  `card_id` int NOT NULL,
  `label_id` int NOT NULL,
  `position` tinyint unsigned,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`card_id`,`label_id`),
  CONSTRAINT `fk_card_id` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_label_id` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`) ON DELETE CASCADE
);

CREATE TABLE `checklist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `card_id` int NOT NULL,
  `title` varchar(200),
  `item` text NOT NULL,
  `position` tinyint unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `card_id` (`card_id`),
  CONSTRAINT `fk_chkli_card_id` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`) ON DELETE CASCADE
);
